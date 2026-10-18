import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { YJYESSPLANForm, YJYESSPLANQuery, YJYESSPLANVO } from './types';

// 查询列表
export function listYJYESSPLAN(query?: YJYESSPLANQuery): AxiosPromise<YJYESSPLANVO[]> {
  return request({
    url: '/yjep/emergency_drill/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getYJYESSPLAN(id: string | number): AxiosPromise<YJYESSPLANVO> {
  return request({
    url: '/yjep/emergency_drill/' + id,
    method: 'get'
  })
}

// 新增
export function addYJYESSPLAN(data: YJYESSPLANForm) {
  return request({
    url: '/yjep/emergency_drill',
    method: 'post',
    data: data
  })
}

// 修改
export function updateYJYESSPLAN(data: YJYESSPLANForm) {
  return request({
    url: '/yjep/emergency_drill',
    method: 'put',
    data: data
  })
}

// 删除
export function delYJYESSPLAN(ids: string | number | Array<string | number>) {
  return request({
    url: '/yjep/emergency_drill/' + ids,
    method: 'delete'
  })
}