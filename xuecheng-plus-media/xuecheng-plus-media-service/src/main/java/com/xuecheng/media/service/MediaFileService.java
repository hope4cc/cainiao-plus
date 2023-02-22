package com.xuecheng.media.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.media.model.dto.QueryMediaParamsDto;
import com.xuecheng.media.model.dto.UploadFileParamsDto;
import com.xuecheng.media.model.dto.UploadFileResultDto;
import com.xuecheng.media.model.po.MediaFiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @description 媒资文件管理业务类
 * @author Mr.M
 * @date 2022/9/10 8:55
 * @version 1.0
 */
public interface MediaFileService {

 /**
  * @description 媒资文件查询方法
  * @param pageParams 分页参数
  * @param queryMediaParamsDto 查询条件
  * @return com.xuecheng.base.model.PageResult<com.xuecheng.media.model.po.MediaFiles>
  * @author Mr.M
  * @date 2022/9/10 8:57
 */
 public PageResult<MediaFiles> queryMediaFiels(Long companyId,PageParams pageParams, QueryMediaParamsDto queryMediaParamsDto);


 /**
  * @description 上传文件的通用接口
  * @param companyId  机构id
  * @param uploadFileParamsDto  文件信息
  * @param bytes  文件字节数组
  * @param folder 桶下边的子目录
  * @param objectName 对象名称
  * @return com.xuecheng.media.model.dto.UploadFileResultDto
  * @author Mr.M
  * @date 2022/10/13 15:51
 */
 public UploadFileResultDto uploadFile(Long companyId, UploadFileParamsDto uploadFileParamsDto, byte[] bytes,String folder,String objectName);

 /**
  * 将文件信息入库
  * @param companyId
  * @param fileId
  * @param uploadFileParamsDto
  * @param bucket
  * @param objectName
  * @return
  * 声明式事务基于动态代理也就是cglib(默认)和jkd,
  * cgb原理是继承被代理类。
  * 如果方法被private修饰，可想而知代理类无法重写
  *
  */
 @Transactional
 public MediaFiles addMediaFilesToDb(Long companyId,String fileId,UploadFileParamsDto uploadFileParamsDto,String bucket,String objectName);
}
