package GestioneAccount.Control;

import GestioneAccount.Service.AccountService;
import Utils.ValidazioneInput.PatternInput;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Account;

import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //si controlla se l'utente è gia in sessione, se non è in sessione si procede con il login altrimenti viene indirizzato verso la sua area utente
        if(req.getSession().getAttribute("account")==null){
            //validazione dell'input
            if (req.getParameter("email") != null && PatternInput.email(req.getParameter("email")) &&
                    req.getParameter("pass") != null && PatternInput.password(req.getParameter("pass"))
            ){
                //tutti gli input rispettano il formato, qiundi si continua con il login
                //si instanziano gli oggetti per eseguire il metodo registraUtente
                Account account= new Account();
                account.setEmail(req.getParameter("email"));
                account.setPassword(req.getParameter("pass"));

                AccountService accountService= new AccountService();
                boolean b = accountService.login(account,req.getSession());
                if(b){
                    //il login è stato effettuato con successo
                    //si effettua il redirect verso la pagina di home
                    resp.sendRedirect("index.jsp");

                }else{
                    //non esiste un account nel database con quelle credenziali
                    req.setAttribute("error2", true);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
                    dispatcher.forward(req, resp);
                }
            }else {
                //uno degli input non riseptta il formato
                req.setAttribute("error1", true);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
