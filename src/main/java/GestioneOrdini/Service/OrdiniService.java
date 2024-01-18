package GestioneOrdini.Service;

import Utils.Other.Pagamento;
import model.DAO.OrdineDAO;
import model.entity.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;

public class OrdiniService {

    public void effettuaOrdine(Ordine ordine,Pagamento pagamento) throws Exception {
        double prezzoTotale = 0;
        ArrayList<AcquistoProdotto> prodotti = new ArrayList<>();

        for (ContenutoCarrello cc : ordine.getAccount().getCarrello().getContenutoCarrello()) {
            AcquistoProdotto acquistoProdotto = new AcquistoProdotto();
            acquistoProdotto.setProdotto(cc.getProdotto());
            acquistoProdotto.setQuantita(cc.getQuantita());
            acquistoProdotto.setPrezzoAcquisto(cc.getProdotto().getPrezzo());
            prodotti.add(acquistoProdotto);
            prezzoTotale += cc.getProdotto().getPrezzo() * cc.getQuantita();
        }
        ordine.setPrezzoTotale(BigDecimal.valueOf(prezzoTotale).setScale(2, RoundingMode.HALF_UP).doubleValue());
        pagamento.setValorePagamento(ordine.getPrezzoTotale());
        ordine.setProdotti(prodotti);

        PagamentoService pagamentoService = new PagamentoAdapter();
        boolean b = pagamentoService.pagamento(pagamento);

        if(b) {
            OrdineDAO ordineDAO = new OrdineDAO();
            ordineDAO.creaOrdine(ordine);

            Carrello carrello = new Carrello();
            carrello.setContenutoCarrello(new ArrayList<ContenutoCarrello>());
            ordine.getAccount().setCarrello(carrello);
        }else {
            throw new Exception();
        }
    }

    //puo essere utilizzato solo dai gestori
    public ArrayList<Ordine> ricercaOrdini(String tipoID, int numero, int offset){
        ArrayList<Ordine> ordini = new ArrayList<>();
        OrdineDAO ordineDAO = new OrdineDAO();

        if(tipoID.equals("Utente"))
            ordini= ordineDAO.getOrdiniUtente(numero,offset);
        else {
            Ordine ordine = ordineDAO.getOrdine(numero);
            if(ordine !=null)
                ordini.add(ordine);
        }
        return ordini;
    }

    public ArrayList<Ordine> visualizzaOrdini(Account account,int offset){
        ArrayList<Ordine> ordini = null;
        OrdineDAO ordineDAO = new OrdineDAO();

        if(account.isGestore())
            ordini= ordineDAO.getOrdiniAll(offset);
        else
            ordini= ordineDAO.getOrdiniUtente(account.getId(),offset);
        ordini.sort(new Comparator<Ordine>() {
            @Override
            public int compare(Ordine o1, Ordine o2) {
                if(o1.getId() < o2.getId())
                    return 1;
                else
                    return -1;
            }
        });
        return ordini;
    }
}
