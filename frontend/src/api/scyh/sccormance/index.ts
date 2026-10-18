import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { SCCORMANCEForm, SCCORMANCEQuery, SCCORMANCEVO } from './types';

// 查询列表
export function listSCCORMANCE(query?: SCCORMANCEQuery): AxiosPromise<SCCORMANCEVO[]> {
  return request({
    url: '/lzsj/law_performance/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getSCCORMANCE(id: string | number): AxiosPromise<SCCORMANCEVO> {
  return request({
    url: '/lzsj/law_performance/' + id,
    method: 'get'
  })
}

// 新增
export function addSCCORMANCE(data: SCCORMANCEForm) {
  return request({
    url: '/lzsj/law_performance',
    method: 'post',
    data: data
  })
}

// 修改
export function updateSCCORMANCE(data: SCCORMANCEForm) {
  return request({
    url: '/lzsj/law_performance',
    method: 'put',
    data: data
  })
}

// 删除
export function delSCCORMANCE(ids: string | number | Array<string | number>) {
  return request({
    url: '/lzsj/law_performance/' + ids,
    method: 'delete'
  })
}