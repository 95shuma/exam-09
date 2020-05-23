<#import "partials/main1.ftlh" as main />

<@main.renderWith styles=["css/login.css"]>
    <form class="box-1" action="/theme/addTheme" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <h1>Add theme</h1>
        <input type="text" name="name" placeholder="Name">
        <input type="text" name="text" placeholder="Text">
        <button type="submit">Add</button>
    </form>
</@main.renderWith>