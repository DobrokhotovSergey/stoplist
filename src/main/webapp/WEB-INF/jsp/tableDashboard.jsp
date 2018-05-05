<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="modal modal-form fade bs-example-modal-nm" id="addStoplist-codificator-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-nm">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addStoplist-title">Добавление нового кодификатора</h4>
            </div>
            <div class="modal-body">
                <form id="stoplist-codificator-form" data-parsley-validate class="form-horizontal form-label-left">
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="stoplist-codificator-code-add">Код
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12" >
                            <input id="stoplist-codificator-code-add" name="code" pattern="\d{2}" class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="stoplist-codificator-name-add">Название
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12" >
                            <input id="stoplist-codificator-name-add" name="name" class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                        <button type="submit" class="btn btn-success" id="stoplist-codificator-submit">Добавить</button>
                    </div>
                </form>

            </div>



        </div>
    </div>
</div>
<style>
    .input-group-addon {
        border-left-width: 0;
        border-right-width: 0;
    }
    .input-group-addon:first-child {
        border-left-width: 1px;
    }
    .input-group-addon:last-child {
        border-right-width: 1px;
    }
</style>
<div class="box box-primary" id="userDiv" class="x_panel">
    <div class="box-header">
        <i class="ion ion-clipboard"></i>
        <h3 class="box-title">Дашборд</h3>
    </div>
    <div class="box-body">
        <div class="x_content">
                <form id="dashboard-form">
                    <div class="input-group col-xs-6">
                        <span class="input-group-addon">даты первого среза</span>
                        <input type="text" id="date1-dashboard" name="date1" required class="form-control input-sm datepicker"/>
                        <span class="input-group-addon" style="border-left: 0; border-right: 0;">даты второго среза</span>
                        <input type="text" id="date2-dashboard" name="date2" required class="form-control input-sm datepicker" value="" style="margin-left:-1px" />
                        <span class="input-group-btn" style="width:0px;"></span>
                        <button type="submit" id="dashboard-submit" class="form-control input-sm btn btn-primary" value="расчитать" style="margin-left:-3px">расчитать</button>
                        <%--<input type="submit" id="dashboard-submit" class="form-control input-sm btn btn-primary" value="расчитать" style="margin-left:-3px" >--%>
                    </div>
                </form>



            <div class="table-responsive">
                <table id="stoplist-dashboard-table" class="table no-margin" style="width: 100%">
                    <thead>
                    <tr>
                        <th>Код кодификатора стоп-листа</th>
                        <th>Название кодификатора</th>
                        <th>Количество записей на выбранную дату 1</th>
                        <th>Количество записей на выбранную дату 2</th>
                        <th>Прирост</th>

                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script>

    var tableDashboard = $('#stoplist-dashboard-table').DataTable({
        destroy: true,
        // dom: '<<rB><lf><t>ip>',
        dom: '<lf<t>ip>',
        "searching": false,
        columnDefs: [
            {},{},{},{},{}
        ],
        "ordering": false,
        <%--buttons: [--%>
        <%--{--%>
        <%--className: "btn btn-success",--%>
        <%--text: '<sec:authorize access="hasRole(\'ROLE_RADMIN\')"><i class="fa fa-plus" style="color:white"></i><span style="color:white"> Добавить кодификатор </span></sec:authorize>',--%>
        <%--action: function () {--%>
        <%--$('#addStoplist-codificator-modal').modal('show');--%>
        <%--}}],--%>
        language: {
            url: "resources/datatables.net/Russian.json"
        },
    });


    function initializeDatepicker() {

        // var initDate = new Date();
        // initDate.setFullYear(initDate.getFullYear() - 0);
        // initDate.setMonth(1-1);
        // initDate.setDate(1);

        $('.datepicker').datepicker({
            changeMonth: true,
            changeYear: true,
            // yearRange: "-1:-1",
            dateFormat: "yy-mm-dd",
            maxDate: '-1D',
            // defaultDate: initDate,
        });

    };
    initializeDatepicker();

    $('#dashboard-submit').on('click', function () {

        var $theForm = $('#dashboard-form');
        var check = $theForm[0].checkValidity();

        if(check){


            var form = $('#dashboard-form').serializeObject();

            // console.log(JSON.stringify(form));

            $.ajax({
                type: "post",
                url: "stoplist/ajax/calcDashboard",
                dataType: 'json',
                data:JSON.stringify(form),
                contentType: "application/json; charset=utf-8",
                success: function (data) {

                    tableDashboard.clear().draw();
                    var rows = [];
                    $.each(data, function(i,item){
                        var code = item.codificator.code
                        if(code==-1){
                            code='';
                        }
                        rows[i] = [code, item.codificator.name, item.count1, item.count2, item.diff];
                    });
                    tableDashboard.rows.add(rows).draw();
                    if(rows.length==0){
                        notifyAfterAjax('danger','null');
                    }
                },
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(header, token);
                    beforeSend();
                },
                complete: function () {
                    NProgress.done();
                    unblock_screen();
                },
                error: function (xhr, status, error) {

                    if(xhr.responseJSON.status == 405){
                        $('#error-modal').modal('show');
                    }
                }
            });



        }
        return false;
    });

</script>