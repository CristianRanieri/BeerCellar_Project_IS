package GestioneOrdini.Control;

import GestioneOrdini.Service.OrdiniService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Account;
import model.entity.AcquistoProdotto;
import model.entity.Ordine;
import model.entity.Prodotto;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet("/effettuaOrdine")
public class EffettuaOrdine extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Ordine ordine= new Ordine();
        Date date = new Date(System.currentTimeMillis());
        LocalDate localDate = date.toLocalDate();
        ordine.setData(Date.valueOf(localDate));
        ordine.setProvincia(req.getParameter("provincia"));
        ordine.setCAP(req.getParameter("cap"));
        ordine.setProdotti(new ArrayList<AcquistoProdotto>());
        ordine.setCitta(req.getParameter("citta"));
        ordine.setIndirizzo(req.getParameter("indirizzo"));

        double pt=0;
        for (AcquistoProdotto ap: ordine.getProdotti())
            pt+=ap.getPrezzoAcquisto();
        ordine.setPrezzoTotale(pt);

        OrdiniService ordiniService= new OrdiniService();
        Account account= new Account();
        account.setId(1);
        ordine.setAccount(account);
        ordiniService.effettuaOrdine(ordine);

        //passo il controllo alla parte di visualizzazione(jsp)
        RequestDispatcher dispatcher = req.getRequestDispatcher("visualizzaOrdini");
        dispatcher.forward(req,resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
