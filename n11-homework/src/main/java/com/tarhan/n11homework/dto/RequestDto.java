package com.tarhan.n11homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    private String nameSurname;
    private Double monthlyIncome;
    private String phoneNumber;
    private LocalDate birthDate;
    private Long identityNumber;
    private Double guarantee;
}
