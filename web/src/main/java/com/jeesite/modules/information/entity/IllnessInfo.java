package com.jeesite.modules.information.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.common.utils.excel.annotation.ExcelField;
import com.jeesite.common.utils.excel.annotation.ExcelField.Align;
import com.jeesite.common.utils.excel.annotation.ExcelFields;

/**
 * 疾病信息Entity
 * @author laz
 * @version 2024-05-07
 */
@Table(name="illness_info", alias="a", label="疾病信息信息", columns={
		@Column(name="id", attrName="tid", label="id", isPK=true),
		@Column(name="illness_type", attrName="illnessType", label="疾病种类", queryType=QueryType.LIKE),
		@Column(name="illness_name", attrName="illnessName", label="疾病名称", queryType=QueryType.LIKE),
	}, orderBy="a.id DESC"
)
public class IllnessInfo extends DataEntity<IllnessInfo> {
	
	private static final long serialVersionUID = 1L;
	private Long tid;		// id
	private String illnessType;		// 疾病种类
	private String illnessName;		// 疾病名称

	@ExcelFields({
		@ExcelField(title="id", attrName="tid", align=Align.CENTER, sort=10),
		@ExcelField(title="疾病种类", attrName="illnessType", align=Align.CENTER, sort=20),
		@ExcelField(title="疾病名称", attrName="illnessName", align=Align.CENTER, sort=30),
	})
	public IllnessInfo() {
		this(null);
	}
	
	public IllnessInfo(String id){
		super(id);
	}
	
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}
	
	@NotBlank(message="疾病种类不能为空")
	@Size(min=0, max=255, message="疾病种类长度不能超过 255 个字符")
	public String getIllnessType() {
		return illnessType;
	}

	public void setIllnessType(String illnessType) {
		this.illnessType = illnessType;
	}
	
	@NotBlank(message="疾病名称不能为空")
	@Size(min=0, max=255, message="疾病名称长度不能超过 255 个字符")
	public String getIllnessName() {
		return illnessName;
	}

	public void setIllnessName(String illnessName) {
		this.illnessName = illnessName;
	}
	
}