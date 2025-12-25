package org.example.servlets;

import org.example.Vegetable;
import org.example.VegetableDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditVegetableServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            VegetableDAO dao = new VegetableDAO();
            Vegetable veg = dao.readById(id);
            dao.close();
            if (veg != null) {
                request.setAttribute("vegetable", veg);
                request.getRequestDispatcher("/edit-vegetable.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Vegetable not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading edit form");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int calories = Integer.parseInt(request.getParameter("calories"));
            if (calories < 0) {
                request.setAttribute("error", "Калорийность не может быть отрицательной.");
                Vegetable veg = new Vegetable(id, name, calories);  // Временно, чтобы передать обратно
                request.setAttribute("vegetable", veg);
                request.getRequestDispatcher("/edit-vegetable.jsp").forward(request, response);
                return;
            }
            VegetableDAO dao = new VegetableDAO();
            dao.update(new Vegetable(id, name, calories));
            dao.close();
            response.sendRedirect("veggies");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Некорректное значение калорийности.");
            // Можно добавить логику для восстановления данных, но для простоты форвард
            request.getRequestDispatcher("/edit-vegetable.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating vegetable");
        }
    }
}