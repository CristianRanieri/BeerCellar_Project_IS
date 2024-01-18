package RicercaProdotti.Control;

import Utils.Other.Permesso;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import model.entity.Account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RicercaProdottoFiltroTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private ServletContext servletContext;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private Account account;
    @InjectMocks
    private RicercaProdottiFiltro ricercaProdottiFiltroServlet;

    @BeforeEach
    public void setUp(){
        ArrayList<Permesso> permessi = new ArrayList<>();
        //Gestione Account
        permessi.add(new Permesso("Ospite","Login","doPost"));
        permessi.add(new Permesso("Ospite","Registrazione","doPost"));
        permessi.add(new Permesso("Ospite","VisualizzaLogin","doGet"));
        permessi.add(new Permesso("Ospite","VisualizzaRegistrazione","doGet"));
        permessi.add(new Permesso("Utente","Logout","doGet"));
        permessi.add(new Permesso("Utente","ModificaCredenziali","doPost"));
        permessi.add(new Permesso("Utente","VisualizzaAreaUtente","doGet"));
        permessi.add(new Permesso("Gestore","Logout","doGet"));
        permessi.add(new Permesso("Gestore","ModificaCredenziali","doPost"));
        permessi.add(new Permesso("Gestore","VisualizzaAreaUtente","doGet"));
        //Gestione Carrello
        permessi.add(new Permesso("Utente", "AggiungiProdotto", "doGet"));
        permessi.add(new Permesso("Utente", "RimuoviProdotto", "doGet"));
        permessi.add(new Permesso("Utente", "VisualizzaCarrello", "doGet"));
        permessi.add(new Permesso("Ospite", "AggiungiProdotto", "doGet"));
        permessi.add(new Permesso("Ospite", "RimuoviProdotto", "doGet"));
        permessi.add(new Permesso("Ospite", "VisualizzaCarrello", "doGet"));
        //Gestione Ordini
        permessi.add(new Permesso("Utente", "EffettuaOrdine","doPost"));
        permessi.add(new Permesso("Gestore", "RicercaOrdini","doGet"));
        permessi.add(new Permesso("Utente", "VisualizzaEffettuaOrdine","doGet"));
        permessi.add(new Permesso("Utente", "VisualizzaOrdini","doGet"));
        permessi.add(new Permesso("Gestore", "VisualizzaOrdini","doGet"));
        //Gestione Prodotto
        permessi.add(new Permesso("Gestore", "CreaProdotto", "doGet"));
        permessi.add(new Permesso("Gestore", "CreaProdotto", "doPost"));
        permessi.add(new Permesso("Gestore", "ModificaProdotto", "doGet"));
        permessi.add(new Permesso("Gestore", "ModificaProdotto", "doPost"));
        permessi.add(new Permesso("Gestore", "VisualizzaCreaProdotto", "doGet"));
        permessi.add(new Permesso("Gestore", "VisualizzaCreaProdotto", "doPost"));
        permessi.add(new Permesso("Gestore", "VisualizzaModificaProdotto", "doGet"));
        permessi.add(new Permesso("Gestore", "VisualizzaModificaProdotto", "doPost"));
        permessi.add(new Permesso("Ospite", "VisualizzaProdotto", "doGet"));
        permessi.add(new Permesso("Ospite", "VisualizzaProdotto", "doPost"));
        permessi.add(new Permesso("Utente", "VisualizzaProdotto", "doGet"));
        permessi.add(new Permesso("Utente", "VisualizzaProdotto", "doPost"));
        permessi.add(new Permesso("Gestore", "VisualizzaProdotto", "doGet"));
        permessi.add(new Permesso("Gestore", "VisualizzaProdotto", "doPost"));
        //Ricerca Prodotti
        permessi.add(new Permesso("Utente", "RicercaProdottiFiltro", "doGet"));
        permessi.add(new Permesso("Utente", "RicercaProdottiNome", "doGet"));
        permessi.add(new Permesso("Ospite", "RicercaProdottiFiltro", "doGet"));
        permessi.add(new Permesso("Ospite", "RicercaProdottiNome", "doGet"));
        permessi.add(new Permesso("Gestore", "RicercaProdottiFiltro", "doGet"));
        permessi.add(new Permesso("Gestore", "RicercaProdottiNome", "doGet"));
        Mockito.lenient().when(servletContext.getAttribute("permessi")).thenReturn(permessi);

        ArrayList<String> stili = new ArrayList<>();
        stili.add("IPA");
        stili.add("Lager");
        stili.add("Porter");
        stili.add("Ale");
        stili.add("APA");
        stili.add("Pils");
        stili.add("Lambic");
        stili.add("Stout");
        stili.add("Trappista");
        Mockito.lenient().when(servletContext.getAttribute("stili")).thenReturn(stili);

        ArrayList<String> formati = new ArrayList<>();
        formati.add("Bottiglia");
        formati.add("Lattina");
        formati.add("Fusto");
        Mockito.lenient().when(servletContext.getAttribute("formati")).thenReturn(formati);

        ArrayList<String> fermentazioni = new ArrayList<>();
        fermentazioni.add("Alta");
        fermentazioni.add("Bassa");
        fermentazioni.add("Mista");
        fermentazioni.add("Spontanea");
        Mockito.lenient().when(servletContext.getAttribute("fermentazioni")).thenReturn(fermentazioni);

        ArrayList<String> colori = new ArrayList<>();
        colori.add("Bionda");
        colori.add("Blanche");
        colori.add("Ambrata");
        colori.add("Scura");
        colori.add("Rossa");
        Mockito.lenient().when(servletContext.getAttribute("colori")).thenReturn(colori);
    }

    public void setUp(String stile, String colore, String tassoAlcolico){
        // Simula il comportamento della servlet quando un account è già in sessione
        Mockito.lenient().when(account.getId()).thenReturn(-1);
        Mockito.lenient().when(session.getAttribute("account")).thenReturn(account);
        Mockito.lenient().when(request.getSession()).thenReturn(session);
        Mockito.lenient().when(request.getServletContext()).thenReturn(servletContext);

        // Simula i parametri di richiesta
        Mockito.lenient().when(request.getParameter("stile")).thenReturn(stile);
        Mockito.lenient().when(request.getParameter("colore")).thenReturn(colore);
        Mockito.lenient().when(request.getParameter("tassoAlcolico")).thenReturn(tassoAlcolico);

        Mockito.lenient().when(request.getRequestDispatcher("/WEB-INF/ricercaProdotti.jsp")).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoGet1() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("IP","Bionda","4.3");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet2() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("StringaPiuLungaDiTrentaCaratteri","Rossa","6.36");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet3() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("IP6","Rossa","3.5");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet4() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("IPA","bl","4.2");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet5() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("IPA","StringaPiuLungaDiTrentaCaratteri","3.5");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet6() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("IPA","Rossa 2","12.9");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet7() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("IPA","Rossa","3");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet8() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("IPA","Chiara","35.667");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet9() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("IPA","Scura","3.4a");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetCorretto10() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("IPA","Rossa","3.4");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet11() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("Stringa Di Trenta Caratteri 34","Rossa","13.5");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet12() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("IPAStringaDiTrentaCaratteriIPA","bl","3.5");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet13() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("IPAStringaDiTrentaCaratteriIPA","StringaPiuLungaDiTrentaCaratteri","6.5");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet14() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("IPAStringaDiTrentaCaratteriIPA","Scura 2","7.5");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet15() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("IPAStringaDiTrentaCaratteriIPA","Scura","7");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet16() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("IPAStringaDiTrentaCaratteriIPA","Rossa","15.897");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet17() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("IPAStringaDiTrentaCaratteriIPA","Chiara","b.60");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetCorretto18() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("IPAStringaDiTrentaCaratteriIPA","Rossa","3.45");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet19() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("Lam%bic","Rossa","1.5");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet20() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("Lambic","ch","9.0");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet21() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("PaleALE","StringaPiuLungaDiTrentaCaratteri","16.5");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet22() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("Trappista","gia lla","6.1");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet23() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("Altbier","Chiara","8.");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet24() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("Bealgain","Bionda","155.87");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGet25() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("Dunkel","Scura","6090");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetCorretto26() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUp("Rauchbier","Scura","7.95");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }


    

    //White
    public void setUpWhite(String stile, String colore, String tassoAlcolico, String offset, int idUtente, boolean gestore, String destinazione, String formato){
        // Simula il comportamento della servlet quando un account è già in sessione
        Mockito.lenient().when(account.getId()).thenReturn(idUtente);
        Mockito.lenient().when(account.isGestore()).thenReturn(gestore);
        Mockito.lenient().when(session.getAttribute("account")).thenReturn(account);
        Mockito.lenient().when(request.getSession()).thenReturn(session);
        Mockito.lenient().when(request.getServletContext()).thenReturn(servletContext);

        // Simula i parametri di richiesta
        Mockito.lenient().when(request.getParameter("stile")).thenReturn(stile);
        Mockito.lenient().when(request.getParameter("colore")).thenReturn(colore);
        Mockito.lenient().when(request.getParameter("tassoAlcolico")).thenReturn(tassoAlcolico);
        Mockito.lenient().when(request.getParameter("offset")).thenReturn(offset);
        Mockito.lenient().when(request.getParameter("formato")).thenReturn(formato);

        Mockito.lenient().when(request.getRequestDispatcher(destinazione)).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoGetWhiteErrore1() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("IPA","Rossa","31.4", "10", -1,false, "/WEB-INF/ricercaProdotti.jsp", "Lattina");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso

        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore2() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("IPA","Rossa","31.4", "10", 1,false,"/WEB-INF/ricercaProdotti.jsp","Lattina");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }
    
    @Test
    public void testDoGetWhiteErrore3() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("IPA","Rossa","31.4", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","Lattina");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore4() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("IPA","Rossa","31.4", "10f", -1,false, "ricercaProdottiFiltro","Lattina");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore5() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("IPA","Rossa","31.4", "10f", 1,false, "ricercaProdottiFiltro", "Lattina");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore6() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("IPA","Rossa","31.4", "10f", 1,true, "ricercaProdottiFiltro","Lattina");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }




    @Test
    public void testDoGetWhiteErrore7() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore8() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,null, "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore9() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore10() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,null, "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore11() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore12() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore13() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,"ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore14() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,"ft%$", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore15() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,"ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore16() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,"ft%$", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore17() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,"ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore18() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,"ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore19() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,"null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore20() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,"null", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore21() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,"null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore22() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,"null", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore23() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,"null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore24() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,null,"null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore25() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore26() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£",null, "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore27() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore28() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£",null, "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore29() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore30() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£",null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore31() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore32() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£","ft%$", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore33() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore34() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£","ft%$", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore35() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore36() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£","ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore37() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore38() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£","null", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore39() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore40() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£","null", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore41() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore42() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"dd$£","null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore43() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore44() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null",null, "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore45() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore46() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null",null, "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore47() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore48() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null",null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore49() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore50() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null","ft%$", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore51() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore52() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null","ft%$", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore53() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore54() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null","ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore55() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore56() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null","null", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore57() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore58() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null","null", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore59() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore60() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite(null,"null","null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }




    @Test
    public void testDoGetWhiteErrore61() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore62() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,null, "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore63() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore64() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,null, "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore65() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore66() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore67() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore68() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"ft%$", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore69() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore70() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"ft%$", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore71() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore72() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore73() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore74() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"null", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore75() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore76() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"null", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore77() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore78() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore79() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore80() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£",null, "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore81() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore82() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£",null, "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore83() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore84() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£",null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore85() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore86() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","ft%$", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore87() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore88() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","ft%$", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore89() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore90() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore91() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore92() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","null", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore93() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore94() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","null", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore95() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore96() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore97() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore98() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null",null, "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore99() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore100() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null",null, "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore101() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore102() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null",null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore103() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore104() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","ft%$", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore105() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore106() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","ft%$", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore107() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore108() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore109() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore110() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","null", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore111() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore112() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","null", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore113() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore114() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }




    @Test
    public void testDoGetWhiteErrore115() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore116() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,null, "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore117() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore118() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,null, "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore119() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore120() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore121() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore122() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"ft%$", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore123() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore124() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"ft%$", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore125() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore126() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore127() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore128() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"null", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore129() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore130() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"null", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore131() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore132() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore133() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore134() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£",null, "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore135() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore136() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£",null, "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore137() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore138() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£",null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore139() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore140() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","ft%$", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore141() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore142() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","ft%$", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore143() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore144() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore145() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore146() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","null", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore147() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore148() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","null", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore149() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore150() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore151() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore152() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null",null, "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore153() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore155() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null",null, "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore156() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore157() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null",null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore158() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore159() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","ft%$", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore160() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore161() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","ft%$", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore162() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore163() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore164() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore165() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","null", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore166() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore167() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","null", "10f", 1,true, "ricercaProdottiFiltro","La&&");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore168() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore169() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore170() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doPost(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }






    @Test
    public void testDoGetWhiteErrore171() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore172() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"ft%$", "10f", 1,true, "ricercaProdottiFiltro","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore173() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore174() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"null", "10f", 1,true, "ricercaProdottiFiltro","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore175() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore176() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore177() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore178() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"null", "10f", 1,true, "ricercaProdottiFiltro","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore179() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore180() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£",null, "10f", 1,true, "ricercaProdottiFiltro","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore181() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore182() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£",null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore183() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore184() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£",null, "10f", 1,true, "ricercaProdottiFiltro","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore185() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore186() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore187() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore188() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore189() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","ft%$", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore190() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","ft%$", "10f", 1,true, "ricercaProdottiFiltro","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore191() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore192() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore193() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore194() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore195() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","null", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore196() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","dd$£","null", "10f", 1,true, "ricercaProdottiFiltro","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore197() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore198() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null",null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore199() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null",null, "10", 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore200() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null",null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore201() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null",null, null, 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore202() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null",null, "10f", 1,true, "ricercaProdottiFiltro","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore203() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","ft%$", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore204() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","ft%$", "10f", 1,true, "ricercaProdottiFiltro","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore205() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","ft%$", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore206() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore207() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","ft%$", null, 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore208() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","ft%$", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore209() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","null", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore210() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore211() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","null", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore212() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore213() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","null", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore214() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$","null","null", "10f", 1,true, "ricercaProdottiFiltro","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore215() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,null, null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore216() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore217() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,null, null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore218() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore219() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,null, null, 1,true, "/WEB-INF/ricercaProdotti.jsp","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore220() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,null, "10f", 1,true, "ricercaProdottiFiltro","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore221() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"ft%$", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore222() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore223() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"ft%$", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore224() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore225() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"ft%$", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore226() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"ft%$", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore227() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"null", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore228() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore229() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"null", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore230() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore231() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"null", null, 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore232() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null",null,"null", "10f", 1,true, "ricercaProdottiFiltro","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore233() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£",null, null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore234() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£",null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore235() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£",null, null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore236() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£",null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore237() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£",null, null, 1,true, "/WEB-INF/ricercaProdotti.jsp","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore238() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£",null, "10f", 1,true, "ricercaProdottiFiltro","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore239() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","ft%$", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore240() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore241() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","ft%$", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore242() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore243() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","ft%$", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore244() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","ft%$", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore245() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","null", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore246() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore247() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","null", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore248() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore249() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","null", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore250() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","dd$£","null", "10f", 1,true, "ricercaProdottiFiltro","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore251() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null",null, null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore252() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null",null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore253() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null",null, null, 1,true, "/WEB-INF/ricercaProdotti.jsp","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore255() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null",null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore256() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null",null, null, 1,true, "/WEB-INF/ricercaProdotti.jsp","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore257() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null",null, "10f", 1,true, "ricercaProdottiFiltro","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore258() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","ft%$", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore259() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore260() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","ft%$", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore261() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","ft%$", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore262() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","ft%$", null, 1,true, "/WEB-INF/ricercaProdotti.jsp",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore263() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","ft%$", "10f", 1,true, "ricercaProdottiFiltro","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore264() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","null", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore265() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore266() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","null", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore267() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","null", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore268() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","null", null, 1,true, "/WEB-INF/ricercaProdotti.jsp","ft%$");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore269() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("null","null","null", "10f", 1,true, "ricercaProdottiFiltro",null);
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore270() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"ft%$", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore271() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,null, "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore272() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"ft%$ ", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore273() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("dd£$",null,"4.4", "10f", 1,true, "ricercaProdottiFiltro","null");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }
    @Test
    public void testDoGetWhiteErrore280() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        // Simula il comportamento della servlet quando un account è già in sessione
        Mockito.lenient().when(account.getId()).thenReturn(-1);
        Mockito.lenient().when(account.isGestore()).thenReturn(true);
        Mockito.lenient().when(session.getAttribute("account")).thenReturn(account);
        Mockito.lenient().when(request.getSession()).thenReturn(session);
        Mockito.lenient().when(request.getServletContext()).thenReturn(servletContext);

        // Simula i parametri di richiesta
        Mockito.lenient().when(request.getParameter("stile")).thenReturn(null);
        Mockito.lenient().when(request.getParameter("colore")).thenReturn(null);
        Mockito.lenient().when(request.getParameter("tassoAlcolico")).thenReturn(null);
        Mockito.lenient().when(request.getParameter("offset")).thenReturn(null);
        Mockito.lenient().when(request.getParameter("formato")).thenReturn(null);
        List<Permesso> permessi= new ArrayList<>();
        Mockito.lenient().when(servletContext.getAttribute("permessi")).thenReturn(permessi);

        Mockito.lenient().when(request.getRequestDispatcher("/WEB-INF/errorePermessi.jsp")).thenReturn(requestDispatcher);// Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }


}
