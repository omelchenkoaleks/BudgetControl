package com.omelchenkoaleks.core.abstracts;

import com.omelchenkoaleks.core.interfaces.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractTreeNode implements TreeNode {

    private long id;
    private TreeNode patent;
    private String name;
    private List<TreeNode> childs = new ArrayList<>();


    public AbstractTreeNode() {}

    public AbstractTreeNode(String name) {
        this.name = name;
    }

    public AbstractTreeNode(List<TreeNode> childs) {
        this.childs = childs;
    }

    public AbstractTreeNode(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public AbstractTreeNode(long id, TreeNode patent, String name, List<TreeNode> childs) {
        this.id = id;
        this.patent = patent;
        this.name = name;
        this.childs = childs;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void add(TreeNode child) {
        child.setParent(this);
        childs.add(child);
    }

    @Override
    public void setParent(TreeNode parent) {
        this.patent = parent;
    }

    @Override
    public TreeNode getParent() {
        return patent;
    }

    @Override
    public void remove(TreeNode child) {
        childs.remove(child);
    }

    @Override
    public List<TreeNode> getChild() {
        return childs;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public TreeNode getChild(long id) {

        for (TreeNode child: childs) {
            if (child.getId() == id) {
                return child;
            }
        }

        return null;
    }

    @Override
    public boolean hasChilds() {
        return !childs.isEmpty(); // если есть дочерние элементы - вернуть true
    }

    // если нам нужно будет представить этот объект в виде строки, он вернет нам имя этого узла
    @Override
    public String toString() {
        return name;
    }

    /**
     * Важное переопределение двух методов:
     * теперь, когда эти методы будут сравнивать объекты внутри коллекции для удвления
     * они будут брать уникальный идентификатор и определять (не по имени, потому что имя
     * может совпадать)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTreeNode that = (AbstractTreeNode) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
