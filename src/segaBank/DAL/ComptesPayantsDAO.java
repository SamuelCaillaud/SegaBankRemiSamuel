package segaBank.DAL;
import segaBank.bo.Compte;
import segaBank.bo.ComptePayant;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComptesPayantsDAO implements IDAO<Long, ComptePayant> {
    private static final String INSERT_QUERY = "INSERT INTO comptespayants (solde) VALUES(?)";
    private static final String UPDATE_QUERY = "UPDATE comptespayants SET solde = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM comptespayants WHERE id = ?";
    private static final String SELECT_QUERY = "SELECT FROM comptespayants WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM comptespayants";

    @Override
    public void create(ComptePayant object) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if(connection != null) {
            try(PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, object.getSolde());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        object.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void delete(ComptePayant object) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try(PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
                ps.setDouble(1, object.getSolde());
                ps.setInt(2, object.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void update(ComptePayant object) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try(PreparedStatement ps = connection.prepareStatement(DELETE_QUERY)) {
                ps.setInt(1, object.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public ComptePayant findById(Long aLong) throws SQLException, IOException, ClassNotFoundException {
        ComptePayant comptePayant = null;
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try(PreparedStatement ps = connection.prepareStatement(SELECT_QUERY)) {
                ps.setLong(1, aLong);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        comptePayant = new ComptePayant();
                        comptePayant.setId(rs.getInt("id"));
                        comptePayant.setSolde(rs.getDouble("solde"));
                    }
                }
            }
        }
        return comptePayant;
    }

    @Override
    public List<ComptePayant> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<ComptePayant> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try(PreparedStatement ps = connection.prepareStatement(SELECT_ALL)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        ComptePayant contact = new ComptePayant();
                        contact.setId(rs.getInt("id"));
                        contact.setSolde(rs.getDouble("solde"));
                        list.add(contact);
                    }
                }
            }
        }
        return list;
    }
}
