<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Vegetable</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 500px; margin: 0 auto; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .btn {
            padding: 10px 20px;
            background: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .back-link { margin-top: 15px; display: inline-block; }
        .error { color: red; font-weight: bold; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Редактировать овощ</h1>

        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>

        <form action="edit-vegetable" method="post">
            <input type="hidden" name="id" value="${vegetable.id}">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${vegetable.name}" required>
            </div>
            <div class="form-group">
                <label for="calories">Calories:</label>
                <input type="number" id="calories" name="calories" value="${vegetable.calories}" min="0" required>
            </div>
            <button type="submit" class="btn">Обновить</button>
        </form>
        <a href="veggies" class="back-link">Назад к списку</a>
    </div>
</body>
</html>