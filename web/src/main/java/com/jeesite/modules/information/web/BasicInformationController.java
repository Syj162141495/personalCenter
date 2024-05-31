package com.jeesite.modules.information.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.entity.Page;
import com.jeesite.common.lang.DateUtils;
import com.jeesite.common.utils.excel.ExcelExport;
import com.jeesite.common.utils.excel.annotation.ExcelField.Type;
import org.springframework.web.multipart.MultipartFile;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.information.entity.BasicInformation;
import com.jeesite.modules.information.service.BasicInformationService;

/**
 * 基本信息Controller
 * @author laz
 * @version 2024-05-07
 */
@Controller
@RequestMapping(value = "${adminPath}/information/basicInformation")
public class BasicInformationController extends BaseController {

	@Autowired
	private BasicInformationService basicInformationService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BasicInformation get(String id, boolean isNewRecord) {
		return basicInformationService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("information:basicInformation:view")
	@RequestMapping(value = {"list", ""})
	public String list(BasicInformation basicInformation, Model model) {
		model.addAttribute("basicInformation", basicInformation);
		return "modules/information/basicInformationList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("information:basicInformation:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BasicInformation> listData(BasicInformation basicInformation, HttpServletRequest request, HttpServletResponse response) {
		basicInformation.setPage(new Page<>(request, response));
		Page<BasicInformation> page = basicInformationService.findPage(basicInformation);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("information:basicInformation:view")
	@RequestMapping(value = "form")
	public String form(BasicInformation basicInformation, Model model) {
		model.addAttribute("basicInformation", basicInformation);
		return "modules/information/basicInformationForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("information:basicInformation:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BasicInformation basicInformation) {
		basicInformationService.save(basicInformation);
		return renderResult(Global.TRUE, text("保存信息成功！"));
	}

	/**
	 * 导出数据
	 */
	@RequiresPermissions("information:basicInformation:view")
	@RequestMapping(value = "exportData")
	public void exportData(BasicInformation basicInformation, HttpServletResponse response) {
		List<BasicInformation> list = basicInformationService.findList(basicInformation);
		String fileName = "信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
		try(ExcelExport ee = new ExcelExport("信息", BasicInformation.class)){
			ee.setDataList(list).write(response, fileName);
		}
	}

	/**
	 * 下载模板
	 */
	@RequiresPermissions("information:basicInformation:view")
	@RequestMapping(value = "importTemplate")
	public void importTemplate(HttpServletResponse response) {
		BasicInformation basicInformation = new BasicInformation();
		List<BasicInformation> list = ListUtils.newArrayList(basicInformation);
		String fileName = "信息模板.xlsx";
		try(ExcelExport ee = new ExcelExport("信息", BasicInformation.class, Type.IMPORT)){
			ee.setDataList(list).write(response, fileName);
		}
	}

	/**
	 * 导入数据
	 */
	@ResponseBody
	@RequiresPermissions("information:basicInformation:edit")
	@PostMapping(value = "importData")
	public String importData(MultipartFile file) {
		try {
			String message = basicInformationService.importData(file);
			return renderResult(Global.TRUE, "posfull:"+message);
		} catch (Exception ex) {
			return renderResult(Global.FALSE, "posfull:"+ex.getMessage());
		}
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("information:basicInformation:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BasicInformation basicInformation) {
		basicInformationService.delete(basicInformation);
		return renderResult(Global.TRUE, text("删除信息成功！"));
	}
	
}