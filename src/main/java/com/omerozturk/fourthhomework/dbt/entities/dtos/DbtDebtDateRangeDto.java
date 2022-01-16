package com.omerozturk.fourthhomework.dbt.entities.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class DbtDebtDateRangeDto {
    private Date startDate;
    private Date endDate;
}
