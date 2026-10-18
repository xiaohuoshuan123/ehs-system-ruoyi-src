import type { Key_positionForm, Key_positionQuery, Key_positionVO } from '@/api/yfbw/key_position/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询KeyPosition列表
 * @param query
 * @returns {*}
 */
export const listKey_position = (query?: Key_positionQuery): AxiosPromise<PageResult<Key_positionVO>> => {
  return request({
    url: '/yfbw/key_position/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询KeyPosition详细
 * @param id
 */
export const getKey_position = (id: string | number): AxiosPromise<Key_positionVO> => {
  return request({
    url: '/yfbw/key_position/' + id,
    method: 'get'
  });
};

/**
 * 新增KeyPosition
 * @param data
 */
export const addKey_position = (data: Key_positionForm) => {
  return request({
    url: '/yfbw/key_position',
    method: 'post',
    data: data
  });
};

/**
 * 修改KeyPosition
 * @param data
 */
export const updateKey_position = (data: Key_positionForm) => {
  return request({
    url: '/yfbw/key_position',
    method: 'put',
    data: data
  });
};



/**
 * 删除KeyPosition
 * @param id
 */
export const delKey_position = (id: string | number | Array<string | number>) => {
  return request({
    url: '/yfbw/key_position/' + id,
    method: 'delete'
  });
};
