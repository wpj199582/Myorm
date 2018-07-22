package com.wpj.sorm.core;

public interface TypeConvertor {
    /**
     * 数据库数据类型--》java类型
     * @param colType 数据库字段类型
     * @return  java数据类型
     */
    public String datebaseToJavaType(String colType);

    /**
     * java类型--》数据库字段
     * @param javaType
     * @return
     */
    public String JavaTypeTodatabase(String javaType);
}
