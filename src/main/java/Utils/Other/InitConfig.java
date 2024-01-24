package Utils.Other;

import GestioneProdotto.Service.GestioneProdottoService;
import Utils.ValidazioneInput.PatternInput;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import model.DAO.AccountDAO;
import model.DAO.CarrelloDAO;
import model.DAO.OrdineDAO;
import model.DAO.ProdottoDAO;
import model.entity.Account;
import model.entity.ContenutoCarrello;
import model.entity.Ordine;
import model.entity.Prodotto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

//questa classe serve per inserire le liste dello stile,colore e fasce del tasso alcolico all'intero del applicationContext, le quali vengono
// utilizzate per creare le select nella pagina della ricerca dei prodotti e nella pagina pagina di inserimento di un nuvo prodotto.
@WebServlet(urlPatterns ="/myinit",loadOnStartup = 0)
public class InitConfig extends HttpServlet {

    @Override
    public void init() throws ServletException {
        Comparator comparator= new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };

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
        //Set nell'application
        getServletContext().setAttribute("permessi",permessi);

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
        stili.sort(comparator);
        getServletContext().setAttribute("stili",stili);

        ArrayList<String> formati = new ArrayList<>();
        formati.add("Bottiglia");
        formati.add("Lattina");
        formati.add("Fusto");
        formati.sort(comparator);
        getServletContext().setAttribute("formati", formati);

        ArrayList<String> fermentazioni = new ArrayList<>();
        fermentazioni.add("Alta");
        fermentazioni.add("Bassa");
        fermentazioni.add("Mista");
        fermentazioni.add("Spontanea");
        fermentazioni.sort(comparator);
        getServletContext().setAttribute("fermentazioni", fermentazioni);

        ArrayList<String> colori = new ArrayList<>();
        colori.add("Bionda");
        colori.add("Blanche");
        colori.add("Ambrata");
        colori.add("Scura");
        colori.add("Rossa");
        colori.sort(comparator);
        getServletContext().setAttribute("colori",colori);

        GestioneProdottoService prodottoService= new GestioneProdottoService();
        getServletContext().setAttribute("dataProdottiPiuVenduti",new Date());
        getServletContext().setAttribute("prodottiPiuVenduti", prodottoService.getProdottiPiuVenduti());

        //validita del sistema

        AccountDAO accountDAO= new AccountDAO();
        for(Account account: accountDAO.getAccountAll())
            if(!(PatternInput.numeri1_4Cifre(String.valueOf(account.getId())) && account.getPassword()!=null && account.getNome()!=null && account.getEmail()!=null))
                getServletContext().setAttribute("errore-validazione","L'account con id:"+account.getId()+" ha causato dei problemi durante la fase di inizializzazione del sistema.");

        CarrelloDAO carrelloDAO= new CarrelloDAO();
        for (ContenutoCarrello c: carrelloDAO.getCarrelloAll())
            if (!PatternInput.numeri1_2Cifre(String.valueOf(c.getQuantita())))
                getServletContext().setAttribute("errore-validazione","Il contenuto di un carrello ha causato dei problemi durante la fase di inizializzazione del sistema.");

        OrdineDAO ordineDAO = new OrdineDAO();
        for (Ordine ordine: ordineDAO.getOrdiniAll())
            if (!(ordine.getProdotti()!=null && ordine.getData()!=null && ordine.getCAP()!=null && ordine.getCitta()!=null && ordine.getIndirizzo()!=null && ordine.getProvincia()!=null))
                getServletContext().setAttribute("errore-validazione","L'ordine con id:"+ ordine.getId()+"ha causato dei promblemi durante la fase di inizializzazione del sistema.");

        ProdottoDAO prodottoDAO = new ProdottoDAO();
        for (Prodotto prodotto: prodottoDAO.getProdottiAll())
            if (!(PatternInput.numeri1_4Cifre(String.valueOf(prodotto.getId())) && prodotto.getBirrificio()!=null && prodotto.getNome()!=null && prodotto.getColore()!=null && prodotto.getDescrizione()!=null && prodotto.getFermentazione()!=null && prodotto.getFormato()!=null && prodotto.getStile()!=null))
                getServletContext().setAttribute("errore-validazione","Il prodotto con id:"+ prodotto.getId()+"ha causato dei promblemi durante la fase di inizializzazione del sistema.");

        super.init();
    }
}
