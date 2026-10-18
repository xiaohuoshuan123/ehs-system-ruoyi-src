import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { PXFIONTROLForm, PXFIONTROLQuery, PXFIONTROLVO } from './types';

// 查询列表
export function listPXFIONTROL(query?: PXFIONTROLQuery): AxiosPromise<PXFIONTROLVO[]> {
  return request({
    url: '/pxzs/enterprise_person/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getPXFIONTROL(id: string | number): AxiosPromise<PXFIONTROLVO> {
  return request({
    url: '/pxzs/enterprise_person/' + id,
    method: 'get'
  })
}

// 新增
export function addPXFIONTROL(data: PXFIONTROLForm) {
  return request({
    url: '/pxzs/enterprise_person',
    method: 'post',
    data: data
  })
}

// 修改
export function updatePXFIONTROL(data: PXFIONTROLForm) {
  return request({
    url: '/pxzs/enterprise_person',
    method: 'put',
    data: data
  })
}

// 删除
export function delPXFIONTROL(ids: string | number | Array<string | number>) {
  return request({
    url: '/pxzs/enterprise_person/' + ids,
    method: 'delete'
  })
}