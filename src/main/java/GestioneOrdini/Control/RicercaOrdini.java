package GestioneOrdini.Control;

import GestioneOrdini.Service.OrdiniService;
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

        //prendo e inizializzo i parametri da passare al service
        int offset=0;
        if (req.getParameter("offset")!=null)
            offset= Integer.parseInt(req.getParameter("offset"));
        Account account= new Account();
        account.setGestore(true);
        String tipoID= req.getParameter("tipoID");
        int numero= Integer.parseInt(req.getParameter("numero"));

        //eseguo il service, prendo gli ordini
        OrdiniService ordiniService = new OrdiniService();
        ArrayList<Ordine> ordini = ordiniService.ricercaOrdini(tipoID, numero,offset);

        //setto gli attributi utilizzati dalla jsp
        req.setAttribute("ordini", ordini);
        req.setAttribute("account", account);
        //servono per il calcolo del offset
        req.setAttribute("numeroOrdini",ordini.size()+offset);
        req.setAttribute("nuoviOrdini", ordini.size());
        req.setAttribute("ricerca", true);

        //passo il controllo alla parte di visualizzazione(jsp)
        RequestDispatcher dispatcher = req.getRequestDispatcher(dove);
        dispatcher.forward(req,resp);
    }
}
