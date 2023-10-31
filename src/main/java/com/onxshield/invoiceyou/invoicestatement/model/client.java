package com.onxshield.invoiceyou.invoicestatement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class client {

    @Id
    @Column(name = "client_id")
    private UUID clientId;
    @NotBlank
    private String name;
    private String ICE;

}
