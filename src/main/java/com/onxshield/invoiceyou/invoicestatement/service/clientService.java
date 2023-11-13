package com.onxshield.invoiceyou.invoicestatement.service;

import com.onxshield.invoiceyou.invoicestatement.dto.request.clientRequest;
import com.onxshield.invoiceyou.invoicestatement.dto.response.clientResponse;
import com.onxshield.invoiceyou.invoicestatement.model.client;
import com.onxshield.invoiceyou.invoicestatement.repository.clientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class clientService {

    private final clientRepository clientRepository;


    public clientResponse createClient(clientRequest request) {
        client toSave = clientRepository.save(
                client
                        .builder()
                        .name(request.name())
                        .ICE(request.ICE())
                        .build());
        return new clientResponse(toSave.getClientId(),toSave.getName(),toSave.getICE());
    }
}
