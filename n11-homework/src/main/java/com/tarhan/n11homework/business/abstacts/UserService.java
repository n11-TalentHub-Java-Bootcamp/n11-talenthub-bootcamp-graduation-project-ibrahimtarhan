package com.tarhan.n11homework.business.abstacts;

import com.tarhan.n11homework.dto.RequestCreditInfo;
import com.tarhan.n11homework.dto.RequestDto;
import com.tarhan.n11homework.dto.ResponseDto;
import com.tarhan.n11homework.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User save(User user);
    void delete(Long userId);
    User getById(Long userId);
    ResponseDto calculateCreditInfo(RequestDto userDto);
    ResponseDto getCreditInfo(RequestCreditInfo requestCreditInfo);

}

