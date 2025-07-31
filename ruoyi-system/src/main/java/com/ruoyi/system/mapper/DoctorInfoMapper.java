package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.DoctorInfo;

/**
 * 医生信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-01
 */
public interface DoctorInfoMapper 
{
    /**
     * 查询医生信息
     * 
     * @param doctorId 医生信息主键
     * @return 医生信息
     */
    public DoctorInfo selectDoctorInfoByDoctorId(Long doctorId);

    /**
     * 根据用户ID查询医生信息
     * 
     * @param userId 用户ID
     * @return 医生信息
     */
    public DoctorInfo selectDoctorInfoByUserId(Long userId);

    /**
     * 查询医生信息列表
     * 
     * @param doctorInfo 医生信息
     * @return 医生信息集合
     */
    public List<DoctorInfo> selectDoctorInfoList(DoctorInfo doctorInfo);

    /**
     * 新增医生信息
     * 
     * @param doctorInfo 医生信息
     * @return 结果
     */
    public int insertDoctorInfo(DoctorInfo doctorInfo);

    /**
     * 修改医生信息
     * 
     * @param doctorInfo 医生信息
     * @return 结果
     */
    public int updateDoctorInfo(DoctorInfo doctorInfo);

    /**
     * 删除医生信息
     * 
     * @param doctorId 医生信息主键
     * @return 结果
     */
    public int deleteDoctorInfoByDoctorId(Long doctorId);

    /**
     * 批量删除医生信息
     * 
     * @param doctorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDoctorInfoByDoctorIds(Long[] doctorIds);
} 