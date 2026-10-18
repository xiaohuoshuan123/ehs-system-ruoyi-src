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
          <el-form-item label="发布单位" prop="publishOrg">
            <el-input v-model="queryParams.publishOrg" placeholder="请输入发布单位" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="违章级别" prop="breachLevel">
            <el-input v-model="queryParams.breachLevel" placeholder="请输入违章级别" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="条款编号" prop="clauseNo">
            <el-input v-model="queryParams.clauseNo" placeholder="请输入条款编号" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="条款内容" prop="clauseContent">
            <el-input v-model="queryParams.clauseContent" placeholder="请输入条款内容" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="分值" prop="score">
            <el-input v-model="queryParams.score" placeholder="请输入分值" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>违章记分条款列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:WZTKBRSC:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:WZTKBRSC:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:WZTKBRSC:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:WZTKBRSC:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="违章记分条款主键" align="center" prop="id" width="160" />
        <el-table-column label="状态" align="center" prop="status" width="80">
          <template #default="scope">
            <el-tag :type="scope.row.status === '0' ? 'danger' : 'success'" size="small">
              {{ scope.row.status === '0' ? '停用' : '正常' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="发布单位" align="left" prop="publishOrg" show-overflow-tooltip min-width="120" />
        <el-table-column label="违章级别" align="left" prop="breachLevel" show-overflow-tooltip min-width="120" />
        <el-table-column label="条款编号" align="left" prop="clauseNo" show-overflow-tooltip min-width="120" />
        <el-table-column label="条款内容" align="left" prop="clauseContent" show-overflow-tooltip min-width="120" />
        <el-table-column label="分值" align="left" prop="score" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人" align="left" prop="createBy" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:WZTKBRSC:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:WZTKBRSC:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="wztkbrscFormRef" :model="form" :rules="rules" label-width="100px">
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
            <el-form-item label="发布单位" prop="publishOrg">
              <el-input v-model="form.publishOrg" placeholder="请输入发布单位" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="违章级别" prop="breachLevel">
              <el-input v-model="form.breachLevel" placeholder="请输入违章级别" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="条款编号" prop="clauseNo">
              <el-input v-model="form.clauseNo" placeholder="请输入条款编号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="条款内容" prop="clauseContent">
              <el-input v-model="form.clauseContent" placeholder="请输入条款内容" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="分值" prop="score">
              <el-input v-model="form.score" placeholder="请输入分值" />
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

<script setup name="WZTKBRSC" lang="ts">
import { addWZTKBRSC, delWZTKBRSC, getWZTKBRSC, listWZTKBRSC, updateWZTKBRSC } from '@/api/scyh/wztkbrsc';
import { WZTKBRSCForm, WZTKBRSCQuery, WZTKBRSCVO } from '@/api/scyh/wztkbrsc/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<WZTKBRSCVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const wztkbrscFormRef = ref<ElFormInstance>();

const initFormData: WZTKBRSCForm = {
    status: undefined,
    publishOrg: undefined,
    breachLevel: undefined,
    clauseNo: undefined,
    clauseContent: undefined,
    score: undefined,
}
const data = reactive<PageData<WZTKBRSCForm, WZTKBRSCQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    status: undefined,
    publishOrg: undefined,
    breachLevel: undefined,
    clauseNo: undefined,
    clauseContent: undefined,
    score: undefined,
  },
  rules: {
    status: [
      { required: true, message: "状态不能为空", trigger: "blur" }
    ],
    publishOrg: [
      { required: true, message: "发布单位不能为空", trigger: "blur" }
    ],
    breachLevel: [
      { required: true, message: "违章级别不能为空", trigger: "blur" }
    ],
    clauseNo: [
      { required: true, message: "条款编号不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listWZTKBRSC(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; wztkbrscFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: WZTKBRSCVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增违章记分条款'; dialog.visible = true; }
const handleUpdate = async (row?: WZTKBRSCVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getWZTKBRSC(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改违章记分条款'; dialog.visible = true;
}
const submitForm = () => {
  wztkbrscFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateWZTKBRSC(form.value); }
        else { await addWZTKBRSC(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: WZTKBRSCVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delWZTKBRSC(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/wztkbrsc/export', { ...queryParams.value }, '违章记分条款_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>