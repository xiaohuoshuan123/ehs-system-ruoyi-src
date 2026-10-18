import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { JGAWHYForm, JGAWHYQuery, JGAWHYVO } from './types';

// 查询列表
export function listJGAWHY(query?: JGAWHYQuery): AxiosPromise<JGAWHYVO[]> {
  return request({
    url: '/jgaw/safety_meeting/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getJGAWHY(id: string | number): AxiosPromise<JGAWHYVO> {
  return request({
    url: '/jgaw/safety_meeting/' + id,
    method: 'get'
  })
}

// 新增
export function addJGAWHY(data: JGAWHYForm) {
  return request({
    url: '/jgaw/safety_meeting',
    method: 'post',
    data: data
  })
}

// 修改
export function updateJGAWHY(data: JGAWHYForm) {
  return request({
    url: '/jgaw/safety_meeting',
    method: 'put',
    data: data
  })
}

// 删除
export function delJGAWHY(ids: string | number | Array<string | number>) {
  return request({
    url: '/jgaw/safety_meeting/' + ids,
    method: 'delete'
  })
}