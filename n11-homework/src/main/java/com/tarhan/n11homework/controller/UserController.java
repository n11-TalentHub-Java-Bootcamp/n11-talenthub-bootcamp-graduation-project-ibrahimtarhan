package com.tarhan.n11homework.controller;

import com.tarhan.n11homework.business.abstacts.UserService;
import com.tarhan.n11homework.business.conretes.SmsRequest;
import com.tarhan.n11homework.business.conretes.SmsService;
import com.tarhan.n11homework.dto.RequestCreditInfo;
import com.tarhan.n11homework.dto.RequestDto;
import com.tarhan.n11homework.dto.ResponseDto;
import com.tarhan.n11homework.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/users/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private  SmsService smsService;

//    public UserController(SmsService smsService) {
//        this.smsService = smsService;
//    }

    @GetMapping("")
    public List<User> getAll(){
        return userService.getAll();
    }
    @PostMapping("add")
    public User AddUser(@RequestBody User user){
        return userService.save(user);
    }
    @PostMapping("calculate")
    public ResponseDto CalculateCredit(@RequestBody RequestDto user){
        return userService.calculateCreditInfo(user);
    }
    @DeleteMapping("/{id}")
    public void delete(Long id){
        userService.delete(id);
    }

    @PostMapping("getCreditInfo")
    public ResponseDto getCreditInfo(@RequestBody RequestCreditInfo requestCreditInfo){
        return userService.getCreditInfo(requestCreditInfo);
    }

    @PostMapping("sendsms")
    public void sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        smsService.sendSms(smsRequest);
    }
}
