<template>
  <div class="app-container">
    <el-card shadow="never" class="mb-4">
      <template #header>
        <div class="card-header">
          <span>可视化页面设计器</span>
          <div>
            <el-button type="primary" @click="handleSave" :loading="saving">保存配置</el-button>
            <el-button @click="handlePreview">预览效果</el-button>
            <el-button @click="handleExport">导出JSON</el-button>
          </div>
        </div>
      </template>

      <el-row :gutter="20">
        <!-- 左侧：模块选择 -->
        <el-col :span="5">
          <el-card shadow="never">
            <template #header>选择模块</template>
            <el-input v-model="searchKey" placeholder="搜索模块..." clearable size="small" class="mb-2" />
            <el-scrollbar height="400px">
              <div
                v-for="item in filteredModules"
                :key="item.path"
                class="module-item"
                :class="{ active: currentModule?.path === item.path }"
                @click="selectModule(item)"
              >
                <el-icon><component /></el-icon>
                <span>{{ item.title }}</span>
              </div>
            </el-scrollbar>
          </el-card>
        </el-col>

        <!-- 中间：表格列配置 -->
        <el-col :span="12">
          <el-card shadow="never">
            <template #header>
              <div class="card-header">
                <span>表格列配置</span>
                <el-button type="primary" size="small" @click="addColumn">新增列</el-button>
              </div>
            </template>
            <el-table :data="tableColumns" border size="small" v-if="tableColumns.length > 0">
              <el-table-column type="index" label="序号" width="55" align="center" />
              <el-table-column label="字段名" width="140" align="center">
                <template #default="{ row }">
                  <el-input v-model="row.prop" size="small" placeholder="prop" />
                </template>
              </el-table-column>
              <el-table-column label="列标题" width="140" align="center">
                <template #default="{ row }">
                  <el-input v-model="row.label" size="small" placeholder="中文标题" />
                </template>
              </el-table-column>
              <el-table-column label="列宽" width="90" align="center">
                <template #default="{ row }">
                  <el-input-number v-model="row.width" :min="50" :max="500" size="small" controls-position="right" style="width: 80px" />
                </template>
              </el-table-column>
              <el-table-column label="对齐" width="100" align="center">
                <template #default="{ row }">
                  <el-select v-model="row.align" size="small" style="width: 80px">
                    <el-option label="左" value="left" />
                    <el-option label="中" value="center" />
                    <el-option label="右" value="right" />
                  </el-select>
                </template>
              </el-table-column>
              <el-table-column label="是否显示" width="90" align="center">
                <template #default="{ row }">
                  <el-switch v-model="row.show" />
                </template>
              </el-table-column>
              <el-table-column label="操作" width="80" align="center">
                <template #default="{ $index }">
                  <el-button type="danger" size="small" link @click="removeColumn($index)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
            <el-empty v-else description="请先选择左侧模块" />
          </el-card>
        </el-col>

        <!-- 右侧：表单字段配置 -->
        <el-col :span="7">
          <el-card shadow="never">
            <template #header>
              <div class="card-header">
                <span>表单字段配置</span>
                <el-button type="primary" size="small" @click="addFormField">新增字段</el-button>
              </div>
            </template>
            <el-scrollbar height="400px">
              <div v-for="(field, idx) in formFields" :key="idx" class="form-field-item">
                <el-row :gutter="8">
                  <el-col :span="8">
                    <el-input v-model="field.prop" size="small" placeholder="字段名" />
                  </el-col>
                  <el-col :span="8">
                    <el-input v-model="field.label" size="small" placeholder="中文标签" />
                  </el-col>
                  <el-col :span="6">
                    <el-select v-model="field.type" size="small" placeholder="类型" style="width: 100%">
                      <el-option label="文本" value="input" />
                      <el-option label="数字" value="number" />
                      <el-option label="日期" value="date" />
                      <el-option label="下拉" value="select" />
                      <el-option label="文本域" value="textarea" />
                      <el-option label="开关" value="switch" />
                    </el-select>
                  </el-col>
                  <el-col :span="2">
                    <el-button type="danger" size="small" link @click="removeFormField(idx)">x</el-button>
                  </el-col>
                </el-row>
                <el-row :gutter="8" class="mt-1">
                  <el-col :span="6">
                    <el-checkbox v-model="field.required">必填</el-checkbox>
                  </el-col>
                  <el-col :span="8">
                    <el-input v-model="field.placeholder" size="small" placeholder="提示文字" />
                  </el-col>
                  <el-col :span="10" v-if="field.type === 'select'">
                    <el-input v-model="field.options" size="small" placeholder="选项(逗号分隔)" />
                  </el-col>
                </el-row>
              </div>
              <el-empty v-if="formFields.length === 0" description="无表单字段" />
            </el-scrollbar>
          </el-card>
        </el-col>
      </el-row>

      <!-- 底部：JSON预览 -->
      <el-card shadow="never" class="mt-4" v-if="currentModule">
        <template #header>配置JSON预览</template>
        <el-input
          v-model="configJson"
          type="textarea"
          :rows="8"
          readonly
          placeholder="选择模块后自动生成配置JSON"
        />
      </el-card>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, reactive, watch } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";

