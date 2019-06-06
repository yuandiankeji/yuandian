package com.yuandian.service.impl;

import com.yuandian.service.PhoneMsgService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PhoneMsgServiceImpl implements PhoneMsgService {
    private static final String sendMsgUrl = "http://client.cloud.hbsmservice.com:8080/sms_send2.do";
    private static final String corp_id = "xn0528";
    private static final String corp_pwd = "xnp134";
    private static final String corp_service = "10691587822839";


    @Override
    public boolean sendPhoneMsg(String phoneNum, String content) {
        OkHttpClient okHttpClient = new OkHttpClient(); // OkHttpClient对象
        RequestBody formBody = new FormBody.Builder().add("corp_id", corp_id)
                                                     .add("corp_pwd", corp_pwd)
                                                     .add("corp_service", corp_service)
                                                     .add("mobile", phoneNum)
                                                     .add("msg_content", content).build(); // 表单键值对
        Request request = new Request.Builder().addHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk").url(sendMsgUrl).post(formBody).build(); // 请求
        okHttpClient.newCall(request).enqueue(new Callback() {// 回调

            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());//成功后的回调
            }

            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());//失败后的回调
            }
        });
        return true;
    }
}
