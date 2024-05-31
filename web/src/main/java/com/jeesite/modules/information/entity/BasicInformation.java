package com.jeesite.modules.information.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.common.utils.excel.annotation.ExcelField;
import com.jeesite.common.utils.excel.annotation.ExcelField.Align;
import com.jeesite.common.utils.excel.annotation.ExcelFields;
import com.jeesite.modules.sys.entity.EmpUser;

/**
 * 基本信息Entity
 * @author laz
 * @version 2024-05-07
 */
@Table(name="basic_information", alias="a", label="信息信息", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="user_code", attrName="userCode", label="user_code", isUpdate=false, isQuery=false),
		@Column(name="patient_name", attrName="patientName", label="患者姓名", queryType=QueryType.LIKE),
		@Column(name="sex", attrName="sex", label="性别"),
		@Column(name="marital_status", attrName="maritalStatus", label="婚姻状态"),
		@Column(name="identification_number", attrName="identificationNumber", label="身份证号"),
		@Column(name="birthday", attrName="birthday", label="生日", isUpdateForce=true),
		@Column(name="age", attrName="age", label="年龄", isUpdateForce=true),
		@Column(name="patient_phone", attrName="patientPhone", label="患者电话"),
		@Column(name="ethnic", attrName="ethnic", label="民族"),
		@Column(name="origin", attrName="origin", label="籍贯"),
		@Column(name="occupation", attrName="occupation", label="职业"),
		@Column(name="education", attrName="education", label="文化程度"),
		@Column(name="health_insurance", attrName="healthInsurance", label="医保情况"),
		@Column(name="residential_area", attrName="residentialArea", label="居住地区"),
		@Column(name="address", attrName="address", label="地址"),
		@Column(name="illness", attrName="illness", label="所患疾病"),
		@Column(name="height", attrName="height", label="身高", isUpdateForce=true),
		@Column(name="alcohol_consumption", attrName="alcoholConsumption", label="饮酒情况"),
		@Column(name="smoking_situation", attrName="smokingSituation", label="吸烟情况"),
		@Column(name="sports_situation", attrName="sportsSituation", label="运动情况"),
		@Column(name="mode_of_motion", attrName="modeOfMotion", label="运动方式"),
		@Column(name="meditation_situation", attrName="meditationSituation", label="生活以静坐为主"),
	},
	joinTable = {
		@JoinTable(type=Type.JOIN, entity=EmpUser.class, alias="e",
					on="e.user_code = a.user_code", attrName="empuser",
					columns={@Column(includeEntity=EmpUser.class)}),
	},
	extWhereKeys="extWhere",
	orderBy="a.id DESC"
)
public class BasicInformation extends DataEntity<BasicInformation> {
	
	private static final long serialVersionUID = 1L;
	private String userCode;		// user_code
	private String patientName;		// 患者姓名
	private String sex;		// 性别
	private String maritalStatus;		// 婚姻状态
	private String identificationNumber;		// 身份证号
	private Date birthday;		// 生日
	private Long age;		// 年龄
	private String patientPhone;		// 患者电话
	private String ethnic;		// 民族
	private String origin;		// 籍贯
	private String occupation;		// 职业
	private String education;		// 文化程度
	private String healthInsurance;		// 医保情况
	private String residentialArea;		// 居住地区
	private String address;		// 地址
	private String illness;		// 所患疾病
	private Double height;		// 身高
	private String alcoholConsumption;		// 饮酒情况
	private String smokingSituation;		// 吸烟情况
	private String sportsSituation;		// 运动情况
	private String modeOfMotion;		// 运动方式
	private String meditationSituation;		// 生活以静坐为主

