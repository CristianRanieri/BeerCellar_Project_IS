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
     public Account registraUtente(Account account){
         AccountDAO accountDAO= new AccountDAO();
         CarrelloDAO carrelloDAO= new CarrelloDAO();

         if(accountDAO.getAccountByEmail(account.getEmail()) == null){
             //non esiste un account registrato con questa email
             accountDAO.creaAccount(account);

             //si crea il carrello dell'utente
             carrelloDAO.creaCarrello(account.getId());
         }else {
             //esiste gia un account con questa email
             account = null;
         }
         return account;
     }

    public Account modificaDatiAccount(Account account){

        return account;
    }
}
