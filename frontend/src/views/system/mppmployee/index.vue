<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="工号" prop="empCode">
            <el-input v-model="queryParams.empCode" placeholder="请输入工号" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="queryParams.name" placeholder="请输入姓名" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="姓名拼音" prop="name2">
            <el-input v-model="queryParams.name2" placeholder="请输入姓名拼音" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="姓名简拼" prop="name3">
            <el-input v-model="queryParams.name3" placeholder="请输入姓名简拼" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="曾用名" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入曾用名" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="性别" prop="gender">
            <el-input v-model="queryParams.gender" placeholder="请输入性别" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>人员管理列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:MPMPPLOYEE:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:MPMPPLOYEE:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:MPMPPLOYEE:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:MPMPPLOYEE:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="工号" align="left" prop="empCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="姓名" align="left" prop="name" show-overflow-tooltip min-width="120" />
        <el-table-column label="姓名拼音" align="left" prop="name2" show-overflow-tooltip min-width="120" />
        <el-table-column label="姓名简拼" align="left" prop="name3" show-overflow-tooltip min-width="120" />
        <el-table-column label="曾用名" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="性别" align="left" prop="gender" show-overflow-tooltip min-width="120" />
        <el-table-column label="出生日期" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="证件号码" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="证件类型" align="left" prop="field4" show-overflow-tooltip min-width="120" />
        <el-table-column label="民族" align="left" prop="field5" show-overflow-tooltip min-width="120" />
        <el-table-column label="籍贯" align="left" prop="field6" show-overflow-tooltip min-width="120" />
        <el-table-column label="所属机构" align="left" prop="orgName" show-overflow-tooltip min-width="120" />
        <el-table-column label="政治面貌" align="left" prop="field7" show-overflow-tooltip min-width="120" />
        <el-table-column label="岗位" align="left" prop="post" show-overflow-tooltip min-width="120" />
        <el-table-column label="用户角色" align="left" prop="field8" show-overflow-tooltip min-width="120" />
        <el-table-column label="来公司日期" align="left" prop="field9" show-overflow-tooltip min-width="120" />
        <el-table-column label="单位电话" align="left" prop="unitPhone" show-overflow-tooltip min-width="120" />
        <el-table-column label="电子邮箱" align="left" prop="field10" show-overflow-tooltip min-width="120" />
        <el-table-column label="当前职称" align="left" prop="field11" show-overflow-tooltip min-width="120" />
        <el-table-column label="当前技能等级" align="left" prop="field12" show-overflow-tooltip min-width="120" />
        <el-table-column label="全日制学历" align="left" prop="field13" show-overflow-tooltip min-width="120" />
        <el-table-column label="全日制学位" align="left" prop="field14" show-overflow-tooltip min-width="120" />
        <el-table-column label="全日制专业" align="left" prop="field15" show-overflow-tooltip min-width="120" />
        <el-table-column label="全日制毕业时间" align="left" prop="field16" show-overflow-tooltip min-width="120" />
        <el-table-column label="是否同步" align="left" prop="isSync" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人" align="left" prop="createBy" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="修改人" align="left" prop="field17" show-overflow-tooltip min-width="120" />
        <el-table-column label="修改时间" align="left" prop="field18" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:MPMPPLOYEE:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:MPMPPLOYEE:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="mpmpployeeFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="工号" prop="empCode">
              <el-input v-model="form.empCode" placeholder="请输入工号" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="form.name" placeholder="请输入姓名" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="姓名拼音" prop="name2">
              <el-input v-model="form.name2" placeholder="请输入姓名拼音" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="姓名简拼" prop="name3">
              <el-input v-model="form.name3" placeholder="请输入姓名简拼" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="曾用名" prop="field">
              <el-input v-model="form.field" placeholder="请输入曾用名" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="性别" prop="gender">
              <el-input v-model="form.gender" placeholder="请输入性别" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="出生日期" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入出生日期" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="证件号码" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入证件号码" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="证件类型" prop="field4">
              <el-input v-model="form.field4" placeholder="请输入证件类型" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="民族" prop="field5">
              <el-input v-model="form.field5" placeholder="请输入民族" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="籍贯" prop="field6">
              <el-input v-model="form.field6" placeholder="请输入籍贯" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="所属机构" prop="orgName">
              <el-input v-model="form.orgName" placeholder="请输入所属机构" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="政治面貌" prop="field7">
              <el-input v-model="form.field7" placeholder="请输入政治面貌" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="岗位" prop="post">
              <el-input v-model="form.post" placeholder="请输入岗位" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="用户角色" prop="field8">
              <el-input v-model="form.field8" placeholder="请输入用户角色" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="来公司日期" prop="field9">
              <el-input v-model="form.field9" placeholder="请输入来公司日期" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="单位电话" prop="unitPhone">
              <el-input v-model="form.unitPhone" placeholder="请输入单位电话" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="电子邮箱" prop="field10">
              <el-input v-model="form.field10" placeholder="请输入电子邮箱" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="当前职称" prop="field11">
              <el-input v-model="form.field11" placeholder="请输入当前职称" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="当前技能等级" prop="field12">
              <el-input v-model="form.field12" placeholder="请输入当前技能等级" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="全日制学历" prop="field13">
              <el-input v-model="form.field13" placeholder="请输入全日制学历" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="全日制学位" prop="field14">
              <el-input v-model="form.field14" placeholder="请输入全日制学位" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="全日制专业" prop="field15">
              <el-input v-model="form.field15" placeholder="请输入全日制专业" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="全日制毕业时间" prop="field16">
              <el-input v-model="form.field16" placeholder="请输入全日制毕业时间" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="是否同步" prop="isSync">
              <el-input v-model="form.isSync" placeholder="请输入是否同步" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="修改人" prop="field17">
              <el-input v-model="form.field17" placeholder="请输入修改人" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="修改时间" prop="field18">
              <el-input v-model="form.field18" placeholder="请输入修改时间" />
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

