package com.psbc.wyk.dangjian.config.mapper;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

/**
 * @author wyk on 2019/02/27
 */
public class LockByIdMethod extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo table) {
        FantuanSqlMethod sqlMethod = FantuanSqlMethod.LOCK_BY_ID;
        SqlSource sqlSource;
        sqlSource = new RawSqlSource(configuration, String.format(sqlMethod.getSql(),
                sqlSelectColumns(table, false), table.getTableName(), table.getKeyColumn(), table.getKeyProperty()),
                Object.class);
        return this.addSelectMappedStatement(mapperClass, sqlMethod.getMethod(), sqlSource, modelClass, table);
    }


}
