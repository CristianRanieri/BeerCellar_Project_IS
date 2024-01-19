package GestioneProdotto.Service;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;
import model.entity.Prodotto;
import org.apache.taglibs.standard.lang.jstl.ELException;

import java.io.IOException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestioneProdottoService {
    private ProdottoService prodottoService;

    public GestioneProdottoService(){
        prodottoService = new ProdottoService();
    }
    public Prodotto getProdotto(int id) throws ProdottoException {
        return prodottoService.getProdotto(id);
    }

    public void creaProdotto(Prodotto prodotto, Part image, ServletContext context) throws IOException {
        prodottoService.creaProdotto(prodotto, image, context);
    }
    public void modificaProdotto(Prodotto prodotto,Part image,ServletContext context) throws ProdottoException, ModificaException, IOException {prodottoService.modificaProdotto(prodotto, image, context);}

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
