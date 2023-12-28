package model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Ordine {
    public Ordine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(Double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public ArrayList<AcquistoProdotto> getProdotti() {
        return prodotti;
    }

    public void setAcquistoIndex(int index,AcquistoProdotto acquistoProdotto){
        prodotti.set(index, acquistoProdotto);
    }

    public void setProdotti(ArrayList<AcquistoProdotto> prodotti) {
        this.prodotti = prodotti;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    private Account account;
    private int id;
    private Date data;
    private Double prezzoTotale;
    private String indirizzo;
    private String citta;
    private String CAP;
    private String provincia;
    private ArrayList<AcquistoProdotto> prodotti;
}
