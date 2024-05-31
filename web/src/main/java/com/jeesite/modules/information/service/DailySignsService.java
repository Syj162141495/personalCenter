package com.jeesite.modules.information.service;

import java.util.List;

import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.information.entity.DailySigns;
import com.jeesite.modules.information.dao.DailySignsDao;
import com.jeesite.common.service.ServiceException;
import com.jeesite.common.config.Global;
import com.jeesite.common.validator.ValidatorUtils;
import com.jeesite.common.utils.excel.ExcelImport;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * 日常体征Service
 * @author laz
 * @version 2024-05-09
 */
@Service
public class DailySignsService extends CrudService<DailySignsDao, DailySigns> {
	
	/**
	 * 获取单条数据
	 * @param dailySigns
	 * @return
	 */
	@Override
	public DailySigns get(DailySigns dailySigns) {
		return super.get(dailySigns);
	}
	
	/**
	 * 查询分页数据
	 * @param dailySigns 查询条件
	 * @param dailySigns page 分页对象
	 * @return
	 */
	@Override
	public Page<DailySigns> findPage(DailySigns dailySigns) {
		return super.findPage(dailySigns);
	}
	
	/**
	 * 查询列表数据
	 * @param dailySigns
	 * @return
	 */
	@Override
	public List<DailySigns> findList(DailySigns dailySigns) {
		User user = UserUtils.getUser();
		String extWhere = "AND a.user_code = '" + user.getUserCode() + "'";
		dailySigns.sqlMap().add("extWhere", extWhere);
		return super.findList(dailySigns);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dailySigns
	 */
	@Override
	@Transactional
	public void save(DailySigns dailySigns) {
		User user = UserUtils.getUser();
		dailySigns.setUserCode(user.getUserCode());
		super.save(dailySigns);
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
			List<DailySigns> list = ei.getDataList(DailySigns.class);
			for (DailySigns dailySigns : list) {
				try{
					ValidatorUtils.validateWithException(dailySigns);
					this.save(dailySigns);
					successNum++;
					successMsg.append("<br/>" + successNum + "、编号 " + dailySigns.getId() + " 导入成功");
				} catch (Exception e) {
					failureNum++;
					String msg = "<br/>" + failureNum + "、编号 " + dailySigns.getId() + " 导入失败：";
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
	 * @param dailySigns
	 */
	@Override
	@Transactional
	public void updateStatus(DailySigns dailySigns) {
		super.updateStatus(dailySigns);
	}
	
	/**
	 * 删除数据
	 * @param dailySigns
	 */
	@Override
	@Transactional
	public void delete(DailySigns dailySigns) {
		super.delete(dailySigns);
	}
	
}