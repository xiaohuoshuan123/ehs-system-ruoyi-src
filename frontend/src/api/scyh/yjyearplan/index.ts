import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { YJYEARPLANForm, YJYEARPLANQuery, YJYEARPLANVO } from './types';

// 查询列表
export function listYJYEARPLAN(query?: YJYEARPLANQuery): AxiosPromise<YJYEARPLANVO[]> {
  return request({
    url: '/yjep/emergency_drill/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getYJYEARPLAN(id: string | number): AxiosPromise<YJYEARPLANVO> {
  return request({
    url: '/yjep/emergency_drill/' + id,
    method: 'get'
  })
}

// 新增
export function addYJYEARPLAN(data: YJYEARPLANForm) {
  return request({
    url: '/yjep/emergency_drill',
    method: 'post',
    data: data
  })
}

// 修改
export function updateYJYEARPLAN(data: YJYEARPLANForm) {
  return request({
    url: '/yjep/emergency_drill',
    method: 'put',
    data: data
  })
}

// 删除
export function delYJYEARPLAN(ids: string | number | Array<string | number>) {
  return request({
    url: '/yjep/emergency_drill/' + ids,
    method: 'delete'
  })
}