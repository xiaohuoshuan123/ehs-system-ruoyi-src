import type { Exam_questionForm, Exam_questionQuery, Exam_questionVO } from '@/api/pxks/exam_question/types';
import type { PageResult } from '@/api/types';
import type { AxiosPromise } from '@/utils/api-types';
import request from '@/utils/request';

/**
 * 查询ExamQuestion列表
 * @param query
 * @returns {*}
 */
export const listExam_question = (query?: Exam_questionQuery): AxiosPromise<PageResult<Exam_questionVO>> => {
  return request({
    url: '/pxks/exam_question/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询ExamQuestion详细
 * @param id
 */
export const getExam_question = (id: string | number): AxiosPromise<Exam_questionVO> => {
  return request({
    url: '/pxks/exam_question/' + id,
    method: 'get'
  });
};

/**
 * 新增ExamQuestion
 * @param data
 */
export const addExam_question = (data: Exam_questionForm) => {
  return request({
    url: '/pxks/exam_question',
    method: 'post',
    data: data
  });
};

/**
 * 修改ExamQuestion
 * @param data
 */
export const updateExam_question = (data: Exam_questionForm) => {
  return request({
    url: '/pxks/exam_question',
    method: 'put',
    data: data
  });
};



/**
 * 删除ExamQuestion
 * @param id
 */
export const delExam_question = (id: string | number | Array<string | number>) => {
  return request({
    url: '/pxks/exam_question/' + id,
    method: 'delete'
  });
};
