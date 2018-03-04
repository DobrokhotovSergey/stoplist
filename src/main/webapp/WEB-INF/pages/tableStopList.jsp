<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="modal modal-form fade bs-example-modal-nm" id="searchStoplist-modal"  role="dialog" aria-hidden="true" style="display: none;">
    <div class="modal-dialog modal-nm">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="searchStoplist-title">Поиск по таблице Стоп-лист</h4>
            </div>
            <div class="modal-body">
                <form id="searchStoplist-form" class="form-horizontal form-label-left">
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="inn-search">ИНН/ОКПО
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input id="inn-search" type="text" name="inn" required title="введите 8 цифр для ОКПО или 10 цифр для ИНН" pattern="\d{8}|\d{10}" class="form-control col-md-7 col-xs-12" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="inn-search">ФИО / Название юрлица
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text" id="fio-search" name="fio" required class="form-control col-md-7 col-xs-12"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="inn-search">дата рождения
                        </label>

                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input id="birthdate-search" name="birthdate" class="form-control col-md-7 col-xs-12 datepicker"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                        <input type="submit" value="Найти" class="btn btn-success" id="searchStoplist-submit"/>
                    </div>
                </form>


            </div>
        </div>
    </div>
</div>
<div class="modal modal-form fade bs-example-modal-nm" id="deleteStoplist-modal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
    <div class="modal-dialog modal-nm">
        <div class="modal-content">

            <input id="id-stoplist-delete" style="display: none"/>
            <input id="pos-stoplist-delete" style="display: none"/>

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Удаление записи</h4>
            </div>
            <div class="modal-body">
                <form id="deleteUser-form" data-parsley-validate class="form-horizontal form-label-left">
                    Вы уверены, что хотите удалить запись?
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                <button type="submit" class="btn btn-success" id="deleteStoplist-submit">Подтвердить</button>
            </div>

        </div>
    </div>
</div>
<div class="modal modal-form fade bs-example-modal-nm" id="addStoplist-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-nm">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addStoplist-title">Добавление новой записи</h4>
            </div>
            <div class="modal-body">
                <form id="stoplist-form" data-parsley-validate class="form-horizontal form-label-left">

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="phys-add">Субьект<span class="required">*</span></label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <label class="radio-inline">
                                    физ лицо:
                                    <input type="radio" class="flat-red radio-inline" name="status" id="phys-add" value="phys" checked>
                                </label>
                                <label class="radio-inline">
                                    юр лицо:
                                    <input type="radio" class="flat-red radio-inline" name="status" id="jur-add" value="jur">
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="inn-add">ИНН/ОКПО
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12" >
                                <input id="inn-add" name="inn" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="fio-add">ФИО / Название юрлица<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text"  id="fio-add" name="fio" required class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="birthdate-add">Дата рождения
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text" id="birthdate-add" name="birthDate" data-date-format="yyyy-mm-dd" class="form-control col-md-7 col-xs-12 datepicker">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="pasportSerial-add">Серия паспорта
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text" id="pasportSerial-add" name="pasportSerial"  class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="pasportNumber-add">Номер паспорта
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text" id="pasportNumber-add" name="pasportNumber" class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="comment-add">Комментарий<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text" id="comment-add" name="comment" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="codificator-select-add">Кодификатор стоп-листа<span class="required">*</span>
                        </label>
                        <div id="codificator-select"></div>
                        <%--<div class="col-md-6 col-sm-6 col-xs-12">--%>
                            <%--<input type="text" id="codificator-add" name="codificator" pattern="\d{2}" required="required" class="form-control col-md-7 col-xs-12">--%>
                        <%--</div>--%>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="company-select-add">Компания, внесшая запись <span class="required">*</span>
                        </label>
                        <div id="company-select"></div>
                        <%--<div class="col-md-6 col-sm-6 col-xs-12">--%>
                            <%--<input type="text" id="company-add" name="createCompany" required="required" class="form-control col-md-7 col-xs-12">--%>
                        <%--</div>--%>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="department-select-add">Подразделение, внесшее запись <span class="required">*</span>
                        </label>
                        <div id="department-select"></div>
                        <%--<div class="col-md-6 col-sm-6 col-xs-12">--%>
                            <%--<input type="text" id="department-add" name="createDepartment" required="required" class="form-control col-md-7 col-xs-12">--%>
                        <%--</div>--%>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="source-select-add">Источник <span class="required">*</span>
                        </label>
                        <div id="source-select"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                        <button type="submit" class="btn btn-success" id="addStoplist-submit">Добавить</button>
                    </div>
                </form>

            </div>



        </div>
    </div>
