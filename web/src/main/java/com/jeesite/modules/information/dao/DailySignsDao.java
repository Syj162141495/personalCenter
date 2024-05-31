package com.jeesite.modules.information.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.information.entity.DailySigns;

/**
 * 日常体征DAO接口
 * @author laz
 * @version 2024-05-09
 */
@MyBatisDao
public interface DailySignsDao extends CrudDao<DailySigns> {
	
}