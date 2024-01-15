package model.DAO;

import Utils.Connection.ConPool;
import model.entity.Prodotto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {
    private static final Connection con;

    static {
        try {
            con = ConPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Prodotto creaIstanzaProdotto(ResultSet rs) throws SQLException {
        Prodotto p= new Prodotto();
        p.setId(rs.getInt("IdProdotto"));
        p.setNome(rs.getString("nome"));
        p.setFormato(rs.getString("formato"));
        p.setPrezzo(rs.getDouble("prezzo"));
        p.setFermentazione(rs.getString("Fermentazione"));
        p.setStile(rs.getString("Stile"));
        p.setColore(rs.getString("colore"));
        p.setTassoAlcolico(rs.getDouble("TassoAlcolico"));
        p.setDescrizione(rs.getString("descrizione"));
        p.setGlutine(rs.getBoolean("Glutine"));
        p.setBirrificio(rs.getString("Birrificio"));
        p.setInCatalogo(rs.getBoolean("inCatalogo"));
        return p;
    }

    //prendo 12 prodotti partendo da un valore di offset(parametro), i quali prodotti hanno un formato(parametro) e con le caratteristiche(parametro)
    public List<Prodotto> getProdottiPerFormatoCaratterisitche(String formato, boolean gestore ,ArrayList<String> caratterisitche, int offset){
        List<Prodotto> prodottos= new ArrayList<>();
        String query= "SELECT * FROM Prodotto p WHERE p.formato=?";

        if (gestore==false)
            query+= " AND p.InCatalogo= true";

        if(!caratterisitche.get(0).equals("tutti"))
            query+= " AND p.Stile=\""+ caratterisitche.get(0)+"\"";
        if(!caratterisitche.get(1).equals("tutti"))
            query+= " AND p.Colore<="+ caratterisitche.get(1);
        if(!caratterisitche.get(2).equals("tutti"))
            query+= " AND p.TassoAlcolico=\""+ caratterisitche.get(2)+"\"";

        query+= "LIMIT 12 OFFSET = ?";

        try{
            PreparedStatement ps= con.prepareStatement(query);
            ps.setString(1, formato);
            ps.setInt(2, offset);
            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                prodottos.add(this.creaIstanzaProdotto(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prodottos;
    }

    //ricerca prodotti per nome
    public ArrayList<Prodotto> getProdottiPerNome(List<String> nomi, boolean gestore, int offset){
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        String query="SELECT * FROM Prodotto p WHERE (p.Incatalogo=true";

        //se è un gestore vede anche i prodotti non in catalogo;
        if (gestore==true)
            query+= " OR p.InCatalogo= false)";
        else
            query+=")";

        for (String parola : nomi) {
            query += " AND INSTR(p.Nome,\"" + parola + "\")";
            query += " AND INSTR(p.Birrificio,\"" + parola + "\")";
        }
        query+= "LIMIT 12 OFFSET = ?";

        try{
            PreparedStatement ps= con.prepareStatement(query);

            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                prodotti.add(this.creaIstanzaProdotto(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prodotti;
    }

    //crea un prodotto e lo carica nel database
    public boolean creaProdotto(Prodotto prodotto){
        int b=0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Prodotto (Nome,Formato,Prezzo,Fermentazione,Stile,Colore,TassoAlcolico,Descrizione,Glutine,Birrificio,InCatalogo) VALUES (?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, prodotto.getNome());
            ps.setString(2, prodotto.getFormato());
            ps.setDouble(3, prodotto.getPrezzo());
            ps.setString(4, prodotto.getFermentazione());
            ps.setString(5, prodotto.getStile());
            ps.setString(6, prodotto.getColore());
            ps.setDouble(7, prodotto.getTassoAlcolico());
            ps.setString(8, prodotto.getDescrizione());
            ps.setBoolean(9, prodotto.isGlutine());
            ps.setString(10, prodotto.getBirrificio());
            ps.setBoolean(11, prodotto.isInCatalogo());

            b = ps.executeUpdate();

            ResultSet rs=ps.getGeneratedKeys();
            rs.next();
            prodotto.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return b == 1;
    }

    //modificare un prodotto
    public void modificaProdotto(Prodotto prodotto){
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE Prodotto SET Nome=?,Formato=?,Prezzo=?,Fermentazione=?,Stile=?,Colore=?,TassoAlcolico=?,Descrizione=?,Glutine=?,Birrificio=?,InCatalogo=? WHERE IdProdotto=?");
            ps.setString(1, prodotto.getNome());
            ps.setString(2, prodotto.getFormato());
            ps.setDouble(3, prodotto.getPrezzo());
            ps.setString(4, prodotto.getFermentazione());
            ps.setString(5, prodotto.getStile());
            ps.setString(6, prodotto.getColore());
            ps.setDouble(7, prodotto.getTassoAlcolico());
            ps.setString(8, prodotto.getDescrizione());
            ps.setBoolean(9, prodotto.isGlutine());
            ps.setString(10, prodotto.getBirrificio());
            ps.setBoolean(11, prodotto.isInCatalogo());
            ps.setInt(12, prodotto.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //ottieni il prodotto tramite il suo id
    public Prodotto getProdottoById(int id){
        Prodotto prodotto= null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Prodotto p WHERE p.idProdotto=?");
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            if(rs.next())
                prodotto = this.creaIstanzaProdotto(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prodotto;
    }

}
