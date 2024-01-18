package GestioneProdotto.Control;

import GestioneProdotto.Service.GestioneProdottoService;
import Utils.Other.Permesso;
import Utils.ValidazioneInput.PatternInput;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.entity.Account;
import model.entity.Prodotto;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "visualizzaProdotto", value = "/visualizzaProdotto")
public class VisualizzaProdotto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        ArrayList<Permesso> permessi = (ArrayList<Permesso>) request.getServletContext().getAttribute("permessi");

        if(Permesso.validazioneAccesso(permessi,account,"VisualizzaProdotto","doGet")) {

            if (request.getParameter("id_prodotto") != null && PatternInput.numeri1_4Cifre(request.getParameter("id_prodotto"))) {
                GestioneProdottoService gestioneProdottoService = new GestioneProdottoService();
                Prodotto prodotto = gestioneProdottoService.getProdotto(Integer.parseInt(request.getParameter("id_prodotto")));
                if (prodotto != null) {
                    if (prodotto.isInCatalogo()) {
                        request.setAttribute("prodotto", prodotto);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/prodotto.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        if (account.isGestore()) {
                            request.setAttribute("prodotto", prodotto);
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/prodotto.jsp");
                            dispatcher.forward(request, response);
                        } else {
                            request.setAttribute("errore-prodotto-null",true);
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
                            dispatcher.forward(request, response);
                        }
                    }
                } else {
                    request.setAttribute("errore-prodotto-null",true);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                request.setAttribute("errore-prodotto-null",true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
                dispatcher.forward(request, response);
            }
        }else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
