import type { Position_configureForm, Position_configureQuery, Position_configureVO } from '@/api/yfxc/position_configure/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询PositionConfigure列表
 * @param query
 * @returns {*}
 */
export const listPosition_configure = (query?: Position_configureQuery): AxiosPromise<PageResult<Position_configureVO>> => {
  return request({
    url: '/yfxc/position_configure/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询PositionConfigure详细
 * @param id
 */
export const getPosition_configure = (id: string | number): AxiosPromise<Position_configureVO> => {
  return request({
    url: '/yfxc/position_configure/' + id,
    method: 'get'
  });
};

/**
 * 新增PositionConfigure
 * @param data
 */
export const addPosition_configure = (data: Position_configureForm) => {
  return request({
    url: '/yfxc/position_configure',
    method: 'post',
    data: data
  });
};

/**
 * 修改PositionConfigure
 * @param data
 */
export const updatePosition_configure = (data: Position_configureForm) => {
  return request({
    url: '/yfxc/position_configure',
    method: 'put',
    data: data
  });
};



/**
 * 删除PositionConfigure
 * @param id
 */
export const delPosition_configure = (id: string | number | Array<string | number>) => {
  return request({
    url: '/yfxc/position_configure/' + id,
    method: 'delete'
  });
};
