package model.DAO;

import Utils.Connection.ConPool;
import model.entity.Account;

import java.sql.*;

public class AccountDAO {
    private static final Connection con;

    static {
        try {
            con = ConPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account getUtenteByEmailPass(Account a){
        Account account=null;
        try {
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Account WHERE email=? AND pass=?");
            ps.setString(1,a.getEmail());
            ps.setString(2,a.getPassword());

            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                account=new Account();
                account.setId(rs.getInt("IdUtente"));
                account.setEmail(rs.getString("email"));
                account.setNome(rs.getString("nome"));
                account.setPassword(rs.getString("pass"));
                account.setGestore(rs.getBoolean("gestore"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    public void creaAccount(Account a){
        try {
            PreparedStatement ps= con.prepareStatement("INSERT INTO Account(nome,email,pass,gestore) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,a.getNome());
            ps.setString(2,a.getEmail());
            ps.setString(3,a.getPassword());
            ps.setBoolean(4,a.isGestore());
            ps.executeUpdate();

            ResultSet rs=ps.getGeneratedKeys();
            rs.next();
            a.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account getAccountByEmail(String email){
        Account a=null;
        try {
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Account WHERE email=?");
            ps.setString(1,email);

            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                a=new Account();
                a.setId(rs.getInt("IdUtente"));
                a.setEmail(rs.getString("email"));
                a.setNome(rs.getString("nome"));
                a.setPassword(rs.getString("pass"));
                a.setGestore(rs.getBoolean("gestore"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return a;
    }

    public void updateNomePassword(Account a){
        try {
            PreparedStatement ps= con.prepareStatement("UPDATE Utente SET nome=? , pass=? WHERE idUtente=?");
            ps.setString(1,a.getNome());
            ps.setString(2,a.getPassword());
            ps.setInt(3,a.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
