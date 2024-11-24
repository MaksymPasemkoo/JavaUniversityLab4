package com.ltp.javauniversitylab4.controller;

import com.ltp.javauniversitylab4.dto.request.ClientRequestDto;
import com.ltp.javauniversitylab4.dto.response.ClientResponseDto;
import com.ltp.javauniversitylab4.model.Client;
import com.ltp.javauniversitylab4.service.ClientService;
import com.ltp.javauniversitylab4.utils.ClientHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/client")
    public ResponseEntity<String> addClient(@RequestBody ClientRequestDto clientDto) {
        clientService.addOrUpdateClient(clientDto);
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<ClientResponseDto> findClientById(@PathVariable Long id) {
        Client client = clientService.findClientById(id);
        ClientResponseDto clientResponseDto = ClientHelper.convertToResponseDto(client);
        if (client.getClientId() != -1) {
            return new ResponseEntity<>(clientResponseDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/client")
    public ResponseEntity<String> updateClient(@RequestBody ClientRequestDto clientDto) {
        clientService.addOrUpdateClient(clientDto);
        return new ResponseEntity<>("Updated", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable Long id) {
        Client client = clientService.findClientById(id);
        if (client != null) {
            clientService.deleteClientById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientResponseDto>> findAllClients() {
        List<Client> clients = clientService.findAllClients();

        List<ClientResponseDto> clientResponseDtos = clients.stream()
                .map(ClientHelper::convertToResponseDto)
                .toList();
        if (!clientResponseDtos.isEmpty()) {
            return new ResponseEntity<>(clientResponseDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
