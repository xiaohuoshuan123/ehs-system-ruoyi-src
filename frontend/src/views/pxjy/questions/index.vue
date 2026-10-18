<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="题型" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入题型" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="题干" prop="field2">
            <el-input v-model="queryParams.field2" placeholder="请输入题干" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="解析" prop="field3">
            <el-input v-model="queryParams.field3" placeholder="请输入解析" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="已考次数" prop="field4">
            <el-input v-model="queryParams.field4" placeholder="请输入已考次数" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="错题次数" prop="field5">
            <el-input v-model="queryParams.field5" placeholder="请输入错题次数" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>试题管理列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:questions:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:questions:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:questions:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:questions:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="试题ID" align="center" prop="id" width="160" />
        <el-table-column label="题型" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="题干" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="解析" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="已考次数" align="left" prop="field4" show-overflow-tooltip min-width="120" />
        <el-table-column label="错题次数" align="left" prop="field5" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人" align="left" prop="createBy" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="更新人" align="center" prop="updateBy" width="120" show-overflow-tooltip />
        <el-table-column label="更新时间" align="center" prop="updateTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:questions:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:questions:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="questionsFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="题型" prop="field">
              <el-input v-model="form.field" placeholder="请输入题型" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="题干" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入题干" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="解析" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入解析" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="已考次数" prop="field4">
              <el-input v-model="form.field4" placeholder="请输入已考次数" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="错题次数" prop="field5">
              <el-input v-model="form.field5" placeholder="请输入错题次数" />
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

<script setup name="Questions" lang="ts">
import { addQuestions, delQuestions, getQuestions, listQuestions, updateQuestions } from '@/api/scyh/questions';
import { QuestionsForm, QuestionsQuery, QuestionsVO } from '@/api/scyh/questions/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<QuestionsVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const questionsFormRef = ref<ElFormInstance>();

const initFormData: QuestionsForm = {
    field: undefined,
    field2: undefined,
    field3: undefined,
    field4: undefined,
    field5: undefined,
}
const data = reactive<PageData<QuestionsForm, QuestionsQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    field: undefined,
    field2: undefined,
    field3: undefined,
    field4: undefined,
    field5: undefined,
  },
  rules: {
    field: [
      { required: true, message: "题型不能为空", trigger: "blur" }
    ],
    field2: [
      { required: true, message: "题干不能为空", trigger: "blur" }
    ],
    field3: [
      { required: true, message: "解析不能为空", trigger: "blur" }
    ],
    field4: [
      { required: true, message: "已考次数不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listQuestions(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; questionsFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: QuestionsVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增试题管理'; dialog.visible = true; }
const handleUpdate = async (row?: QuestionsVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getQuestions(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改试题管理'; dialog.visible = true;
}
const submitForm = () => {
  questionsFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateQuestions(form.value); }
        else { await addQuestions(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: QuestionsVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delQuestions(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/questions/export', { ...queryParams.value }, '试题管理_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>