</div>

<div class="modal modal-form fade bs-example-modal-nm" id="editStoplist-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-nm">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editStoplist-title">Редактирование текущей записи</h4>
            </div>
            <div class="modal-body">
                <input id="id-stoplist-edit" style="display: none"/>
                <input id="pos-stoplist-edit" style="display: none"/>

                <form id="editStoplist-form" data-parsley-validate class="form-horizontal form-label-left">

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="phys-edit">Субьект<span class="required">*</span></label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <label class="radio-inline">
                                физ лицо:
                                <input type="radio" class="flat-red radio-inline" name="status" id="phys-edit" value="phys" checked required="required">
                            </label>
                            <label class="radio-inline">
                                юр лицо:
                                <input type="radio" class="flat-red radio-inline" name="status" id="jur-edit" value="jur" required="required">
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="inn-add">ИНН/ОКПО
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12" >
                            <input id="inn-edit" name="inn" class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="fio-add">ФИО / Название юрлица<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text"  id="fio-edit" name="fio" required class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="birthdate-add">Дата рождения
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text" id="birthdate-edit" name="birthDate" data-date-format="yyyy-mm-dd" class="form-control col-md-7 col-xs-12 datepicker">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="pasportSerial-add">Серия паспорта
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text" id="pasportSerial-edit" name="pasportSerial"  class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="pasportNumber-add">Номер паспорта
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text" id="pasportNumber-edit" name="pasportNumber" class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                <sec:authorize access="hasRole('ROLE_RADMIN') or hasRole('ROLE_SADMIN') or hasRole('ROLE_SECURITY')">
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="comment-add">Комментарий<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text" id="comment-edit" name="comment" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                </sec:authorize>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="codificator-select-ed-add">Кодификатор стоп-листа<span class="required">*</span>
                        </label>
                        <div id="codificator-select-ed"></div>
                        <%--<div class="col-md-6 col-sm-6 col-xs-12">--%>
                        <%--<input type="text" id="codificator-add" name="codificator" pattern="\d{2}" required="required" class="form-control col-md-7 col-xs-12">--%>
                        <%--</div>--%>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="company-select-ed-add">Компания, внесшая запись <span class="required">*</span>
                        </label>
                        <div id="company-select-ed"></div>
                        <%--<div class="col-md-6 col-sm-6 col-xs-12">--%>
                        <%--<input type="text" id="company-add" name="createCompany" required="required" class="form-control col-md-7 col-xs-12">--%>
                        <%--</div>--%>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="department-select-ed-add">Подразделение, внесшее запись <span class="required">*</span>
                        </label>
                        <div id="department-select-ed"></div>
                        <%--<div class="col-md-6 col-sm-6 col-xs-12">--%>
                        <%--<input type="text" id="department-add" name="createDepartment" required="required" class="form-control col-md-7 col-xs-12">--%>
                        <%--</div>--%>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="source-select-ed-add">Источник <span class="required">*</span>
                        </label>
                        <div id="source-select-ed"></div>
                        <%--<div class="col-md-6 col-sm-6 col-xs-12">--%>
                            <%--<input type="text" id="source-edit" name="source" required="required" class="form-control col-md-7 col-xs-12">--%>
                        <%--</div>--%>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                        <button type="submit" class="btn btn-success" id="editStoplist-submit">ОК</button>
                    </div>
                </form>

            </div>



        </div>
    </div>
</div>