<script setup name="MPMPPLOYEE" lang="ts">
import { addMPMPPLOYEE, delMPMPPLOYEE, getMPMPPLOYEE, listMPMPPLOYEE, updateMPMPPLOYEE } from '@/api/scyh/mpmpployee';
import { MPMPPLOYEEForm, MPMPPLOYEEQuery, MPMPPLOYEEVO } from '@/api/scyh/mpmpployee/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<MPMPPLOYEEVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const mpmpployeeFormRef = ref<ElFormInstance>();

const initFormData: MPMPPLOYEEForm = {
    empCode: undefined,
    name: undefined,
    name2: undefined,
    name3: undefined,
    field: undefined,
    gender: undefined,
    field2: undefined,
    field3: undefined,
    field4: undefined,
    field5: undefined,
    field6: undefined,
    orgName: undefined,
    field7: undefined,
    post: undefined,
    field8: undefined,
    field9: undefined,
    unitPhone: undefined,
    field10: undefined,
    field11: undefined,
    field12: undefined,
    field13: undefined,
    field14: undefined,
    field15: undefined,
    field16: undefined,
    isSync: undefined,
    field17: undefined,
    field18: undefined,
}
const data = reactive<PageData<MPMPPLOYEEForm, MPMPPLOYEEQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    empCode: undefined,
    name: undefined,
    name2: undefined,
    name3: undefined,
    field: undefined,
    gender: undefined,
  },
  rules: {
    empCode: [
      { required: true, message: "工号不能为空", trigger: "blur" }
    ],
    name: [
      { required: true, message: "姓名不能为空", trigger: "blur" }
    ],
    name2: [
      { required: true, message: "姓名拼音不能为空", trigger: "blur" }
    ],
    name3: [
      { required: true, message: "姓名简拼不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listMPMPPLOYEE(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; mpmpployeeFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: MPMPPLOYEEVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增人员管理'; dialog.visible = true; }
const handleUpdate = async (row?: MPMPPLOYEEVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getMPMPPLOYEE(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改人员管理'; dialog.visible = true;
}
const submitForm = () => {
  mpmpployeeFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateMPMPPLOYEE(form.value); }
        else { await addMPMPPLOYEE(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: MPMPPLOYEEVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delMPMPPLOYEE(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/mpmpployee/export', { ...queryParams.value }, '人员管理_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>