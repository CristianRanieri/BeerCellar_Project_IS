package GestioneOrdini.Control;

import Utils.ValidazioneInput.ValidaCarrello;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Account;
import model.entity.ContenutoCarrello;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@WebServlet("/visualizzaEffettuaOrdine")
public class VisualizzaEffettuaOrdine extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Account account = (Account)req.getSession().getAttribute("account");
        //controllo che sia loggato e che è un gestore
        if(account.getId() != -1 && account.isGestore()){
            //rimandato pagina di errore
            resp.sendRedirect("errorePermessi.jsp");
        }else {
            if(account.getId() == -1){
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
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
