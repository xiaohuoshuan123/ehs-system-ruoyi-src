import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { YJEMTERIALForm, YJEMTERIALQuery, YJEMTERIALVO } from './types';

// 查询列表
export function listYJEMTERIAL(query?: YJEMTERIALQuery): AxiosPromise<YJEMTERIALVO[]> {
  return request({
    url: '/yjwz/emergency_material/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getYJEMTERIAL(id: string | number): AxiosPromise<YJEMTERIALVO> {
  return request({
    url: '/yjwz/emergency_material/' + id,
    method: 'get'
  })
}

// 新增
export function addYJEMTERIAL(data: YJEMTERIALForm) {
  return request({
    url: '/yjwz/emergency_material',
    method: 'post',
    data: data
  })
}

// 修改
export function updateYJEMTERIAL(data: YJEMTERIALForm) {
  return request({
    url: '/yjwz/emergency_material',
    method: 'put',
    data: data
  })
}

// 删除
export function delYJEMTERIAL(ids: string | number | Array<string | number>) {
  return request({
    url: '/yjwz/emergency_material/' + ids,
    method: 'delete'
  })
}