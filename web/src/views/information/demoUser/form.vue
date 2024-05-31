<!--
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 * No deletion without permission, or be held responsible to law.
 * @author 2
-->
<template>
  <BasicDrawer
    v-bind="$attrs"
    :showFooter="true"
    :okAuth="'information:demoUser:edit'"
    @register="registerDrawer"
    @ok="handleSubmit"
    width="60%"
  >
    <template #title>
      <Icon :icon="getTitle.icon" class="m-1 pr-1" />
      <span> {{ getTitle.value }} </span>
    </template>
    <BasicForm @register="registerForm" />
  </BasicDrawer>
</template>
<script lang="ts" setup name="ViewsInformationDemoUserForm">
  import { ref, unref, computed } from 'vue';
  import { useI18n } from '/@/hooks/web/useI18n';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { router } from '/@/router';
  import { Icon } from '/@/components/Icon';
  import { BasicForm, FormSchema, useForm } from '/@/components/Form';
  import { BasicDrawer, useDrawerInner } from '/@/components/Drawer';
  import { DemoUser, demoUserSave, demoUserForm } from '/@/api/information/demoUser';

  const emit = defineEmits(['success', 'register']);

  const { t } = useI18n('information.demoUser');
  const { showMessage } = useMessage();
  const { meta } = unref(router.currentRoute);
  const record = ref<DemoUser>({} as DemoUser);

  const getTitle = computed(() => ({
    icon: meta.icon || 'ant-design:book-outlined',
    value: record.value.isNewRecord ? t('新增user') : t('编辑user'),
  }));

  const inputFormSchemas: FormSchema[] = [
    {
      label: t('姓名'),
      field: 'name',
      component: 'Input',
      componentProps: {
        maxlength: 255,
      },
      required: true,
    },
    {
      label: t('性别'),
      field: 'sex',
      component: 'Input',
      componentProps: {
        maxlength: 255,
      },
      required: true,
      colProps: { lg: 24, md: 24 },
    },
    {
      label: t('日期'),
      field: 'date',
      component: 'DatePicker',
      componentProps: {
        format: 'YYYY-MM-DD HH:mm',
        showTime: { format: 'HH:mm' },
      },
      required: true,
      colProps: { lg: 24, md: 24 },
    },
    {
      label: t('信息'),
      field: 'message',
      component: 'Input',
      componentProps: {
        maxlength: 255,
      },
      colProps: { lg: 24, md: 24 },
    },
    {
      label: t('email'),
      field: 'email',
      component: 'Input',
      componentProps: {
        maxlength: 255,
      },
    },
    {
      label: t('hobby'),
      field: 'hobby',
      component: 'Input',
      componentProps: {
        maxlength: 255,
      },
      colProps: { lg: 24, md: 24 },
    },
    {
      label: t('habit'),
      field: 'habit',
      component: 'Input',
      componentProps: {
        maxlength: 255,
      },
      colProps: { lg: 24, md: 24 },
    },
    {
      label: t('favor'),
      field: 'favor',
      component: 'Input',
      componentProps: {
        maxlength: 255,
      },
      colProps: { lg: 24, md: 24 },
    },
    {
      label: t('sports'),
      field: 'sports',
      component: 'Input',
      componentProps: {
        maxlength: 255,
      },
    },
    {
      label: t('score'),
      field: 'score',
      component: 'Input',
      componentProps: {
        maxlength: 255,
      },
      colProps: { lg: 24, md: 24 },
    },
  ];

  const [registerForm, { resetFields, setFieldsValue, validate }] = useForm({
    labelWidth: 120,
    schemas: inputFormSchemas,
    baseColProps: { lg: 12, md: 24 },
  });

  const [registerDrawer, { setDrawerProps, closeDrawer }] = useDrawerInner(async (data) => {
    setDrawerProps({ loading: true });
    await resetFields();
    const res = await demoUserForm(data);
    record.value = (res.demoUser || {}) as DemoUser;
    record.value.__t = new Date().getTime();
    setFieldsValue(record.value);
    setDrawerProps({ loading: false });
  });

  async function handleSubmit() {
    try {
      const data = await validate();
      setDrawerProps({ confirmLoading: true });
      const params: any = {
        isNewRecord: record.value.isNewRecord,
        id: record.value.id,
      };
      // console.log('submit', params, data, record);
      const res = await demoUserSave(params, data);
      showMessage(res.message);
      setTimeout(closeDrawer);
      emit('success', data);
    } catch (error: any) {
      if (error && error.errorFields) {
        showMessage(t('common.validateError'));
      }
      console.log('error', error);
    } finally {
      setDrawerProps({ confirmLoading: false });
    }
  }
</script>
