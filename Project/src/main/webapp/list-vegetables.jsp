<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Vegetables List</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 800px; margin: 0 auto; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
        th, td { padding: 10px; border: 1px solid #ddd; text-align: left; }
        th { background: #f8f9fa; }
        .btn { padding: 8px 16px; background: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer; }
        .delete-btn { background: #dc3545; }
        .action-link { margin-right: 10px; }
        .form-group { margin-bottom: 15px; }
        .error { color: red; font-weight: bold; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Овощи для салата ${message}</h1>

        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>

        <!-- Список овощей -->
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Calories</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="veg" items="${veggies}">
                <tr>
                    <td>${veg.id}</td>
                    <td>${veg.name}</td>
                    <td>${veg.calories}</td>
                    <td>
                        <a href="edit-vegetable?id=${veg.id}" class="action-link">Edit</a>
                        <form action="delete-vegetable" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="${veg.id}">
                            <button type="submit" class="delete-btn">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <!-- Подсчёт калорий -->
        <p>Общая калорийность салата: ${totalCalories}</p>

        <!-- Сортировка -->
        <a href="veggies?action=sort" class="action-link">Сортировать по калорийности</a>

        <!-- Сброс -->
        <a href="veggies" class="action-link">Сброс сортировки</a>

        <!-- Поиск по диапазону
        <form action="veggies?action=range" method="get">
            <div class="form-group">
                <label>Min calories:</label>
                <input type="number" name="min" value="15" min="0" required>
            </div>
            <div class="form-group">
                <label>Max calories:</label>
                <input type="number" name="max" value="25" min="0" required>
            </div>
            <button type="submit" class="btn">Поиск по диапазону</button>
        </form>-->

        <!-- Добавление -->
        <a href="add-vegetable" class="btn">Добавить овощ</a>
    </div>
</body>
</html>