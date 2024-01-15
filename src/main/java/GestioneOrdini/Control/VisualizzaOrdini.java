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

@WebServlet("/visualizzaOrdini")
public class VisualizzaOrdini extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dove="/WEB-INF/ordini.jsp";
        Account account1 = (Account)req.getSession().getAttribute("account");
        ArrayList<Permesso> permessi = (ArrayList<Permesso>) req.getServletContext().getAttribute("permessi");
        String attore;
        if(account1.getId() == -1)
            attore= "Ospite";
        else if(account1.isGestore())
            attore = "Gestore";
        else
            attore = "Utente";

        Permesso permesso = new Permesso(attore,"VisualizzaOrdini","doGet");

        //si controlla che l'utente sia loggato
        if(((Account)req.getSession().getAttribute("account")).getId() != -1){
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
                GestioneOrdiniService ordiniService = new GestioneOrdiniService();
                ArrayList<Ordine> ordini = ordiniService.visualizzaOrdini(account, offset);
                ordini.sort(new Comparator<Ordine>() {
                    @Override
                    public int compare(Ordine o1, Ordine o2) {
                        if(o1.getId() < o2.getId())
                            return 1;
                        else
                            return -1;
                    }
                });

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
