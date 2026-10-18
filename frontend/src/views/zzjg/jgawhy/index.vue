<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="会议标题" prop="meetingTitle">
            <el-input v-model="queryParams.meetingTitle" placeholder="请输入会议标题" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="发布时间" prop="publishTime">
            <el-input v-model="queryParams.publishTime" placeholder="请输入发布时间" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="发布单位" prop="publishOrg">
            <el-input v-model="queryParams.publishOrg" placeholder="请输入发布单位" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="发布人" prop="publisher">
            <el-input v-model="queryParams.publisher" placeholder="请输入发布人" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="类型" prop="type">
            <el-input v-model="queryParams.type" placeholder="请输入类型" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="会议材料" prop="meetingMaterial">
            <el-input v-model="queryParams.meetingMaterial" placeholder="请输入会议材料" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>安委会会议纪要列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:JGAWHY:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:JGAWHY:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:JGAWHY:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:JGAWHY:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="安委会会议纪要ID" align="center" prop="id" width="160" />
        <el-table-column label="会议标题" align="left" prop="meetingTitle" show-overflow-tooltip min-width="120" />
        <el-table-column label="发布时间" align="center" prop="publishTime" width="120" show-overflow-tooltip />
        <el-table-column label="发布单位" align="left" prop="publishOrg" show-overflow-tooltip min-width="120" />
        <el-table-column label="发布人" align="left" prop="publisher" show-overflow-tooltip min-width="120" />
        <el-table-column label="类型" align="left" prop="type" show-overflow-tooltip min-width="120" />
        <el-table-column label="会议材料" align="left" prop="meetingMaterial" show-overflow-tooltip min-width="120" />
        <el-table-column label="会议纪要" align="left" prop="meetingSummary" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:JGAWHY:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:JGAWHY:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="jgawhyFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="会议标题" prop="meetingTitle">
              <el-input v-model="form.meetingTitle" placeholder="请输入会议标题" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="发布时间" prop="publishTime">
              <el-date-picker v-model="form.publishTime" type="datetime" placeholder="选择发布时间" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="发布单位" prop="publishOrg">
              <el-input v-model="form.publishOrg" placeholder="请输入发布单位" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="发布人" prop="publisher">
              <el-input v-model="form.publisher" placeholder="请输入发布人" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="类型" prop="type">
              <el-input v-model="form.type" placeholder="请输入类型" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="会议材料" prop="meetingMaterial">
              <el-input v-model="form.meetingMaterial" placeholder="请输入会议材料" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="会议纪要" prop="meetingSummary">
              <el-input v-model="form.meetingSummary" placeholder="请输入会议纪要" />
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

<script setup name="JGAWHY" lang="ts">
import { addJGAWHY, delJGAWHY, getJGAWHY, listJGAWHY, updateJGAWHY } from '@/api/scyh/jgawhy';
import { JGAWHYForm, JGAWHYQuery, JGAWHYVO } from '@/api/scyh/jgawhy/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<JGAWHYVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const jgawhyFormRef = ref<ElFormInstance>();

const initFormData: JGAWHYForm = {
    meetingTitle: undefined,
    publishTime: undefined,
    publishOrg: undefined,
    publisher: undefined,
    type: undefined,
    meetingMaterial: undefined,
    meetingSummary: undefined,
}
const data = reactive<PageData<JGAWHYForm, JGAWHYQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    meetingTitle: undefined,
    publishTime: undefined,
    publishOrg: undefined,
    publisher: undefined,
    type: undefined,
    meetingMaterial: undefined,
  },
  rules: {
    meetingTitle: [
      { required: true, message: "会议标题不能为空", trigger: "blur" }
    ],
    publishTime: [
      { required: true, message: "发布时间不能为空", trigger: "blur" }
    ],
    publishOrg: [
      { required: true, message: "发布单位不能为空", trigger: "blur" }
    ],
    publisher: [
      { required: true, message: "发布人不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listJGAWHY(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; jgawhyFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: JGAWHYVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增安委会会议纪要'; dialog.visible = true; }
const handleUpdate = async (row?: JGAWHYVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getJGAWHY(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改安委会会议纪要'; dialog.visible = true;
}
const submitForm = () => {
  jgawhyFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateJGAWHY(form.value); }
        else { await addJGAWHY(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: JGAWHYVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delJGAWHY(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/jgawhy/export', { ...queryParams.value }, '安委会会议纪要_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>