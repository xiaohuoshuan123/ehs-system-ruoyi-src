import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { WHINSTOCKForm, WHINSTOCKQuery, WHINSTOCKVO } from './types';

// 查询列表
export function listWHINSTOCK(query?: WHINSTOCKQuery): AxiosPromise<WHINSTOCKVO[]> {
  return request({
    url: '/whtz/instock/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getWHINSTOCK(id: string | number): AxiosPromise<WHINSTOCKVO> {
  return request({
    url: '/whtz/instock/' + id,
    method: 'get'
  })
}

// 新增
export function addWHINSTOCK(data: WHINSTOCKForm) {
  return request({
    url: '/whtz/instock',
    method: 'post',
    data: data
  })
}

// 修改
export function updateWHINSTOCK(data: WHINSTOCKForm) {
  return request({
    url: '/whtz/instock',
    method: 'put',
    data: data
  })
}

// 删除
export function delWHINSTOCK(ids: string | number | Array<string | number>) {
  return request({
    url: '/whtz/instock/' + ids,
    method: 'delete'
  })
}