package com.tarhan.n11homework.business.conretes;

import com.tarhan.n11homework.business.abstacts.UserService;
import com.tarhan.n11homework.dataAccess.UserDao;
import com.tarhan.n11homework.dto.RequestCreditInfo;
import com.tarhan.n11homework.dto.RequestDto;
import com.tarhan.n11homework.dto.ResponseDto;
import com.tarhan.n11homework.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserManager implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private  SmsService smsService;

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public void delete(Long userId) {
        User user = userDao.getById(userId);
        userDao.delete(user);
    }

    @Override
    public User getById(Long userId) {
        return userDao.getById(userId);
    }
    public Long generateCreditScore(Long identity){
        //Random rand = new Random();
        if(identity%10 == 0){
            return ThreadLocalRandom.current().nextLong(1000,2000);

        }
        else if(identity%10 == 2 || identity%10 == 4){
            return ThreadLocalRandom.current().nextLong(500);
        }
        else if(identity%10 == 6 || identity%10 == 8){
            return ThreadLocalRandom.current().nextLong(500,1000);
        }
        return 1L;
    }

    @Override
    public ResponseDto calculateCreditInfo(RequestDto userDto) {
        Double creditLimitCoefficient = 4D;
        Long creditScore = generateCreditScore(userDto.getIdentityNumber());
        User requestUser = userDao.findUserByIdentityNumber(userDto.getIdentityNumber());
        if(requestUser == null){
            requestUser = new User();
        }

        requestUser.setMonthlyIncome(userDto.getMonthlyIncome());
        requestUser.setBirthDate(userDto.getBirthDate());
        requestUser.setIdentityNumber(userDto.getIdentityNumber());
        requestUser.setGuarantee(userDto.getGuarantee());
        requestUser.setNameSurname(userDto.getNameSurname());
        requestUser.setPhoneNumber(userDto.getPhoneNumber());
        if(creditScore < 500){
            requestUser.setIsApprove(false);
            requestUser.setCreditAmount(0D);

        }
        else if(creditScore >= 500 && creditScore < 1000){
            requestUser.setIsApprove(true);
            if(requestUser.getMonthlyIncome()>0 && requestUser.getMonthlyIncome()<5000){
                requestUser.setCreditAmount(10000D + requestUser.getGuarantee()/10);

            }
            else if(requestUser.getMonthlyIncome()>=5000 && requestUser.getMonthlyIncome()<10000){
                requestUser.setCreditAmount(20000D + requestUser.getGuarantee()/5);

            }
            else if(requestUser.getMonthlyIncome()>=10000){
                requestUser.setCreditAmount((requestUser.getMonthlyIncome()*creditLimitCoefficient/2) +requestUser.getGuarantee()/4);

            }

        }
        else if(creditScore >= 1000){
            requestUser.setIsApprove(true);
            requestUser.setCreditAmount(requestUser.getMonthlyIncome()*creditLimitCoefficient + requestUser.getGuarantee()/2);
        }
        User saveUser = save(requestUser);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setCreditAmount(requestUser.getCreditAmount());
        responseDto.setIsApprove(requestUser.getIsApprove());
        String messsage = "Kredi başvurunuz :";
        if(requestUser.getIsApprove() == false){
            messsage += "Reddedildi";
        }
        else{
            messsage += "Onaylandı, çekebileceğiniz kredi miktarı" + requestUser.getCreditAmount();
        }

        SmsRequest smsRequest = new SmsRequest(requestUser.getPhoneNumber(),messsage);

        smsService.sendSms(smsRequest);
        return responseDto;
    }

    @Override
    public ResponseDto getCreditInfo(RequestCreditInfo requestCreditInfo) {
        User user = userDao.findCreditByIdentityAndBirthDate(requestCreditInfo.getIdentityNumber(),requestCreditInfo.getBirthDate());
        ResponseDto responseDto = new ResponseDto();
        responseDto.setIsApprove(user.getIsApprove());
        responseDto.setCreditAmount(user.getCreditAmount());
        return  responseDto;
    }
}
