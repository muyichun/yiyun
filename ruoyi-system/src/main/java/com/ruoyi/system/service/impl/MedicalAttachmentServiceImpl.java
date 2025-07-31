package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MedicalAttachmentMapper;
import com.ruoyi.system.domain.MedicalAttachment;
import com.ruoyi.system.service.IMedicalAttachmentService;

@Service
public class MedicalAttachmentServiceImpl implements IMedicalAttachmentService {
    @Autowired
    private MedicalAttachmentMapper medicalAttachmentMapper;

    @Override
    public MedicalAttachment selectMedicalAttachmentById(Long attachmentId) {
        return medicalAttachmentMapper.selectMedicalAttachmentByAttachmentId(attachmentId);
    }

    @Override
    public List<MedicalAttachment> selectMedicalAttachmentList(MedicalAttachment medicalAttachment) {
        return medicalAttachmentMapper.selectMedicalAttachmentList(medicalAttachment);
    }

    @Override
    public List<MedicalAttachment> selectAttachmentsByRecordId(Long recordId) {
        return medicalAttachmentMapper.selectAttachmentsByRecordId(recordId);
    }

    @Override
    public int insertMedicalAttachment(MedicalAttachment medicalAttachment) {
        return medicalAttachmentMapper.insertMedicalAttachment(medicalAttachment);
    }

    @Override
    public int updateMedicalAttachment(MedicalAttachment medicalAttachment) {
        return medicalAttachmentMapper.updateMedicalAttachment(medicalAttachment);
    }

    @Override
    public int deleteMedicalAttachmentByIds(Long[] attachmentIds) {
        return medicalAttachmentMapper.deleteMedicalAttachmentByAttachmentIds(attachmentIds);
    }

    @Override
    public int deleteMedicalAttachmentById(Long attachmentId) {
        return medicalAttachmentMapper.deleteMedicalAttachmentByAttachmentId(attachmentId);
    }
} 