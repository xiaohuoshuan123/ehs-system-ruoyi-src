import type { Master_apprenticeForm, Master_apprenticeQuery, Master_apprenticeVO } from '@/api/mp/master_apprentice/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询MasterApprentice列表
 * @param query
 * @returns {*}
 */
export const listMaster_apprentice = (query?: Master_apprenticeQuery): AxiosPromise<PageResult<Master_apprenticeVO>> => {
  return request({
    url: '/mp/master_apprentice/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询MasterApprentice详细
 * @param id
 */
export const getMaster_apprentice = (id: string | number): AxiosPromise<Master_apprenticeVO> => {
  return request({
    url: '/mp/master_apprentice/' + id,
    method: 'get'
  });
};

/**
 * 新增MasterApprentice
 * @param data
 */
export const addMaster_apprentice = (data: Master_apprenticeForm) => {
  return request({
    url: '/mp/master_apprentice',
    method: 'post',
    data: data
  });
};

/**
 * 修改MasterApprentice
 * @param data
 */
export const updateMaster_apprentice = (data: Master_apprenticeForm) => {
  return request({
    url: '/mp/master_apprentice',
    method: 'put',
    data: data
  });
};



/**
 * 删除MasterApprentice
 * @param id
 */
export const delMaster_apprentice = (id: string | number | Array<string | number>) => {
  return request({
    url: '/mp/master_apprentice/' + id,
    method: 'delete'
  });
};
