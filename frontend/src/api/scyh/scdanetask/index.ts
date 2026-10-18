import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { SCDANETASKForm, SCDANETASKQuery, SCDANETASKVO } from './types';

// 查询列表
export function listSCDANETASK(query?: SCDANETASKQuery): AxiosPromise<SCDANETASKVO[]> {
  return request({
    url: '/yfxc/patrol_task/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getSCDANETASK(id: string | number): AxiosPromise<SCDANETASKVO> {
  return request({
    url: '/yfxc/patrol_task/' + id,
    method: 'get'
  })
}

// 新增
export function addSCDANETASK(data: SCDANETASKForm) {
  return request({
    url: '/yfxc/patrol_task',
    method: 'post',
    data: data
  })
}

// 修改
export function updateSCDANETASK(data: SCDANETASKForm) {
  return request({
    url: '/yfxc/patrol_task',
    method: 'put',
    data: data
  })
}

// 删除
export function delSCDANETASK(ids: string | number | Array<string | number>) {
  return request({
    url: '/yfxc/patrol_task/' + ids,
    method: 'delete'
  })
}