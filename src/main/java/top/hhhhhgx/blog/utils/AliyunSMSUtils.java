package top.hhhhhgx.blog.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: blog
 * @description: 阿里云短信工具类
 * @author: hgx
 * @create: 2020-02-03 21:31
 **/
@Data
@Slf4j
public class AliyunSMSUtils {

    private static String accessKeyId = "";

    private static String accessSecret = "";

    private static String signName = "小星星blog";

    private static DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
    private static IAcsClient client = new DefaultAcsClient(profile);

    public static AliyunSMSResponse send(CommonRequest request) throws ClientException {
        CommonResponse response = client.getCommonResponse(request);
        AliyunSMSResponse aliyunSMSResponse = JSON.parseObject(response.getData(), AliyunSMSResponse.class);
        return aliyunSMSResponse;
    }

    public static AliyunSMSResponse send(String phone, String TemplateCode, String TemplateParam) throws ClientException {
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", TemplateCode);
        request.putQueryParameter("TemplateParam", TemplateParam);
        return send(request);
    }

    public static AliyunSMSResponse send(String phone, String code) throws ClientException {
        JSONObject json = new JSONObject();
        json.put("code", code);
        return send(phone, "SMS_132991016", json.toJSONString());
    }

    @Data
    public static class AliyunSMSResponse {

        /*状态码的描述。*/
        private String Message;

        /*请求ID。*/
        private String RequestId;

        /*发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态。*/
        private String BizId;

        /*请求状态码。返回OK代表请求成功。*/
        private String Code;

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
}
