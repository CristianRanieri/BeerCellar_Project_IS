package GestioneAccount.Control;

import GestioneAccount.Service.AccountService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RegistrazioneTest {

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
    private AccountService accountService;

    @Mock
    private Account account;

    @InjectMocks
    private Registrazione registrazioneServlet;

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

    public void setUpCorretto(String emil, String pass, String confermaPass, String nome, int id){
        // Simula il comportamento della servlet quando un account è già in sessione
        Mockito.lenient().when(account.getId()).thenReturn(id);
        Mockito.lenient().when(session.getAttribute("account")).thenReturn(account);
        Mockito.lenient().when(request.getSession()).thenReturn(session);
        Mockito.lenient().when(request.getServletContext()).thenReturn(servletContext);

        // Simula i parametri di richiesta
        Mockito.lenient().when(request.getParameter("nome")).thenReturn(nome);
        Mockito.lenient().when(request.getParameter("email")).thenReturn(emil);
        Mockito.lenient().when(request.getParameter("pass")).thenReturn(pass);
        Mockito.lenient().when(request.getParameter("confermaPass")).thenReturn(confermaPass);


        // Simula il comportamento del metodo di login nel service
        Mockito.lenient().when(accountService.login(any(Account.class), any(HttpSession.class))).thenReturn(true);
    }

    @Test //TC_3_12
    public void testDoGetCorrettaNomeLunghezza2() throws Exception {
        this.setUpCorretto("giorgia@gmail.com","12345aAb!","12345aAb!","Su",-1);
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(response).sendRedirect("index.jsp");
    }

    @Test //TC_3_32
    public void testDoGetCorrettaNomeLunghezza6() throws Exception {
        this.setUpCorretto("giorgie@gmail.com","12345aABb!","12345aABb!","Ginaaa",-1);
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(response).sendRedirect("index.jsp");
    }

    @Test //TC_3_22
    public void testDoGetCorrettaNomeLunghezza30() throws Exception {
        this.setUpCorretto("giorg@gmail.com","12345aAb!","12345aAb!","Ginaaaaaaaaaaaaaaaaaaaaaaaaaaa",-1);
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(response).sendRedirect("index.jsp");
    }

    public void setUpEmailRegistrata(String emil, String pass, String confermaPass, String nome){
        Mockito.lenient().when(account.getId()).thenReturn(-1);
        Mockito.lenient().when(session.getAttribute("account")).thenReturn(account);
        Mockito.lenient().when(request.getSession()).thenReturn(session);
        Mockito.lenient().when(request.getServletContext()).thenReturn(servletContext);

        // Simula i parametri di richiesta
        Mockito.lenient().when(request.getParameter("nome")).thenReturn(nome);
        Mockito.lenient().when(request.getParameter("email")).thenReturn(emil);
        Mockito.lenient().when(request.getParameter("pass")).thenReturn(pass);
        Mockito.lenient().when(request.getParameter("confermaPass")).thenReturn(confermaPass);

        Mockito.lenient().when(request.getRequestDispatcher("/WEB-INF/registrazione.jsp")).thenReturn(requestDispatcher);

        // Simula il comportamento del metodo di login nel service
        Mockito.lenient().when(accountService.login(any(Account.class), any(HttpSession.class))).thenReturn(true);
    }

    @Test //TC_3_07
    public void testDoGetEmailRegistrataConNomeLunghezza2() throws Exception {
        this.setUpEmailRegistrata("giorgi@gmail.com","1234aAa!","1234aAa!","Su");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error1",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_27
    public void testDoGetEmailRegistrataConNomeLunghezza6() throws Exception {
        this.setUpEmailRegistrata("giorgi@gmail.com","12345aAb!","12345aAb!","Ginaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error1",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_17
    public void testDoGetEmailRegistrataConNomeLunghezza30() throws Exception {
        this.setUpEmailRegistrata("giorgi@gmail.com","12345aAb!","12345aAb!","Ginaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error1",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void testDoGetParametriInvalidi() throws Exception {
        this.setUpEmailRegistrata("utente5@gmailcom","Utente1234%","Utente1234%","utente");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_02
    public void testDoGetNomeCorto() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","1234aAa!","1234aAa!","A");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_02
    public void testDoGetNomeLungo() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","1234aAa!","1234aAa!","Ginaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_03
    public void testDoGetNomeFormatoErratoNomeLunghezza2() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","1234aAa!","1234aAa!","S1");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_23
    public void testDoGetNomeFormatoErratoNomeLunghezza6() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","12345aAb!","12345aAb!","Ginaa1");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_13
    public void testDoGetNomeFormatoErratoNomeLunghezza30() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","12345aAb!","12345aAb!","Ginaaaaaaaaaaaaaaaaaaaaaaaaaa1");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_04
    public void testDoGetEmailCortaNomeLunghezza2() throws Exception {
        this.setUpEmailRegistrata("g@i.it","1234aAa!","1234aAa!","Su");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_24
    public void testDoGetEmailCortaNomeLunghezza6() throws Exception {
        this.setUpEmailRegistrata("g@g.it","12345aAb!","12345aAb!","Ginaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_14
    public void testDoGetEmailCortaNomeLunghezza30() throws Exception {
        this.setUpEmailRegistrata("g@i.it","12345aAb!","12345aAb!","Ginaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_03_05
    public void testDoGetEmailLungaNomeLunghezza2() throws Exception {
        this.setUpEmailRegistrata("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.info","1234aAa!","1234aAa!","Su");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_25
    public void testDoGetEmailLungaNomeLunghezza6() throws Exception {
        this.setUpEmailRegistrata("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.info","12345aAb!","12345aAb!","Ginaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_15
    public void testDoGetEmailLungaNomeLunghezza30() throws Exception {
        this.setUpEmailRegistrata("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.info","12345aAb!","12345aAb!","Ginaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_08
    public void testDoGetPasswordCortaNomeLunghezza2() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","1234aA!","1234aA!","Su");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_28
    public void testDoGetPasswordCortaNomeLunghezza6() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","1234aA!","1234aA!","Ginaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_18
    public void testDoGetPasswordCortaNomeLunghezza30() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","1234aA!","1234aA!","Ginaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_09
    public void testDoGetPasswordLungaNomeLunghezza2() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","123423456789011223345arE&214aA!","123423456789011223345arE&214aA!","Su");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_29
    public void testDoGetPasswordLungaNomeLunghezza6() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","123423456789011223345arE&214aA!","123423456789011223345arE&214aA!","Ginaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_19
    public void testDoGetPasswordLungaNomeLunghezza30() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","123423456789011223345arE&214aA!","123423456789011223345arE&214aA!","Ginaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_10
    public void testDoGetPasswordFormatoErratoNomeLunghezza2() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","12345aAb","12345aAb","Su");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_30
    public void testDoGetPasswordFormatoErratoNomeLunghezza6() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","12345AB!","12345AB!","Ginaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_20
    public void testDoGetPasswordFormatoErratoNomeLunghezza30() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","12345aAb","12345aAb","Ginaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_06
    public void testDoGetEmailFormatoErratoNomeLunghezza2() throws Exception {
        this.setUpEmailRegistrata("giorgino@it","1234aAa!","1234aAa!","Su");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_26
    public void testDoGetEmailFormatoErratoNomeLunghezza6() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmailcom","12345aAb!","12345aAb!","Ginaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_16
    public void testDoGetEmailFormatoErratoNomeLunghezza30() throws Exception {
        this.setUpEmailRegistrata("giorgino@it","12345aAb!","12345aAb!","Ginaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_11
    public void testDoGetConfermaPasswordDiversaDaPasswordNomeLunghezza2() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","12345aAb!","12345aAb","Su");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_31
    public void testDoGetConfermaPasswordDiversaDaPasswordNomeLunghezza6() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","12345aABb!","12345aAB!","Ginaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }

    @Test //TC_3_21
    public void testDoGetConfermaPasswordDiversaDaPasswordNomeLunghezza30() throws Exception {
        this.setUpEmailRegistrata("giorgino@gmail.com","12345aAb!","12345aAb","Ginaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(request).setAttribute("error2",true);
        verify(requestDispatcher).forward(request,response);
    }


    @Test
    public void testDoGetUtenteLoggato() throws Exception {
        this.setUpCorretto("utente@gmail.com","Utente1234%","Utente1234%","utente",1);
        // Esegui la servlet
        registrazioneServlet.doGet(request, response);
        // Verifica il comportamento atteso
        verify(response).sendRedirect("visualizzaAreaUtente");
    }
}
