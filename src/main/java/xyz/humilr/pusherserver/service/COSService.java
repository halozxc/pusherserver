package xyz.humilr.pusherserver.service;



import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.AnonymousCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.*;
import com.tencent.cloud.CosStsClient;
import com.tencent.cloud.Response;
import org.springframework.stereotype.Service;
import xyz.humilr.pusherserver.pojo.module.COSResponse;

import java.util.TreeMap;

@Service
public class COSService {
   public COSResponse getCOSKey(String allowedPath){
       TreeMap<String, Object> config = new TreeMap<String, Object>();

       try {
           //这里的 SecretId 和 SecretKey 代表了用于申请临时密钥的永久身份（主账号、子账号等），子账号需要具有操作存储桶的权限。
           // 替换为您的云 api 密钥 SecretId
           config.put("secretId", "AKIDW1FDtJUP2PVJq78sMYU9XeU3MOAgurXx");
           // 替换为您的云 api 密钥 SecretKey
           config.put("secretKey", "A3f15sOdbIhphHacNrFMDm45oHTN0NTI");

           // 设置域名:
           // 如果您使用了腾讯云 cvm，可以设置内部域名
           //config.put("host", "sts.internal.tencentcloudapi.com");

           // 临时密钥有效时长，单位是秒，默认 1800 秒，目前主账号最长 2 小时（即 7200 秒），子账号最长 36 小时（即 129600）秒
           config.put("durationSeconds", 1800);

           // 换成您的 bucket
           config.put("bucket", "pusher-bucket-1301767316");
           // 换成 bucket 所在地区
           config.put("region", "ap-shanghai");

           // 这里改成允许的路径前缀，可以根据自己网站的用户登录态判断允许上传的具体路径
           // 列举几种典型的前缀授权场景：
           // 1、允许访问所有对象："*"
           // 2、允许访问指定的对象："a/a1.txt", "b/b1.txt"
           // 3、允许访问指定前缀的对象："a*", "a/*", "b/*"
           // 如果填写了“*”，将允许用户访问所有资源；除非业务需要，否则请按照最小权限原则授予用户相应的访问权限范围。
           config.put("allowPrefixes", new String[] {
                  allowedPath,

           });

           // 密钥的权限列表。必须在这里指定本次临时密钥所需要的权限。
           // 简单上传、表单上传和分块上传需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
           String[] allowActions = new String[] {
                   // 简单上传
                   "name/cos:PutObject",
                   // 表单上传、小程序上传
                   "name/cos:PostObject",
                   // 分块上传
                   "name/cos:InitiateMultipartUpload",
                   "name/cos:ListMultipartUploads",
                   "name/cos:ListParts",
                   "name/cos:UploadPart",
                   "name/cos:CompleteMultipartUpload",
                   "name/cos:GetObject",
                   "name/cos:HeadBucket",
                   "name/cos:GetBucket"

           };
           config.put("allowActions", allowActions);

           Response response = CosStsClient.getCredential(config);

           return new COSResponse(response.credentials.tmpSecretId,response.credentials.tmpSecretKey,response.credentials.sessionToken, 1800L);
       } catch (Exception e) {
           e.printStackTrace();
           throw new IllegalArgumentException("no valid secret !");
       }
   }
   public String getFileURL(String path){
       COSCredentials cred = new AnonymousCOSCredentials();

// ClientConfig 中包含了后续请求 COS 的客户端设置：
       ClientConfig clientConfig = new ClientConfig();

// 设置 bucket 的地域
// COS_REGION 请参照 https://cloud.tencent.com/document/product/436/6224
       clientConfig.setRegion(new Region("COS_REGION"));

// 设置生成的 url 的请求协议, http 或者 https
// 5.6.53 及更低的版本，建议设置使用 https 协议
// 5.6.54 及更高版本，默认使用了 https
       clientConfig.setHttpProtocol(HttpProtocol.https);

// 生成cos客户端
       COSClient cosclient = new COSClient(cred, clientConfig);

// 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
       String bucketName = "examplebucket-1250000000";
// 对象键(Key)是对象在存储桶中的唯一标识。详情请参见 [对象键](https://cloud.tencent.com/document/product/436/13324)
       String key = "exampleobject";

       System.out.println(cosclient.getObjectUrl(bucketName, key));
       return String.valueOf(cosclient.getObjectUrl(bucketName, key));
   }
}

// 根据 github 提供的 maven 集成方法导入 java sts sdk，使用 3.1.0 及更高版本
