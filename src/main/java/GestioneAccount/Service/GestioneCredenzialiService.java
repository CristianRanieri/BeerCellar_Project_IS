package GestioneAccount.Service;

import model.DAO.AccountDAO;
import model.DAO.CarrelloDAO;
import model.entity.Account;

public class GestioneCredenzialiService {
    /**
     * Implementa la funzionalità di registrazione di un account utente.
     * @param account  da registrare nel databse.
     * @return account se la registrazione va a buon fine altrimenti null.
     */
     public void registraUtente(Account account) throws AccountException {
         AccountDAO accountDAO= new AccountDAO();
         CarrelloDAO carrelloDAO= new CarrelloDAO();

         if(accountDAO.getAccountByEmail(account.getEmail()) == null){
             //non esiste un account registrato con questa email
             accountDAO.creaAccount(account);

             //si crea il carrello dell'utente
             carrelloDAO.creaCarrello(account.getId());
         }else
             throw new AccountException("email gia in utilizzo");
     }

    /**
     * Implementa la funzionalità di modifica delle credenziali di un utente registrato.
     * @param account che contine la nuova passowrd e nome del account.
     * @return true se la password è diversa dal quella attuale altrimenti false.
     */
    public void modificaDatiAccount(Account account) throws AccountException {
        AccountDAO accountDAO= new AccountDAO();
        Account accountSearch = accountDAO.getUtenteByEmailPass(account);

       if(accountSearch==null){
            //le password sono diverse quidi si effettua la modifica
            accountDAO.updateNomePassword(account);
        }else
           //le password sono uguali
            throw new AccountException("le password sono uguali");
    }
}
