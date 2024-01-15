package RicercaProdotti.Control;

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
import model.entity.Prodotto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name="ricercaProdottiNome", value="/ricercaProdottiNome")
public class RicercaProdottiNome extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        Account account = (Account) request.getSession().getAttribute("account");

        ArrayList<Permesso> permessi = (ArrayList<Permesso>) request.getServletContext().getAttribute("permessi");
        String attore;
        if(account.getId() == -1)
            attore= "Ospite";
        else if(account.isGestore())
            attore = "Gestore";
        else
            attore = "Utente";

        Permesso permesso = new Permesso(attore,"RicercaProdottiNome","doGet");

        String nomeRicerca = request.getParameter("ricerca");

        if(permessi.contains(permesso)) {

            if (nomeRicerca != null) {
                List<String> nomiProdotto = Arrays.stream(nomeRicerca.split(" ")).toList();

                boolean b = true;
                int offset = 0;
                //controllo valore offset, se è diverso da null deve rispettare il formato
                if (request.getParameter("offset") != null)
                    if (!PatternInput.numeri1_4Cifre(request.getParameter("offset"))) {
                        b = false;
                    } else {
                        offset = Integer.parseInt(request.getParameter("offset"));
                    }

                if (b) {
                    //gli input sono validi, eseguo il metodo di ricerca deglio ordini
                    GestioneProdottoService prodottoService = new GestioneProdottoService();
                    ArrayList<Prodotto> prodotti = prodottoService.ricercaProdottiNome(nomiProdotto, account.isGestore(), offset);

                    request.setAttribute("prodotti", prodotti);
                    request.setAttribute("tipoRicerca", "ricercaProdottiNome");
                    request.setAttribute("nuoviProdotti", prodotti.size());
                    request.setAttribute("numeroProdotti", prodotti.size() + offset);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ricercaProdotti.jsp");
                    dispatcher.forward(request, resp);
                } else {
                    //input non validi
                    RequestDispatcher dispatcher = request.getRequestDispatcher("ricercaProdottiFiltro");
                    dispatcher.forward(request, resp);
                }
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("ricercaProdottiFiltro");
                dispatcher.forward(request, resp);
            }
        } else {
            RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
            dispatcher.forward(request,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
