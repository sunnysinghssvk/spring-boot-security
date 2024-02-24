package com.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DEVELOPER")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EXPERIENCE")
    private String experience;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "PRIMARY_LANGUAGE")
    private String primaryLanguage;

    @Column(name = "CRT_TS")
    @CreationTimestamp(source = SourceType.VM)
    private Timestamp crtTs;
}
