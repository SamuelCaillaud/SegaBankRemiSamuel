package segaBank.DAL;

import segaBank.bo.ComptePayant;
import segaBank.bo.CompteSimple;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComptesSimplesDAO implements IDAO<Long, CompteSimple> {

    private static final String INSERT_QUERY = "INSERT INTO comptessimples (solde, decouvert) VALUES(?, ?)";
    private static final String UPDATE_QUERY = "UPDATE comptessimples SET solde = ?, decouvert = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM comptessimples WHERE id = ?";
    private static final String SELECT_QUERY = "SELECT FROM comptessimples WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM comptessimples";

    @Override
    public void create(CompteSimple object) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if(connection != null) {
            try(PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, object.getSolde());
                ps.setDouble(1, object.getDecouvert());
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
    public void delete(CompteSimple object) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try(PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
                ps.setDouble(1, object.getSolde());
                ps.setDouble(1, object.getDecouvert());
                ps.setInt(2, object.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void update(CompteSimple object) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try(PreparedStatement ps = connection.prepareStatement(DELETE_QUERY)) {
                ps.setInt(1, object.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public CompteSimple findById(Long aLong) throws SQLException, IOException, ClassNotFoundException {
        CompteSimple compteSimple = null;
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try(PreparedStatement ps = connection.prepareStatement(SELECT_QUERY)) {
                ps.setLong(1, aLong);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        compteSimple = new CompteSimple();
                        compteSimple.setId(rs.getInt("id"));
                        compteSimple.setSolde(rs.getDouble("solde"));
                        compteSimple.setDecouvert(rs.getDouble("decouvert"));
                    }
                }
            }
        }
        return compteSimple;
    }

    @Override
    public List<CompteSimple> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<CompteSimple> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try(PreparedStatement ps = connection.prepareStatement(SELECT_ALL)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        CompteSimple compteSimple = new CompteSimple();
                        compteSimple.setId(rs.getInt("id"));
                        compteSimple.setSolde(rs.getDouble("solde"));
                        compteSimple.setDecouvert(rs.getDouble("decouvert"));
                        list.add(compteSimple);
                    }
                }
            }
        }
        return list;
    }
}
