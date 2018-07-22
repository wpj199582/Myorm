package com.wpj.sorm.core;

import java.util.List;

/**
 * @author wangpj
 * @create 2018-07-18 23:09
 * 负责查询（对外提供服务的核心接口）
 */
public interface Query {
    /**
     * 直接执行sql语句
     * @param sql sql语句
     * @param params 参数
     * @return 返回执行sql语句后受影响的行数
     */
    int executeDML(String sql, Object[] params);

    /**
     * 将一个对象存储到数据库(自动生成sql语句)
     * @param obj
     */
    void insert(Object obj);

    /**
     * 删除clazz对应的表中的记录(指定主键值ID)
     * @param clazz
     * @param id 主键
     * @return
     */
    void delete(Class clazz, int id);

    /**
     * 删除对象在数据库中的记录(类对应到表，对象主键值对应到记录)
     * @param obj
     */

    /**
     * 更新对象对应记录
     * @param obj
     * @param params 需要更新的属性列表
     * @return
     */
    int update(Object obj, String[] params);

    /**
     * 查询返回多行记录，并将每行记录封装到clazz指定的类中
     * @param sql 查询语句
     * @param clazz 封装数据的javabean
     * @param params 参数
     * @return  查询结果列表
     */
    List queryRows(String sql, Class clazz, Object[] params);

    /**
     * 查询返回单行记录，并将每行记录封装到clazz指定的类中
     * @param sql 查询语句
     * @param clazz
     * @param params
     * @return 查询到的单个结果
     */
    Object querySingleRows(String sql, Class clazz, Object[] params);


    /**
     * 查询返回单行单列，并将每行记录封装到clazz指定的类中
     * @param sql 查询语句
     * @param params 参数
     * @return  查询到的单行单列
     */
    Object querySingleValue(String sql, Object[] params);

    /**
     * 查询返回一个数字，并将每行记录封装到clazz指定的类中
     * @param sql 查询语句
     * @param params 参数
     * @return  查询到的数字
     */
    Number queryNumber(String sql, Object[] params);

}
