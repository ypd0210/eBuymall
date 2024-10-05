package com.study.document.service.impl;

import cn.afterturn.easypoi.word.WordExportUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.document.domain.dto.CustomerInfo;
import com.study.document.domain.po.CustomerInfoPo;
import com.study.document.mapper.DocumentMapper;
import com.study.document.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: eBuymall
 * @description: 文档实现类
 * @author: 心念
 * @create: 2024-09-18 11:09
 **/
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, CustomerInfoPo> implements DocumentService {

    @Override
    public String getDocument(Long id) throws Exception {
        //获取模板文档
        File rootFile = new File(ResourceUtils.getURL("classPath").getPath());
        File tempFile = new File(rootFile, "contractTemp/contractTemp.docx");

        //获取数据
        CustomerInfoPo customerInfoPo = getById(id);
        System.out.println(customerInfoPo);
        CustomerInfo customerInfo = new CustomerInfo();
        BeanUtils.copyProperties(customerInfoPo,customerInfo);
        Map<String, Object> map = new HashMap<>();
        map.put("id", customerInfo.getId());
        map.put("landlordName", customerInfo.getLandlordName());
        map.put("landlordIdNumber", customerInfo.getLandlordIdNumber());
        map.put("landlordAddress", customerInfo.getLandlordAddress());
        map.put("landlordPhone", customerInfo.getLandlordPhone());
        map.put("tenantName", customerInfo.getTenantName());
        map.put("tenantIdNumber", customerInfo.getTenantIdNumber());
        map.put("tenantAddress", customerInfo.getTenantAddress());
        map.put("tenantPhone", customerInfo.getTenantPhone());
        map.put("houseAddress", customerInfo.getHouseAddress());
        map.put("houseArea", customerInfo.getHouseArea());
        map.put("houseLayout", customerInfo.getHouseLayout());
        map.put("rentStartDate", customerInfo.getRentStartDate());
        map.put("rentEndDate", customerInfo.getRentEndDate());
        map.put("rentAmount", customerInfo.getRentAmount());
        map.put("paymentFrequency", customerInfo.getPaymentFrequency());
        map.put("deposit", customerInfo.getDeposit());

        //将数据导入模板
        XWPFDocument contractDocument = WordExportUtil.exportWord07(tempFile.getPath(), map);

        //导出word
        String contractName = customerInfo.getTenantName() + "租房合同.docx";
        contractDocument.write(new FileOutputStream(contractName));

        return "success";
    }
}
