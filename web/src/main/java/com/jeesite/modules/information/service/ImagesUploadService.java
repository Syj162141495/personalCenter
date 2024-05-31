package com.jeesite.modules.information.service;

import java.util.List;

import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.information.entity.ImagesUpload;
import com.jeesite.modules.information.dao.ImagesUploadDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 健康动态Service
 * @author laz
 * @version 2024-05-09
 */
@Service
public class ImagesUploadService extends CrudService<ImagesUploadDao, ImagesUpload> {
	
	/**
	 * 获取单条数据
	 * @param imagesUpload
	 * @return
	 */
	@Override
	public ImagesUpload get(ImagesUpload imagesUpload) {
		return super.get(imagesUpload);
	}
	
	/**
	 * 查询分页数据
	 * @param imagesUpload 查询条件
	 * @param imagesUpload page 分页对象
	 * @return
	 */
	@Override
	public Page<ImagesUpload> findPage(ImagesUpload imagesUpload) {
		return super.findPage(imagesUpload);
	}
	
	/**
	 * 查询列表数据
	 * @param imagesUpload
	 * @return
	 */
	@Override
	public List<ImagesUpload> findList(ImagesUpload imagesUpload) {
		User user = UserUtils.getUser();
		String extWhere = "AND a.user_code = '" + user.getUserCode() + "'";
		imagesUpload.sqlMap().add("extWhere", extWhere);
		return super.findList(imagesUpload);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param imagesUpload
	 */
	@Override
	@Transactional
	public void save(ImagesUpload imagesUpload) {
		User user = UserUtils.getUser();
		imagesUpload.setUserCode(user.getUserCode());
		super.save(imagesUpload);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(imagesUpload, imagesUpload.getId(), "imagesUpload_image");
	}
	
	/**
	 * 更新状态
	 * @param imagesUpload
	 */
	@Override
	@Transactional
	public void updateStatus(ImagesUpload imagesUpload) {
		super.updateStatus(imagesUpload);
	}
	
	/**
	 * 删除数据
	 * @param imagesUpload
	 */
	@Override
	@Transactional
	public void delete(ImagesUpload imagesUpload) {
		super.delete(imagesUpload);
	}
	
}