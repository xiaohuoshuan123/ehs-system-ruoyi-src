<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="存储库ID" prop="id2">
            <el-input v-model="queryParams.id2" placeholder="请输入存储库ID" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="所属机构" prop="orgName">
            <el-input v-model="queryParams.orgName" placeholder="请输入所属机构" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="场所编码" prop="siteCode">
            <el-input v-model="queryParams.siteCode" placeholder="请输入场所编码" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="存储库" prop="storageName">
            <el-input v-model="queryParams.storageName" placeholder="请输入存储库" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="库位" prop="location">
            <el-input v-model="queryParams.location" placeholder="请输入库位" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>库存管理列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:WHSTOCK:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:WHSTOCK:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:WHSTOCK:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:WHSTOCK:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="库存ID" align="center" prop="id" width="160" />
        <el-table-column label="存储库ID" align="left" prop="id2" show-overflow-tooltip min-width="120" />
        <el-table-column label="所属机构" align="left" prop="orgName" show-overflow-tooltip min-width="120" />
        <el-table-column label="场所编码" align="left" prop="siteCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="存储库" align="left" prop="storageName" show-overflow-tooltip min-width="120" />
        <el-table-column label="库位" align="left" prop="location" show-overflow-tooltip min-width="120" />
        <el-table-column label="目录序号" align="left" prop="articleCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="危化品名称" align="left" prop="hazardousName" show-overflow-tooltip min-width="120" />
        <el-table-column label="物料名称" align="left" prop="materialName" show-overflow-tooltip min-width="120" />
        <el-table-column label="CAS号" align="left" prop="casNo" show-overflow-tooltip min-width="120" />
        <el-table-column label="规格" align="left" prop="spec" show-overflow-tooltip min-width="120" />
        <el-table-column label="规格单位code" align="left" prop="specUnit" show-overflow-tooltip min-width="120" />
        <el-table-column label="规格单位name" align="left" prop="specUnit2" show-overflow-tooltip min-width="120" />
        <el-table-column label="当前库存量" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="最大存放量" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="标准库存量" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="所属基地" align="left" prop="field4" show-overflow-tooltip min-width="120" />
        <el-table-column label="扩展字段1" align="left" prop="extField1" show-overflow-tooltip min-width="120" />
        <el-table-column label="扩展字段2" align="left" prop="extField2" show-overflow-tooltip min-width="120" />
        <el-table-column label="扩展字段3" align="left" prop="extField3" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人工号" align="left" prop="createByEmpCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人姓名" align="left" prop="createByName" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="修改人工号" align="left" prop="field5" show-overflow-tooltip min-width="120" />
        <el-table-column label="修改人姓名" align="left" prop="field6" show-overflow-tooltip min-width="120" />
        <el-table-column label="修改时间" align="left" prop="field7" show-overflow-tooltip min-width="120" />
        <el-table-column label="删除人工号" align="left" prop="delete" show-overflow-tooltip min-width="120" />
        <el-table-column label="删除人姓名" align="left" prop="delete2" show-overflow-tooltip min-width="120" />
        <el-table-column label="删除时间" align="center" prop="deleteTime" width="120" show-overflow-tooltip />
        <el-table-column label="删除标记" align="left" prop="delete3" show-overflow-tooltip min-width="120" />
        <el-table-column label="标准库存单位：重量：KG,体积：L" align="left" prop="kgl" show-overflow-tooltip min-width="120" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:WHSTOCK:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:WHSTOCK:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="whstockFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="存储库ID" prop="id2">
              <el-input v-model="form.id2" placeholder="请输入存储库ID" />
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
            <el-form-item label="场所编码" prop="siteCode">
              <el-input v-model="form.siteCode" placeholder="请输入场所编码" />
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
            <el-form-item label="库位" prop="location">
              <el-input v-model="form.location" placeholder="请输入库位" />
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
            <el-form-item label="CAS号" prop="casNo">
              <el-input v-model="form.casNo" placeholder="请输入CAS号" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="规格" prop="spec">
              <el-input v-model="form.spec" placeholder="请输入规格" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="规格单位code" prop="specUnit">
              <el-input v-model="form.specUnit" placeholder="请输入规格单位code" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="规格单位name" prop="specUnit2">
              <el-input v-model="form.specUnit2" placeholder="请输入规格单位name" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="当前库存量" prop="field">
              <el-input v-model="form.field" placeholder="请输入当前库存量" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="最大存放量" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入最大存放量" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="标准库存量" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入标准库存量" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="所属基地" prop="field4">
              <el-input v-model="form.field4" placeholder="请输入所属基地" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="扩展字段1" prop="extField1">
              <el-input v-model="form.extField1" placeholder="请输入扩展字段1" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="扩展字段2" prop="extField2">
              <el-input v-model="form.extField2" placeholder="请输入扩展字段2" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="扩展字段3" prop="extField3">
              <el-input v-model="form.extField3" placeholder="请输入扩展字段3" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="创建人工号" prop="createByEmpCode">
              <el-input v-model="form.createByEmpCode" placeholder="请输入创建人工号" />
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
            <el-form-item label="修改人工号" prop="field5">
              <el-input v-model="form.field5" placeholder="请输入修改人工号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="修改人姓名" prop="field6">
              <el-input v-model="form.field6" placeholder="请输入修改人姓名" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="修改时间" prop="field7">
              <el-input v-model="form.field7" placeholder="请输入修改时间" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="删除人工号" prop="delete">
              <el-input v-model="form.delete" placeholder="请输入删除人工号" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="删除人姓名" prop="delete2">
              <el-input v-model="form.delete2" placeholder="请输入删除人姓名" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="删除时间" prop="deleteTime">
              <el-date-picker v-model="form.deleteTime" type="datetime" placeholder="选择删除时间" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="删除标记" prop="delete3">
              <el-input v-model="form.delete3" placeholder="请输入删除标记" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="标准库存单位：重量：KG,体积：L" prop="kgl">
              <el-input v-model="form.kgl" placeholder="请输入标准库存单位：重量：KG,体积：L" />
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

