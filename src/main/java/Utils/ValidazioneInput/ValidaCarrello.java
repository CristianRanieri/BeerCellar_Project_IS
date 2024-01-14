package Utils.ValidazioneInput;

import GestioneProdotto.Service.GestioneProdottoService;
import model.entity.Carrello;
import model.entity.ContenutoCarrello;
import model.entity.Prodotto;
import java.util.ArrayList;

public class ValidaCarrello {
    /**
     * si occupa di validare il contenuto del carrello rispetto rispetto allo stato dei prodotti sul database.
     * @param carrelloAttuale è il carrello attuale dell'utente.
     * @return restituisce true se tutti i prodotti sono validi false se alemo uno di essi non è valido.
     */
    public static boolean validazioneCarrello(Carrello carrelloAttuale){
        GestioneProdottoService prodottoService = new GestioneProdottoService();
        Prodotto prodotto;
        ArrayList<ContenutoCarrello> contenutoCarrelloNuovo = new ArrayList<>();
        boolean b= true;

        //controllo che ogni prodotto nel carrello sia valido
        for(ContenutoCarrello contenutoCarrello : carrelloAttuale.getContenutoCarrello()){
            int id = contenutoCarrello.getProdotto().getId();
            //prendo il prodotto dal db
            prodotto = prodottoService.getProdotto(id);

            //controllo che siano uguali
            if(!prodotto.equals(contenutoCarrello.getProdotto()) || !prodotto.isInCatalogo()){
                //i prodotti sono diversi
                if(prodotto.isInCatalogo()){
                    //setto il nuovo prodotto nel contenuto del carrello
                    contenutoCarrello.setProdotto(prodotto);
                    //lo aggiungo alla nuovo lista
                    contenutoCarrelloNuovo.add(contenutoCarrello);
                }

                b= false;
            }else {
                //i prodotti sono uguali
                contenutoCarrelloNuovo.add(contenutoCarrello);
            }
        }
        carrelloAttuale.setContenutoCarrello(contenutoCarrelloNuovo);
        return b;
    }
}
