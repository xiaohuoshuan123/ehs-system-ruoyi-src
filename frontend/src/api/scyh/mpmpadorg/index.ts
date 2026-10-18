import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { MPMPADORGForm, MPMPADORGQuery, MPMPADORGVO } from './types';

// 查询列表
export function listMPMPADORG(query?: MPMPADORGQuery): AxiosPromise<MPMPADORGVO[]> {
  return request({
    url: '/lzbz/standard_dept/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getMPMPADORG(id: string | number): AxiosPromise<MPMPADORGVO> {
  return request({
    url: '/lzbz/standard_dept/' + id,
    method: 'get'
  })
}

// 新增
export function addMPMPADORG(data: MPMPADORGForm) {
  return request({
    url: '/lzbz/standard_dept',
    method: 'post',
    data: data
  })
}

// 修改
export function updateMPMPADORG(data: MPMPADORGForm) {
  return request({
    url: '/lzbz/standard_dept',
    method: 'put',
    data: data
  })
}

// 删除
export function delMPMPADORG(ids: string | number | Array<string | number>) {
  return request({
    url: '/lzbz/standard_dept/' + ids,
    method: 'delete'
  })
}