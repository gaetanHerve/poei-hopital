package dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T, PK> {
	public List<T> findAll() throws ClassNotFoundException, SQLException;
	public T findById(PK id) throws ClassNotFoundException, SQLException;
	public void create(T obj) throws ClassNotFoundException, SQLException;
	public void update(T obj) throws ClassNotFoundException, SQLException;
	public void delete(T obj) throws ClassNotFoundException, SQLException;
}
