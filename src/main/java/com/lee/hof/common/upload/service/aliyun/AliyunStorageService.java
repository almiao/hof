package com.lee.hof.common.upload.service.aliyun;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.*;
import com.lee.hof.common.exception.HofException;
import com.lee.hof.common.upload.*;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;


public class AliyunStorageService extends StorageService {
    private final static Logger logger = LoggerFactory.getLogger(AliyunStorageService.class);

    private final static String ALIYUN_PROVIDER_NAME = "aliyun";

    protected String bucketName;

    protected String endpoint;

    protected String accessKeyId;

    protected String accessKeySecret;

    protected OSSClient ossClient;

    public AliyunStorageService(Properties properties) {
        if (properties.containsKey("aliyun.oss.bucketName")) {
            this.bucketName = properties.getProperty("aliyun.oss.bucketName");
        }

        if (properties.containsKey("aliyun.oss.endpoint")) {
            this.endpoint = properties.getProperty("aliyun.oss.endpoint");
        }

        if (properties.containsKey("aliyun.oss.accessKeyId")) {
            this.accessKeyId = properties.getProperty("aliyun.oss.accessKeyId");
        }

        if (properties.containsKey("aliyun.oss.accessKeySecret")) {
            this.accessKeySecret = properties.getProperty("aliyun.oss.accessKeySecret");
        }
    }

    @Override
    public String getKey(String targetFilename, String originalFilename) {
        if (StringUtils.isEmpty(targetFilename)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            targetFilename = simpleDateFormat.format(Calendar.getInstance().getTime()) + "/" + System.currentTimeMillis() + "_" + originalFilename;
        }
        return targetFilename;
    }

    @Override
    public UploadedFileBean uploadFile(MultipartFile uploadedFile, String targetFilename) throws IOException {
        return uploadFile(uploadedFile.getInputStream(), new FileInfo(uploadedFile), getKey(targetFilename, uploadedFile.getOriginalFilename()));
    }

    @Override
    public UploadedFileBean uploadFile(InputStream inputStream, FileInfo fileInfo, String targetFilename) throws IOException {
        OSSClient ossClient = getOSSClient();

        PutObjectResult putObjectResult = ossClient.putObject(bucketName, targetFilename, inputStream);
        logger.info("upload result: " + putObjectResult);

        UploadedFileBean uploadedFileBean = new UploadedFileBean(targetFilename, ALIYUN_PROVIDER_NAME);
        uploadedFileBean.setName(fileInfo.getName());
        uploadedFileBean.setSize(fileInfo.getSize());
        uploadedFileBean.setType(fileInfo.getContentType());
        uploadedFileBean.setExtension(FilenameUtils.getExtension(fileInfo.getName()));

        return uploadedFileBean;
    }

    @Override
    public InputStream getFile(String key) {
        OSSClient ossClient = getOSSClient();

        OSSObject ossObject = ossClient.getObject(bucketName, key);
        return ossObject.getObjectContent();
    }

    @Override
    public URL getFileUrl(String key) {

        OSSClient ossClient = getOSSClient();

        // 设置URL过期时间为3小时。
        Date expiration = new Date(System.currentTimeMillis() + 3 * 3600 * 1000);

        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        return ossClient.generatePresignedUrl(bucketName, key, expiration);

    }

