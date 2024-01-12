package GestioneCarrello.Service;

import model.entity.Carrello;

public class GestioneCarrelloService {
    public GestioneCarrelloService(){
        carrelloService= new CarrelloService();
    }

    public void caricaCarrello(Carrello carrello, int id){
        carrelloService.caricaCarrello(carrello,id);
    }
    private CarrelloService carrelloService;
}
