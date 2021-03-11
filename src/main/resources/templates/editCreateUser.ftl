<#import "parts/common.ftl" as c>

<@c.page "none">
    <div>
        <form method="post" action="/createuser/show">
            <h3 style="color: #1e90ff">Редактирование пользователя</h3>
            <table  style="margin-top: 20px">
                <thead align="center">
                <tr>
                    <th scope="col">Логин</th>
                    <th scope="col">Пароль</th>
                    <th scope="col">ФИО</th>
                    <th scope="col">Телефон</th>
                    <th scope="col">Электронная почта</th>
                </tr>
                <tr align="center">
                    <th><input class="form-control" type="text" name="username" value="${user.username}"></th>
                    <th><input class="form-control" type="text" name="password" value="${user.password}"></th>
                    <th><input class="form-control" type="text" name="fio" value="${user.fio}"></th>
                    <th><input class="form-control" type="text" name="phone" value="${user.phone}"></th>
                    <th><input class="form-control" type="email" name="email" value="${user.email}"></th>
                </tr>
                </thead>
            </table>
            <input type="hidden" value="${user.id}" name="id">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button type="submit" class="btn btn-secondary">Изменить</button>
        </form>
    </div>
</@c.page>