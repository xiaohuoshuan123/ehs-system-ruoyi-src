<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="风险管控主项ID" prop="id2">
            <el-input v-model="queryParams.id2" placeholder="请输入风险管控主项ID" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="完成状态" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入完成状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="机构名称" prop="orgName">
            <el-input v-model="queryParams.orgName" placeholder="请输入机构名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="作业单元" prop="field2">
            <el-input v-model="queryParams.field2" placeholder="请输入作业单元" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="作业步骤/设备设施" prop="field3">
            <el-input v-model="queryParams.field3" placeholder="请输入作业步骤/设备设施" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="危险源ID" prop="danger">
            <el-input v-model="queryParams.danger" placeholder="请输入危险源ID" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>控制绩效评价列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:SCCORMANCE:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:SCCORMANCE:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:SCCORMANCE:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:SCCORMANCE:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="评审ID" align="center" prop="id" width="160" />
        <el-table-column label="风险管控主项ID" align="left" prop="id2" show-overflow-tooltip min-width="120" />
        <el-table-column label="完成状态" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="机构名称" align="left" prop="orgName" show-overflow-tooltip min-width="120" />
        <el-table-column label="作业单元" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="作业步骤/设备设施" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="危险源ID" align="left" prop="danger" show-overflow-tooltip min-width="120" />
        <el-table-column label="危险源编号" align="left" prop="danger2" show-overflow-tooltip min-width="120" />
        <el-table-column label="第一类危险源" align="left" prop="field4" show-overflow-tooltip min-width="120" />
        <el-table-column label="第二类危险源" align="left" prop="field5" show-overflow-tooltip min-width="120" />
        <el-table-column label="前固有风险等级" align="left" prop="field6" show-overflow-tooltip min-width="120" />
        <el-table-column label="前控制风险等级" align="left" prop="field7" show-overflow-tooltip min-width="120" />
        <el-table-column label="现固有风险等级" align="left" prop="field8" show-overflow-tooltip min-width="120" />
        <el-table-column label="现控制风险等级" align="left" prop="field9" show-overflow-tooltip min-width="120" />
        <el-table-column label="控制绩效评价" align="left" prop="field10" show-overflow-tooltip min-width="120" />
        <el-table-column label="评审日期" align="left" prop="field11" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:SCCORMANCE:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:SCCORMANCE:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="sccormanceFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="风险管控主项ID" prop="id2">
              <el-input v-model="form.id2" placeholder="请输入风险管控主项ID" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="完成状态" prop="field">
              <el-input v-model="form.field" placeholder="请输入完成状态" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="机构名称" prop="orgName">
              <el-input v-model="form.orgName" placeholder="请输入机构名称" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="作业单元" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入作业单元" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="作业步骤/设备设施" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入作业步骤/设备设施" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="危险源ID" prop="danger">
              <el-input v-model="form.danger" placeholder="请输入危险源ID" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="危险源编号" prop="danger2">
              <el-input v-model="form.danger2" placeholder="请输入危险源编号" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="第一类危险源" prop="field4">
              <el-input v-model="form.field4" placeholder="请输入第一类危险源" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="第二类危险源" prop="field5">
              <el-input v-model="form.field5" placeholder="请输入第二类危险源" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="前固有风险等级" prop="field6">
              <el-input v-model="form.field6" placeholder="请输入前固有风险等级" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="前控制风险等级" prop="field7">
              <el-input v-model="form.field7" placeholder="请输入前控制风险等级" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="现固有风险等级" prop="field8">
              <el-input v-model="form.field8" placeholder="请输入现固有风险等级" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="现控制风险等级" prop="field9">
              <el-input v-model="form.field9" placeholder="请输入现控制风险等级" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="控制绩效评价" prop="field10">
              <el-input v-model="form.field10" placeholder="请输入控制绩效评价" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="评审日期" prop="field11">
              <el-input v-model="form.field11" placeholder="请输入评审日期" />
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

<script setup name="SCCORMANCE" lang="ts">
import { addSCCORMANCE, delSCCORMANCE, getSCCORMANCE, listSCCORMANCE, updateSCCORMANCE } from '@/api/scyh/sccormance';
import { SCCORMANCEForm, SCCORMANCEQuery, SCCORMANCEVO } from '@/api/scyh/sccormance/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<SCCORMANCEVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const sccormanceFormRef = ref<ElFormInstance>();

const initFormData: SCCORMANCEForm = {
    id2: undefined,
    field: undefined,
    orgName: undefined,
    field2: undefined,
    field3: undefined,
    danger: undefined,
    danger2: undefined,
    field4: undefined,
    field5: undefined,
    field6: undefined,
    field7: undefined,
    field8: undefined,
    field9: undefined,
    field10: undefined,
    field11: undefined,
}
const data = reactive<PageData<SCCORMANCEForm, SCCORMANCEQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    id2: undefined,
    field: undefined,
    orgName: undefined,
    field2: undefined,
    field3: undefined,
    danger: undefined,
  },
  rules: {
    id2: [
      { required: true, message: "风险管控主项ID不能为空", trigger: "blur" }
    ],
    field: [
      { required: true, message: "完成状态不能为空", trigger: "blur" }
    ],
    orgName: [
      { required: true, message: "机构名称不能为空", trigger: "blur" }
    ],
    field2: [
      { required: true, message: "作业单元不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listSCCORMANCE(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; sccormanceFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: SCCORMANCEVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增控制绩效评价'; dialog.visible = true; }
const handleUpdate = async (row?: SCCORMANCEVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getSCCORMANCE(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改控制绩效评价'; dialog.visible = true;
}
const submitForm = () => {
  sccormanceFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateSCCORMANCE(form.value); }
        else { await addSCCORMANCE(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: SCCORMANCEVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delSCCORMANCE(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/sccormance/export', { ...queryParams.value }, '控制绩效评价_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>