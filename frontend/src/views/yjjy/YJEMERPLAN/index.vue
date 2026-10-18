<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="单位名称" prop="unitName">
            <el-input v-model="queryParams.unitName" placeholder="请输入单位名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="预案名称" prop="planName">
            <el-input v-model="queryParams.planName" placeholder="请输入预案名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="预案类型" prop="planType">
            <el-input v-model="queryParams.planType" placeholder="请输入预案类型" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="预案等级" prop="planLevel">
            <el-input v-model="queryParams.planLevel" placeholder="请输入预案等级" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="编制部门" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入编制部门" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="发布日期" prop="publishDate">
            <el-input v-model="queryParams.publishDate" placeholder="请输入发布日期" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>应急预案列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:YJEMERPLAN:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:YJEMERPLAN:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:YJEMERPLAN:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:YJEMERPLAN:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="应急预案id" align="center" prop="id" width="160" />
        <el-table-column label="单位名称" align="left" prop="unitName" show-overflow-tooltip min-width="120" />
        <el-table-column label="预案名称" align="left" prop="planName" show-overflow-tooltip min-width="120" />
        <el-table-column label="预案类型" align="left" prop="planType" show-overflow-tooltip min-width="120" />
        <el-table-column label="预案等级" align="left" prop="planLevel" show-overflow-tooltip min-width="120" />
        <el-table-column label="编制部门" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="发布日期" align="center" prop="publishDate" width="120" show-overflow-tooltip />
        <el-table-column label="审核人" align="left" prop="audit" show-overflow-tooltip min-width="120" />
        <el-table-column label="演练周期" align="left" prop="drill" show-overflow-tooltip min-width="120" />
        <el-table-column label="附件数量" align="left" prop="attachmentCount" show-overflow-tooltip min-width="120" />
        <el-table-column label="数据状态" align="left" prop="dataStatus" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人" align="left" prop="createBy" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:YJEMERPLAN:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:YJEMERPLAN:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="yjemerplanFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="单位名称" prop="unitName">
              <el-input v-model="form.unitName" placeholder="请输入单位名称" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="预案名称" prop="planName">
              <el-input v-model="form.planName" placeholder="请输入预案名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="预案类型" prop="planType">
              <el-input v-model="form.planType" placeholder="请输入预案类型" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="预案等级" prop="planLevel">
              <el-input v-model="form.planLevel" placeholder="请输入预案等级" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="编制部门" prop="field">
              <el-input v-model="form.field" placeholder="请输入编制部门" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="发布日期" prop="publishDate">
              <el-date-picker v-model="form.publishDate" type="datetime" placeholder="选择发布日期" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="审核人" prop="audit">
              <el-input v-model="form.audit" placeholder="请输入审核人" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="演练周期" prop="drill">
              <el-input v-model="form.drill" placeholder="请输入演练周期" />
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
            <el-form-item label="数据状态" prop="dataStatus">
              <el-input v-model="form.dataStatus" placeholder="请输入数据状态" />
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

<script setup name="YJEMERPLAN" lang="ts">
import { addYJEMERPLAN, delYJEMERPLAN, getYJEMERPLAN, listYJEMERPLAN, updateYJEMERPLAN } from '@/api/scyh/yjemerplan';
import { YJEMERPLANForm, YJEMERPLANQuery, YJEMERPLANVO } from '@/api/scyh/yjemerplan/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<YJEMERPLANVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const yjemerplanFormRef = ref<ElFormInstance>();

const initFormData: YJEMERPLANForm = {
    unitName: undefined,
    planName: undefined,
    planType: undefined,
    planLevel: undefined,
    field: undefined,
    publishDate: undefined,
    audit: undefined,
    drill: undefined,
    attachmentCount: undefined,
    dataStatus: undefined,
}
const data = reactive<PageData<YJEMERPLANForm, YJEMERPLANQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    unitName: undefined,
    planName: undefined,
    planType: undefined,
    planLevel: undefined,
    field: undefined,
    publishDate: undefined,
  },
  rules: {
    unitName: [
      { required: true, message: "单位名称不能为空", trigger: "blur" }
    ],
    planName: [
      { required: true, message: "预案名称不能为空", trigger: "blur" }
    ],
    planType: [
      { required: true, message: "预案类型不能为空", trigger: "blur" }
    ],
    planLevel: [
      { required: true, message: "预案等级不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listYJEMERPLAN(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; yjemerplanFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: YJEMERPLANVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增应急预案'; dialog.visible = true; }
const handleUpdate = async (row?: YJEMERPLANVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getYJEMERPLAN(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改应急预案'; dialog.visible = true;
}
const submitForm = () => {
  yjemerplanFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateYJEMERPLAN(form.value); }
        else { await addYJEMERPLAN(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: YJEMERPLANVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delYJEMERPLAN(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/yjemerplan/export', { ...queryParams.value }, '应急预案_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>