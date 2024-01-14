package GestioneProdotto.Control;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.entity.Account;

import java.io.IOException;

@WebServlet(name = "visualizzaCreaProdotto", value = "/visualizzaCreaProdotto")
public class VisualizzaCreaProdotto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account.getId() != -1){
            if (account.isGestore()){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/creaProdotto.jsp");
                dispatcher.forward(request, response);
            }
            else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
                dispatcher.forward(request, response);
            }
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}