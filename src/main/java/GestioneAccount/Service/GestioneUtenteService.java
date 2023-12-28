package GestioneAccount.Service;

import jakarta.servlet.http.HttpSession;
import model.DAO.AccountDAO;
import model.DAO.CarrelloDAO;
import model.entity.Account;
import model.entity.Carrello;

public class GestioneUtenteService {
    /**
     * Implementa la funzionlità di login di un utente nella sessione.
     * @param account da loggare nella sessione.
     * @param session sessione nel quale inserire l'account.
     * @return true nel caso in cui il login avviene con successo alotrimenti false.
     */
    public boolean login(Account account, HttpSession session){
        AccountDAO accountDAO= new AccountDAO();
        CarrelloDAO carrelloDAO= new CarrelloDAO();

        account = accountDAO.getUtenteByEmailPass(account);
        if (account!=null){
            //l'account è stato trovato
            session.setAttribute("account",account);

            //si carica il carrello dell'utente nella sessione
            Carrello carrello = carrelloDAO.getCarrelloByIdUtente(account.getId());
            //se il carrello caricato dal database non è vuoto allaroa sovrascrive quello in sessione altrimenti si lascia il contenuto inserito prima del login
            if(!carrello.isEmpty()){
                //il carrello non è vuoto, quindi si sostituisce
                session.setAttribute("carrello", carrello);
            }else {
                //il carrello è vuoto quindi cambio l'id da -1 generico per utenti non loggati con quello dell'utente
                ((Carrello)session.getAttribute("carrello")).setId(account.getId());
            }
        }
        return account!=null;
    }

    /**
     * Implementa la funzionalità di rimozione dell'utente dalla sessione.
     * @param session dal quale rimuovere l'utente
     */
    public void logout(HttpSession session){
        session.invalidate();
    }

    /**
     * Metodo per la verifica di un utente nella sessione.
     * @param session nella quale cercare l'utente.
     * @return true se l'utente si trova in sessione altrimenti false.
     */
    public boolean isLogged(HttpSession session){
        return session.getAttribute("account") != null;
    }
}
