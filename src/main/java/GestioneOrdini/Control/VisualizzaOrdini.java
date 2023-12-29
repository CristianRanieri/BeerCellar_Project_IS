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

@WebServlet("/visualizzaOrdini")
public class VisualizzaOrdini extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dove="/WEB-INF/ordini.jsp";

        //si controlla che l'utente sia loggato
        if(req.getSession().getAttribute("account")!=null){
            //l'utente è loggato, controllo degli input
            boolean b=true;
            int offset=0;
            //controllo valore offset, se è diverso da null deve rispettare il formato
            if(req.getParameter("offset")!=null)
                if(!PatternInput.numeri1_4Cifre(req.getParameter("offset"))) {
                    b = false;
                }else {
                    offset= Integer.parseInt(req.getParameter("offset"));
                }

            if(b) {
                //gli input sono validi
                Account account= (Account) req.getSession().getAttribute("account");

                //eseguo il service, prendo gli ordini
                OrdiniService ordiniService = new OrdiniService();
                ArrayList<Ordine> ordini = ordiniService.visualizzaOrdini(account, offset);

                //setto gli attributi utilizzati dalla jsp
                req.setAttribute("ordini", ordini);
                req.setAttribute("account", account);
                //servono per il calcolo del offset
                req.setAttribute("numeroOrdini",ordini.size()+offset);
                req.setAttribute("nuoviOrdini", ordini.size());

                //passo il controllo alla parte di visualizzazione(jsp)
                RequestDispatcher dispatcher = req.getRequestDispatcher(dove);
                dispatcher.forward(req,resp);
            }else {
                //parametro non valido, redirect pagina iniziale
                resp.sendRedirect("visualizzaOrdini");
            }

        }else {
            //l'utente non e loggato, ridirezione pagina di login
            resp.sendRedirect("visualizzaLogin");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
