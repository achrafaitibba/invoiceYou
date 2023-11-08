package com.onxshield.invoiceyou.invoicestatement.service;

import com.onxshield.invoiceyou.invoicestatement.model.client;
import com.onxshield.invoiceyou.invoicestatement.repository.clientRepository;
import com.onxshield.invoiceyou.invoicestatement.request.clientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class clientService {

    private final clientRepository repository;

    public client addClient(clientRequest request) {
        var toSave = client.builder()
                .name(request.name())
                .ICE(Long.valueOf(request.ICE()))
                .build();
        return repository.save(toSave);
    }
}
