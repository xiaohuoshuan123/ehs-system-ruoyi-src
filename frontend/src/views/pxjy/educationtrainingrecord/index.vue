<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="实施机构" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入实施机构" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="培训年度" prop="trainYear">
            <el-input v-model="queryParams.trainYear" placeholder="请输入培训年度" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="课程名称" prop="courseName">
            <el-input v-model="queryParams.courseName" placeholder="请输入课程名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="开始时间" prop="startTime">
            <el-input v-model="queryParams.startTime" placeholder="请输入开始时间" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="结束时间" prop="endTime">
            <el-input v-model="queryParams.endTime" placeholder="请输入结束时间" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="培训学时" prop="trainHours">
            <el-input v-model="queryParams.trainHours" placeholder="请输入培训学时" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>培训记录列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:educationTrainingRecord:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:educationTrainingRecord:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:educationTrainingRecord:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:educationTrainingRecord:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="记录id" align="center" prop="id" width="160" />
        <el-table-column label="实施机构" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="培训年度" align="left" prop="trainYear" show-overflow-tooltip min-width="120" />
        <el-table-column label="课程名称" align="left" prop="courseName" show-overflow-tooltip min-width="120" />
        <el-table-column label="开始时间" align="center" prop="startTime" width="120" show-overflow-tooltip />
        <el-table-column label="结束时间" align="center" prop="endTime" width="120" show-overflow-tooltip />
        <el-table-column label="培训学时" align="left" prop="trainHours" show-overflow-tooltip min-width="120" />
        <el-table-column label="培训成绩" align="left" prop="trainScore" show-overflow-tooltip min-width="120" />
        <el-table-column label="学习方式" align="left" prop="study" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人" align="left" prop="createBy" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:educationTrainingRecord:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:educationTrainingRecord:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="educationtrainingrecordFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="实施机构" prop="field">
              <el-input v-model="form.field" placeholder="请输入实施机构" />
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
            <el-form-item label="课程名称" prop="courseName">
              <el-input v-model="form.courseName" placeholder="请输入课程名称" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="开始时间" prop="startTime">
              <el-date-picker v-model="form.startTime" type="datetime" placeholder="选择开始时间" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="结束时间" prop="endTime">
              <el-date-picker v-model="form.endTime" type="datetime" placeholder="选择结束时间" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="培训学时" prop="trainHours">
              <el-input v-model="form.trainHours" placeholder="请输入培训学时" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="培训成绩" prop="trainScore">
              <el-input v-model="form.trainScore" placeholder="请输入培训成绩" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="学习方式" prop="study">
              <el-input v-model="form.study" placeholder="请输入学习方式" />
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

<script setup name="EducationTrainingRecord" lang="ts">
import { addEducationTrainingRecord, delEducationTrainingRecord, getEducationTrainingRecord, listEducationTrainingRecord, updateEducationTrainingRecord } from '@/api/scyh/educationtrainingrecord';
import { EducationTrainingRecordForm, EducationTrainingRecordQuery, EducationTrainingRecordVO } from '@/api/scyh/educationtrainingrecord/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<EducationTrainingRecordVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const educationtrainingrecordFormRef = ref<ElFormInstance>();

const initFormData: EducationTrainingRecordForm = {
    field: undefined,
    trainYear: undefined,
    courseName: undefined,
    startTime: undefined,
    endTime: undefined,
    trainHours: undefined,
    trainScore: undefined,
    study: undefined,
}
const data = reactive<PageData<EducationTrainingRecordForm, EducationTrainingRecordQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    field: undefined,
    trainYear: undefined,
    courseName: undefined,
    startTime: undefined,
    endTime: undefined,
    trainHours: undefined,
  },
  rules: {
    field: [
      { required: true, message: "实施机构不能为空", trigger: "blur" }
    ],
    trainYear: [
      { required: true, message: "培训年度不能为空", trigger: "blur" }
    ],
    courseName: [
      { required: true, message: "课程名称不能为空", trigger: "blur" }
    ],
    startTime: [
      { required: true, message: "开始时间不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listEducationTrainingRecord(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; educationtrainingrecordFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: EducationTrainingRecordVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增培训记录'; dialog.visible = true; }
const handleUpdate = async (row?: EducationTrainingRecordVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getEducationTrainingRecord(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改培训记录'; dialog.visible = true;
}
const submitForm = () => {
  educationtrainingrecordFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateEducationTrainingRecord(form.value); }
        else { await addEducationTrainingRecord(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: EducationTrainingRecordVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delEducationTrainingRecord(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/educationtrainingrecord/export', { ...queryParams.value }, '培训记录_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>