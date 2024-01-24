package GestioneCarrello.Service;

import jakarta.servlet.http.HttpSession;
import model.entity.Carrello;

public class GestioneCarrelloService {
    public GestioneCarrelloService(){
        carrelloService= new CarrelloService();
    }

    public void caricaCarrello(Carrello carrello, int id){
        carrelloService.caricaCarrello(carrello,id);
    }

    public void rimuoviProdotto(int idProdotto, HttpSession session) throws CarrelloException { carrelloService.rimuoviProdotto(idProdotto, session);}

    public void aggiungiProdotto(int idProdotto, int quantita, HttpSession session) throws CarrelloException {carrelloService.aggiungiProdotto(idProdotto, quantita, session);}
    private CarrelloService carrelloService;
}
