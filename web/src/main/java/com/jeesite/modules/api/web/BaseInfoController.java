package com.jeesite.modules.api.web;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.api.service.BaseInfoService;
import com.jeesite.modules.information.entity.BasicInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author weidong
 * @description: 首页桌面接口
 * @date 2024年06月28日
 */
@Controller
@RequestMapping(value = "/api/desktop")
public class BaseInfoController {

    @Autowired
    private BaseInfoService baseInfoService;

    /**
     * 查询基础信息
     * @param phone
     * @return
     */
    @RequestMapping(value = "getBaseInfo")
    @ResponseBody
    public BasicInformation getBaseInfo(String phone) {
        return baseInfoService.getBaseInfo(phone);
    }

    /**
     * 预约信息
     * @param phone
     * @return
     */
    @RequestMapping(value = "listAppointment")
    @ResponseBody
    public List<JSONObject> listAppointment(String phone) {
        return baseInfoService.listAppointment(phone);
    }

    /**
     * 实时信息
     * @param phone
     * @return
     */
    @RequestMapping(value = "getNowInfo")
    @ResponseBody
    public JSONObject getNowInfo(String phone) {
        return baseInfoService.getNowInfo(phone);
    }

    /**
     * 医生建议
     * @param phone
     * @param adate 建议日期
     * @return
     */
    @RequestMapping(value = "listAdvice")
    @ResponseBody
    public List<JSONObject> listAdvice(String phone,String adate) {
        return baseInfoService.listAdvice(phone,adate);
    }

    /**
     * 体温图表
     * @param phone
     * @return
     */
    @RequestMapping(value = "listTemprature")
    @ResponseBody
    public List<JSONObject> listTemprature(String phone) {
        return baseInfoService.listTemprature(phone);
    }

    /**
     * 心率图表
     * @param phone
     * @return
     */
    @RequestMapping(value = "listHeartRate")
    @ResponseBody
    public List<JSONObject> listHeartRate(String phone) {
        return baseInfoService.listHeartRate(phone);
    }

    /**
     * 血压图表
     * @param phone
     * @return
     */
    @RequestMapping(value = "listBloodPressure")
    @ResponseBody
    public List<JSONObject> listBloodPressure(String phone) {
        return baseInfoService.listBloodPressure(phone);
    }


}
