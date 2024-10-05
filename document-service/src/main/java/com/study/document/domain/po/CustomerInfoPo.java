package com.study.document.domain.po;

import java.util.Date;

/**
 * @program: EasyPOI
 * @description: 客户信息表
 * @author: 心念
 * @create: 2024-09-17 23:09
 **/
public class CustomerInfoPo {
    private Long id;

    private String landlordName;

    private String landlordIdNumber;

    private String landlordAddress;

    private String landlordPhone;

    private String tenantName;

    private String tenantIdNumber;

    private String tenantAddress;

    private String tenantPhone;

    private String houseAddress;

    private Long houseArea;

    private String houseLayout;

    private Date rentStartDate;

    private Date rentEndDate;

    private Long rentAmount;

    private String paymentFrequency;

    private Long deposit;
}
