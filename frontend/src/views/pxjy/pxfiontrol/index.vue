<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="使用状态" prop="useStatus">
            <el-input v-model="queryParams.useStatus" placeholder="请输入使用状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="应复审剩余天数" prop="reviewRemainDays">
            <el-input v-model="queryParams.reviewRemainDays" placeholder="请输入应复审剩余天数" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="所属机构" prop="orgName">
            <el-input v-model="queryParams.orgName" placeholder="请输入所属机构" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="消防证书类别" prop="fireControlCertificate">
            <el-input v-model="queryParams.fireControlCertificate" placeholder="请输入消防证书类别" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="queryParams.name" placeholder="请输入姓名" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="性别" prop="gender">
            <el-input v-model="queryParams.gender" placeholder="请输入性别" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
          <el-button icon="Refresh" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card shadow="hover">
      <template #header>
        <el-row :gutter="2">
          <el-col :span="12"><h3>消防持证列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:PXFIONTROL:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:PXFIONTROL:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:PXFIONTROL:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:PXFIONTROL:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="消防持证表ID" align="center" prop="id" width="160" />
        <el-table-column label="使用状态" align="left" prop="useStatus" show-overflow-tooltip min-width="120" />
        <el-table-column label="应复审剩余天数" align="left" prop="reviewRemainDays" show-overflow-tooltip min-width="120" />
        <el-table-column label="所属机构" align="left" prop="orgName" show-overflow-tooltip min-width="120" />
        <el-table-column label="消防证书类别" align="left" prop="fireControlCertificate" show-overflow-tooltip min-width="120" />
        <el-table-column label="姓名" align="left" prop="name" show-overflow-tooltip min-width="120" />
        <el-table-column label="性别" align="left" prop="gender" show-overflow-tooltip min-width="120" />
        <el-table-column label="证书类型" align="left" prop="certificateType" show-overflow-tooltip min-width="120" />
        <el-table-column label="证号" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="发证机构" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="初领日期" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="有效期" align="center" prop="validDate" width="120" show-overflow-tooltip />
        <el-table-column label="应复审日期" align="left" prop="field4" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人" align="left" prop="createBy" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="证书同步时间" align="center" prop="certificateSyncTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:PXFIONTROL:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:PXFIONTROL:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="pxfiontrolFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="使用状态" prop="useStatus">
              <el-input v-model="form.useStatus" placeholder="请输入使用状态" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="应复审剩余天数" prop="reviewRemainDays">
              <el-input v-model="form.reviewRemainDays" placeholder="请输入应复审剩余天数" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="所属机构" prop="orgName">
              <el-input v-model="form.orgName" placeholder="请输入所属机构" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="消防证书类别" prop="fireControlCertificate">
              <el-input v-model="form.fireControlCertificate" placeholder="请输入消防证书类别" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="form.name" placeholder="请输入姓名" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="性别" prop="gender">
              <el-input v-model="form.gender" placeholder="请输入性别" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="证书类型" prop="certificateType">
              <el-input v-model="form.certificateType" placeholder="请输入证书类型" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="证号" prop="field">
              <el-input v-model="form.field" placeholder="请输入证号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="发证机构" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入发证机构" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="初领日期" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入初领日期" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="有效期" prop="validDate">
              <el-date-picker v-model="form.validDate" type="datetime" placeholder="选择有效期" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="应复审日期" prop="field4">
              <el-input v-model="form.field4" placeholder="请输入应复审日期" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="证书同步时间" prop="certificateSyncTime">
              <el-date-picker v-model="form.certificateSyncTime" type="datetime" placeholder="选择证书同步时间" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <el-button type="primary" :loading="buttonLoading" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="PXFIONTROL" lang="ts">
import { addPXFIONTROL, delPXFIONTROL, getPXFIONTROL, listPXFIONTROL, updatePXFIONTROL } from '@/api/scyh/pxfiontrol';
import { PXFIONTROLForm, PXFIONTROLQuery, PXFIONTROLVO } from '@/api/scyh/pxfiontrol/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<PXFIONTROLVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const pxfiontrolFormRef = ref<ElFormInstance>();

const initFormData: PXFIONTROLForm = {
    useStatus: undefined,
    reviewRemainDays: undefined,
    orgName: undefined,
    fireControlCertificate: undefined,
    name: undefined,
    gender: undefined,
    certificateType: undefined,
    field: undefined,
    field2: undefined,
    field3: undefined,
    validDate: undefined,
    field4: undefined,
    certificateSyncTime: undefined,
}
const data = reactive<PageData<PXFIONTROLForm, PXFIONTROLQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    useStatus: undefined,
    reviewRemainDays: undefined,
    orgName: undefined,
    fireControlCertificate: undefined,
    name: undefined,
    gender: undefined,
  },
  rules: {
    useStatus: [
      { required: true, message: "使用状态不能为空", trigger: "blur" }
    ],
    reviewRemainDays: [
      { required: true, message: "应复审剩余天数不能为空", trigger: "blur" }
    ],
    orgName: [
      { required: true, message: "所属机构不能为空", trigger: "blur" }
    ],
    fireControlCertificate: [
      { required: true, message: "消防证书类别不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listPXFIONTROL(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; pxfiontrolFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: PXFIONTROLVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增消防持证'; dialog.visible = true; }
const handleUpdate = async (row?: PXFIONTROLVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getPXFIONTROL(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改消防持证'; dialog.visible = true;
}
const submitForm = () => {
  pxfiontrolFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updatePXFIONTROL(form.value); }
        else { await addPXFIONTROL(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: PXFIONTROLVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delPXFIONTROL(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/pxfiontrol/export', { ...queryParams.value }, '消防持证_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>