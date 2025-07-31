package com.ruoyi.web.controller.medical;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.domain.MedicalRecord;
import com.ruoyi.system.domain.MedicalAttachment;
import com.ruoyi.system.service.IMedicalRecordService;
import com.ruoyi.system.service.IMedicalAttachmentService;

/**
 * 电子病历档案复印系统Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/medical/record")
public class MedicalRecordController extends BaseController
{
    @Autowired
    private IMedicalRecordService medicalRecordService;

    @Autowired
    private IMedicalAttachmentService medicalAttachmentService;

    /**
     * 获取病历记录列表
     */
    @PreAuthorize("@ss.hasPermi('medical:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(MedicalRecord medicalRecord) {
        startPage();
        List<MedicalRecord> list = medicalRecordService.selectMedicalRecordList(medicalRecord);
        return getDataTable(list);
    }

    /**
     * 查询我的病历列表（患者角色）
     */
    @PreAuthorize("@ss.hasPermi('medical:record:my:list')")
    @GetMapping("/my/list")
    public TableDataInfo myList(MedicalRecord medicalRecord)
    {
        startPage();
        // 设置当前用户ID，只查询当前用户的病历
        medicalRecord.setPatientId(SecurityUtils.getUserId());
        List<MedicalRecord> list = medicalRecordService.selectMedicalRecordList(medicalRecord);
        return getDataTable(list);
    }

    /**
     * 查询我创建的病历列表（医生角色）
     */
    @PreAuthorize("@ss.hasPermi('medical:record:doctor:list')")
    @GetMapping("/doctor/list")
    public TableDataInfo doctorList(MedicalRecord medicalRecord)
    {
        startPage();
        // 设置当前医生ID，只查询当前医生创建的病历
        medicalRecord.setDoctorId(SecurityUtils.getUserId());
        List<MedicalRecord> list = medicalRecordService.selectMedicalRecordList(medicalRecord);
        return getDataTable(list);
    }

    /**
     * 获取病历详细信息
     */
    @PreAuthorize("@ss.hasPermi('medical:record:query')")
    @GetMapping("/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        MedicalRecord medicalRecord = medicalRecordService.selectMedicalRecordById(recordId);
        if (medicalRecord != null) {
            // 查询附件列表
            List<MedicalAttachment> attachments = medicalAttachmentService.selectAttachmentsByRecordId(recordId);
            medicalRecord.setAttachments(attachments);
        }
        return success(medicalRecord);
    }

    /**
     * 新增病历记录
     */
    @PreAuthorize("@ss.hasPermi('medical:record:add')")
    @Log(title = "病历记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody MedicalRecord medicalRecord)
    {
        medicalRecord.setDoctorId(SecurityUtils.getUserId());
        return toAjax(medicalRecordService.insertMedicalRecord(medicalRecord));
    }

    /**
     * 修改病历记录
     */
    @PreAuthorize("@ss.hasPermi('medical:record:edit')")
    @Log(title = "病历记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody MedicalRecord medicalRecord)
    {
        // 验证是否为当前医生创建的病历
        MedicalRecord existingRecord = medicalRecordService.selectMedicalRecordById(medicalRecord.getRecordId());
        if (existingRecord == null || !existingRecord.getDoctorId().equals(SecurityUtils.getUserId())) {
            return error("病历不存在或无权限修改");
        }
        return toAjax(medicalRecordService.updateMedicalRecord(medicalRecord));
    }

    /**
     * 删除病历记录
     */
    @PreAuthorize("@ss.hasPermi('medical:record:remove')")
    @Log(title = "病历记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        // 验证是否为当前医生创建的病历
        for (Long recordId : recordIds) {
            MedicalRecord record = medicalRecordService.selectMedicalRecordById(recordId);
            if (record == null || !record.getDoctorId().equals(SecurityUtils.getUserId())) {
                return error("病历不存在或无权限删除");
            }
        }
        return toAjax(medicalRecordService.deleteMedicalRecordByIds(recordIds));
    }

    /**
     * 上传病历附件
     */
    @PreAuthorize("@ss.hasPermi('medical:record:attachment:upload')")
    @Log(title = "病历附件上传", businessType = BusinessType.INSERT)
    @PostMapping("/attachment/upload")
    public AjaxResult uploadAttachment(@RequestParam("file") MultipartFile file, 
                                     @RequestParam("recordId") Long recordId, 
                                     @RequestParam(value = "attachmentType", defaultValue = "1") String attachmentType, 
                                     @RequestParam(value = "description", defaultValue = "") String description)
    {
        try {
            // 验证是否为当前医生创建的病历
            MedicalRecord record = medicalRecordService.selectMedicalRecordById(recordId);
            if (record == null || !record.getDoctorId().equals(SecurityUtils.getUserId())) {
                return error("病历不存在或无权限上传附件");
            }

            if (file.isEmpty()) {
                return error("上传文件不能为空");
            }
            
            // 检查文件大小（限制为10MB）
            if (file.getSize() > 10 * 1024 * 1024) {
                return error("文件大小不能超过10MB");
            }
            
            // 检查文件类型
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
            String[] allowedExtensions = {"jpg", "jpeg", "png", "gif", "pdf", "doc", "docx", "xls", "xlsx"};
            boolean isAllowed = false;
            for (String ext : allowedExtensions) {
                if (ext.equals(fileExtension)) {
                    isAllowed = true;
                    break;
                }
            }
            if (!isAllowed) {
                return error("不支持的文件类型，请上传图片、PDF或Office文档");
            }
            
            // 生成文件存储路径
            String uploadPath = "upload/medical/" + DateUtils.datePath() + "/";
            String fileName = IdUtils.fastSimpleUUID() + "." + fileExtension;
            String filePath = uploadPath + fileName;
            
            // 创建目录
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            
            // 保存文件
            File dest = new File(uploadPath + fileName);
            file.transferTo(dest);
            
            // 保存附件信息到数据库
            MedicalAttachment attachment = new MedicalAttachment();
            attachment.setRecordId(recordId);
            attachment.setFileName(fileName);
            attachment.setOriginalName(originalFilename);
            attachment.setFilePath(filePath);
            attachment.setFileSize(file.getSize());
            attachment.setFileType(fileExtension);
            attachment.setAttachmentType(attachmentType);
            attachment.setDescription(description.isEmpty() ? "上传的附件" : description);
            attachment.setUploadTime(DateUtils.getNowDate());
            attachment.setUploadBy(SecurityUtils.getUsername());
            attachment.setDelFlag("0");
            attachment.setCreateBy(SecurityUtils.getUsername());
            attachment.setCreateTime(DateUtils.getNowDate());
            
            medicalAttachmentService.insertMedicalAttachment(attachment);
            
            return success("文件上传成功");
        } catch (Exception e) {
            return error("文件上传失败：" + e.getMessage());
        }
    }

    /**
     * 删除病历附件
     */
    @PreAuthorize("@ss.hasPermi('medical:record:attachment:remove')")
    @Log(title = "病历附件删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/attachment/{attachmentIds}")
    public AjaxResult removeAttachment(@PathVariable Long[] attachmentIds)
    {
        try {
            // 验证附件权限
            for (Long attachmentId : attachmentIds) {
                MedicalAttachment attachment = medicalAttachmentService.selectMedicalAttachmentById(attachmentId);
                if (attachment == null) {
                    return error("附件不存在");
                }
                // 验证是否为当前医生创建的病历的附件
                MedicalRecord record = medicalRecordService.selectMedicalRecordById(attachment.getRecordId());
                if (record == null || !record.getDoctorId().equals(SecurityUtils.getUserId())) {
                    return error("无权限删除此附件");
                }
                
                // 删除物理文件
                String filePath = attachment.getFilePath();
                if (filePath != null && !filePath.isEmpty()) {
                    File file = new File(filePath);
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
            return toAjax(medicalAttachmentService.deleteMedicalAttachmentByIds(attachmentIds));
        } catch (Exception e) {
            return error("附件删除失败：" + e.getMessage());
        }
    }
} 