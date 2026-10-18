import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { MenuForm, MenuQuery, MenuVO } from './types';

// 查询列表
export function listMenu(query?: MenuQuery): AxiosPromise<MenuVO[]> {
  return request({
    url: '/yfdh/emergency_point/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getMenu(id: string | number): AxiosPromise<MenuVO> {
  return request({
    url: '/yfdh/emergency_point/' + id,
    method: 'get'
  })
}

// 新增
export function addMenu(data: MenuForm) {
  return request({
    url: '/yfdh/emergency_point',
    method: 'post',
    data: data
  })
}

// 修改
export function updateMenu(data: MenuForm) {
  return request({
    url: '/yfdh/emergency_point',
    method: 'put',
    data: data
  })
}

// 删除
export function delMenu(ids: string | number | Array<string | number>) {
  return request({
    url: '/yfdh/emergency_point/' + ids,
    method: 'delete'
  })
}