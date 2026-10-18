<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="模板" prop="template">
            <el-input v-model="queryParams.template" placeholder="请输入模板" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="年度" prop="year">
            <el-input v-model="queryParams.year" placeholder="请输入年度" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="甲方" prop="partyA">
            <el-input v-model="queryParams.partyA" placeholder="请输入甲方" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="乙方签字" prop="partyB">
            <el-input v-model="queryParams.partyB" placeholder="请输入乙方签字" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="甲方签字" prop="partyA2">
            <el-input v-model="queryParams.partyA2" placeholder="请输入甲方签字" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="乙方人数" prop="partyB2">
            <el-input v-model="queryParams.partyB2" placeholder="请输入乙方人数" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>责任书下发列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:dispatch:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:dispatch:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:dispatch:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:dispatch:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="模板" align="left" prop="template" show-overflow-tooltip min-width="120" />
        <el-table-column label="年度" align="left" prop="year" show-overflow-tooltip min-width="120" />
        <el-table-column label="甲方" align="left" prop="partyA" show-overflow-tooltip min-width="120" />
        <el-table-column label="乙方签字" align="left" prop="partyB" show-overflow-tooltip min-width="120" />
        <el-table-column label="甲方签字" align="left" prop="partyA2" show-overflow-tooltip min-width="120" />
        <el-table-column label="乙方人数" align="left" prop="partyB2" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人" align="left" prop="createBy" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:dispatch:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:dispatch:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="dispatchFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="模板" prop="template">
              <el-input v-model="form.template" placeholder="请输入模板" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="年度" prop="year">
              <el-input v-model="form.year" placeholder="请输入年度" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="甲方" prop="partyA">
              <el-input v-model="form.partyA" placeholder="请输入甲方" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="乙方签字" prop="partyB">
              <el-input v-model="form.partyB" placeholder="请输入乙方签字" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="甲方签字" prop="partyA2">
              <el-input v-model="form.partyA2" placeholder="请输入甲方签字" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="乙方人数" prop="partyB2">
              <el-input v-model="form.partyB2" placeholder="请输入乙方人数" />
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

<script setup name="Dispatch" lang="ts">
import { addDispatch, delDispatch, getDispatch, listDispatch, updateDispatch } from '@/api/scyh/dispatch';
import { DispatchForm, DispatchQuery, DispatchVO } from '@/api/scyh/dispatch/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<DispatchVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const dispatchFormRef = ref<ElFormInstance>();

const initFormData: DispatchForm = {
    template: undefined,
    year: undefined,
    partyA: undefined,
    partyB: undefined,
    partyA2: undefined,
    partyB2: undefined,
}
const data = reactive<PageData<DispatchForm, DispatchQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    template: undefined,
    year: undefined,
    partyA: undefined,
    partyB: undefined,
    partyA2: undefined,
    partyB2: undefined,
  },
  rules: {
    template: [
      { required: true, message: "模板不能为空", trigger: "blur" }
    ],
    year: [
      { required: true, message: "年度不能为空", trigger: "blur" }
    ],
    partyA: [
      { required: true, message: "甲方不能为空", trigger: "blur" }
    ],
    partyB: [
      { required: true, message: "乙方签字不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listDispatch(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; dispatchFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: DispatchVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增责任书下发'; dialog.visible = true; }
const handleUpdate = async (row?: DispatchVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getDispatch(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改责任书下发'; dialog.visible = true;
}
const submitForm = () => {
  dispatchFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateDispatch(form.value); }
        else { await addDispatch(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: DispatchVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delDispatch(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/dispatch/export', { ...queryParams.value }, '责任书下发_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>