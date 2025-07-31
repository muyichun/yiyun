package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 医生信息对象 doctor_info
 * 
 * @author ruoyi
 */
public class DoctorInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 医生ID */
    private Long doctorId;

    /** 关联用户ID */
    @Excel(name = "关联用户ID")
    private Long userId;

    /** 医生编号 */
    @Excel(name = "医生编号")
    private String doctorNo;

    /** 医生姓名 */
    @Excel(name = "医生姓名")
    private String doctorName;

    /** 性别 */
    @Excel(name = "性别", readConverterExp = "0=男,1=女")
    private String gender;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthDate;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 科室 */
    @Excel(name = "科室")
    private String department;

    /** 职称 */
    @Excel(name = "职称")
    private String title;

    /** 专长 */
    @Excel(name = "专长")
    private String specialty;

    /** 学历 */
    @Excel(name = "学历")
    private String education;

    /** 执业证号 */
    @Excel(name = "执业证号")
    private String licenseNo;

    /** 工作年限 */
    @Excel(name = "工作年限")
    private Integer workYears;

    /** 状态 */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志 */
    private String delFlag;

    public void setDoctorId(Long doctorId) 
    {
        this.doctorId = doctorId;
    }

    public Long getDoctorId() 
    {
        return doctorId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setDoctorNo(String doctorNo) 
    {
        this.doctorNo = doctorNo;
    }

    public String getDoctorNo() 
    {
        return doctorNo;
    }

    public void setDoctorName(String doctorName) 
    {
        this.doctorName = doctorName;
    }

    public String getDoctorName() 
    {
        return doctorName;
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

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setSpecialty(String specialty) 
    {
        this.specialty = specialty;
    }

    public String getSpecialty() 
    {
        return specialty;
    }

    public void setEducation(String education) 
    {
        this.education = education;
    }

    public String getEducation() 
    {
        return education;
    }

    public void setLicenseNo(String licenseNo) 
    {
        this.licenseNo = licenseNo;
    }

    public String getLicenseNo() 
    {
        return licenseNo;
    }

    public void setWorkYears(Integer workYears) 
    {
        this.workYears = workYears;
    }

    public Integer getWorkYears() 
    {
        return workYears;
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
            .append("doctorId", getDoctorId())
            .append("userId", getUserId())
            .append("doctorNo", getDoctorNo())
            .append("doctorName", getDoctorName())
            .append("gender", getGender())
            .append("birthDate", getBirthDate())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("department", getDepartment())
            .append("title", getTitle())
            .append("specialty", getSpecialty())
            .append("education", getEducation())
            .append("licenseNo", getLicenseNo())
            .append("workYears", getWorkYears())
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