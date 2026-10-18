import type { Compliance_evaluationForm, Compliance_evaluationQuery, Compliance_evaluationVO } from '@/api/lzbz/compliance_evaluation/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询ComplianceEvaluation列表
 * @param query
 * @returns {*}
 */
export const listCompliance_evaluation = (query?: Compliance_evaluationQuery): AxiosPromise<PageResult<Compliance_evaluationVO>> => {
  return request({
    url: '/lzbz/compliance_evaluation/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询ComplianceEvaluation详细
 * @param id
 */
export const getCompliance_evaluation = (id: string | number): AxiosPromise<Compliance_evaluationVO> => {
  return request({
    url: '/lzbz/compliance_evaluation/' + id,
    method: 'get'
  });
};

/**
 * 新增ComplianceEvaluation
 * @param data
 */
export const addCompliance_evaluation = (data: Compliance_evaluationForm) => {
  return request({
    url: '/lzbz/compliance_evaluation',
    method: 'post',
    data: data
  });
};

/**
 * 修改ComplianceEvaluation
 * @param data
 */
export const updateCompliance_evaluation = (data: Compliance_evaluationForm) => {
  return request({
    url: '/lzbz/compliance_evaluation',
    method: 'put',
    data: data
  });
};



/**
 * 删除ComplianceEvaluation
 * @param id
 */
export const delCompliance_evaluation = (id: string | number | Array<string | number>) => {
  return request({
    url: '/lzbz/compliance_evaluation/' + id,
    method: 'delete'
  });
};
