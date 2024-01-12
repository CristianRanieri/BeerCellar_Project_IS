package GestioneCarrello.Service;

import model.DAO.CarrelloDAO;
import model.entity.Carrello;

public class CarrelloService {
    /**
     * questo metodo si occupda di caricare il carrello aggiornato nel database
     * @param carrello il carrello con tutti i prodotti da aggiornare
     * @param id dell'utente
     */
    public void caricaCarrello(Carrello carrello, int id){
        CarrelloDAO carrelloDAO= new CarrelloDAO();
        carrelloDAO.caricaCarrello(carrello, id);
    }
}
