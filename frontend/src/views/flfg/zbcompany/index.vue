<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="专业管理部门" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入专业管理部门" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="法律法规及其他要求名称" prop="lawRegulation">
            <el-input v-model="queryParams.lawRegulation" placeholder="请输入法律法规及其他要求名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="对应公司级管理文件" prop="field2">
            <el-input v-model="queryParams.field2" placeholder="请输入对应公司级管理文件" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="填写日期" prop="field3">
            <el-input v-model="queryParams.field3" placeholder="请输入填写日期" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>转化公司管理规定列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:ZBCOMPANY:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:ZBCOMPANY:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:ZBCOMPANY:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:ZBCOMPANY:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="转化公司管理规定ID" align="center" prop="id" width="160" />
        <el-table-column label="专业管理部门" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="法律法规及其他要求名称" align="left" prop="lawRegulation" show-overflow-tooltip min-width="120" />
        <el-table-column label="对应公司级管理文件" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="填写日期" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:ZBCOMPANY:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:ZBCOMPANY:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="zbcompanyFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="专业管理部门" prop="field">
              <el-input v-model="form.field" placeholder="请输入专业管理部门" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="法律法规及其他要求名称" prop="lawRegulation">
              <el-input v-model="form.lawRegulation" placeholder="请输入法律法规及其他要求名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="对应公司级管理文件" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入对应公司级管理文件" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="填写日期" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入填写日期" />
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

<script setup name="ZBCOMPANY" lang="ts">
import { addZBCOMPANY, delZBCOMPANY, getZBCOMPANY, listZBCOMPANY, updateZBCOMPANY } from '@/api/scyh/zbcompany';
import { ZBCOMPANYForm, ZBCOMPANYQuery, ZBCOMPANYVO } from '@/api/scyh/zbcompany/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<ZBCOMPANYVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const zbcompanyFormRef = ref<ElFormInstance>();

const initFormData: ZBCOMPANYForm = {
    field: undefined,
    lawRegulation: undefined,
    field2: undefined,
    field3: undefined,
}
const data = reactive<PageData<ZBCOMPANYForm, ZBCOMPANYQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    field: undefined,
    lawRegulation: undefined,
    field2: undefined,
    field3: undefined,
  },
  rules: {
    field: [
      { required: true, message: "专业管理部门不能为空", trigger: "blur" }
    ],
    lawRegulation: [
      { required: true, message: "法律法规及其他要求名称不能为空", trigger: "blur" }
    ],
    field2: [
      { required: true, message: "对应公司级管理文件不能为空", trigger: "blur" }
    ],
    field3: [
      { required: true, message: "填写日期不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listZBCOMPANY(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; zbcompanyFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: ZBCOMPANYVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增转化公司管理规定'; dialog.visible = true; }
const handleUpdate = async (row?: ZBCOMPANYVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getZBCOMPANY(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改转化公司管理规定'; dialog.visible = true;
}
const submitForm = () => {
  zbcompanyFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateZBCOMPANY(form.value); }
        else { await addZBCOMPANY(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: ZBCOMPANYVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delZBCOMPANY(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/zbcompany/export', { ...queryParams.value }, '转化公司管理规定_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>