import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { MasterApprenticeForm, MasterApprenticeQuery, MasterApprenticeVO } from './types';

// 查询列表
export function listMasterApprentice(query?: MasterApprenticeQuery): AxiosPromise<MasterApprenticeVO[]> {
  return request({
    url: '/mp/master_apprentice/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getMasterApprentice(id: string | number): AxiosPromise<MasterApprenticeVO> {
  return request({
    url: '/mp/master_apprentice/' + id,
    method: 'get'
  })
}

// 新增
export function addMasterApprentice(data: MasterApprenticeForm) {
  return request({
    url: '/mp/master_apprentice',
    method: 'post',
    data: data
  })
}

// 修改
export function updateMasterApprentice(data: MasterApprenticeForm) {
  return request({
    url: '/mp/master_apprentice',
    method: 'put',
    data: data
  })
}

// 删除
export function delMasterApprentice(ids: string | number | Array<string | number>) {
  return request({
    url: '/mp/master_apprentice/' + ids,
    method: 'delete'
  })
}