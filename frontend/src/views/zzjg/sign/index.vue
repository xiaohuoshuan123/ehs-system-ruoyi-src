<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="签署状态" prop="signStatus">
            <el-input v-model="queryParams.signStatus" placeholder="请输入签署状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="乙方工号" prop="partyBEmpCode">
            <el-input v-model="queryParams.partyBEmpCode" placeholder="请输入乙方工号" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="甲方工号" prop="partyAEmpCode">
            <el-input v-model="queryParams.partyAEmpCode" placeholder="请输入甲方工号" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="乙方姓名" prop="partyBName">
            <el-input v-model="queryParams.partyBName" placeholder="请输入乙方姓名" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="部门" prop="dept">
            <el-input v-model="queryParams.dept" placeholder="请输入部门" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="甲方" prop="partyA">
            <el-input v-model="queryParams.partyA" placeholder="请输入甲方" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>责任书签署列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:sign:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:sign:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:sign:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:sign:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="签署状态" align="left" prop="signStatus" show-overflow-tooltip min-width="120" />
        <el-table-column label="乙方工号" align="left" prop="partyBEmpCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="甲方工号" align="left" prop="partyAEmpCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="乙方姓名" align="left" prop="partyBName" show-overflow-tooltip min-width="120" />
        <el-table-column label="部门" align="left" prop="dept" show-overflow-tooltip min-width="120" />
        <el-table-column label="甲方" align="left" prop="partyA" show-overflow-tooltip min-width="120" />
        <el-table-column label="模板" align="left" prop="template" show-overflow-tooltip min-width="120" />
        <el-table-column label="签署时间" align="center" prop="signTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:sign:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:sign:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="signFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="签署状态" prop="signStatus">
              <el-input v-model="form.signStatus" placeholder="请输入签署状态" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="乙方工号" prop="partyBEmpCode">
              <el-input v-model="form.partyBEmpCode" placeholder="请输入乙方工号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="甲方工号" prop="partyAEmpCode">
              <el-input v-model="form.partyAEmpCode" placeholder="请输入甲方工号" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="乙方姓名" prop="partyBName">
              <el-input v-model="form.partyBName" placeholder="请输入乙方姓名" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="部门" prop="dept">
              <el-input v-model="form.dept" placeholder="请输入部门" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="甲方" prop="partyA">
              <el-input v-model="form.partyA" placeholder="请输入甲方" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="模板" prop="template">
              <el-input v-model="form.template" placeholder="请输入模板" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="签署时间" prop="signTime">
              <el-date-picker v-model="form.signTime" type="datetime" placeholder="选择签署时间" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
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

<script setup name="Sign" lang="ts">
import { addSign, delSign, getSign, listSign, updateSign } from '@/api/scyh/sign';
import { SignForm, SignQuery, SignVO } from '@/api/scyh/sign/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<SignVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const signFormRef = ref<ElFormInstance>();

const initFormData: SignForm = {
    signStatus: undefined,
    partyBEmpCode: undefined,
    partyAEmpCode: undefined,
    partyBName: undefined,
    dept: undefined,
    partyA: undefined,
    template: undefined,
    signTime: undefined,
}
const data = reactive<PageData<SignForm, SignQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    signStatus: undefined,
    partyBEmpCode: undefined,
    partyAEmpCode: undefined,
    partyBName: undefined,
    dept: undefined,
    partyA: undefined,
  },
  rules: {
    signStatus: [
      { required: true, message: "签署状态不能为空", trigger: "blur" }
    ],
    partyBEmpCode: [
      { required: true, message: "乙方工号不能为空", trigger: "blur" }
    ],
    partyAEmpCode: [
      { required: true, message: "甲方工号不能为空", trigger: "blur" }
    ],
    partyBName: [
      { required: true, message: "乙方姓名不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listSign(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; signFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: SignVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增责任书签署'; dialog.visible = true; }
const handleUpdate = async (row?: SignVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getSign(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改责任书签署'; dialog.visible = true;
}
const submitForm = () => {
  signFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateSign(form.value); }
        else { await addSign(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: SignVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delSign(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/sign/export', { ...queryParams.value }, '责任书签署_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>