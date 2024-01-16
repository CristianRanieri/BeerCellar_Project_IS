package Utils.Other;

import GestioneProdotto.Service.GestioneProdottoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
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

        ArrayList<String> tassoAlcolico= new ArrayList<>();
        tassoAlcolico.add("0-6");
        tassoAlcolico.add("7-14");
        tassoAlcolico.add("15-29");
        tassoAlcolico.add("30-49");
        tassoAlcolico.add("50-70");
        getServletContext().setAttribute("fascieTassoAlcolico",tassoAlcolico);

        GestioneProdottoService prodottoService= new GestioneProdottoService();
        getServletContext().setAttribute("dataProdottiPiuVenduti",new Date());
        getServletContext().setAttribute("prodottiPiuVenduti", prodottoService.getProdottiPiuVenduti());

        super.init();
    }
}
