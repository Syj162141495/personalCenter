package com.jeesite.modules.information.entity;

import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.common.utils.excel.annotation.ExcelField;
import com.jeesite.common.utils.excel.annotation.ExcelField.Align;
import com.jeesite.common.utils.excel.annotation.ExcelFields;
import com.jeesite.modules.sys.entity.EmpUser;
import org.apache.poi.hpsf.Decimal;

/**
 * 日常体征Entity
 * @author laz
 * @version 2024-05-09
 */
@Table(name="daily_signs", alias="a", label="日常体征信息", columns={
		@Column(name="id", attrName="tid", label="id", isPK=true),
		@Column(name="user_code", attrName="userCode", label="用户编码", isUpdate=false, isQuery=false),
		@Column(name="identification", attrName="identification", label="身份证号",isUpdateForce=true, isQuery=false),
		@Column(name="create_time", attrName="createTime", label="创建时间", queryType=QueryType.LIKE),
		@Column(name="weight", attrName="weight", label="体重", comment="体重（kg）", isUpdateForce=true),
		@Column(name="heart_rate", attrName="heartRate", label="心率", comment="心率（次/分）", isUpdateForce=true),
		@Column(name="breathe", attrName="breathe", label="呼吸频率", comment="呼吸频率（次/分）", isUpdateForce=true),
		@Column(name="systolic_pressure", attrName="systolicPressure", label="收缩压", comment="收缩压（mmHg）", isUpdateForce=true),
		@Column(name="diastolic_pressure", attrName="diastolicPressure", label="舒张压", comment="舒张压(mmHg)", isUpdateForce=true),
		@Column(name="blood_sugar", attrName="bloodSugar", label="血糖", isUpdateForce=true),
		@Column(name="temperature", attrName="temperature", label="体温", isUpdateForce=true),
	},
		joinTable = {
				@JoinTable(type=Type.JOIN, entity= EmpUser.class, alias="e",
						on="e.user_code = a.user_code", attrName="empuser",
						columns={@Column(includeEntity=EmpUser.class)}),
		},
		extWhereKeys="extWhere",
		orderBy="a.id DESC"
)
public class DailySigns extends DataEntity<DailySigns> {
	
	private static final long serialVersionUID = 1L;
	private String tid;		// id
	private String userCode;		// 用户编码
	private String identification;		// 身份证号
	private Date createTime;		// 创建时间
	private Double weight;		// 体重（kg）
	private Long heartRate;		// 心率（次/分）
	private Double breathe;		// 呼吸频率（次/分）
	private Long systolicPressure;		// 收缩压（mmHg）
	private Long diastolicPressure;		// 舒张压(mmHg)
	private Double bloodSugar;		// 血糖
	private Decimal temperature;		// 体温

	@ExcelFields({
		@ExcelField(title="id", attrName="tid", align=Align.CENTER, sort=10),
		@ExcelField(title="创建时间", attrName="createTime", align=Align.CENTER, sort=40, dataFormat="yyyy-MM-dd hh:mm"),
		@ExcelField(title="体重", attrName="weight", align=Align.CENTER, sort=50),
		@ExcelField(title="心率", attrName="heartRate", align=Align.CENTER, sort=60),
		@ExcelField(title="呼吸频率", attrName="breathe", align=Align.CENTER, sort=70),
		@ExcelField(title="收缩压", attrName="systolicPressure", align=Align.CENTER, sort=80),
		@ExcelField(title="舒张压", attrName="diastolicPressure", align=Align.CENTER, sort=90),
		@ExcelField(title="血糖", attrName="bloodSugar", align=Align.CENTER, sort=100),
	})
	public DailySigns() {
		this(null);
	}
	
	public DailySigns(String id){
		super(id);
	}
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	public Long getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(Long heartRate) {
		this.heartRate = heartRate;
	}
	
	public Double getBreathe() {
		return breathe;
	}

	public void setBreathe(Double breathe) {
		this.breathe = breathe;
	}
	
	public Long getSystolicPressure() {
		return systolicPressure;
	}

	public void setSystolicPressure(Long systolicPressure) {
		this.systolicPressure = systolicPressure;
	}
	
	public Long getDiastolicPressure() {
		return diastolicPressure;
	}

	public void setDiastolicPressure(Long diastolicPressure) {
		this.diastolicPressure = diastolicPressure;
	}
	
	public Double getBloodSugar() {
		return bloodSugar;
	}

	public void setBloodSugar(Double bloodSugar) {
		this.bloodSugar = bloodSugar;
	}

	public Decimal getTemperature() {
		return temperature;
	}

	public void setTemperature(Decimal temperature) {
		this.temperature = temperature;
	}
}