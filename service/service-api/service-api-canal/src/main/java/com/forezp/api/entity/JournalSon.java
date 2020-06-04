package com.forezp.api.entity;

import java.util.List;

public class JournalSon extends Journal {
    List<Tablevalue> beforeColumns;//修改前数据
    List<Tablevalue> afterColumns;//修改后数据
    List<Tablevalue> insertData;//新增数据
    List<Tablevalue> deleteData;//删除数据

    public List<Tablevalue> getBeforeColumns() {
        return beforeColumns;
    }

    public void setBeforeColumns(List<Tablevalue> beforeColumns) {
        this.beforeColumns = beforeColumns;
    }

    public List<Tablevalue> getAfterColumns() {
        return afterColumns;
    }

    public void setAfterColumns(List<Tablevalue> afterColumns) {
        this.afterColumns = afterColumns;
    }

    public List<Tablevalue> getInsertData() {
        return insertData;
    }

    public void setInsertData(List<Tablevalue> insertData) {
        this.insertData = insertData;
    }

    public List<Tablevalue> getDeleteData() {
        return deleteData;
    }

    public void setDeleteData(List<Tablevalue> deleteData) {
        this.deleteData = deleteData;
    }
}
