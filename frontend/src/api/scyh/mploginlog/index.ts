import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { MPLOGINLOGForm, MPLOGINLOGQuery, MPLOGINLOGVO } from './types';

// 查询列表
export function listMPLOGINLOG(query?: MPLOGINLOGQuery): AxiosPromise<MPLOGINLOGVO[]> {
  return request({
    url: '/system/logininfor/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getMPLOGINLOG(id: string | number): AxiosPromise<MPLOGINLOGVO> {
  return request({
    url: '/system/logininfor/' + id,
    method: 'get'
  })
}

// 新增
export function addMPLOGINLOG(data: MPLOGINLOGForm) {
  return request({
    url: '/system/logininfor',
    method: 'post',
    data: data
  })
}

// 修改
export function updateMPLOGINLOG(data: MPLOGINLOGForm) {
  return request({
    url: '/system/logininfor',
    method: 'put',
    data: data
  })
}

// 删除
export function delMPLOGINLOG(ids: string | number | Array<string | number>) {
  return request({
    url: '/system/logininfor/' + ids,
    method: 'delete'
  })
}