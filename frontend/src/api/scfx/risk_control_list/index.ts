import type { Risk_control_listForm, Risk_control_listQuery, Risk_control_listVO } from '@/api/scfx/risk_control_list/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询RiskControlList列表
 * @param query
 * @returns {*}
 */
export const listRisk_control_list = (query?: Risk_control_listQuery): AxiosPromise<PageResult<Risk_control_listVO>> => {
  return request({
    url: '/scfx/risk_control_list/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询RiskControlList详细
 * @param id
 */
export const getRisk_control_list = (id: string | number): AxiosPromise<Risk_control_listVO> => {
  return request({
    url: '/scfx/risk_control_list/' + id,
    method: 'get'
  });
};

/**
 * 新增RiskControlList
 * @param data
 */
export const addRisk_control_list = (data: Risk_control_listForm) => {
  return request({
    url: '/scfx/risk_control_list',
    method: 'post',
    data: data
  });
};

/**
 * 修改RiskControlList
 * @param data
 */
export const updateRisk_control_list = (data: Risk_control_listForm) => {
  return request({
    url: '/scfx/risk_control_list',
    method: 'put',
    data: data
  });
};



/**
 * 删除RiskControlList
 * @param id
 */
export const delRisk_control_list = (id: string | number | Array<string | number>) => {
  return request({
    url: '/scfx/risk_control_list/' + id,
    method: 'delete'
  });
};
