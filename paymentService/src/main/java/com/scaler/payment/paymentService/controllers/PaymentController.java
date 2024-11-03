package com.scaler.payment.paymentService.controllers;

import com.scaler.payment.paymentService.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;

    PaymentController(PaymentService paymentService)
    {
        this.paymentService = paymentService;
    }

    @PostMapping(path = "/{ticket_id}")
    public ResponseEntity<String> completePayment(@PathVariable Long ticket_id){
        try {
            String response;
            response = paymentService.completePayment( ticket_id);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}