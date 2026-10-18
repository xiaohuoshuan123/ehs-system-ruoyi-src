import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { SCRIOLLISTForm, SCRIOLLISTQuery, SCRIOLLISTVO } from './types';

// 查询列表
export function listSCRIOLLIST(query?: SCRIOLLISTQuery): AxiosPromise<SCRIOLLISTVO[]> {
  return request({
    url: '/scfx/risk_control_list/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getSCRIOLLIST(id: string | number): AxiosPromise<SCRIOLLISTVO> {
  return request({
    url: '/scfx/risk_control_list/' + id,
    method: 'get'
  })
}

// 新增
export function addSCRIOLLIST(data: SCRIOLLISTForm) {
  return request({
    url: '/scfx/risk_control_list',
    method: 'post',
    data: data
  })
}

// 修改
export function updateSCRIOLLIST(data: SCRIOLLISTForm) {
  return request({
    url: '/scfx/risk_control_list',
    method: 'put',
    data: data
  })
}

// 删除
export function delSCRIOLLIST(ids: string | number | Array<string | number>) {
  return request({
    url: '/scfx/risk_control_list/' + ids,
    method: 'delete'
  })
}