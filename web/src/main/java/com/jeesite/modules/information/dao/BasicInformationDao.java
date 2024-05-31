package com.jeesite.modules.information.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.information.entity.BasicInformation;

/**
 * 基本信息DAO接口
 * @author laz
 * @version 2024-05-07
 */
@MyBatisDao
public interface BasicInformationDao extends CrudDao<BasicInformation> {
	
}