package com.learningenglish.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserExport {

    @CsvBindByPosition(position = 0)
    private String username;
    @CsvBindByPosition(position = 1)
    private String email;
    @CsvBindByPosition(position = 2)
    private String fullname;
    @CsvBindByPosition(position = 3)
    private String phonenumber;
    
}
