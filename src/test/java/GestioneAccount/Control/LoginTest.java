package GestioneAccount.Control;

import GestioneAccount.Service.AccountService;

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
public class LoginTest {
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
    private Login loginServlet;

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

    public void setUpCorretto(String emil, String pass){
        // Simula il comportamento della servlet quando un account è già in sessione
        Mockito.lenient().when(account.getId()).thenReturn(-1);
        Mockito.lenient().when(session.getAttribute("account")).thenReturn(account);
        Mockito.lenient().when(request.getSession()).thenReturn(session);
        Mockito.lenient().when(request.getServletContext()).thenReturn(servletContext);

        // Simula i parametri di richiesta
        Mockito.lenient().when(request.getParameter("email")).thenReturn(emil);
        Mockito.lenient().when(request.getParameter("pass")).thenReturn(pass);

    }

    @Test
    public void tc_2_06() throws Exception {
        this.setUpCorretto("oi@a.eu","Ciao1234!?@#?");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(response).sendRedirect("index.jsp");
    }

    @Test
    public void tc_2_14() throws Exception {
        this.setUpCorretto("una.mail@presente.nel.db","Ciao1234!?@#?");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(response).sendRedirect("index.jsp");
    }
    @Test
    public void tc_2_10() throws Exception {
        this.setUpCorretto("ciaoaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@comevaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.ttok","Ciao1234!?@#?");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(response).sendRedirect("index.jsp");
    }

    public void setUpCredenzialiInesistentiOInvalide(String emil,String pass){
        Mockito.lenient().when(account.getId()).thenReturn(-1);
        Mockito.lenient().when(session.getAttribute("account")).thenReturn(account);
        Mockito.lenient().when(request.getSession()).thenReturn(session);
        Mockito.lenient().when(request.getServletContext()).thenReturn(servletContext);

        // Simula i parametri di richiesta
        Mockito.lenient().when(request.getParameter("email")).thenReturn(emil);
        Mockito.lenient().when(request.getParameter("pass")).thenReturn(pass);

        Mockito.lenient().when(request.getRequestDispatcher("/WEB-INF/login.jsp")).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoGetCredenzialiInesistenti1() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("utente12@gmail.com","Utente12345%");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetParametriInvalidi1() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("utente1gmail.com", "Utente12345%");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error1", true);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void tc_2_01() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("a@b.cd", "Ciao1234!?@#");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error1", true);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void tc_2_02() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@bbbbbbbbbbbbbbbbbbbbb.ccc", "Ciao1234!?@#");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error1", true);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void tc_2_03() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("o(@/.eu", "Ciao1234!?@#");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error1", true);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void tc_2_11() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("ciao(){}@//comeva.ttok", "Ciao1234!?@#");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error1", true);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void tc_2_04() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("EN@o.nl","Ciao1234!?@#");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void tc_2_08() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("QuestaMailNonEsisteAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@comevaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.ttok","Ciao1234!?@#");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void tc_2_12() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("una.mail.NON@presente.nel.db","Ciao1234!?@#");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void tc_2_05() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("oi@a.eu","Sbagliata1234!?@#");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void tc_2_13() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("una.mail@presente.nel.db","Sbagliata1234!?@#");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void tc_2_09() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("ciaoaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@comevaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.ttok","Sbagliata1234!?@#");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void tc_2_07() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("ciao(){}aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@//comevaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.ttok", "Ciao1234!?@#");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error1", true);
        verify(requestDispatcher).forward(request, response);
    }

    public void setUpUtenteLoggato(String emil, String pass, int idUtente, boolean gestore){
        Mockito.lenient().when(account.getId()).thenReturn(idUtente);
        Mockito.lenient().when(account.isGestore()).thenReturn(gestore);
        Mockito.lenient().when(session.getAttribute("account")).thenReturn(account);
        Mockito.lenient().when(request.getSession()).thenReturn(session);
        Mockito.lenient().when(request.getServletContext()).thenReturn(servletContext);

        // Simula i parametri di richiesta
        Mockito.lenient().when(request.getParameter("email")).thenReturn(emil);
        Mockito.lenient().when(request.getParameter("pass")).thenReturn(pass);
    }


    //white testing
    @Test
    public void testDoGetUtenteLoggato1() throws Exception {
        this.setUpUtenteLoggato("utente5@gmail.com","Utente1234%", 1, true);
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(response).sendRedirect("visualizzaAreaUtente");
    }

    @Test
    public void testDoGetUtenteLoggato2() throws Exception {
        this.setUpUtenteLoggato("utente5@gmail.com","Utente1234%", 1, false);
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(response).sendRedirect("visualizzaAreaUtente");
    }

    @Test
    public void testDoGetParametriInvalidi3() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide(null, null);
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error1", true);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoGetParametriInvalidi4() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide(null, "F$");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error1", true);
        verify(requestDispatcher).forward(request, response);
    }


    @Test
    public void testDoGetParametriInvalidi8() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide(null, "Utente1234%");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error1", true);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoGetParametriInvalidi5() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("email", null);
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error1", true);
        verify(requestDispatcher).forward(request, response);
    }


    @Test
    public void testDoGetParametriInvalidi9() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("email@gmail.com", null);
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error1", true);
        verify(requestDispatcher).forward(request, response);
    }


    @Test
    public void testDoGetParametriInvalidi10() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("email@gmail.com", "gygyG");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error1", true);
        verify(requestDispatcher).forward(request, response);
    }


    @Test
    public void testDoGetParametriInvalidi6() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("email", "F$");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error1", true);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoGetCorretto7() throws Exception {
        this.setUpCorretto("utente5@gmail.com","Utente1234%");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(response).sendRedirect("index.jsp");
    }

    @Test
    public void testDoGetCorretto8() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("utente5@gmail.com","Utenteeee1234%");
        // Esegui la servlet
        loginServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2", true);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoGetCorretto10() throws Exception {
        this.setUpCredenzialiInesistentiOInvalide("utente5@gmail.com","Utenteeee1234%");
        // Esegui la servlet
        loginServlet.doPost(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2", true);
        verify(requestDispatcher).forward(request, response);
    }

}