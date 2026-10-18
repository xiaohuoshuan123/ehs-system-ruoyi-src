<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="审核状态编码" prop="auditStatusCode">
            <el-input v-model="queryParams.auditStatusCode" placeholder="请输入审核状态编码" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="审核状态" prop="auditStatus">
            <el-input v-model="queryParams.auditStatus" placeholder="请输入审核状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="使用状态" prop="useStatus">
            <el-input v-model="queryParams.useStatus" placeholder="请输入使用状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="动火点编号" prop="hotWork">
            <el-input v-model="queryParams.hotWork" placeholder="请输入动火点编号" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="责任单位" prop="dutyUnit">
            <el-input v-model="queryParams.dutyUnit" placeholder="请输入责任单位" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="作业单元" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入作业单元" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>固定动火点列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:menu:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:menu:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:menu:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:menu:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="固定动火点ID" align="center" prop="id" width="160" />
        <el-table-column label="审核状态编码" align="left" prop="auditStatusCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="审核状态" align="left" prop="auditStatus" show-overflow-tooltip min-width="120" />
        <el-table-column label="使用状态" align="left" prop="useStatus" show-overflow-tooltip min-width="120" />
        <el-table-column label="动火点编号" align="left" prop="hotWork" show-overflow-tooltip min-width="120" />
        <el-table-column label="责任单位" align="left" prop="dutyUnit" show-overflow-tooltip min-width="120" />
        <el-table-column label="作业单元" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="具体位置" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="责任人" align="left" prop="duty" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人" align="left" prop="createBy" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:menu:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:menu:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="menuFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="审核状态编码" prop="auditStatusCode">
              <el-input v-model="form.auditStatusCode" placeholder="请输入审核状态编码" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="审核状态" prop="auditStatus">
              <el-input v-model="form.auditStatus" placeholder="请输入审核状态" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="使用状态" prop="useStatus">
              <el-input v-model="form.useStatus" placeholder="请输入使用状态" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="动火点编号" prop="hotWork">
              <el-input v-model="form.hotWork" placeholder="请输入动火点编号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="责任单位" prop="dutyUnit">
              <el-input v-model="form.dutyUnit" placeholder="请输入责任单位" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="作业单元" prop="field">
              <el-input v-model="form.field" placeholder="请输入作业单元" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="具体位置" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入具体位置" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="责任人" prop="duty">
              <el-input v-model="form.duty" placeholder="请输入责任人" />
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

<script setup name="Menu" lang="ts">
import { addMenu, delMenu, getMenu, listMenu, updateMenu } from '@/api/scyh/menu';
import { MenuForm, MenuQuery, MenuVO } from '@/api/scyh/menu/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<MenuVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const menuFormRef = ref<ElFormInstance>();

const initFormData: MenuForm = {
    auditStatusCode: undefined,
    auditStatus: undefined,
    useStatus: undefined,
    hotWork: undefined,
    dutyUnit: undefined,
    field: undefined,
    field2: undefined,
    duty: undefined,
}
const data = reactive<PageData<MenuForm, MenuQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    auditStatusCode: undefined,
    auditStatus: undefined,
    useStatus: undefined,
    hotWork: undefined,
    dutyUnit: undefined,
    field: undefined,
  },
  rules: {
    auditStatusCode: [
      { required: true, message: "审核状态编码不能为空", trigger: "blur" }
    ],
    auditStatus: [
      { required: true, message: "审核状态不能为空", trigger: "blur" }
    ],
    useStatus: [
      { required: true, message: "使用状态不能为空", trigger: "blur" }
    ],
    hotWork: [
      { required: true, message: "动火点编号不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listMenu(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; menuFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: MenuVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增固定动火点'; dialog.visible = true; }
const handleUpdate = async (row?: MenuVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getMenu(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改固定动火点'; dialog.visible = true;
}
const submitForm = () => {
  menuFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateMenu(form.value); }
        else { await addMenu(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: MenuVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delMenu(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/menu/export', { ...queryParams.value }, '固定动火点_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>