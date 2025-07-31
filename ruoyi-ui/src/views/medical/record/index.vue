<template>
  <div class="app-container">
    <!-- 搜索表单 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="病历编号" prop="recordNo">
        <el-input v-model="queryParams.recordNo" placeholder="请输入病历编号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="就诊类型" prop="visitType">
        <el-select v-model="queryParams.visitType" placeholder="请选择就诊类型" clearable>
          <el-option v-for="dict in dict.type.medical_visit_type" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="病历状态" prop="recordStatus">
        <el-select v-model="queryParams.recordStatus" placeholder="请选择病历状态" clearable>
          <el-option v-for="dict in dict.type.medical_record_status" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="就诊日期">
        <el-date-picker v-model="dateRange" style="width: 240px" value-format="yyyy-MM-dd" type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <!-- 超级管理员和医生可以新增病历 -->
      <el-col :span="1.5" v-if="isAdmin || isDoctor">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['medical:record:add']">新增病历</el-button>
      </el-col>
      <!-- 超级管理员和医生可以修改病历 -->
      <el-col :span="1.5" v-if="isAdmin || isDoctor">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['medical:record:edit']">修改</el-button>
      </el-col>
      <!-- 只有超级管理员可以删除病历 -->
      <el-col :span="1.5" v-if="isAdmin">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['medical:record:remove']">删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 数据表格 -->
    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <!-- 只有超级管理员和医生才显示选择列 -->
      <el-table-column v-if="isAdmin || isDoctor" type="selection" width="55" align="center" />
      <el-table-column label="病历编号" align="center" prop="recordNo" />
      <el-table-column label="患者姓名" align="center" prop="patientInfo.patientName" />
      <el-table-column label="医生姓名" align="center" prop="doctorInfo.doctorName" />
      <el-table-column label="就诊日期" align="center" prop="visitDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.visitDate, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="就诊类型" align="center" prop="visitType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.medical_visit_type" :value="scope.row.visitType"/>
        </template>
      </el-table-column>
      <el-table-column label="主诉" align="center" prop="chiefComplaint" :show-overflow-tooltip="true" />
      <el-table-column label="诊断结果" align="center" prop="diagnosis" :show-overflow-tooltip="true" />
      <el-table-column label="病历状态" align="center" prop="recordStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.medical_record_status" :value="scope.row.recordStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)" v-hasPermi="['medical:record:query']">查看</el-button>
          <!-- 超级管理员和医生可以修改病历 -->
          <el-button v-if="isAdmin || isDoctor" size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['medical:record:edit']">修改</el-button>
          <!-- 只有超级管理员可以删除病历 -->
          <el-button v-if="isAdmin" size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['medical:record:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <!-- 分页 -->
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改病历记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="病历编号" prop="recordNo">
              <el-input v-model="form.recordNo" placeholder="请输入病历编号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="就诊类型" prop="visitType">
              <el-select v-model="form.visitType" placeholder="请选择就诊类型">
                <el-option v-for="dict in dict.type.medical_visit_type" :key="dict.value" :label="dict.label" :value="dict.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="就诊日期" prop="visitDate">
              <el-date-picker clearable v-model="form.visitDate" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="请选择就诊日期"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="病历状态" prop="recordStatus">
              <el-select v-model="form.recordStatus" placeholder="请选择病历状态">
                <el-option v-for="dict in dict.type.medical_record_status" :key="dict.value" :label="dict.label" :value="dict.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="主诉" prop="chiefComplaint">
          <el-input v-model="form.chiefComplaint" type="textarea" :rows="3" placeholder="请输入主诉" />
        </el-form-item>
        <el-form-item label="现病史" prop="presentIllness">
          <el-input v-model="form.presentIllness" type="textarea" :rows="4" placeholder="请输入现病史" />
        </el-form-item>
        <el-form-item label="既往史" prop="pastHistory">
          <el-input v-model="form.pastHistory" type="textarea" :rows="3" placeholder="请输入既往史" />
        </el-form-item>
        <el-form-item label="体格检查" prop="physicalExam">
          <el-input v-model="form.physicalExam" type="textarea" :rows="4" placeholder="请输入体格检查" />
        </el-form-item>
        <el-form-item label="辅助检查" prop="auxiliaryExam">
          <el-input v-model="form.auxiliaryExam" type="textarea" :rows="3" placeholder="请输入辅助检查" />
        </el-form-item>
        <el-form-item label="诊断结果" prop="diagnosis">
          <el-input v-model="form.diagnosis" type="textarea" :rows="3" placeholder="请输入诊断结果" />
        </el-form-item>
        <el-form-item label="治疗方案" prop="treatmentPlan">
          <el-input v-model="form.treatmentPlan" type="textarea" :rows="4" placeholder="请输入治疗方案" />
        </el-form-item>
        <el-form-item label="处方信息" prop="prescription">
          <el-input v-model="form.prescription" type="textarea" :rows="3" placeholder="请输入处方信息" />
        </el-form-item>
        <el-form-item label="随访计划" prop="followUpPlan">
          <el-input v-model="form.followUpPlan" type="textarea" :rows="2" placeholder="请输入随访计划" />
        </el-form-item>
        <el-form-item label="医生备注" prop="notes">
          <el-input v-model="form.notes" type="textarea" :rows="2" placeholder="请输入医生备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 查看病历记录对话框 -->
    <el-dialog title="查看病历记录" :visible.sync="viewOpen" width="900px" append-to-body>
      <el-descriptions :column="2" border>
        <el-descriptions-item label="病历编号">{{ form.recordNo }}</el-descriptions-item>
        <el-descriptions-item label="就诊类型">
          <dict-tag :options="dict.type.medical_visit_type" :value="form.visitType"/>
        </el-descriptions-item>
        <el-descriptions-item label="就诊日期">{{ parseTime(form.visitDate) }}</el-descriptions-item>
        <el-descriptions-item label="病历状态">
          <dict-tag :options="dict.type.medical_record_status" :value="form.recordStatus"/>
        </el-descriptions-item>
        <el-descriptions-item label="患者姓名">{{ form.patientInfo ? form.patientInfo.patientName : '' }}</el-descriptions-item>
        <el-descriptions-item label="医生姓名">{{ form.doctorInfo ? form.doctorInfo.doctorName : '' }}</el-descriptions-item>
        <el-descriptions-item label="主诉" :span="2">{{ form.chiefComplaint }}</el-descriptions-item>
        <el-descriptions-item label="现病史" :span="2">{{ form.presentIllness }}</el-descriptions-item>
        <el-descriptions-item label="既往史" :span="2">{{ form.pastHistory }}</el-descriptions-item>
        <el-descriptions-item label="体格检查" :span="2">{{ form.physicalExam }}</el-descriptions-item>
        <el-descriptions-item label="辅助检查" :span="2">{{ form.auxiliaryExam }}</el-descriptions-item>
        <el-descriptions-item label="诊断结果" :span="2">{{ form.diagnosis }}</el-descriptions-item>
        <el-descriptions-item label="治疗方案" :span="2">{{ form.treatmentPlan }}</el-descriptions-item>
        <el-descriptions-item label="处方信息" :span="2">{{ form.prescription }}</el-descriptions-item>
        <el-descriptions-item label="随访计划" :span="2">{{ form.followUpPlan }}</el-descriptions-item>
        <el-descriptions-item label="医生备注" :span="2">{{ form.notes }}</el-descriptions-item>
        <el-descriptions-item label="复印次数">{{ form.copyCount }}</el-descriptions-item>
        <el-descriptions-item label="创建时间">{{ parseTime(form.createTime) }}</el-descriptions-item>
      </el-descriptions>

      <!-- 附件列表 -->
      <el-divider content-position="left">病历附件</el-divider>
      <el-table :data="form.attachments" style="width: 100%">
        <el-table-column prop="originalName" label="文件名" />
        <el-table-column prop="attachmentType" label="附件类型">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.medical_attachment_type" :value="scope.row.attachmentType"/>
          </template>
        </el-table-column>
        <el-table-column prop="fileSize" label="文件大小">
          <template slot-scope="scope">
            {{ formatFileSize(scope.row.fileSize) }}
          </template>
        </el-table-column>
        <el-table-column prop="uploadTime" label="上传时间">
          <template slot-scope="scope">
            {{ parseTime(scope.row.uploadTime) }}
          </template>
        </el-table-column>
        <el-table-column prop="description" label="描述" />
        <el-table-column label="操作" width="200">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="downloadAttachment(scope.row)">下载</el-button>
            <el-button size="mini" type="text" @click="previewAttachment(scope.row)">预览</el-button>
            <el-button v-if="isDoctor" size="mini" type="text" style="color: #F56C6C" @click="removeAttachment(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <!-- 文件上传区域（仅医生可见） -->
      <el-divider v-if="isDoctor" content-position="left">上传附件</el-divider>
      <div v-if="isDoctor" style="margin-top: 20px; padding: 20px; border: 1px dashed #d9d9d9; border-radius: 6px;">
        <el-form :model="uploadForm" label-width="100px">
          <el-form-item label="附件类型">
            <el-select v-model="uploadForm.attachmentType" placeholder="请选择附件类型">
              <el-option label="检查报告" value="1"></el-option>
              <el-option label="影像资料" value="2"></el-option>
              <el-option label="其他" value="3"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="文件描述">
            <el-input v-model="uploadForm.description" placeholder="请输入文件描述"></el-input>
          </el-form-item>
          <el-form-item label="选择文件">
            <el-upload ref="upload" :action="uploadUrl" :data="uploadForm" :before-upload="beforeUpload" :on-success="handleUploadSuccess" :on-error="handleUploadError" :file-list="fileList" :limit="1" accept=".jpg,.jpeg,.png,.gif,.pdf,.doc,.docx,.xls,.xlsx">
              <el-button size="small" type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">只能上传jpg/png/gif/pdf/doc/docx/xls/xlsx文件，且不超过10MB</div>
            </el-upload>
          </el-form-item>
        </el-form>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button @click="viewOpen = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAllRecords, listMyRecords, listDoctorRecords, getRecord, addRecord, updateRecord, delRecord, uploadAttachment, delAttachment } from "@/api/medical/record";

export default {
  name: "MedicalRecord",
  dicts: ['medical_visit_type', 'medical_record_status', 'medical_attachment_type'],
  data() {
    return {
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      recordList: [],
      title: "",
      open: false,
      viewOpen: false,
      dateRange: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        recordNo: null,
        visitType: null,
        recordStatus: null
      },
      form: {},
      rules: {
        recordNo: [{ required: true, message: "病历编号不能为空", trigger: "blur" }],
        visitType: [{ required: true, message: "就诊类型不能为空", trigger: "change" }],
        visitDate: [{ required: true, message: "就诊日期不能为空", trigger: "blur" }],
        chiefComplaint: [{ required: true, message: "主诉不能为空", trigger: "blur" }],
        presentIllness: [{ required: true, message: "现病史不能为空", trigger: "blur" }],
        physicalExam: [{ required: true, message: "体格检查不能为空", trigger: "blur" }],
        diagnosis: [{ required: true, message: "诊断结果不能为空", trigger: "blur" }],
        treatmentPlan: [{ required: true, message: "治疗方案不能为空", trigger: "blur" }]
      },
      uploadForm: {
        recordId: null,
        attachmentType: '1',
        description: ''
      },
      fileList: [],
      uploadUrl: process.env.VUE_APP_BASE_API + '/medical/record/attachment/upload'
    };
  },
  created() {
    this.getList();
  },
  computed: {
    isDoctor() {
      return this.$store.getters.roles.includes('doctor');
    },
    isAdmin() {
      return this.$store.getters.roles.includes('admin');
    },
    isPatient() {
      return this.$store.getters.roles.includes('patient');
    }
  },
  methods: {
    getList() {
      this.loading = true;
      let apiMethod;
      
      // 根据角色选择不同的API方法
      if (this.isAdmin) {
        // 超级管理员：查看所有病历
        apiMethod = listAllRecords;
      } else if (this.isDoctor) {
        // 医生：查看自己创建的病历
        apiMethod = listDoctorRecords;
      } else if (this.isPatient) {
        // 患者：查看自己的病历
        apiMethod = listMyRecords;
      } else {
        // 默认使用患者API
        apiMethod = listMyRecords;
      }
      
      apiMethod(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.recordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    cancel() {
      this.open = false;
      this.reset();
    },
    reset() {
      this.form = {
        recordId: null,
        recordNo: null,
        patientId: null,
        doctorId: null,
        visitDate: null,
        visitType: null,
        chiefComplaint: null,
        presentIllness: null,
        pastHistory: null,
        physicalExam: null,
        auxiliaryExam: null,
        diagnosis: null,
        treatmentPlan: null,
        prescription: null,
        followUpPlan: null,
        notes: null,
        recordStatus: "0",
        copyCount: 0,
        attachments: []
      };
      this.resetForm("form");
    },
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.recordId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加病历记录";
    },
    handleView(row) {
      this.reset();
      const recordId = row.recordId || this.ids
      getRecord(recordId).then(response => {
        this.form = response.data;
        this.uploadForm.recordId = recordId;
        this.viewOpen = true;
      });
    },
    handleUpdate(row) {
      this.reset();
      const recordId = row.recordId || this.ids
      getRecord(recordId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改病历记录";
      });
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recordId != null) {
            updateRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecord(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    handleDelete(row) {
      const recordIds = row.recordId || this.ids;
      this.$modal.confirm('是否确认删除病历记录编号为"' + recordIds + '"的数据项？').then(function() {
        return delRecord(recordIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    formatFileSize(size) {
      if (size < 1024) {
        return size + ' B';
      } else if (size < 1024 * 1024) {
        return (size / 1024).toFixed(2) + ' KB';
      } else if (size < 1024 * 1024 * 1024) {
        return (size / (1024 * 1024)).toFixed(2) + ' MB';
      } else {
        return (size / (1024 * 1024 * 1024)).toFixed(2) + ' GB';
      }
    },
    downloadAttachment(attachment) {
      const link = document.createElement('a');
      link.href = process.env.VUE_APP_BASE_API + '/common/download?fileName=' + encodeURIComponent(attachment.filePath) + '&delete=' + false;
      link.download = attachment.originalName;
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    },
    previewAttachment(attachment) {
      const fileType = attachment.fileType.toLowerCase();
      if (['jpg', 'jpeg', 'png', 'gif'].includes(fileType)) {
        this.$alert('<img src="' + process.env.VUE_APP_BASE_API + '/' + attachment.filePath + '" style="max-width: 100%;">', '图片预览', {
          dangerouslyUseHTMLString: true,
          confirmButtonText: '关闭'
        });
      } else if (fileType === 'pdf') {
        window.open(process.env.VUE_APP_BASE_API + '/' + attachment.filePath, '_blank');
      } else {
        this.$modal.msgInfo("此文件类型不支持预览，请下载后查看");
      }
    },
    removeAttachment(attachment) {
      this.$modal.confirm('是否确认删除附件"' + attachment.originalName + '"？').then(function() {
        return delAttachment(attachment.attachmentId);
      }).then(() => {
        this.$modal.msgSuccess("删除成功");
        this.handleView({ recordId: this.form.recordId });
      }).catch(() => {});
    },
    beforeUpload(file) {
      const isValidType = ['jpg', 'jpeg', 'png', 'gif', 'pdf', 'doc', 'docx', 'xls', 'xlsx'].includes(file.name.split('.').pop().toLowerCase());
      const isLt10M = file.size / 1024 / 1024 < 10;
      
      if (!isValidType) {
        this.$message.error('只能上传图片、PDF或Office文档!');
        return false;
      }
      if (!isLt10M) {
        this.$message.error('文件大小不能超过 10MB!');
        return false;
      }
      return true;
    },
    handleUploadSuccess(response, file, fileList) {
      this.$modal.msgSuccess("上传成功");
      this.fileList = [];
      this.handleView({ recordId: this.form.recordId });
    },
    handleUploadError(err, file, fileList) {
      this.$modal.msgError("上传失败");
      this.fileList = [];
    }
  }
};
</script> 