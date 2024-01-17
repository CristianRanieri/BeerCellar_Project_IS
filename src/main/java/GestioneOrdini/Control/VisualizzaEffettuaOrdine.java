package GestioneOrdini.Control;

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

@WebServlet("/visualizzaEffettuaOrdine")
public class VisualizzaEffettuaOrdine extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Account account = (Account)req.getSession().getAttribute("account");

        ArrayList<Permesso> permessi = (ArrayList<Permesso>) req.getServletContext().getAttribute("permessi");
        String attore;
        if(account.getId() == -1)
            attore= "Ospite";
        else if(account.isGestore())
            attore = "Gestore";
        else
            attore = "Utente";

        Permesso permesso = new Permesso(attore,"VisualizzaEffettuaOrdine","doGet");

        //controllo che sia loggato e che è un gestore
        if(!permessi.contains(permesso)){
            //l'attore non ha i permessi per effettuare un ordine
            if(attore.equals("Gestore")) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
                dispatcher.forward(req, resp);
            }else
                resp.sendRedirect("visualizzaLogin");
        }else {
            if(account.getCarrello().isEmpty()){
                resp.sendRedirect("visualizzaCarrello");
            }else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/effettuaOrdine.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
