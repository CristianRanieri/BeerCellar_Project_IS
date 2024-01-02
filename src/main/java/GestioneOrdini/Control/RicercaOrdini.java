package GestioneOrdini.Control;

import GestioneOrdini.Service.OrdiniService;
import Utils.ValidazioneInput.PatternInput;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Account;
import model.entity.Ordine;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ricercaOrdini")
public class RicercaOrdini extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dove="/WEB-INF/ordini.jsp";
        Account account = (Account) req.getSession().getAttribute("account");

        //controllo che ci sia un utente loggato in sessione
        if(account.getId() != -1){
            //l'utente è loggato, controllo che sia un gestore
            if(account.isGestore()){
                //è un gestore, quindi si effettua il controllo della validita degli input
                if(req.getParameter("tipoID")!=null && req.getParameter("numero")!=null && (req.getParameter("tipoID").equals("Utente") ||
                        req.getParameter("tipoID").equals("Ordine")) && PatternInput.numeri1_4Cifre(req.getParameter("numero"))
                ){
                    boolean b=true;
                    int offset=0;
                    //controllo valore offset, se è diverso da null deve rispettare il formato
                    if(req.getParameter("offset")!=null)
                        if(!PatternInput.numeri1_4Cifre(req.getParameter("offset"))) {
                            b = false;
                        }else {
                            offset= Integer.parseInt(req.getParameter("offset"));
                        }

                    if(b){
                        //gli input sono validi, eseguo il metodo di ricerca deglio ordini
                        OrdiniService ordiniService = new OrdiniService();
                        ArrayList<Ordine> ordini = ordiniService.ricercaOrdini(req.getParameter("tipoID"), Integer.parseInt(req.getParameter("numero")),offset);

                        //setto gli attributi utilizzati dalla jsp
                        req.setAttribute("ordini", ordini);
                        //servono per il calcolo del offset
                        req.setAttribute("numeroOrdini",ordini.size()+offset);
                        req.setAttribute("nuoviOrdini", ordini.size());
                        req.setAttribute("ricerca", true);

                        //passo il controllo alla parte di visualizzazione(jsp)
                        RequestDispatcher dispatcher = req.getRequestDispatcher(dove);
                        dispatcher.forward(req,resp);
                    }else {
                        //input non validi
                        resp.sendRedirect("visualizzaOrdini");
                    }
                }else {
                    //input non validi
                    resp.sendRedirect("visualizzaOrdini");
                }
            }else {
                //non è un gestore, ridirezione pagina di errore, mancanza dei permessi
                //da aggiungere
            }
        }else {
            //l'utente non è loggato, ridirezione login
            resp.sendRedirect("visualizzaLogin");
        }
    }
}
