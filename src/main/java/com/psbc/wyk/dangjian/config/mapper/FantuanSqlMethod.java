package com.psbc.wyk.dangjian.config.mapper;

public enum FantuanSqlMethod  {

	LOCK_LIST("lockList", "查询满足条件所有数据", "<script>SELECT %s FROM %s %s for update</script>"),
	LOCK_PAGE("lockPage", "查询满足条件所有数据（并翻页）", "<script>SELECT %s FROM %s %s for update</script>"),
	LOCK_BY_ID("lockById", "根据ID 查询一条数据", "SELECT %s FROM %s WHERE %s=#{%s} for update"),
	LOCK_BATCH_BY_IDS("lockBatchIds", "根据ID集合，批量查询数据", "<script>SELECT %s FROM %s WHERE %s IN (%s) for update</script>")
	;
	
	private final String method;
    private final String desc;
    private final String sql;

    FantuanSqlMethod(final String method, final String desc, final String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

    public String getMethod() {
        return this.method;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getSql() {
        return this.sql;
    }
}
