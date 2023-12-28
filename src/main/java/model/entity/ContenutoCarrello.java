package model.entity;

public class ContenutoCarrello {
    public ContenutoCarrello() {
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    //da cambiare anche il CarrelloDAO
    private Prodotto prodotto;
    private int quantita;
}
