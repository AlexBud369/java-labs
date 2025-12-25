package org.example.servlets;

import org.example.Vegetable;
import org.example.VegetableDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListVegetableServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            VegetableDAO dao = new VegetableDAO();
            String action = request.getParameter("action");
            List<Vegetable> veggies;
            String message = "";

            if ("sort".equals(action)) {
                veggies = dao.sortByCalories();
                message = "Сортировка по калорийности";
            } else if ("range".equals(action)) {
                int min;
                int max;
                try {
                    min = Integer.parseInt(request.getParameter("min"));
                    max = Integer.parseInt(request.getParameter("max"));
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Некорректные значения для диапазона.");
                    veggies = dao.readAll();
                    request.setAttribute("veggies", veggies);
                    request.setAttribute("totalCalories", dao.calculateSaladCalories());
                    request.getRequestDispatcher("/list-vegetables.jsp").forward(request, response);
                    return;
                }
                if (min < 0 || max < 0 || min > max) {
                    request.setAttribute("error", "Диапазон калорийности должен быть положительным и min <= max.");
                    veggies = dao.readAll();  // Показываем полный список при ошибке
                } else {
                    veggies = dao.findByCaloriesRange(min, max);
                    message = "Овощи в диапазоне " + min + "-" + max + " калорий";
                }
            } else {
                veggies = dao.readAll();
            }

            request.setAttribute("veggies", veggies);
            request.setAttribute("totalCalories", dao.calculateSaladCalories());
            request.setAttribute("message", message);
            dao.close();

            request.getRequestDispatcher("/list-vegetables.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error listing vegetables");
        }
    }
}