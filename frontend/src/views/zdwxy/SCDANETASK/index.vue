<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="角色Ids" prop="role">
            <el-input v-model="queryParams.role" placeholder="请输入角色Ids" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="任务状态" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入任务状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="检查角色/责任人" prop="checkRole">
            <el-input v-model="queryParams.checkRole" placeholder="请输入检查角色/责任人" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="检查机构Code" prop="checkOrg">
            <el-input v-model="queryParams.checkOrg" placeholder="请输入检查机构Code" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="被查机构" prop="field2">
            <el-input v-model="queryParams.field2" placeholder="请输入被查机构" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="检查层级" prop="checkLevel">
            <el-input v-model="queryParams.checkLevel" placeholder="请输入检查层级" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>检查任务列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:SCDANETASK:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:SCDANETASK:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:SCDANETASK:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:SCDANETASK:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="危险源检查任务主项ID" align="center" prop="id" width="160" />
        <el-table-column label="角色Ids" align="left" prop="role" show-overflow-tooltip min-width="120" />
        <el-table-column label="任务状态" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="检查角色/责任人" align="left" prop="checkRole" show-overflow-tooltip min-width="120" />
        <el-table-column label="检查机构Code" align="left" prop="checkOrg" show-overflow-tooltip min-width="120" />
        <el-table-column label="被查机构" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="检查层级" align="left" prop="checkLevel" show-overflow-tooltip min-width="120" />
        <el-table-column label="作业单元名称" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="固有风险等级" align="left" prop="field4" show-overflow-tooltip min-width="120" />
        <el-table-column label="检查频度" align="left" prop="check" show-overflow-tooltip min-width="120" />
        <el-table-column label="任务时间" align="left" prop="field5" show-overflow-tooltip min-width="120" />
        <el-table-column label="危险源数量" align="left" prop="danger" show-overflow-tooltip min-width="120" />
        <el-table-column label="实际检查数量" align="left" prop="field6" show-overflow-tooltip min-width="120" />
        <el-table-column label="不受控数量" align="left" prop="field7" show-overflow-tooltip min-width="120" />
        <el-table-column label="检查人员" align="left" prop="checker" show-overflow-tooltip min-width="120" />
        <el-table-column label="反馈人员" align="left" prop="feedbackPerson" show-overflow-tooltip min-width="120" />
        <el-table-column label="反馈时间" align="center" prop="feedbackTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:SCDANETASK:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:SCDANETASK:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="scdanetaskFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="角色Ids" prop="role">
              <el-input v-model="form.role" placeholder="请输入角色Ids" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="任务状态" prop="field">
              <el-input v-model="form.field" placeholder="请输入任务状态" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="检查角色/责任人" prop="checkRole">
              <el-input v-model="form.checkRole" placeholder="请输入检查角色/责任人" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="检查机构Code" prop="checkOrg">
              <el-input v-model="form.checkOrg" placeholder="请输入检查机构Code" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="被查机构" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入被查机构" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="检查层级" prop="checkLevel">
              <el-input v-model="form.checkLevel" placeholder="请输入检查层级" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="作业单元名称" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入作业单元名称" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="固有风险等级" prop="field4">
              <el-input v-model="form.field4" placeholder="请输入固有风险等级" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="检查频度" prop="check">
              <el-input v-model="form.check" placeholder="请输入检查频度" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="任务时间" prop="field5">
              <el-input v-model="form.field5" placeholder="请输入任务时间" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="危险源数量" prop="danger">
              <el-input v-model="form.danger" placeholder="请输入危险源数量" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="实际检查数量" prop="field6">
              <el-input v-model="form.field6" placeholder="请输入实际检查数量" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="不受控数量" prop="field7">
              <el-input v-model="form.field7" placeholder="请输入不受控数量" />
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
            <el-form-item label="反馈人员" prop="feedbackPerson">
              <el-input v-model="form.feedbackPerson" placeholder="请输入反馈人员" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="反馈时间" prop="feedbackTime">
              <el-date-picker v-model="form.feedbackTime" type="datetime" placeholder="选择反馈时间" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
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

<script setup name="SCDANETASK" lang="ts">
import { addSCDANETASK, delSCDANETASK, getSCDANETASK, listSCDANETASK, updateSCDANETASK } from '@/api/scyh/scdanetask';
import { SCDANETASKForm, SCDANETASKQuery, SCDANETASKVO } from '@/api/scyh/scdanetask/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<SCDANETASKVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const scdanetaskFormRef = ref<ElFormInstance>();

const initFormData: SCDANETASKForm = {
    role: undefined,
    field: undefined,
    checkRole: undefined,
    checkOrg: undefined,
    field2: undefined,
    checkLevel: undefined,
    field3: undefined,
    field4: undefined,
    check: undefined,
    field5: undefined,
    danger: undefined,
    field6: undefined,
    field7: undefined,
    checker: undefined,
    feedbackPerson: undefined,
    feedbackTime: undefined,
}
const data = reactive<PageData<SCDANETASKForm, SCDANETASKQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    role: undefined,
    field: undefined,
    checkRole: undefined,
    checkOrg: undefined,
    field2: undefined,
    checkLevel: undefined,
  },
  rules: {
    role: [
      { required: true, message: "角色Ids不能为空", trigger: "blur" }
    ],
    field: [
      { required: true, message: "任务状态不能为空", trigger: "blur" }
    ],
    checkRole: [
      { required: true, message: "检查角色/责任人不能为空", trigger: "blur" }
    ],
    checkOrg: [
      { required: true, message: "检查机构Code不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listSCDANETASK(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; scdanetaskFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: SCDANETASKVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增检查任务'; dialog.visible = true; }
const handleUpdate = async (row?: SCDANETASKVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getSCDANETASK(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改检查任务'; dialog.visible = true;
}
const submitForm = () => {
  scdanetaskFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateSCDANETASK(form.value); }
        else { await addSCDANETASK(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: SCDANETASKVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delSCDANETASK(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/scdanetask/export', { ...queryParams.value }, '检查任务_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>