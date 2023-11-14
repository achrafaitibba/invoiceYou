package com.onxshield.invoiceyou.invoicestatement.service;

import com.onxshield.invoiceyou.invoicestatement.dto.request.clientRequest;
import com.onxshield.invoiceyou.invoicestatement.dto.response.clientResponse;
import com.onxshield.invoiceyou.invoicestatement.model.client;
import com.onxshield.invoiceyou.invoicestatement.repository.clientRepository;
import com.onxshield.invoiceyou.invoicestatement.exceptions.requestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class clientService {

    private final clientRepository clientRepository;


    public clientResponse createClient(clientRequest request) {
        client toSave = clientRepository.save(
                client
                        .builder()
                        .name(request.name())
                        .ICE(request.ICE() == null ? 0 : request.ICE())
                        .build());
        return new clientResponse(toSave.getClientId(),toSave.getName(),toSave.getICE());
    }

    public List<clientResponse> allClients() {
        return clientRepository.findAll().stream().map(
                client -> new clientResponse(
                        client.getClientId(),
                        client.getName(),
                        client.getICE()
                        )
        ).toList();
    }

    public clientResponse updateClient(Long id, clientRequest request) {
        Optional<client> toUpdate = clientRepository.findById(id);
        if(toUpdate.isPresent()){
             toUpdate.get().setName(request.name());
             toUpdate.get().setICE(request.ICE());
             clientRepository.save(toUpdate.get());
             return new clientResponse(id, request.name(), request.ICE());
        }
        else {
            throw new requestException("The id you provided doesn't exist.", HttpStatus.CONFLICT);
        }
    }

    public Integer deleteClient(Long id) {
        Optional<client> toDelete = clientRepository.findById(id);
        if(toDelete.isPresent()){
            clientRepository.deleteById(id);
            return 1;
        }else {
            throw new requestException("The id you provided doesn't exist.", HttpStatus.CONFLICT);
        }
    }
}
