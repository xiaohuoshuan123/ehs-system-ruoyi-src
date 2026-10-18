<template>
  <div class="p-2">
    <el-card shadow="hover" class="search-panel">
      <el-form ref="queryFormRef" :model="queryParams" :inline="true" label-width="80px">
          <el-form-item label="参考人员保存状态" prop="field">
            <el-input v-model="queryParams.field" placeholder="请输入参考人员保存状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="当前状态" prop="field2">
            <el-input v-model="queryParams.field2" placeholder="请输入当前状态" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="组织编码" prop="orgCode">
            <el-input v-model="queryParams.orgCode" placeholder="请输入组织编码" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="所属机构" prop="orgName">
            <el-input v-model="queryParams.orgName" placeholder="请输入所属机构" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="考试名称" prop="examName">
            <el-input v-model="queryParams.examName" placeholder="请输入考试名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
          </el-form-item>
          <el-form-item label="证明名称" prop="certificateName">
            <el-input v-model="queryParams.certificateName" placeholder="请输入证明名称" clearable @keyup.enter="handleQuery" style="width: 180px" />
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
          <el-col :span="12"><h3>考试发布列表</h3></el-col>
          <el-col :span="12" style="text-align: right">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['scyh:exam:add']">新增</el-button>
            <el-button type="success" plain icon="Edit" :disabled="!single" @click="handleUpdate()" v-hasPermi="['scyh:exam:edit']">修改</el-button>
            <el-button type="danger" plain icon="Delete" :disabled="!multiple" @click="handleDelete()" v-hasPermi="['scyh:exam:remove']">删除</el-button>
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['scyh:exam:export']">导出</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column type="index" label="序号" width="55" align="center" />
        <el-table-column label="考试发布ID" align="center" prop="id" width="160" />
        <el-table-column label="参考人员保存状态" align="left" prop="field" show-overflow-tooltip min-width="120" />
        <el-table-column label="当前状态" align="left" prop="field2" show-overflow-tooltip min-width="120" />
        <el-table-column label="组织编码" align="left" prop="orgCode" show-overflow-tooltip min-width="120" />
        <el-table-column label="所属机构" align="left" prop="orgName" show-overflow-tooltip min-width="120" />
        <el-table-column label="考试名称" align="left" prop="examName" show-overflow-tooltip min-width="120" />
        <el-table-column label="证明名称" align="left" prop="certificateName" show-overflow-tooltip min-width="120" />
        <el-table-column label="试卷名称" align="left" prop="paperName" show-overflow-tooltip min-width="120" />
        <el-table-column label="安全学习主表ID" align="left" prop="id2" show-overflow-tooltip min-width="120" />
        <el-table-column label="考试类型" align="left" prop="examType" show-overflow-tooltip min-width="120" />
        <el-table-column label="考试时间" align="center" prop="examTime" width="120" show-overflow-tooltip />
        <el-table-column label="考试时间起" align="center" prop="examTime2" width="120" show-overflow-tooltip />
        <el-table-column label="考试时间止" align="center" prop="examTime3" width="120" show-overflow-tooltip />
        <el-table-column label="已考人数" align="left" prop="field3" show-overflow-tooltip min-width="120" />
        <el-table-column label="未考人数" align="left" prop="field4" show-overflow-tooltip min-width="120" />
        <el-table-column label="平均分" align="left" prop="field5" show-overflow-tooltip min-width="120" />
        <el-table-column label="合格率" align="left" prop="pass" show-overflow-tooltip min-width="120" />
        <el-table-column label="不合格人数" align="left" prop="field6" show-overflow-tooltip min-width="120" />
        <el-table-column label="参与率" align="left" prop="field7" show-overflow-tooltip min-width="120" />
        <el-table-column label="是否阅卷" align="left" prop="is" show-overflow-tooltip min-width="120" />
        <el-table-column label="考试次数" align="left" prop="exam" show-overflow-tooltip min-width="120" />
        <el-table-column label="考前练习" align="left" prop="field8" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建人" align="left" prop="createBy" show-overflow-tooltip min-width="120" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="120" show-overflow-tooltip />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['scyh:exam:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['scyh:exam:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog :title="dialog.title" v-model="dialog.visible" width="780px" append-to-body>
      <el-form ref="examFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="参考人员保存状态" prop="field">
              <el-input v-model="form.field" placeholder="请输入参考人员保存状态" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="当前状态" prop="field2">
              <el-input v-model="form.field2" placeholder="请输入当前状态" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="组织编码" prop="orgCode">
              <el-input v-model="form.orgCode" placeholder="请输入组织编码" />
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
            <el-form-item label="考试名称" prop="examName">
              <el-input v-model="form.examName" placeholder="请输入考试名称" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="证明名称" prop="certificateName">
              <el-input v-model="form.certificateName" placeholder="请输入证明名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="试卷名称" prop="paperName">
              <el-input v-model="form.paperName" placeholder="请输入试卷名称" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="安全学习主表ID" prop="id2">
              <el-input v-model="form.id2" placeholder="请输入安全学习主表ID" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="考试类型" prop="examType">
              <el-input v-model="form.examType" placeholder="请输入考试类型" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="考试时间" prop="examTime">
              <el-date-picker v-model="form.examTime" type="datetime" placeholder="选择考试时间" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="考试时间起" prop="examTime2">
              <el-date-picker v-model="form.examTime2" type="datetime" placeholder="选择考试时间起" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="考试时间止" prop="examTime3">
              <el-date-picker v-model="form.examTime3" type="datetime" placeholder="选择考试时间止" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="已考人数" prop="field3">
              <el-input v-model="form.field3" placeholder="请输入已考人数" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="未考人数" prop="field4">
              <el-input v-model="form.field4" placeholder="请输入未考人数" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="平均分" prop="field5">
              <el-input v-model="form.field5" placeholder="请输入平均分" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="合格率" prop="pass">
              <el-input v-model="form.pass" placeholder="请输入合格率" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="不合格人数" prop="field6">
              <el-input v-model="form.field6" placeholder="请输入不合格人数" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="参与率" prop="field7">
              <el-input v-model="form.field7" placeholder="请输入参与率" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="是否阅卷" prop="is">
              <el-input v-model="form.is" placeholder="请输入是否阅卷" />
            </el-form-item>
          </el-col>


          <el-col :span="12">
            <el-form-item label="考试次数" prop="exam">
              <el-input v-model="form.exam" placeholder="请输入考试次数" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="考前练习" prop="field8">
              <el-input v-model="form.field8" placeholder="请输入考前练习" />
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

