package GestioneOrdini.Service;

import model.entity.Account;
import model.entity.Ordine;

import java.util.ArrayList;

public class GestioneOrdiniService {
    private OrdiniService ordiniService;
    public GestioneOrdiniService() {
        ordiniService = new OrdiniService();
    }
    public void effettuaOrdine(Ordine ordine){
        ordiniService.effettuaOrdine(ordine);
    }
    public ArrayList<Ordine> ricercaOrdini(String tipoID, int numero, int offset){
        return ordiniService.ricercaOrdini(tipoID, numero, offset);
    }
    public ArrayList<Ordine> visualizzaOrdini(Account account, int offset){
        return ordiniService.visualizzaOrdini(account, offset);
    }

}
