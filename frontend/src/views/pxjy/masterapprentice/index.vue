<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="师傅姓名" prop="masterName">
            <el-input v-model="queryParams.masterName" placeholder="请输入师傅姓名" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="师傅所属机构" prop="masterOrgName">
            <el-input v-model="queryParams.masterOrgName" placeholder="请输入师傅所属机构" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="徒弟姓名" prop="apprenticeName">
            <el-input v-model="queryParams.apprenticeName" placeholder="请输入徒弟姓名" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="徒弟所属机构" prop="apprenticeOrgName">
            <el-input v-model="queryParams.apprenticeOrgName" placeholder="请输入徒弟所属机构" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="签订日期" prop="signDateDate">
            <el-input v-model="queryParams.signDateDate" placeholder="请输入签订日期" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="开始日期" prop="startDate">
            <el-input v-model="queryParams.startDate" placeholder="请输入开始日期" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>师徒制列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:masterApprentice:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:masterApprentice:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:masterApprentice:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:masterApprentice:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="师傅姓名" align="left" prop="masterName" show-overflow-tooltip min-width="120" />
        <el-table-column label="师傅所属机构" align="left" prop="masterOrgName" show-overflow-tooltip min-width="120" />
        <el-table-column label="徒弟姓名" align="left" prop="apprenticeName" show-overflow-tooltip min-width="120" />
        <el-table-column label="徒弟所属机构" align="left" prop="apprenticeOrgName" show-overflow-tooltip min-width="120" />
        <el-table-column label="签订日期" align="center" prop="signDateDate" width="120" show-overflow-tooltip />
        <el-table-column label="开始日期" align="center" prop="startDate" width="120" show-overflow-tooltip />
        <el-table-column label="结束日期" align="center" prop="endDate" width="120" show-overflow-tooltip />
        <el-table-column label="状态" align="center" prop="status" width="80">
          <template #default="scope">
            <el-tag :type="scope.row.status === '0' ? 'danger' : 'success'" size="small">
              {{ scope.row.status === '0' ? '停用' : '正常' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="创建人" align="left" prop="createBy" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:masterApprentice:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:masterApprentice:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="masterapprenticeFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="师傅姓名" prop="masterName">
              <el-input v-model="form.masterName" placeholder="请输入师傅姓名" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="师傅所属机构" prop="masterOrgName">
              <el-input v-model="form.masterOrgName" placeholder="请输入师傅所属机构" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="徒弟姓名" prop="apprenticeName">
              <el-input v-model="form.apprenticeName" placeholder="请输入徒弟姓名" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="徒弟所属机构" prop="apprenticeOrgName">
              <el-input v-model="form.apprenticeOrgName" placeholder="请输入徒弟所属机构" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="签订日期" prop="signDateDate">
              <el-date-picker v-model="form.signDateDate" type="datetime" placeholder="选择签订日期" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="开始日期" prop="startDate">
              <el-date-picker v-model="form.startDate" type="datetime" placeholder="选择开始日期" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="结束日期" prop="endDate">
              <el-date-picker v-model="form.endDate" type="datetime" placeholder="选择结束日期" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态" style="width: 100%">
                <el-option label="正常" value="0" />
                <el-option label="停用" value="1" />
              </el-select>
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

<script setup name="MasterApprentice" lang="ts">
import { addMasterApprentice, delMasterApprentice, getMasterApprentice, listMasterApprentice, updateMasterApprentice } from '@/api/scyh/masterapprentice';
import { MasterApprenticeForm, MasterApprenticeQuery, MasterApprenticeVO } from '@/api/scyh/masterapprentice/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<MasterApprenticeVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const masterapprenticeFormRef = ref<ElFormInstance>();

const initFormData: MasterApprenticeForm = {
    masterName: undefined,
    masterOrgName: undefined,
    apprenticeName: undefined,
    apprenticeOrgName: undefined,
    signDateDate: undefined,
    startDate: undefined,
    endDate: undefined,
    status: undefined,
}
const data = reactive<PageData<MasterApprenticeForm, MasterApprenticeQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    masterName: undefined,
    masterOrgName: undefined,
    apprenticeName: undefined,
    apprenticeOrgName: undefined,
    signDateDate: undefined,
    startDate: undefined,
  },
  rules: {
    masterName: [
      { required: true, message: "师傅姓名不能为空", trigger: "blur" }
    ],
    masterOrgName: [
      { required: true, message: "师傅所属机构不能为空", trigger: "blur" }
    ],
    apprenticeName: [
      { required: true, message: "徒弟姓名不能为空", trigger: "blur" }
    ],
    apprenticeOrgName: [
      { required: true, message: "徒弟所属机构不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listMasterApprentice(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; masterapprenticeFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: MasterApprenticeVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增师徒制'; dialog.visible = true; }
const handleUpdate = async (row?: MasterApprenticeVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getMasterApprentice(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改师徒制'; dialog.visible = true;
}
const submitForm = () => {
  masterapprenticeFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateMasterApprentice(form.value); }
        else { await addMasterApprentice(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: MasterApprenticeVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delMasterApprentice(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/masterapprentice/export', { ...queryParams.value }, '师徒制_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>