<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="机构编码" prop="orgCode">
            <el-input v-model="queryParams.orgCode" placeholder="请输入机构编码" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="机构全路径" prop="orgFullPath">
            <el-input v-model="queryParams.orgFullPath" placeholder="请输入机构全路径" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="机构名称" prop="orgName">
            <el-input v-model="queryParams.orgName" placeholder="请输入机构名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="机构简称" prop="orgShortName">
            <el-input v-model="queryParams.orgShortName" placeholder="请输入机构简称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="机构类型" prop="orgType">
            <el-input v-model="queryParams.orgType" placeholder="请输入机构类型" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="设立日期" prop="establishDate">
            <el-input v-model="queryParams.establishDate" placeholder="请输入设立日期" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>机构管理列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:MPMPADORG:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:MPMPADORG:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:MPMPADORG:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:MPMPADORG:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="机构ID" align="center" prop="id" width="160" />
        <el-table-column label="机构编码" align="left" prop="orgCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="机构全路径" align="left" prop="orgFullPath" show-overflow-tooltip min-width="120" />
        <el-table-column label="机构名称" align="left" prop="orgName" show-overflow-tooltip min-width="120" />
        <el-table-column label="机构简称" align="left" prop="orgShortName" show-overflow-tooltip min-width="120" />
        <el-table-column label="机构类型" align="left" prop="orgType" show-overflow-tooltip min-width="120" />
        <el-table-column label="设立日期" align="center" prop="establishDate" width="120" show-overflow-tooltip />
        <el-table-column label="撤销日期" align="center" prop="revokeDate" width="120" show-overflow-tooltip />
        <el-table-column label="生效日期" align="center" prop="effectiveDate" width="120" show-overflow-tooltip />
        <el-table-column label="失效日期" align="center" prop="invalidDate" width="120" show-overflow-tooltip />
        <el-table-column label="有效标志" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="显示顺序" align="left" prop="display" show-overflow-tooltip min-width="120" />
        <el-table-column label="地址" align="left" prop="address" show-overflow-tooltip min-width="120" />
        <el-table-column label="机构职能" align="left" prop="org" show-overflow-tooltip min-width="120" />
        <el-table-column label="机构纬度" align="left" prop="orgLatitude" show-overflow-tooltip min-width="120" />
        <el-table-column label="行政纬度上级代码" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="资产维度上级" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="党群维度上级" align="left" prop="field4" show-overflow-tooltip min-width="120" />
        <el-table-column label="工会维度上级" align="left" prop="field5" show-overflow-tooltip min-width="120" />
        <el-table-column label="层级码" align="left" prop="level" show-overflow-tooltip min-width="120" />
        <el-table-column label="行业分类" align="left" prop="industryCategory" show-overflow-tooltip min-width="120" />
        <el-table-column label="行业分类(内部)" align="left" prop="industryCategory2" show-overflow-tooltip min-width="120" />
        <el-table-column label="公司活动状态" align="left" prop="field6" show-overflow-tooltip min-width="120" />
        <el-table-column label="工商注册号" align="left" prop="businessRegister" show-overflow-tooltip min-width="120" />
        <el-table-column label="注册日期" align="center" prop="registerDate" width="120" show-overflow-tooltip />
        <el-table-column label="组织机构代码证代码" align="left" prop="orgOrg" show-overflow-tooltip min-width="120" />
        <el-table-column label="法定代表人(负责人)" align="left" prop="field7" show-overflow-tooltip min-width="120" />
        <el-table-column label="统计登记号" align="left" prop="statistics" show-overflow-tooltip min-width="120" />
        <el-table-column label="主营单位代码" align="left" prop="mainUnit" show-overflow-tooltip min-width="120" />
        <el-table-column label="税务登记号" align="left" prop="tax" show-overflow-tooltip min-width="120" />
        <el-table-column label="备注" align="left" prop="remark" show-overflow-tooltip min-width="120" />
        <el-table-column label="是否显示" align="left" prop="isDisplay" show-overflow-tooltip min-width="120" />
        <el-table-column label="是否同步" align="left" prop="isSync" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:MPMPADORG:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:MPMPADORG:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="mpmpadorgFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="机构编码" prop="orgCode">
              <el-input v-model="form.orgCode" placeholder="请输入机构编码" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="机构全路径" prop="orgFullPath">
              <el-input v-model="form.orgFullPath" placeholder="请输入机构全路径" />
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
            <el-form-item label="机构简称" prop="orgShortName">
              <el-input v-model="form.orgShortName" placeholder="请输入机构简称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="机构类型" prop="orgType">
              <el-input v-model="form.orgType" placeholder="请输入机构类型" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="设立日期" prop="establishDate">
              <el-date-picker v-model="form.establishDate" type="datetime" placeholder="选择设立日期" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="撤销日期" prop="revokeDate">
              <el-date-picker v-model="form.revokeDate" type="datetime" placeholder="选择撤销日期" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="生效日期" prop="effectiveDate">
              <el-date-picker v-model="form.effectiveDate" type="datetime" placeholder="选择生效日期" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="失效日期" prop="invalidDate">
              <el-date-picker v-model="form.invalidDate" type="datetime" placeholder="选择失效日期" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="有效标志" prop="field">
              <el-input v-model="form.field" placeholder="请输入有效标志" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="显示顺序" prop="display">
              <el-input v-model="form.display" placeholder="请输入显示顺序" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="地址" prop="address">
              <el-input v-model="form.address" placeholder="请输入地址" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="机构职能" prop="org">
              <el-input v-model="form.org" placeholder="请输入机构职能" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="机构纬度" prop="orgLatitude">
              <el-input v-model="form.orgLatitude" placeholder="请输入机构纬度" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="行政纬度上级代码" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入行政纬度上级代码" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="资产维度上级" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入资产维度上级" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="党群维度上级" prop="field4">
              <el-input v-model="form.field4" placeholder="请输入党群维度上级" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="工会维度上级" prop="field5">
              <el-input v-model="form.field5" placeholder="请输入工会维度上级" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="层级码" prop="level">
              <el-input v-model="form.level" placeholder="请输入层级码" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="行业分类" prop="industryCategory">
              <el-input v-model="form.industryCategory" placeholder="请输入行业分类" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="行业分类(内部)" prop="industryCategory2">
              <el-input v-model="form.industryCategory2" placeholder="请输入行业分类(内部)" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="公司活动状态" prop="field6">
              <el-input v-model="form.field6" placeholder="请输入公司活动状态" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="工商注册号" prop="businessRegister">
              <el-input v-model="form.businessRegister" placeholder="请输入工商注册号" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="注册日期" prop="registerDate">
              <el-date-picker v-model="form.registerDate" type="datetime" placeholder="选择注册日期" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="组织机构代码证代码" prop="orgOrg">
              <el-input v-model="form.orgOrg" placeholder="请输入组织机构代码证代码" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="法定代表人(负责人)" prop="field7">
              <el-input v-model="form.field7" placeholder="请输入法定代表人(负责人)" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="统计登记号" prop="statistics">
              <el-input v-model="form.statistics" placeholder="请输入统计登记号" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="主营单位代码" prop="mainUnit">
              <el-input v-model="form.mainUnit" placeholder="请输入主营单位代码" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="税务登记号" prop="tax">
              <el-input v-model="form.tax" placeholder="请输入税务登记号" />
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
            <el-form-item label="是否显示" prop="isDisplay">
              <el-input v-model="form.isDisplay" placeholder="请输入是否显示" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="是否同步" prop="isSync">
              <el-input v-model="form.isSync" placeholder="请输入是否同步" />
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

