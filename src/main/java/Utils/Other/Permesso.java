package Utils.Other;

import model.entity.Account;

import java.util.ArrayList;
import java.util.Objects;

public class Permesso {
    public Permesso(String attore, String classe, String operazione) {
        this.attore = attore;
        this.classe = classe;
        this.operazione = operazione;
    }

    public String getAttore() {
        return attore;
    }

    public void setAttore(String attore) {
        this.attore = attore;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getOperazione() {
        return operazione;
    }

    public void setOperazione(String operazione) {
        this.operazione = operazione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permesso permesso = (Permesso) o;
        return Objects.equals(attore, permesso.attore) && Objects.equals(classe, permesso.classe) && Objects.equals(operazione, permesso.operazione);
    }

    public static boolean validazioneAccesso(ArrayList<Permesso> permessi, Account account, String classe, String operazione){
        String attore;
        if(account.getId() == -1)
            attore= "Ospite";
        else if(account.isGestore())
            attore = "Gestore";
        else
            attore = "Utente";

        Permesso permesso = new Permesso(attore,classe,operazione);
        return permessi.contains(permesso);
    }
    private String attore;
    private String classe;
    private String operazione;
}
