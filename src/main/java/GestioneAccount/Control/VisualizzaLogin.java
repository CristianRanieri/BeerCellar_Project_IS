package GestioneAccount.Control;

import Utils.Other.Permesso;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Account;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/visualizzaLogin")
public class VisualizzaLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account1 = (Account)req.getSession().getAttribute("account");
        ArrayList<Permesso> permessi = (ArrayList<Permesso>) req.getServletContext().getAttribute("permessi");
        String attore;
        if(account1.getId() == -1)
            attore= "Ospite";
        else if(account1.isGestore())
            attore = "Gestore";
        else
            attore = "Utente";

        Permesso permesso = new Permesso(attore,"VisualizzaLogin","doGet");

        //controllo che non sia loggato
        if(permessi.contains(permesso)){
            RequestDispatcher dispatcher= req.getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(req,resp);
        }else {
            resp.sendRedirect("visualizzaAreaUtente");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
