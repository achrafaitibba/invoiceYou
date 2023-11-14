package com.onxshield.invoiceyou.invoicestatement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class client {

    @Id
    @Column(name = "client_id")
    @GeneratedValue()
    private Long clientId;
    @NotNull
    private String name;
    private Long ICE;

}
