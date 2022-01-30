package com.tarhan.n11homework.business.conretes;

import com.tarhan.n11homework.business.abstacts.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
@org.springframework.stereotype.Service
public class SmsService {
    private final SmsSender smsSender;
    @Autowired
    public SmsService(@Qualifier("twilio") TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }
    public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
    }
}