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
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.information.entity.ImagesUpload;
import com.jeesite.modules.information.service.ImagesUploadService;

/**
 * 健康动态Controller
 * @author laz
 * @version 2024-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/information/imagesUpload")
public class ImagesUploadController extends BaseController {

	@Autowired
	private ImagesUploadService imagesUploadService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ImagesUpload get(String id, boolean isNewRecord) {
		return imagesUploadService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("information:imagesUpload:view")
	@RequestMapping(value = {"list", ""})
	public String list(ImagesUpload imagesUpload, Model model) {
		model.addAttribute("imagesUpload", imagesUpload);
		return "modules/information/imagesUploadList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("information:imagesUpload:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ImagesUpload> listData(ImagesUpload imagesUpload, HttpServletRequest request, HttpServletResponse response) {
		imagesUpload.setPage(new Page<>(request, response));
		Page<ImagesUpload> page = imagesUploadService.findPage(imagesUpload);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("information:imagesUpload:view")
	@RequestMapping(value = "form")
	public String form(ImagesUpload imagesUpload, Model model) {
		model.addAttribute("imagesUpload", imagesUpload);
		return "modules/information/imagesUploadForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("information:imagesUpload:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ImagesUpload imagesUpload) {
		imagesUploadService.save(imagesUpload);
		return renderResult(Global.TRUE, text("保存健康动态成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("information:imagesUpload:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ImagesUpload imagesUpload) {
		imagesUploadService.delete(imagesUpload);
		return renderResult(Global.TRUE, text("删除健康动态成功！"));
	}
	
}