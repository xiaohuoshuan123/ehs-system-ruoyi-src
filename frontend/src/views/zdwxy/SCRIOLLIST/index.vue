<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="审核状态" prop="auditStatus">
            <el-input v-model="queryParams.auditStatus" placeholder="请输入审核状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="检查表状态" prop="check">
            <el-input v-model="queryParams.check" placeholder="请输入检查表状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="所属机构" prop="orgName">
            <el-input v-model="queryParams.orgName" placeholder="请输入所属机构" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="作业步骤" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入作业步骤" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="设备设施/作业设备" prop="equipment">
            <el-input v-model="queryParams.equipment" placeholder="请输入设备设施/作业设备" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="类别划分" prop="field2">
            <el-input v-model="queryParams.field2" placeholder="请输入类别划分" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>风险管控列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:SCRIOLLIST:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:SCRIOLLIST:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:SCRIOLLIST:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:SCRIOLLIST:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="风险管控主项ID" align="center" prop="id" width="160" />
        <el-table-column label="审核状态" align="left" prop="auditStatus" show-overflow-tooltip min-width="120" />
        <el-table-column label="检查表状态" align="left" prop="check" show-overflow-tooltip min-width="120" />
        <el-table-column label="所属机构" align="left" prop="orgName" show-overflow-tooltip min-width="120" />
        <el-table-column label="作业步骤" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="设备设施/作业设备" align="left" prop="equipment" show-overflow-tooltip min-width="120" />
        <el-table-column label="类别划分" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="岗位" align="left" prop="post" show-overflow-tooltip min-width="120" />
        <el-table-column label="危险源编号" align="left" prop="danger" show-overflow-tooltip min-width="120" />
        <el-table-column label="第一类危险源" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="第二类危险源" align="left" prop="field4" show-overflow-tooltip min-width="120" />
        <el-table-column label="固有风险等级" align="left" prop="field5" show-overflow-tooltip min-width="120" />
        <el-table-column label="控制风险等级" align="left" prop="field6" show-overflow-tooltip min-width="120" />
        <el-table-column label="管控类型" align="left" prop="field7" show-overflow-tooltip min-width="120" />
        <el-table-column label="可能发生的事故或职业病" align="left" prop="field8" show-overflow-tooltip min-width="120" />
        <el-table-column label="匹配隐患" align="left" prop="field9" show-overflow-tooltip min-width="120" />
        <el-table-column label="状态" align="center" prop="status" width="80">
          <template #default="scope">
            <el-tag :type="scope.row.status === '0' ? 'danger' : 'success'" size="small">
              {{ scope.row.status === '0' ? '停用' : '正常' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="责任人" align="left" prop="duty" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人" align="left" prop="createBy" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="更新人" align="center" prop="updateBy" width="120" show-overflow-tooltip />
        <el-table-column label="更新时间" align="center" prop="updateTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:SCRIOLLIST:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:SCRIOLLIST:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="scriollistFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="审核状态" prop="auditStatus">
              <el-input v-model="form.auditStatus" placeholder="请输入审核状态" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="检查表状态" prop="check">
              <el-input v-model="form.check" placeholder="请输入检查表状态" />
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
            <el-form-item label="作业步骤" prop="field">
              <el-input v-model="form.field" placeholder="请输入作业步骤" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="设备设施/作业设备" prop="equipment">
              <el-input v-model="form.equipment" placeholder="请输入设备设施/作业设备" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="类别划分" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入类别划分" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="岗位" prop="post">
              <el-input v-model="form.post" placeholder="请输入岗位" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="危险源编号" prop="danger">
              <el-input v-model="form.danger" placeholder="请输入危险源编号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="第一类危险源" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入第一类危险源" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="第二类危险源" prop="field4">
              <el-input v-model="form.field4" placeholder="请输入第二类危险源" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="固有风险等级" prop="field5">
              <el-input v-model="form.field5" placeholder="请输入固有风险等级" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="控制风险等级" prop="field6">
              <el-input v-model="form.field6" placeholder="请输入控制风险等级" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="管控类型" prop="field7">
              <el-input v-model="form.field7" placeholder="请输入管控类型" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="可能发生的事故或职业病" prop="field8">
              <el-input v-model="form.field8" placeholder="请输入可能发生的事故或职业病" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="匹配隐患" prop="field9">
              <el-input v-model="form.field9" placeholder="请输入匹配隐患" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态" style="width: 100%">
                <el-option label="正常" value="0" />
                <el-option label="停用" value="1" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="责任人" prop="duty">
              <el-input v-model="form.duty" placeholder="请输入责任人" />
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

<script setup name="SCRIOLLIST" lang="ts">
import { addSCRIOLLIST, delSCRIOLLIST, getSCRIOLLIST, listSCRIOLLIST, updateSCRIOLLIST } from '@/api/scyh/scriollist';
import { SCRIOLLISTForm, SCRIOLLISTQuery, SCRIOLLISTVO } from '@/api/scyh/scriollist/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<SCRIOLLISTVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const scriollistFormRef = ref<ElFormInstance>();

const initFormData: SCRIOLLISTForm = {
    auditStatus: undefined,
    check: undefined,
    orgName: undefined,
    field: undefined,
    equipment: undefined,
    field2: undefined,
    post: undefined,
    danger: undefined,
    field3: undefined,
    field4: undefined,
    field5: undefined,
    field6: undefined,
    field7: undefined,
    field8: undefined,
    field9: undefined,
    status: undefined,
    duty: undefined,
}
const data = reactive<PageData<SCRIOLLISTForm, SCRIOLLISTQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    auditStatus: undefined,
    check: undefined,
    orgName: undefined,
    field: undefined,
    equipment: undefined,
    field2: undefined,
  },
  rules: {
    auditStatus: [
      { required: true, message: "审核状态不能为空", trigger: "blur" }
    ],
    check: [
      { required: true, message: "检查表状态不能为空", trigger: "blur" }
    ],
    orgName: [
      { required: true, message: "所属机构不能为空", trigger: "blur" }
    ],
    field: [
      { required: true, message: "作业步骤不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listSCRIOLLIST(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; scriollistFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: SCRIOLLISTVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增风险管控'; dialog.visible = true; }
const handleUpdate = async (row?: SCRIOLLISTVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getSCRIOLLIST(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改风险管控'; dialog.visible = true;
}
const submitForm = () => {
  scriollistFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateSCRIOLLIST(form.value); }
        else { await addSCRIOLLIST(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: SCRIOLLISTVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delSCRIOLLIST(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/scriollist/export', { ...queryParams.value }, '风险管控_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>