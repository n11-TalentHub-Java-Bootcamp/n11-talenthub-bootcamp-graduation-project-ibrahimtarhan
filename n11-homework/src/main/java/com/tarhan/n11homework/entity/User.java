package com.tarhan.n11homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameSurname;
    private Double monthlyIncome;
    private String phoneNumber;
    private LocalDate birthDate;
    private Long identityNumber;
    private Double guarantee;
    private Boolean isApprove;
    private Long creditScore;
    private Double creditAmount;






}
