package com.study.document.controller;


import com.study.document.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: eBuymall
 * @description: 文档控制器
 * @author: 心念
 * @create: 2024-09-18 11:08
 **/
@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;
    @RequestMapping("/getDocument")
    public String getDocument(Long id) throws Exception {
        return documentService.getDocument(id);
    }
}
