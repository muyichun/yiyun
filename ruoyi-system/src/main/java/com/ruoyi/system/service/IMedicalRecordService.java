package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.MedicalRecord;

/**
 * 病历记录Service接口
 * 
 * @author ruoyi
 */
public interface IMedicalRecordService 
{
    /**
     * 查询病历记录
     * 
     * @param recordId 病历记录主键
     * @return 病历记录
     */
    public MedicalRecord selectMedicalRecordById(Long recordId);

    /**
     * 查询病历记录列表
     * 
     * @param medicalRecord 病历记录
     * @return 病历记录集合
     */
    public List<MedicalRecord> selectMedicalRecordList(MedicalRecord medicalRecord);

    /**
     * 新增病历记录
     * 
     * @param medicalRecord 病历记录
     * @return 结果
     */
    public int insertMedicalRecord(MedicalRecord medicalRecord);

    /**
     * 修改病历记录
     * 
     * @param medicalRecord 病历记录
     * @return 结果
     */
    public int updateMedicalRecord(MedicalRecord medicalRecord);

    /**
     * 批量删除病历记录
     * 
     * @param recordIds 需要删除的病历记录主键集合
     * @return 结果
     */
    public int deleteMedicalRecordByIds(Long[] recordIds);

    /**
     * 删除病历记录信息
     * 
     * @param recordId 病历记录主键
     * @return 结果
     */
    public int deleteMedicalRecordById(Long recordId);

    /**
     * 查询我的病历记录列表（患者）
     * 
     * @param userId 用户ID
     * @return 病历记录集合
     */
    public List<MedicalRecord> selectMyMedicalRecordList(Long userId);

    /**
     * 查询医生创建的病历记录列表
     * 
     * @param userId 医生用户ID
     * @return 病历记录集合
     */
    public List<MedicalRecord> selectDoctorMedicalRecordList(Long userId);
} 