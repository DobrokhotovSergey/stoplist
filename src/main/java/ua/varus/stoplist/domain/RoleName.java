package ua.varus.stoplist.domain;




public enum RoleName {
/*
*
 ROLE_SECURITY_ADMIN -
просмотр всех полей
редактирование данных
удаление записей
удаление доступов пользователей уровня Security, Operator

ROLE_SECURITY -
просмотр всех полей
редактирование данных
удаление записей

ROLE_RISK_ADMIN
просмотр ограниченного списка полей - поле “Комментарий скрыто”
редактирование данных
удаление записей
удаление доступа пользователя любого уровня
добавление нового кодификатора стоп-листа

ROLE_OPERATOR
просмотр ограниченного списка полей - поле “Комментарий скрыто”

* */

    ROLE_SADM("ROLE_SADMIN"),
    ROLE_SEC("ROLE_SECURITY"),
    ROLE_RADM("ROLE_RADMIN"),
    ROLE_OPER("ROLE_OPERATOR");

    private String name;

    RoleName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        System.out.println(RoleName.ROLE_OPER.name instanceof String);
    }

}


