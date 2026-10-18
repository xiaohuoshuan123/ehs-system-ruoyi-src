import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { YJEMERTEAMForm, YJEMERTEAMQuery, YJEMERTEAMVO } from './types';

// 查询列表
export function listYJEMERTEAM(query?: YJEMERTEAMQuery): AxiosPromise<YJEMERTEAMVO[]> {
  return request({
    url: '/yjtm/emergency_team/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getYJEMERTEAM(id: string | number): AxiosPromise<YJEMERTEAMVO> {
  return request({
    url: '/yjtm/emergency_team/' + id,
    method: 'get'
  })
}

// 新增
export function addYJEMERTEAM(data: YJEMERTEAMForm) {
  return request({
    url: '/yjtm/emergency_team',
    method: 'post',
    data: data
  })
}

// 修改
export function updateYJEMERTEAM(data: YJEMERTEAMForm) {
  return request({
    url: '/yjtm/emergency_team',
    method: 'put',
    data: data
  })
}

// 删除
export function delYJEMERTEAM(ids: string | number | Array<string | number>) {
  return request({
    url: '/yjtm/emergency_team/' + ids,
    method: 'delete'
  })
}