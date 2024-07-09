package com.jeesite.modules.api.service;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.api.dao.BaseInfoDao;
import com.jeesite.modules.information.dao.BasicInformationDao;
import com.jeesite.modules.information.entity.BasicInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weidong
 * @description: TODO
 * @date 2024年07月01日
 */
@Service
public class BaseInfoService  extends CrudService<BasicInformationDao, BasicInformation> {

    @Autowired
    private BaseInfoDao baseInfoDao;

    public BasicInformation getBaseInfo(String phone) {
        return baseInfoDao.getBaseInfo(phone);
    }

    public List<JSONObject> listAppointment(String phone) {
        return baseInfoDao.listAppointment(phone);
    }

    public JSONObject getNowInfo(String phone) {
        JSONObject jsonObject = new JSONObject();
        // 最后一条日常体征数据
        JSONObject daily = baseInfoDao.getLastDaily(phone);
        jsonObject.put("daily",daily);
        // 科室排行统计
        List<JSONObject> departments = baseInfoDao.getDepartmentRanking(phone);
        jsonObject.put("departments",departments);
        return jsonObject;
    }

    public List<JSONObject> listAdvice(String phone, String adate) {
        return baseInfoDao.listAdvice(phone,adate);
    }

    public List<JSONObject> listTemprature(String phone) {
        return baseInfoDao.listTemprature(phone);
    }

    public List<JSONObject> listHeartRate(String phone) {
        return baseInfoDao.listHeartRate(phone);
    }

    public List<JSONObject> listBloodPressure(String phone) {
        return baseInfoDao.listBloodPressure(phone);
    }
}
