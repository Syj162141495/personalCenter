package com.jeesite.modules.information.service;

import java.util.List;

import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.information.entity.BasicInformation;
import com.jeesite.modules.information.dao.BasicInformationDao;
import com.jeesite.common.service.ServiceException;
import com.jeesite.common.config.Global;
import com.jeesite.common.validator.ValidatorUtils;
import com.jeesite.common.utils.excel.ExcelImport;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * 基本信息Service
 * @author laz
 * @version 2024-05-07
 */
@Service
public class BasicInformationService extends CrudService<BasicInformationDao, BasicInformation> {
	
	/**
	 * 获取单条数据
	 * @param basicInformation
	 * @return
	 */
	@Override
	public BasicInformation get(BasicInformation basicInformation) {
		return super.get(basicInformation);
	}
	
	/**
	 * 查询分页数据
	 * @param basicInformation 查询条件
	 * @param basicInformation page 分页对象
	 * @return
	 */
	@Override
	public Page<BasicInformation> findPage(BasicInformation basicInformation) {
		return super.findPage(basicInformation);
	}
	
	/**
	 * 查询列表数据
	 * @param basicInformation
	 * @return
	 */
	@Override
	public List<BasicInformation> findList(BasicInformation basicInformation) {
		User user = UserUtils.getUser();
		String extWhere = "AND a.user_code = '" + user.getUserCode() + "'";
		basicInformation.sqlMap().add("extWhere", extWhere);
		return super.findList(basicInformation);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param basicInformation
	 */
	@Override
	@Transactional
	public void save(BasicInformation basicInformation) {
		super.save(basicInformation);
	}

	/**
	 * 导入数据
	 * @param file 导入的数据文件
	 */
	@Transactional
	public String importData(MultipartFile file) {
		if (file == null){
			throw new ServiceException(text("请选择导入的数据文件！"));
		}
		int successNum = 0; int failureNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		try(ExcelImport ei = new ExcelImport(file, 2, 0)){
			List<BasicInformation> list = ei.getDataList(BasicInformation.class);
			for (BasicInformation basicInformation : list) {
				try{
					ValidatorUtils.validateWithException(basicInformation);
					this.save(basicInformation);
					successNum++;
					successMsg.append("<br/>" + successNum + "、编号 " + basicInformation.getId() + " 导入成功");
				} catch (Exception e) {
					failureNum++;
					String msg = "<br/>" + failureNum + "、编号 " + basicInformation.getId() + " 导入失败：";
					if (e instanceof ConstraintViolationException){
						ConstraintViolationException cve = (ConstraintViolationException)e;
						for (ConstraintViolation<?> violation : cve.getConstraintViolations()) {
							msg += Global.getText(violation.getMessage()) + " ("+violation.getPropertyPath()+")";
						}
					}else{
						msg += e.getMessage();
					}
					failureMsg.append(msg);
					logger.error(msg, e);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			failureMsg.append(e.getMessage());
			return failureMsg.toString();
		}
		if (failureNum > 0) {
			failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
			throw new ServiceException(failureMsg.toString());
		}else{
			successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
		}
		return successMsg.toString();
	}
	
	/**
	 * 更新状态
	 * @param basicInformation
	 */
	@Override
	@Transactional
	public void updateStatus(BasicInformation basicInformation) {
		super.updateStatus(basicInformation);
	}
	
	/**
	 * 删除数据
	 * @param basicInformation
	 */
	@Override
	@Transactional
	public void delete(BasicInformation basicInformation) {
		super.delete(basicInformation);
	}
	
}