<#import "parts/common.ftl" as c>

<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Поиск по тегу">
            <button type="submit" class="btn btn-primary ml-2">Найти</button>
        </form>
    </div>
</div>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Добавить новое сообщение
    </a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control" name="text" placeholder="Введите сообщение" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="tag" placeholder="Тэг">
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Выберите файл</label>
                </div>
            </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
    </form>
    </div>
</div>

<div class="card-columns">
    <#list messages as message>

    <div class="card my-3">
            <#if message.filename??>
                <img srs="/uploads/${message.filename}" class="card-img-top">
            </#if>
        <div class="m-2">
            <span>${message.text}</span>
            <i>${message.tag}</i>
        </div>
        <div class="card-footer text-muted">
            ${message.authorName}
        </div>
    </div>
    <#else>
    Сообщения отсутствуют
    </#list>
</div>
</@c.page>