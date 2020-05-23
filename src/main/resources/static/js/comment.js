'use strict';

function addComment(id) {
    let form = document.getElementById("form-comment");
    let data = new FormData(form);
    let name = "_csrf"
    let value = document.getElementsByName("_csrf_token")[0].getAttribute("content")
    data.append(name, value);
    data.append("id",id);

    fetch("http://localhost:8000/theme/addComment", {
        method: 'POST',
        body: data
    }).then(r => r.json());
    window.location.replace("http://localhost:8000/theme/");
}

$('#myModal').on('shown.bs.modal', function () {
    $('#myInput').trigger('focus')
})