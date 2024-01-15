package GestioneProdotto.Control;

import GestioneProdotto.Service.GestioneProdottoService;
import Utils.ValidazioneInput.PatternInput;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.entity.Account;
import model.entity.Prodotto;
import java.io.IOException;

@WebServlet(name = "visualizzaModificaProdotto", value = "/visualizzaModificaProdotto")
public class VisualizzaModificaProdotto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account.getId() != -1){
            if (account.isGestore()){
                if (request.getParameter("id_prodotto") != null && PatternInput.numeri1_4Cifre(request.getParameter("id_prodotto"))){
                    GestioneProdottoService gestioneProdottoService = new GestioneProdottoService();
                    Prodotto prodotto = gestioneProdottoService.getProdotto(Integer.parseInt(request.getParameter("id_prodotto")));
                    if (prodotto != null){
                        request.setAttribute("prodotto", prodotto);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modificaProdotto.jsp");
                        dispatcher.forward(request, response);
                    }
                    else {
                        request.setAttribute("errore-prodotto-null", true);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
                        dispatcher.forward(request, response);
                    }
                }
                else {
                    request.setAttribute("errore-prodotto-null", true);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
                    dispatcher.forward(request, response);
                }
            }
            else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
                dispatcher.forward(request, response);
            }
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
