package com.etiya.ecommercedemopair1.business.concretes;

import com.etiya.ecommercedemopair1.business.abstracts.InvoiceService;
import com.etiya.ecommercedemopair1.business.dtos.request.invoice.AddInvoiceRequest;
import com.etiya.ecommercedemopair1.core.util.mapping.ModelMapperService;
import com.etiya.ecommercedemopair1.core.util.results.Result;
import com.etiya.ecommercedemopair1.core.util.results.SuccessDataResult;
import com.etiya.ecommercedemopair1.core.util.results.SuccessResult;
import com.etiya.ecommercedemopair1.entities.concretes.Invoice;
import com.etiya.ecommercedemopair1.repository.abstracts.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class InvoiceManager implements InvoiceService {
    private InvoiceRepository invoiceRepository;
    private ModelMapperService modelMapperService;
    @Autowired
    public InvoiceManager(InvoiceRepository invoiceRepository,ModelMapperService modelMapperService) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapperService=modelMapperService;
    }




    @Override
    public Result addInvoice(Invoice invoice) {
        Invoice invoice1=new Invoice();
        invoice1.setInvoiceDate(invoice.getInvoiceDate());
        invoice1.setTotalInvoicePrice(invoice.getTotalInvoicePrice());
        invoice1.setOrder(invoice.getOrder());
        Invoice savedInvoice=invoiceRepository.save(invoice1);
        return new SuccessResult("Invoice was created succesfully");
    }

    @Override
    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }
}
