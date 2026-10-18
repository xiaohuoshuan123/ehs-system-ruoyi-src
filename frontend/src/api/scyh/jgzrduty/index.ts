import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { JGZRDUTYForm, JGZRDUTYQuery, JGZRDUTYVO } from './types';

// 查询列表
export function listJGZRDUTY(query?: JGZRDUTYQuery): AxiosPromise<JGZRDUTYVO[]> {
  return request({
    url: '/jgzr/safety_duty/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getJGZRDUTY(id: string | number): AxiosPromise<JGZRDUTYVO> {
  return request({
    url: '/jgzr/safety_duty/' + id,
    method: 'get'
  })
}

// 新增
export function addJGZRDUTY(data: JGZRDUTYForm) {
  return request({
    url: '/jgzr/safety_duty',
    method: 'post',
    data: data
  })
}

// 修改
export function updateJGZRDUTY(data: JGZRDUTYForm) {
  return request({
    url: '/jgzr/safety_duty',
    method: 'put',
    data: data
  })
}

// 删除
export function delJGZRDUTY(ids: string | number | Array<string | number>) {
  return request({
    url: '/jgzr/safety_duty/' + ids,
    method: 'delete'
  })
}