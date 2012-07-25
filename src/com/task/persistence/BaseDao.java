package com.task.persistence;

import java.io.Reader;

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BaseDao {
	private static BaseDao instance = new BaseDao();
	private static Logger log = Logger.getLogger(BaseDao.class.getName());
	protected static SqlMapClient sqlMap;
	
	
	static {
		try {
			log.debug("Attempting to Initalise sql Map");
			String resource = "com/task/persistence/SqlMapConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
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
