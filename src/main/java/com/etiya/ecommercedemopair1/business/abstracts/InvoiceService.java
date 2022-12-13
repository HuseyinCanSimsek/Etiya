package com.etiya.ecommercedemopair1.business.abstracts;

import com.etiya.ecommercedemopair1.business.dtos.request.invoice.AddInvoiceRequest;
import com.etiya.ecommercedemopair1.core.util.results.Result;
import com.etiya.ecommercedemopair1.entities.concretes.Invoice;

import java.util.List;

public interface InvoiceService {
    Result addInvoice(Invoice invoice);
    List<Invoice> getAll();
}
