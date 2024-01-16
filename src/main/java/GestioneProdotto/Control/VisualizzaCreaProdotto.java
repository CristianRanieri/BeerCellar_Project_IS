package GestioneProdotto.Control;

import Utils.Other.Permesso;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.entity.Account;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "visualizzaCreaProdotto", value = "/visualizzaCreaProdotto")
public class VisualizzaCreaProdotto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        ArrayList<Permesso> permessi = (ArrayList<Permesso>) request.getServletContext().getAttribute("permessi");

        if(Permesso.validazioneAccesso(permessi,account,"VisualizzaCreaProdotto","doGet")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/creaProdotto.jsp");
            dispatcher.forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
