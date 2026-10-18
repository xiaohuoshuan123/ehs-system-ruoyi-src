import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { SignForm, SignQuery, SignVO } from './types';

// 查询列表
export function listSign(query?: SignQuery): AxiosPromise<SignVO[]> {
  return request({
    url: '/mbgr/sign_template/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getSign(id: string | number): AxiosPromise<SignVO> {
  return request({
    url: '/mbgr/sign_template/' + id,
    method: 'get'
  })
}

// 新增
export function addSign(data: SignForm) {
  return request({
    url: '/mbgr/sign_template',
    method: 'post',
    data: data
  })
}

// 修改
export function updateSign(data: SignForm) {
  return request({
    url: '/mbgr/sign_template',
    method: 'put',
    data: data
  })
}

// 删除
export function delSign(ids: string | number | Array<string | number>) {
  return request({
    url: '/mbgr/sign_template/' + ids,
    method: 'delete'
  })
}