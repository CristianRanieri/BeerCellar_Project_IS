package GestioneAccount.Service;

import jakarta.servlet.http.HttpSession;
import model.entity.Account;

public class AccountService {
    public AccountService() {
        this.gestioneUtenteService = new GestioneUtenteService();
        this.gestioneCredenzialiService = new GestioneCredenzialiService();
    }

    public Account registraUtente(Account account){
        return gestioneCredenzialiService.registraUtente(account);
    }

    public boolean modificaDatiAccount(Account account){
        return gestioneCredenzialiService.modificaDatiAccount(account);
    }

    public boolean login(Account account,HttpSession session){
        return gestioneUtenteService.login(account,session);
    }

    public void logout(HttpSession session){
        gestioneUtenteService.logout(session);
    }

    public boolean isLogged(HttpSession session){
        return gestioneUtenteService.isLogged(session);
    }

    private GestioneUtenteService gestioneUtenteService;
    private GestioneCredenzialiService gestioneCredenzialiService;
}
