package com.scaler.payment.paymentService.services;

import com.scaler.payment.paymentService.dtos.PaymentDto;
import com.scaler.payment.paymentService.dtos.TicketResponse;
import com.scaler.payment.paymentService.enums.PaymentStatus;
import com.scaler.payment.paymentService.models.Payment;
import com.scaler.payment.paymentService.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    @Autowired
    private RestTemplate restTemplate;

    PaymentService(PaymentRepository movieRepository){
        this.paymentRepository= movieRepository;
    }

    public String completePayment(Long ticket_id) {
        String paymentStatus;
        //Get the totalAmount from the bookmymovie service
        Payment paymentDto=new Payment();

        paymentDto.setAmount(getTicketPrice(ticket_id));

        //CreditCardPayment cardPayment=new CreditCardPayment();


        paymentStatus= String.valueOf(PaymentStatus.COMPLETED);
        paymentDto.setStatus(paymentStatus);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        paymentDto.setCreatedOn(formatter.format(date));

        paymentDto.setTicketId(ticket_id);
        paymentRepository.save(paymentDto);

    return paymentStatus;
    }

    public double getTicketPrice(Long id) {
        TicketResponse ticketResponse = restTemplate.getForObject("http://localhost:8081/ticket/{id}", TicketResponse.class, id);
        return ticketResponse.getTotalPrice();
    }

}
