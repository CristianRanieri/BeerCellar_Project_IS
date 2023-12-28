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

    public Account modificaDatiAccount(Account account){
        return gestioneCredenzialiService.modificaDatiAccount(account);
    }

    public Account login(Account account){
        return gestioneUtenteService.login(account);
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
