package GestioneProdotto.Service;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;
import model.entity.Prodotto;
import java.io.IOException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public void modificaProdotto(Prodotto prodotto){prodottoService.modificaProdotto(prodotto);}

    public ArrayList<Prodotto> ricercaProdottiFiltro(String formato, boolean gestore , ArrayList<String> filtro, int offset){
        return prodottoService.ricercaProdottiFiltro(formato, gestore, filtro, offset);
    }

    public ArrayList<Prodotto> ricercaProdottiNome(List<String> nomi, boolean gestore, int offset){
        return prodottoService.ricercaProdottiNome(nomi, gestore,offset);
    }
    public List<Prodotto> getProdottiPiuVenduti(){
        return prodottoService.getProdottiPiuVenduti();
    }
}
