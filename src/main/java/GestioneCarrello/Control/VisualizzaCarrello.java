package GestioneCarrello.Control;

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

@WebServlet("/visualizzaCarrello")
public class VisualizzaCarrello extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Account account = (Account)req.getSession().getAttribute("account");
        //controllo che sia loggato e che è un gestore
        if(account.getId() != -1 && account.isGestore()){
            //rimandato pagina di errore
            resp.sendRedirect("errorePermessi.jsp");
        }else {
            //controllare la validita del carrello
            if(!ValidaCarrello.validazioneCarrello(account.getCarrello()))
                //se non è valido viene modificato, setto l'ttributo per notificarlo all'utente
                req.setAttribute("cambiamenti",true);

            //calcolo il prezzo totale
            double prezzoTotale=0;
            for(ContenutoCarrello contenutoCarrello : account.getCarrello().getContenutoCarrello()){
                prezzoTotale+= contenutoCarrello.getProdotto().getPrezzo()*contenutoCarrello.getQuantita();
            }
            req.setAttribute("prezzoTotale", BigDecimal.valueOf(prezzoTotale).setScale(2, RoundingMode.HALF_UP).doubleValue());

            RequestDispatcher dispatcher= req.getRequestDispatcher("/WEB-INF/carrello.jsp");
            dispatcher.forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
