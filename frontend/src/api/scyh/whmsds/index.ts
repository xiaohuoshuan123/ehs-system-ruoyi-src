import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { WHMSDSForm, WHMSDSQuery, WHMSDSVO } from './types';

// 查询列表
export function listWHMSDS(query?: WHMSDSQuery): AxiosPromise<WHMSDSVO[]> {
  return request({
    url: '/whml/msds/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getWHMSDS(id: string | number): AxiosPromise<WHMSDSVO> {
  return request({
    url: '/whml/msds/' + id,
    method: 'get'
  })
}

// 新增
export function addWHMSDS(data: WHMSDSForm) {
  return request({
    url: '/whml/msds',
    method: 'post',
    data: data
  })
}

// 修改
export function updateWHMSDS(data: WHMSDSForm) {
  return request({
    url: '/whml/msds',
    method: 'put',
    data: data
  })
}

// 删除
export function delWHMSDS(ids: string | number | Array<string | number>) {
  return request({
    url: '/whml/msds/' + ids,
    method: 'delete'
  })
}