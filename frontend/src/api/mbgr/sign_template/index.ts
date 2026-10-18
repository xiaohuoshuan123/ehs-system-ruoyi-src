import type { Sign_templateForm, Sign_templateQuery, Sign_templateVO } from '@/api/mbgr/sign_template/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询SignTemplate列表
 * @param query
 * @returns {*}
 */
export const listSign_template = (query?: Sign_templateQuery): AxiosPromise<PageResult<Sign_templateVO>> => {
  return request({
    url: '/mbgr/sign_template/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询SignTemplate详细
 * @param id
 */
export const getSign_template = (id: string | number): AxiosPromise<Sign_templateVO> => {
  return request({
    url: '/mbgr/sign_template/' + id,
    method: 'get'
  });
};

/**
 * 新增SignTemplate
 * @param data
 */
export const addSign_template = (data: Sign_templateForm) => {
  return request({
    url: '/mbgr/sign_template',
    method: 'post',
    data: data
  });
};

/**
 * 修改SignTemplate
 * @param data
 */
export const updateSign_template = (data: Sign_templateForm) => {
  return request({
    url: '/mbgr/sign_template',
    method: 'put',
    data: data
  });
};



/**
 * 删除SignTemplate
 * @param id
 */
export const delSign_template = (id: string | number | Array<string | number>) => {
  return request({
    url: '/mbgr/sign_template/' + id,
    method: 'delete'
  });
};
