package com.jeesite.modules.information.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.information.entity.IllnessInfo;

/**
 * 疾病信息DAO接口
 * @author laz
 * @version 2024-05-07
 */
@MyBatisDao
public interface IllnessInfoDao extends CrudDao<IllnessInfo> {
	
}