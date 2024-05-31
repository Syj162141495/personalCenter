/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 * No deletion without permission, or be held responsible to law.
 * @author 2
 */
import { defHttp } from '/@/utils/http/axios';
import { useGlobSetting } from '/@/hooks/setting';
import { BasicModel, Page } from '../model/baseModel';
import { UploadApiResult } from '../sys/upload';
import { UploadFileParams } from '/#/axios';

const { ctxPath, adminPath } = useGlobSetting();

export interface DemoUser extends BasicModel<DemoUser> {
  name?: string; // 姓名
  sex?: string; // 性别
  date?: string; // 日期
  message?: string; // 信息
  email?: string; // email
  hobby?: string; // hobby
  habit?: string; // habit
  favor?: string; // favor
  sports?: string; // sports
  score?: string; // score
}

export const demoUserList = (params?: DemoUser | any) =>
  defHttp.get<DemoUser>({ url: adminPath + '/information/demoUser/list', params });

export const demoUserListData = (params?: DemoUser | any) =>
  defHttp.post<Page<DemoUser>>({ url: adminPath + '/information/demoUser/listData', params });

export const demoUserForm = (params?: DemoUser | any) =>
  defHttp.get<DemoUser>({ url: adminPath + '/information/demoUser/form', params });

export const demoUserSave = (params?: any, data?: DemoUser | any) =>
  defHttp.postJson<DemoUser>({ url: adminPath + '/information/demoUser/save', params, data });

export const demoUserImportData = (
  params: UploadFileParams,
  onUploadProgress: (progressEvent: ProgressEvent) => void,
) =>
  defHttp.uploadFile<UploadApiResult>(
    {
      url: ctxPath + adminPath + '/information/demoUser/importData',
      onUploadProgress,
    },
    params,
  );

export const demoUserDelete = (params?: DemoUser | any) =>
  defHttp.get<DemoUser>({ url: adminPath + '/information/demoUser/delete', params });
