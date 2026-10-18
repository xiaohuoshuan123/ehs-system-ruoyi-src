<template>
  <div class="p-2 page-shell mbgh-plan-page">
    <div class="search-wrap">
      <el-card shadow="hover" class="search-panel" :class="{ 'is-collapsed': !showSearch }">
        <template #header>
          <div class="panel-heading search-panel-toggle" @click.stop="showSearch = !showSearch">
            <div><h3>筛选条件</h3></div>
          </div>
        </template>
        <el-form ref="queryFormRef" :model="queryParams" :inline="true" class="query-form">
            <el-form-item label="计划" prop="status">
              <el-select v-model="queryParams.status" placeholder="请选择plan" clearable >
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
            <h3>Plan列表</h3>
          </div>
          <div class="toolbar-actions">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['mbgh:plan:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['mbgh:plan:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['mbgh:plan:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['mbgh:plan:export']">导出</el-button>
            <right-toolbar v-model:show-search="showSearch" :search="false" @query-table="getList"></right-toolbar>
          </div>
        </div>
      </template>

      <el-table v-loading="loading" border class="data-table" :data="planList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="计划" align="center" prop="id" />
        <el-table-column label="计划" align="center" prop="orgCode" />
        <el-table-column label="计划" align="center" prop="orgCodePath" />
        <el-table-column label="计划" align="center" prop="status">
          <template #default="scope">
            <dict-tag :options="sys_normal_disable" :value="scope.row.status"/>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['mbgh:plan:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['mbgh:plan:remove']"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>
    <!-- 添加或修改Plan对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="planFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="计划" prop="status">
          <el-select v-model="form.status" placeholder="请选择plan">
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

<script setup name="Plan" lang="ts">
import {
  addPlan,
  delPlan,
  getPlan,
  listPlan,
  updatePlan
} from '@/api/mbgh/plan';
import { PlanForm, PlanQuery, PlanVO } from '@/api/mbgh/plan/types';
import { useLoading } from '@/hooks/async/useLoading';
import { useFormDialog } from '@/hooks/dialog/useFormDialog';
import { useSearchReset } from '@/hooks/form/useSearchReset';
import { useSearchToggle } from '@/hooks/form/useSearchToggle';
import { useTableSelection } from '@/hooks/table/useTableSelection';
import { useDict } from '@/utils/dict';
import modal from '@/plugins/modal';
import { download as requestDownload } from '@/utils/request';

const { sys_normal_disable } = toRefs<any>(useDict('sys_normal_disable'));


const planList = ref<PlanVO[]>([]);
const buttonLoading = ref(false);
const { loading, withLoading } = useLoading(true);
const { showSearch } = useSearchToggle();
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const planFormRef = ref<ElFormInstance>();

const initFormData: PlanForm = {
  id: undefined,
  orgCode: undefined,
  orgCodePath: undefined,
  planYear: undefined,
  targetType: undefined,
  treeData: undefined,
  planContent: undefined,
  status: undefined,
}
const data = reactive<PageData<PlanForm, PlanQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    orgCode: undefined,
    orgCodePath: undefined,
    status: undefined,
    params: {
    }
  },
  rules: {
id: [
      { required: true, message: "plan不能为空", trigger: "blur" }
    ],
orgCode: [
      { required: true, message: "plan不能为空", trigger: "blur" }
    ],
orgCodePath: [
      { required: true, message: "plan不能为空", trigger: "blur" }
    ],
planYear: [
      { required: true, message: "plan不能为空", trigger: "blur" }
    ],
targetType: [
      { required: true, message: "plan不能为空", trigger: "blur" }
    ],
treeData: [
      { required: true, message: "plan不能为空", trigger: "blur" }
    ],
planContent: [
      { required: true, message: "plan不能为空", trigger: "blur" }
    ],
status: [
      { required: true, message: "plan不能为空", trigger: "change" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);
const { ids, single, multiple, handleSelectionChange } = useTableSelection<PlanVO>(item => item.id);
const { dialog, resetForm: reset, openDialog, showDialog, closeDialog } = useFormDialog({
  form,
  formRef: planFormRef,
  initialFormData: initFormData
});

/** 查询Plan列表 */
const getList = async () => {
  await withLoading(async () => {
    const res = await listPlan(queryParams.value);
    planList.value = res.data?.rows;
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
  openDialog('添加Plan');
};

/** 修改按钮操作 */
const handleUpdate = async (row?: Partial<PlanVO>) => {
  reset();
  const _id = row?.id || ids.value[0];
  const res = await getPlan(_id);
  Object.assign(form.value, res.data);
  showDialog('修改Plan');
};

/** 提交按钮 */
const submitForm = () => {
  planFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updatePlan(form.value).finally(() => (buttonLoading.value = false));
      } else {
        await addPlan(form.value).finally(() => (buttonLoading.value = false));
      }
      modal.msgSuccess('操作成功');
      closeDialog();
      await getList();
    }
  });
};

/** 删除按钮操作 */
const handleDelete = async (row?: Partial<PlanVO>) => {
  const _ids = row?.id || ids.value;
  await modal.confirm('是否确认删除Plan编号为"' + _ids + '"的数据项？');
  await delPlan(_ids);
  modal.msgSuccess('删除成功');
  await getList();
};

/** 导出按钮操作 */
const handleExport = () => {
  requestDownload(
    'mbgh/plan/export',
    {
      ...queryParams.value
    },
    `plan_${new Date().getTime()}.xlsx`
  );
};



onMounted(() => {
  getList();
});
</script>

