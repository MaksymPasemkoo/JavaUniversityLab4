package com.ltp.javauniversitylab4.service;

import com.ltp.javauniversitylab4.dto.request.ClientRequestDto;
import com.ltp.javauniversitylab4.model.Client;
import com.ltp.javauniversitylab4.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public void addOrUpdateClient(ClientRequestDto clientDto) {
        String name = clientDto.getName();
        double balance = clientDto.getBalance();
        Client client = new Client(name,balance);
        clientRepository.save(client);
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElse(new Client(-1L));
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

}
