package com.onxshield.invoiceyou.bankstatement.controller;

import com.onxshield.invoiceyou.bankstatement.service.statementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statements")
public class statementController {

    private final statementService statementService;


}
