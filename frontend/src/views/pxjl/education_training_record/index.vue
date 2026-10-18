<template>
  <div class="p-2 page-shell pxjl-education_training_record-page">
    <div class="search-wrap">
      <el-card shadow="hover" class="search-panel" :class="{ 'is-collapsed': !showSearch }">
        <template #header>
          <div class="panel-heading search-panel-toggle" @click.stop="showSearch = !showSearch">
            <div><h3>筛选条件</h3></div>
          </div>
        </template>
        <el-form ref="queryFormRef" :model="queryParams" :inline="true" class="query-form">
            <el-form-item label="培训记录" prop="status">
              <el-select v-model="queryParams.status" placeholder="请选择education_training_record" clearable >
                <el-option v-for="dict in sys_normal_disable" :key="dict.value" :label="dict.label" :value="dict.value"/>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
              <el-button icon="Refresh" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>
      </el-card>
    </div>

    <el-card shadow="hover" class="table-panel">
      <template #header>
        <div class="toolbar-shell">
          <div class="table-heading">
            <h3>EducationTrainingRecord列表</h3>
          </div>
          <div class="toolbar-actions">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['pxjl:education_training_record:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['pxjl:education_training_record:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['pxjl:education_training_record:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['pxjl:education_training_record:export']">导出</el-button>
            <right-toolbar v-model:show-search="showSearch" :search="false" @query-table="getList"></right-toolbar>
          </div>
        </div>
      </template>

      <el-table v-loading="loading" border class="data-table" :data="education_training_recordList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="培训记录" align="center" prop="id" />
        <el-table-column label="培训记录" align="center" prop="orgCode1" />
        <el-table-column label="培训记录" align="center" prop="empCode" />
        <el-table-column label="培训记录" align="center" prop="empName" />
        <el-table-column label="培训记录" align="center" prop="status">
          <template #default="scope">
            <dict-tag :options="sys_normal_disable" :value="scope.row.status"/>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['pxjl:education_training_record:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['pxjl:education_training_record:remove']"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>
    <!-- 添加或修改EducationTrainingRecord对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="education_training_recordFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="培训记录" prop="status">
          <el-select v-model="form.status" placeholder="请选择education_training_record">
            <el-option
                v-for="dict in sys_normal_disable"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Education_training_record" lang="ts">
import {
  addEducation_training_record,
  delEducation_training_record,
  getEducation_training_record,
  listEducation_training_record,
  updateEducation_training_record
} from '@/api/pxjl/education_training_record';
import { Education_training_recordForm, Education_training_recordQuery, Education_training_recordVO } from '@/api/pxjl/education_training_record/types';
import { useLoading } from '@/hooks/async/useLoading';
import { useFormDialog } from '@/hooks/dialog/useFormDialog';
import { useSearchReset } from '@/hooks/form/useSearchReset';
import { useSearchToggle } from '@/hooks/form/useSearchToggle';
import { useTableSelection } from '@/hooks/table/useTableSelection';
import { useDict } from '@/utils/dict';
import modal from '@/plugins/modal';
import { download as requestDownload } from '@/utils/request';

const { sys_normal_disable } = toRefs<any>(useDict('sys_normal_disable'));


const education_training_recordList = ref<Education_training_recordVO[]>([]);
const buttonLoading = ref(false);
const { loading, withLoading } = useLoading(true);
const { showSearch } = useSearchToggle();
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const education_training_recordFormRef = ref<ElFormInstance>();

const initFormData: Education_training_recordForm = {
  id: undefined,
  mstId: undefined,
  orgCode1: undefined,
  trainYear: undefined,
  trainType: undefined,
  empCode: undefined,
  empName: undefined,
  trainResult: undefined,
  score: undefined,
  status: undefined,
}
const data = reactive<PageData<Education_training_recordForm, Education_training_recordQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    orgCode1: undefined,
    empCode: undefined,
    empName: undefined,
    status: undefined,
    params: {
    }
  },
  rules: {
id: [
      { required: true, message: "education_training_record不能为空", trigger: "blur" }
    ],
mstId: [
      { required: true, message: "education_training_record不能为空", trigger: "blur" }
    ],
orgCode1: [
      { required: true, message: "education_training_record不能为空", trigger: "blur" }
    ],
trainYear: [
      { required: true, message: "education_training_record不能为空", trigger: "blur" }
    ],
trainType: [
      { required: true, message: "education_training_record不能为空", trigger: "blur" }
    ],
empCode: [
      { required: true, message: "education_training_record不能为空", trigger: "blur" }
    ],
empName: [
      { required: true, message: "education_training_record不能为空", trigger: "blur" }
    ],
trainResult: [
      { required: true, message: "education_training_record不能为空", trigger: "blur" }
    ],
score: [
      { required: true, message: "education_training_record不能为空", trigger: "blur" }
    ],
status: [
      { required: true, message: "education_training_record不能为空", trigger: "change" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);
const { ids, single, multiple, handleSelectionChange } = useTableSelection<Education_training_recordVO>(item => item.id);
const { dialog, resetForm: reset, openDialog, showDialog, closeDialog } = useFormDialog({
  form,
  formRef: education_training_recordFormRef,
  initialFormData: initFormData
});

/** 查询EducationTrainingRecord列表 */
const getList = async () => {
  await withLoading(async () => {
    const res = await listEducation_training_record(queryParams.value);
    education_training_recordList.value = res.data?.rows;
    total.value = res.data?.total;
  });
};

/** 取消按钮 */
const cancel = () => {
  reset();
  closeDialog();
};

/** 搜索按钮操作 */
const handleQuery = () => {
  queryParams.value.pageNum = 1;
  getList();
};

const { resetQuery } = useSearchReset({
  queryFormRef,
  queryParams,
  pageNumKey: 'pageNum',
  pageSizeKey: 'pageSize',
  initialPageSize: 10,
  resetExtras: () => {
  },
  afterReset: () => {
    handleQuery();
  }
});

/** 新增按钮操作 */
const handleAdd = () => {
  openDialog('添加EducationTrainingRecord');
};

/** 修改按钮操作 */
const handleUpdate = async (row?: Partial<Education_training_recordVO>) => {
  reset();
  const _id = row?.id || ids.value[0];
  const res = await getEducation_training_record(_id);
  Object.assign(form.value, res.data);
  showDialog('修改EducationTrainingRecord');
};

/** 提交按钮 */
const submitForm = () => {
  education_training_recordFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updateEducation_training_record(form.value).finally(() => (buttonLoading.value = false));
      } else {
        await addEducation_training_record(form.value).finally(() => (buttonLoading.value = false));
      }
      modal.msgSuccess('操作成功');
      closeDialog();
      await getList();
    }
  });
};

/** 删除按钮操作 */
const handleDelete = async (row?: Partial<Education_training_recordVO>) => {
  const _ids = row?.id || ids.value;
  await modal.confirm('是否确认删除EducationTrainingRecord编号为"' + _ids + '"的数据项？');
  await delEducation_training_record(_ids);
  modal.msgSuccess('删除成功');
  await getList();
};

/** 导出按钮操作 */
const handleExport = () => {
  requestDownload(
    'pxjl/education_training_record/export',
    {
      ...queryParams.value
    },
    `education_training_record_${new Date().getTime()}.xlsx`
  );
};



onMounted(() => {
  getList();
});
</script>

