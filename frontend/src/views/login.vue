<template>
  <div class="login">
    <div class="login-shell">
      <section class="login-brand">
        <span class="brand-pill">Plus UI Workspace</span>
        <h1 class="brand-title">企业级后台管理系统</h1>
        <p class="brand-desc">
          真正面向企业级的应用框架 组件化 模块化 轻耦合 高扩展 针对企业痛点 业界一流技术栈
          <br />
          重写 RuoYi-Vue 所有功能 集成 Sa-Token、Mybatis-Plus、WarmFlow、SpringDoc、Hutool、OSS 定期同步。
        </p>
        <div class="brand-highlights">
          <span v-for="item in highlights" :key="item" class="highlight-chip">{{ item }}</span>
        </div>
        <div class="brand-metrics">
          <article v-for="item in quickStats" :key="item.label" class="metric-card">
            <strong>{{ item.value }}</strong>
            <span>{{ item.label }}</span>
          </article>
        </div>
      </section>

      <el-form ref="loginRef" :model="loginForm" :rules="loginRules" class="login-form">
        <div class="title-box">
          <div>
            <p class="eyebrow">系统登录</p>
            <h3 class="title">欢迎回来</h3>
            <p class="subtitle">请输入账号和密码登录系统</p>
          </div>
        </div>

        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            type="text"
            size="large"
            auto-complete="off"
            placeholder="请输入账号"
          >
            <template #prefix><svg-icon icon-class="user" class="el-input__icon input-icon" /></template>
          </el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            size="large"
            auto-complete="off"
            placeholder="请输入密码"
            @keyup.enter="handleLogin"
          >
            <template #prefix><svg-icon icon-class="password" class="el-input__icon input-icon" /></template>
          </el-input>
        </el-form-item>

        <div class="form-meta">
          <el-checkbox v-model="loginForm.rememberMe">记住密码</el-checkbox>
        </div>

        <el-form-item class="submit-row">
          <el-button :loading="loading" size="large" type="primary" class="submit-button" @click.prevent="handleLogin">
            <span v-if="!loading">登 录</span>
            <span v-else>登录中...</span>
          </el-button>
        </el-form-item>
      </el-form>
    </div>

    <div class="el-login-footer">
      <span>Copyright © 2024-{{ currentYear }} 永杰新材 All Rights Reserved.</span>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useUserStore } from '@/store/modules/user';
import type { FormInstance, FormRules } from 'element-plus';
import { LoginData } from '@/api/types';

const currentYear = new Date().getFullYear();
const userStore = useUserStore();
const router = useRouter();
const loginRef = ref<FormInstance>();
const loading = ref(false);

const loginForm = ref<LoginData>({
  username: 'admin',
  password: 'admin123',
  rememberMe: false
} as LoginData);

const loginRules: FormRules = {
  username: [{ required: true, trigger: 'blur', message: '请输入账号' }],
  password: [{ required: true, trigger: 'blur', message: '请输入密码' }]
};

const getLoginData = () => {
  const username = localStorage.getItem('username');
  const rememberMe = localStorage.getItem('rememberMe');
  loginForm.value = {
    username: username === null ? 'admin' : username,
    password: username === null ? 'admin123' : '',
    rememberMe: rememberMe === 'true'
  } as LoginData;
};

const handleLogin = () => {
  loginRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      loading.value = true;
      if (loginForm.value.rememberMe) {
        localStorage.setItem('username', String(loginForm.value.username));
        localStorage.setItem('rememberMe', 'true');
      } else {
        localStorage.removeItem('username');
        localStorage.removeItem('rememberMe');
      }
      try {
        await userStore.login(loginForm.value);
        const redirect = router.currentRoute.value.query?.redirect as string || '/';
        await router.push(redirect);
      } catch (e) {
        console.error('登录失败', e);
      } finally {
        loading.value = false;
      }
    }
  });
};

onMounted(() => {
  getLoginData();
});
</script>

<style lang="scss" scoped>
.login {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 40px 24px;
  background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%);
}

.login-shell {
  width: min(900px, 100%);
  display: grid;
  grid-template-columns: 1fr 380px;
  gap: 24px;
  align-items: center;
}

.login-brand {
  padding: 40px;
  border-radius: 20px;
  background: rgba(255, 255, 255, 0.05);
  border: 1px solid rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  color: #fff;
}

.brand-pill {
  display: inline-block;
  padding: 6px 12px;
  border-radius: 20px;
  background: rgba(53, 109, 255, 0.3);
  font-size: 12px;
  color: #a8c7ff;
}

.brand-title {
  margin: 20px 0 12px;
  font-size: 32px;
  font-weight: 700;
  color: #fff;
}

.brand-desc {
  margin: 0;
  font-size: 14px;
  line-height: 1.8;
  color: rgba(255, 255, 255, 0.7);
}

.login-form {
  padding: 36px;
  border-radius: 20px;
  background: #fff;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
}

.title-box {
  margin-bottom: 28px;
}

.eyebrow {
  margin: 0 0 6px;
  font-size: 12px;
  font-weight: 600;
  color: #356bff;
  text-transform: uppercase;
  letter-spacing: 0.05em;
}

.title {
  margin: 0 0 6px;
  font-size: 24px;
  font-weight: 700;
  color: #1a1a2e;
}

.subtitle {
  margin: 0;
  font-size: 14px;
  color: #666;
}

.login-form .el-input {
  height: 48px;
}

.login-form .input-icon {
  height: 46px;
  width: 14px;
  margin-left: 0;
}

.form-meta {
  display: flex;
  align-items: center;
  margin: -2px 0 20px;
}

.submit-row {
  margin-bottom: 0;
}

.submit-button {
  width: 100%;
  height: 50px;
  border-radius: 12px;
  font-size: 16px;
  font-weight: 600;
  background: linear-gradient(135deg, #356bff 0%, #1a56db 100%);
  border: none;
}

.el-login-footer {
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  padding: 20px;
  color: rgba(255, 255, 255, 0.5);
  font-size: 12px;
}

@media (max-width: 768px) {
  .login-shell {
    grid-template-columns: 1fr;
    max-width: 400px;
  }

  .login-brand {
    display: none;
  }
}
</style>
