import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { PXCECONFIGForm, PXCECONFIGQuery, PXCECONFIGVO } from './types';

// 查询列表
export function listPXCECONFIG(query?: PXCECONFIGQuery): AxiosPromise<PXCECONFIGVO[]> {
  return request({
    url: '/pxzs/certificate_config/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getPXCECONFIG(id: string | number): AxiosPromise<PXCECONFIGVO> {
  return request({
    url: '/pxzs/certificate_config/' + id,
    method: 'get'
  })
}

// 新增
export function addPXCECONFIG(data: PXCECONFIGForm) {
  return request({
    url: '/pxzs/certificate_config',
    method: 'post',
    data: data
  })
}

// 修改
export function updatePXCECONFIG(data: PXCECONFIGForm) {
  return request({
    url: '/pxzs/certificate_config',
    method: 'put',
    data: data
  })
}

// 删除
export function delPXCECONFIG(ids: string | number | Array<string | number>) {
  return request({
    url: '/pxzs/certificate_config/' + ids,
    method: 'delete'
  })
}