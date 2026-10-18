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
          <el-form-item label="目录序号" prop="articleCode">
            <el-input v-model="queryParams.articleCode" placeholder="请输入目录序号" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="危化品名称" prop="hazardousName">
            <el-input v-model="queryParams.hazardousName" placeholder="请输入危化品名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="物料名称" prop="materialName">
            <el-input v-model="queryParams.materialName" placeholder="请输入物料名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="厂家" prop="manufacturer">
            <el-input v-model="queryParams.manufacturer" placeholder="请输入厂家" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>安全数据表列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:WHMSDS:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:WHMSDS:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:WHMSDS:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:WHMSDS:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="危险品ID" align="center" prop="id" width="160" />
        <el-table-column label="状态" align="center" prop="status" width="80">
          <template #default="scope">
            <el-tag :type="scope.row.status === '0' ? 'danger' : 'success'" size="small">
              {{ scope.row.status === '0' ? '停用' : '正常' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="发布单位" align="left" prop="publishOrg" show-overflow-tooltip min-width="120" />
        <el-table-column label="目录序号" align="left" prop="articleCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="危化品名称" align="left" prop="hazardousName" show-overflow-tooltip min-width="120" />
        <el-table-column label="物料名称" align="left" prop="materialName" show-overflow-tooltip min-width="120" />
        <el-table-column label="厂家" align="left" prop="manufacturer" show-overflow-tooltip min-width="120" />
        <el-table-column label="剧毒属性" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="重点监管" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="易制毒" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="易制爆" align="left" prop="field4" show-overflow-tooltip min-width="120" />
        <el-table-column label="其他" align="left" prop="field5" show-overflow-tooltip min-width="120" />
        <el-table-column label="CAS号" align="left" prop="casNo" show-overflow-tooltip min-width="120" />
        <el-table-column label="附件数量" align="left" prop="attachmentCount" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人姓名" align="left" prop="createByName" show-overflow-tooltip min-width="120" />
        <el-table-column label="MSDS发布日期" align="left" prop="msds" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:WHMSDS:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:WHMSDS:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="whmsdsFormRef" :model="form" :rules="rules" label-width="100px">
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
            <el-form-item label="目录序号" prop="articleCode">
              <el-input v-model="form.articleCode" placeholder="请输入目录序号" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="危化品名称" prop="hazardousName">
              <el-input v-model="form.hazardousName" placeholder="请输入危化品名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="物料名称" prop="materialName">
              <el-input v-model="form.materialName" placeholder="请输入物料名称" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="厂家" prop="manufacturer">
              <el-input v-model="form.manufacturer" placeholder="请输入厂家" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="剧毒属性" prop="field">
              <el-input v-model="form.field" placeholder="请输入剧毒属性" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="重点监管" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入重点监管" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="易制毒" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入易制毒" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="易制爆" prop="field4">
              <el-input v-model="form.field4" placeholder="请输入易制爆" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="其他" prop="field5">
              <el-input v-model="form.field5" placeholder="请输入其他" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="CAS号" prop="casNo">
              <el-input v-model="form.casNo" placeholder="请输入CAS号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="附件数量" prop="attachmentCount">
              <el-input v-model="form.attachmentCount" placeholder="请输入附件数量" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="创建人姓名" prop="createByName">
              <el-input v-model="form.createByName" placeholder="请输入创建人姓名" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="MSDS发布日期" prop="msds">
              <el-input v-model="form.msds" placeholder="请输入MSDS发布日期" />
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

<script setup name="WHMSDS" lang="ts">
import { addWHMSDS, delWHMSDS, getWHMSDS, listWHMSDS, updateWHMSDS } from '@/api/scyh/whmsds';
import { WHMSDSForm, WHMSDSQuery, WHMSDSVO } from '@/api/scyh/whmsds/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<WHMSDSVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const whmsdsFormRef = ref<ElFormInstance>();

const initFormData: WHMSDSForm = {
    status: undefined,
    publishOrg: undefined,
    articleCode: undefined,
    hazardousName: undefined,
    materialName: undefined,
    manufacturer: undefined,
    field: undefined,
    field2: undefined,
    field3: undefined,
    field4: undefined,
    field5: undefined,
    casNo: undefined,
    attachmentCount: undefined,
    createByName: undefined,
    msds: undefined,
}
const data = reactive<PageData<WHMSDSForm, WHMSDSQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    status: undefined,
    publishOrg: undefined,
    articleCode: undefined,
    hazardousName: undefined,
    materialName: undefined,
    manufacturer: undefined,
  },
  rules: {
    status: [
      { required: true, message: "状态不能为空", trigger: "blur" }
    ],
    publishOrg: [
      { required: true, message: "发布单位不能为空", trigger: "blur" }
    ],
    articleCode: [
      { required: true, message: "目录序号不能为空", trigger: "blur" }
    ],
    hazardousName: [
      { required: true, message: "危化品名称不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listWHMSDS(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; whmsdsFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: WHMSDSVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增安全数据表'; dialog.visible = true; }
const handleUpdate = async (row?: WHMSDSVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getWHMSDS(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改安全数据表'; dialog.visible = true;
}
const submitForm = () => {
  whmsdsFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateWHMSDS(form.value); }
        else { await addWHMSDS(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: WHMSDSVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delWHMSDS(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/whmsds/export', { ...queryParams.value }, '安全数据表_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>