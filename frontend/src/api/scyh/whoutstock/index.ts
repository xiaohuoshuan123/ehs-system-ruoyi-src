import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { WHOUTSTOCKForm, WHOUTSTOCKQuery, WHOUTSTOCKVO } from './types';

// 查询列表
export function listWHOUTSTOCK(query?: WHOUTSTOCKQuery): AxiosPromise<WHOUTSTOCKVO[]> {
  return request({
    url: '/whtz/outstock/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getWHOUTSTOCK(id: string | number): AxiosPromise<WHOUTSTOCKVO> {
  return request({
    url: '/whtz/outstock/' + id,
    method: 'get'
  })
}

// 新增
export function addWHOUTSTOCK(data: WHOUTSTOCKForm) {
  return request({
    url: '/whtz/outstock',
    method: 'post',
    data: data
  })
}

// 修改
export function updateWHOUTSTOCK(data: WHOUTSTOCKForm) {
  return request({
    url: '/whtz/outstock',
    method: 'put',
    data: data
  })
}

// 删除
export function delWHOUTSTOCK(ids: string | number | Array<string | number>) {
  return request({
    url: '/whtz/outstock/' + ids,
    method: 'delete'
  })
}