<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="目标名称" prop="targetName">
            <el-input v-model="queryParams.targetName" placeholder="请输入目标名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="年度" prop="year">
            <el-input v-model="queryParams.year" placeholder="请输入年度" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="单位名称" prop="unitName">
            <el-input v-model="queryParams.unitName" placeholder="请输入单位名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="指标数量" prop="indicatorCount">
            <el-input v-model="queryParams.indicatorCount" placeholder="请输入指标数量" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="目标责任书" prop="targetDuty">
            <el-input v-model="queryParams.targetDuty" placeholder="请输入目标责任书" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="年度目标完成情况" prop="yearTarget">
            <el-input v-model="queryParams.yearTarget" placeholder="请输入年度目标完成情况" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>企业年度目标列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:MBMBTARGET:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:MBMBTARGET:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:MBMBTARGET:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:MBMBTARGET:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="年度目标ID" align="center" prop="id" width="160" />
        <el-table-column label="目标名称" align="left" prop="targetName" show-overflow-tooltip min-width="120" />
        <el-table-column label="年度" align="left" prop="year" show-overflow-tooltip min-width="120" />
        <el-table-column label="单位名称" align="left" prop="unitName" show-overflow-tooltip min-width="120" />
        <el-table-column label="指标数量" align="left" prop="indicatorCount" show-overflow-tooltip min-width="120" />
        <el-table-column label="目标责任书" align="left" prop="targetDuty" show-overflow-tooltip min-width="120" />
        <el-table-column label="年度目标完成情况" align="left" prop="yearTarget" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人工号" align="left" prop="createByEmpCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:MBMBTARGET:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:MBMBTARGET:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="mbmbtargetFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="目标名称" prop="targetName">
              <el-input v-model="form.targetName" placeholder="请输入目标名称" />
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
            <el-form-item label="单位名称" prop="unitName">
              <el-input v-model="form.unitName" placeholder="请输入单位名称" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="指标数量" prop="indicatorCount">
              <el-input v-model="form.indicatorCount" placeholder="请输入指标数量" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="目标责任书" prop="targetDuty">
              <el-input v-model="form.targetDuty" placeholder="请输入目标责任书" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="年度目标完成情况" prop="yearTarget">
              <el-input v-model="form.yearTarget" placeholder="请输入年度目标完成情况" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="创建人工号" prop="createByEmpCode">
              <el-input v-model="form.createByEmpCode" placeholder="请输入创建人工号" />
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

<script setup name="MBMBTARGET" lang="ts">
import { addMBMBTARGET, delMBMBTARGET, getMBMBTARGET, listMBMBTARGET, updateMBMBTARGET } from '@/api/scyh/mbmbtarget';
import { MBMBTARGETForm, MBMBTARGETQuery, MBMBTARGETVO } from '@/api/scyh/mbmbtarget/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<MBMBTARGETVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const mbmbtargetFormRef = ref<ElFormInstance>();

const initFormData: MBMBTARGETForm = {
    targetName: undefined,
    year: undefined,
    unitName: undefined,
    indicatorCount: undefined,
    targetDuty: undefined,
    yearTarget: undefined,
    createByEmpCode: undefined,
}
const data = reactive<PageData<MBMBTARGETForm, MBMBTARGETQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    targetName: undefined,
    year: undefined,
    unitName: undefined,
    indicatorCount: undefined,
    targetDuty: undefined,
    yearTarget: undefined,
  },
  rules: {
    targetName: [
      { required: true, message: "目标名称不能为空", trigger: "blur" }
    ],
    year: [
      { required: true, message: "年度不能为空", trigger: "blur" }
    ],
    unitName: [
      { required: true, message: "单位名称不能为空", trigger: "blur" }
    ],
    indicatorCount: [
      { required: true, message: "指标数量不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listMBMBTARGET(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; mbmbtargetFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: MBMBTARGETVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增企业年度目标'; dialog.visible = true; }
const handleUpdate = async (row?: MBMBTARGETVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getMBMBTARGET(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改企业年度目标'; dialog.visible = true;
}
const submitForm = () => {
  mbmbtargetFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateMBMBTARGET(form.value); }
        else { await addMBMBTARGET(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: MBMBTARGETVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delMBMBTARGET(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/mbmbtarget/export', { ...queryParams.value }, '企业年度目标_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>