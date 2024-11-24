package com.ltp.javauniversitylab4.utils;

import com.ltp.javauniversitylab4.dto.request.ClientRequestDto;
import com.ltp.javauniversitylab4.dto.response.ClientResponseDto;
import com.ltp.javauniversitylab4.model.Client;


public class ClientHelper {
    public static ClientResponseDto convertToResponseDto(Client client){
        Long id = client.getClientId();
        String name = client.getName();
        double balance = client.getBalance();
        return new ClientResponseDto(id,name,balance);
    }

}
