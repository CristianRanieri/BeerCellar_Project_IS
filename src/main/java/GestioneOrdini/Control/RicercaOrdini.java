package GestioneOrdini.Control;

import GestioneOrdini.Service.GestioneOrdiniService;
import Utils.Other.Permesso;
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
import java.util.Comparator;

@WebServlet("/ricercaOrdini")
public class RicercaOrdini extends HttpServlet {
    private GestioneOrdiniService ordiniService= new GestioneOrdiniService();
    public void setOrdiniService(GestioneOrdiniService ordiniService){
        this.ordiniService= ordiniService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dove="/WEB-INF/ordini.jsp";
        Account account = (Account) req.getSession().getAttribute("account");
        ArrayList<Permesso> permessi = (ArrayList<Permesso>) req.getServletContext().getAttribute("permessi");

        if(Permesso.validazioneAccesso(permessi,account,"RicercaOrdini","doGet")){
            //è un gestore, quindi si effettua il controllo della validita degli input
            if(req.getParameter("tipoID")!=null && req.getParameter("numero")!=null && (req.getParameter("tipoID").equals("Utente") ||
                    req.getParameter("tipoID").equals("Ordine")) && PatternInput.numeri1_4Cifre(req.getParameter("numero"))
            ){
                boolean b = true;
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
                    req.setAttribute("error1",true);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("visualizzaOrdini");
                    dispatcher.forward(req,resp);
                }
            }else {
                //input non validi
                req.setAttribute("error2",true);
                RequestDispatcher dispatcher = req.getRequestDispatcher("visualizzaOrdini");
                dispatcher.forward(req,resp);
            }
        }else {
            //non è un gestore, ridirezione pagina di errore, mancanza dei permessi
            RequestDispatcher dispatcher= req.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
            dispatcher.forward(req,resp);
        }

    }
}
