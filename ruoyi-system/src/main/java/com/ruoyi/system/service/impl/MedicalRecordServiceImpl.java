package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MedicalRecordMapper;
import com.ruoyi.system.domain.MedicalRecord;
import com.ruoyi.system.service.IMedicalRecordService;

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {
    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    @Override
    public MedicalRecord selectMedicalRecordById(Long recordId) {
        return medicalRecordMapper.selectMedicalRecordByRecordId(recordId);
    }

    @Override
    public List<MedicalRecord> selectMedicalRecordList(MedicalRecord medicalRecord) {
        return medicalRecordMapper.selectMedicalRecordList(medicalRecord);
    }

    @Override
    public int insertMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordMapper.insertMedicalRecord(medicalRecord);
    }

    @Override
    public int updateMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordMapper.updateMedicalRecord(medicalRecord);
    }

    @Override
    public int deleteMedicalRecordByIds(Long[] recordIds) {
        return medicalRecordMapper.deleteMedicalRecordByRecordIds(recordIds);
    }

    @Override
    public int deleteMedicalRecordById(Long recordId) {
        return medicalRecordMapper.deleteMedicalRecordByRecordId(recordId);
    }

    @Override
    public List<MedicalRecord> selectMyMedicalRecordList(Long userId) {
        return medicalRecordMapper.selectMyMedicalRecordList(userId);
    }

    @Override
    public List<MedicalRecord> selectDoctorMedicalRecordList(Long userId) {
        return medicalRecordMapper.selectDoctorMedicalRecordList(userId);
    }
} 