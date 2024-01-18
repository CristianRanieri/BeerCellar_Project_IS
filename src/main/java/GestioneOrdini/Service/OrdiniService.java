package GestioneOrdini.Service;

import Utils.Other.Pagamento;
import model.DAO.OrdineDAO;
import model.entity.Account;
import model.entity.AcquistoProdotto;
import model.entity.ContenutoCarrello;
import model.entity.Ordine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;

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

        return ordini;
    }
}
