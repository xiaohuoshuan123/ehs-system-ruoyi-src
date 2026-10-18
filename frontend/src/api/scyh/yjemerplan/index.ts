import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { YJEMERPLANForm, YJEMERPLANQuery, YJEMERPLANVO } from './types';

// 查询列表
export function listYJEMERPLAN(query?: YJEMERPLANQuery): AxiosPromise<YJEMERPLANVO[]> {
  return request({
    url: '/yjep/emergency_plan/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getYJEMERPLAN(id: string | number): AxiosPromise<YJEMERPLANVO> {
  return request({
    url: '/yjep/emergency_plan/' + id,
    method: 'get'
  })
}

// 新增
export function addYJEMERPLAN(data: YJEMERPLANForm) {
  return request({
    url: '/yjep/emergency_plan',
    method: 'post',
    data: data
  })
}

// 修改
export function updateYJEMERPLAN(data: YJEMERPLANForm) {
  return request({
    url: '/yjep/emergency_plan',
    method: 'put',
    data: data
  })
}

// 删除
export function delYJEMERPLAN(ids: string | number | Array<string | number>) {
  return request({
    url: '/yjep/emergency_plan/' + ids,
    method: 'delete'
  })
}