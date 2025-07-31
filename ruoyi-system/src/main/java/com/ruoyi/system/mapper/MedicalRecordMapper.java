package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.MedicalRecord;

/**
 * 病历档案Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
public interface MedicalRecordMapper 
{
    /**
     * 查询病历档案
     * 
     * @param recordId 病历档案主键
     * @return 病历档案
     */
    public MedicalRecord selectMedicalRecordByRecordId(Long recordId);

    /**
     * 查询病历档案列表
     * 
     * @param medicalRecord 病历档案
     * @return 病历档案集合
     */
    public List<MedicalRecord> selectMedicalRecordList(MedicalRecord medicalRecord);

    /**
     * 查询我的病历档案列表（患者）
     * 
     * @param userId 用户ID
     * @return 病历档案集合
     */
    public List<MedicalRecord> selectMyMedicalRecordList(Long userId);

    /**
     * 查询医生创建的病历档案列表
     * 
     * @param userId 医生用户ID
     * @return 病历档案集合
     */
    public List<MedicalRecord> selectDoctorMedicalRecordList(Long userId);

    /**
     * 新增病历档案
     * 
     * @param medicalRecord 病历档案
     * @return 结果
     */
    public int insertMedicalRecord(MedicalRecord medicalRecord);

    /**
     * 修改病历档案
     * 
     * @param medicalRecord 病历档案
     * @return 结果
     */
    public int updateMedicalRecord(MedicalRecord medicalRecord);

    /**
     * 删除病历档案
     * 
     * @param recordId 病历档案主键
     * @return 结果
     */
    public int deleteMedicalRecordByRecordId(Long recordId);

    /**
     * 批量删除病历档案
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMedicalRecordByRecordIds(Long[] recordIds);
} 