package GestioneCarrello.Control;

import GestioneProdotto.Service.GestioneProdottoService;
import Utils.Other.Permesso;
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
import java.util.ArrayList;

@WebServlet("/aggiungiProdotto")
public class AggiungiProdotto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account)req.getSession().getAttribute("account");
        ArrayList<Permesso> permessi = (ArrayList<Permesso>) req.getServletContext().getAttribute("permessi");

        if(!Permesso.validazioneAccesso(permessi,account,"AggiungiProdotto","doGet")) {
            //l'attore non ha i permessi per effettuare un ordine
            RequestDispatcher dispatcher= req.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
            dispatcher.forward(req,resp);
        }else {
            //account di tipo Utente loggato oppure non loggato
            //ho dei valori da validare?
            //controllo i valori
            if(req.getParameter("id") != null && PatternInput.numeri1_4Cifre(req.getParameter("id")) &&
                    req.getParameter("quantita") != null && PatternInput.numeri1_2Cifre(req.getParameter("quantita"))
            ){
                //giu input sono validi
                //controllare che il prodotto esite e che sia in catalogo
                GestioneProdottoService serviceProdotto = new GestioneProdottoService();
                Prodotto prodotto= serviceProdotto.getProdotto(Integer.parseInt(req.getParameter("id")));
                if(prodotto != null && prodotto.isInCatalogo()){
                    //il prodotto esiste ed è in catalogo
                    //controllo che il prodotto non sia nel carrello
                    boolean b= true;
                    for(ContenutoCarrello cc: account.getCarrello().getContenutoCarrello()){
                        if(cc.getProdotto().getId() == Integer.parseInt(req.getParameter("id"))) {
                            //il prodotto è gia presente, aumento solo la quantita
                            int nuvo = cc.getQuantita() + Integer.parseInt(req.getParameter("quantita"));
                            if(nuvo <= 99)
                                cc.setQuantita(nuvo);
                            b=false;
                        }
                    }

                    //il prodotto non è gia presente, lo inserisco
                    if(b) {
                        ContenutoCarrello c= new ContenutoCarrello();
                        c.setProdotto(prodotto);
                        c.setQuantita(Integer.parseInt(req.getParameter("quantita")));
                        account.getCarrello().getContenutoCarrello().add(c);
                    }

                    //calcolo il prezzo totale
                    double prezzoTotale=0;
                    for(ContenutoCarrello contenutoCarrello : account.getCarrello().getContenutoCarrello()){
                        prezzoTotale+= contenutoCarrello.getProdotto().getPrezzo()*contenutoCarrello.getQuantita();
                    }
                    req.setAttribute("prezzoTotale", BigDecimal.valueOf(prezzoTotale).setScale(2, RoundingMode.HALF_UP).doubleValue());

                    RequestDispatcher dispatcher= req.getRequestDispatcher("/WEB-INF/carrello.jsp");
                    dispatcher.forward(req,resp);
                }else {
                    //il prodotto non esiste o non in catalogo
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
