package com.ruoyi.system.domain;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 病历记录对象 medical_record
 * 
 * @author ruoyi
 */
public class MedicalRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 病历记录ID */
    private Long recordId;

    /** 病历编号 */
    @Excel(name = "病历编号")
    private String recordNo;

    /** 患者ID */
    @Excel(name = "患者ID")
    private Long patientId;

    /** 医生ID */
    @Excel(name = "医生ID")
    private Long doctorId;

    /** 就诊日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "就诊日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date visitDate;

    /** 就诊类型 */
    @Excel(name = "就诊类型", readConverterExp = "1=初诊,2=复诊,3=急诊,4=体检")
    private String visitType;

    /** 主诉 */
    @Excel(name = "主诉")
    private String chiefComplaint;

    /** 现病史 */
    @Excel(name = "现病史")
    private String presentIllness;

    /** 既往史 */
    @Excel(name = "既往史")
    private String pastHistory;

    /** 体格检查 */
    @Excel(name = "体格检查")
    private String physicalExam;

    /** 辅助检查 */
    @Excel(name = "辅助检查")
    private String auxiliaryExam;

    /** 诊断结果 */
    @Excel(name = "诊断结果")
    private String diagnosis;

    /** 治疗方案 */
    @Excel(name = "治疗方案")
    private String treatmentPlan;

    /** 处方信息 */
    @Excel(name = "处方信息")
    private String prescription;

    /** 随访计划 */
    @Excel(name = "随访计划")
    private String followUpPlan;

    /** 医生备注 */
    @Excel(name = "医生备注")
    private String notes;

    /** 病历状态 */
    @Excel(name = "病历状态", readConverterExp = "0=草稿,1=完成,2=已复印")
    private String recordStatus;

    /** 复印次数 */
    @Excel(name = "复印次数")
    private Integer copyCount;

    /** 最后复印时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最后复印时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lastCopyTime;

    /** 删除标志 */
    private String delFlag;

    /** 患者信息 */
    private PatientInfo patientInfo;

    /** 医生信息 */
    private DoctorInfo doctorInfo;

    /** 附件列表 */
    private List<MedicalAttachment> attachments;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }

    public void setRecordNo(String recordNo) 
    {
        this.recordNo = recordNo;
    }

    public String getRecordNo() 
    {
        return recordNo;
    }

    public void setPatientId(Long patientId) 
    {
        this.patientId = patientId;
    }

    public Long getPatientId() 
    {
        return patientId;
    }

    public void setDoctorId(Long doctorId) 
    {
        this.doctorId = doctorId;
    }

    public Long getDoctorId() 
    {
        return doctorId;
    }

    public void setVisitDate(Date visitDate) 
    {
        this.visitDate = visitDate;
    }

    public Date getVisitDate() 
    {
        return visitDate;
    }

    public void setVisitType(String visitType) 
    {
        this.visitType = visitType;
    }

    public String getVisitType() 
    {
        return visitType;
    }

    public void setChiefComplaint(String chiefComplaint) 
    {
        this.chiefComplaint = chiefComplaint;
    }

    public String getChiefComplaint() 
    {
        return chiefComplaint;
    }

    public void setPresentIllness(String presentIllness) 
    {
        this.presentIllness = presentIllness;
    }

    public String getPresentIllness() 
    {
        return presentIllness;
    }

    public void setPastHistory(String pastHistory) 
    {
        this.pastHistory = pastHistory;
    }

    public String getPastHistory() 
    {
        return pastHistory;
    }

    public void setPhysicalExam(String physicalExam) 
    {
        this.physicalExam = physicalExam;
    }

    public String getPhysicalExam() 
    {
        return physicalExam;
    }

    public void setAuxiliaryExam(String auxiliaryExam) 
    {
        this.auxiliaryExam = auxiliaryExam;
    }

    public String getAuxiliaryExam() 
    {
        return auxiliaryExam;
    }

    public void setDiagnosis(String diagnosis) 
    {
        this.diagnosis = diagnosis;
    }

    public String getDiagnosis() 
    {
        return diagnosis;
    }

    public void setTreatmentPlan(String treatmentPlan) 
    {
        this.treatmentPlan = treatmentPlan;
    }

    public String getTreatmentPlan() 
    {
        return treatmentPlan;
    }

    public void setPrescription(String prescription) 
    {
        this.prescription = prescription;
    }

    public String getPrescription() 
    {
        return prescription;
    }

    public void setFollowUpPlan(String followUpPlan) 
    {
        this.followUpPlan = followUpPlan;
    }

    public String getFollowUpPlan() 
    {
        return followUpPlan;
    }

    public void setNotes(String notes) 
    {
        this.notes = notes;
    }

    public String getNotes() 
    {
        return notes;
    }

    public void setRecordStatus(String recordStatus) 
    {
        this.recordStatus = recordStatus;
    }

    public String getRecordStatus() 
    {
        return recordStatus;
    }

    public void setCopyCount(Integer copyCount) 
    {
        this.copyCount = copyCount;
    }

    public Integer getCopyCount() 
    {
        return copyCount;
    }

    public void setLastCopyTime(Date lastCopyTime) 
    {
        this.lastCopyTime = lastCopyTime;
    }

    public Date getLastCopyTime() 
    {
        return lastCopyTime;
    }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public PatientInfo getPatientInfo() 
    {
        return patientInfo;
    }

    public void setPatientInfo(PatientInfo patientInfo) 
    {
        this.patientInfo = patientInfo;
    }

    public DoctorInfo getDoctorInfo() 
    {
        return doctorInfo;
    }

    public void setDoctorInfo(DoctorInfo doctorInfo) 
    {
        this.doctorInfo = doctorInfo;
    }

    public List<MedicalAttachment> getAttachments() 
    {
        return attachments;
    }

    public void setAttachments(List<MedicalAttachment> attachments) 
    {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("recordNo", getRecordNo())
            .append("patientId", getPatientId())
            .append("doctorId", getDoctorId())
            .append("visitDate", getVisitDate())
            .append("visitType", getVisitType())
            .append("chiefComplaint", getChiefComplaint())
            .append("presentIllness", getPresentIllness())
            .append("pastHistory", getPastHistory())
            .append("physicalExam", getPhysicalExam())
            .append("auxiliaryExam", getAuxiliaryExam())
            .append("diagnosis", getDiagnosis())
            .append("treatmentPlan", getTreatmentPlan())
            .append("prescription", getPrescription())
            .append("followUpPlan", getFollowUpPlan())
            .append("notes", getNotes())
            .append("recordStatus", getRecordStatus())
            .append("copyCount", getCopyCount())
            .append("lastCopyTime", getLastCopyTime())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
} 