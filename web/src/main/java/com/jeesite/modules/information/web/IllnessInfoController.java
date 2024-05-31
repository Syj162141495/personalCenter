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
import com.jeesite.modules.information.entity.IllnessInfo;
import com.jeesite.modules.information.service.IllnessInfoService;

/**
 * 疾病信息Controller
 * @author laz
 * @version 2024-05-07
 */
@Controller
@RequestMapping(value = "${adminPath}/information/illnessInfo")
public class IllnessInfoController extends BaseController {

	@Autowired
	private IllnessInfoService illnessInfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public IllnessInfo get(Long tid, boolean isNewRecord) {
		return illnessInfoService.get(tid, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("information:illnessInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(IllnessInfo illnessInfo, Model model) {
		model.addAttribute("illnessInfo", illnessInfo);
		return "modules/information/illnessInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("information:illnessInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<IllnessInfo> listData(IllnessInfo illnessInfo, HttpServletRequest request, HttpServletResponse response) {
		illnessInfo.setPage(new Page<>(request, response));
		Page<IllnessInfo> page = illnessInfoService.findPage(illnessInfo);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("information:illnessInfo:view")
	@RequestMapping(value = "form")
	public String form(IllnessInfo illnessInfo, Model model) {
		model.addAttribute("illnessInfo", illnessInfo);
		return "modules/information/illnessInfoForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("information:illnessInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated IllnessInfo illnessInfo) {
		illnessInfoService.save(illnessInfo);
		return renderResult(Global.TRUE, text("保存疾病信息成功！"));
	}

	/**
	 * 导出数据
	 */
	@RequiresPermissions("information:illnessInfo:view")
	@RequestMapping(value = "exportData")
	public void exportData(IllnessInfo illnessInfo, HttpServletResponse response) {
		List<IllnessInfo> list = illnessInfoService.findList(illnessInfo);
		String fileName = "疾病信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
		try(ExcelExport ee = new ExcelExport("疾病信息", IllnessInfo.class)){
			ee.setDataList(list).write(response, fileName);
		}
	}

	/**
	 * 下载模板
	 */
	@RequiresPermissions("information:illnessInfo:view")
	@RequestMapping(value = "importTemplate")
	public void importTemplate(HttpServletResponse response) {
		IllnessInfo illnessInfo = new IllnessInfo();
		List<IllnessInfo> list = ListUtils.newArrayList(illnessInfo);
		String fileName = "疾病信息模板.xlsx";
		try(ExcelExport ee = new ExcelExport("疾病信息", IllnessInfo.class, Type.IMPORT)){
			ee.setDataList(list).write(response, fileName);
		}
	}

	/**
	 * 导入数据
	 */
	@ResponseBody
	@RequiresPermissions("information:illnessInfo:edit")
	@PostMapping(value = "importData")
	public String importData(MultipartFile file) {
		try {
			String message = illnessInfoService.importData(file);
			return renderResult(Global.TRUE, "posfull:"+message);
		} catch (Exception ex) {
			return renderResult(Global.FALSE, "posfull:"+ex.getMessage());
		}
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("information:illnessInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(IllnessInfo illnessInfo) {
		illnessInfoService.delete(illnessInfo);
		return renderResult(Global.TRUE, text("删除疾病信息成功！"));
	}
	
}