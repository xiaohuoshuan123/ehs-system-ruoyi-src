import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { ZBCOMPANYForm, ZBCOMPANYQuery, ZBCOMPANYVO } from './types';

// 查询列表
export function listZBCOMPANY(query?: ZBCOMPANYQuery): AxiosPromise<ZBCOMPANYVO[]> {
  return request({
    url: '/zbfl/company_regulation/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getZBCOMPANY(id: string | number): AxiosPromise<ZBCOMPANYVO> {
  return request({
    url: '/zbfl/company_regulation/' + id,
    method: 'get'
  })
}

// 新增
export function addZBCOMPANY(data: ZBCOMPANYForm) {
  return request({
    url: '/zbfl/company_regulation',
    method: 'post',
    data: data
  })
}

// 修改
export function updateZBCOMPANY(data: ZBCOMPANYForm) {
  return request({
    url: '/zbfl/company_regulation',
    method: 'put',
    data: data
  })
}

// 删除
export function delZBCOMPANY(ids: string | number | Array<string | number>) {
  return request({
    url: '/zbfl/company_regulation/' + ids,
    method: 'delete'
  })
}