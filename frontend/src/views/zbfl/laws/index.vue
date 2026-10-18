<template>
  <div class="p-2 page-shell zbfl-laws-page">
    <div class="search-wrap">
      <el-card shadow="hover" class="search-panel" :class="{ 'is-collapsed': !showSearch }">
        <template #header>
          <div class="panel-heading search-panel-toggle" @click.stop="showSearch = !showSearch">
            <div><h3>筛选条件</h3></div>
          </div>
        </template>
        <el-form ref="queryFormRef" :model="queryParams" :inline="true" class="query-form">
            <el-form-item label="法律法规" prop="status">
              <el-select v-model="queryParams.status" placeholder="请选择laws" clearable >
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
            <h3>Laws列表</h3>
          </div>
          <div class="toolbar-actions">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['zbfl:laws:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['zbfl:laws:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['zbfl:laws:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['zbfl:laws:export']">导出</el-button>
            <right-toolbar v-model:show-search="showSearch" :search="false" @query-table="getList"></right-toolbar>
          </div>
        </div>
      </template>

      <el-table v-loading="loading" border class="data-table" :data="lawsList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="法律法规" align="center" prop="id" />
        <el-table-column label="法律法规" align="center" prop="publishDate" />
        <el-table-column label="法律法规" align="center" prop="fileName" />
        <el-table-column label="法律法规" align="center" prop="orgCode" />
        <el-table-column label="法律法规" align="center" prop="orgName" />
        <el-table-column label="法律法规" align="center" prop="status">
          <template #default="scope">
            <dict-tag :options="sys_normal_disable" :value="scope.row.status"/>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['zbfl:laws:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['zbfl:laws:remove']"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>
    <!-- 添加或修改Laws对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="lawsFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="法律法规" prop="status">
          <el-select v-model="form.status" placeholder="请选择laws">
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

<script setup name="Laws" lang="ts">
import {
  addLaws,
  delLaws,
  getLaws,
  listLaws,
  updateLaws
} from '@/api/zbfl/laws';
import { LawsForm, LawsQuery, LawsVO } from '@/api/zbfl/laws/types';
import { useLoading } from '@/hooks/async/useLoading';
import { useFormDialog } from '@/hooks/dialog/useFormDialog';
import { useSearchReset } from '@/hooks/form/useSearchReset';
import { useSearchToggle } from '@/hooks/form/useSearchToggle';
import { useTableSelection } from '@/hooks/table/useTableSelection';
import { useDict } from '@/utils/dict';
import modal from '@/plugins/modal';
import { download as requestDownload } from '@/utils/request';

const { sys_normal_disable } = toRefs<any>(useDict('sys_normal_disable'));


const lawsList = ref<LawsVO[]>([]);
const buttonLoading = ref(false);
const { loading, withLoading } = useLoading(true);
const { showSearch } = useSearchToggle();
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const lawsFormRef = ref<ElFormInstance>();

const initFormData: LawsForm = {
  id: undefined,
  publishDate: undefined,
  fileNo: undefined,
  fileName: undefined,
  title: undefined,
  lawType: undefined,
  lawsDataStatus: undefined,
  effectStatus: undefined,
  orgCode: undefined,
  orgName: undefined,
  content: undefined,
  attachmentIds: undefined,
  status: undefined,
}
const data = reactive<PageData<LawsForm, LawsQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    fileName: undefined,
    orgCode: undefined,
    orgName: undefined,
    status: undefined,
    params: {
    }
  },
  rules: {
id: [
      { required: true, message: "laws不能为空", trigger: "blur" }
    ],
publishDate: [
      { required: true, message: "laws不能为空", trigger: "blur" }
    ],
fileNo: [
      { required: true, message: "laws不能为空", trigger: "blur" }
    ],
fileName: [
      { required: true, message: "laws不能为空", trigger: "blur" }
    ],
title: [
      { required: true, message: "laws不能为空", trigger: "blur" }
    ],
lawType: [
      { required: true, message: "laws不能为空", trigger: "blur" }
    ],
lawsDataStatus: [
      { required: true, message: "laws不能为空", trigger: "blur" }
    ],
effectStatus: [
      { required: true, message: "laws不能为空", trigger: "blur" }
    ],
orgCode: [
      { required: true, message: "laws不能为空", trigger: "blur" }
    ],
orgName: [
      { required: true, message: "laws不能为空", trigger: "blur" }
    ],
content: [
      { required: true, message: "laws不能为空", trigger: "blur" }
    ],
attachmentIds: [
      { required: true, message: "laws不能为空", trigger: "blur" }
    ],
status: [
      { required: true, message: "laws不能为空", trigger: "change" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);
const { ids, single, multiple, handleSelectionChange } = useTableSelection<LawsVO>(item => item.id);
const { dialog, resetForm: reset, openDialog, showDialog, closeDialog } = useFormDialog({
  form,
  formRef: lawsFormRef,
  initialFormData: initFormData
});

/** 查询Laws列表 */
const getList = async () => {
  await withLoading(async () => {
    const res = await listLaws(queryParams.value);
    lawsList.value = res.data?.rows;
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
  openDialog('添加Laws');
};

/** 修改按钮操作 */
const handleUpdate = async (row?: Partial<LawsVO>) => {
  reset();
  const _id = row?.id || ids.value[0];
  const res = await getLaws(_id);
  Object.assign(form.value, res.data);
  showDialog('修改Laws');
};

/** 提交按钮 */
const submitForm = () => {
  lawsFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updateLaws(form.value).finally(() => (buttonLoading.value = false));
      } else {
        await addLaws(form.value).finally(() => (buttonLoading.value = false));
      }
      modal.msgSuccess('操作成功');
      closeDialog();
      await getList();
    }
  });
};

/** 删除按钮操作 */
const handleDelete = async (row?: Partial<LawsVO>) => {
  const _ids = row?.id || ids.value;
  await modal.confirm('是否确认删除Laws编号为"' + _ids + '"的数据项？');
  await delLaws(_ids);
  modal.msgSuccess('删除成功');
  await getList();
};

/** 导出按钮操作 */
const handleExport = () => {
  requestDownload(
    'zbfl/laws/export',
    {
      ...queryParams.value
    },
    `laws_${new Date().getTime()}.xlsx`
  );
};



onMounted(() => {
  getList();
});
</script>

