<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="事件报告ID" prop="eventId">
            <el-input v-model="queryParams.eventId" placeholder="请输入事件报告ID" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="queryParams.status" placeholder="全部" clearable style="width: 180px">
              <el-option label="正常" value="0" />
              <el-option label="停用" value="1" />
            </el-select>
          </el-form-item>
          <el-form-item label="事件时间" prop="eventTime">
            <el-input v-model="queryParams.eventTime" placeholder="请输入事件时间" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="事件发生单位" prop="event">
            <el-input v-model="queryParams.event" placeholder="请输入事件发生单位" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="事件级别" prop="eventLevel">
            <el-input v-model="queryParams.eventLevel" placeholder="请输入事件级别" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="事发地点" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入事发地点" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>应急处置评估列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:SGEMSSMENT:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:SGEMSSMENT:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:SGEMSSMENT:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:SGEMSSMENT:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="应急处置评估ID" align="center" prop="id" width="160" />
        <el-table-column label="事件报告ID" align="left" prop="eventId" show-overflow-tooltip min-width="120" />
        <el-table-column label="状态" align="center" prop="status" width="80">
          <template #default="scope">
            <el-tag :type="scope.row.status === '0' ? 'danger' : 'success'" size="small">
              {{ scope.row.status === '0' ? '停用' : '正常' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="事件时间" align="center" prop="eventTime" width="120" show-overflow-tooltip />
        <el-table-column label="事件发生单位" align="left" prop="event" show-overflow-tooltip min-width="120" />
        <el-table-column label="事件级别" align="left" prop="eventLevel" show-overflow-tooltip min-width="120" />
        <el-table-column label="事发地点" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="事件简要经过" align="left" prop="event2" show-overflow-tooltip min-width="120" />
        <el-table-column label="应急处置情况" align="left" prop="emergencyHandle" show-overflow-tooltip min-width="120" />
        <el-table-column label="是否涉及应急预案" align="left" prop="is" show-overflow-tooltip min-width="120" />
        <el-table-column label="预案是否需要修订" align="left" prop="planIs" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:SGEMSSMENT:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:SGEMSSMENT:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="sgemssmentFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="事件报告ID" prop="eventId">
              <el-input v-model="form.eventId" placeholder="请输入事件报告ID" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态" style="width: 100%">
                <el-option label="正常" value="0" />
                <el-option label="停用" value="1" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="事件时间" prop="eventTime">
              <el-date-picker v-model="form.eventTime" type="datetime" placeholder="选择事件时间" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="事件发生单位" prop="event">
              <el-input v-model="form.event" placeholder="请输入事件发生单位" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="事件级别" prop="eventLevel">
              <el-input v-model="form.eventLevel" placeholder="请输入事件级别" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="事发地点" prop="field">
              <el-input v-model="form.field" placeholder="请输入事发地点" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="事件简要经过" prop="event2">
              <el-input v-model="form.event2" placeholder="请输入事件简要经过" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="应急处置情况" prop="emergencyHandle">
              <el-input v-model="form.emergencyHandle" placeholder="请输入应急处置情况" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="是否涉及应急预案" prop="is">
              <el-input v-model="form.is" placeholder="请输入是否涉及应急预案" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="预案是否需要修订" prop="planIs">
              <el-input v-model="form.planIs" placeholder="请输入预案是否需要修订" />
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

<script setup name="SGEMSSMENT" lang="ts">
import { addSGEMSSMENT, delSGEMSSMENT, getSGEMSSMENT, listSGEMSSMENT, updateSGEMSSMENT } from '@/api/scyh/sgemssment';
import { SGEMSSMENTForm, SGEMSSMENTQuery, SGEMSSMENTVO } from '@/api/scyh/sgemssment/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<SGEMSSMENTVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const sgemssmentFormRef = ref<ElFormInstance>();

const initFormData: SGEMSSMENTForm = {
    eventId: undefined,
    status: undefined,
    eventTime: undefined,
    event: undefined,
    eventLevel: undefined,
    field: undefined,
    event2: undefined,
    emergencyHandle: undefined,
    is: undefined,
    planIs: undefined,
}
const data = reactive<PageData<SGEMSSMENTForm, SGEMSSMENTQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    eventId: undefined,
    status: undefined,
    eventTime: undefined,
    event: undefined,
    eventLevel: undefined,
    field: undefined,
  },
  rules: {
    eventId: [
      { required: true, message: "事件报告ID不能为空", trigger: "blur" }
    ],
    status: [
      { required: true, message: "状态不能为空", trigger: "blur" }
    ],
    eventTime: [
      { required: true, message: "事件时间不能为空", trigger: "blur" }
    ],
    event: [
      { required: true, message: "事件发生单位不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listSGEMSSMENT(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; sgemssmentFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: SGEMSSMENTVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增应急处置评估'; dialog.visible = true; }
const handleUpdate = async (row?: SGEMSSMENTVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getSGEMSSMENT(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改应急处置评估'; dialog.visible = true;
}
const submitForm = () => {
  sgemssmentFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateSGEMSSMENT(form.value); }
        else { await addSGEMSSMENT(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: SGEMSSMENTVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delSGEMSSMENT(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/sgemssment/export', { ...queryParams.value }, '应急处置评估_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>