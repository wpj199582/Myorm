package com.wpj.sorm.bean;

import java.util.Map;

/**
 * 封装一张表的信息
 */
public class TableInfo {
    /**
     * 表名
     */
    private String tableName;

    /**
     * 所有字段信息
     */
    private Map<String,ColumnInfo> columnInfoMap;

    /**
     * 唯一主键(目前只支持唯一主键)
     */
    private ColumnInfo primaryKey;

    public TableInfo(String tableName, Map<String, ColumnInfo> columnInfoMap, ColumnInfo primaryKey) {
        this.tableName = tableName;
        this.columnInfoMap = columnInfoMap;
        this.primaryKey = primaryKey;
    }

    public TableInfo() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, ColumnInfo> getColumnInfoMap() {
        return columnInfoMap;
    }

    public void setColumnInfoMap(Map<String, ColumnInfo> columnInfoMap) {
        this.columnInfoMap = columnInfoMap;
    }

    public ColumnInfo getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ColumnInfo primaryKey) {
        this.primaryKey = primaryKey;
    }
}
