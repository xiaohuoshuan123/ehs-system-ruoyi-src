<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="设备名称" prop="equipmentName">
            <el-input v-model="queryParams.equipmentName" placeholder="请输入设备名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="型号" prop="model">
            <el-input v-model="queryParams.model" placeholder="请输入型号" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="数量" prop="count">
            <el-input v-model="queryParams.count" placeholder="请输入数量" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="购置时间" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入购置时间" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="下次检验日期" prop="field2">
            <el-input v-model="queryParams.field2" placeholder="请输入下次检验日期" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="存放地点" prop="field3">
            <el-input v-model="queryParams.field3" placeholder="请输入存放地点" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>应急物资列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:YJEMTERIAL:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:YJEMTERIAL:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:YJEMTERIAL:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:YJEMTERIAL:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="应急物资ID" align="center" prop="id" width="160" />
        <el-table-column label="设备名称" align="left" prop="equipmentName" show-overflow-tooltip min-width="120" />
        <el-table-column label="型号" align="left" prop="model" show-overflow-tooltip min-width="120" />
        <el-table-column label="数量" align="left" prop="count" show-overflow-tooltip min-width="120" />
        <el-table-column label="购置时间" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="下次检验日期" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="存放地点" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="用途" align="left" prop="field4" show-overflow-tooltip min-width="120" />
        <el-table-column label="设备状况" align="left" prop="equipment" show-overflow-tooltip min-width="120" />
        <el-table-column label="填报单位" align="left" prop="field5" show-overflow-tooltip min-width="120" />
        <el-table-column label="联系人" align="left" prop="field6" show-overflow-tooltip min-width="120" />
        <el-table-column label="联系电话" align="left" prop="field7" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:YJEMTERIAL:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:YJEMTERIAL:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="yjemterialFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="设备名称" prop="equipmentName">
              <el-input v-model="form.equipmentName" placeholder="请输入设备名称" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="型号" prop="model">
              <el-input v-model="form.model" placeholder="请输入型号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="数量" prop="count">
              <el-input v-model="form.count" placeholder="请输入数量" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="购置时间" prop="field">
              <el-input v-model="form.field" placeholder="请输入购置时间" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="下次检验日期" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入下次检验日期" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="存放地点" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入存放地点" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="用途" prop="field4">
              <el-input v-model="form.field4" placeholder="请输入用途" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="设备状况" prop="equipment">
              <el-input v-model="form.equipment" placeholder="请输入设备状况" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="填报单位" prop="field5">
              <el-input v-model="form.field5" placeholder="请输入填报单位" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="联系人" prop="field6">
              <el-input v-model="form.field6" placeholder="请输入联系人" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="联系电话" prop="field7">
              <el-input v-model="form.field7" placeholder="请输入联系电话" />
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

<script setup name="YJEMTERIAL" lang="ts">
import { addYJEMTERIAL, delYJEMTERIAL, getYJEMTERIAL, listYJEMTERIAL, updateYJEMTERIAL } from '@/api/scyh/yjemterial';
import { YJEMTERIALForm, YJEMTERIALQuery, YJEMTERIALVO } from '@/api/scyh/yjemterial/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<YJEMTERIALVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const yjemterialFormRef = ref<ElFormInstance>();

const initFormData: YJEMTERIALForm = {
    equipmentName: undefined,
    model: undefined,
    count: undefined,
    field: undefined,
    field2: undefined,
    field3: undefined,
    field4: undefined,
    equipment: undefined,
    field5: undefined,
    field6: undefined,
    field7: undefined,
}
const data = reactive<PageData<YJEMTERIALForm, YJEMTERIALQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    equipmentName: undefined,
    model: undefined,
    count: undefined,
    field: undefined,
    field2: undefined,
    field3: undefined,
  },
  rules: {
    equipmentName: [
      { required: true, message: "设备名称不能为空", trigger: "blur" }
    ],
    model: [
      { required: true, message: "型号不能为空", trigger: "blur" }
    ],
    count: [
      { required: true, message: "数量不能为空", trigger: "blur" }
    ],
    field: [
      { required: true, message: "购置时间不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listYJEMTERIAL(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; yjemterialFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: YJEMTERIALVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增应急物资'; dialog.visible = true; }
const handleUpdate = async (row?: YJEMTERIALVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getYJEMTERIAL(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改应急物资'; dialog.visible = true;
}
const submitForm = () => {
  yjemterialFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateYJEMTERIAL(form.value); }
        else { await addYJEMTERIAL(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: YJEMTERIALVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delYJEMTERIAL(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/yjemterial/export', { ...queryParams.value }, '应急物资_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>