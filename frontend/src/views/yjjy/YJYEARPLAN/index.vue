<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="填报状态" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入填报状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-form-item label="预案等级" prop="planLevel">
            <el-input v-model="queryParams.planLevel" placeholder="请输入预案等级" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="计划时间" prop="planTime">
            <el-input v-model="queryParams.planTime" placeholder="请输入计划时间" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>年度演练计划列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:YJYEARPLAN:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:YJYEARPLAN:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:YJYEARPLAN:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:YJYEARPLAN:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="应急演练评估ID" align="center" prop="id" width="160" />
        <el-table-column label="填报状态" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="单位名称" align="left" prop="unitName" show-overflow-tooltip min-width="120" />
        <el-table-column label="演练类型" align="left" prop="drillType" show-overflow-tooltip min-width="120" />
        <el-table-column label="预案名称" align="left" prop="planName" show-overflow-tooltip min-width="120" />
        <el-table-column label="预案等级" align="left" prop="planLevel" show-overflow-tooltip min-width="120" />
        <el-table-column label="计划时间" align="center" prop="planTime" width="120" show-overflow-tooltip />
        <el-table-column label="附件数量" align="left" prop="attachmentCount" show-overflow-tooltip min-width="120" />
        <el-table-column label="修改人" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="修改时间" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:YJYEARPLAN:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:YJYEARPLAN:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="yjyearplanFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="填报状态" prop="field">
              <el-input v-model="form.field" placeholder="请输入填报状态" />
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
            <el-form-item label="附件数量" prop="attachmentCount">
              <el-input v-model="form.attachmentCount" placeholder="请输入附件数量" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="修改人" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入修改人" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="修改时间" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入修改时间" />
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

<script setup name="YJYEARPLAN" lang="ts">
import { addYJYEARPLAN, delYJYEARPLAN, getYJYEARPLAN, listYJYEARPLAN, updateYJYEARPLAN } from '@/api/scyh/yjyearplan';
import { YJYEARPLANForm, YJYEARPLANQuery, YJYEARPLANVO } from '@/api/scyh/yjyearplan/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<YJYEARPLANVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const yjyearplanFormRef = ref<ElFormInstance>();

const initFormData: YJYEARPLANForm = {
    field: undefined,
    unitName: undefined,
    drillType: undefined,
    planName: undefined,
    planLevel: undefined,
    planTime: undefined,
    attachmentCount: undefined,
    field2: undefined,
    field3: undefined,
}
const data = reactive<PageData<YJYEARPLANForm, YJYEARPLANQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    field: undefined,
    unitName: undefined,
    drillType: undefined,
    planName: undefined,
    planLevel: undefined,
    planTime: undefined,
  },
  rules: {
    field: [
      { required: true, message: "填报状态不能为空", trigger: "blur" }
    ],
    unitName: [
      { required: true, message: "单位名称不能为空", trigger: "blur" }
    ],
    drillType: [
      { required: true, message: "演练类型不能为空", trigger: "blur" }
    ],
    planName: [
      { required: true, message: "预案名称不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listYJYEARPLAN(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; yjyearplanFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: YJYEARPLANVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增年度演练计划'; dialog.visible = true; }
const handleUpdate = async (row?: YJYEARPLANVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getYJYEARPLAN(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改年度演练计划'; dialog.visible = true;
}
const submitForm = () => {
  yjyearplanFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateYJYEARPLAN(form.value); }
        else { await addYJYEARPLAN(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: YJYEARPLANVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delYJYEARPLAN(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/yjyearplan/export', { ...queryParams.value }, '年度演练计划_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>