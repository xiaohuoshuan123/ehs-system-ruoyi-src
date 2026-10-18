import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { TestPaperTemplateMstForm, TestPaperTemplateMstQuery, TestPaperTemplateMstVO } from './types';

// 查询列表
export function listTestPaperTemplateMst(query?: TestPaperTemplateMstQuery): AxiosPromise<TestPaperTemplateMstVO[]> {
  return request({
    url: '/pxks/exam_paper/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getTestPaperTemplateMst(id: string | number): AxiosPromise<TestPaperTemplateMstVO> {
  return request({
    url: '/pxks/exam_paper/' + id,
    method: 'get'
  })
}

// 新增
export function addTestPaperTemplateMst(data: TestPaperTemplateMstForm) {
  return request({
    url: '/pxks/exam_paper',
    method: 'post',
    data: data
  })
}

// 修改
export function updateTestPaperTemplateMst(data: TestPaperTemplateMstForm) {
  return request({
    url: '/pxks/exam_paper',
    method: 'put',
    data: data
  })
}

// 删除
export function delTestPaperTemplateMst(ids: string | number | Array<string | number>) {
  return request({
    url: '/pxks/exam_paper/' + ids,
    method: 'delete'
  })
}