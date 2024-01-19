package GestioneOrdini.Service;

import Utils.Other.Pagamento;
import model.entity.Account;
import model.entity.Ordine;
import java.util.ArrayList;

public class GestioneOrdiniService {
    private OrdiniService ordiniService;
    public GestioneOrdiniService() {
        ordiniService = new OrdiniService();
    }
    public void effettuaOrdine(Ordine ordine, Pagamento pagamento) throws OrdiniException {
        ordiniService.effettuaOrdine(ordine, pagamento);
    }
    public ArrayList<Ordine> ricercaOrdini(String tipoID, int numero, int offset){
        return ordiniService.ricercaOrdini(tipoID, numero, offset);
    }
    public ArrayList<Ordine> visualizzaOrdini(Account account, int offset){
        return ordiniService.visualizzaOrdini(account, offset);
    }

}
