package com.BankingApp.mohammed.banking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Baseclass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;
    private Instant createdDate;

    private Instant lastModifiedDate;


}
