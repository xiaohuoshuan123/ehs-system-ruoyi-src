import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { JGAWJGForm, JGAWJGQuery, JGAWJGVO } from './types';

// 查询列表
export function listJGAWJG(query?: JGAWJGQuery): AxiosPromise<JGAWJGVO[]> {
  return request({
    url: '/jgaw/architecture/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getJGAWJG(id: string | number): AxiosPromise<JGAWJGVO> {
  return request({
    url: '/jgaw/architecture/' + id,
    method: 'get'
  })
}

// 新增
export function addJGAWJG(data: JGAWJGForm) {
  return request({
    url: '/jgaw/architecture',
    method: 'post',
    data: data
  })
}

// 修改
export function updateJGAWJG(data: JGAWJGForm) {
  return request({
    url: '/jgaw/architecture',
    method: 'put',
    data: data
  })
}

// 删除
export function delJGAWJG(ids: string | number | Array<string | number>) {
  return request({
    url: '/jgaw/architecture/' + ids,
    method: 'delete'
  })
}