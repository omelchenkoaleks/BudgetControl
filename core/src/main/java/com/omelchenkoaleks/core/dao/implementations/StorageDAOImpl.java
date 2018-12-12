package com.omelchenkoaleks.core.dao.implementations;

import com.omelchenkoaleks.core.dao.interfaces.StorageDAO;
import com.omelchenkoaleks.core.database.SQLiteConnection;
import com.omelchenkoaleks.core.implementations.DefaultStorage;
import com.omelchenkoaleks.core.interfaces.Storage;
import com.omelchenkoaleks.core.utils.TreeConstructor;

import org.sqlite.SQLiteException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StorageDAOImpl implements StorageDAO {

    private static final String CURRENCY_TABLE = "currency_amount";
    private static final String STORAGE_TABLE = "storage";

    private TreeConstructor<Storage> treeConstructor = new TreeConstructor();

    private List<Storage> storageList = new ArrayList<>();

    @Override
    public boolean addCurrency(Storage storage, Currency currency) {

        // для автоматического закрытия ресурсов
        try (PreparedStatement stmt = com.omelchenkoaleks.core.database.SQLiteConnection.getConnection()
                .prepareStatement("insert into " + CURRENCY_TABLE + "(currency_code, storage_id, amount) values(?,?,?");) {

            stmt.setString(1, currency.getCurrencyCode());
            stmt.setLong(2, storage.getId());
            stmt.setBigDecimal(3, BigDecimal.ZERO);

            if (stmt.executeUpdate() == 1) { // если была обновлена 1 запись
                return true;
            }


        } catch (SQLiteException e) {
            Logger.getLogger(StorageDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteCurrency(Storage storage, Currency currency) {

        try (PreparedStatement stmt = com.omelchenkoaleks.core.database
                .SQLiteConnection.getConnection()
                .prepareStatement("delete from " + CURRENCY_TABLE + " where storage_id=? and currency_code=?");) {

            stmt.setLong(1, storage.getId());
            stmt.setString(2, currency.getCurrencyCode());

            if (stmt.executeUpdate() == 1) { // если была обновлена 1 запись
                return true;
            }

        } catch (SQLiteException e) {
            Logger.getLogger(StorageDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateAmount(Storage storage, BigDecimal amount) {
        return false;
    }

    public StorageDAOImpl() {
        super();
    }

    @Override
    public List<Storage> getAll() {

        storageList.clear();

        try (Statement stmt = SQLiteConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("select * from " + STORAGE_TABLE);) {

            while (rs.next()) {
                DefaultStorage storage = new DefaultStorage();
                storage.setId(rs.getLong("id"));
                storage.setName(rs.getString("name"));

                long parentId = rs.getLong("parent_id");

                treeConstructor.addToTree(parentId, storage, storageList);
            }

            return storageList;


        } catch (SQLiteException e) {
            Logger.getLogger(StorageDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Storage get(long id) {
        return null;
    }

    @Override
    public boolean update(Storage storage) {

        try (PreparedStatement stmt = SQLiteConnection.getConnection()
                .prepareStatement("update " + STORAGE_TABLE + " set name=? where id=?");) {

                stmt.setString(1, storage.getName());
                stmt.setLong(2, storage.getId());

                if (stmt.executeUpdate() == 1) {
                    return true;
                }

        } catch (SQLiteException e) {
            Logger.getLogger(StorageDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Storage storage) {

        try (PreparedStatement stmt = SQLiteConnection.getConnection()
                .prepareStatement("delete from " + STORAGE_TABLE + " where id=?");) {

            stmt.setLong(1, storage.getId());

            if (stmt.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLiteException e) {
            Logger.getLogger(StorageDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
