import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { ExamForm, ExamQuery, ExamVO } from './types';

// 查询列表
export function listExam(query?: ExamQuery): AxiosPromise<ExamVO[]> {
  return request({
    url: '/pxks/exam_paper/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getExam(id: string | number): AxiosPromise<ExamVO> {
  return request({
    url: '/pxks/exam_paper/' + id,
    method: 'get'
  })
}

// 新增
export function addExam(data: ExamForm) {
  return request({
    url: '/pxks/exam_paper',
    method: 'post',
    data: data
  })
}

// 修改
export function updateExam(data: ExamForm) {
  return request({
    url: '/pxks/exam_paper',
    method: 'put',
    data: data
  })
}

// 删除
export function delExam(ids: string | number | Array<string | number>) {
  return request({
    url: '/pxks/exam_paper/' + ids,
    method: 'delete'
  })
}