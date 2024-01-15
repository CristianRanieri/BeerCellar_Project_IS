package GestioneCarrello.Control;

import GestioneProdotto.Service.GestioneProdottoService;
import Utils.ValidazioneInput.PatternInput;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Account;
import model.entity.ContenutoCarrello;
import model.entity.Prodotto;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@WebServlet("/rimuoviProdotto")
public class RimuoviProdotto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account)req.getSession().getAttribute("account");
        //controllo che sia loggato e che è un gestore
        //chi puo accedere?
        if(account.getId() != -1 && account.isGestore()) {
            //gestore loggato, pagina errore peressi
            resp.sendRedirect("errorePermessi.jsp");
        }else {
            //account di tipo Utente loggato oppure non loggato
            //ho dei valori da validare?
            //controllo i valori
            if(req.getParameter("id") != null && PatternInput.numeri1_4Cifre(req.getParameter("id")))
            {
                //giu input sono validi
                //controllare che il prodotto esite e che sia in catalogo
                GestioneProdottoService serviceProdotto = new GestioneProdottoService();
                Prodotto prodotto= serviceProdotto.getProdotto(Integer.parseInt(req.getParameter("id")));
                if(prodotto != null && prodotto.isInCatalogo()){
                    //il prodotto esiste ed è in catalogo
                    //controllo che il prodotto non sia nel carrello
                    ContenutoCarrello cDaRimuovere= null;
                    for(ContenutoCarrello cc: account.getCarrello().getContenutoCarrello()){
                        if(cc.getProdotto().getId() == Integer.parseInt(req.getParameter("id"))) {
                            //il prodotto è gia presente, aumento solo la quantita
                            cDaRimuovere = cc;
                        }
                    }

                    //il prodotto non è presente, lo rimuovo
                    if(cDaRimuovere!=null)
                        account.getCarrello().getContenutoCarrello().remove(cDaRimuovere);

                    //calcolo il prezzo totale
                    double prezzoTotale=0;
                    for(ContenutoCarrello contenutoCarrello : account.getCarrello().getContenutoCarrello()){
                        prezzoTotale+= contenutoCarrello.getProdotto().getPrezzo()*contenutoCarrello.getQuantita();
                    }
                    req.setAttribute("prezzoTotale", BigDecimal.valueOf(prezzoTotale).setScale(2, RoundingMode.HALF_UP).doubleValue());

                    RequestDispatcher dispatcher= req.getRequestDispatcher("/WEB-INF/carrello.jsp");
                    dispatcher.forward(req,resp);
                }else {
                    //il prodotto non esiste o non in cataloglo
                    RequestDispatcher dispatcher= req.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
                    dispatcher.forward(req,resp);
                }
            }else {
                //gli input non sono validi
                //almeno un input non rispetta il formato, si setta l'attributo di errore e si restituisce la pagina di un errore
                req.setAttribute("error1", true);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/carrello.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
