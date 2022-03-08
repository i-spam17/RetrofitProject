package hw5.db.dao;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import java.util.List;

public interface JoinMapper {
    @SelectProvider(type= SqlProviderAdapter.class, method="select")
    @ResultMap("SimpleJoinResult")
    List<JoinMapper> selectMany(SelectStatementProvider selectStatement);
}
