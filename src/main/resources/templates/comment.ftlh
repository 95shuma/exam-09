<#import "partials/main.ftlh" as main />
<@main.renderWith scripts=["js/comments.js","https://code.jquery.com/jquery-3.4.1.slim.min.js","https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"] styles=["css/main.css","https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <#if theme??>
        <div class="row">
            <div class="col-5">${theme.name}</div>
            <div class="col-2">${theme.ldt}</div>
            <div class="col-2">${theme.user.login}</div>
        </div>
        <div class="row">
            <div class="col-12">
                ${theme.text}
                <#if user??>
                    <p style="font-size: 2em"><button type="button" class="btn btn-info" data-toggle="modal1" data-target="#exampleModal1">Add new comment</button></p>
                </#if>
            </div>
        </div>
        <#if comments??>
            <#list comments as comment>
                <a href="/theme/${theme.id}"><div class="row">
                        <div class="col-2">
                            <p><i class="fas fa-user-circle" style="font-size: 3em; color: black"></i></p>
                            <p>${comment.user.login}</p>
                        </div>
                        <div class="col-10">
                            <p>${comment.ldt}</p>
                            <p>${comment.text}</p>
                        </div>
                    </div></a>
            </#list>
        </#if>
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