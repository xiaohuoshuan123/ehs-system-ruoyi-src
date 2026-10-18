import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { QuestionsForm, QuestionsQuery, QuestionsVO } from './types';

// 查询列表
export function listQuestions(query?: QuestionsQuery): AxiosPromise<QuestionsVO[]> {
  return request({
    url: '/pxks/exam_question/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getQuestions(id: string | number): AxiosPromise<QuestionsVO> {
  return request({
    url: '/pxks/exam_question/' + id,
    method: 'get'
  })
}

// 新增
export function addQuestions(data: QuestionsForm) {
  return request({
    url: '/pxks/exam_question',
    method: 'post',
    data: data
  })
}

// 修改
export function updateQuestions(data: QuestionsForm) {
  return request({
    url: '/pxks/exam_question',
    method: 'put',
    data: data
  })
}

// 删除
export function delQuestions(ids: string | number | Array<string | number>) {
  return request({
    url: '/pxks/exam_question/' + ids,
    method: 'delete'
  })
}