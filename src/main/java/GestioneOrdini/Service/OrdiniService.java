package GestioneOrdini.Service;

import model.DAO.OrdineDAO;
import model.entity.Account;
import model.entity.AcquistoProdotto;
import model.entity.Ordine;

import java.util.ArrayList;

public class OrdiniService {

    public void effettuaOrdine(Ordine ordine){
        OrdineDAO ordineDAO = new OrdineDAO();
        boolean b=ordineDAO.creaOrdine(ordine);
    }

    //puo essere utilizzato solo dai gestori
    public ArrayList<Ordine> ricercaOrdini(String tipoID, int numero, int offset){
        ArrayList<Ordine> ordini = new ArrayList<>();
        OrdineDAO ordineDAO = new OrdineDAO();

        if(tipoID.equals("Utente"))
            ordini= ordineDAO.getOrdiniUtente(numero,offset);
        else
            ordini.add(ordineDAO.getOrdine(numero));

        return ordini;
    }

    public ArrayList<Ordine> visualizzaOrdini(Account account,int offset){
        ArrayList<Ordine> ordini = null;
        OrdineDAO ordineDAO = new OrdineDAO();

        if(account.isGestore())
            ordini= ordineDAO.getOrdiniAll(offset);
        else
            ordini= ordineDAO.getOrdiniUtente(account.getId(),offset);

        return ordini;
    }
}
