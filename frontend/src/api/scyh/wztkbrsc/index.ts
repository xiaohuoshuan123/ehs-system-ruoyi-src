import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { WZTKBRSCForm, WZTKBRSCQuery, WZTKBRSCVO } from './types';

// 查询列表
export function listWZTKBRSC(query?: WZTKBRSCQuery): AxiosPromise<WZTKBRSCVO[]> {
  return request({
    url: '/wztk/brscore_standard/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getWZTKBRSC(id: string | number): AxiosPromise<WZTKBRSCVO> {
  return request({
    url: '/wztk/brscore_standard/' + id,
    method: 'get'
  })
}

// 新增
export function addWZTKBRSC(data: WZTKBRSCForm) {
  return request({
    url: '/wztk/brscore_standard',
    method: 'post',
    data: data
  })
}

// 修改
export function updateWZTKBRSC(data: WZTKBRSCForm) {
  return request({
    url: '/wztk/brscore_standard',
    method: 'put',
    data: data
  })
}

// 删除
export function delWZTKBRSC(ids: string | number | Array<string | number>) {
  return request({
    url: '/wztk/brscore_standard/' + ids,
    method: 'delete'
  })
}