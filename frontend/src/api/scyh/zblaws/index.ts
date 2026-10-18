import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { ZBLAWSForm, ZBLAWSQuery, ZBLAWSVO } from './types';

// 查询列表
export function listZBLAWS(query?: ZBLAWSQuery): AxiosPromise<ZBLAWSVO[]> {
  return request({
    url: '/zbfl/laws/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getZBLAWS(id: string | number): AxiosPromise<ZBLAWSVO> {
  return request({
    url: '/zbfl/laws/' + id,
    method: 'get'
  })
}

// 新增
export function addZBLAWS(data: ZBLAWSForm) {
  return request({
    url: '/zbfl/laws',
    method: 'post',
    data: data
  })
}

// 修改
export function updateZBLAWS(data: ZBLAWSForm) {
  return request({
    url: '/zbfl/laws',
    method: 'put',
    data: data
  })
}

// 删除
export function delZBLAWS(ids: string | number | Array<string | number>) {
  return request({
    url: '/zbfl/laws/' + ids,
    method: 'delete'
  })
}