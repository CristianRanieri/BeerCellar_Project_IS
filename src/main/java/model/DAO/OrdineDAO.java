package model.DAO;

import Utils.Connection.ConPool;

import model.entity.AcquistoProdotto;
import model.entity.Ordine;
import model.entity.Prodotto;

import java.sql.*;
import java.util.ArrayList;

public class OrdineDAO {
    private static final Connection con;

    static {
        try {
            con = ConPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //restituisce tutti gli ordini (Per i gestori)
    public ArrayList<Ordine> getOrdiniAll(int offset){
        ArrayList<Ordine> ordini = new ArrayList<>();
        try{
            String qeuary="SELECT * FROM AcquistoProdotto WHERE IdOrdine=?";

            PreparedStatement ps= con.prepareStatement("SELECT * FROM Ordine ORDER BY IdOrdine LIMIT 10 OFFSET ?");
            ps.setInt(1, offset);
            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                ps= con.prepareStatement(qeuary);
                ps.setInt(1,rs.getInt("IdOrdine"));

                Ordine ordine= new Ordine();
                ordine.setId(rs.getInt("IdOrdine"));
                ordine.setData(rs.getDate("DataOrdine"));
                ordine.setIndirizzo(rs.getString("Indirizzo"));
                ordine.setCitta(rs.getString("citta"));
                ordine.setProvincia(rs.getString("provincia"));
                ordine.setPrezzoTotale(rs.getDouble("prezzoTotale"));
                ordine.setCAP(rs.getString("CAP"));
                ordini.add(ordine);

                ResultSet rs2=ps.executeQuery();
                ArrayList<AcquistoProdotto> acquisti= new ArrayList<>();
                while (rs2.next()){
                    ProdottoDAO prodottoDAO= new ProdottoDAO();
                    Prodotto prodotto= prodottoDAO.getProdottoById(rs2.getInt("IdProdotto"));

                    AcquistoProdotto acquisto = new AcquistoProdotto();
                    acquisto.setPrezzoAcquisto(rs2.getDouble("PrezzoAcquisto"));
                    acquisto.setQuantita(rs2.getInt("Quantita"));
                    acquisto.setProdotto(prodotto);
                    acquisti.add(acquisto);
                }
                ordine.setProdotti(acquisti);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordini;
    }


    //restituisce tutti gli ordini di un utente
    public ArrayList<Ordine> getOrdiniUtente(int idUtente, int offset){
        ArrayList<Ordine> ordini = new ArrayList<>();
        try{
            String qeuary="SELECT * FROM AcquistoProdotto WHERE IdOrdine=?";

            PreparedStatement ps= con.prepareStatement("SELECT * FROM Ordine WHERE IdUtente=? LIMIT 10 OFFSET ?");
            ps.setInt(1, idUtente);
            ps.setInt(2, offset);
            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                ps= con.prepareStatement(qeuary);
                ps.setInt(1,rs.getInt("IdOrdine"));

                Ordine ordine= new Ordine();
                ordine.setId(rs.getInt("IdOrdine"));
                ordine.setData(rs.getDate("DataOrdine"));
                ordine.setIndirizzo(rs.getString("Indirizzo"));
                ordine.setCitta(rs.getString("citta"));
                ordine.setProvincia(rs.getString("provincia"));
                ordine.setPrezzoTotale(rs.getDouble("prezzoTotale"));
                ordine.setCAP(rs.getString("CAP"));
                ordini.add(ordine);

                ResultSet rs2=ps.executeQuery();
                ArrayList<AcquistoProdotto> acquisti= new ArrayList<>();
                while (rs2.next()){
                    ProdottoDAO prodottoDAO= new ProdottoDAO();
                    Prodotto prodotto= prodottoDAO.getProdottoById(rs2.getInt("IdProdotto"));

                    AcquistoProdotto acquisto = new AcquistoProdotto();
                    acquisto.setPrezzoAcquisto(rs2.getDouble("PrezzoAcquisto"));
                    acquisto.setQuantita(rs2.getInt("Quantita"));
                    acquisto.setProdotto(prodotto);
                    acquisti.add(acquisto);
                }
                ordine.setProdotti(acquisti);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordini;
    }

    public Ordine getOrdine(int idOrdine){
        Ordine ordine = new Ordine();
        try{
            String qeuary="SELECT * FROM AcquistoProdotto WHERE IdOrdine=?";

            PreparedStatement ps= con.prepareStatement("SELECT * FROM Ordine WHERE IdOrdine=?");
            ps.setInt(1, idOrdine);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                ps= con.prepareStatement(qeuary);
                ps.setInt(1,rs.getInt("IdOrdine"));

                ordine.setId(rs.getInt("IdOrdine"));
                ordine.setData(rs.getDate("DataOrdine"));
                ordine.setIndirizzo(rs.getString("Indirizzo"));
                ordine.setCitta(rs.getString("citta"));
                ordine.setProvincia(rs.getString("provincia"));
                ordine.setPrezzoTotale(rs.getDouble("prezzoTotale"));
                ordine.setCAP(rs.getString("CAP"));

                ResultSet rs2=ps.executeQuery();
                ArrayList<AcquistoProdotto> acquisti= new ArrayList<>();
                while (rs2.next()){
                    ProdottoDAO prodottoDAO= new ProdottoDAO();
                    Prodotto prodotto= prodottoDAO.getProdottoById(rs2.getInt("IdProdotto"));

                    AcquistoProdotto acquisto = new AcquistoProdotto();
                    acquisto.setPrezzoAcquisto(rs2.getDouble("PrezzoAcquisto"));
                    acquisto.setQuantita(rs2.getInt("Quantita"));
                    acquisto.setProdotto(prodotto);
                    acquisti.add(acquisto);
                }
                ordine.setProdotti(acquisti);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordine;
    }


    //permette di creare un ordine
    public boolean creaOrdine(Ordine ordine){
        int b=0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Ordine (IdUtente,DataOrdine,PrezzoTotale,Indirizzo,Citta,CAP,Provincia) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,ordine.getAccount().getId());
            ps.setDate(2, (Date) ordine.getData());
            ps.setDouble(3, ordine.getPrezzoTotale());
            ps.setString(4,ordine.getIndirizzo());
            ps.setString(5,ordine.getCitta());
            ps.setString(6,ordine.getCAP());
            ps.setString(7,ordine.getProvincia());

            ps.executeUpdate();

            ResultSet rs=ps.getGeneratedKeys();
            rs.next();
            int idOrdine=rs.getInt(1);

            for(AcquistoProdotto acquisto : ordine.getProdotti()){
                ps = con.prepareStatement("INSERT INTO AcquistoProdotto (IdOrdine,IdProdotto,Quantita,PrezzoAcquisto) VALUES (?,?,?,?)");
                ps.setInt(1,idOrdine);
                ps.setInt(2,acquisto.getProdotto().getId());
                ps.setInt(3, acquisto.getQuantita());
                ps.setDouble(4,acquisto.getPrezzoAcquisto());

                b=ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return b==1;
    }

}
