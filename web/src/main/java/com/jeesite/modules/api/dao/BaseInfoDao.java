package com.jeesite.modules.api.dao;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.information.entity.BasicInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface BaseInfoDao {

    BasicInformation getBaseInfo(String phone);

    List<JSONObject> listAppointment(@Param("phone") String phone);

    JSONObject getLastDaily(String phone);


    List<JSONObject> getDepartmentRanking(String phone);

    List<JSONObject> listAdvice(@Param("phone")String phone,@Param("adate") String adate);

    List<JSONObject> listTemprature(String phone);

    List<JSONObject> listHeartRate(String phone);

    List<JSONObject> listBloodPressure(String phone);

}
