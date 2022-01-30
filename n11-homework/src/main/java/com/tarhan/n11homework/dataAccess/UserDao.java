package com.tarhan.n11homework.dataAccess;

import com.tarhan.n11homework.dto.ResponseDto;
import com.tarhan.n11homework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    @Query("select user from User user where user.identityNumber = :identityNumber")
    User findUserByIdentityNumber(@Param("identityNumber") Long identityNumber);

    @Query("select user from User user where user.identityNumber = :identityNumber and user.birthDate = :birthDate")
    User findCreditByIdentityAndBirthDate(@Param("identityNumber") Long identityNumber, @Param("birthDate") LocalDate birthDate);
}
