import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { ZBASSESSForm, ZBASSESSQuery, ZBASSESSVO } from './types';

// 查询列表
export function listZBASSESS(query?: ZBASSESSQuery): AxiosPromise<ZBASSESSVO[]> {
  return request({
    url: '/lzbz/compliance_evaluation/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getZBASSESS(id: string | number): AxiosPromise<ZBASSESSVO> {
  return request({
    url: '/lzbz/compliance_evaluation/' + id,
    method: 'get'
  })
}

// 新增
export function addZBASSESS(data: ZBASSESSForm) {
  return request({
    url: '/lzbz/compliance_evaluation',
    method: 'post',
    data: data
  })
}

// 修改
export function updateZBASSESS(data: ZBASSESSForm) {
  return request({
    url: '/lzbz/compliance_evaluation',
    method: 'put',
    data: data
  })
}

// 删除
export function delZBASSESS(ids: string | number | Array<string | number>) {
  return request({
    url: '/lzbz/compliance_evaluation/' + ids,
    method: 'delete'
  })
}