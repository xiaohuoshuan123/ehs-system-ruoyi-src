import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { EnterprisePersonForm, EnterprisePersonQuery, EnterprisePersonVO } from './types';

// 查询列表
export function listEnterprisePerson(query?: EnterprisePersonQuery): AxiosPromise<EnterprisePersonVO[]> {
  return request({
    url: '/pxzs/enterprise_person/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getEnterprisePerson(id: string | number): AxiosPromise<EnterprisePersonVO> {
  return request({
    url: '/pxzs/enterprise_person/' + id,
    method: 'get'
  })
}

// 新增
export function addEnterprisePerson(data: EnterprisePersonForm) {
  return request({
    url: '/pxzs/enterprise_person',
    method: 'post',
    data: data
  })
}

// 修改
export function updateEnterprisePerson(data: EnterprisePersonForm) {
  return request({
    url: '/pxzs/enterprise_person',
    method: 'put',
    data: data
  })
}

// 删除
export function delEnterprisePerson(ids: string | number | Array<string | number>) {
  return request({
    url: '/pxzs/enterprise_person/' + ids,
    method: 'delete'
  })
}