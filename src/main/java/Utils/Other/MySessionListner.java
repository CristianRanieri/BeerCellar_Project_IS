package Utils.Other;

import GestioneCarrello.Service.GestioneCarrelloService;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import model.DAO.CarrelloDAO;
import model.entity.Account;
import model.entity.Carrello;
import model.entity.ContenutoCarrello;

import java.util.ArrayList;

@WebListener
public class MySessionListner implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Questo metodo viene chiamato quando una sessione viene creata
        Account account = new Account();
        Carrello carrello= new Carrello();
        carrello.setContenutoCarrello(new ArrayList<ContenutoCarrello>());

        account.setCarrello(carrello);
        account.setId(-1);

        se.getSession().setAttribute("account",account);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Questo metodo viene chiamato quando una sessione viene chiusa o invalidata
        GestioneCarrelloService gestioneCarrelloService= new GestioneCarrelloService();
        Account account= (Account) se.getSession().getAttribute("account");

        if(account.getId()!=-1 && !account.isGestore())
            gestioneCarrelloService.caricaCarrello(account.getCarrello(), account.getId());
    }
}
