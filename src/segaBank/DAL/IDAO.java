package segaBank.DAL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<ID, T> {
    void create(T object) throws SQLException, IOException, ClassNotFoundException;
    void delete(T object) throws SQLException, IOException, ClassNotFoundException;
    void update(T object) throws SQLException, IOException, ClassNotFoundException;
    T findById(ID id) throws SQLException, IOException, ClassNotFoundException;
    List<T> findAll() throws SQLException, IOException, ClassNotFoundException;

}
