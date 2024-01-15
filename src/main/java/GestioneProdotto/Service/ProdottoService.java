package GestioneProdotto.Service;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;
import model.DAO.ProdottoDAO;
import model.entity.Prodotto;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoService {
    ProdottoDAO prodottoDAO = new ProdottoDAO();

    /**
     * questo metodo si occupa di ritirare un prodotto dal database.
     * @param id del prodotto da ricercare.
     * @return istanza del prodotto se viene trovato altrimenti null.
     */
    public Prodotto getProdotto(int id){
        return prodottoDAO.getProdottoById(id);
    }

    /**
     * Questo metodo si occupa della creazione di un prodotto a livello di persistenza
     * @param prodotto Oggetto prodotto da memorizzare
     * @return true se il prodotto è stato creato con successo false altrimenti.
     */
    public boolean creaProdotto(Prodotto prodotto){
        return prodottoDAO.creaProdotto(prodotto);
    }

    /**
     * Questo metodo si occupa di salvare nel path specificato l'immagine relativa al prodotto salvato
     * @param immagine oggetto di tipo Part che rappresenta l'immagine
     * @param id codice univoco del prodotto
     * @param servletContext il ServletContext per gestire il path
     */
    public void salvaImmagine(Part immagine, int id, ServletContext servletContext) throws IOException {
        String destinazione = "upload" + File.separator + "ID_" + id + ".png";
        Path pathDestinazione = Paths.get(servletContext.getRealPath(destinazione));

        InputStream imageInputStream = immagine.getInputStream();

        // crea la cartella upload, se non esiste
        Files.createDirectories(pathDestinazione.getParent());

        // Sovrascrive l'immagine se esiste già
        CopyOption[] options = { StandardCopyOption.REPLACE_EXISTING };
        Files.copy(imageInputStream, pathDestinazione, options);
    }

    public void modificaProdotto(Prodotto prodotto){
        prodottoDAO.modificaProdotto(prodotto);
    }

    public ArrayList<Prodotto> ricercaProdottiFiltro(String formato, boolean gestore ,ArrayList<String> filtro, int offset){
        return prodottoDAO.getProdottiConFiltro(formato, gestore, filtro, offset);
    }

    public ArrayList<Prodotto> ricercaProdottiNome(List<String> nomi, boolean gestore, int offset){
        return prodottoDAO.getProdottiPerNome(nomi, gestore,offset);
    }

}
