package com.etiya.ecommercedemopair1.business.dtos.request.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AddInvoiceRequest {
    private LocalDateTime invoiceDate;
    private double totalInvoicePrice;
    private int orderId;
}
