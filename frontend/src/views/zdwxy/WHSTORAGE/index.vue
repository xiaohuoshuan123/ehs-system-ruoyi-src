<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="机构" prop="org">
            <el-input v-model="queryParams.org" placeholder="请输入机构" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="场所编码" prop="siteCode">
            <el-input v-model="queryParams.siteCode" placeholder="请输入场所编码" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="机构编码" prop="orgCode">
            <el-input v-model="queryParams.orgCode" placeholder="请输入机构编码" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="场所名称" prop="siteName">
            <el-input v-model="queryParams.siteName" placeholder="请输入场所名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="位置" prop="location">
            <el-input v-model="queryParams.location" placeholder="请输入位置" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="联系人" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入联系人" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>存储库管理列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:WHSTORAGE:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:WHSTORAGE:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:WHSTORAGE:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:WHSTORAGE:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="存储库ID" align="center" prop="id" width="160" />
        <el-table-column label="机构" align="left" prop="org" show-overflow-tooltip min-width="120" />
        <el-table-column label="场所编码" align="left" prop="siteCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="机构编码" align="left" prop="orgCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="场所名称" align="left" prop="siteName" show-overflow-tooltip min-width="120" />
        <el-table-column label="位置" align="left" prop="location" show-overflow-tooltip min-width="120" />
        <el-table-column label="联系人" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="联系人电话" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人工号" align="left" prop="createByEmpCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人姓名" align="left" prop="createByName" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="危险场所说明" align="left" prop="dangerSite" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:WHSTORAGE:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:WHSTORAGE:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="whstorageFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="机构" prop="org">
              <el-input v-model="form.org" placeholder="请输入机构" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="场所编码" prop="siteCode">
              <el-input v-model="form.siteCode" placeholder="请输入场所编码" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="机构编码" prop="orgCode">
              <el-input v-model="form.orgCode" placeholder="请输入机构编码" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="场所名称" prop="siteName">
              <el-input v-model="form.siteName" placeholder="请输入场所名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="位置" prop="location">
              <el-input v-model="form.location" placeholder="请输入位置" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="联系人" prop="field">
              <el-input v-model="form.field" placeholder="请输入联系人" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="联系人电话" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入联系人电话" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="创建人工号" prop="createByEmpCode">
              <el-input v-model="form.createByEmpCode" placeholder="请输入创建人工号" />
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
            <el-form-item label="危险场所说明" prop="dangerSite">
              <el-input v-model="form.dangerSite" placeholder="请输入危险场所说明" />
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

<script setup name="WHSTORAGE" lang="ts">
import { addWHSTORAGE, delWHSTORAGE, getWHSTORAGE, listWHSTORAGE, updateWHSTORAGE } from '@/api/scyh/whstorage';
import { WHSTORAGEForm, WHSTORAGEQuery, WHSTORAGEVO } from '@/api/scyh/whstorage/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<WHSTORAGEVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const whstorageFormRef = ref<ElFormInstance>();

const initFormData: WHSTORAGEForm = {
    org: undefined,
    siteCode: undefined,
    orgCode: undefined,
    siteName: undefined,
    location: undefined,
    field: undefined,
    field2: undefined,
    createByEmpCode: undefined,
    createByName: undefined,
    dangerSite: undefined,
}
const data = reactive<PageData<WHSTORAGEForm, WHSTORAGEQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    org: undefined,
    siteCode: undefined,
    orgCode: undefined,
    siteName: undefined,
    location: undefined,
    field: undefined,
  },
  rules: {
    org: [
      { required: true, message: "机构不能为空", trigger: "blur" }
    ],
    siteCode: [
      { required: true, message: "场所编码不能为空", trigger: "blur" }
    ],
    orgCode: [
      { required: true, message: "机构编码不能为空", trigger: "blur" }
    ],
    siteName: [
      { required: true, message: "场所名称不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listWHSTORAGE(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; whstorageFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: WHSTORAGEVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增存储库管理'; dialog.visible = true; }
const handleUpdate = async (row?: WHSTORAGEVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getWHSTORAGE(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改存储库管理'; dialog.visible = true;
}
const submitForm = () => {
  whstorageFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateWHSTORAGE(form.value); }
        else { await addWHSTORAGE(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: WHSTORAGEVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delWHSTORAGE(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/whstorage/export', { ...queryParams.value }, '存储库管理_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>