package segaBank.DAL;

import segaBank.bo.CompteEpargne;
import segaBank.bo.CompteSimple;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComptesEpargneDAO implements IDAO<Long, CompteEpargne> {

    private static final String INSERT_QUERY = "INSERT INTO comptesepargnes (solde, tauxInteret) VALUES(?, ?)";
    private static final String UPDATE_QUERY = "UPDATE comptesepargnes SET solde = ?, tauxInteret = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM comptesepargnes WHERE id = ?";
    private static final String SELECT_QUERY = "SELECT FROM comptesepargnes WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM comptesepargnes";

    @Override
    public void create(CompteEpargne object) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if(connection != null) {
            try(PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, object.getSolde());
                ps.setDouble(1, object.getTauxInterets());
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
    public void delete(CompteEpargne object) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try(PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
                ps.setDouble(1, object.getSolde());
                ps.setDouble(1, object.getTauxInterets());
                ps.setInt(2, object.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void update(CompteEpargne object) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try(PreparedStatement ps = connection.prepareStatement(DELETE_QUERY)) {
                ps.setInt(1, object.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public CompteEpargne findById(Long aLong) throws SQLException, IOException, ClassNotFoundException {
        CompteEpargne compteEpargne = null;
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try(PreparedStatement ps = connection.prepareStatement(SELECT_QUERY)) {
                ps.setLong(1, aLong);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        compteEpargne = new CompteEpargne();
                        compteEpargne.setId(rs.getInt("id"));
                        compteEpargne.setSolde(rs.getDouble("solde"));
                        compteEpargne.setTauxInterets(rs.getDouble("tauxInteret"));
                    }
                }
            }
        }
        return compteEpargne;
    }

    @Override
    public List<CompteEpargne> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<CompteEpargne> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try(PreparedStatement ps = connection.prepareStatement(SELECT_ALL)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        CompteEpargne compteEpargne = new CompteEpargne();
                        compteEpargne.setId(rs.getInt("id"));
                        compteEpargne.setSolde(rs.getDouble("solde"));
                        compteEpargne.setTauxInterets(rs.getDouble("tauxInteret"));
                        list.add(compteEpargne);
                    }
                }
            }
        }
        return list;
    }
}
