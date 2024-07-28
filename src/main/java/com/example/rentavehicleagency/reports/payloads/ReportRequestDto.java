package com.example.rentavehicleagency.reports.payloads;

import com.example.rentavehicleagency.reports.entities.PriorityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequestDto {
    private String title;
    private String content;
    private PriorityType priority;
    private Long userId;
}
