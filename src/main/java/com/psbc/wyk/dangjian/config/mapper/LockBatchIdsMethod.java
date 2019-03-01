package com.psbc.wyk.dangjian.config.mapper;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author wyk on 2019/02/27
 */
public class LockBatchIdsMethod extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo table) {

        SqlSource sqlSource;
        FantuanSqlMethod sqlMethod = FantuanSqlMethod.LOCK_BATCH_BY_IDS;
        String ids = "\n<foreach item=\"item\" index=\"index\" collection=\"coll\" separator=\",\">" +
                "#{item}" +
                "\n</foreach>";
        sqlSource = languageDriver.createSqlSource(configuration, String.format(sqlMethod.getSql(),
                sqlSelectColumns(table, false), table.getTableName(), table.getKeyColumn(), ids),
                modelClass);

        return this.addSelectMappedStatement(mapperClass, sqlMethod.getMethod(), sqlSource, modelClass, table);
    }
}
