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
          <el-form-item label="所属模块" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入所属模块" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="指标编号" prop="indicatorCode">
            <el-input v-model="queryParams.indicatorCode" placeholder="请输入指标编号" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="指标名称" prop="indicatorName">
            <el-input v-model="queryParams.indicatorName" placeholder="请输入指标名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="扣分规则" prop="deductScore">
            <el-input v-model="queryParams.deductScore" placeholder="请输入扣分规则" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="数据来源" prop="dataSource">
            <el-input v-model="queryParams.dataSource" placeholder="请输入数据来源" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>绩效考核模型列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:LZSAND:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:LZSAND:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:LZSAND:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:LZSAND:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="模型ID" align="center" prop="id" width="160" />
        <el-table-column label="状态" align="center" prop="status" width="80">
          <template #default="scope">
            <el-tag :type="scope.row.status === '0' ? 'danger' : 'success'" size="small">
              {{ scope.row.status === '0' ? '停用' : '正常' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="所属模块" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="指标编号" align="left" prop="indicatorCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="指标名称" align="left" prop="indicatorName" show-overflow-tooltip min-width="120" />
        <el-table-column label="扣分规则" align="left" prop="deductScore" show-overflow-tooltip min-width="120" />
        <el-table-column label="数据来源" align="left" prop="dataSource" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:LZSAND:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:LZSAND:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="lzsandFormRef" :model="form" :rules="rules" label-width="100px">
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
            <el-form-item label="所属模块" prop="field">
              <el-input v-model="form.field" placeholder="请输入所属模块" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="指标编号" prop="indicatorCode">
              <el-input v-model="form.indicatorCode" placeholder="请输入指标编号" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="指标名称" prop="indicatorName">
              <el-input v-model="form.indicatorName" placeholder="请输入指标名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="扣分规则" prop="deductScore">
              <el-input v-model="form.deductScore" placeholder="请输入扣分规则" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="数据来源" prop="dataSource">
              <el-input v-model="form.dataSource" placeholder="请输入数据来源" />
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

<script setup name="LZSAND" lang="ts">
import { addLZSAND, delLZSAND, getLZSAND, listLZSAND, updateLZSAND } from '@/api/scyh/lzsand';
import { LZSANDForm, LZSANDQuery, LZSANDVO } from '@/api/scyh/lzsand/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<LZSANDVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const lzsandFormRef = ref<ElFormInstance>();

const initFormData: LZSANDForm = {
    status: undefined,
    field: undefined,
    indicatorCode: undefined,
    indicatorName: undefined,
    deductScore: undefined,
    dataSource: undefined,
}
const data = reactive<PageData<LZSANDForm, LZSANDQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    status: undefined,
    field: undefined,
    indicatorCode: undefined,
    indicatorName: undefined,
    deductScore: undefined,
    dataSource: undefined,
  },
  rules: {
    status: [
      { required: true, message: "状态不能为空", trigger: "blur" }
    ],
    field: [
      { required: true, message: "所属模块不能为空", trigger: "blur" }
    ],
    indicatorCode: [
      { required: true, message: "指标编号不能为空", trigger: "blur" }
    ],
    indicatorName: [
      { required: true, message: "指标名称不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listLZSAND(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; lzsandFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: LZSANDVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增绩效考核模型'; dialog.visible = true; }
const handleUpdate = async (row?: LZSANDVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getLZSAND(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改绩效考核模型'; dialog.visible = true;
}
const submitForm = () => {
  lzsandFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateLZSAND(form.value); }
        else { await addLZSAND(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: LZSANDVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delLZSAND(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/lzsand/export', { ...queryParams.value }, '绩效考核模型_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>