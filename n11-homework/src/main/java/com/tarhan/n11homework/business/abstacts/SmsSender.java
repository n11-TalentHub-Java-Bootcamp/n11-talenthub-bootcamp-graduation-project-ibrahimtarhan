package com.tarhan.n11homework.business.abstacts;

import com.tarhan.n11homework.business.conretes.SmsRequest;

public interface SmsSender {
    void sendSms(SmsRequest smsRequest);
    // or maybe void sendSms(String phoneNumber, String message);
}
