import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { TemplateForm, TemplateQuery, TemplateVO } from './types';

// 查询列表
export function listTemplate(query?: TemplateQuery): AxiosPromise<TemplateVO[]> {
  return request({
    url: '/mbgr/sign_template/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getTemplate(id: string | number): AxiosPromise<TemplateVO> {
  return request({
    url: '/mbgr/sign_template/' + id,
    method: 'get'
  })
}

// 新增
export function addTemplate(data: TemplateForm) {
  return request({
    url: '/mbgr/sign_template',
    method: 'post',
    data: data
  })
}

// 修改
export function updateTemplate(data: TemplateForm) {
  return request({
    url: '/mbgr/sign_template',
    method: 'put',
    data: data
  })
}

// 删除
export function delTemplate(ids: string | number | Array<string | number>) {
  return request({
    url: '/mbgr/sign_template/' + ids,
    method: 'delete'
  })
}