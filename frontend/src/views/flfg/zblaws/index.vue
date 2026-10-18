<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="发布状态" prop="publishStatus">
            <el-input v-model="queryParams.publishStatus" placeholder="请输入发布状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="效力状态" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入效力状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="文号" prop="fileNo">
            <el-input v-model="queryParams.fileNo" placeholder="请输入文号" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="文件名称" prop="fileName">
            <el-input v-model="queryParams.fileName" placeholder="请输入文件名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="别名/简称" prop="alias">
            <el-input v-model="queryParams.alias" placeholder="请输入别名/简称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="文件分类" prop="fileCategory">
            <el-input v-model="queryParams.fileCategory" placeholder="请输入文件分类" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>法律法规列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:ZBLAWS:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:ZBLAWS:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:ZBLAWS:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:ZBLAWS:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="法律主键" align="center" prop="id" width="160" />
        <el-table-column label="发布状态" align="left" prop="publishStatus" show-overflow-tooltip min-width="120" />
        <el-table-column label="效力状态" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="文号" align="left" prop="fileNo" show-overflow-tooltip min-width="120" />
        <el-table-column label="文件名称" align="left" prop="fileName" show-overflow-tooltip min-width="120" />
        <el-table-column label="别名/简称" align="left" prop="alias" show-overflow-tooltip min-width="120" />
        <el-table-column label="文件分类" align="left" prop="fileCategory" show-overflow-tooltip min-width="120" />
        <el-table-column label="发布日期" align="center" prop="publishDate" width="120" show-overflow-tooltip />
        <el-table-column label="实施日期" align="center" prop="effectiveDate" width="120" show-overflow-tooltip />
        <el-table-column label="发布部门/制定单位" align="left" prop="publishDept" show-overflow-tooltip min-width="120" />
        <el-table-column label="来源" align="left" prop="source" show-overflow-tooltip min-width="120" />
        <el-table-column label="附件数量" align="left" prop="attachmentCount" show-overflow-tooltip min-width="120" />
        <el-table-column label="上传人" align="left" prop="uploader" show-overflow-tooltip min-width="120" />
        <el-table-column label="上传时间" align="center" prop="uploadTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:ZBLAWS:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:ZBLAWS:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="zblawsFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="发布状态" prop="publishStatus">
              <el-input v-model="form.publishStatus" placeholder="请输入发布状态" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="效力状态" prop="field">
              <el-input v-model="form.field" placeholder="请输入效力状态" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="文号" prop="fileNo">
              <el-input v-model="form.fileNo" placeholder="请输入文号" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="文件名称" prop="fileName">
              <el-input v-model="form.fileName" placeholder="请输入文件名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="别名/简称" prop="alias">
              <el-input v-model="form.alias" placeholder="请输入别名/简称" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="文件分类" prop="fileCategory">
              <el-input v-model="form.fileCategory" placeholder="请输入文件分类" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="发布日期" prop="publishDate">
              <el-date-picker v-model="form.publishDate" type="datetime" placeholder="选择发布日期" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="实施日期" prop="effectiveDate">
              <el-date-picker v-model="form.effectiveDate" type="datetime" placeholder="选择实施日期" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="发布部门/制定单位" prop="publishDept">
              <el-input v-model="form.publishDept" placeholder="请输入发布部门/制定单位" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="来源" prop="source">
              <el-input v-model="form.source" placeholder="请输入来源" />
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
            <el-form-item label="上传人" prop="uploader">
              <el-input v-model="form.uploader" placeholder="请输入上传人" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="上传时间" prop="uploadTime">
              <el-date-picker v-model="form.uploadTime" type="datetime" placeholder="选择上传时间" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
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

<script setup name="ZBLAWS" lang="ts">
import { addZBLAWS, delZBLAWS, getZBLAWS, listZBLAWS, updateZBLAWS } from '@/api/scyh/zblaws';
import { ZBLAWSForm, ZBLAWSQuery, ZBLAWSVO } from '@/api/scyh/zblaws/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<ZBLAWSVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const zblawsFormRef = ref<ElFormInstance>();

const initFormData: ZBLAWSForm = {
    publishStatus: undefined,
    field: undefined,
    fileNo: undefined,
    fileName: undefined,
    alias: undefined,
    fileCategory: undefined,
    publishDate: undefined,
    effectiveDate: undefined,
    publishDept: undefined,
    source: undefined,
    attachmentCount: undefined,
    uploader: undefined,
    uploadTime: undefined,
}
const data = reactive<PageData<ZBLAWSForm, ZBLAWSQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    publishStatus: undefined,
    field: undefined,
    fileNo: undefined,
    fileName: undefined,
    alias: undefined,
    fileCategory: undefined,
  },
  rules: {
    publishStatus: [
      { required: true, message: "发布状态不能为空", trigger: "blur" }
    ],
    field: [
      { required: true, message: "效力状态不能为空", trigger: "blur" }
    ],
    fileNo: [
      { required: true, message: "文号不能为空", trigger: "blur" }
    ],
    fileName: [
      { required: true, message: "文件名称不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listZBLAWS(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; zblawsFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: ZBLAWSVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增法律法规'; dialog.visible = true; }
const handleUpdate = async (row?: ZBLAWSVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getZBLAWS(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改法律法规'; dialog.visible = true;
}
const submitForm = () => {
  zblawsFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateZBLAWS(form.value); }
        else { await addZBLAWS(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: ZBLAWSVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delZBLAWS(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/zblaws/export', { ...queryParams.value }, '法律法规_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>