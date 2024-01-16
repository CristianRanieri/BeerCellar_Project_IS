package GestioneOrdini.Control;

import GestioneOrdini.Service.OrdiniService;
import Utils.Other.Permesso;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RicercaOrdiniTest {

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
    private OrdiniService ordiniService;

    @Mock
    private Account account;

    @InjectMocks
    private RicercaOrdini ricercaOrdiniServlet;

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
    }

    public void setUpCorretto(String tipoID, String numero){
        // Simula il comportamento della servlet quando un account è già in sessione
        Mockito.lenient().when(account.getId()).thenReturn(1);
        Mockito.lenient().when(account.isGestore()).thenReturn(true);
        Mockito.lenient().when(session.getAttribute("account")).thenReturn(account);
        Mockito.lenient().when(request.getSession()).thenReturn(session);
        Mockito.lenient().when(request.getServletContext()).thenReturn(servletContext);

        // Simula i parametri di richiesta
        Mockito.lenient().when(request.getParameter("tipoID")).thenReturn(tipoID);
        Mockito.lenient().when(request.getParameter("numero")).thenReturn(numero);

        Mockito.lenient().when(request.getRequestDispatcher("/WEB-INF/ordini.jsp")).thenReturn(requestDispatcher);

    }

    @Test
    public void testDoGetCorretta() throws Exception {
        this.setUpCorretto("Utente","1");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("ricerca",true);
    }

    @Test
    public void testDoGetCorretta6() throws Exception {
        this.setUpCorretto("Utente","1");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("ricerca",true);
    }

    @Test
    public void testDoGetCorretta8() throws Exception {
        this.setUpCorretto("Utente","1234");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("ricerca",true);
    }

    @Test
    public void testDoGetCorretta10() throws Exception {
        this.setUpCorretto("Utente","34");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("ricerca",true);
    }

    @Test
    public void testDoGetCorretta14() throws Exception {
        this.setUpCorretto("Ordine","1");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("ricerca",true);
    }

    @Test
    public void testDoGetCorretta16() throws Exception {
        this.setUpCorretto("Ordine","1234");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("ricerca",true);
    }

    @Test
    public void testDoGetCorretta18() throws Exception {
        this.setUpCorretto("Ordine","34");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("ricerca",true);
    }

    public void setUpParametriInvalidi(String tipoID, String numero){
        // Simula il comportamento della servlet quando un account è già in sessione
        Mockito.lenient().when(account.getId()).thenReturn(1);
        Mockito.lenient().when(account.isGestore()).thenReturn(true);
        Mockito.lenient().when(session.getAttribute("account")).thenReturn(account);
        Mockito.lenient().when(request.getSession()).thenReturn(session);
        Mockito.lenient().when(request.getServletContext()).thenReturn(servletContext);

        // Simula i parametri di richiesta
        Mockito.lenient().when(request.getParameter("tipoID")).thenReturn(tipoID);
        Mockito.lenient().when(request.getParameter("numero")).thenReturn(numero);

        Mockito.lenient().when(request.getRequestDispatcher("visualizzaOrdini")).thenReturn(requestDispatcher);

    }

    @Test
    public void testDoGetParametriInvalidi() throws Exception {
        this.setUpParametriInvalidi("Utent","1");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("error2",true);
    }

    @Test
    public void testDoGetParametriInvalidi1() throws Exception {
        this.setUpParametriInvalidi("ID","34");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("error2",true);
    }

    @Test
    public void testDoGetParametriInvalidi2() throws Exception {
        this.setUpParametriInvalidi("ID","34");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("error2",true);
    }

    @Test
    public void testDoGetParametriInvalidi3() throws Exception {
        this.setUpParametriInvalidi("Utente","");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("error2",true);
    }

    @Test
    public void testDoGetParametriInvalidi4() throws Exception {
        this.setUpParametriInvalidi("Utente","12345");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("error2",true);
    }

    @Test
    public void testDoGetParametriInvalidi5() throws Exception {
        this.setUpParametriInvalidi("Utente","a");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("error2",true);
    }

    @Test
    public void testDoGetParametriInvalidi7() throws Exception {
        this.setUpParametriInvalidi("Utente","123a");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("error2",true);
    }

    @Test
    public void testDoGetParametriInvalidi9() throws Exception {
        this.setUpParametriInvalidi("Utente","34a");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("error2",true);
    }

    @Test
    public void testDoGetParametriInvalidi11() throws Exception {
        this.setUpParametriInvalidi("Ordine","");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("error2",true);
    }

    @Test
    public void testDoGetParametriInvalidi12() throws Exception {
        this.setUpParametriInvalidi("Ordine","12345");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("error2",true);
    }

    @Test
    public void testDoGetParametriInvalidi13() throws Exception {
        this.setUpParametriInvalidi("Ordine","a");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("error2",true);
    }

    @Test
    public void testDoGetParametriInvalidi15() throws Exception {
        this.setUpParametriInvalidi("Ordine","123a");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("error2",true);
    }

    @Test
    public void testDoGetParametriInvalidi17() throws Exception {
        this.setUpParametriInvalidi("Ordine","34a");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("error2",true);
    }

    public void setUpWhite(String tipoID, String numero, String offset, int idUtente, boolean gestore, String forward){
        Mockito.lenient().when(account.getId()).thenReturn(idUtente);
        Mockito.lenient().when(account.isGestore()).thenReturn(gestore);
        Mockito.lenient().when(session.getAttribute("account")).thenReturn(account);
        Mockito.lenient().when(request.getSession()).thenReturn(session);
        Mockito.lenient().when(request.getServletContext()).thenReturn(servletContext);

        // Simula i parametri di richiesta
        Mockito.lenient().when(request.getParameter("tipoID")).thenReturn(tipoID);
        Mockito.lenient().when(request.getParameter("numero")).thenReturn(numero);
        Mockito.lenient().when(request.getParameter("offset")).thenReturn(offset);

        Mockito.lenient().when(request.getRequestDispatcher(forward)).thenReturn(requestDispatcher);

    }

    @Test
    public void testDoGetWhite1() throws Exception {
        this.setUpWhite("Utente","1", "10",1,false,"/WEB-INF/errorePermessi.jsp");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhite2() throws Exception {
        this.setUpWhite("Utente","1", "10",-1,false,"/WEB-INF/errorePermessi.jsp");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetWhite3() throws Exception {
        this.setUpWhite("Utente","1", "1l0",1,true,"visualizzaOrdini");
        // Esegui la servlet
        ricercaOrdiniServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("error1",true);
    }
}
