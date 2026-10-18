<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="机构名称" prop="orgName">
            <el-input v-model="queryParams.orgName" placeholder="请输入机构名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="安全管理机构" prop="safety">
            <el-input v-model="queryParams.safety" placeholder="请输入安全管理机构" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="人员数量" prop="personCount">
            <el-input v-model="queryParams.personCount" placeholder="请输入人员数量" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>安委会架构列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:JGAWJG:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:JGAWJG:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:JGAWJG:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:JGAWJG:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="安委会架构ID" align="center" prop="id" width="160" />
        <el-table-column label="机构名称" align="left" prop="orgName" show-overflow-tooltip min-width="120" />
        <el-table-column label="安全管理机构" align="left" prop="safety" show-overflow-tooltip min-width="120" />
        <el-table-column label="人员数量" align="left" prop="personCount" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人" align="left" prop="createBy" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:JGAWJG:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:JGAWJG:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="jgawjgFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="机构名称" prop="orgName">
              <el-input v-model="form.orgName" placeholder="请输入机构名称" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="安全管理机构" prop="safety">
              <el-input v-model="form.safety" placeholder="请输入安全管理机构" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="人员数量" prop="personCount">
              <el-input v-model="form.personCount" placeholder="请输入人员数量" />
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

<script setup name="JGAWJG" lang="ts">
import { addJGAWJG, delJGAWJG, getJGAWJG, listJGAWJG, updateJGAWJG } from '@/api/scyh/jgawjg';
import { JGAWJGForm, JGAWJGQuery, JGAWJGVO } from '@/api/scyh/jgawjg/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<JGAWJGVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const jgawjgFormRef = ref<ElFormInstance>();

const initFormData: JGAWJGForm = {
    orgName: undefined,
    safety: undefined,
    personCount: undefined,
}
const data = reactive<PageData<JGAWJGForm, JGAWJGQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    orgName: undefined,
    safety: undefined,
    personCount: undefined,
  },
  rules: {
    orgName: [
      { required: true, message: "机构名称不能为空", trigger: "blur" }
    ],
    safety: [
      { required: true, message: "安全管理机构不能为空", trigger: "blur" }
    ],
    personCount: [
      { required: true, message: "人员数量不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listJGAWJG(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; jgawjgFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: JGAWJGVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增安委会架构'; dialog.visible = true; }
const handleUpdate = async (row?: JGAWJGVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getJGAWJG(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改安委会架构'; dialog.visible = true;
}
const submitForm = () => {
  jgawjgFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateJGAWJG(form.value); }
        else { await addJGAWJG(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: JGAWJGVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delJGAWJG(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/jgawjg/export', { ...queryParams.value }, '安委会架构_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>