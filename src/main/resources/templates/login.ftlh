<#import "partials/main1.ftlh" as main />

<@main.renderWith styles=["css/login.css"]>
    <form class="box-1" action="/login" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <h1>Login</h1>
        <input type="text" name="username" placeholder="Email">
        <input type="password" name="password" placeholder="Password">
        <button type="submit">Login</button>
    </form>
</@main.renderWith>