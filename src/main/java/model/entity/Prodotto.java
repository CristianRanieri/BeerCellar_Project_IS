package model.entity;

public class Prodotto {
    public Prodotto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public String getFermentazione() {
        return fermentazione;
    }

    public void setFermentazione(String fermentazione) {
        this.fermentazione = fermentazione;
    }

    public String getStile() {
        return stile;
    }

    public void setStile(String stile) {
        this.stile = stile;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public Double getTassoAlcolico() {
        return tassoAlcolico;
    }

    public void setTassoAlcolico(Double tassoAlcolico) {
        this.tassoAlcolico = tassoAlcolico;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean isGlutine() {
        return glutine;
    }

    public void setGlutine(boolean glutine) {
        this.glutine = glutine;
    }

    public String getBirrificio() {
        return birrificio;
    }

    public void setBirrificio(String birrificio) {
        this.birrificio = birrificio;
    }

    public boolean isInCatalogo() {
        return inCatalogo;
    }

    public void setInCatalogo(boolean inCatalogo) {
        this.inCatalogo = inCatalogo;
    }

    private int id;
    private String nome;
    private String formato;
    private Double prezzo;
    private String fermentazione;
    private String stile;
    private String colore;
    private Double tassoAlcolico;
    private String descrizione;
    private boolean glutine;
    private String birrificio;
    private boolean inCatalogo;
}