<script setup name="WHSTOCK" lang="ts">
import { addWHSTOCK, delWHSTOCK, getWHSTOCK, listWHSTOCK, updateWHSTOCK } from '@/api/scyh/whstock';
import { WHSTOCKForm, WHSTOCKQuery, WHSTOCKVO } from '@/api/scyh/whstock/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<WHSTOCKVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const whstockFormRef = ref<ElFormInstance>();

const initFormData: WHSTOCKForm = {
    id2: undefined,
    orgName: undefined,
    siteCode: undefined,
    storageName: undefined,
    location: undefined,
    articleCode: undefined,
    hazardousName: undefined,
    materialName: undefined,
    casNo: undefined,
    spec: undefined,
    specUnit: undefined,
    specUnit2: undefined,
    field: undefined,
    field2: undefined,
    field3: undefined,
    field4: undefined,
    extField1: undefined,
    extField2: undefined,
    extField3: undefined,
    createByEmpCode: undefined,
    createByName: undefined,
    field5: undefined,
    field6: undefined,
    field7: undefined,
    delete: undefined,
    delete2: undefined,
    deleteTime: undefined,
    delete3: undefined,
    kgl: undefined,
}
const data = reactive<PageData<WHSTOCKForm, WHSTOCKQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    id2: undefined,
    orgName: undefined,
    siteCode: undefined,
    storageName: undefined,
    location: undefined,
    articleCode: undefined,
  },
  rules: {
    id2: [
      { required: true, message: "存储库ID不能为空", trigger: "blur" }
    ],
    orgName: [
      { required: true, message: "所属机构不能为空", trigger: "blur" }
    ],
    siteCode: [
      { required: true, message: "场所编码不能为空", trigger: "blur" }
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
    const res = await listWHSTOCK(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; whstockFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: WHSTOCKVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增库存管理'; dialog.visible = true; }
const handleUpdate = async (row?: WHSTOCKVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getWHSTOCK(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改库存管理'; dialog.visible = true;
}
const submitForm = () => {
  whstockFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateWHSTOCK(form.value); }
        else { await addWHSTOCK(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: WHSTOCKVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delWHSTOCK(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/whstock/export', { ...queryParams.value }, '库存管理_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>