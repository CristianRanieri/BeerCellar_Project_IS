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

@MultipartConfig
@WebServlet(name = "creaProdotto", value = "/creaProdotto")
public class CreaProdotto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        ArrayList<String> formati = (ArrayList<String>) request.getServletContext().getAttribute("formati");
        ArrayList<String> fermentazioni = (ArrayList<String>) request.getServletContext().getAttribute("fermentazioni");
        ArrayList<String> stili = (ArrayList<String>) request.getServletContext().getAttribute("stili");
        ArrayList<String> colori = (ArrayList<String>) request.getServletContext().getAttribute("colori");
        ArrayList<Permesso> permessi = (ArrayList<Permesso>) request.getServletContext().getAttribute("permessi");

        if (Permesso.validazioneAccesso(permessi,account,"CreaProdotto","doPost")){
            if (
                    request.getParameter("nomeBirra") != null &&
                    request.getParameter("formato") != null &&
                    request.getParameter("prezzo") != null &&
                    request.getParameter("fermentazione") != null &&
                    request.getParameter("stile") != null &&
                    request.getParameter("colore") != null &&
                    request.getParameter("tassoAlcolico") != null &&
                    request.getPart("immagineBirra").getSize() != 0 &&
                    request.getParameter("nomeBirrificio") != null &&
                    request.getParameter("descrizione") != null &&
                    PatternInput.descrizione(request.getParameter("descrizione")) &&
                    PatternInput.stringaConSpazzi(request.getParameter("nomeBirra"))  &&
                    PatternInput.nome(request.getParameter("formato"))  &&
                    PatternInput.prezzo(request.getParameter("prezzo"))  &&
                    Double.parseDouble(request.getParameter("prezzo"))>0.00 &&
                    PatternInput.nome(request.getParameter("fermentazione"))  &&
                    PatternInput.nome(request.getParameter("stile"))  &&
                    PatternInput.nome(request.getParameter("colore"))  &&
                    PatternInput.tassoAlcolico(request.getParameter("tassoAlcolico"))  &&
                    (Double.parseDouble(request.getParameter("tassoAlcolico")) <= 70.0) &&
                    request.getPart("immagineBirra").getContentType().contains("image")  &&
                    PatternInput.stringaConSpazzi(request.getParameter("nomeBirrificio")) &&
                    formati.contains(request.getParameter("formato")) &&
                    fermentazioni.contains(request.getParameter("fermentazione")) &&
                    stili.contains(request.getParameter("stile")) &&
                    colori.contains(request.getParameter("colore"))
            ){
                Prodotto prodotto = new Prodotto();
                prodotto.setNome(request.getParameter("nomeBirra"));
                prodotto.setFormato(request.getParameter("formato"));
                prodotto.setPrezzo(Double.valueOf(request.getParameter("prezzo")));
                prodotto.setFermentazione(request.getParameter("fermentazione"));
                prodotto.setStile(request.getParameter("stile"));
                prodotto.setColore(request.getParameter("colore"));
                prodotto.setTassoAlcolico(Double.valueOf(request.getParameter("tassoAlcolico")));
                prodotto.setGlutine(request.getParameter("glutine") != null);
                prodotto.setBirrificio(request.getParameter("nomeBirrificio"));
                prodotto.setInCatalogo(true);
                prodotto.setDescrizione(request.getParameter("descrizione"));

                GestioneProdottoService gestioneProdottoService = new GestioneProdottoService();
                gestioneProdottoService.creaProdotto(prodotto,request.getPart("immagineBirra"),request.getServletContext());

                response.sendRedirect("visualizzaProdotto?Successo=true&id_prodotto=" + prodotto.getId());
            }
            else {
                request.setAttribute("errore-parametri-invalidi", true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/creaProdotto.jsp");
                dispatcher.forward(request, response);
            }
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
