package org.example.servlets;

import org.example.Vegetable;
import org.example.VegetableDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddVegetableServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/add-vegetable.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            int calories = Integer.parseInt(request.getParameter("calories"));
            if (calories < 0) {
                request.setAttribute("error", "Калорийность не может быть отрицательной.");
                request.getRequestDispatcher("/add-vegetable.jsp").forward(request, response);
                return;
            }
            VegetableDAO dao = new VegetableDAO();
            dao.create(new Vegetable(0, name, calories));
            dao.close();
            response.sendRedirect("veggies");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Некорректное значение калорийности.");
            request.getRequestDispatcher("/add-vegetable.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding vegetable");
        }
    }
}