import { useI18n } from '/@/hooks/web/useI18n';
import { BasicColumn, BasicTableProps, FormProps } from '/@/components/Table';
import { demoUserListData } from '/@/api/information/demoUser';

const { t } = useI18n('sys.demoUser');

const modalProps = {
  title: t('user选择'),
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

const tableProps: BasicTableProps = {
  api: demoUserListData,
  beforeFetch: (params) => {
    params['isAll'] = true;
    return params;
  },
  columns: tableColumns,
  formConfig: searchForm,
  rowKey: 'id',
};

export default {
  modalProps,
  tableProps,
  itemCode: 'id',
  itemName: 'id',
  isShowCode: false,
};
