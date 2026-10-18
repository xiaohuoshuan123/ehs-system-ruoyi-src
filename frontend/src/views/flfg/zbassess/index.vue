<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="专业管理部门名称" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入专业管理部门名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="年度" prop="year">
            <el-input v-model="queryParams.year" placeholder="请输入年度" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="评价报告" prop="field2">
            <el-input v-model="queryParams.field2" placeholder="请输入评价报告" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>合规性评价列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:ZBASSESS:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:ZBASSESS:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:ZBASSESS:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:ZBASSESS:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="合规性评价ID" align="center" prop="id" width="160" />
        <el-table-column label="专业管理部门名称" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="年度" align="left" prop="year" show-overflow-tooltip min-width="120" />
        <el-table-column label="评价报告" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:ZBASSESS:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:ZBASSESS:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="zbassessFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="专业管理部门名称" prop="field">
              <el-input v-model="form.field" placeholder="请输入专业管理部门名称" />
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
            <el-form-item label="评价报告" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入评价报告" />
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

<script setup name="ZBASSESS" lang="ts">
import { addZBASSESS, delZBASSESS, getZBASSESS, listZBASSESS, updateZBASSESS } from '@/api/scyh/zbassess';
import { ZBASSESSForm, ZBASSESSQuery, ZBASSESSVO } from '@/api/scyh/zbassess/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<ZBASSESSVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const zbassessFormRef = ref<ElFormInstance>();

const initFormData: ZBASSESSForm = {
    field: undefined,
    year: undefined,
    field2: undefined,
}
const data = reactive<PageData<ZBASSESSForm, ZBASSESSQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    field: undefined,
    year: undefined,
    field2: undefined,
  },
  rules: {
    field: [
      { required: true, message: "专业管理部门名称不能为空", trigger: "blur" }
    ],
    year: [
      { required: true, message: "年度不能为空", trigger: "blur" }
    ],
    field2: [
      { required: true, message: "评价报告不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listZBASSESS(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; zbassessFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: ZBASSESSVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增合规性评价'; dialog.visible = true; }
const handleUpdate = async (row?: ZBASSESSVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getZBASSESS(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改合规性评价'; dialog.visible = true;
}
const submitForm = () => {
  zbassessFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateZBASSESS(form.value); }
        else { await addZBASSESS(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: ZBASSESSVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delZBASSESS(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/zbassess/export', { ...queryParams.value }, '合规性评价_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>