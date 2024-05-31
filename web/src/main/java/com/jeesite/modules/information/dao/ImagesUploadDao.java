package com.jeesite.modules.information.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.information.entity.ImagesUpload;

/**
 * 健康动态DAO接口
 * @author laz
 * @version 2024-05-09
 */
@MyBatisDao
public interface ImagesUploadDao extends CrudDao<ImagesUpload> {
	
}