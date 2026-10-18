import type { Company_regulationForm, Company_regulationQuery, Company_regulationVO } from '@/api/zbfl/company_regulation/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询CompanyRegulation列表
 * @param query
 * @returns {*}
 */
export const listCompany_regulation = (query?: Company_regulationQuery): AxiosPromise<PageResult<Company_regulationVO>> => {
  return request({
    url: '/zbfl/company_regulation/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询CompanyRegulation详细
 * @param id
 */
export const getCompany_regulation = (id: string | number): AxiosPromise<Company_regulationVO> => {
  return request({
    url: '/zbfl/company_regulation/' + id,
    method: 'get'
  });
};

/**
 * 新增CompanyRegulation
 * @param data
 */
export const addCompany_regulation = (data: Company_regulationForm) => {
  return request({
    url: '/zbfl/company_regulation',
    method: 'post',
    data: data
  });
};

/**
 * 修改CompanyRegulation
 * @param data
 */
export const updateCompany_regulation = (data: Company_regulationForm) => {
  return request({
    url: '/zbfl/company_regulation',
    method: 'put',
    data: data
  });
};



/**
 * 删除CompanyRegulation
 * @param id
 */
export const delCompany_regulation = (id: string | number | Array<string | number>) => {
  return request({
    url: '/zbfl/company_regulation/' + id,
    method: 'delete'
  });
};
