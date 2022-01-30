package com.tarhan.n11homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreditInfo {
    private Long identityNumber;
    private LocalDate birthDate;
}
