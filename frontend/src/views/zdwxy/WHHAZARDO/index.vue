<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="目录序号" prop="articleCode">
            <el-input v-model="queryParams.articleCode" placeholder="请输入目录序号" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="品名" prop="articleName">
            <el-input v-model="queryParams.articleName" placeholder="请输入品名" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="别名" prop="alias">
            <el-input v-model="queryParams.alias" placeholder="请输入别名" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="CAS号" prop="casNo">
            <el-input v-model="queryParams.casNo" placeholder="请输入CAS号" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="危险性" prop="danger">
            <el-input v-model="queryParams.danger" placeholder="请输入危险性" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="化学品分类" prop="chemicalCategory">
            <el-input v-model="queryParams.chemicalCategory" placeholder="请输入化学品分类" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>危化品目录-其他列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:WHHAZARDO:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:WHHAZARDO:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:WHHAZARDO:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:WHHAZARDO:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="危化品目录ID" align="center" prop="id" width="160" />
        <el-table-column label="目录序号" align="left" prop="articleCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="品名" align="left" prop="articleName" show-overflow-tooltip min-width="120" />
        <el-table-column label="别名" align="left" prop="alias" show-overflow-tooltip min-width="120" />
        <el-table-column label="CAS号" align="left" prop="casNo" show-overflow-tooltip min-width="120" />
        <el-table-column label="危险性" align="left" prop="danger" show-overflow-tooltip min-width="120" />
        <el-table-column label="化学品分类" align="left" prop="chemicalCategory" show-overflow-tooltip min-width="120" />
        <el-table-column label="剧毒属性" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="重点监管品名" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="易制爆品" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="易制毒品" align="left" prop="field4" show-overflow-tooltip min-width="120" />
        <el-table-column label="其他" align="left" prop="field5" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:WHHAZARDO:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:WHHAZARDO:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="whhazardoFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="目录序号" prop="articleCode">
              <el-input v-model="form.articleCode" placeholder="请输入目录序号" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="品名" prop="articleName">
              <el-input v-model="form.articleName" placeholder="请输入品名" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="别名" prop="alias">
              <el-input v-model="form.alias" placeholder="请输入别名" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="CAS号" prop="casNo">
              <el-input v-model="form.casNo" placeholder="请输入CAS号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="危险性" prop="danger">
              <el-input v-model="form.danger" placeholder="请输入危险性" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="化学品分类" prop="chemicalCategory">
              <el-input v-model="form.chemicalCategory" placeholder="请输入化学品分类" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="剧毒属性" prop="field">
              <el-input v-model="form.field" placeholder="请输入剧毒属性" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="重点监管品名" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入重点监管品名" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="易制爆品" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入易制爆品" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="易制毒品" prop="field4">
              <el-input v-model="form.field4" placeholder="请输入易制毒品" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="其他" prop="field5">
              <el-input v-model="form.field5" placeholder="请输入其他" />
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

<script setup name="WHHAZARDO" lang="ts">
import { addWHHAZARDO, delWHHAZARDO, getWHHAZARDO, listWHHAZARDO, updateWHHAZARDO } from '@/api/scyh/whhazardo';
import { WHHAZARDOForm, WHHAZARDOQuery, WHHAZARDOVO } from '@/api/scyh/whhazardo/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<WHHAZARDOVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const whhazardoFormRef = ref<ElFormInstance>();

const initFormData: WHHAZARDOForm = {
    articleCode: undefined,
    articleName: undefined,
    alias: undefined,
    casNo: undefined,
    danger: undefined,
    chemicalCategory: undefined,
    field: undefined,
    field2: undefined,
    field3: undefined,
    field4: undefined,
    field5: undefined,
}
const data = reactive<PageData<WHHAZARDOForm, WHHAZARDOQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    articleCode: undefined,
    articleName: undefined,
    alias: undefined,
    casNo: undefined,
    danger: undefined,
    chemicalCategory: undefined,
  },
  rules: {
    articleCode: [
      { required: true, message: "目录序号不能为空", trigger: "blur" }
    ],
    articleName: [
      { required: true, message: "品名不能为空", trigger: "blur" }
    ],
    alias: [
      { required: true, message: "别名不能为空", trigger: "blur" }
    ],
    casNo: [
      { required: true, message: "CAS号不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listWHHAZARDO(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; whhazardoFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: WHHAZARDOVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增危化品目录-其他'; dialog.visible = true; }
const handleUpdate = async (row?: WHHAZARDOVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getWHHAZARDO(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改危化品目录-其他'; dialog.visible = true;
}
const submitForm = () => {
  whhazardoFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateWHHAZARDO(form.value); }
        else { await addWHHAZARDO(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: WHHAZARDOVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delWHHAZARDO(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/whhazardo/export', { ...queryParams.value }, '危化品目录-其他_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>