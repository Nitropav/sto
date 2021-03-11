<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page "none">

<div class="row">

    <div class="col-lg-6 new-col-lg-6">
        <div class="panel panel-default" style="margin-top:45px">
            <div class="panel-heading">
                <h3 class="panel-title">Записаться на СТО</h3>
            </div>
            <div class="panel-body">
                <form method="post">
                    <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">

                    <div class="container-fluid">
                        <label> Выберите авто: </label>
                        <select name="choiceCar" class="mdb-select md-form" data-style="btn-info">
                            <#list cars as car>
                                <option value="${car.carname}">${car.carname}</option>
                            </#list>
                        </select>
                    </div>

                    <div class="container-fluid">
                        <label> Выберите услугу: </label>
                        <select name="choiceService" class="selectpicker" data-style="btn-info">
                            <#list services as service>
                                <option value="${service.name}">${service.name}</option>
                            </#list>
                        </select>
                    </div>

                    <input id="input" name="datatimestart" width="312"/>
                    <script>
                        $('#input').datetimepicker({footer: true, modal: true});
                    </script>


                    <button type="submit" class="btn btn-info" style="margin-left: 35%;margin-right: 20%">Добавить
                    </button>
                </form>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Авто</th>
                        <th scope="col">Услуга</th>
                        <th scope="col">Дата</th>
                        <th scope="col">К оплате</th>
                    </tr>
                    </thead>
                    <#if flag>
                        <#list OrderLines as orderLine>
                            <tbody>
                            <tr>
                                <td>${orderLine[0]}</td>
                                <td>${orderLine[1]}</td>
                                <td>${orderLine[2]}</td>
                                <td>${orderLine[3]}</td>
                            </tr>
                            </tbody>
                        </#list>
                    <h3>Сумма заказа: ${sum}</h3>
                    <#--<form method="post" action="/registerOnService/confirm">-->
                        <#--<input type="hidden" name="confirm">-->
                        <#--<input type="hidden" name="id_user"  value="${id}>-->
                        <#--<input type="hidden" name="_csrf" value="${_csrf.token}">-->
                        <#--<button class="btn btn-outline-primary" type="submit">Готово</button>-->
                    <#--</form>-->
                    <a class="btn btn-info" href="/">Готово</a>
                    </#if>
                </table>
            </div>
        </div>

    </div>

    <div class="col-lg-6 new-col-lg-6">
        <h3 style="color: #1e90ff">Запись на обслуживание</h3>
    </div>
</div>
<#--<input id="timepicker" width="276" />-->
<#--<script>-->
<#--$('#timepicker').timepicker({-->
<#--uiLibrary: 'bootstrap4'-->
<#--});-->
<#--</script>-->


<#--<div class="row">-->

<#--<div class="col-lg-3 new-col-lg-3">-->
<#--<div class="product-item">-->
<#--<div>-->
<#--<img src="/static/diagnostic.png" class="img-resp">-->
<#--</div>-->
<#--<h4><a class="text-in_block"> Диагностика авто </a></h4>-->
<#--<div class="add-to-card-btn">-->
<#--<a href="../more/about.html#opyt" class="btn btn-danger text-font">-->
<#--Подробнее-->
<#--</a>-->
<#--</div>-->
<#--</div>-->
<#--</div>-->

<#--<div class="col-lg-3 new-col-lg-3">-->
<#--<div class="product-item">-->
<#--<div>-->
<#--<img src="/static/repair.png" class="img-resp">-->
<#--</div>-->
<#--<h4><a class="text-in_block"> Любой ремонт </a></h4>-->
<#--<div class="add-to-card-btn">-->
<#--<a href="../more/about.html#garantiya" class="btn btn-danger text-font">-->
<#--Подробнее-->
<#--</a>-->
<#--</div>-->
<#--</div>-->
<#--</div>-->

<#--<div class="col-lg-3 new-col-lg-3">-->
<#--<div class="product-item">-->
<#--<div>-->
<#--<img src="/static/look.jpg" class="img-resp">-->
<#--</div>-->
<#--<h4><a class="text-in_block"> Внешний вид </a></h4>-->
<#--<div class="add-to-card-btn">-->
<#--<a href="../more/about.html#skidki" class="btn btn-danger text-font">-->
<#--Подробнее-->
<#--</a>-->
<#--</div>-->
<#--</div>-->
<#--</div>-->

<#--<div class="col-lg-3 new-col-lg-3">-->
<#--<div class="product-item">-->
<#--<div>-->
<#--<img src="/static/clear.png"img-resp">-->
<#--</div>-->
<#--<h4><a class="text-in_block"> Автомойка </a></h4>-->
<#--<div class="add-to-card-btn">-->
<#--<a href="../more/about.html#opt" class="btn btn-danger text-font">-->
<#--Подробнее-->
<#--</a>-->
<#--</div>-->
<#--</div>-->
<#--</div>-->

<#--</div>-->


</@c.page>