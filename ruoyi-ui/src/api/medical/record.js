import request from '@/utils/request'

// 查询所有病历列表（超级管理员）
export function listAllRecords(query) {
  return request({
    url: '/medical/record/list',
    method: 'get',
    params: query
  })
}

// 查询我的病历列表（患者角色）
export function listMyRecords(query) {
  return request({
    url: '/medical/record/my/list',
    method: 'get',
    params: query
  })
}

// 查询我创建的病历列表（医生角色）
export function listDoctorRecords(query) {
  return request({
    url: '/medical/record/doctor/list',
    method: 'get',
    params: query
  })
}

// 查询病历详细
export function getRecord(recordId) {
  return request({
    url: '/medical/record/' + recordId,
    method: 'get'
  })
}

// 新增病历记录
export function addRecord(data) {
  return request({
    url: '/medical/record',
    method: 'post',
    data: data
  })
}

// 修改病历记录
export function updateRecord(data) {
  return request({
    url: '/medical/record',
    method: 'put',
    data: data
  })
}

// 删除病历记录
export function delRecord(recordId) {
  return request({
    url: '/medical/record/' + recordId,
    method: 'delete'
  })
}

// 上传病历附件
export function uploadAttachment(data) {
  return request({
    url: '/medical/record/attachment/upload',
    method: 'post',
    data: data,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

// 删除病历附件
export function delAttachment(attachmentIds) {
  return request({
    url: '/medical/record/attachment/' + attachmentIds,
    method: 'delete'
  })
} 