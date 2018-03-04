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

<%--<div class="modal modal-form fade bs-example-modal-nm" id="deleteStoplist-codificator-modal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">--%>
    <%--<div class="modal-dialog modal-nm">--%>
        <%--<div class="modal-content">--%>

            <%--<input id="id-stoplist-codificator-delete" style="display: none"/>--%>
            <%--<input id="pos-stoplist-codificator-delete" style="display: none"/>--%>

            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>--%>
                <%--</button>--%>
                <%--<h4 class="modal-title">Удаление кодификатора</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<form id="deleteUser-form" data-parsley-validate class="form-horizontal form-label-left">--%>
                    <%--Вы уверены, что хотите удалить кодификатор?--%>
                <%--</form>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>--%>
                <%--<button type="submit" class="btn btn-success" id="deleteStoplist-submit">Подтвердить</button>--%>
            <%--</div>--%>

        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<div class="box box-primary" id="userDiv" class="x_panel">
    <div class="box-header">
        <i class="ion ion-clipboard"></i>
        <h3 class="box-title">Таблица Кодификаторов</h3>
    </div>
    <div class="box-body">
        <div class="x_content">
            <sec:authorize access="hasRole('ROLE_RADMIN')">
            <button class="fa fa-plus btn btn-success" id='add-codificator-plus' >Добавить кодификатор</button>
            </sec:authorize>
            <%--<button class="fa fa-plus" id='add-codificator-plus' style="color:white"> Добавить кодификатор</button>--%>

            <div class="table-responsive">

                <table id="stoplist-codificator-table" class="table no-margin" style="width: 100%">
                    <thead>
                    <tr>
                        <th>Код</th>
                        <th>Название</th>
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
$('#add-codificator-plus').on('click', function(){
    $('#addStoplist-codificator-modal').modal('show');
});
    var tableCodificator = $('#stoplist-codificator-table').DataTable({
        destroy: true,
        // dom: '<<rB><lf><t>ip>',
        dom: '<lf<t>ip>',
        columnDefs: [
            {},{}
        ],
        // "ordering": false,
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


    $('#stoplist-codificator-submit').on('click', function(){
        var form = $('#stoplist-codificator-form').serializeObject();
        console.log(form);
        $('#addStoplist-codificator-modal').modal('hide');
        $.ajax({
            type: "post",
            url: "/stoplist/ajax/insertCodificator",
            dataType: 'json',
            data:JSON.stringify(form),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                tableCodificator.clear().draw();

                var rows = [];
                $.each(data, function(i,item){
                    rows[i] = [item.code, item.name];
                });
                tableCodificator.rows.add(rows).draw();

                // tableCodificator.row.add([item.code, item.name]).draw();
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
                notifyAfterAjax('danger','Простите, но Кодификатор не добавлен :(');
                console.log(xhr);
                console.log(status);
                console.log(error);
            }
        });
        return false;
    });

    $.ajax({
        type: "post",
        url: "/stoplist/ajax/getListCodificators",
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data);
            tableCodificator.clear().draw();
            var rows = [];
            $.each(data, function(i,item){
                rows[i] = [item.code, item.name];
            });
            tableCodificator.rows.add(rows).draw();
            if(rows.length==0){
                notifyAfterAjax('danger','Простите, но Ничего не найдено :(');
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
            notifyAfterAjax('danger','Простите, но Ничего не найдено :(');
            console.log(xhr);
            console.log(status);
            console.log(error);
        }
    });
</script>