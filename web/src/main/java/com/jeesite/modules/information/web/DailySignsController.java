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
import com.jeesite.modules.information.entity.DailySigns;
import com.jeesite.modules.information.service.DailySignsService;

/**
 * 日常体征Controller
 * @author laz
 * @version 2024-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/information/dailySigns")
public class DailySignsController extends BaseController {

	@Autowired
	private DailySignsService dailySignsService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DailySigns get(String tid, boolean isNewRecord) {
		return dailySignsService.get(tid, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("information:dailySigns:view")
	@RequestMapping(value = {"list", ""})
	public String list(DailySigns dailySigns, Model model) {
		model.addAttribute("dailySigns", dailySigns);
		return "modules/information/dailySignsList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("information:dailySigns:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DailySigns> listData(DailySigns dailySigns, HttpServletRequest request, HttpServletResponse response) {
		dailySigns.setPage(new Page<>(request, response));
		Page<DailySigns> page = dailySignsService.findPage(dailySigns);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("information:dailySigns:view")
	@RequestMapping(value = "form")
	public String form(DailySigns dailySigns, Model model) {
		model.addAttribute("dailySigns", dailySigns);
		return "modules/information/dailySignsForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("information:dailySigns:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DailySigns dailySigns) {
		dailySignsService.save(dailySigns);
		return renderResult(Global.TRUE, text("保存日常体征成功！"));
	}

	/**
	 * 导出数据
	 */
	@RequiresPermissions("information:dailySigns:view")
	@RequestMapping(value = "exportData")
	public void exportData(DailySigns dailySigns, HttpServletResponse response) {
		List<DailySigns> list = dailySignsService.findList(dailySigns);
		String fileName = "日常体征" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
		try(ExcelExport ee = new ExcelExport("日常体征", DailySigns.class)){
			ee.setDataList(list).write(response, fileName);
		}
	}

	/**
	 * 下载模板
	 */
	@RequiresPermissions("information:dailySigns:view")
	@RequestMapping(value = "importTemplate")
	public void importTemplate(HttpServletResponse response) {
		DailySigns dailySigns = new DailySigns();
		List<DailySigns> list = ListUtils.newArrayList(dailySigns);
		String fileName = "日常体征模板.xlsx";
		try(ExcelExport ee = new ExcelExport("日常体征", DailySigns.class, Type.IMPORT)){
			ee.setDataList(list).write(response, fileName);
		}
	}

	/**
	 * 导入数据
	 */
	@ResponseBody
	@RequiresPermissions("information:dailySigns:edit")
	@PostMapping(value = "importData")
	public String importData(MultipartFile file) {
		try {
			String message = dailySignsService.importData(file);
			return renderResult(Global.TRUE, "posfull:"+message);
		} catch (Exception ex) {
			return renderResult(Global.FALSE, "posfull:"+ex.getMessage());
		}
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("information:dailySigns:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DailySigns dailySigns) {
		dailySignsService.delete(dailySigns);
		return renderResult(Global.TRUE, text("删除日常体征成功！"));
	}
	
}