<input id="isRoleSADMIN" access="hasRole('ROLE_SADMIN')" type="text" class="hide" value="true"/>
<input id="isRoleSECURITY" access="hasRole('ROLE_SECURITY')" type="text" class="hide" value="true"/>
<input id="isRoleRADMIN" access="hasRole('ROLE_RADMIN')" type="text" class="hide" value="true"/>
<input id="isRoleOPERATOR" access="hasRole('ROLE_OPERATOR')" type="text" class="hide" value="true"/>

<div class="box box-primary" id="userDiv" class="x_panel">
    <div class="box-header">
        <i class="ion ion-clipboard"></i>
        <h3 class="box-title">Таблица Стоп лист</h3>
    </div>
    <div class="box-body">
        <div class="x_content">
            <div class="table-responsive">
                <table id="stoplist-table" class="table no-margin" style="width: 100%">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>ИНН</th>
                        <th>ОКПО</th>
                        <th>ФИО/Название юрлица</th>
                        <th>Дата рождения</th>
                        <th>Серия паспорта</th>
                        <th>Номер паспорта</th>
                        <th>Дата внесения</th>
                        <th>Дата редактирования</th>
                        <th>Статус записи</th>
                        <th>Дата удаления</th>
                        <th>Комментарий</th>
                        <th>Кодификатор стоп-листа</th>
                        <th>Логин сотрудника/системы, внесшей запись</th>
                        <th>Логин сотрудника/системы, редактировавшей запись</th>
                        <th>Компания, внесшая запись</th>
                        <th>Подразделение, внесшее запис</th>
                        <th>источник</th>
                        <sec:authorize access="hasRole('ROLE_RADMIN') or hasRole('ROLE_SADMIN') or hasRole('ROLE_SECURITY')">
                        <th>Редактирование</th>
                        </sec:authorize>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%--<div class="form-group">--%>
    <%--<label class="control-label col-md-3 col-sm-3 col-xs-12" for="codificator-add">Кодификатор стоп-листа<span class="required">*</span>--%>
    <%--</label>--%>
    <%--<div class="col-md-6 col-sm-6 col-xs-12">--%>
        <%----%>
    <%--</div>--%>
<%--</div>--%>