// 模块列表（从路由配置读取）
const searchKey = ref("");
const modules = ref([
  { title: "安全目标", path: "mbmb/target" },
  { title: "安全规划", path: "mb/mbghplan" },
  { title: "安全责任", path: "jgzr/safety_duty" },
  { title: "安全费用", path: "jgfy/safety_cost" },
  { title: "组织架构", path: "jgaw/architecture" },
  { title: "安全会议", path: "jgaw/safety_meeting" },
  { title: "法律法规", path: "zbfl/laws" },
  { title: "隐患管理", path: "scyh/hiddentrouble" },
  { title: "应急预案", path: "yjep/emergency_plan" },
  { title: "事故管理", path: "scfx/accident" },
  { title: "风险清单", path: "scfx/risk_control_list" },
  { title: "文档管理", path: "wd/document_project" },
]);

const filteredModules = computed(() => {
  if (!searchKey.value) return modules.value;
  return modules.value.filter((m) =>
    m.title.toLowerCase().includes(searchKey.value.toLowerCase())
  );
});

const currentModule = ref<any>(null);
const tableColumns = ref<any[]>([]);
const formFields = ref<any[]>([]);
const saving = ref(false);

// 计算配置JSON
const configJson = computed(() => {
  if (!currentModule.value) return "";
  return JSON.stringify(
    {
      module: currentModule.value.path,
      title: currentModule.value.title,
      table: tableColumns.value,
      form: formFields.value,
    },
    null,
    2
  );
});

// 选择模块
function selectModule(item: any) {
  currentModule.value = item;
  // 从localStorage读取已保存的配置
  const saved = localStorage.getItem(`vd_config_${item.path}`);
  if (saved) {
    try {
      const config = JSON.parse(saved);
      tableColumns.value = config.table || [];
      formFields.value = config.form || [];
    } catch {
      tableColumns.value = [];
      formFields.value = [];
    }
  } else {
    // 默认配置
    tableColumns.value = [
      { prop: "id", label: "序号", width: 80, align: "center", show: true },
    ];
    formFields.value = [];
  }
}

// 新增表格列
function addColumn() {
  tableColumns.value.push({
    prop: "",
    label: "",
    width: 120,
    align: "center",
    show: true,
  });
}

// 删除表格列
function removeColumn(index: number) {
  tableColumns.value.splice(index, 1);
}

// 新增表单字段
function addFormField() {
  formFields.value.push({
    prop: "",
    label: "",
    type: "input",
    required: false,
    placeholder: "",
    options: "",
  });
}

// 删除表单字段
function removeFormField(index: number) {
  formFields.value.splice(index, 1);
}

// 保存配置
function handleSave() {
  if (!currentModule.value) {
    ElMessage.warning("请先选择模块");
    return;
  }
  saving.value = true;
  const config = {
    module: currentModule.value.path,
    title: currentModule.value.title,
    table: tableColumns.value,
    form: formFields.value,
    updatedAt: new Date().toISOString(),
  };
  localStorage.setItem(`vd_config_${currentModule.value.path}`, JSON.stringify(config));
  saving.value = false;
  ElMessage.success(`配置已保存：${currentModule.value.title}`);
}

// 预览
function handlePreview() {
  if (!currentModule.value) {
    ElMessage.warning("请先选择模块");
    return;
  }
  ElMessageBox.alert(configJson.value, "配置预览", {
    customClass: "json-preview-dialog",
    confirmButtonText: "关闭",
  });
}

// 导出JSON
function handleExport() {
  if (!currentModule.value) {
    ElMessage.warning("请先选择模块");
    return;
  }
  const blob = new Blob([configJson.value], { type: "application/json" });
  const url = URL.createObjectURL(blob);
  const a = document.createElement("a");
  a.href = url;
  a.download = `config_${currentModule.value.path.replace(/\//g, "_")}.json`;
  a.click();
  URL.revokeObjectURL(url);
  ElMessage.success("JSON文件已导出");
}
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.module-item {
  padding: 8px 12px;
  cursor: pointer;
  border-radius: 4px;
  transition: all 0.2s;
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 14px;
}
.module-item:hover {
  background: #f0f0f0;
}
.module-item.active {
  background: #409eff;
  color: #fff;
}
.form-field-item {
  padding: 8px;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  margin-bottom: 8px;
}
.mt-1 {
  margin-top: 4px;
}
.mb-2 {
  margin-bottom: 8px;
}
.mt-4 {
  margin-top: 16px;
}
.mb-4 {
  margin-bottom: 16px;
}
</style>
