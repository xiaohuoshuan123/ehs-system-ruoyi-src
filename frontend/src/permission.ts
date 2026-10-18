import { to as tos } from 'await-to-js';
import { ElMessage } from 'element-plus/es';
import * as NProgressModule from 'nprogress';
import 'nprogress/nprogress.css';
import { usePermissionStore } from '@/store/modules/permission';
import { useSettingsStore } from '@/store/modules/settings';
import { useUserStore } from '@/store/modules/user';
import { getToken } from '@/utils/auth';
import { isHandledRequestError, isRelogin } from '@/utils/request';
import { isHttp, isPathMatch } from '@/utils/validate';
import { constantRoutes } from './router';
import router from './router';

const NProgress = ('default' in NProgressModule ? NProgressModule.default : NProgressModule) as typeof NProgressModule;

NProgress.configure({ showSpinner: false });
const whiteList = ['/login', '/register', '/social-callback', '/register*', '/register/*'];

const isWhiteList = (path: string) => {
  return whiteList.some(pattern => isPathMatch(pattern, path));
};

router.beforeEach(async (to, from) => {
  NProgress.start();
  if (getToken()) {
    to.meta.title && useSettingsStore().setTitle(to.meta.title as string);
    /* has token*/
    if (to.path === '/login') {
      NProgress.done();
      return { path: '/' };
    } else if (isWhiteList(to.path)) {
      return true;
    } else {
      if (useUserStore().roles.length === 0) {
        isRelogin.show = true;
        // 判断当前用户是否已拉取完user_info信息
        const [err] = await tos(useUserStore().getInfo());
        if (err) {
          await useUserStore().logout();
          if (!isHandledRequestError(err)) {
            ElMessage.error(err instanceof Error ? err.message : String(err));
          }
          return { path: '/' };
        } else {
          isRelogin.show = false;
          const accessRoutes = await usePermissionStore().generateRoutes();
          // 清除之前注册的所有动态路由（非constantRoutes）
          const existingRoutes = router.getRoutes();
          for (const r of existingRoutes) {
            if (!constantRoutes.some(cr => cr.path === r.path) && !r.path.startsWith('/@') && r.path !== '/:pathMatch(.*)*') {
              router.removeRoute(r.name as string);
            }
          }
          // 重新添加所有动态路由
          accessRoutes.forEach(route => {
            if (!isHttp(route.path) && route.path) {
              const cleanPath = route.path.startsWith('/') ? route.path : '/' + route.path;
              try {
                router.addRoute({ ...route, path: cleanPath });
              } catch (e) {
                console.error('Failed to add route:', cleanPath, e);
              }
            }
          });
          // hack方法 确保addRoutes已完成
          return {
            path: to.path,
            replace: true,
            params: to.params,
            query: to.query,
            hash: to.hash,
            name: to.name as string
          };
        }
      } else {
        return true;
      }
    }
  } else {
    // 没有token
    if (isWhiteList(to.path)) {
      // 在免登录白名单，直接进入
      return true;
    } else {
      const redirect = encodeURIComponent(to.fullPath || '/');
      NProgress.done();
      return `/login?redirect=${redirect}`; // 否则全部重定向到登录页
    }
  }
});

router.afterEach(() => {
  NProgress.done();
});
