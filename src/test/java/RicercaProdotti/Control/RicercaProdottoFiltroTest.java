package RicercaProdotti.Control;

import GestioneProdotto.Service.ProdottoService;
import Utils.Other.Permesso;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
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
    private ProdottoService prodottoService;
    @Mock
    private Account account;
    @InjectMocks
    private RicercaProdottiFiltro ricercaProdottiFiltroServlet;

    @BeforeEach
    public void setUp() throws ServletException {
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
    public void setUpWhite(String stile, String colore, String tassoAlcolico, String offset, int idUtente, boolean gestore, String destinazione){
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

        Mockito.lenient().when(request.getRequestDispatcher(destinazione)).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoGetWhiteErrore1() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("IPA","Rossa","31.4", "10", -1,false, "/WEB-INF/ricercaProdotti.jsp");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso

        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore2() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("IPA","Rossa","31.4", "10", 1,false,"/WEB-INF/ricercaProdotti.jsp");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }
    
    @Test
    public void testDoGetWhiteErrore3() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("IPA","Rossa","31.4", "10", 1,true, "/WEB-INF/ricercaProdotti.jsp");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore4() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("IPA","Rossa","31.4", "10f", -1,false, "ricercaProdottiFiltro");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore5() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("IPA","Rossa","31.4", "10f", 1,false, "ricercaProdottiFiltro");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhiteErrore6() throws Exception {
        // Simula il comportamento della servlet quando un account è già in sessione
        this.setUpWhite("IPA","Rossa","31.4", "10f", 1,true, "ricercaProdottiFiltro");
        // Esegui la servlet
        ricercaProdottiFiltroServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }
}
