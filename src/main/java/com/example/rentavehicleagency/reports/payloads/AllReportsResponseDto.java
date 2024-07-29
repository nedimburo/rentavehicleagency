package com.example.rentavehicleagency.reports.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllReportsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String priority;
    private String creationDate;
    private ReportEmployeeDto employee;
}
