<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="状态" prop="status">
            <el-select v-model="queryParams.status" placeholder="全部" clearable style="width: 180px">
              <el-option label="正常" value="0" />
              <el-option label="停用" value="1" />
            </el-select>
          </el-form-item>
          <el-form-item label="发布机构" prop="publishOrg">
            <el-input v-model="queryParams.publishOrg" placeholder="请输入发布机构" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="学习范围编码" prop="studyRangeCode">
            <el-input v-model="queryParams.studyRangeCode" placeholder="请输入学习范围编码" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="学习范围编码全编码" prop="studyRangeCode2">
            <el-input v-model="queryParams.studyRangeCode2" placeholder="请输入学习范围编码全编码" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="学习范围" prop="studyRange">
            <el-input v-model="queryParams.studyRange" placeholder="请输入学习范围" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="学习期限" prop="study">
            <el-input v-model="queryParams.study" placeholder="请输入学习期限" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>安全学习列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:safetyStudyMst:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:safetyStudyMst:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:safetyStudyMst:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:safetyStudyMst:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="安全学习主表ID" align="center" prop="id" width="160" />
        <el-table-column label="状态" align="center" prop="status" width="80">
          <template #default="scope">
            <el-tag :type="scope.row.status === '0' ? 'danger' : 'success'" size="small">
              {{ scope.row.status === '0' ? '停用' : '正常' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="发布机构" align="left" prop="publishOrg" show-overflow-tooltip min-width="120" />
        <el-table-column label="学习范围编码" align="left" prop="studyRangeCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="学习范围编码全编码" align="left" prop="studyRangeCode2" show-overflow-tooltip min-width="120" />
        <el-table-column label="学习范围" align="left" prop="studyRange" show-overflow-tooltip min-width="120" />
        <el-table-column label="学习期限" align="left" prop="study" show-overflow-tooltip min-width="120" />
        <el-table-column label="材料类别" align="left" prop="material" show-overflow-tooltip min-width="120" />
        <el-table-column label="是否必学" align="left" prop="is" show-overflow-tooltip min-width="120" />
        <el-table-column label="材料名称" align="left" prop="materialName" show-overflow-tooltip min-width="120" />
        <el-table-column label="关联考试" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="公共材料" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="已学人数" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="未学人数" align="left" prop="field4" show-overflow-tooltip min-width="120" />
        <el-table-column label="附件数量" align="left" prop="attachmentCount" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人姓名" align="left" prop="createByName" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="更新人姓名" align="center" prop="updateByName" width="120" show-overflow-tooltip />
        <el-table-column label="更新时间" align="center" prop="updateTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:safetyStudyMst:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:safetyStudyMst:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="safetystudymstFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态" style="width: 100%">
                <el-option label="正常" value="0" />
                <el-option label="停用" value="1" />
              </el-select>
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="发布机构" prop="publishOrg">
              <el-input v-model="form.publishOrg" placeholder="请输入发布机构" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="学习范围编码" prop="studyRangeCode">
              <el-input v-model="form.studyRangeCode" placeholder="请输入学习范围编码" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="学习范围编码全编码" prop="studyRangeCode2">
              <el-input v-model="form.studyRangeCode2" placeholder="请输入学习范围编码全编码" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="学习范围" prop="studyRange">
              <el-input v-model="form.studyRange" placeholder="请输入学习范围" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="学习期限" prop="study">
              <el-input v-model="form.study" placeholder="请输入学习期限" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="材料类别" prop="material">
              <el-input v-model="form.material" placeholder="请输入材料类别" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="是否必学" prop="is">
              <el-input v-model="form.is" placeholder="请输入是否必学" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="材料名称" prop="materialName">
              <el-input v-model="form.materialName" placeholder="请输入材料名称" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="关联考试" prop="field">
              <el-input v-model="form.field" placeholder="请输入关联考试" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="公共材料" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入公共材料" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="已学人数" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入已学人数" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="未学人数" prop="field4">
              <el-input v-model="form.field4" placeholder="请输入未学人数" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="附件数量" prop="attachmentCount">
              <el-input v-model="form.attachmentCount" placeholder="请输入附件数量" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="创建人姓名" prop="createByName">
              <el-input v-model="form.createByName" placeholder="请输入创建人姓名" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="更新人姓名" prop="updateByName">
              <el-date-picker v-model="form.updateByName" type="datetime" placeholder="选择更新人姓名" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
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

<script setup name="SafetyStudyMst" lang="ts">
import { addSafetyStudyMst, delSafetyStudyMst, getSafetyStudyMst, listSafetyStudyMst, updateSafetyStudyMst } from '@/api/scyh/safetystudymst';
import { SafetyStudyMstForm, SafetyStudyMstQuery, SafetyStudyMstVO } from '@/api/scyh/safetystudymst/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<SafetyStudyMstVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const safetystudymstFormRef = ref<ElFormInstance>();

const initFormData: SafetyStudyMstForm = {
    status: undefined,
    publishOrg: undefined,
    studyRangeCode: undefined,
    studyRangeCode2: undefined,
    studyRange: undefined,
    study: undefined,
    material: undefined,
    is: undefined,
    materialName: undefined,
    field: undefined,
    field2: undefined,
    field3: undefined,
    field4: undefined,
    attachmentCount: undefined,
    createByName: undefined,
    updateByName: undefined,
}
const data = reactive<PageData<SafetyStudyMstForm, SafetyStudyMstQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    status: undefined,
    publishOrg: undefined,
    studyRangeCode: undefined,
    studyRangeCode2: undefined,
    studyRange: undefined,
    study: undefined,
  },
  rules: {
    status: [
      { required: true, message: "状态不能为空", trigger: "blur" }
    ],
    publishOrg: [
      { required: true, message: "发布机构不能为空", trigger: "blur" }
    ],
    studyRangeCode: [
      { required: true, message: "学习范围编码不能为空", trigger: "blur" }
    ],
    studyRangeCode2: [
      { required: true, message: "学习范围编码全编码不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listSafetyStudyMst(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; safetystudymstFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: SafetyStudyMstVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增安全学习'; dialog.visible = true; }
const handleUpdate = async (row?: SafetyStudyMstVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getSafetyStudyMst(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改安全学习'; dialog.visible = true;
}
const submitForm = () => {
  safetystudymstFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateSafetyStudyMst(form.value); }
        else { await addSafetyStudyMst(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: SafetyStudyMstVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delSafetyStudyMst(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/safetystudymst/export', { ...queryParams.value }, '安全学习_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>