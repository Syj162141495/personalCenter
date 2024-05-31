package com.jeesite.modules.information.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.modules.sys.entity.EmpUser;

/**
 * 健康动态Entity
 * @author laz
 * @version 2024-05-09
 */
@Table(name="images_upload", alias="a", label="健康动态信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="user_code", attrName="userCode", label="用户编号", isUpdate=false, isQuery=false),
		@Column(name="identification", attrName="identification", label="身份证号", isQuery=false),
		@Column(name="type", attrName="type", label="类型"),
		@Column(name="other", attrName="other", label="其他"),
		@Column(name="time", attrName="time", label="时间"),
		@Column(name="description", attrName="description", label="描述"),
	},
		joinTable = {
				@JoinTable(type=Type.JOIN, entity= EmpUser.class, alias="e",
						on="e.user_code = a.user_code", attrName="empuser",
						columns={@Column(includeEntity=EmpUser.class)}),
		},
		extWhereKeys="extWhere",
		orderBy="a.id DESC"
)
public class ImagesUpload extends DataEntity<ImagesUpload> {
	
	private static final long serialVersionUID = 1L;
	private String userCode;		// 用户编号
	private String identification;		// 身份证号
	private String type;		// 类型
	private String other;		// 其他
	private Date time;		// 时间
	private String description;		// 描述

	public ImagesUpload() {
		this(null);
	}
	
	public ImagesUpload(String id){
		super(id);
	}
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}
	
	@NotBlank(message="类型不能为空")
	@Size(min=0, max=16, message="类型长度不能超过 16 个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Size(min=0, max=32, message="其他长度不能超过 32 个字符")
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="时间不能为空")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	@Size(min=0, max=255, message="描述长度不能超过 255 个字符")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}