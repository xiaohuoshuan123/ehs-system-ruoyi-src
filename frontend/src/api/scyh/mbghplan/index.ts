import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { MBGHPLANForm, MBGHPLANQuery, MBGHPLANVO } from './types';

// 查询列表
export function listMBGHPLAN(query?: MBGHPLANQuery): AxiosPromise<MBGHPLANVO[]> {
  return request({
    url: '/mbgh/plan/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getMBGHPLAN(id: string | number): AxiosPromise<MBGHPLANVO> {
  return request({
    url: '/mbgh/plan/' + id,
    method: 'get'
  })
}

// 新增
export function addMBGHPLAN(data: MBGHPLANForm) {
  return request({
    url: '/mbgh/plan',
    method: 'post',
    data: data
  })
}

// 修改
export function updateMBGHPLAN(data: MBGHPLANForm) {
  return request({
    url: '/mbgh/plan',
    method: 'put',
    data: data
  })
}

// 删除
export function delMBGHPLAN(ids: string | number | Array<string | number>) {
  return request({
    url: '/mbgh/plan/' + ids,
    method: 'delete'
  })
}