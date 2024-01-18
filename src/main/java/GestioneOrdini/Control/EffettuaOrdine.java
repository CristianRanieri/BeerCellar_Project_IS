package GestioneOrdini.Control;

import GestioneOrdini.Service.GestioneOrdiniService;
import GestioneOrdini.Service.PagamentoAdapter;
import GestioneOrdini.Service.PagamentoService;
import Utils.Other.Pagamento;
import Utils.Other.Permesso;
import Utils.ValidazioneInput.PatternInput;
import Utils.ValidazioneInput.ValidaCarrello;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;

@WebServlet("/effettuaOrdine")
public class EffettuaOrdine extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account)req.getSession().getAttribute("account");
        ArrayList<Permesso> permessi = (ArrayList<Permesso>) req.getServletContext().getAttribute("permessi");

        //controllo che sia loggato e che è un gestore
        if (!Permesso.validazioneAccesso(permessi,account,"EffettuaOrdine","doPost")) {
            //l'attore non ha i permessi per effettuare un ordine
            RequestDispatcher dispatcher= req.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
            dispatcher.forward(req,resp);
        } else {

            if(account.getCarrello().isEmpty()) {
                resp.sendRedirect("visualizzaCarrello");
            } else {
                //Controllo gli input
                if (req.getParameter("nome") != null && req.getParameter("cognome") != null && req.getParameter("carta") != null && !req.getParameter("dataScadenza").isEmpty()
                        && req.getParameter("cvv") != null && req.getParameter("nomeCarta") != null && req.getParameter("indirizzo") != null
                        && req.getParameter("citta") != null && req.getParameter("cap") != null && req.getParameter("provincia") != null
                        && PatternInput.nomeCognome(req.getParameter("nome")) && PatternInput.nomeCognome(req.getParameter("cognome"))
                        && PatternInput.numeroCarta(req.getParameter("carta")) && PatternInput.data(new Date(Integer.parseInt(req.getParameter("dataScadenza").substring(0, 4)) - 1900, Integer.parseInt(req.getParameter("dataScadenza").substring(5, 7)) - 1, 1))
                        && PatternInput.numeroCCV(req.getParameter("cvv")) && PatternInput.nomeCognome(req.getParameter("nomeCarta"))
                        && PatternInput.stringaConSpazzi(req.getParameter("indirizzo")) && PatternInput.stringCaratteriSpeciali(req.getParameter("citta"))
                        && PatternInput.numeroCAP(req.getParameter("cap")) && PatternInput.stringCaratteriSpeciali(req.getParameter("provincia"))
                ) {
                    //controllo validita carrello
                    if (ValidaCarrello.validazioneCarrello(account.getCarrello())) {

                        //Gli input sono validi
                        Ordine ordine = new Ordine();
                        ordine.setAccount(account);
                        ordine.setCAP(req.getParameter("cap"));
                        Date date = new Date(System.currentTimeMillis());
                        LocalDate localDate = date.toLocalDate();
                        ordine.setData(Date.valueOf(localDate));
                        ordine.setCitta(req.getParameter("citta"));
                        ordine.setIndirizzo(req.getParameter("indirizzo"));
                        ordine.setProvincia(req.getParameter("provincia"));
/*
                        double prezzoTotale = 0;
                        ArrayList<AcquistoProdotto> prodotti = new ArrayList<>();

                        for (ContenutoCarrello cc : account.getCarrello().getContenutoCarrello()) {
                            AcquistoProdotto acquistoProdotto = new AcquistoProdotto();
                            acquistoProdotto.setProdotto(cc.getProdotto());
                            acquistoProdotto.setQuantita(cc.getQuantita());
                            acquistoProdotto.setPrezzoAcquisto(cc.getProdotto().getPrezzo());
                            prodotti.add(acquistoProdotto);
                            prezzoTotale += cc.getProdotto().getPrezzo() * cc.getQuantita();
                        }
                        ordine.setPrezzoTotale(BigDecimal.valueOf(prezzoTotale).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        ordine.setProdotti(prodotti);

                        GestioneOrdiniService gestioneOrdiniService = new GestioneOrdiniService();
                        gestioneOrdiniService.effettuaOrdine(ordine);
*/
                        Pagamento pagamento = new Pagamento();
                        pagamento.setData(String.valueOf(new Date(Integer.parseInt(req.getParameter("dataScadenza").substring(0, 4)) - 1900, Integer.parseInt(req.getParameter("dataScadenza").substring(5, 7)) - 1, 1)));
                        pagamento.setCvv(req.getParameter("cvv"));
                        pagamento.setNumeroCarta(req.getParameter("carta"));
/*
                        PagamentoService pagamentoService = new PagamentoAdapter();
                        boolean b = pagamentoService.pagamento(pagamento);
*/
                        GestioneOrdiniService ordiniService = new GestioneOrdiniService();
                        try {
                            //pagamento va a buon fine
                            ordiniService.effettuaOrdine(ordine,pagamento);
                            Carrello carrello = new Carrello();
                            carrello.setContenutoCarrello(new ArrayList<ContenutoCarrello>());

                            account.setCarrello(carrello);

                            req.setAttribute("Successo", true);
                            RequestDispatcher dispatcher = req.getRequestDispatcher("visualizzaOrdini");
                            dispatcher.forward(req, resp);
                        } catch (Exception e) {
                            //pagamento fallito
                            req.setAttribute("Pagamento-Fallito", true);
                            RequestDispatcher dispatcher = req.getRequestDispatcher("visualizzaCarrello");
                            dispatcher.forward(req, resp);
                        }
                    } else {
                        //carrello non valido, setto l'ttributo per notificarlo all'utente
                        req.setAttribute("cambiamenti", true);
                        RequestDispatcher dispatcher = req.getRequestDispatcher("visualizzaCarrello");
                        dispatcher.forward(req, resp);
                    }
                } else {
                    //Gli input non sono validi
                    req.setAttribute("errore", true);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/effettuaOrdine.jsp");
                    dispatcher.forward(req, resp);
                }
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
