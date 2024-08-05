<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Top 10 Cats</title>
    <style><#include "css/result.css"></style>
</head>
<body>
<header>
    <h1>Мимимиметр</h1>
    <h2>Топ 10</h2>
</header>
<main>


    <section>
        <table>
            <thead>
            <tr>
                <th>Ранг</th>
                <th>Картинка</th>
                <th>Имя</th>
                <th>Колличество голосов</th>
            </tr>
            </thead>
            <tbody>
            <#list cats as cat>
                <tr>
                    <td>${cat_index + 1}</td>
                    <td><img src="${cat.imageUrl()}" alt="${cat.name()}" width="150"></td>
                    <td>${cat.name()}</td>
                    <td>${cat.voteCount()}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </section>

    <section>
        <form action="/upload" method="post" enctype="multipart/form-data">
            <div>
                <label for="name">Имя кота:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div>
                <label for="file">Выберите изображение:</label>
                <input type="file" id="file" name="file" accept="image/*" required>
            </div>
            <button type="submit">Загрузить</button>
        </form>
        <#if message??>
            <p>${message}</p>
        </#if>
    </section>
</main>
</body>
</html>
