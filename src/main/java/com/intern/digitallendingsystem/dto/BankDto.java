package com.intern.digitallendingsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDto {

//    private long id;
    private String name;
    private Integer code;
    private String address;
    @JsonIgnore
    private Boolean isActive;
    private Date createdAt;
}
