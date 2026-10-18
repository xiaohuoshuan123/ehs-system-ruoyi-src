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
          <el-form-item label="检查层级" prop="checkLevel">
            <el-input v-model="queryParams.checkLevel" placeholder="请输入检查层级" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="风险等级" prop="riskLevel">
            <el-input v-model="queryParams.riskLevel" placeholder="请输入风险等级" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="检查频次" prop="check">
            <el-input v-model="queryParams.check" placeholder="请输入检查频次" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="queryParams.roleName" placeholder="请输入角色名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="责任人" prop="duty">
            <el-input v-model="queryParams.duty" placeholder="请输入责任人" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>危险源检查配置列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:SCDAECONFI:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:SCDAECONFI:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:SCDAECONFI:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:SCDAECONFI:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="危险源检查配置ID" align="center" prop="id" width="160" />
        <el-table-column label="状态" align="center" prop="status" width="80">
          <template #default="scope">
            <el-tag :type="scope.row.status === '0' ? 'danger' : 'success'" size="small">
              {{ scope.row.status === '0' ? '停用' : '正常' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="检查层级" align="left" prop="checkLevel" show-overflow-tooltip min-width="120" />
        <el-table-column label="风险等级" align="left" prop="riskLevel" show-overflow-tooltip min-width="120" />
        <el-table-column label="检查频次" align="left" prop="check" show-overflow-tooltip min-width="120" />
        <el-table-column label="角色名称" align="left" prop="roleName" show-overflow-tooltip min-width="120" />
        <el-table-column label="责任人" align="left" prop="duty" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人姓名" align="left" prop="createByName" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="修改人姓名" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="修改时间" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:SCDAECONFI:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:SCDAECONFI:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="scdaeconfiFormRef" :model="form" :rules="rules" label-width="100px">
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
            <el-form-item label="检查层级" prop="checkLevel">
              <el-input v-model="form.checkLevel" placeholder="请输入检查层级" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="风险等级" prop="riskLevel">
              <el-input v-model="form.riskLevel" placeholder="请输入风险等级" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="检查频次" prop="check">
              <el-input v-model="form.check" placeholder="请输入检查频次" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="角色名称" prop="roleName">
              <el-input v-model="form.roleName" placeholder="请输入角色名称" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="责任人" prop="duty">
              <el-input v-model="form.duty" placeholder="请输入责任人" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="创建人姓名" prop="createByName">
              <el-input v-model="form.createByName" placeholder="请输入创建人姓名" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="修改人姓名" prop="field">
              <el-input v-model="form.field" placeholder="请输入修改人姓名" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="修改时间" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入修改时间" />
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

<script setup name="SCDAECONFI" lang="ts">
import { addSCDAECONFI, delSCDAECONFI, getSCDAECONFI, listSCDAECONFI, updateSCDAECONFI } from '@/api/scyh/scdaeconfi';
import { SCDAECONFIForm, SCDAECONFIQuery, SCDAECONFIVO } from '@/api/scyh/scdaeconfi/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<SCDAECONFIVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const scdaeconfiFormRef = ref<ElFormInstance>();

const initFormData: SCDAECONFIForm = {
    status: undefined,
    checkLevel: undefined,
    riskLevel: undefined,
    check: undefined,
    roleName: undefined,
    duty: undefined,
    createByName: undefined,
    field: undefined,
    field2: undefined,
}
const data = reactive<PageData<SCDAECONFIForm, SCDAECONFIQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    status: undefined,
    checkLevel: undefined,
    riskLevel: undefined,
    check: undefined,
    roleName: undefined,
    duty: undefined,
  },
  rules: {
    status: [
      { required: true, message: "状态不能为空", trigger: "blur" }
    ],
    checkLevel: [
      { required: true, message: "检查层级不能为空", trigger: "blur" }
    ],
    riskLevel: [
      { required: true, message: "风险等级不能为空", trigger: "blur" }
    ],
    check: [
      { required: true, message: "检查频次不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listSCDAECONFI(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; scdaeconfiFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: SCDAECONFIVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增危险源检查配置'; dialog.visible = true; }
const handleUpdate = async (row?: SCDAECONFIVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getSCDAECONFI(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改危险源检查配置'; dialog.visible = true;
}
const submitForm = () => {
  scdaeconfiFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateSCDAECONFI(form.value); }
        else { await addSCDAECONFI(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: SCDAECONFIVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delSCDAECONFI(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/scdaeconfi/export', { ...queryParams.value }, '危险源检查配置_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>