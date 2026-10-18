<template>
  <div class="p-2 page-shell pxzs-certificate_config-page">
    <div class="search-wrap">
      <el-card shadow="hover" class="search-panel" :class="{ 'is-collapsed': !showSearch }">
        <template #header>
          <div class="panel-heading search-panel-toggle" @click.stop="showSearch = !showSearch">
            <div><h3>筛选条件</h3></div>
          </div>
        </template>
        <el-form ref="queryFormRef" :model="queryParams" :inline="true" class="query-form">
            <el-form-item label="证书配置" prop="status">
              <el-select v-model="queryParams.status" placeholder="请选择certificate_config" clearable >
                <el-option v-for="dict in sys_normal_disable" :key="dict.value" :label="dict.label" :value="dict.value"/>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
              <el-button icon="Refresh" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>
      </el-card>
    </div>

    <el-card shadow="hover" class="table-panel">
      <template #header>
        <div class="toolbar-shell">
          <div class="table-heading">
            <h3>CertificateConfig列表</h3>
          </div>
          <div class="toolbar-actions">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['pxzs:certificate_config:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['pxzs:certificate_config:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['pxzs:certificate_config:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['pxzs:certificate_config:export']">导出</el-button>
            <right-toolbar v-model:show-search="showSearch" :search="false" @query-table="getList"></right-toolbar>
          </div>
        </div>
      </template>

      <el-table v-loading="loading" border class="data-table" :data="certificate_configList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="证书配置" align="center" prop="id" />
        <el-table-column label="证书配置" align="center" prop="status">
          <template #default="scope">
            <dict-tag :options="sys_normal_disable" :value="scope.row.status"/>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['pxzs:certificate_config:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['pxzs:certificate_config:remove']"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>
    <!-- 添加或修改CertificateConfig对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="certificate_configFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="证书配置" prop="status">
          <el-select v-model="form.status" placeholder="请选择certificate_config">
            <el-option
                v-for="dict in sys_normal_disable"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Certificate_config" lang="ts">
import {
  addCertificate_config,
  delCertificate_config,
  getCertificate_config,
  listCertificate_config,
  updateCertificate_config
} from '@/api/pxzs/certificate_config';
import { Certificate_configForm, Certificate_configQuery, Certificate_configVO } from '@/api/pxzs/certificate_config/types';
import { useLoading } from '@/hooks/async/useLoading';
import { useFormDialog } from '@/hooks/dialog/useFormDialog';
import { useSearchReset } from '@/hooks/form/useSearchReset';
import { useSearchToggle } from '@/hooks/form/useSearchToggle';
import { useTableSelection } from '@/hooks/table/useTableSelection';
import { useDict } from '@/utils/dict';
import modal from '@/plugins/modal';
import { download as requestDownload } from '@/utils/request';

const { sys_normal_disable } = toRefs<any>(useDict('sys_normal_disable'));


const certificate_configList = ref<Certificate_configVO[]>([]);
const buttonLoading = ref(false);
const { loading, withLoading } = useLoading(true);
const { showSearch } = useSearchToggle();
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const certificate_configFormRef = ref<ElFormInstance>();

const initFormData: Certificate_configForm = {
  id: undefined,
  certificateTypeValue: undefined,
  title: undefined,
  validDays: undefined,
  expireWarningDays: undefined,
  status: undefined,
}
const data = reactive<PageData<Certificate_configForm, Certificate_configQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    status: undefined,
    params: {
    }
  },
  rules: {
id: [
      { required: true, message: "certificate_config不能为空", trigger: "blur" }
    ],
certificateTypeValue: [
      { required: true, message: "certificate_config不能为空", trigger: "blur" }
    ],
title: [
      { required: true, message: "certificate_config不能为空", trigger: "blur" }
    ],
validDays: [
      { required: true, message: "certificate_config不能为空", trigger: "blur" }
    ],
expireWarningDays: [
      { required: true, message: "certificate_config不能为空", trigger: "blur" }
    ],
status: [
      { required: true, message: "certificate_config不能为空", trigger: "change" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);
const { ids, single, multiple, handleSelectionChange } = useTableSelection<Certificate_configVO>(item => item.id);
const { dialog, resetForm: reset, openDialog, showDialog, closeDialog } = useFormDialog({
  form,
  formRef: certificate_configFormRef,
  initialFormData: initFormData
});

/** 查询CertificateConfig列表 */
const getList = async () => {
  await withLoading(async () => {
    const res = await listCertificate_config(queryParams.value);
    certificate_configList.value = res.data?.rows;
    total.value = res.data?.total;
  });
};

/** 取消按钮 */
const cancel = () => {
  reset();
  closeDialog();
};

/** 搜索按钮操作 */
const handleQuery = () => {
  queryParams.value.pageNum = 1;
  getList();
};

const { resetQuery } = useSearchReset({
  queryFormRef,
  queryParams,
  pageNumKey: 'pageNum',
  pageSizeKey: 'pageSize',
  initialPageSize: 10,
  resetExtras: () => {
  },
  afterReset: () => {
    handleQuery();
  }
});

/** 新增按钮操作 */
const handleAdd = () => {
  openDialog('添加CertificateConfig');
};

/** 修改按钮操作 */
const handleUpdate = async (row?: Partial<Certificate_configVO>) => {
  reset();
  const _id = row?.id || ids.value[0];
  const res = await getCertificate_config(_id);
  Object.assign(form.value, res.data);
  showDialog('修改CertificateConfig');
};

/** 提交按钮 */
const submitForm = () => {
  certificate_configFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updateCertificate_config(form.value).finally(() => (buttonLoading.value = false));
      } else {
        await addCertificate_config(form.value).finally(() => (buttonLoading.value = false));
      }
      modal.msgSuccess('操作成功');
      closeDialog();
      await getList();
    }
  });
};

/** 删除按钮操作 */
const handleDelete = async (row?: Partial<Certificate_configVO>) => {
  const _ids = row?.id || ids.value;
  await modal.confirm('是否确认删除CertificateConfig编号为"' + _ids + '"的数据项？');
  await delCertificate_config(_ids);
  modal.msgSuccess('删除成功');
  await getList();
};

/** 导出按钮操作 */
const handleExport = () => {
  requestDownload(
    'pxzs/certificate_config/export',
    {
      ...queryParams.value
    },
    `certificate_config_${new Date().getTime()}.xlsx`
  );
};



onMounted(() => {
  getList();
});
</script>

