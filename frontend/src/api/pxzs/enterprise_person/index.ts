import type { Enterprise_personForm, Enterprise_personQuery, Enterprise_personVO } from '@/api/pxzs/enterprise_person/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询EnterprisePerson列表
 * @param query
 * @returns {*}
 */
export const listEnterprise_person = (query?: Enterprise_personQuery): AxiosPromise<PageResult<Enterprise_personVO>> => {
  return request({
    url: '/pxzs/enterprise_person/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询EnterprisePerson详细
 * @param id
 */
export const getEnterprise_person = (id: string | number): AxiosPromise<Enterprise_personVO> => {
  return request({
    url: '/pxzs/enterprise_person/' + id,
    method: 'get'
  });
};

/**
 * 新增EnterprisePerson
 * @param data
 */
export const addEnterprise_person = (data: Enterprise_personForm) => {
  return request({
    url: '/pxzs/enterprise_person',
    method: 'post',
    data: data
  });
};

/**
 * 修改EnterprisePerson
 * @param data
 */
export const updateEnterprise_person = (data: Enterprise_personForm) => {
  return request({
    url: '/pxzs/enterprise_person',
    method: 'put',
    data: data
  });
};



/**
 * 删除EnterprisePerson
 * @param id
 */
export const delEnterprise_person = (id: string | number | Array<string | number>) => {
  return request({
    url: '/pxzs/enterprise_person/' + id,
    method: 'delete'
  });
};
