package GestioneAccount.Control;

import GestioneAccount.Service.AccountService;
import Utils.Other.Permesso;
import Utils.ValidazioneInput.PatternInput;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Account;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/modificaCredenziali")
public class ModificaCredenziali extends HttpServlet {
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

        Permesso permesso = new Permesso(attore,"ModificaCredenziali","doPost");

        //si controlla che l'utente è loggato nella sessione
        if(permessi.contains(permesso)) {
            //l'utente è loggato
            if(req.getParameter("nome")!=null && req.getParameter("pass")!=null && !req.getParameter("nome").equals("") && !req.getParameter("pass").equals("") &&
                    PatternInput.nome(req.getParameter("nome")) && PatternInput.password(req.getParameter("pass"))
            ){
                //credenziali valide, si procede con la modifica
                //si instanziano gli oggetti per eseguire il metodo modificaDatiAccount
                Account account= new Account();
                account.setNome(req.getParameter("nome"));
                account.setEmail(((Account) req.getSession().getAttribute("account")).getEmail());
                account.setPassword(req.getParameter("pass"));
                account.setId(((Account) req.getSession().getAttribute("account")).getId());

                AccountService accountService= new AccountService();
                boolean b = accountService.modificaDatiAccount(account);

                if(b){
                    //la modifica è stata effettuata nel modo corretto, si setta un attributo per notificare la corretta modfica
                    req.setAttribute("corretto",true);
                    account= (Account)req.getSession().getAttribute("account");
                    account.setNome(req.getParameter("nome"));
                    account.setPassword(req.getParameter("pass"));
                }else {
                    //la modifica non è andata a boun fine, la pasword inserita è uguale a quella registrata
                    req.setAttribute("error1",true);
                }
            }else{
                //credenziali non valide, si setta l'attributo di errore e si reindirizza alla pagina del'area utente
                req.setAttribute("error2",true);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/areaUtente.jsp");
            dispatcher.forward(req, resp);
        }else {
            //l'utente non è loggato si reindirizza verso la pagina di login
            resp.sendRedirect("visualizzaLogin");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
