<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="完成状态" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入完成状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="培训年度" prop="trainYear">
            <el-input v-model="queryParams.trainYear" placeholder="请输入培训年度" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="所属机构" prop="orgName">
            <el-input v-model="queryParams.orgName" placeholder="请输入所属机构" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="在职状态" prop="employedStatus">
            <el-input v-model="queryParams.employedStatus" placeholder="请输入在职状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="参训人员" prop="participantPerson">
            <el-input v-model="queryParams.participantPerson" placeholder="请输入参训人员" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="部门" prop="dept">
            <el-input v-model="queryParams.dept" placeholder="请输入部门" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>再教育培训列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:educationTrainingMst:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:educationTrainingMst:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:educationTrainingMst:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:educationTrainingMst:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="再教育培训记录ID" align="center" prop="id" width="160" />
        <el-table-column label="完成状态" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="培训年度" align="left" prop="trainYear" show-overflow-tooltip min-width="120" />
        <el-table-column label="所属机构" align="left" prop="orgName" show-overflow-tooltip min-width="120" />
        <el-table-column label="在职状态" align="left" prop="employedStatus" show-overflow-tooltip min-width="120" />
        <el-table-column label="参训人员" align="left" prop="participantPerson" show-overflow-tooltip min-width="120" />
        <el-table-column label="部门" align="left" prop="dept" show-overflow-tooltip min-width="120" />
        <el-table-column label="岗位" align="left" prop="post" show-overflow-tooltip min-width="120" />
        <el-table-column label="培训次数" align="left" prop="train" show-overflow-tooltip min-width="120" />
        <el-table-column label="学时要求" align="left" prop="hours" show-overflow-tooltip min-width="120" />
        <el-table-column label="实际学时" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="完成率" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:educationTrainingMst:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:educationTrainingMst:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="educationtrainingmstFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="完成状态" prop="field">
              <el-input v-model="form.field" placeholder="请输入完成状态" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="培训年度" prop="trainYear">
              <el-input v-model="form.trainYear" placeholder="请输入培训年度" />
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
            <el-form-item label="在职状态" prop="employedStatus">
              <el-input v-model="form.employedStatus" placeholder="请输入在职状态" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="参训人员" prop="participantPerson">
              <el-input v-model="form.participantPerson" placeholder="请输入参训人员" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="部门" prop="dept">
              <el-input v-model="form.dept" placeholder="请输入部门" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="岗位" prop="post">
              <el-input v-model="form.post" placeholder="请输入岗位" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="培训次数" prop="train">
              <el-input v-model="form.train" placeholder="请输入培训次数" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="学时要求" prop="hours">
              <el-input v-model="form.hours" placeholder="请输入学时要求" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="实际学时" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入实际学时" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="完成率" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入完成率" />
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

<script setup name="EducationTrainingMst" lang="ts">
import { addEducationTrainingMst, delEducationTrainingMst, getEducationTrainingMst, listEducationTrainingMst, updateEducationTrainingMst } from '@/api/scyh/educationtrainingmst';
import { EducationTrainingMstForm, EducationTrainingMstQuery, EducationTrainingMstVO } from '@/api/scyh/educationtrainingmst/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<EducationTrainingMstVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const educationtrainingmstFormRef = ref<ElFormInstance>();

const initFormData: EducationTrainingMstForm = {
    field: undefined,
    trainYear: undefined,
    orgName: undefined,
    employedStatus: undefined,
    participantPerson: undefined,
    dept: undefined,
    post: undefined,
    train: undefined,
    hours: undefined,
    field2: undefined,
    field3: undefined,
}
const data = reactive<PageData<EducationTrainingMstForm, EducationTrainingMstQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    field: undefined,
    trainYear: undefined,
    orgName: undefined,
    employedStatus: undefined,
    participantPerson: undefined,
    dept: undefined,
  },
  rules: {
    field: [
      { required: true, message: "完成状态不能为空", trigger: "blur" }
    ],
    trainYear: [
      { required: true, message: "培训年度不能为空", trigger: "blur" }
    ],
    orgName: [
      { required: true, message: "所属机构不能为空", trigger: "blur" }
    ],
    employedStatus: [
      { required: true, message: "在职状态不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listEducationTrainingMst(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; educationtrainingmstFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: EducationTrainingMstVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增再教育培训'; dialog.visible = true; }
const handleUpdate = async (row?: EducationTrainingMstVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getEducationTrainingMst(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改再教育培训'; dialog.visible = true;
}
const submitForm = () => {
  educationtrainingmstFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateEducationTrainingMst(form.value); }
        else { await addEducationTrainingMst(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: EducationTrainingMstVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delEducationTrainingMst(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/educationtrainingmst/export', { ...queryParams.value }, '再教育培训_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>