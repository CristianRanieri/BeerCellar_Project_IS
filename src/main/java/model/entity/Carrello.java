package model.entity;

import java.util.ArrayList;

public class Carrello {
    public Carrello() {
    }

    public boolean isEmpty(){
        return contenutoCarrello.isEmpty();
    }

    public ArrayList<ContenutoCarrello> getContenutoCarrello() {
        return contenutoCarrello;
    }

    public void setContenutoCarrello(ArrayList<ContenutoCarrello> contenutoCarrello) {
        this.contenutoCarrello = contenutoCarrello;
    }

    private ArrayList<ContenutoCarrello> contenutoCarrello;
}
