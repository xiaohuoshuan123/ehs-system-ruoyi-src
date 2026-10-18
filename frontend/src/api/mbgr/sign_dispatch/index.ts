import type { Sign_dispatchForm, Sign_dispatchQuery, Sign_dispatchVO } from '@/api/mbgr/sign_dispatch/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询SignDispatch列表
 * @param query
 * @returns {*}
 */
export const listSign_dispatch = (query?: Sign_dispatchQuery): AxiosPromise<PageResult<Sign_dispatchVO>> => {
  return request({
    url: '/mbgr/sign_dispatch/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询SignDispatch详细
 * @param id
 */
export const getSign_dispatch = (id: string | number): AxiosPromise<Sign_dispatchVO> => {
  return request({
    url: '/mbgr/sign_dispatch/' + id,
    method: 'get'
  });
};

/**
 * 新增SignDispatch
 * @param data
 */
export const addSign_dispatch = (data: Sign_dispatchForm) => {
  return request({
    url: '/mbgr/sign_dispatch',
    method: 'post',
    data: data
  });
};

/**
 * 修改SignDispatch
 * @param data
 */
export const updateSign_dispatch = (data: Sign_dispatchForm) => {
  return request({
    url: '/mbgr/sign_dispatch',
    method: 'put',
    data: data
  });
};



/**
 * 删除SignDispatch
 * @param id
 */
export const delSign_dispatch = (id: string | number | Array<string | number>) => {
  return request({
    url: '/mbgr/sign_dispatch/' + id,
    method: 'delete'
  });
};
