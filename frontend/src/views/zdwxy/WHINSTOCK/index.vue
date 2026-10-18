<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="单据编号" prop="documentCode">
            <el-input v-model="queryParams.documentCode" placeholder="请输入单据编号" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="入库日期" prop="inTime">
            <el-input v-model="queryParams.inTime" placeholder="请输入入库日期" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="部门" prop="dept">
            <el-input v-model="queryParams.dept" placeholder="请输入部门" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="存储库" prop="storageName">
            <el-input v-model="queryParams.storageName" placeholder="请输入存储库" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="使用区域" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入使用区域" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="目录序号" prop="articleCode">
            <el-input v-model="queryParams.articleCode" placeholder="请输入目录序号" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>入库管理列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:WHINSTOCK:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:WHINSTOCK:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:WHINSTOCK:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:WHINSTOCK:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="入库ID" align="center" prop="id" width="160" />
        <el-table-column label="单据编号" align="left" prop="documentCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="入库日期" align="center" prop="inTime" width="120" show-overflow-tooltip />
        <el-table-column label="部门" align="left" prop="dept" show-overflow-tooltip min-width="120" />
        <el-table-column label="存储库" align="left" prop="storageName" show-overflow-tooltip min-width="120" />
        <el-table-column label="使用区域" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="目录序号" align="left" prop="articleCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="危化品名称" align="left" prop="hazardousName" show-overflow-tooltip min-width="120" />
        <el-table-column label="物料名称" align="left" prop="materialName" show-overflow-tooltip min-width="120" />
        <el-table-column label="剧毒品" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="易制爆品" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="易制毒品" align="left" prop="field4" show-overflow-tooltip min-width="120" />
        <el-table-column label="重点监管品" align="left" prop="field5" show-overflow-tooltip min-width="120" />
        <el-table-column label="其他" align="left" prop="field6" show-overflow-tooltip min-width="120" />
        <el-table-column label="规格（数量/单位）" align="left" prop="spec" show-overflow-tooltip min-width="120" />
        <el-table-column label="入库量" align="left" prop="instock" show-overflow-tooltip min-width="120" />
        <el-table-column label="上次库存" align="left" prop="field7" show-overflow-tooltip min-width="120" />
        <el-table-column label="当前库存" align="left" prop="field8" show-overflow-tooltip min-width="120" />
        <el-table-column label="最大库存" align="left" prop="field9" show-overflow-tooltip min-width="120" />
        <el-table-column label="入库人" align="left" prop="instock2" show-overflow-tooltip min-width="120" />
        <el-table-column label="确认时间" align="left" prop="field10" show-overflow-tooltip min-width="120" />
        <el-table-column label="用途" align="left" prop="field11" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:WHINSTOCK:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:WHINSTOCK:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="whinstockFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="单据编号" prop="documentCode">
              <el-input v-model="form.documentCode" placeholder="请输入单据编号" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="入库日期" prop="inTime">
              <el-date-picker v-model="form.inTime" type="datetime" placeholder="选择入库日期" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="部门" prop="dept">
              <el-input v-model="form.dept" placeholder="请输入部门" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="存储库" prop="storageName">
              <el-input v-model="form.storageName" placeholder="请输入存储库" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="使用区域" prop="field">
              <el-input v-model="form.field" placeholder="请输入使用区域" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="目录序号" prop="articleCode">
              <el-input v-model="form.articleCode" placeholder="请输入目录序号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="危化品名称" prop="hazardousName">
              <el-input v-model="form.hazardousName" placeholder="请输入危化品名称" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="物料名称" prop="materialName">
              <el-input v-model="form.materialName" placeholder="请输入物料名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="剧毒品" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入剧毒品" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="易制爆品" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入易制爆品" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="易制毒品" prop="field4">
              <el-input v-model="form.field4" placeholder="请输入易制毒品" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="重点监管品" prop="field5">
              <el-input v-model="form.field5" placeholder="请输入重点监管品" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="其他" prop="field6">
              <el-input v-model="form.field6" placeholder="请输入其他" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="规格（数量/单位）" prop="spec">
              <el-input v-model="form.spec" placeholder="请输入规格（数量/单位）" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="入库量" prop="instock">
              <el-input v-model="form.instock" placeholder="请输入入库量" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="上次库存" prop="field7">
              <el-input v-model="form.field7" placeholder="请输入上次库存" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="当前库存" prop="field8">
              <el-input v-model="form.field8" placeholder="请输入当前库存" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="最大库存" prop="field9">
              <el-input v-model="form.field9" placeholder="请输入最大库存" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="入库人" prop="instock2">
              <el-input v-model="form.instock2" placeholder="请输入入库人" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="确认时间" prop="field10">
              <el-input v-model="form.field10" placeholder="请输入确认时间" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="用途" prop="field11">
              <el-input v-model="form.field11" placeholder="请输入用途" />
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

<script setup name="WHINSTOCK" lang="ts">
import { addWHINSTOCK, delWHINSTOCK, getWHINSTOCK, listWHINSTOCK, updateWHINSTOCK } from '@/api/scyh/whinstock';
import { WHINSTOCKForm, WHINSTOCKQuery, WHINSTOCKVO } from '@/api/scyh/whinstock/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<WHINSTOCKVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const whinstockFormRef = ref<ElFormInstance>();

const initFormData: WHINSTOCKForm = {
    documentCode: undefined,
    inTime: undefined,
    dept: undefined,
    storageName: undefined,
    field: undefined,
    articleCode: undefined,
    hazardousName: undefined,
    materialName: undefined,
    field2: undefined,
    field3: undefined,
    field4: undefined,
    field5: undefined,
    field6: undefined,
    spec: undefined,
    instock: undefined,
    field7: undefined,
    field8: undefined,
    field9: undefined,
    instock2: undefined,
    field10: undefined,
    field11: undefined,
}
const data = reactive<PageData<WHINSTOCKForm, WHINSTOCKQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    documentCode: undefined,
    inTime: undefined,
    dept: undefined,
    storageName: undefined,
    field: undefined,
    articleCode: undefined,
  },
  rules: {
    documentCode: [
      { required: true, message: "单据编号不能为空", trigger: "blur" }
    ],
    inTime: [
      { required: true, message: "入库日期不能为空", trigger: "blur" }
    ],
    dept: [
      { required: true, message: "部门不能为空", trigger: "blur" }
    ],
    storageName: [
      { required: true, message: "存储库不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listWHINSTOCK(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; whinstockFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: WHINSTOCKVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增入库管理'; dialog.visible = true; }
const handleUpdate = async (row?: WHINSTOCKVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getWHINSTOCK(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改入库管理'; dialog.visible = true;
}
const submitForm = () => {
  whinstockFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateWHINSTOCK(form.value); }
        else { await addWHINSTOCK(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: WHINSTOCKVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delWHINSTOCK(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/whinstock/export', { ...queryParams.value }, '入库管理_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>