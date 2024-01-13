package GestioneProdotto.Service;

import model.DAO.ProdottoDAO;
import model.entity.Prodotto;


public class ProdottoService {
    /**
     * questo metodo si occupa di ritirare un prodotto dal database.
     * @param id del prodotto da ricercare.
     * @return istanza del prodotto se viene trovato altrimenti null.
     */
    public Prodotto getProdotto(int id){
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        return prodottoDAO.getProdottoById(id);
    }
}
