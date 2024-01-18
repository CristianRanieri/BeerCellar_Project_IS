package GestioneProdotto.Service;

import Utils.ValidazioneInput.PatternInput;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import model.DAO.ProdottoDAO;
import model.entity.Prodotto;
import org.apache.taglibs.standard.lang.jstl.ELException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    public void creaProdotto(Prodotto prodotto, Part image, ServletContext context) throws IOException {
        prodottoDAO.creaProdotto(prodotto);
        this.salvaImmagine(image, prodotto.getId(), context);
    }

    /**
     * Questo metodo si occupa di salvare nel path specificato l'immagine relativa al prodotto salvato
     * @param immagine oggetto di tipo Part che rappresenta l'immagine
     * @param id codice univoco del prodotto
     * @param servletContext il ServletContext per gestire il path
     */
    private void salvaImmagine(Part immagine, int id, ServletContext servletContext) throws IOException {
        String destinazione = "upload" + File.separator + "ID_" + id + ".png";
        Path pathDestinazione = Paths.get(servletContext.getRealPath(destinazione));

        InputStream imageInputStream = immagine.getInputStream();

        // crea la cartella upload, se non esiste
        Files.createDirectories(pathDestinazione.getParent());

        // Sovrascrive l'immagine se esiste già
        CopyOption[] options = { StandardCopyOption.REPLACE_EXISTING };
        Files.copy(imageInputStream, pathDestinazione, options);
    }

    /**
     * questa funzionalita permette di modificare un prodotto.
     * @param prodotto prodotto contente i valori da modificare.
     */
    public void modificaProdotto(Prodotto prodotto, Part image, ServletContext context) throws ELException, Error, IOException {
        Prodotto prodottoInDB = prodottoDAO.getProdottoById(prodotto.getId());

        if(prodottoInDB!=null) {
            if (prodottoInDB.isTheSame(prodotto) && image.getSize() == 0) {
                throw new ELException();
            } else {
                prodottoDAO.modificaProdotto(prodotto);

                if (image.getSize() != 0 && image.getContentType().contains("image")){
                    this.salvaImmagine(image, prodotto.getId(), context);
                }
            }
        }else
            throw new Error();
    }

    /**
     *
     * @param formato il formato delle birre da ricercare
     * @param gestore se il valore è true ricerca anche i prodotti non in catalogo altrimenti solo quelli in catalogo.
     * @param offset il numero dal quale parte la selezione dei prodotti idonei al nome.
     * @return la lista dei prodotti idonei al filtro.
     */
    public ArrayList<Prodotto> ricercaProdottiFiltro(String formato,boolean gestore,ArrayList<String> filtro,int offset){
        return prodottoDAO.getProdottiConFiltro(formato, gestore, filtro, offset);
    }

    /**
     * Quesat funzionalita permette di ricercare dei prodotti nel catalogo tramite il nome della birra e birrificaio.
     * @param nomi lista di nomi che vengono sulla quale viene effettuata la ricerca.
     * @param gestore se il valore è true ricerca anche i prodotti non in catalogo altrimenti solo quelli in catalogo.
     * @param offset il numero dal quale parte la selezione dei prodotti idonei al nome.
     * @return la lista dei prodotti che hanno come sottostringa i nomi indicati.
     */
    public ArrayList<Prodotto> ricercaProdottiNome(List<String> nomi, boolean gestore, int offset){return prodottoDAO.getProdottiPerNome(nomi, gestore,offset);}

    /**
     * questa operazione permette di ottenere i prodotti piu venduti.
     * @return la lista dei prodotti piu venduti
     */
    public List<Prodotto> getProdottiPiuVenduti(){
        return prodottoDAO.getProdottiPiuVenduti();
    }
}