<script>
    $(function () {
        var initDate = new Date();
        initDate.setFullYear(initDate.getFullYear() - 35);
        initDate.setMonth(1-1);
        initDate.setDate(1);

        $('.datepicker').datepicker({
            changeMonth: true,
            changeYear: true,
//            yearRange: "1930:2017",
            yearRange: "-80:-1",
            dateFormat: "yy-mm-dd",
            maxDate: '-1D',
            defaultDate: initDate,
        });

//        ui-datepicker-year
    });

    jQuery(function ($) {
        var $inputs = $('#inn-search,#fio-search');
        $inputs.on('input', function () {
            // Set the required property of the other input to false if this input is not empty.
            $inputs.not(this).prop('required', !$(this).val().length);
        });
    });

    $('#addStoplist-submit').click(function(e){

        var codificatorSelected = $('#codificator-select-add option:selected', '#stoplist-form').val();
        var radioButtonSelected = $('input[name=status]:checked', '#stoplist-form').val();

        if(radioButtonSelected=='phys'){
            $('#inn-add').attr('pattern', '\\d{10}');
            $('#inn-add').attr('title', 'введите 10 цифр для ИНН');
        }else if(radioButtonSelected=='jur') {
            $('#inn-add').attr('pattern', '\\d{8}');
            $('#inn-add').attr('title', 'введите 8 цифр для ОКПО');
        }


        if(codificatorSelected == 1 || codificatorSelected == 3){
            if(radioButtonSelected=='jur'){
                $('#phys-add').iCheck('check');
                $('#jur-add').iCheck('uncheck');
            }
            $('#inn-add').attr('required', 'required');
        }
        else if(codificatorSelected == 4){
            $('#inn-add').attr('required', 'required');
        }

        var $theForm = $('#stoplist-form');
        var check = $theForm[0].checkValidity();

        if (( typeof($theForm[0].checkValidity) == "function" ) && !$theForm[0].checkValidity()) {
            return;
        }
        if(check){
            var form = $('#stoplist-form').serializeObject();


            // form[codificator[]] = $("#codificator-select-add option:selected" ).val();
            var cc = {};
            cc['code'] = $("#codificator-select-add option:selected" ).val();
            form['codificator'] = cc;

            form['createCompany'] = $("#company-select-add option:selected" ).val();
            form['createDepartment'] = $("#department-select-add option:selected" ).val();

            console.log(JSON.stringify(form));
            $('#addStoplist-modal').modal('hide');

            //
            $.ajax({
                type: "post",
                url: "/stoplist/ajax/insertStopList",
                dataType: 'json',
                data:JSON.stringify(form),
                contentType: "application/json; charset=utf-8",
                success: function (item) {
                    tableStopList.clear().draw();
                    // console.log(data);
                    tableStopList.row.add([item.id,item.inn, item.okpo, item.fio, item.birthDate, item.pasportSerial, item.pasportNumber,
                        item.createDate,
                        item.editDate, item.status, item.removeDate, item.comment, item.codificator.name, item.createLoginEmployee,
                        item.editLoginEmployee, item.createCompany, item.createDepartment, item.source,
                        '<a class="btn btn-info edit-stoplist btn-xs"><i class="fa fa-pencil"></i> Ред. </a>  '+
                        '<a class="btn btn-danger delete-stoplist  btn-xs"><i class="fa  fa-trash-o"></i> Удал. </a>']).draw();
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
        }


        return false;

    });

    var tableStopList = $('#stoplist-table').DataTable({
        destroy: true,
        // dom: '<<rB><lf><t>ip>',
        dom: '<Blf<t>ip>',
        columnDefs: [ {
            orderable: false,
            targets  : 'no-sort',
            targets:   [0],
            visible: false,
            searchable: false,
        },{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}


            ],
        // "ordering": false,
        buttons: [
            {
                className: "btn btn-primary btn-search",
                text: '<i style="color:white"></i><span style="color:white"> Поиск </span>',
                action: function () {
                    $('#searchStoplist-modal').modal('show');
                    return false;
                }},
            {
                className: "btn btn-success",
                text: '<i class="fa fa-plus" id="addStoplist-btn" style="color:white"></i><span style="color:white"> Внести запись </span>',
                action: function () {
                    $('#addStoplist-modal').modal('show');
                    return false;
                }}],
        language: {
            url: "resources/datatables.net/Russian.json"
        },
    });

    $( document ).ajaxComplete(function() {

//       function test(){
//           $("#addStoplist-submit" ).attr('disabled', false);
//       }
//        setTimeout(test, 12000);
        $("#addStoplist-submit" ).attr('disabled', false);

    });
function ajaxDrawSelect(company, department, codificator, source){
    $("#addStoplist-submit" ).attr('disabled', true);
    $('#'+company).html('');
    $('#'+department).html('');
    $('#'+codificator).html('');
    $('#'+source).html('');
    $.ajax({
        type: "post",
        url: "/stoplist/ajax/getListCodificators",
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (item) {
            var tepHtmlSelect = '';
            $.each(item, function(index, value) {
                tepHtmlSelect+='<option value="'+item[index].code+'">'+item[index].name+'</option>\n';
            });

            var codficatorSelect = '<div class="col-md-6 col-sm-6 col-xs-12"><select class="form-control" id="'+codificator+'-add">\n' +
                tepHtmlSelect +
                '</select></div>'
            $('#'+codificator).html(codficatorSelect);

            $('#codificator-select-add').on('change', function() {
                var val = this.value;
                if(val != 1 && val !=3 && val !=4){
//                    $('#inn-add').removeAttr('pattern');
                    $('#inn-add').removeAttr('required');
                    $('#inn-add').removeAttr('title');
                }

            });

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

    $.ajax({
        type: "post",
        url: "/stoplist/ajax/getListCompany",
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (item) {
            var tepHtmlSelect = '';
            $.each(item, function(index, value) {
                tepHtmlSelect+='<option value="'+item[index]+'">'+item[index]+'</option>\n';
            });

            var companySelect = '<div class="col-md-6 col-sm-6 col-xs-12"><select class="form-control" id="'+company+'-add">\n' +
                                tepHtmlSelect +
                                '</select></div>';
            $('#'+company).html(companySelect);
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
    $.ajax({
        type: "post",
        url: "/stoplist/ajax/getListDepartment",
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (item) {
            var tepHtmlSelect = '';
            $.each(item, function(index, value) {
                tepHtmlSelect+='<option value="'+item[index]+'">'+item[index]+'</option>\n';
            });

            var companySelect = '<div class="col-md-6 col-sm-6 col-xs-12"><select class="form-control" id="'+department+'-add">\n' +
                tepHtmlSelect +
                '</select></div>';
            $('#'+department).html(companySelect);
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
    $.ajax({
        type: "post",
        url: "/stoplist/ajax/getListSources",
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (item) {
            var tepHtmlSelect = '';
            $.each(item, function(index, value) {
                tepHtmlSelect+='<option value="'+item[index]+'">'+item[index]+'</option>\n';
            });

            var companySelect = '<div class="col-md-6 col-sm-6 col-xs-12"><select class="form-control" id="'+source+'-add">\n' +
                tepHtmlSelect +
                '</select></div>';
            $('#'+source).html(companySelect);
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
}


    $(document).on('show.bs.modal','#addStoplist-modal', function (e) {
        ajaxDrawSelect('company-select', 'department-select', 'codificator-select', 'source-select');

    });
    $(document).on('show.bs.modal','#editStoplist-modal', function (e) {
        ajaxDrawSelect('company-select-ed', 'department-select-ed', 'codificator-select-ed', 'source-select-ed');
    });


    $('#stoplist-table tbody').on('click', 'tr td .edit-stoplist', function(){
        $('#editStoplist-modal').modal('show');

        var data = tableStopList.row( $(this).parents('tr') ).data();
        var position = tableStopList.cell($(this).parents('td') ).index().row;
        console.log(data);

        $('#id-stoplist-edit').text(data[0]);
        $('#pos-stoplist-edit').text(position);


        if(data[1]!=null){
            $('#inn-edit').val(data[1]);
            $('#phys-edit').iCheck('check');
            $('#jur-edit').iCheck('uncheck');
        }
        if(data[2]!=null){
            $('#inn-edit').val(data[2]);
            $('#phys-edit').iCheck('uncheck');
            $('#jur-edit').iCheck('check');
        }
        $('#fio-edit').val(data[3]);
        $('#birthdate-edit').val(data[4]);
        $('#pasportSerial-edit').val(data[5]);
        $('#pasportNumber-edit').val(data[6]);
        $('#comment-edit').val(data[11]);
        $('#codificator-edit').val(data[12]);
        $('#company-edit').val(data[15]);
        $('#department-edit').val(data[16]);
        $('#source-edit').val(data[17]);

    });

    $('#editStoplist-submit').click(function(e){

        var radioButtonSelected = $('input[name=status]:checked', '#editStoplist-form').val();
        if(radioButtonSelected=='phys'){
            $('#inn-edit').attr('pattern', '\\d{10}');
            $('#inn-edit').attr('title', 'введите 10 цифр для ИНН');
        }else if(radioButtonSelected=='jur') {
            $('#inn-edit').attr('pattern', '\\d{8}');
            $('#inn-edit').attr('title', 'введите 8 цифр для ОКПО');
        }

        var $theForm = $('#editStoplist-form');
        var check = $theForm[0].checkValidity();

        if (( typeof($theForm[0].checkValidity) == "function" ) && !$theForm[0].checkValidity()) {
            return;
        }
        if(check){
            var form = $('#editStoplist-form').serializeObject();
            form['id'] = $('#id-stoplist-edit').text();
            var cc = {};
            cc['code'] = $("#codificator-select-ed-add option:selected" ).val();
            form['codificator'] = cc;

            form['createCompany'] = $("#company-select-ed-add option:selected" ).val();
            form['createDepartment'] = $("#department-select-ed-add option:selected" ).val();

            $('#editStoplist-modal').modal('hide');
            $.ajax({
                type: "post",
                url: "/stoplist/ajax/editStoplistRow",
                dataType: 'json',
                data:JSON.stringify(form),
                contentType: "application/json; charset=utf-8",
                success: function (item) {
                    tableStopList.clear().draw();
                    // console.log(data);
                    tableStopList.row.add([item.id,item.inn, item.okpo, item.fio, item.birthDate, item.pasportSerial, item.pasportNumber,
                        item.createDate,
                        item.editDate, item.status, item.removeDate, item.comment, item.codificator.name, item.createLoginEmployee,
                        item.editLoginEmployee, item.createCompany, item.createDepartment, item.source,
                        '<a class="btn btn-info edit-stoplist btn-xs"><i class="fa fa-pencil"></i> Ред. </a>  '+
                        '<a class="btn btn-danger delete-stoplist  btn-xs"><i class="fa  fa-trash-o"></i> Удал. </a>']).draw();
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
        }


        return false;

    });


    $('#stoplist-table tbody').on('click', 'tr td .delete-stoplist', function(){
        $('#deleteStoplist-modal').modal('show');
        var position = tableStopList.cell($(this).parents('td') ).index().row;
        var data = tableStopList.row( $(this).parents('tr') ).data();
        $('#pos-stoplist-delete').text(position);
        $('#id-stoplist-delete').text(data[0]);
    });

    $('#deleteStoplist-submit').on('click', function(){
        var pos = $('#pos-stoplist-delete').text();
        var id = $('#id-stoplist-delete').text();

        $.ajax({
            type: "post",
            url: "/stoplist/ajax/deleteStoplistRow",
            // dataType: 'json',
            data:{id:id},
            success: function (data) {
                $('#deleteStoplist-modal').modal('hide');
                if(data){
                    tableStopList.row(pos).remove() .draw();
                    notifyAfterAjax('success','Запись удалена');
                }else{
                    notifyAfterAjax('danger','Простите, но запись не удалена :(');
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
                notifyAfterAjax('danger','Простите, но запись не удалена :(');
                console.log(xhr);
                console.log(status);
                console.log(error);
            }
        });
    });

    $('#searchStoplist-submit').on('click', function(e) {

        console.log('cc');
//
        var $theForm = $('#searchStoplist-form');
        var check = $theForm[0].checkValidity();
        console.log(check);
        if (( typeof($theForm[0].checkValidity) == "function" ) && !$theForm[0].checkValidity()) {
            return;
        }
        var form = $('#searchStoplist-form').serializeObject();
        $('#searchStoplist-modal').modal('hide');
        $.ajax({
            type: "post",
            url: "/stoplist/ajax/searchStopList",
            dataType: 'json',
            data:JSON.stringify(form),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                 tableStopList.clear().draw();
                 var rows = [];
                 $.each(data, function(i,item){
                 rows[i] = [item.id,item.inn, item.okpo, item.fio, item.birthDate, item.pasportSerial, item.pasportNumber,
                            item.createDate,
                            item.editDate, item.status, item.removeDate, item.comment, item.codificator.name, item.createLoginEmployee,
                            item.editLoginEmployee, item.createCompany, item.createDepartment, item.source,
                     '<a class="btn btn-info edit-stoplist btn-xs"><i class="fa fa-pencil"></i> Ред. </a>  '+
                     '<a class="btn btn-danger delete-stoplist  btn-xs"><i class="fa  fa-trash-o"></i> Удал. </a>'];
                 });
                tableStopList.rows.add(rows).draw();
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
        e.preventDefault()
    });

    //iCheck for checkbox and radio inputs
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
        checkboxClass: 'icheckbox_minimal-blue',
        radioClass   : 'iradio_minimal-blue'
    })
    //Red color scheme for iCheck
    $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
        checkboxClass: 'icheckbox_minimal-red',
        radioClass   : 'iradio_minimal-red'
    })
    //Flat red color scheme for iCheck
    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
        checkboxClass: 'icheckbox_flat-green',
        radioClass   : 'iradio_flat-green'
    })

</script>