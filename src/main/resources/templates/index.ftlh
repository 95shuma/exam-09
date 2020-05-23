<#import "partials/main.ftlh" as main />
<@main.renderWith styles=["css/main.css","https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <h2>All themes</h2>
    <#if themes??>
        <#list themes as theme>
            <a href="/theme/${theme.id}"><div class="row">
                <div class="col-6">
                    <p>${theme.name}</p>
                </div>
                <div class="col-2">
                    <p>${theme.ldt}</p>
                </div>
                <div class="col-2">
                    <p>${theme.user.login}</p>
                </div>
                <div class="col-2">
                    <p>Answers: ${theme.qty}</p>
                </div>
            </div></a>
        </#list>
    </#if>

    <div>
        <#if hasPrev>
            <a id="loadPrev" href="${prevPageLink!''}">&lt;&lt;&lt;Previous page</a>
        <#else>
            <span id="loadPrev">Previous page</span>
        </#if>

        <#if hasNext>
            <a id="loadNext" href="${nextPageLink!''}" data-default-page-size="${defaultPageSize!5}">Next page&gt;&gt;&gt;</a>
        <#else>
            <span>Next page</span>
        </#if>
    </div>

</@main.renderWith>