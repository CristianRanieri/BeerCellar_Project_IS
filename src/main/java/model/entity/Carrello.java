package model.entity;

import java.util.ArrayList;

public class Carrello {
    public Carrello() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<ContenutoCarrello> getContenutoCarrello() {
        return contenutoCarrello;
    }

    public void setContenutoCarrello(ArrayList<ContenutoCarrello> contenutoCarrello) {
        this.contenutoCarrello = contenutoCarrello;
    }

    private int id;
    private ArrayList<ContenutoCarrello> contenutoCarrello;
}
