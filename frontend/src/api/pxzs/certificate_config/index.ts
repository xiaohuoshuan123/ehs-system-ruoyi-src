import type { Certificate_configForm, Certificate_configQuery, Certificate_configVO } from '@/api/pxzs/certificate_config/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询CertificateConfig列表
 * @param query
 * @returns {*}
 */
export const listCertificate_config = (query?: Certificate_configQuery): AxiosPromise<PageResult<Certificate_configVO>> => {
  return request({
    url: '/pxzs/certificate_config/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询CertificateConfig详细
 * @param id
 */
export const getCertificate_config = (id: string | number): AxiosPromise<Certificate_configVO> => {
  return request({
    url: '/pxzs/certificate_config/' + id,
    method: 'get'
  });
};

/**
 * 新增CertificateConfig
 * @param data
 */
export const addCertificate_config = (data: Certificate_configForm) => {
  return request({
    url: '/pxzs/certificate_config',
    method: 'post',
    data: data
  });
};

/**
 * 修改CertificateConfig
 * @param data
 */
export const updateCertificate_config = (data: Certificate_configForm) => {
  return request({
    url: '/pxzs/certificate_config',
    method: 'put',
    data: data
  });
};



/**
 * 删除CertificateConfig
 * @param id
 */
export const delCertificate_config = (id: string | number | Array<string | number>) => {
  return request({
    url: '/pxzs/certificate_config/' + id,
    method: 'delete'
  });
};
