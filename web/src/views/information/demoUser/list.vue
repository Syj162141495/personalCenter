<!--
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 * No deletion without permission, or be held responsible to law.
 * @author 2
-->
<template>
  <div>
    <BasicTable @register="registerTable">
      <template #tableTitle>
        <Icon :icon="getTitle.icon" class="m-1 pr-1" />
        <span> {{ getTitle.value }} </span>
      </template>
      <template #toolbar>
        <a-button type="default" @click="handleExport()">
          <Icon icon="ant-design:download-outlined" /> {{ t('导出') }}
        </a-button>
        <a-button type="default" @click="handleImport()">
          <Icon icon="ant-design:upload-outlined" /> {{ t('导入') }}
        </a-button>
        <a-button type="primary" @click="handleForm({})" v-auth="'information:demoUser:edit'">
          <Icon icon="fluent:add-12-filled" /> {{ t('新增') }}
        </a-button>
      </template>
      <template #firstColumn="{ record }">
        <a @click="handleForm({ id: record.id })">
          {{ record.name }}
        </a>
      </template>
    </BasicTable>
    <InputForm @register="registerDrawer" @success="handleSuccess" />
    <FormImport @register="registerImportModal" @success="handleSuccess" />
  </div>
</template>
<script lang="ts" setup name="ViewsInformationDemoUserList">
  import { unref } from 'vue';
  import { useI18n } from '/@/hooks/web/useI18n';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { useGlobSetting } from '/@/hooks/setting';
  import { downloadByUrl } from '/@/utils/file/download';
  import { router } from '/@/router';
  import { Icon } from '/@/components/Icon';
  import { BasicTable, BasicColumn, useTable } from '/@/components/Table';
  import { demoUserDelete, demoUserListData } from '/@/api/information/demoUser';
  import { useDrawer } from '/@/components/Drawer';
  import { useModal } from '/@/components/Modal';
  import { FormProps } from '/@/components/Form';
  import InputForm from './form.vue';
  import FormImport from './formImport.vue';

  const { t } = useI18n('information.demoUser');
  const { showMessage } = useMessage();
  const { meta } = unref(router.currentRoute);

  const getTitle = {
    icon: meta.icon || 'ant-design:book-outlined',
    value: meta.title || t('user管理'),
  };

  const searchForm: FormProps = {
    baseColProps: { lg: 6, md: 8 },
    labelWidth: 90,
    schemas: [
      {
        label: t('姓名'),
        field: 'name',
        component: 'Input',
      },
      {
        label: t('性别'),
        field: 'sex',
        component: 'Input',
      },
      {
        label: t('日期'),
        field: 'date',
        component: 'DatePicker',
        componentProps: {
          format: 'YYYY-MM-DD HH:mm',
          showTime: { format: 'HH:mm' },
        },
      },
      {
        label: t('信息'),
        field: 'message',
        component: 'Input',
      },
      {
        label: t('email'),
        field: 'email',
        component: 'Input',
      },
      {
        label: t('hobby'),
        field: 'hobby',
        component: 'Input',
      },
      {
        label: t('habit'),
        field: 'habit',
        component: 'Input',
      },
      {
        label: t('favor'),
        field: 'favor',
        component: 'Input',
      },
      {
        label: t('sports'),
        field: 'sports',
        component: 'Input',
      },
      {
        label: t('score'),
        field: 'score',
        component: 'Input',
      },
    ],
  };

  const tableColumns: BasicColumn[] = [
    {
      title: t('姓名'),
      dataIndex: 'name',
      key: 'a.name',
      sorter: true,
      width: 230,
      align: 'left',
      slot: 'firstColumn',
    },
    {
      title: t('性别'),
      dataIndex: 'sex',
      key: 'a.sex',
      sorter: true,
      width: 130,
      align: 'left',
    },
    {
      title: t('日期'),
      dataIndex: 'date',
      key: 'a.date',
      sorter: true,
      width: 130,
      align: 'center',
    },
    {
      title: t('信息'),
      dataIndex: 'message',
      key: 'a.message',
      sorter: true,
      width: 130,
      align: 'left',
    },
    {
      title: t('email'),
      dataIndex: 'email',
      key: 'a.email',
      sorter: true,
      width: 130,
      align: 'left',
    },
    {
      title: t('hobby'),
      dataIndex: 'hobby',
      key: 'a.hobby',
      sorter: true,
      width: 130,
      align: 'left',
    },
    {
      title: t('habit'),
      dataIndex: 'habit',
      key: 'a.habit',
      sorter: true,
      width: 130,
      align: 'left',
    },
    {
      title: t('favor'),
      dataIndex: 'favor',
      key: 'a.favor',
      sorter: true,
      width: 130,
      align: 'left',
    },
    {
      title: t('sports'),
      dataIndex: 'sports',
      key: 'a.sports',
      sorter: true,
      width: 130,
      align: 'left',
    },
    {
      title: t('score'),
      dataIndex: 'score',
      key: 'a.score',
      sorter: true,
      width: 130,
      align: 'left',
    },
  ];

  const actionColumn: BasicColumn = {
    width: 160,
    actions: (record: Recordable) => [
      {
        icon: 'clarity:note-edit-line',
        title: t('编辑user'),
        onClick: handleForm.bind(this, { id: record.id }),
        auth: 'information:demoUser:edit',
      },
      {
        icon: 'ant-design:delete-outlined',
        color: 'error',
        title: t('删除user'),
        popConfirm: {
          title: t('是否确认删除user'),
          confirm: handleDelete.bind(this, record),
        },
        auth: 'information:demoUser:edit',
      },
    ],
  };

  const [registerDrawer, { openDrawer }] = useDrawer();
  const [registerTable, { reload, getForm }] = useTable({
    api: demoUserListData,
    beforeFetch: (params) => {
      return params;
    },
    columns: tableColumns,
    actionColumn: actionColumn,
    formConfig: searchForm,
    showTableSetting: true,
    useSearchForm: true,
    canResize: true,
  });

  function handleForm(record: Recordable) {
    openDrawer(true, record);
  }

  async function handleExport() {
    const { ctxAdminPath } = useGlobSetting();
    downloadByUrl({
      url: ctxAdminPath + '/information/demoUser/exportData',
      params: getForm().getFieldsValue(),
    });
  }

  const [registerImportModal, { openModal: importModal }] = useModal();

  function handleImport() {
    importModal(true, {});
  }

  async function handleDelete(record: Recordable) {
    const params = { id: record.id };
    const res = await demoUserDelete(params);
    showMessage(res.message);
    handleSuccess(record);
  }

  function handleSuccess(record: Recordable) {
    reload({ record });
  }
</script>
