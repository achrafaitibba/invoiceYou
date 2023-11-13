package com.onxshield.invoiceyou.invoicestatement.controller;

import com.onxshield.invoiceyou.invoicestatement.dto.request.clientRequest;
import com.onxshield.invoiceyou.invoicestatement.dto.response.clientResponse;
import com.onxshield.invoiceyou.invoicestatement.service.clientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class clientController {
    private final clientService clientService;
    @PostMapping("/create")
    public ResponseEntity<clientResponse> createClient(@RequestBody clientRequest request){
        return ResponseEntity.ok(clientService.createClient(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<clientResponse> updateClient(@PathVariable Long id,
                                                       @RequestBody clientRequest request){
        return ResponseEntity.ok(clientService.updateClient(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteClient(@PathVariable Long id){
        return ResponseEntity.ok(clientService.deleteClient(id));
    }
    @GetMapping()
    public ResponseEntity<List<clientResponse>> allClient(){
        return ResponseEntity.ok(clientService.allClients());
    }

}
