package top.hhhhhgx.blog.controller;

import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hhhhhgx.blog.common.constants.CommonConstants;
import top.hhhhhgx.blog.common.result.Result;
import top.hhhhhgx.blog.common.result.ResultCode;
import top.hhhhhgx.blog.common.result.ResultUtil;
import top.hhhhhgx.blog.utils.AliyunSMSUtils;
import top.hhhhhgx.blog.utils.CommonUtils;
import top.hhhhhgx.blog.utils.RedisUtils;

/**
 * @program: blog
 * @description: 短信服务
 * @author: hgx
 * @create: 2020-02-04 20:54
 **/
@RestController
@RequestMapping("/sms")
@Slf4j
public class SmsController {

    @Autowired
    private RedisUtils redisUtils;

    /*验证码码过期时间*/
    private final long time = 3 * 60;

    /***
     * @Description: 获取缓存key
     * @Param: [phone]
     * @return: java.lang.String
     * @Author: hgx
     * @Date: 2020/2/4
     */
    public static String getRedisKey(String phone) {
        return CommonConstants.SMSCODE_REDISKEY + phone;
    }

    /***
     * @Description: 发送验证码
     * @Param: [phone]
     * @return: top.hhhhhgx.blog.common.result.Result
     * @Author: hgx
     * @Date: 2020/2/4
     */
    @RequestMapping(value = "/{phone}")
    public Result sendSms(@PathVariable("phone") String phone) throws ClientException {
        if (StringUtils.isEmpty(phone)) return ResultUtil.fail(ResultCode.FAIL,"请输入手机号码");
        String key = getRedisKey(phone);
        if (redisUtils.exists(key)) return ResultUtil.fail(ResultCode.TWO,"已发送过验证码");
        int rdmNum = CommonUtils.getRandom();
        AliyunSMSUtils.AliyunSMSResponse aliyunSMSResponse = AliyunSMSUtils.send(phone, String.valueOf(rdmNum));
        if ("OK".equals(aliyunSMSResponse.getCode())) {
            boolean l = redisUtils.set(key, rdmNum, time);
            log.info("sendSms-->发送验证码成功;手机号码是:{},返回参数:{},redisKey:{}", phone, aliyunSMSResponse.toString(), key);
            return ResultUtil.success();
        }
        log.error("sendSms-->发送验证码失败;手机号码是:{},返回参数:{}", phone, aliyunSMSResponse.toString());
        return ResultUtil.fail("发送验证码失败");
    }

}
