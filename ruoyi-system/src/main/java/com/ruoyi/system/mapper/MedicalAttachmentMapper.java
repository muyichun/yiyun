package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.MedicalAttachment;

/**
 * 病历附件Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
public interface MedicalAttachmentMapper 
{
    /**
     * 查询病历附件
     * 
     * @param attachmentId 病历附件主键
     * @return 病历附件
     */
    public MedicalAttachment selectMedicalAttachmentByAttachmentId(Long attachmentId);

    /**
     * 查询病历附件列表
     * 
     * @param medicalAttachment 病历附件
     * @return 病历附件集合
     */
    public List<MedicalAttachment> selectMedicalAttachmentList(MedicalAttachment medicalAttachment);

    /**
     * 根据病历ID查询附件列表
     * 
     * @param recordId 病历ID
     * @return 病历附件集合
     */
    public List<MedicalAttachment> selectAttachmentsByRecordId(Long recordId);

    /**
     * 新增病历附件
     * 
     * @param medicalAttachment 病历附件
     * @return 结果
     */
    public int insertMedicalAttachment(MedicalAttachment medicalAttachment);

    /**
     * 修改病历附件
     * 
     * @param medicalAttachment 病历附件
     * @return 结果
     */
    public int updateMedicalAttachment(MedicalAttachment medicalAttachment);

    /**
     * 删除病历附件
     * 
     * @param attachmentId 病历附件主键
     * @return 结果
     */
    public int deleteMedicalAttachmentByAttachmentId(Long attachmentId);

    /**
     * 批量删除病历附件
     * 
     * @param attachmentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMedicalAttachmentByAttachmentIds(Long[] attachmentIds);

    /**
     * 根据病历ID删除附件
     * 
     * @param recordId 病历ID
     * @return 结果
     */
    public int deleteAttachmentsByRecordId(Long recordId);
} 