<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="证书配置表ID" prop="id2">
            <el-input v-model="queryParams.id2" placeholder="请输入证书配置表ID" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="是否合规" prop="isCompliant">
            <el-input v-model="queryParams.isCompliant" placeholder="请输入是否合规" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="配置编码" prop="configCode">
            <el-input v-model="queryParams.configCode" placeholder="请输入配置编码" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="组织编码" prop="orgCode">
            <el-input v-model="queryParams.orgCode" placeholder="请输入组织编码" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="所属机构" prop="orgName">
            <el-input v-model="queryParams.orgName" placeholder="请输入所属机构" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="证书类型" prop="certificateType">
            <el-input v-model="queryParams.certificateType" placeholder="请输入证书类型" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>证书配置列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:PXCECONFIG:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:PXCECONFIG:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:PXCECONFIG:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:PXCECONFIG:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="证书配置表明细ID" align="center" prop="id" width="160" />
        <el-table-column label="证书配置表ID" align="left" prop="id2" show-overflow-tooltip min-width="120" />
        <el-table-column label="是否合规" align="left" prop="isCompliant" show-overflow-tooltip min-width="120" />
        <el-table-column label="配置编码" align="left" prop="configCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="组织编码" align="left" prop="orgCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="所属机构" align="left" prop="orgName" show-overflow-tooltip min-width="120" />
        <el-table-column label="证书类型" align="left" prop="certificateType" show-overflow-tooltip min-width="120" />
        <el-table-column label="作业类别" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="需持证数量" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="实际持证数量" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="备注" align="left" prop="remark" show-overflow-tooltip min-width="120" />
        <el-table-column label="所属基地" align="left" prop="field4" show-overflow-tooltip min-width="120" />
        <el-table-column label="扩展字段1:系统上线前不使用" align="left" prop="extField1" show-overflow-tooltip min-width="120" />
        <el-table-column label="扩展字段2:系统上线前不使用" align="left" prop="extField2" show-overflow-tooltip min-width="120" />
        <el-table-column label="扩展字段3:系统上线前不使用" align="left" prop="extField3" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人工号" align="left" prop="createByEmpCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人姓名" align="left" prop="createByName" show-overflow-tooltip min-width="120" />
        <el-table-column label="修改人工号" align="left" prop="field5" show-overflow-tooltip min-width="120" />
        <el-table-column label="修改人" align="left" prop="field6" show-overflow-tooltip min-width="120" />
        <el-table-column label="修改时间" align="left" prop="field7" show-overflow-tooltip min-width="120" />
        <el-table-column label="删除人工号" align="left" prop="delete" show-overflow-tooltip min-width="120" />
        <el-table-column label="删除人姓名" align="left" prop="delete2" show-overflow-tooltip min-width="120" />
        <el-table-column label="删除时间" align="center" prop="deleteTime" width="120" show-overflow-tooltip />
        <el-table-column label="删除标记" align="left" prop="delete3" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:PXCECONFIG:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:PXCECONFIG:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="pxceconfigFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="证书配置表ID" prop="id2">
              <el-input v-model="form.id2" placeholder="请输入证书配置表ID" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="是否合规" prop="isCompliant">
              <el-input v-model="form.isCompliant" placeholder="请输入是否合规" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="配置编码" prop="configCode">
              <el-input v-model="form.configCode" placeholder="请输入配置编码" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="组织编码" prop="orgCode">
              <el-input v-model="form.orgCode" placeholder="请输入组织编码" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="所属机构" prop="orgName">
              <el-input v-model="form.orgName" placeholder="请输入所属机构" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="证书类型" prop="certificateType">
              <el-input v-model="form.certificateType" placeholder="请输入证书类型" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="作业类别" prop="field">
              <el-input v-model="form.field" placeholder="请输入作业类别" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="需持证数量" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入需持证数量" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="实际持证数量" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入实际持证数量" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入备注" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="所属基地" prop="field4">
              <el-input v-model="form.field4" placeholder="请输入所属基地" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="扩展字段1:系统上线前不使用" prop="extField1">
              <el-input v-model="form.extField1" placeholder="请输入扩展字段1:系统上线前不使用" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="扩展字段2:系统上线前不使用" prop="extField2">
              <el-input v-model="form.extField2" placeholder="请输入扩展字段2:系统上线前不使用" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="扩展字段3:系统上线前不使用" prop="extField3">
              <el-input v-model="form.extField3" placeholder="请输入扩展字段3:系统上线前不使用" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="创建人工号" prop="createByEmpCode">
              <el-input v-model="form.createByEmpCode" placeholder="请输入创建人工号" />
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
            <el-form-item label="修改人工号" prop="field5">
              <el-input v-model="form.field5" placeholder="请输入修改人工号" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="修改人" prop="field6">
              <el-input v-model="form.field6" placeholder="请输入修改人" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="修改时间" prop="field7">
              <el-input v-model="form.field7" placeholder="请输入修改时间" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="删除人工号" prop="delete">
              <el-input v-model="form.delete" placeholder="请输入删除人工号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="删除人姓名" prop="delete2">
              <el-input v-model="form.delete2" placeholder="请输入删除人姓名" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="删除时间" prop="deleteTime">
              <el-date-picker v-model="form.deleteTime" type="datetime" placeholder="选择删除时间" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="删除标记" prop="delete3">
              <el-input v-model="form.delete3" placeholder="请输入删除标记" />
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

<script setup name="PXCECONFIG" lang="ts">
import { addPXCECONFIG, delPXCECONFIG, getPXCECONFIG, listPXCECONFIG, updatePXCECONFIG } from '@/api/scyh/pxceconfig';
import { PXCECONFIGForm, PXCECONFIGQuery, PXCECONFIGVO } from '@/api/scyh/pxceconfig/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<PXCECONFIGVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const pxceconfigFormRef = ref<ElFormInstance>();

const initFormData: PXCECONFIGForm = {
    id2: undefined,
    isCompliant: undefined,
    configCode: undefined,
    orgCode: undefined,
    orgName: undefined,
    certificateType: undefined,
    field: undefined,
    field2: undefined,
    field3: undefined,
    remark: undefined,
    field4: undefined,
    extField1: undefined,
    extField2: undefined,
    extField3: undefined,
    createByEmpCode: undefined,
    createByName: undefined,
    field5: undefined,
    field6: undefined,
    field7: undefined,
    delete: undefined,
    delete2: undefined,
    deleteTime: undefined,
    delete3: undefined,
}
const data = reactive<PageData<PXCECONFIGForm, PXCECONFIGQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    id2: undefined,
    isCompliant: undefined,
    configCode: undefined,
    orgCode: undefined,
    orgName: undefined,
    certificateType: undefined,
  },
  rules: {
    id2: [
      { required: true, message: "证书配置表ID不能为空", trigger: "blur" }
    ],
    isCompliant: [
      { required: true, message: "是否合规不能为空", trigger: "blur" }
    ],
    configCode: [
      { required: true, message: "配置编码不能为空", trigger: "blur" }
    ],
    orgCode: [
      { required: true, message: "组织编码不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listPXCECONFIG(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; pxceconfigFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: PXCECONFIGVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增证书配置'; dialog.visible = true; }
const handleUpdate = async (row?: PXCECONFIGVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getPXCECONFIG(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改证书配置'; dialog.visible = true;
}
const submitForm = () => {
  pxceconfigFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updatePXCECONFIG(form.value); }
        else { await addPXCECONFIG(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: PXCECONFIGVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delPXCECONFIG(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/pxceconfig/export', { ...queryParams.value }, '证书配置_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>