package com.bloodbank.bloodbankapp.DTO.Request;

import com.bloodbank.bloodbankapp.Entity.Appoitment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDTO {
    private int pageNo;
    private int totalPages;
    private long totalItems;
    private List<Appoitment> appoitmentList;
}
