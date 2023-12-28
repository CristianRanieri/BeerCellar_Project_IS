package Utils.Other;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import model.DAO.CarrelloDAO;
import model.entity.Carrello;
import model.entity.ContenutoCarrello;

import java.util.ArrayList;

@WebListener
public class MySessionListner implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Questo metodo viene chiamato quando una sessione viene creata
        Carrello carrello= new Carrello();
        //-1 indicata che non è un utente loggato
        carrello.setId(-1);
        ArrayList<ContenutoCarrello> contenutoCarrello= new ArrayList<>();
        carrello.setContenutoCarrello(contenutoCarrello);

        se.getSession().setAttribute("carrello",carrello);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Questo metodo viene chiamato quando una sessione viene chiusa o invalidata
        CarrelloDAO carrelloDAO= new CarrelloDAO();
        Carrello carrello= (Carrello)se.getSession().getAttribute("carrello");

        if(carrello.getId()!=-1)
            carrelloDAO.caricaCarrello(carrello.getId(),carrello);
    }
}