    @Override
    public String getUploadId(String key) {
        try {
            InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, key);
            InitiateMultipartUploadResult upresult = ossClient.initiateMultipartUpload(request);
            return upresult.getUploadId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new HofException("获取上传id错误");
        }
    }


    @Override
    public UploadedFileBean uploadPartFile(UploadPartFileBean uploadPartFileBean) {
        try {
            UploadPartRequest uploadPartRequest = new UploadPartRequest();
            uploadPartRequest.setBucketName(bucketName);
            uploadPartRequest.setKey(uploadPartFileBean.getKey());
            uploadPartRequest.setUploadId(uploadPartFileBean.getUploadId());
            MockMultipartFile mockMultipartFile = uploadPartFileBean.getFile();
            InputStream inputStream = mockMultipartFile.getInputStream();
            uploadPartRequest.setInputStream(inputStream);
            // 设置分片大小。除了最后一个分片没有大小限制，其他的分片最小为100 KB。
            uploadPartRequest.setPartSize(inputStream.available());
            // 设置分片号。每一个上传的分片都有一个分片号，取值范围是1~10000，如果超出这个范围，OSS将返回InvalidArgument的错误码。
            uploadPartRequest.setPartNumber(uploadPartFileBean.getPartNumber());
            // 每个分片不需要按顺序上传，甚至可以在不同客户端上传，OSS会按照分片号排序组成完整的文件。
            UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);

            UploadedFileBean uploadedFileBean = new UploadedFileBean(uploadPartFileBean.getKey(), ALIYUN_PROVIDER_NAME);
            uploadedFileBean.setName(mockMultipartFile.getOriginalFilename());
            uploadedFileBean.setType(mockMultipartFile.getContentType());
            uploadedFileBean.setSize(mockMultipartFile.getSize());
            uploadedFileBean.setExtension(FilenameUtils.getExtension(mockMultipartFile.getOriginalFilename()));
            uploadedFileBean.setUploadId(uploadPartFileBean.getUploadId());
            uploadedFileBean.setPartEtag(JSONObject.toJSONString(uploadPartResult.getPartETag()));
            return uploadedFileBean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HofException("分块上传文件错误");
        }
    }

    @Override
    public UploadedFileBean mergePartFiles(MergePartFileBean mergePartFileBean) {
        try {
            List<String> partUniqs = mergePartFileBean.getPartUniqs();
            List<PartETag> partETags = new ArrayList<>();
            partUniqs.forEach(partUniq -> partETags.add(JSONObject.parseObject(partUniq, PartETag.class)));

            // 创建CompleteMultipartUploadRequest对象。
            //  在执行完成分片上传操作时，需要提供所有有效的partETags。OSS收到提交的partETags后，会逐一验证每个分片的有效性。当所有的数据分片验证通过后，OSS将把这些分片组合成一个完整的文件。
            CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest(bucketName, mergePartFileBean.getKey(), mergePartFileBean.getUploadId(), partETags);
            // 如果需要在完成文件上传的同时设置文件访问权限，请参考以下示例代码。
            // completeMultipartUploadRequest.setObjectACL(CannedAccessControlList.PublicRead);
            // 完成上传。
            CompleteMultipartUploadResult completeMultipartUploadResult = ossClient.completeMultipartUpload(completeMultipartUploadRequest);
            UploadedFileBean uploadedFileBean = new UploadedFileBean(mergePartFileBean.getKey(), ALIYUN_PROVIDER_NAME);
            uploadedFileBean.setUploadId(mergePartFileBean.getUploadId());
            uploadedFileBean.setPartEtag(completeMultipartUploadResult.getETag());
            return uploadedFileBean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HofException("文件上传合并错误");
        }

    }

    @Override
    public void deleteFile(String key) {
        OSSClient ossClient = getOSSClient();
        ossClient.deleteObject(bucketName, key);
    }

    @Override
    public List<UploadedFileBean> listFiles(String prefix) {
        List<UploadedFileBean> uploadedFileBeans = new ArrayList<UploadedFileBean>();

        ObjectListing objectListing = getOSSClient().listObjects(prefix);
        for (OSSObjectSummary ossObjectSummary : objectListing.getObjectSummaries()) {
            uploadedFileBeans.add(new UploadedFileBean(ossObjectSummary.getKey(), ALIYUN_PROVIDER_NAME));
        }

        return uploadedFileBeans;
    }

    @Override
    public String getContext() {
        return bucketName;
    }

    public synchronized OSSClient getOSSClient() {
        if (ossClient == null) {
            CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
            ossClient = new OSSClient(endpoint, credentialsProvider, null);
        }

        return ossClient;
    }
}
