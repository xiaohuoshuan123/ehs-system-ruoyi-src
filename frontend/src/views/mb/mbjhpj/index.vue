<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="状态" prop="status">
            <el-select v-model="queryParams.status" placeholder="全部" clearable style="width: 180px">
              <el-option label="正常" value="0" />
              <el-option label="停用" value="1" />
            </el-select>
          </el-form-item>
          <el-form-item label="计划名称" prop="planName">
            <el-input v-model="queryParams.planName" placeholder="请输入计划名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="计划年度" prop="planYear">
            <el-input v-model="queryParams.planYear" placeholder="请输入计划年度" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="责任单位" prop="dutyUnit">
            <el-input v-model="queryParams.dutyUnit" placeholder="请输入责任单位" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="反馈数量" prop="feedbackCount">
            <el-input v-model="queryParams.feedbackCount" placeholder="请输入反馈数量" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="未反馈数量" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入未反馈数量" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>年度计划列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:MBJHPJ:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:MBJHPJ:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:MBJHPJ:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:MBJHPJ:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="年度计划ID" align="center" prop="id" width="160" />
        <el-table-column label="状态" align="center" prop="status" width="80">
          <template #default="scope">
            <el-tag :type="scope.row.status === '0' ? 'danger' : 'success'" size="small">
              {{ scope.row.status === '0' ? '停用' : '正常' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="计划名称" align="left" prop="planName" show-overflow-tooltip min-width="120" />
        <el-table-column label="计划年度" align="left" prop="planYear" show-overflow-tooltip min-width="120" />
        <el-table-column label="责任单位" align="left" prop="dutyUnit" show-overflow-tooltip min-width="120" />
        <el-table-column label="反馈数量" align="left" prop="feedbackCount" show-overflow-tooltip min-width="120" />
        <el-table-column label="未反馈数量" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人" align="left" prop="createBy" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="置顶状态" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:MBJHPJ:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:MBJHPJ:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="mbjhpjFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态" style="width: 100%">
                <el-option label="正常" value="0" />
                <el-option label="停用" value="1" />
              </el-select>
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="计划名称" prop="planName">
              <el-input v-model="form.planName" placeholder="请输入计划名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="计划年度" prop="planYear">
              <el-input v-model="form.planYear" placeholder="请输入计划年度" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="责任单位" prop="dutyUnit">
              <el-input v-model="form.dutyUnit" placeholder="请输入责任单位" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="反馈数量" prop="feedbackCount">
              <el-input v-model="form.feedbackCount" placeholder="请输入反馈数量" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="未反馈数量" prop="field">
              <el-input v-model="form.field" placeholder="请输入未反馈数量" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="置顶状态" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入置顶状态" />
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

<script setup name="MBJHPJ" lang="ts">
import { addMBJHPJ, delMBJHPJ, getMBJHPJ, listMBJHPJ, updateMBJHPJ } from '@/api/scyh/mbjhpj';
import { MBJHPJForm, MBJHPJQuery, MBJHPJVO } from '@/api/scyh/mbjhpj/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<MBJHPJVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const mbjhpjFormRef = ref<ElFormInstance>();

const initFormData: MBJHPJForm = {
    status: undefined,
    planName: undefined,
    planYear: undefined,
    dutyUnit: undefined,
    feedbackCount: undefined,
    field: undefined,
    field2: undefined,
}
const data = reactive<PageData<MBJHPJForm, MBJHPJQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    status: undefined,
    planName: undefined,
    planYear: undefined,
    dutyUnit: undefined,
    feedbackCount: undefined,
    field: undefined,
  },
  rules: {
    status: [
      { required: true, message: "状态不能为空", trigger: "blur" }
    ],
    planName: [
      { required: true, message: "计划名称不能为空", trigger: "blur" }
    ],
    planYear: [
      { required: true, message: "计划年度不能为空", trigger: "blur" }
    ],
    dutyUnit: [
      { required: true, message: "责任单位不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listMBJHPJ(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; mbjhpjFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: MBJHPJVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增年度计划'; dialog.visible = true; }
const handleUpdate = async (row?: MBJHPJVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getMBJHPJ(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改年度计划'; dialog.visible = true;
}
const submitForm = () => {
  mbjhpjFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateMBJHPJ(form.value); }
        else { await addMBJHPJ(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: MBJHPJVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delMBJHPJ(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/mbjhpj/export', { ...queryParams.value }, '年度计划_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>