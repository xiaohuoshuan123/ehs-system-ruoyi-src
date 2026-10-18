import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { WZJLBREAForm, WZJLBREAQuery, WZJLBREAVO } from './types';

// 查询列表
export function listWZJLBREA(query?: WZJLBREAQuery): AxiosPromise<WZJLBREAVO[]> {
  return request({
    url: '/wzjl/breach_record/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getWZJLBREA(id: string | number): AxiosPromise<WZJLBREAVO> {
  return request({
    url: '/wzjl/breach_record/' + id,
    method: 'get'
  })
}

// 新增
export function addWZJLBREA(data: WZJLBREAForm) {
  return request({
    url: '/wzjl/breach_record',
    method: 'post',
    data: data
  })
}

// 修改
export function updateWZJLBREA(data: WZJLBREAForm) {
  return request({
    url: '/wzjl/breach_record',
    method: 'put',
    data: data
  })
}

// 删除
export function delWZJLBREA(ids: string | number | Array<string | number>) {
  return request({
    url: '/wzjl/breach_record/' + ids,
    method: 'delete'
  })
}