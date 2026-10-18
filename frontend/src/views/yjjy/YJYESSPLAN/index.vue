<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="填报状态" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入填报状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="审批状态" prop="approveStatus">
            <el-input v-model="queryParams.approveStatus" placeholder="请输入审批状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="当前处理人" prop="field2">
            <el-input v-model="queryParams.field2" placeholder="请输入当前处理人" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="单位名称" prop="unitName">
            <el-input v-model="queryParams.unitName" placeholder="请输入单位名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="演练类型" prop="drillType">
            <el-input v-model="queryParams.drillType" placeholder="请输入演练类型" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="预案名称" prop="planName">
            <el-input v-model="queryParams.planName" placeholder="请输入预案名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>演练实施列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:YJYESSPLAN:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:YJYESSPLAN:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:YJYESSPLAN:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:YJYESSPLAN:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="应急演练评估ID" align="center" prop="id" width="160" />
        <el-table-column label="填报状态" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="审批状态" align="left" prop="approveStatus" show-overflow-tooltip min-width="120" />
        <el-table-column label="当前处理人" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="单位名称" align="left" prop="unitName" show-overflow-tooltip min-width="120" />
        <el-table-column label="演练类型" align="left" prop="drillType" show-overflow-tooltip min-width="120" />
        <el-table-column label="预案名称" align="left" prop="planName" show-overflow-tooltip min-width="120" />
        <el-table-column label="预案等级" align="left" prop="planLevel" show-overflow-tooltip min-width="120" />
        <el-table-column label="计划时间" align="center" prop="planTime" width="120" show-overflow-tooltip />
        <el-table-column label="演练日期" align="center" prop="drillDate" width="120" show-overflow-tooltip />
        <el-table-column label="参演人数" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="演练投入(万元)" align="left" prop="drill" show-overflow-tooltip min-width="120" />
        <el-table-column label="计划时间差异分析" align="center" prop="planTime2" width="120" show-overflow-tooltip />
        <el-table-column label="附件数量" align="left" prop="attachmentCount" show-overflow-tooltip min-width="120" />
        <el-table-column label="修改人" align="left" prop="field4" show-overflow-tooltip min-width="120" />
        <el-table-column label="修改时间" align="left" prop="field5" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:YJYESSPLAN:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:YJYESSPLAN:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="yjyessplanFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="填报状态" prop="field">
              <el-input v-model="form.field" placeholder="请输入填报状态" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="审批状态" prop="approveStatus">
              <el-input v-model="form.approveStatus" placeholder="请输入审批状态" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="当前处理人" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入当前处理人" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="单位名称" prop="unitName">
              <el-input v-model="form.unitName" placeholder="请输入单位名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="演练类型" prop="drillType">
              <el-input v-model="form.drillType" placeholder="请输入演练类型" />
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
            <el-form-item label="预案等级" prop="planLevel">
              <el-input v-model="form.planLevel" placeholder="请输入预案等级" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="计划时间" prop="planTime">
              <el-date-picker v-model="form.planTime" type="datetime" placeholder="选择计划时间" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="演练日期" prop="drillDate">
              <el-date-picker v-model="form.drillDate" type="datetime" placeholder="选择演练日期" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="参演人数" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入参演人数" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="演练投入(万元)" prop="drill">
              <el-input v-model="form.drill" placeholder="请输入演练投入(万元)" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="计划时间差异分析" prop="planTime2">
              <el-date-picker v-model="form.planTime2" type="datetime" placeholder="选择计划时间差异分析" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
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
            <el-form-item label="修改人" prop="field4">
              <el-input v-model="form.field4" placeholder="请输入修改人" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="修改时间" prop="field5">
              <el-input v-model="form.field5" placeholder="请输入修改时间" />
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

<script setup name="YJYESSPLAN" lang="ts">
import { addYJYESSPLAN, delYJYESSPLAN, getYJYESSPLAN, listYJYESSPLAN, updateYJYESSPLAN } from '@/api/scyh/yjyessplan';
import { YJYESSPLANForm, YJYESSPLANQuery, YJYESSPLANVO } from '@/api/scyh/yjyessplan/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<YJYESSPLANVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const yjyessplanFormRef = ref<ElFormInstance>();

const initFormData: YJYESSPLANForm = {
    field: undefined,
    approveStatus: undefined,
    field2: undefined,
    unitName: undefined,
    drillType: undefined,
    planName: undefined,
    planLevel: undefined,
    planTime: undefined,
    drillDate: undefined,
    field3: undefined,
    drill: undefined,
    planTime2: undefined,
    attachmentCount: undefined,
    field4: undefined,
    field5: undefined,
}
const data = reactive<PageData<YJYESSPLANForm, YJYESSPLANQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    field: undefined,
    approveStatus: undefined,
    field2: undefined,
    unitName: undefined,
    drillType: undefined,
    planName: undefined,
  },
  rules: {
    field: [
      { required: true, message: "填报状态不能为空", trigger: "blur" }
    ],
    approveStatus: [
      { required: true, message: "审批状态不能为空", trigger: "blur" }
    ],
    field2: [
      { required: true, message: "当前处理人不能为空", trigger: "blur" }
    ],
    unitName: [
      { required: true, message: "单位名称不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listYJYESSPLAN(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; yjyessplanFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: YJYESSPLANVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增演练实施'; dialog.visible = true; }
const handleUpdate = async (row?: YJYESSPLANVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getYJYESSPLAN(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改演练实施'; dialog.visible = true;
}
const submitForm = () => {
  yjyessplanFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateYJYESSPLAN(form.value); }
        else { await addYJYESSPLAN(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: YJYESSPLANVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delYJYESSPLAN(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/yjyessplan/export', { ...queryParams.value }, '演练实施_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>