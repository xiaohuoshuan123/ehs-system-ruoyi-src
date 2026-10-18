import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { WHHAZARDOForm, WHHAZARDOQuery, WHHAZARDOVO } from './types';

// 查询列表
export function listWHHAZARDO(query?: WHHAZARDOQuery): AxiosPromise<WHHAZARDOVO[]> {
  return request({
    url: '/whml/hazardous_material/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getWHHAZARDO(id: string | number): AxiosPromise<WHHAZARDOVO> {
  return request({
    url: '/whml/hazardous_material/' + id,
    method: 'get'
  })
}

// 新增
export function addWHHAZARDO(data: WHHAZARDOForm) {
  return request({
    url: '/whml/hazardous_material',
    method: 'post',
    data: data
  })
}

// 修改
export function updateWHHAZARDO(data: WHHAZARDOForm) {
  return request({
    url: '/whml/hazardous_material',
    method: 'put',
    data: data
  })
}

// 删除
export function delWHHAZARDO(ids: string | number | Array<string | number>) {
  return request({
    url: '/whml/hazardous_material/' + ids,
    method: 'delete'
  })
}