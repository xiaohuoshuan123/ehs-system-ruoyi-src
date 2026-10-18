import type { Standard_deptForm, Standard_deptQuery, Standard_deptVO } from '@/api/lzbz/standard_dept/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询StandardDept列表
 * @param query
 * @returns {*}
 */
export const listStandard_dept = (query?: Standard_deptQuery): AxiosPromise<PageResult<Standard_deptVO>> => {
  return request({
    url: '/lzbz/standard_dept/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询StandardDept详细
 * @param id
 */
export const getStandard_dept = (id: string | number): AxiosPromise<Standard_deptVO> => {
  return request({
    url: '/lzbz/standard_dept/' + id,
    method: 'get'
  });
};

/**
 * 新增StandardDept
 * @param data
 */
export const addStandard_dept = (data: Standard_deptForm) => {
  return request({
    url: '/lzbz/standard_dept',
    method: 'post',
    data: data
  });
};

/**
 * 修改StandardDept
 * @param data
 */
export const updateStandard_dept = (data: Standard_deptForm) => {
  return request({
    url: '/lzbz/standard_dept',
    method: 'put',
    data: data
  });
};



/**
 * 删除StandardDept
 * @param id
 */
export const delStandard_dept = (id: string | number | Array<string | number>) => {
  return request({
    url: '/lzbz/standard_dept/' + id,
    method: 'delete'
  });
};
