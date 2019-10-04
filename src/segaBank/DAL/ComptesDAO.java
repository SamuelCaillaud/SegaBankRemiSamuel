package segaBank.DAL;
import segaBank.bo.Compte;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class ComptesDAO implements IDAO<Long, Compte> {
    private static final String INSERT_QUERY = "INSERT INTO contact (name, email) VALUES(?,?)";
    private static final String UPDATE_QUERY = "UPDATE contact SET name = ?, email= ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM contact WHERE name = ? AND email = ?";

    @Override
    public void create(Compte object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void delete(Compte object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void update(Compte object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public Compte findById(Long aLong) {
        return null;
    }

    @Override
    public List<Compte> findAll() {
        return null;
    }
}
