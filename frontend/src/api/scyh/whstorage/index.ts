import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { WHSTORAGEForm, WHSTORAGEQuery, WHSTORAGEVO } from './types';

// 查询列表
export function listWHSTORAGE(query?: WHSTORAGEQuery): AxiosPromise<WHSTORAGEVO[]> {
  return request({
    url: '/whtz/storage/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getWHSTORAGE(id: string | number): AxiosPromise<WHSTORAGEVO> {
  return request({
    url: '/whtz/storage/' + id,
    method: 'get'
  })
}

// 新增
export function addWHSTORAGE(data: WHSTORAGEForm) {
  return request({
    url: '/whtz/storage',
    method: 'post',
    data: data
  })
}

// 修改
export function updateWHSTORAGE(data: WHSTORAGEForm) {
  return request({
    url: '/whtz/storage',
    method: 'put',
    data: data
  })
}

// 删除
export function delWHSTORAGE(ids: string | number | Array<string | number>) {
  return request({
    url: '/whtz/storage/' + ids,
    method: 'delete'
  })
}