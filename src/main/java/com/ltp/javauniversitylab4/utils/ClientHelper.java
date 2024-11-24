package com.ltp.javauniversitylab4.utils;

import com.ltp.javauniversitylab4.dto.response.ClientResponseDto;
import com.ltp.javauniversitylab4.model.Client;

public class ClientHelper {
    public static ClientResponseDto convertToResponseDto(final Client client) {
        final Long id = client.getClientId();
        final String name = client.getName();
        final double balance = client.getBalance();
        return new ClientResponseDto(id, name, balance);
    }

}
