import type { Document_projectForm, Document_projectQuery, Document_projectVO } from '@/api/wd/document_project/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询DocumentProject列表
 * @param query
 * @returns {*}
 */
export const listDocument_project = (query?: Document_projectQuery): AxiosPromise<PageResult<Document_projectVO>> => {
  return request({
    url: '/wd/document_project/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询DocumentProject详细
 * @param id
 */
export const getDocument_project = (id: string | number): AxiosPromise<Document_projectVO> => {
  return request({
    url: '/wd/document_project/' + id,
    method: 'get'
  });
};

/**
 * 新增DocumentProject
 * @param data
 */
export const addDocument_project = (data: Document_projectForm) => {
  return request({
    url: '/wd/document_project',
    method: 'post',
    data: data
  });
};

/**
 * 修改DocumentProject
 * @param data
 */
export const updateDocument_project = (data: Document_projectForm) => {
  return request({
    url: '/wd/document_project',
    method: 'put',
    data: data
  });
};



/**
 * 删除DocumentProject
 * @param id
 */
export const delDocument_project = (id: string | number | Array<string | number>) => {
  return request({
    url: '/wd/document_project/' + id,
    method: 'delete'
  });
};
