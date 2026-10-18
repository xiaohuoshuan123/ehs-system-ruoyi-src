import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { JGSZORGForm, JGSZORGQuery, JGSZORGVO } from './types';

// 查询列表
export function listJGSZORG(query?: JGSZORGQuery): AxiosPromise<JGSZORGVO[]> {
  return request({
    url: '/jgaw/architecture/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getJGSZORG(id: string | number): AxiosPromise<JGSZORGVO> {
  return request({
    url: '/jgaw/architecture/' + id,
    method: 'get'
  })
}

// 新增
export function addJGSZORG(data: JGSZORGForm) {
  return request({
    url: '/jgaw/architecture',
    method: 'post',
    data: data
  })
}

// 修改
export function updateJGSZORG(data: JGSZORGForm) {
  return request({
    url: '/jgaw/architecture',
    method: 'put',
    data: data
  })
}

// 删除
export function delJGSZORG(ids: string | number | Array<string | number>) {
  return request({
    url: '/jgaw/architecture/' + ids,
    method: 'delete'
  })
}