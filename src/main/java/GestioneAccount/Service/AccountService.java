package GestioneAccount.Service;

import jakarta.servlet.http.HttpSession;
import model.entity.Account;

public class AccountService {
    public AccountService() {
        this.gestioneUtenteService = new GestioneUtenteService();
        this.gestioneCredenzialiService = new GestioneCredenzialiService();
    }

    public void registraUtente(Account account) throws AccountException {
        gestioneCredenzialiService.registraUtente(account);
    }

    public void modificaDatiAccount(Account account) throws AccountException {
        gestioneCredenzialiService.modificaDatiAccount(account);
    }

    public void login(Account account,HttpSession session) throws AccountException {
        gestioneUtenteService.login(account,session);
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
