package com.wpj.sorm.core;

import com.wpj.sorm.bean.ColumnInfo;
import com.wpj.sorm.bean.TableInfo;
import com.wpj.sorm.utils.JavaFileUtils;
import com.wpj.sorm.utils.StringUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangpj
 * @create 2018-07-22 11:36
 * 负责获取管理数据库所有表结构和类结构的关系，并根据表结构生产类结构
 */
public class TableContext {
    /**
     * 表名为key，表信息对象为value
     */
    public static  Map<String,TableInfo>  tables = new HashMap<String,TableInfo>();

    /**
     * 将po的class对象和表信息对象关联起来，便于重用！
     */
    public static Map<Class,TableInfo> poClassTableMap = new HashMap<Class,TableInfo>();

    private TableContext(){}

    static {
        try {
            //初始化获得表的信息
            Connection con = DBManager.getConnection();
            DatabaseMetaData dbmd = con.getMetaData();

            ResultSet tableRet = dbmd.getTables(null, "%","%",new String[]{"TABLE"});

            while(tableRet.next()){
                String tableName = (String) tableRet.getObject("TABLE_NAME");

                TableInfo ti = new TableInfo(tableName,new HashMap<String, ColumnInfo>(),new ColumnInfo());
                tables.put(tableName, ti);

                ResultSet set = dbmd.getColumns(null, "%", tableName, "%");  //查询表中的所有字段
                while(set.next()){
                    ColumnInfo ci = new ColumnInfo(set.getString("COLUMN_NAME")
                            set.getString("TYPE_NAME"), 0);
                    ti.getColumnInfoMap().put(set.getString("COLUMN_NAME"), ci);
                }

                ResultSet set2 = dbmd.getPrimaryKeys(null, "%", tableName);  //查询t_user表中的主键
                while(set2.next()){
                    ColumnInfo ci2 = (ColumnInfo) ti.getColumnInfoMap().get(set2.getObject("COLUMN_NAME"));
                    ci2.setKeyType(1);  //设置为主键类型
                    ti.getPrimaryKey().add(ci2);
                }

                if(ti.getPrimaryKey().size()>0){  //取唯一主键。。方便使用。如果是联合主键。则为空！
                    ti.setOnlyPriKey(ti.getPriKeys().get(0));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //更新类结构
        updateJavaPOFile();

        //加载po包下面所有的类，便于重用，提高效率！
        loadPOTables();
    }

    /**
     * 根据表结构，更新配置的po包下面的java类
     * 实现了从表结构转化到类结构
     */
    public static void updateJavaPOFile(){
        Map<String,TableInfo> map = TableContext.tables;
        for(TableInfo t:map.values()){
            JavaFileUtils.createJavaPOFile(t,new MySqlTypeConvertor());
        }
    }

    /**
     * 加载po包下面的类
     */
    public static void loadPOTables(){

        for(TableInfo tableInfo:tables.values()){
            try {
                Class c = Class.forName(DBManager.getConf().getPoPackage()
                        +"."+StringUtils.firstChartoUpper(tableInfo.getTableName()));
                poClassTableMap.put(c, tableInfo);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
