package GestioneAccount.Control;

import GestioneAccount.Service.AccountService;
import Utils.Other.Permesso;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Account;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/logout")
public class Logout extends HttpServlet {
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

        Permesso permesso = new Permesso(attore,"Logout","doGet");

        //si controlla che l'utente è loggato nella sessione
        if(permessi.contains(permesso)) {
            //l'utente è loggato
            AccountService accountService = new AccountService();
            accountService.logout(req.getSession());
            resp.sendRedirect("index.jsp");
        }else {
            //l'utente non è loggato
            resp.sendRedirect("visualizzaLogin");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
