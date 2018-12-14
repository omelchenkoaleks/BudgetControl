package com.omelchenkoaleks.core.decorator;

import com.omelchenkoaleks.core.dao.interfaces.StorageDAO;
import com.omelchenkoaleks.core.exceptions.CurrencyException;
import com.omelchenkoaleks.core.interfaces.Storage;
import com.omelchenkoaleks.core.utils.TreeUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    синхронизирует все действия между объектами коллекции и базой данных (Декоратор)
 */
public class StorageSync implements StorageDAO {

    private TreeUtils<Storage> treeUtils = new TreeUtils();

    private List<Storage> treeList = new ArrayList<>();
    private Map<Long, Storage> identityMap = new HashMap<>();

    private StorageDAO storageDAO;

    public StorageSync(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
        init();
    }

    private void init() {
        List<Storage> storageList = storageDAO.getAll();

        for (Storage s : storageList) {
            identityMap.put(s.getId(), s);
            treeUtils.addToTree(s.getParentId(), s, treeList);
        }
    }

    @Override
    public List<Storage> getAll() {// возвращает объекты уже в виде деревьев
        return treeList;
    }

    @Override
    public Storage get(long id) {// не делаем запрос в БД, а получаем ранее загруженный объект из коллекции
        return identityMap.get(id);
    }

    @Override
    public boolean update(Storage storage) {
        if (storageDAO.delete(storage)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Storage storage) {
        if (storageDAO.delete(storage)) {
            identityMap.remove(storage.getId());
            if (storage.getParent()!=null) {
                storage.getParent().remove(storage);
            }else{
                treeList.remove(storage);
            }
            return true;
        }
        return false;
    }

    public StorageDAO getStorageDAO() {
        return storageDAO;
    }

    @Override
    public boolean updateAmount(Storage storage,Currency currency, BigDecimal amount) {
        if (storageDAO.updateAmount(storage, currency, amount)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addCurrency(Storage storage, Currency currency) throws CurrencyException {
        if (storageDAO.addCurrency(storage, currency)){
            storage.addCurrency(currency);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCurrency(Storage storage, Currency currency) throws CurrencyException {
        if (storageDAO.deleteCurrency(storage, currency)) {
            storage.deleteCurrency(currency);
            return true;
        }
        return false;
    }
}
