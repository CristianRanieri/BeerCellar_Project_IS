package GestioneAccount.Service;

import jakarta.servlet.http.HttpSession;
import model.entity.Account;

public class GestioneUtenteService {

    public Account login(Account account){
        return account;
    }

    public void logout(HttpSession session){

    }

    public boolean isLogged(HttpSession session){
        return session.getAttribute("account") != null;
    }
}
