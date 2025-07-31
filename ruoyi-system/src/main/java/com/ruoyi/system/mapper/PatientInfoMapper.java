package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.PatientInfo;

/**
 * 患者信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
public interface PatientInfoMapper 
{
    /**
     * 查询患者信息
     * 
     * @param patientId 患者信息主键
     * @return 患者信息
     */
    public PatientInfo selectPatientInfoByPatientId(Long patientId);

    /**
     * 根据用户ID查询患者信息
     * 
     * @param userId 用户ID
     * @return 患者信息
     */
    public PatientInfo selectPatientInfoByUserId(Long userId);

    /**
     * 查询患者信息列表
     * 
     * @param patientInfo 患者信息
     * @return 患者信息集合
     */
    public List<PatientInfo> selectPatientInfoList(PatientInfo patientInfo);

    /**
     * 新增患者信息
     * 
     * @param patientInfo 患者信息
     * @return 结果
     */
    public int insertPatientInfo(PatientInfo patientInfo);

    /**
     * 修改患者信息
     * 
     * @param patientInfo 患者信息
     * @return 结果
     */
    public int updatePatientInfo(PatientInfo patientInfo);

    /**
     * 删除患者信息
     * 
     * @param patientId 患者信息主键
     * @return 结果
     */
    public int deletePatientInfoByPatientId(Long patientId);

    /**
     * 批量删除患者信息
     * 
     * @param patientIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePatientInfoByPatientIds(Long[] patientIds);
} 