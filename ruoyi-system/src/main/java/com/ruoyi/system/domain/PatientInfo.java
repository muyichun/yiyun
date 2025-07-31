package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 患者信息对象 patient_info
 * 
 * @author ruoyi
 */
public class PatientInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 患者ID */
    private Long patientId;

    /** 关联用户ID */
    @Excel(name = "关联用户ID")
    private Long userId;

    /** 患者编号 */
    @Excel(name = "患者编号")
    private String patientNo;

    /** 患者姓名 */
    @Excel(name = "患者姓名")
    private String patientName;

    /** 性别 */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String gender;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthDate;

    /** 年龄 */
    @Excel(name = "年龄")
    private Integer age;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCard;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 紧急联系人 */
    @Excel(name = "紧急联系人")
    private String emergencyContact;

    /** 紧急联系人电话 */
    @Excel(name = "紧急联系人电话")
    private String emergencyPhone;

    /** 家庭住址 */
    @Excel(name = "家庭住址")
    private String address;

    /** 血型 */
    @Excel(name = "血型")
    private String bloodType;

    /** 过敏史 */
    @Excel(name = "过敏史")
    private String allergyHistory;

    /** 家族病史 */
    @Excel(name = "家族病史")
    private String familyHistory;

    /** 婚姻状况 */
    @Excel(name = "婚姻状况", readConverterExp = "0=未婚,1=已婚,2=离异,3=丧偶")
    private String maritalStatus;

    /** 职业 */
    @Excel(name = "职业")
    private String occupation;

    /** 医保类型 */
    @Excel(name = "医保类型")
    private String insuranceType;

    /** 医保号 */
    @Excel(name = "医保号")
    private String insuranceNo;

    /** 状态 */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志 */
    private String delFlag;

    public void setPatientId(Long patientId) 
    {
        this.patientId = patientId;
    }

    public Long getPatientId() 
    {
        return patientId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setPatientNo(String patientNo) 
    {
        this.patientNo = patientNo;
    }

    public String getPatientNo() 
    {
        return patientNo;
    }

    public void setPatientName(String patientName) 
    {
        this.patientName = patientName;
    }

    public String getPatientName() 
    {
        return patientName;
    }

    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }

    public void setBirthDate(Date birthDate) 
    {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() 
    {
        return birthDate;
    }

    public void setAge(Integer age) 
    {
        this.age = age;
    }

    public Integer getAge() 
    {
        return age;
    }

    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setEmergencyContact(String emergencyContact) 
    {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContact() 
    {
        return emergencyContact;
    }

    public void setEmergencyPhone(String emergencyPhone) 
    {
        this.emergencyPhone = emergencyPhone;
    }

    public String getEmergencyPhone() 
    {
        return emergencyPhone;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setBloodType(String bloodType) 
    {
        this.bloodType = bloodType;
    }

    public String getBloodType() 
    {
        return bloodType;
    }

    public void setAllergyHistory(String allergyHistory) 
    {
        this.allergyHistory = allergyHistory;
    }

    public String getAllergyHistory() 
    {
        return allergyHistory;
    }

    public void setFamilyHistory(String familyHistory) 
    {
        this.familyHistory = familyHistory;
    }

    public String getFamilyHistory() 
    {
        return familyHistory;
    }

    public void setMaritalStatus(String maritalStatus) 
    {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() 
    {
        return maritalStatus;
    }

    public void setOccupation(String occupation) 
    {
        this.occupation = occupation;
    }

    public String getOccupation() 
    {
        return occupation;
    }

    public void setInsuranceType(String insuranceType) 
    {
        this.insuranceType = insuranceType;
    }

    public String getInsuranceType() 
    {
        return insuranceType;
    }

    public void setInsuranceNo(String insuranceNo) 
    {
        this.insuranceNo = insuranceNo;
    }

    public String getInsuranceNo() 
    {
        return insuranceNo;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("patientId", getPatientId())
            .append("userId", getUserId())
            .append("patientNo", getPatientNo())
            .append("patientName", getPatientName())
            .append("gender", getGender())
            .append("birthDate", getBirthDate())
            .append("age", getAge())
            .append("idCard", getIdCard())
            .append("phone", getPhone())
            .append("emergencyContact", getEmergencyContact())
            .append("emergencyPhone", getEmergencyPhone())
            .append("address", getAddress())
            .append("bloodType", getBloodType())
            .append("allergyHistory", getAllergyHistory())
            .append("familyHistory", getFamilyHistory())
            .append("maritalStatus", getMaritalStatus())
            .append("occupation", getOccupation())
            .append("insuranceType", getInsuranceType())
            .append("insuranceNo", getInsuranceNo())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
} 