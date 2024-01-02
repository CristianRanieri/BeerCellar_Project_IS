package Utils.Other;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import model.DAO.CarrelloDAO;
import model.entity.Account;

@WebListener
public class MySessionListner implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Questo metodo viene chiamato quando una sessione viene creata
        Account account = new Account();
        account.setId(-1);

        se.getSession().setAttribute("account",account);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Questo metodo viene chiamato quando una sessione viene chiusa o invalidata
        CarrelloDAO carrelloDAO= new CarrelloDAO();
        Account account= (Account) se.getSession().getAttribute("account");

        if(account.getId()!=-1 && !account.isGestore())
            carrelloDAO.caricaCarrello(account.getCarrello(), account.getId());
    }
}
