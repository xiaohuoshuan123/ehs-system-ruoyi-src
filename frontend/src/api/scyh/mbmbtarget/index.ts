import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { MBMBTARGETForm, MBMBTARGETQuery, MBMBTARGETVO } from './types';

// 查询列表
export function listMBMBTARGET(query?: MBMBTARGETQuery): AxiosPromise<MBMBTARGETVO[]> {
  return request({
    url: '/mbmb/target/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getMBMBTARGET(id: string | number): AxiosPromise<MBMBTARGETVO> {
  return request({
    url: '/mbmb/target/' + id,
    method: 'get'
  })
}

// 新增
export function addMBMBTARGET(data: MBMBTARGETForm) {
  return request({
    url: '/mbmb/target',
    method: 'post',
    data: data
  })
}

// 修改
export function updateMBMBTARGET(data: MBMBTARGETForm) {
  return request({
    url: '/mbmb/target',
    method: 'put',
    data: data
  })
}

// 删除
export function delMBMBTARGET(ids: string | number | Array<string | number>) {
  return request({
    url: '/mbmb/target/' + ids,
    method: 'delete'
  })
}