import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { LZSANDForm, LZSANDQuery, LZSANDVO } from './types';

// 查询列表
export function listLZSAND(query?: LZSANDQuery): AxiosPromise<LZSANDVO[]> {
  return request({
    url: '/lzbz/law_model/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getLZSAND(id: string | number): AxiosPromise<LZSANDVO> {
  return request({
    url: '/lzbz/law_model/' + id,
    method: 'get'
  })
}

// 新增
export function addLZSAND(data: LZSANDForm) {
  return request({
    url: '/lzbz/law_model',
    method: 'post',
    data: data
  })
}

// 修改
export function updateLZSAND(data: LZSANDForm) {
  return request({
    url: '/lzbz/law_model',
    method: 'put',
    data: data
  })
}

// 删除
export function delLZSAND(ids: string | number | Array<string | number>) {
  return request({
    url: '/lzbz/law_model/' + ids,
    method: 'delete'
  })
}