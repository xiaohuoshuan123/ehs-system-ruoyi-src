import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { ZBUPLAWSForm, ZBUPLAWSQuery, ZBUPLAWSVO } from './types';

// 查询列表
export function listZBUPLAWS(query?: ZBUPLAWSQuery): AxiosPromise<ZBUPLAWSVO[]> {
  return request({
    url: '/zbfl/laws/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getZBUPLAWS(id: string | number): AxiosPromise<ZBUPLAWSVO> {
  return request({
    url: '/zbfl/laws/' + id,
    method: 'get'
  })
}

// 新增
export function addZBUPLAWS(data: ZBUPLAWSForm) {
  return request({
    url: '/zbfl/laws',
    method: 'post',
    data: data
  })
}

// 修改
export function updateZBUPLAWS(data: ZBUPLAWSForm) {
  return request({
    url: '/zbfl/laws',
    method: 'put',
    data: data
  })
}

// 删除
export function delZBUPLAWS(ids: string | number | Array<string | number>) {
  return request({
    url: '/zbfl/laws/' + ids,
    method: 'delete'
  })
}