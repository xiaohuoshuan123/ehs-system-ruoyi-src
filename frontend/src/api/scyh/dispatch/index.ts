import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { DispatchForm, DispatchQuery, DispatchVO } from './types';

// 查询列表
export function listDispatch(query?: DispatchQuery): AxiosPromise<DispatchVO[]> {
  return request({
    url: '/mbgr/sign_dispatch/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getDispatch(id: string | number): AxiosPromise<DispatchVO> {
  return request({
    url: '/mbgr/sign_dispatch/' + id,
    method: 'get'
  })
}

// 新增
export function addDispatch(data: DispatchForm) {
  return request({
    url: '/mbgr/sign_dispatch',
    method: 'post',
    data: data
  })
}

// 修改
export function updateDispatch(data: DispatchForm) {
  return request({
    url: '/mbgr/sign_dispatch',
    method: 'put',
    data: data
  })
}

// 删除
export function delDispatch(ids: string | number | Array<string | number>) {
  return request({
    url: '/mbgr/sign_dispatch/' + ids,
    method: 'delete'
  })
}