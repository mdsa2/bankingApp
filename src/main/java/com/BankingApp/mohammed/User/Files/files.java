package com.BankingApp.mohammed.User.Files;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;

public class files {

    @Id
    @SequenceGenerator(name = "file_seq" , sequenceName = "file_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Image name cannot be blank")
    private String fileType;

    @NotBlank(message = "Image reference cannot be blank")
    private String fileReference;


    @NotBlank(message = "Image URL cannot be blank")
    private String fileUrl;

    public files(String fileType, String fileReference, String fileUrl) {
        this.fileType = fileType;
        this.fileReference = fileReference;
        this.fileUrl = fileUrl;
    }

    public files() {
    };
}
