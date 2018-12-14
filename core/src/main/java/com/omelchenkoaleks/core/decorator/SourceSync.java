package com.omelchenkoaleks.core.decorator;

import com.omelchenkoaleks.core.dao.interfaces.SourceDAO;
import com.omelchenkoaleks.core.enums.OperationType;
import com.omelchenkoaleks.core.interfaces.Source;
import com.omelchenkoaleks.core.utils.TreeUtils;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SourceSync implements SourceDAO {

    private TreeUtils<Source> treeUtils = new TreeUtils();

    private List<Source> treeList = new ArrayList<>();
    private Map<OperationType, List<Source>> sourceMap = new EnumMap<>(OperationType.class);
    private Map<Long, Source> identityMap = new HashMap<>();

    private SourceDAO sourceDAO;

    public SourceSync(SourceDAO sourceDAO) {
        this.sourceDAO = sourceDAO;
        init();
    }

    public void init() {
        List<Source> sourceList = sourceDAO.getAll();

        for (Source s : sourceList) {
            identityMap.put(s.getId(), s);
            treeUtils.addToTree(s.getParentId(), s, treeList);
        }

        // важно - сначала построить деревья, уже потом разделять по типам операции
        fillSourceMap(treeList); // разделяем по типам операции
    }

    private void fillSourceMap(List<Source> list) {
        for (OperationType type : OperationType.values()) {
            sourceMap.put(type, list.stream()
                    .filter(s -> s
                    .getOperationType() == type)
                    .collect(Collectors.toList()));
        }
    }


    @Override
    public List<Source> getAll() {// возвращает объекты уже в виде деревьев
        return treeList;
    }


    @Override
    public Source get(long id) {// не делаем запрос в БД, а получаем ранее загруженный объект из коллекции
        return identityMap.get(id);
    }


    @Override
    public boolean update(Source source) {
        if (sourceDAO.update(source)){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Source source) {
        // TODO добавить нужные Exceptions
        if (sourceDAO.delete(source)) {
            identityMap.remove(source.getId());
            if (source.getParent() != null) {
                source.getParent().remove(source);
            } else {
                sourceMap.get(source.getOperationType()).remove(source);
                treeList.remove(source);
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Source> getList(OperationType operationType) {
        return sourceMap.get(operationType);
    }

    // если понадобится напрямую получить объекты из БД - можно использовать sourceDAO
    public SourceDAO getSourceDAO() {
        return sourceDAO;
    }
}
