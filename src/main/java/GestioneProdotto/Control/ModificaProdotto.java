package GestioneProdotto.Control;

import GestioneProdotto.Service.GestioneProdottoService;
import GestioneProdotto.Service.ModificaException;
import GestioneProdotto.Service.ProdottoException;
import Utils.Other.Permesso;
import Utils.ValidazioneInput.PatternInput;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.entity.Account;
import model.entity.Prodotto;
import org.apache.taglibs.standard.lang.jstl.ELException;

import java.io.IOException;
import java.util.ArrayList;

@MultipartConfig
@WebServlet(name = "modificaProdotto", value = "/modificaProdotto")
public class ModificaProdotto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        ArrayList<String> formati = (ArrayList<String>) request.getServletContext().getAttribute("formati");
        ArrayList<String> fermentazioni = (ArrayList<String>) request.getServletContext().getAttribute("fermentazioni");
        ArrayList<String> stili = (ArrayList<String>) request.getServletContext().getAttribute("stili");
        ArrayList<String> colori = (ArrayList<String>) request.getServletContext().getAttribute("colori");
        ArrayList<Permesso> permessi = (ArrayList<Permesso>) request.getServletContext().getAttribute("permessi");

        if (Permesso.validazioneAccesso(permessi,account,"ModificaProdotto","doPost")){
            if(request.getParameter("id_prodotto") != null && PatternInput.numeri1_4Cifre(request.getParameter("id_prodotto"))){
                if (
                        request.getParameter("nomeBirra") != null &&
                                request.getParameter("formato") != null &&
                                request.getParameter("prezzo") != null &&
                                request.getParameter("fermentazione") != null &&
                                request.getParameter("stile") != null &&
                                request.getParameter("colore") != null &&
                                request.getParameter("tassoAlcolico") != null &&
                                request.getParameter("nomeBirrificio") != null &&
                                request.getParameter("descrizione") != null &&
                                PatternInput.descrizione(request.getParameter("descrizione")) &&
                                PatternInput.stringaConSpazzi(request.getParameter("nomeBirra")) &&
                                PatternInput.nome(request.getParameter("formato")) &&
                                PatternInput.prezzo(request.getParameter("prezzo")) &&
                                PatternInput.nome(request.getParameter("fermentazione")) &&
                                PatternInput.nome(request.getParameter("stile")) &&
                                PatternInput.nome(request.getParameter("colore")) &&
                                PatternInput.tassoAlcolico(request.getParameter("tassoAlcolico")) &&
                                (Double.parseDouble(request.getParameter("tassoAlcolico")) <= 70.0) &&
                                PatternInput.stringaConSpazzi(request.getParameter("nomeBirrificio")) &&
                                formati.contains(request.getParameter("formato")) &&
                                fermentazioni.contains(request.getParameter("fermentazione")) &&
                                stili.contains(request.getParameter("stile")) &&
                                colori.contains(request.getParameter("colore"))
                ) {
                    Prodotto prodottoModificato = new Prodotto();
                    prodottoModificato.setId(Integer.parseInt(request.getParameter("id_prodotto")));
                    prodottoModificato.setNome(request.getParameter("nomeBirra"));
                    prodottoModificato.setFormato(request.getParameter("formato"));
                    prodottoModificato.setPrezzo(Double.valueOf(request.getParameter("prezzo")));
                    prodottoModificato.setFermentazione(request.getParameter("fermentazione"));
                    prodottoModificato.setStile(request.getParameter("stile"));
                    prodottoModificato.setColore(request.getParameter("colore"));
                    prodottoModificato.setTassoAlcolico(Double.valueOf(request.getParameter("tassoAlcolico")));
                    prodottoModificato.setGlutine(request.getParameter("glutine") != null);
                    prodottoModificato.setInCatalogo(request.getParameter("inCatalogo") != null);
                    prodottoModificato.setBirrificio(request.getParameter("nomeBirrificio"));
                    prodottoModificato.setDescrizione(request.getParameter("descrizione"));

                    GestioneProdottoService gestioneProdottoService = new GestioneProdottoService();
                    try {
                        gestioneProdottoService.modificaProdotto(prodottoModificato, request.getPart("immagineBirra"), request.getServletContext());
                        response.sendRedirect("visualizzaProdotto?successo-modifica=true&id_prodotto=" + prodottoModificato.getId());
                    } catch (ProdottoException e) {
                        //il prodotto non esiste
                        request.setAttribute("errore-prodotto-null", true);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorePermessi.jsp");
                        dispatcher.forward(request, response);
                    } catch (ModificaException e2) {
                        //il prodotto non ha subito modifiche
                        response.sendRedirect("visualizzaProdotto?errore-nessuna-modifica=true&id_prodotto=" + prodottoModificato.getId());
                    }
                } else {
                    request.setAttribute("errore-parametri-invalidi", true);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("visualizzaModificaProdotto?id_prodotto"+request.getParameter("id_prodotto"));
                    dispatcher.forward(request, response);
                }
            }else {
                //il prodotto non esiste
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
