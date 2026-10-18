<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="处理状态" prop="handleStatus">
            <el-input v-model="queryParams.handleStatus" placeholder="请输入处理状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="违章时间" prop="breachTime">
            <el-input v-model="queryParams.breachTime" placeholder="请输入违章时间" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="违章级别" prop="breachLevel">
            <el-input v-model="queryParams.breachLevel" placeholder="请输入违章级别" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="违章部门" prop="breachDept">
            <el-input v-model="queryParams.breachDept" placeholder="请输入违章部门" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="检查机构" prop="checkOrg">
            <el-input v-model="queryParams.checkOrg" placeholder="请输入检查机构" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="处理机构" prop="handleOrg">
            <el-input v-model="queryParams.handleOrg" placeholder="请输入处理机构" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>违章处理列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:WZCLBREA:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:WZCLBREA:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:WZCLBREA:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:WZCLBREA:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="违章处理主键" align="center" prop="id" width="160" />
        <el-table-column label="处理状态" align="left" prop="handleStatus" show-overflow-tooltip min-width="120" />
        <el-table-column label="违章时间" align="center" prop="breachTime" width="120" show-overflow-tooltip />
        <el-table-column label="违章级别" align="left" prop="breachLevel" show-overflow-tooltip min-width="120" />
        <el-table-column label="违章部门" align="left" prop="breachDept" show-overflow-tooltip min-width="120" />
        <el-table-column label="检查机构" align="left" prop="checkOrg" show-overflow-tooltip min-width="120" />
        <el-table-column label="处理机构" align="left" prop="handleOrg" show-overflow-tooltip min-width="120" />
        <el-table-column label="违章描述" align="left" prop="breachDesc" show-overflow-tooltip min-width="120" />
        <el-table-column label="违章人员性质" align="left" prop="personNature" show-overflow-tooltip min-width="120" />
        <el-table-column label="违章人员" align="left" prop="breachPerson" show-overflow-tooltip min-width="120" />
        <el-table-column label="检查人员" align="left" prop="checker" show-overflow-tooltip min-width="120" />
        <el-table-column label="处理人员" align="left" prop="handler" show-overflow-tooltip min-width="120" />
        <el-table-column label="处理日期" align="center" prop="handleDate" width="120" show-overflow-tooltip />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:WZCLBREA:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:WZCLBREA:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="wzclbreaFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="处理状态" prop="handleStatus">
              <el-input v-model="form.handleStatus" placeholder="请输入处理状态" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="违章时间" prop="breachTime">
              <el-date-picker v-model="form.breachTime" type="datetime" placeholder="选择违章时间" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="违章级别" prop="breachLevel">
              <el-input v-model="form.breachLevel" placeholder="请输入违章级别" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="违章部门" prop="breachDept">
              <el-input v-model="form.breachDept" placeholder="请输入违章部门" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="检查机构" prop="checkOrg">
              <el-input v-model="form.checkOrg" placeholder="请输入检查机构" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="处理机构" prop="handleOrg">
              <el-input v-model="form.handleOrg" placeholder="请输入处理机构" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="违章描述" prop="breachDesc">
              <el-input v-model="form.breachDesc" placeholder="请输入违章描述" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="违章人员性质" prop="personNature">
              <el-input v-model="form.personNature" placeholder="请输入违章人员性质" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="违章人员" prop="breachPerson">
              <el-input v-model="form.breachPerson" placeholder="请输入违章人员" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="检查人员" prop="checker">
              <el-input v-model="form.checker" placeholder="请输入检查人员" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="处理人员" prop="handler">
              <el-input v-model="form.handler" placeholder="请输入处理人员" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="处理日期" prop="handleDate">
              <el-date-picker v-model="form.handleDate" type="datetime" placeholder="选择处理日期" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
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

<script setup name="WZCLBREA" lang="ts">
import { addWZCLBREA, delWZCLBREA, getWZCLBREA, listWZCLBREA, updateWZCLBREA } from '@/api/scyh/wzclbrea';
import { WZCLBREAForm, WZCLBREAQuery, WZCLBREAVO } from '@/api/scyh/wzclbrea/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<WZCLBREAVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const wzclbreaFormRef = ref<ElFormInstance>();

const initFormData: WZCLBREAForm = {
    handleStatus: undefined,
    breachTime: undefined,
    breachLevel: undefined,
    breachDept: undefined,
    checkOrg: undefined,
    handleOrg: undefined,
    breachDesc: undefined,
    personNature: undefined,
    breachPerson: undefined,
    checker: undefined,
    handler: undefined,
    handleDate: undefined,
}
const data = reactive<PageData<WZCLBREAForm, WZCLBREAQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    handleStatus: undefined,
    breachTime: undefined,
    breachLevel: undefined,
    breachDept: undefined,
    checkOrg: undefined,
    handleOrg: undefined,
  },
  rules: {
    handleStatus: [
      { required: true, message: "处理状态不能为空", trigger: "blur" }
    ],
    breachTime: [
      { required: true, message: "违章时间不能为空", trigger: "blur" }
    ],
    breachLevel: [
      { required: true, message: "违章级别不能为空", trigger: "blur" }
    ],
    breachDept: [
      { required: true, message: "违章部门不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listWZCLBREA(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; wzclbreaFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: WZCLBREAVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增违章处理'; dialog.visible = true; }
const handleUpdate = async (row?: WZCLBREAVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getWZCLBREA(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改违章处理'; dialog.visible = true;
}
const submitForm = () => {
  wzclbreaFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateWZCLBREA(form.value); }
        else { await addWZCLBREA(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: WZCLBREAVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delWZCLBREA(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/wzclbrea/export', { ...queryParams.value }, '违章处理_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>