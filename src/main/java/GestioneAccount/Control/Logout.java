package GestioneAccount.Control;

import GestioneAccount.Service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //si controlla che l'utente è loggato nella sessione
        if(req.getSession().getAttribute("account")!=null) {
            //l'utente è loggato
            AccountService accountService = new AccountService();
            accountService.logout(req.getSession());
            resp.sendRedirect("index.jsp");
        }else {
            //l'utente non è loggato
            resp.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
