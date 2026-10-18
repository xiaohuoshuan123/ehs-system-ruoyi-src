import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { SGEMSSMENTForm, SGEMSSMENTQuery, SGEMSSMENTVO } from './types';

// 查询列表
export function listSGEMSSMENT(query?: SGEMSSMENTQuery): AxiosPromise<SGEMSSMENTVO[]> {
  return request({
    url: '/scfx/accident/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getSGEMSSMENT(id: string | number): AxiosPromise<SGEMSSMENTVO> {
  return request({
    url: '/scfx/accident/' + id,
    method: 'get'
  })
}

// 新增
export function addSGEMSSMENT(data: SGEMSSMENTForm) {
  return request({
    url: '/scfx/accident',
    method: 'post',
    data: data
  })
}

// 修改
export function updateSGEMSSMENT(data: SGEMSSMENTForm) {
  return request({
    url: '/scfx/accident',
    method: 'put',
    data: data
  })
}

// 删除
export function delSGEMSSMENT(ids: string | number | Array<string | number>) {
  return request({
    url: '/scfx/accident/' + ids,
    method: 'delete'
  })
}