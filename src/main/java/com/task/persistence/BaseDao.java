package com.task.persistence;

import java.io.Reader;

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import java.util.Properties;

public class BaseDao {
	private static BaseDao instance = new BaseDao();
	private static Logger log = Logger.getLogger(BaseDao.class.getName());
	protected static SqlMapClient sqlMap;
	
	
	static {
		try {

			Properties properties = new Properties();
			System.out.println(System.getenv("JAWSDB_URL"));
			properties.setProperty("jdbcURL", System.getenv("JAWSDB_URL"));
			properties.setProperty("username", System.getenv("JAWS_user"));
			properties.setProperty("password", System.getenv("JAWS_password"));

			log.debug("properties " + properties);

			log.debug("Attempting to Initalise sql Map");
			String resource = "SqlMapConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader, properties);
			log.debug("Initialized SqlMap");
		} catch(Exception e) {
			 log.error("Error intializing BaseIbatisDao ", e);
	            e.printStackTrace();
	            throw new RuntimeException("Error initializing BaseDao class. Cause: " + e);
		}
	}
	
	protected BaseDao() {
    }

    public static BaseDao getInstance() {
        return instance;
    }
}
