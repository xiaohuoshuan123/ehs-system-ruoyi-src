import type { Exam_paperForm, Exam_paperQuery, Exam_paperVO } from '@/api/pxks/exam_paper/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询ExamPaper列表
 * @param query
 * @returns {*}
 */
export const listExam_paper = (query?: Exam_paperQuery): AxiosPromise<PageResult<Exam_paperVO>> => {
  return request({
    url: '/pxks/exam_paper/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询ExamPaper详细
 * @param id
 */
export const getExam_paper = (id: string | number): AxiosPromise<Exam_paperVO> => {
  return request({
    url: '/pxks/exam_paper/' + id,
    method: 'get'
  });
};

/**
 * 新增ExamPaper
 * @param data
 */
export const addExam_paper = (data: Exam_paperForm) => {
  return request({
    url: '/pxks/exam_paper',
    method: 'post',
    data: data
  });
};

/**
 * 修改ExamPaper
 * @param data
 */
export const updateExam_paper = (data: Exam_paperForm) => {
  return request({
    url: '/pxks/exam_paper',
    method: 'put',
    data: data
  });
};



/**
 * 删除ExamPaper
 * @param id
 */
export const delExam_paper = (id: string | number | Array<string | number>) => {
  return request({
    url: '/pxks/exam_paper/' + id,
    method: 'delete'
  });
};
