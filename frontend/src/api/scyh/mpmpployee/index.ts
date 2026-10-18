import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { MPMPPLOYEEForm, MPMPPLOYEEQuery, MPMPPLOYEEVO } from './types';

// 查询列表
export function listMPMPPLOYEE(query?: MPMPPLOYEEQuery): AxiosPromise<MPMPPLOYEEVO[]> {
  return request({
    url: '/system/user/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getMPMPPLOYEE(id: string | number): AxiosPromise<MPMPPLOYEEVO> {
  return request({
    url: '/system/user/' + id,
    method: 'get'
  })
}

// 新增
export function addMPMPPLOYEE(data: MPMPPLOYEEForm) {
  return request({
    url: '/system/user',
    method: 'post',
    data: data
  })
}

// 修改
export function updateMPMPPLOYEE(data: MPMPPLOYEEForm) {
  return request({
    url: '/system/user',
    method: 'put',
    data: data
  })
}

// 删除
export function delMPMPPLOYEE(ids: string | number | Array<string | number>) {
  return request({
    url: '/system/user/' + ids,
    method: 'delete'
  })
}