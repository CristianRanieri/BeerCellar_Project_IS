package GestioneAccount.Service;

import model.DAO.AccountDAO;
import model.entity.Account;

public class GestioneCredenzialiService {
     public Account registraUtente(Account account){
         AccountDAO accountDAO= new AccountDAO();

         if(accountDAO.getAccountByEmail(account.getEmail()) == null){
             //non esiste un account registrato con questa email
             accountDAO.creaAccount(account);
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
