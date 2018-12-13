package com.omelchenkoaleks.core.implementations;

import com.omelchenkoaleks.core.abstracts.AbstractTreeNode;
import com.omelchenkoaleks.core.interfaces.Source;
import com.omelchenkoaleks.core.interfaces.TreeNode;
import com.omelchenkoaleks.core.enums.OperationType;

import java.util.List;

public class DefaultSource extends AbstractTreeNode implements Source {

    private OperationType operationType;

    public DefaultSource() {
    }

    public DefaultSource(String name) {
        super(name);
    }

    public DefaultSource(List<TreeNode> childs) {
        super(childs);
    }

    public DefaultSource(long id, String name) {
        super(id, name);
    }

    public DefaultSource(long id, TreeNode patent, String name, List<TreeNode> childs) {
        super(id, patent, name, childs);
    }

    public DefaultSource(long id, String name, OperationType operationType) {
        super(id, name);
        this.operationType = operationType;
    }


    @Override
    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }


    @Override
    public void add(TreeNode child) {

        // TODO: применить паттерн
        // для дочернего элемента устанавливаем текущий тип
        if (child instanceof DefaultSource) {
            ((DefaultSource) child).setOperationType(operationType);
        }

        super.add(child);
    }
}
