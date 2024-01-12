package GestioneProdotto.Service;

import model.entity.Prodotto;

public class GestioneProdottoService {
    public GestioneProdottoService(){
        prodottoService = new ProdottoService();
    }

    public Prodotto getProdotto(int id){
        return prodottoService.getProdotto(id);
    }

    private ProdottoService prodottoService;
}