<script setup name="Exam" lang="ts">
import { addExam, delExam, getExam, listExam, updateExam } from '@/api/scyh/exam';
import { ExamForm, ExamQuery, ExamVO } from '@/api/scyh/exam/types';

import { useLoading } from '@/hooks/async/useLoading';
import modal from '@/plugins/modal';
import { download } from '@/utils/request';
const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { loading, buttonLoading } = useLoading();
const dataList = ref<ExamVO[]>([]);
const total = ref(0);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);

const queryFormRef = ref<ElFormInstance>();
const examFormRef = ref<ElFormInstance>();

const initFormData: ExamForm = {
    field: undefined,
    field2: undefined,
    orgCode: undefined,
    orgName: undefined,
    examName: undefined,
    certificateName: undefined,
    paperName: undefined,
    id2: undefined,
    examType: undefined,
    examTime: undefined,
    examTime2: undefined,
    examTime3: undefined,
    field3: undefined,
    field4: undefined,
    field5: undefined,
    pass: undefined,
    field6: undefined,
    field7: undefined,
    is: undefined,
    exam: undefined,
    field8: undefined,
}
const data = reactive<PageData<ExamForm, ExamQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    field: undefined,
    field2: undefined,
    orgCode: undefined,
    orgName: undefined,
    examName: undefined,
    certificateName: undefined,
  },
  rules: {
    field: [
      { required: true, message: "参考人员保存状态不能为空", trigger: "blur" }
    ],
    field2: [
      { required: true, message: "当前状态不能为空", trigger: "blur" }
    ],
    orgCode: [
      { required: true, message: "组织编码不能为空", trigger: "blur" }
    ],
    orgName: [
      { required: true, message: "所属机构不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

const dialog = reactive({ visible: false, title: '' });

const getList = async () => {
  loading.value = true;
  try {
    const res = await listExam(queryParams.value);
    dataList.value = res.rows;
    total.value = res.total;
  } finally {
    loading.value = false;
  }
}

const cancel = () => { reset(); dialog.visible = false; }
const reset = () => { form.value = { ...initFormData }; examFormRef.value?.resetFields(); }
const handleQuery = () => { queryParams.value.pageNum = 1; getList(); }
const resetQuery = () => { queryFormRef.value?.resetFields(); handleQuery(); }
const handleSelectionChange = (selection: ExamVO[]) => {
  ids.value = selection.map(item => item.id || '');
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}
const handleAdd = () => { reset(); dialog.title = '新增考试发布'; dialog.visible = true; }
const handleUpdate = async (row?: ExamVO) => {
  reset();
  const id = row?.id || ids.value[0];
  const res = await getExam(id);
  Object.assign(form.value, res.data);
  dialog.title = '修改考试发布'; dialog.visible = true;
}
const submitForm = () => {
  examFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      try {
        if (form.value.id) { await updateExam(form.value); }
        else { await addExam(form.value); }
        proxy?.$modal.msgSuccess('操作成功');
        dialog.visible = false;
        getList();
      } finally { buttonLoading.value = false; }
    }
  })
}
const handleDelete = async (row?: ExamVO) => {
  const delIds = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除？');
  loading.value = true;
  try {
    await delExam(delIds);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } finally { loading.value = false; }
}
const handleExport = () => {
  proxy?.download('scyh/exam/export', { ...queryParams.value }, '考试发布_' + new Date().getTime() + '.xlsx');
}
onMounted(() => { getList(); })
</script>