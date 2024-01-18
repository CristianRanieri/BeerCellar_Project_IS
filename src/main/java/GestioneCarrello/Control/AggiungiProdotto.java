package GestioneCarrello.Control;

import GestioneCarrello.Service.CarrelloService;
import GestioneCarrello.Service.GestioneCarrelloService;
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
                GestioneCarrelloService carrelloService= new GestioneCarrelloService();
                try {
                    carrelloService.aggiungiProdotto(Integer.parseInt(req.getParameter("id")),Integer.parseInt(req.getParameter("quantita")),req.getSession());
                    resp.sendRedirect("visualizzaCarrello");
                } catch (Exception e) {
                    //il prodotto non esiste o non in catalogo
                    req.setAttribute("errore-prodotto-null",true);
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
