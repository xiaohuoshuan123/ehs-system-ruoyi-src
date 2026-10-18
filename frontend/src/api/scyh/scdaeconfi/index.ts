import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { SCDAECONFIForm, SCDAECONFIQuery, SCDAECONFIVO } from './types';

// 查询列表
export function listSCDAECONFI(query?: SCDAECONFIQuery): AxiosPromise<SCDAECONFIVO[]> {
  return request({
    url: '/yfxc/position_configure/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getSCDAECONFI(id: string | number): AxiosPromise<SCDAECONFIVO> {
  return request({
    url: '/yfxc/position_configure/' + id,
    method: 'get'
  })
}

// 新增
export function addSCDAECONFI(data: SCDAECONFIForm) {
  return request({
    url: '/yfxc/position_configure',
    method: 'post',
    data: data
  })
}

// 修改
export function updateSCDAECONFI(data: SCDAECONFIForm) {
  return request({
    url: '/yfxc/position_configure',
    method: 'put',
    data: data
  })
}

// 删除
export function delSCDAECONFI(ids: string | number | Array<string | number>) {
  return request({
    url: '/yfxc/position_configure/' + ids,
    method: 'delete'
  })
}