<script setup name="MPMPADORG" lang="ts">
import { addMPMPADORG, delMPMPADORG, getMPMPADORG, listMPMPADORG, updateMPMPADORG } from '@/api/scyh/mpmpadorg';
import { MPMPADORGForm, MPMPADORGQuery, MPMPADORGVO } from '@/api/scyh/mpmpadorg/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<MPMPADORGVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const mpmpadorgFormRef = ref<ElFormInstance>();

const initFormData: MPMPADORGForm = {
    orgCode: undefined,
    orgFullPath: undefined,
    orgName: undefined,
    orgShortName: undefined,
    orgType: undefined,
    establishDate: undefined,
    revokeDate: undefined,
    effectiveDate: undefined,
    invalidDate: undefined,
    field: undefined,
    display: undefined,
    address: undefined,
    org: undefined,
    orgLatitude: undefined,
    field2: undefined,
    field3: undefined,
    field4: undefined,
    field5: undefined,
    level: undefined,
    industryCategory: undefined,
    industryCategory2: undefined,
    field6: undefined,
    businessRegister: undefined,
    registerDate: undefined,
    orgOrg: undefined,
    field7: undefined,
    statistics: undefined,
    mainUnit: undefined,
    tax: undefined,
    remark: undefined,
    isDisplay: undefined,
    isSync: undefined,
}
const data = reactive<PageData<MPMPADORGForm, MPMPADORGQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    orgCode: undefined,
    orgFullPath: undefined,
    orgName: undefined,
    orgShortName: undefined,
    orgType: undefined,
    establishDate: undefined,
  },
  rules: {
    orgCode: [
      { required: true, message: "机构编码不能为空", trigger: "blur" }
    ],
    orgFullPath: [
      { required: true, message: "机构全路径不能为空", trigger: "blur" }
    ],
    orgName: [
      { required: true, message: "机构名称不能为空", trigger: "blur" }
    ],
    orgShortName: [
      { required: true, message: "机构简称不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listMPMPADORG(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; mpmpadorgFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: MPMPADORGVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增机构管理'; dialog.visible = true; }
const handleUpdate = async (row?: MPMPADORGVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getMPMPADORG(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改机构管理'; dialog.visible = true;
}
const submitForm = () => {
  mpmpadorgFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateMPMPADORG(form.value); }
        else { await addMPMPADORG(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: MPMPADORGVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delMPMPADORG(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/mpmpadorg/export', { ...queryParams.value }, '机构管理_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>