package RicercaProdotti.Control;

import GestioneOrdini.Service.GestioneOrdiniService;
import GestioneProdotto.Service.GestioneProdottoService;
import Utils.ValidazioneInput.PatternInput;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Account;
import model.entity.Ordine;
import model.entity.Prodotto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

@WebServlet(name = "ricercaProdottiFiltro", value= "/ricercaProdottiFiltro")
public class RicercaProdottiFiltro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");

        if(request.getParameter("formato") != null && PatternInput.nome(request.getParameter("formato")) && !request.getParameter("formato").equals("null")){
            ArrayList<String> filtro = new ArrayList<>();
            if(request.getParameter("stile") != null && PatternInput.nome(request.getParameter("stile")) && !request.getParameter("stile").equals("null")){
                filtro.add(request.getParameter("stile"));
                request.setAttribute("stile", request.getParameter("stile"));
            }else
                filtro.add("tutti");

            if(request.getParameter("colore") != null && PatternInput.nome(request.getParameter("colore")) && !request.getParameter("colore").equals("null")){
                filtro.add(request.getParameter("colore"));
                request.setAttribute("colore", request.getParameter("colore"));
            }else
                filtro.add("tutti");

            if(request.getParameter("tassoAlcolico") != null && PatternInput.tassoAlcolico(request.getParameter("tassoAlcolico"))&& !request.getParameter("tassoAlcolico").equals("null")){
                filtro.add(request.getParameter("tassoAlcolico"));
                request.setAttribute("tassoAlcolico", request.getParameter("tassoAlcolico"));
            }else
                filtro.add("tutti");

            boolean b=true;
            int offset=0;
            //controllo valore offset, se è diverso da null deve rispettare il formato
            if(request.getParameter("offset")!=null)
                if(!PatternInput.numeri1_4Cifre(request.getParameter("offset"))) {
                    b = false;
                }else {
                    offset= Integer.parseInt(request.getParameter("offset"));
                }

            if(b) {
                //gli input sono validi, eseguo il metodo di ricerca deglio ordini
                GestioneProdottoService prodottoService = new GestioneProdottoService();
                ArrayList<Prodotto> prodotti = prodottoService.ricercaProdottiFiltro(request.getParameter("formato"), account.isGestore(), filtro, offset);

                request.setAttribute("prodotti", prodotti);
                request.setAttribute("tipoRicerca","ricercaProdottiFiltro");
                request.setAttribute("nuoviProdotti",prodotti.size());
                request.setAttribute("numeroProdotti",prodotti.size()+offset);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ricercaProdotti.jsp");
                dispatcher.forward(request, resp);
            }else {
                //input non validi
                request.setAttribute("formato", request.getAttribute("formato"));
                RequestDispatcher dispatcher = request.getRequestDispatcher("ricercaProdottiFiltro");
                dispatcher.forward(request,resp);
            }

        }else{
            request.setAttribute("formato", "Bottiglia");
            RequestDispatcher dispatcher = request.getRequestDispatcher("ricercaProdottiFiltro");
            dispatcher.forward(request,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        doGet(request, resp);
    }
}
