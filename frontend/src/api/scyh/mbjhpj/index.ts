import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { MBJHPJForm, MBJHPJQuery, MBJHPJVO } from './types';

// 查询列表
export function listMBJHPJ(query?: MBJHPJQuery): AxiosPromise<MBJHPJVO[]> {
  return request({
    url: '/mbgh/plan/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getMBJHPJ(id: string | number): AxiosPromise<MBJHPJVO> {
  return request({
    url: '/mbgh/plan/' + id,
    method: 'get'
  })
}

// 新增
export function addMBJHPJ(data: MBJHPJForm) {
  return request({
    url: '/mbgh/plan',
    method: 'post',
    data: data
  })
}

// 修改
export function updateMBJHPJ(data: MBJHPJForm) {
  return request({
    url: '/mbgh/plan',
    method: 'put',
    data: data
  })
}

// 删除
export function delMBJHPJ(ids: string | number | Array<string | number>) {
  return request({
    url: '/mbgh/plan/' + ids,
    method: 'delete'
  })
}