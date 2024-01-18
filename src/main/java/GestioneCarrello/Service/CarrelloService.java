package GestioneCarrello.Service;

import GestioneProdotto.Service.GestioneProdottoService;
import Utils.ValidazioneInput.PatternInput;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import model.DAO.AccountDAO;
import model.DAO.CarrelloDAO;
import model.DAO.ProdottoDAO;
import model.entity.Account;
import model.entity.Carrello;
import model.entity.ContenutoCarrello;
import model.entity.Prodotto;

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

    public void aggiungiProdotto(int idProdotto, int quantita, HttpSession session) throws Exception {
        //controllare che il prodotto esite e che sia in catalogo
        Account account= (Account) session.getAttribute("account");

        ProdottoDAO prodottoDAO= new ProdottoDAO();

        Prodotto prodotto= prodottoDAO.getProdottoById(Integer.parseInt(String.valueOf(idProdotto)));
        if(prodotto != null && prodotto.isInCatalogo()){
            //il prodotto esiste ed è in catalogo
            //controllo che il prodotto non sia nel carrello
            boolean b= true;
            for(ContenutoCarrello cc: account.getCarrello().getContenutoCarrello()){
                if(cc.getProdotto().getId() == Integer.parseInt(String.valueOf(idProdotto))){
                    //il prodotto è gia presente, aumento solo la quantita
                    int nuvo = cc.getQuantita() + Integer.parseInt(String.valueOf(quantita));
                    if(nuvo <= 99)
                        cc.setQuantita(nuvo);
                    b=false;
                }
            }

            //il prodotto non è gia presente, lo inserisco
            if(b) {
                ContenutoCarrello c= new ContenutoCarrello();
                c.setProdotto(prodotto);
                c.setQuantita(quantita);
                account.getCarrello().getContenutoCarrello().add(c);
            }
        }else {
            throw new Exception();
        }
    }

    public void rimuoviProdotto(int idProdotto,HttpSession session) throws Exception {
        //controllare che il prodotto esite e che sia in catalogo
        Account account = (Account) session.getAttribute("account");

        //controllo che il prodotto non sia nel carrello
        ContenutoCarrello cDaRimuovere = null;
        for (ContenutoCarrello cc : account.getCarrello().getContenutoCarrello()) {
            if (cc.getProdotto().getId() == Integer.parseInt(String.valueOf(idProdotto))){
                //il prodotto è presente, quindi lo memorizzo
                cDaRimuovere = cc;
            }
        }

        if (cDaRimuovere != null)
            //il prodotto è presente, lo rimuovo
            account.getCarrello().getContenutoCarrello().remove(cDaRimuovere);
        else
            //il prodotto non è presente, lancio eccezione
            throw new Exception();
    }

}
