package model.DAO;

import Utils.Connection.ConPool;
import model.entity.Carrello;
import model.entity.ContenutoCarrello;
import model.entity.Prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarrelloDAO {

    private static final Connection con;

    static {
        try {
            con = ConPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //recupera dal db il carrello dell'utente
    public Carrello getCarrelloByIdUtente(int id){
        Carrello carrello= new Carrello();
        ProdottoDAO prodottoDAO= new ProdottoDAO();
        ArrayList<ContenutoCarrello> contenutoCarrellos= new ArrayList<>();
        try{
            PreparedStatement ps= con.prepareStatement("SELECT * FROM ContenutoCarrello WHERE IdCarrello=?");
            ps.setInt(1,id);

            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                ContenutoCarrello contenutoCarrello= new ContenutoCarrello();
                contenutoCarrello.setProdotto(prodottoDAO.getProdottoById(rs.getInt("IdProdotto")));
                contenutoCarrello.setQuantita(rs.getInt("Quantita"));
                contenutoCarrellos.add(contenutoCarrello);
            }

            carrello.setContenutoCarrello(contenutoCarrellos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carrello;
    }

    //aggiorna il contenuto del carrello di un utente
    public void caricaCarrello(Carrello carrello, int id){
        try {
            //elimino tutto cio che si trova nel attuale carrello di quell'utente sul db
            PreparedStatement ps= con.prepareStatement("DELETE FROM ContenutoCarrello WHERE idCarrello=?");
            ps.setInt(1, id);

            ps.executeUpdate();

            //salvo tutto cio che si trova nel carrello aggiornato nel db
            for(ContenutoCarrello c: carrello.getContenutoCarrello()) {
                ps = con.prepareStatement("INSERT INTO ContenutoCarrello VALUES (?,?,?)");
                ps.setInt(2, c.getProdotto().getId());
                ps.setInt(3, c.getQuantita());

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void creaCarrello(int idUtente){
        PreparedStatement ps= null;
        try {
            ps = con.prepareStatement("INSERT INTO Carrello VALUES (?)");
            ps.setInt(1,idUtente);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
