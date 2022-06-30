package com.leeneko.util;

import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DbUtils {
	
	@Autowired
	private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public DbUtils(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		DbUtils.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	public static <R> List<R> selectList(String sql, Map<String, Object> paramMap, Class<R> clazz) throws Exception {
		List<R> list = namedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(clazz)); 
		return list;
	}
	
	public static <R> R select(String sql, Map<String, Object> paramMap, Class<R> clazz) throws Exception {
		R r = namedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(clazz)).get(0);
		return r;
	}
	
	public static <T> T selectOne(String sql) throws Exception {
		T result = namedParameterJdbcTemplate.query(sql, new ResultSetExtractor<T>() {
			@SuppressWarnings("unchecked")
			@Override
			public T extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				return (T) rs.getObject(1);
			}
		});
		return result;
	}

	public static String getSqlByPath(Class<?> clazz, String sqlFileName) {
		String sql = null;
		String sqlPath = clazz.getPackage().getName();
		sqlPath = StringUtils.replace(sqlPath, ".", "/") + "/" + sqlFileName;
		
		VelocityEngine engine = new VelocityEngine();
		engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		engine.init();
		
		VelocityContext context = new VelocityContext();
		StringWriter writer = new StringWriter();
		Template template = engine.getTemplate(sqlPath);
		template.merge(context, writer);
		
		sql = writer.toString().trim();
		
		engine.evaluate(context, writer, sql, sql);
		// log.info("\nSQL : \n{}\nat {}\n", sql, new Exception().getStackTrace()[0]);
		
		return sql;
	}
	
}
