import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { WHSTOCKForm, WHSTOCKQuery, WHSTOCKVO } from './types';

// 查询列表
export function listWHSTOCK(query?: WHSTOCKQuery): AxiosPromise<WHSTOCKVO[]> {
  return request({
    url: '/whtz/stock/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getWHSTOCK(id: string | number): AxiosPromise<WHSTOCKVO> {
  return request({
    url: '/whtz/stock/' + id,
    method: 'get'
  })
}

// 新增
export function addWHSTOCK(data: WHSTOCKForm) {
  return request({
    url: '/whtz/stock',
    method: 'post',
    data: data
  })
}

// 修改
export function updateWHSTOCK(data: WHSTOCKForm) {
  return request({
    url: '/whtz/stock',
    method: 'put',
    data: data
  })
}

// 删除
export function delWHSTOCK(ids: string | number | Array<string | number>) {
  return request({
    url: '/whtz/stock/' + ids,
    method: 'delete'
  })
}