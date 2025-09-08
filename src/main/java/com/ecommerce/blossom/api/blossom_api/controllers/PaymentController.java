package com.ecommerce.blossom.api.blossom_api.controllers;

import com.ecommerce.blossom.api.blossom_api.dtos.ManualPaymentRequestDTO;
import com.ecommerce.blossom.api.blossom_api.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<String> processManualPayment(@Valid @RequestBody ManualPaymentRequestDTO request) {
        paymentService.processManualPayment(request.getOrderId(), request.getSuccess());
        return ResponseEntity.ok("Payment processed successfully");
    }
}
