<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="单位" prop="unit">
            <el-input v-model="queryParams.unit" placeholder="请输入单位" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="队伍名称" prop="teamName">
            <el-input v-model="queryParams.teamName" placeholder="请输入队伍名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="负责人" prop="leader">
            <el-input v-model="queryParams.leader" placeholder="请输入负责人" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="负责人电话" prop="leaderPhone">
            <el-input v-model="queryParams.leaderPhone" placeholder="请输入负责人电话" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>应急队伍列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:YJEMERTEAM:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:YJEMERTEAM:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:YJEMERTEAM:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:YJEMERTEAM:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="应急队伍ID" align="center" prop="id" width="160" />
        <el-table-column label="单位" align="left" prop="unit" show-overflow-tooltip min-width="120" />
        <el-table-column label="队伍名称" align="left" prop="teamName" show-overflow-tooltip min-width="120" />
        <el-table-column label="负责人" align="left" prop="leader" show-overflow-tooltip min-width="120" />
        <el-table-column label="负责人电话" align="left" prop="leaderPhone" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人" align="left" prop="createBy" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:YJEMERTEAM:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:YJEMERTEAM:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="yjemerteamFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="单位" prop="unit">
              <el-input v-model="form.unit" placeholder="请输入单位" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="队伍名称" prop="teamName">
              <el-input v-model="form.teamName" placeholder="请输入队伍名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="负责人" prop="leader">
              <el-input v-model="form.leader" placeholder="请输入负责人" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="负责人电话" prop="leaderPhone">
              <el-input v-model="form.leaderPhone" placeholder="请输入负责人电话" />
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

<script setup name="YJEMERTEAM" lang="ts">
import { addYJEMERTEAM, delYJEMERTEAM, getYJEMERTEAM, listYJEMERTEAM, updateYJEMERTEAM } from '@/api/scyh/yjemerteam';
import { YJEMERTEAMForm, YJEMERTEAMQuery, YJEMERTEAMVO } from '@/api/scyh/yjemerteam/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<YJEMERTEAMVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const yjemerteamFormRef = ref<ElFormInstance>();

const initFormData: YJEMERTEAMForm = {
    unit: undefined,
    teamName: undefined,
    leader: undefined,
    leaderPhone: undefined,
}
const data = reactive<PageData<YJEMERTEAMForm, YJEMERTEAMQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    unit: undefined,
    teamName: undefined,
    leader: undefined,
    leaderPhone: undefined,
  },
  rules: {
    unit: [
      { required: true, message: "单位不能为空", trigger: "blur" }
    ],
    teamName: [
      { required: true, message: "队伍名称不能为空", trigger: "blur" }
    ],
    leader: [
      { required: true, message: "负责人不能为空", trigger: "blur" }
    ],
    leaderPhone: [
      { required: true, message: "负责人电话不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listYJEMERTEAM(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; yjemerteamFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: YJEMERTEAMVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增应急队伍'; dialog.visible = true; }
const handleUpdate = async (row?: YJEMERTEAMVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getYJEMERTEAM(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改应急队伍'; dialog.visible = true;
}
const submitForm = () => {
  yjemerteamFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateYJEMERTEAM(form.value); }
        else { await addYJEMERTEAM(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: YJEMERTEAMVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delYJEMERTEAM(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/yjemerteam/export', { ...queryParams.value }, '应急队伍_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>