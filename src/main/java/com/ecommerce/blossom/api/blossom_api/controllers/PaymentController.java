package com.ecommerce.blossom.api.blossom_api.controllers;

import com.ecommerce.blossom.api.blossom_api.dtos.ManualPaymentRequestDTO;
import com.ecommerce.blossom.api.blossom_api.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Payments", description = "Endpoints for managing payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Process a manual payment")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment processed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid payment request")
    })
    @PostMapping("/process")
    public ResponseEntity<String> processManualPayment(@Valid @RequestBody ManualPaymentRequestDTO request) {
        paymentService.processManualPayment(request.getOrderId(), request.getSuccess());
        return ResponseEntity.ok("Payment processed successfully");
    }
}
