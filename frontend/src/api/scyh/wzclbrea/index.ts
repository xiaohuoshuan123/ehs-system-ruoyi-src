import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { WZCLBREAForm, WZCLBREAQuery, WZCLBREAVO } from './types';

// 查询列表
export function listWZCLBREA(query?: WZCLBREAQuery): AxiosPromise<WZCLBREAVO[]> {
  return request({
    url: '/wzjl/breach_record/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getWZCLBREA(id: string | number): AxiosPromise<WZCLBREAVO> {
  return request({
    url: '/wzjl/breach_record/' + id,
    method: 'get'
  })
}

// 新增
export function addWZCLBREA(data: WZCLBREAForm) {
  return request({
    url: '/wzjl/breach_record',
    method: 'post',
    data: data
  })
}

// 修改
export function updateWZCLBREA(data: WZCLBREAForm) {
  return request({
    url: '/wzjl/breach_record',
    method: 'put',
    data: data
  })
}

// 删除
export function delWZCLBREA(ids: string | number | Array<string | number>) {
  return request({
    url: '/wzjl/breach_record/' + ids,
    method: 'delete'
  })
}