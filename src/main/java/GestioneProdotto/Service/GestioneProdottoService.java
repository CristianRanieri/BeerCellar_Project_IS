package GestioneProdotto.Service;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;
import model.entity.Prodotto;

import java.io.IOException;

public class GestioneProdottoService {
    private ProdottoService prodottoService;

    public GestioneProdottoService(){
        prodottoService = new ProdottoService();
    }
    public Prodotto getProdotto(int id){
        return prodottoService.getProdotto(id);
    }
    public void salvaImmagine(Part immagine, int id, ServletContext servletContext) throws IOException {
        prodottoService.salvaImmagine(immagine, id, servletContext);
    }
    public boolean creaProdotto(Prodotto prodotto){return prodottoService.creaProdotto(prodotto);}
}
