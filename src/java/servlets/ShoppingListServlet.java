package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {

    private static ArrayList<String> itemlist = new ArrayList<String>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        itemlist.clear();
        HttpSession session = request.getSession();

        if (request.getParameter("action") != null) {
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;

        }

        if (session.getAttribute("username") != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
            return;
        }

        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if (action.equals("register")) {
            String username = request.getParameter("username");
            session.setAttribute("username", username);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
            return;

        }

        if (action.equals("logout")) {

            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }

        if (action.equals("add")) {

            String items = "";
            String item = request.getParameter("item");
            itemlist.add(item);

            for (int i = 0; i < itemlist.size(); i++) {
                items += "<li><input type='radio' name='item' value='" + itemlist.get(i) + "'>" + itemlist.get(i) + "</li>";
            }
            session.setAttribute("items", items);

            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
            return;
        }

        if (action.equals("delete")) {

            if (request.getParameter("item") != null) {

                String items = "";
                String selectedItem = request.getParameter("item");

                for (int i = 0; i < itemlist.size(); i++) {
                    if (selectedItem.equals(itemlist.get(i))) {
                        itemlist.remove(i);
                        break;
                    }
                }

                for (int i = 0; i < itemlist.size(); i++) {
                    items += "<li><input type='radio' name='item' value='" + itemlist.get(i) + "'>" + itemlist.get(i) + "</li>";
                }
                session.setAttribute("items", items);

            }

            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
            return;

        }
    }
}
