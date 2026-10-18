<template>
  <div class="p-2 page-shell scyh-hiddentrouble-page">
    <div class="search-wrap">
      <el-card shadow="hover" class="search-panel" :class="{ 'is-collapsed': !showSearch }">
        <template #header>
          <div class="panel-heading search-panel-toggle" @click.stop="showSearch = !showSearch">
            <div><h3>筛选条件</h3></div>
          </div>
        </template>
        <el-form ref="queryFormRef" :model="queryParams" :inline="true" class="query-form">
          <el-form-item label="机构" prop="discoverOrgCodePath">
            <el-input v-model="queryParams.discoverOrgCodePath" placeholder="请输入机构" clearable @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="分类" prop="hiddenTroubleTypeName">
            <el-input v-model="queryParams.hiddenTroubleTypeName" placeholder="请输入隐患分类" clearable @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="隐患等级" prop="hiddenTroubleRankName">
            <el-select v-model="queryParams.hiddenTroubleRankName" placeholder="全部" clearable>
              <el-option label="一般隐患" value="一般隐患" />
              <el-option label="较大隐患" value="较大隐患" />
              <el-option label="重大隐患" value="重大隐患" />
            </el-select>
          </el-form-item>
          <el-form-item label="发现人" prop="discoverUserName">
            <el-input v-model="queryParams.discoverUserName" placeholder="请输入发现人" clearable @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="隐患内容" prop="hiddenTroubleName">
            <el-input v-model="queryParams.hiddenTroubleName" placeholder="请输入隐患内容" clearable @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="queryParams.status" placeholder="全部" clearable>
              <el-option label="暂存" value="0" />
              <el-option label="已提交" value="1" />
              <el-option label="整改中" value="2" />
              <el-option label="待验证" value="3" />
              <el-option label="已关闭" value="4" />
              <el-option label="已撤回" value="5" />
            </el-select>
          </el-form-item>
          <el-form-item label="发现时间">
            <el-date-picker v-model="dateRange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="YYYY-MM-DD" />
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
            <h3>隐患管理列表</h3>
          </div>
          <div class="toolbar-actions">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:hiddentrouble:add']">新增</el-button>
            <el-button type="info" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['scyh:hiddentrouble:edit']">处理</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['scyh:hiddentrouble:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:hiddentrouble:export']">导出</el-button>
            <el-button type="default" plain icon="RefreshLeft" :disabled="single" @click="handleUpdate()" v-hasPermi="['scyh:hiddentrouble:edit']">撤回</el-button>
            <right-toolbar v-model:show-search="showSearch" :search="false" @query-table="getList"></right-toolbar>
          </div>
        </div>
      </template>

      <el-table v-loading="loading" border class="data-table" :data="hiddentroubleList" @selection-change="handleSelectionChange">
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="隐患状态" align="center" prop="status" width="80">
          <template #default="scope">
            <el-tag :type="statusTag(scope.row.status)" size="small">{{ statusText(scope.row.status) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="隐患编号" align="center" prop="id" width="160" />
        <el-table-column label="隐患内容" align="left" prop="hiddenTroubleName" show-overflow-tooltip min-width="180" />
        <el-table-column label="隐患分类" align="center" prop="hiddenTroubleTypeName" width="120" />
        <el-table-column label="隐患等级" align="center" prop="hiddenTroubleRankName" width="100" />
        <el-table-column label="隐患描述" align="left" prop="hiddenTroubleDesc" show-overflow-tooltip min-width="150" />
        <el-table-column label="隐患部门" align="center" prop="discoverOrgCodePath" width="150" show-overflow-tooltip />
        <el-table-column label="隐患位置" align="center" prop="location" width="120" show-overflow-tooltip />
        <el-table-column label="发现人" align="center" prop="discoverUserName" width="90" />
        <el-table-column label="发现日期" align="center" prop="discoverTime" width="120" />
        <el-table-column label="危险源类型" align="center" prop="dangerType" width="110" show-overflow-tooltip />
        <el-table-column label="来源" align="center" prop="listsource" width="80" />
        <el-table-column label="提交时间" align="center" prop="submitTime" width="120" />
        <el-table-column label="关闭时间" align="center" prop="closeTime" width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="100" fixed="right">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:hiddentrouble:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:hiddentrouble:remove']"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <!-- 添加或修改隐患对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="hiddentroubleFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="隐患名称" prop="hiddenTroubleName">
              <el-input v-model="form.hiddenTroubleName" placeholder="请输入隐患名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="隐患分类" prop="hiddenTroubleTypeName">
              <el-input v-model="form.hiddenTroubleTypeName" placeholder="如：人的不安全行为/检修安全" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="隐患等级" prop="hiddenTroubleRankName">
              <el-select v-model="form.hiddenTroubleRankName" placeholder="请选择隐患等级" style="width:100%">
                <el-option label="一般隐患" value="一般隐患" />
                <el-option label="较大隐患" value="较大隐患" />
                <el-option label="重大隐患" value="重大隐患" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="危险源类型" prop="dangerType">
              <el-input v-model="form.dangerType" placeholder="请输入危险源类型" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="隐患描述" prop="hiddenTroubleDesc">
          <el-input v-model="form.hiddenTroubleDesc" type="textarea" :rows="3" placeholder="请输入隐患详细描述" />
        </el-form-item>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="隐患位置" prop="location">
              <el-input v-model="form.location" placeholder="请输入隐患位置/区域" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="隐患部门" prop="discoverOrgCodePath">
              <el-input v-model="form.discoverOrgCodePath" placeholder="如：总部/生产部" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="发现人" prop="discoverUserName">
              <el-input v-model="form.discoverUserName" placeholder="请输入发现人姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="发现时间" prop="discoverTime">
              <el-date-picker v-model="form.discoverTime" type="datetime" placeholder="选择发现时间" value-format="YYYY-MM-DD HH:mm:ss" style="width:100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="来源" prop="listsource">
              <el-input v-model="form.listsource" placeholder="请输入来源" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态" style="width:100%">
                <el-option label="暂存" value="0" />
                <el-option label="已提交" value="1" />
                <el-option label="整改中" value="2" />
                <el-option label="待验证" value="3" />
                <el-option label="已关闭" value="4" />
                <el-option label="已撤回" value="5" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
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

<script setup name="Hiddentrouble" lang="ts">
import {
  addHiddentrouble,
  delHiddentrouble,
  getHiddentrouble,
  listHiddentrouble,
  updateHiddentrouble
} from '@/api/scyh/hiddentrouble';
import { HiddentroubleForm, HiddentroubleQuery, HiddentroubleVO } from '@/api/scyh/hiddentrouble/types';
import { useLoading } from '@/hooks/async/useLoading';
import { useFormDialog } from '@/hooks/dialog/useFormDialog';
import { useSearchReset } from '@/hooks/form/useSearchReset';
import { useSearchToggle } from '@/hooks/form/useSearchToggle';
import { useTableSelection } from '@/hooks/table/useTableSelection';
import modal from '@/plugins/modal';
import { download as requestDownload } from '@/utils/request';

const hiddentroubleList = ref<HiddentroubleVO[]>([]);
const buttonLoading = ref(false);
const { loading, withLoading } = useLoading(true);
const { showSearch } = useSearchToggle();
const total = ref(0);
const dateRange = ref<[string, string] | []>([]);

const queryFormRef = ref<ElFormInstance>();
const hiddentroubleFormRef = ref<ElFormInstance>();

// 状态映射
const statusMap: Record<string, string> = {
  '0': '暂存', '1': '已提交', '2': '整改中', '3': '待验证', '4': '已关闭', '5': '已撤回'
};
const statusText = (val: string) => statusMap[val] || val || '';
const statusTag = (val: string) => {
  const m: Record<string, string> = { '0': 'info', '1': 'warning', '2': 'warning', '3': 'warning', '4': 'success', '5': 'danger' };
  return m[val] || 'info';
};

const initFormData: HiddentroubleForm = {
  id: undefined,
  status: '0',
  operate: undefined,
  hiddenTroubleTypeName: undefined,
  hiddenTroubleRankName: undefined,
  discoverOrgCode: undefined,
  discoverOrgCodePath: undefined,
  discoverUserCode: undefined,
  discoverUserName: undefined,
  discoverTime: undefined,
  hiddenTroubleName: undefined,
  hiddenTroubleDesc: undefined,
  dangerType: undefined,
  location: undefined,
  listsource: undefined,
  unitFindFlag: undefined,
  rewardXl: undefined,
  displayOrder: undefined,
  submitTime: undefined,
  closeTime: undefined,
  bizSource: undefined,
  bizSourceId: undefined,
  ieqmsStatus: undefined,
  userJobId: undefined,
  deleteFlag: undefined,
}
const data = reactive<PageData<HiddentroubleForm, HiddentroubleQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    status: undefined,
    hiddenTroubleTypeName: undefined,
    hiddenTroubleRankName: undefined,
    discoverOrgCode: undefined,
    discoverOrgCodePath: undefined,
    discoverUserCode: undefined,
    discoverUserName: undefined,
    hiddenTroubleName: undefined,
    params: {
    }
  },
  rules: {
    hiddenTroubleName: [
      { required: true, message: "隐患名称不能为空", trigger: "blur" }
    ],
    hiddenTroubleTypeName: [
      { required: true, message: "隐患分类不能为空", trigger: "blur" }
    ],
    hiddenTroubleRankName: [
      { required: true, message: "隐患等级不能为空", trigger: "change" }
    ],
    discoverUserName: [
      { required: true, message: "发现人不能为空", trigger: "blur" }
    ],
    discoverTime: [
      { required: true, message: "发现时间不能为空", trigger: "change" }
    ],
    hiddenTroubleDesc: [
      { required: true, message: "隐患描述不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);
const { ids, single, multiple, handleSelectionChange } = useTableSelection<HiddentroubleVO>(item => item.id);
const { dialog, resetForm: reset, openDialog, showDialog, closeDialog } = useFormDialog({
  form,
  formRef: hiddentroubleFormRef,
  initialFormData: initFormData
});

/** 查询隐患列表 */
const getList = async () => {
  await withLoading(async () => {
    // 日期范围参数
    if (dateRange.value && dateRange.value.length === 2) {
      queryParams.value.params = { beginDiscoverTime: dateRange.value[0], endDiscoverTime: dateRange.value[1] };
    } else {
      queryParams.value.params = {};
    }
    const res = await listHiddentrouble(queryParams.value);
    hiddentroubleList.value = res.data?.rows;
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
    dateRange.value = [];
  },
  afterReset: () => {
    handleQuery();
  }
});

/** 新增按钮操作 */
const handleAdd = () => {
  openDialog('添加隐患');
};

/** 修改按钮操作 */
const handleUpdate = async (row?: Partial<HiddentroubleVO>) => {
  reset();
  const _id = row?.id || ids.value[0];
  const res = await getHiddentrouble(_id);
  Object.assign(form.value, res.data);
  showDialog('修改隐患');
};

/** 提交按钮 */
const submitForm = () => {
  hiddentroubleFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updateHiddentrouble(form.value).finally(() => (buttonLoading.value = false));
      } else {
        await addHiddentrouble(form.value).finally(() => (buttonLoading.value = false));
      }
      modal.msgSuccess('操作成功');
      closeDialog();
      await getList();
    }
  });
};

/** 删除按钮操作 */
const handleDelete = async (row?: Partial<HiddentroubleVO>) => {
  const _ids = row?.id || ids.value;
  await modal.confirm('是否确认删除隐患编号为"' + _ids + '"的数据项？');
  await delHiddentrouble(_ids);
  modal.msgSuccess('删除成功');
  await getList();
};

/** 导出按钮操作 */
const handleExport = () => {
  requestDownload(
    'scyh/hiddentrouble/export',
    {
      ...queryParams.value
    },
    `隐患管理_${new Date().getTime()}.xlsx`
  );
};

onMounted(() => {
  getList();
});
</script>
