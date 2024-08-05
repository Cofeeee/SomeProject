<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Caterberg - Vote for Cats</title>
    <style><#include "css/vote.css"></style>
</head>
<body>
<header>
    <h1>Caterberg</h1>
</header>
<main>
    <h2>Проголосуй за своего любимого кота!</h2>
    <form id="voteForm" action="/vote" method="post">
        <input type="hidden" name="userId" value="${userId}"/>
        <input type="hidden" id="selectedCatId" name="catId" value=""/>
        <div class="cats-container">
            <div class="cat-item" data-cat-id="${cat1.id()}">
                <img src="${cat1.imageUrl()}" alt="${cat1.name()}"/>
                <p>${cat1.name()}</p>
                <button type="button" class="vote-button">Vote for this cat</button>
            </div>
            <div class="cat-item" data-cat-id="${cat2.id()}">
                <img src="${cat2.imageUrl()}" alt="${cat2.name()}"/>
                <p>${cat2.name()}</p>
                <button type="button" class="vote-button">Vote for this cat</button>
            </div>
        </div>
    </form>
    <script>
        document.querySelectorAll('.cat-item').forEach(item => {
            item.addEventListener('click', () => {
                const catId = item.getAttribute('data-cat-id');
                document.getElementById('selectedCatId').value = catId;
                document.getElementById('voteForm').submit();
            });
        });

        document.querySelectorAll('.vote-button').forEach(button => {
            button.addEventListener('click', (e) => {
                e.stopPropagation(); // Prevents click event from triggering form submission
                const catId = button.closest('.cat-item').getAttribute('data-cat-id');
                document.getElementById('selectedCatId').value = catId;
                document.getElementById('voteForm').submit();
            });
        });
    </script>
</main>
</body>
</html>