	@ExcelFields({
		@ExcelField(title="患者姓名", attrName="patientName", align=Align.CENTER, sort=30),
		@ExcelField(title="性别", attrName="sex", dictType="sys_user_sex", align=Align.CENTER, sort=40),
		@ExcelField(title="婚姻状态", attrName="maritalStatus", align=Align.CENTER, sort=50),
		@ExcelField(title="身份证号", attrName="identificationNumber", align=Align.CENTER, sort=60),
		@ExcelField(title="生日", attrName="birthday", align=Align.CENTER, sort=70, dataFormat="yyyy-MM-dd hh:mm"),
		@ExcelField(title="年龄", attrName="age", align=Align.CENTER, sort=80),
		@ExcelField(title="患者电话", attrName="patientPhone", align=Align.CENTER, sort=90),
		@ExcelField(title="民族", attrName="ethnic", align=Align.CENTER, sort=100),
		@ExcelField(title="籍贯", attrName="origin", align=Align.CENTER, sort=110),
		@ExcelField(title="职业", attrName="occupation", align=Align.CENTER, sort=120),
		@ExcelField(title="文化程度", attrName="education", align=Align.CENTER, sort=130),
		@ExcelField(title="医保情况", attrName="healthInsurance", align=Align.CENTER, sort=140),
		@ExcelField(title="居住地区", attrName="residentialArea", align=Align.CENTER, sort=150),
		@ExcelField(title="地址", attrName="address", align=Align.CENTER, sort=160),
	})
	public BasicInformation() {
		this(null);
	}
	
	public BasicInformation(String id){
		super(id);
	}
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@NotBlank(message="患者姓名不能为空")
	@Size(min=0, max=64, message="患者姓名长度不能超过 64 个字符")
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	@NotBlank(message="性别不能为空")
	@Size(min=0, max=1, message="性别长度不能超过 1 个字符")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Size(min=0, max=10, message="婚姻状态长度不能超过 10 个字符")
	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	@NotBlank(message="身份证号不能为空")
	@Size(min=0, max=20, message="身份证号长度不能超过 20 个字符")
	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}
	
	@Size(min=0, max=20, message="患者电话长度不能超过 20 个字符")
	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
	
	@Size(min=0, max=20, message="民族长度不能超过 20 个字符")
	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}
	
	@Size(min=0, max=255, message="籍贯长度不能超过 255 个字符")
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	@Size(min=0, max=255, message="职业长度不能超过 255 个字符")
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	@Size(min=0, max=255, message="文化程度长度不能超过 255 个字符")
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	@Size(min=0, max=255, message="医保情况长度不能超过 255 个字符")
	public String getHealthInsurance() {
		return healthInsurance;
	}

	public void setHealthInsurance(String healthInsurance) {
		this.healthInsurance = healthInsurance;
	}
	
	@Size(min=0, max=255, message="居住地区长度不能超过 255 个字符")
	public String getResidentialArea() {
		return residentialArea;
	}

	public void setResidentialArea(String residentialArea) {
		this.residentialArea = residentialArea;
	}
	
	@Size(min=0, max=255, message="地址长度不能超过 255 个字符")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Size(min=0, max=255, message="所患疾病长度不能超过 255 个字符")
	public String getIllness() {
		return illness;
	}

	public void setIllness(String illness) {
		this.illness = illness;
	}
	
	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}
	
	@Size(min=0, max=255, message="饮酒情况长度不能超过 255 个字符")
	public String getAlcoholConsumption() {
		return alcoholConsumption;
	}

	public void setAlcoholConsumption(String alcoholConsumption) {
		this.alcoholConsumption = alcoholConsumption;
	}
	
	@Size(min=0, max=255, message="吸烟情况长度不能超过 255 个字符")
	public String getSmokingSituation() {
		return smokingSituation;
	}

	public void setSmokingSituation(String smokingSituation) {
		this.smokingSituation = smokingSituation;
	}
	
	@Size(min=0, max=255, message="运动情况长度不能超过 255 个字符")
	public String getSportsSituation() {
		return sportsSituation;
	}

	public void setSportsSituation(String sportsSituation) {
		this.sportsSituation = sportsSituation;
	}
	
	@Size(min=0, max=255, message="运动方式长度不能超过 255 个字符")
	public String getModeOfMotion() {
		return modeOfMotion;
	}

	public void setModeOfMotion(String modeOfMotion) {
		this.modeOfMotion = modeOfMotion;
	}
	
	@Size(min=0, max=8, message="生活以静坐为主长度不能超过 8 个字符")
	public String getMeditationSituation() {
		return meditationSituation;
	}

	public void setMeditationSituation(String meditationSituation) {
		this.meditationSituation = meditationSituation;
	}
	
}