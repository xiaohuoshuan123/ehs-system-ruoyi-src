<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="工号姓名" prop="empName">
            <el-input v-model="queryParams.empName" placeholder="请输入工号姓名" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="机构" prop="org">
            <el-input v-model="queryParams.org" placeholder="请输入机构" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="queryParams.roleName" placeholder="请输入角色名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="登录时间" prop="loginTime">
            <el-input v-model="queryParams.loginTime" placeholder="请输入登录时间" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="终端类型" prop="terminalType">
            <el-input v-model="queryParams.terminalType" placeholder="请输入终端类型" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="IP地址" prop="ipAddress">
            <el-input v-model="queryParams.ipAddress" placeholder="请输入IP地址" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>登录日志列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:MPLOGINLOG:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:MPLOGINLOG:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:MPLOGINLOG:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:MPLOGINLOG:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="登录日志ID" align="center" prop="id" width="160" />
        <el-table-column label="工号姓名" align="left" prop="empName" show-overflow-tooltip min-width="120" />
        <el-table-column label="机构" align="left" prop="org" show-overflow-tooltip min-width="120" />
        <el-table-column label="角色名称" align="left" prop="roleName" show-overflow-tooltip min-width="120" />
        <el-table-column label="登录时间" align="center" prop="loginTime" width="120" show-overflow-tooltip />
        <el-table-column label="终端类型" align="left" prop="terminalType" show-overflow-tooltip min-width="120" />
        <el-table-column label="IP地址" align="left" prop="ipAddress" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:MPLOGINLOG:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:MPLOGINLOG:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="mploginlogFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="工号姓名" prop="empName">
              <el-input v-model="form.empName" placeholder="请输入工号姓名" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="机构" prop="org">
              <el-input v-model="form.org" placeholder="请输入机构" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="角色名称" prop="roleName">
              <el-input v-model="form.roleName" placeholder="请输入角色名称" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="登录时间" prop="loginTime">
              <el-date-picker v-model="form.loginTime" type="datetime" placeholder="选择登录时间" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="终端类型" prop="terminalType">
              <el-input v-model="form.terminalType" placeholder="请输入终端类型" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="IP地址" prop="ipAddress">
              <el-input v-model="form.ipAddress" placeholder="请输入IP地址" />
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

<script setup name="MPLOGINLOG" lang="ts">
import { addMPLOGINLOG, delMPLOGINLOG, getMPLOGINLOG, listMPLOGINLOG, updateMPLOGINLOG } from '@/api/scyh/mploginlog';
import { MPLOGINLOGForm, MPLOGINLOGQuery, MPLOGINLOGVO } from '@/api/scyh/mploginlog/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<MPLOGINLOGVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const mploginlogFormRef = ref<ElFormInstance>();

const initFormData: MPLOGINLOGForm = {
    empName: undefined,
    org: undefined,
    roleName: undefined,
    loginTime: undefined,
    terminalType: undefined,
    ipAddress: undefined,
}
const data = reactive<PageData<MPLOGINLOGForm, MPLOGINLOGQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    empName: undefined,
    org: undefined,
    roleName: undefined,
    loginTime: undefined,
    terminalType: undefined,
    ipAddress: undefined,
  },
  rules: {
    empName: [
      { required: true, message: "工号姓名不能为空", trigger: "blur" }
    ],
    org: [
      { required: true, message: "机构不能为空", trigger: "blur" }
    ],
    roleName: [
      { required: true, message: "角色名称不能为空", trigger: "blur" }
    ],
    loginTime: [
      { required: true, message: "登录时间不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listMPLOGINLOG(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; mploginlogFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: MPLOGINLOGVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增登录日志'; dialog.visible = true; }
const handleUpdate = async (row?: MPLOGINLOGVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getMPLOGINLOG(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改登录日志'; dialog.visible = true;
}
const submitForm = () => {
  mploginlogFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateMPLOGINLOG(form.value); }
        else { await addMPLOGINLOG(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: MPLOGINLOGVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delMPLOGINLOG(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/mploginlog/export', { ...queryParams.value }, '登录日志_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>