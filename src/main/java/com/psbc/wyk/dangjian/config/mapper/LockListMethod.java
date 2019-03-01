package com.psbc.wyk.dangjian.config.mapper;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author wyk on 2019/02/27
 */
public class LockListMethod extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo table) {

        String sql = String.format(FantuanSqlMethod.LOCK_LIST.getSql(), sqlSelectColumns(table, true), table.getTableName(),
                sqlWhereEntityWrapper(Boolean.FALSE, table));
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addSelectMappedStatement(mapperClass, FantuanSqlMethod.LOCK_LIST.getMethod(), sqlSource, modelClass, table);
    }
}
