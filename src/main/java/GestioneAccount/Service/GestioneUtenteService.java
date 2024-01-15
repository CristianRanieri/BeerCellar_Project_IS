package GestioneAccount.Service;

import jakarta.servlet.http.HttpSession;
import model.DAO.AccountDAO;
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
        Carrello carrelloAttuale = account.getCarrello();

        //si prende l'utente dal database tramite le credenziali
        account = accountDAO.getUtenteByEmailPass(account);
        if (account != null){
            //l'account è stato trovato
            //se il carrello caricato dal database non è vuoto allora sovrascrive quello in sessione altrimenti si lascia il contenuto inserito prima del login
            if(!account.isGestore() && account.getCarrello().isEmpty()){
                //il carrello non è vuoto, quindi si mantiene quello in sessione
                //si setta il carrello in sessione nella nuova istanza di account
                account.setCarrello(carrelloAttuale);
            }

            session.setAttribute("account", account);
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
        return ((Account)session.getAttribute("account")).getId() != -1;
    }
}
