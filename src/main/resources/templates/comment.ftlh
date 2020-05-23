<#import "partials/main.ftlh" as main />
<@main.renderWith scripts=["/js/comments.js","https://code.jquery.com/jquery-3.4.1.slim.min.js","https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"] styles=["/css/main.css","https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
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
        <#if hasPrev??>
            <a id="loadPrev" href="${prevPageLink!''}">&lt;&lt;&lt;Previous page</a>
        <#else>
            <span id="loadPrev">Previous page</span>
        </#if>

        <#if hasNext??>
            <a id="loadNext" href="${nextPageLink!''}" data-default-page-size="${defaultPageSize!5}">Next page&gt;&gt;&gt;</a>
        <#else>
            <span>Next page</span>
        </#if>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel1" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-uppercase" id="exampleModalLabel">Create new comment</h5>
                    <button type="button" class="close" data-dismiss="modal1" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="form-comment" >
                        <div class="form-group">
                            <label for="Name2">Comment text</label>
                            <input minlength="5" maxlength="100" name="text" type="text" class="form-control" id="text" aria-describedby="emailHelp" placeholder="Enter comment text">
                        </div>
                        <button type="button" onclick="addComment(${theme.id})" class="btn btn-primary">Add</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


</@main.renderWith>