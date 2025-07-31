package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.MedicalAttachment;

/**
 * 病历附件Service接口
 * 
 * @author ruoyi
 */
public interface IMedicalAttachmentService 
{
    /**
     * 查询病历附件
     * 
     * @param attachmentId 病历附件主键
     * @return 病历附件
     */
    public MedicalAttachment selectMedicalAttachmentById(Long attachmentId);

    /**
     * 查询病历附件列表
     * 
     * @param medicalAttachment 病历附件
     * @return 病历附件集合
     */
    public List<MedicalAttachment> selectMedicalAttachmentList(MedicalAttachment medicalAttachment);

    /**
     * 根据病历记录ID查询附件列表
     * 
     * @param recordId 病历记录ID
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
     * 批量删除病历附件
     * 
     * @param attachmentIds 需要删除的病历附件主键集合
     * @return 结果
     */
    public int deleteMedicalAttachmentByIds(Long[] attachmentIds);

    /**
     * 删除病历附件信息
     * 
     * @param attachmentId 病历附件主键
     * @return 结果
     */
    public int deleteMedicalAttachmentById(Long attachmentId);
} 