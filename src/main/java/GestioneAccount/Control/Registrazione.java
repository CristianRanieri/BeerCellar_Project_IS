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

@WebServlet("/registrazione")
public class Registrazione extends HttpServlet {
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

        Permesso permesso = new Permesso(attore,"Registrazione","doPost");

        //si controlla se l'utente è gia in sessione, se non è in sessione si procede con la registrazione altrimenti viene indirizzato verso la sua area utente
        if(permessi.contains(permesso)){
            //validazione dell'input
            if (req.getParameter("nome") != null && PatternInput.nome(req.getParameter("nome")) &&
                    req.getParameter("email") != null && PatternInput.email(req.getParameter("email")) &&
                    req.getParameter("pass") != null && PatternInput.password(req.getParameter("pass")) &&
                    req.getParameter("confermaPass") != null && PatternInput.password(req.getParameter("confermaPass")) && req.getParameter("confermaPass").equals(req.getParameter("pass"))
            ){
                //tutti gli input rispettano il formato, qiundi si continua con la registrazione
                //si instanziano gli oggetti per eseguire il metodo registraUtente
                Account account= new Account();
                account.setEmail(req.getParameter("email"));
                account.setNome(req.getParameter("nome"));
                account.setPassword(req.getParameter("pass"));

                AccountService accountService= new AccountService();
                account = accountService.registraUtente(account);
                if (account!=null){
                    //la registrazione ha avuto successo

                    //si setta il carrello carrello
                    account.setCarrello(((Account)req.getSession().getAttribute("account")).getCarrello());

                    //si aggiorna l'oggetto account nella sessione
                    req.getSession().setAttribute("account", account);

                    //si effettua il redirect verso la pagina di home
                    resp.sendRedirect("index.jsp");

                }else {
                    //la registrazione non ha avuto successo, l'email indicata è gia registrata, quindi si setta l'attributo di errore e si restituisce nuovalemnte la pagina di registrazione
                    req.setAttribute("error1", true);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/registrazione.jsp");
                    dispatcher.forward(req, resp);
                }

            }else {
                //almeno un input non rispetta il formato, si setta l'attributo di errore e si restituisce nuovalemnte la pagina di registrazione
                req.setAttribute("error2", true);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/registrazione.jsp");
                dispatcher.forward(req, resp);
            }
        }else{
            resp.sendRedirect("visualizzaAreaUtente");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
