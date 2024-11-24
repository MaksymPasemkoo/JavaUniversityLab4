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
    private final ClientRepository clientRepository;

    public ClientService(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void addOrUpdateClient(final ClientRequestDto clientDto) {
        final String name = clientDto.getName();
        final double balance = clientDto.getBalance();
        final Client client = new Client(name, balance);
        clientRepository.save(client);
    }

    public Client findClientById(final Long id) {
        return clientRepository.findById(id).orElse(new Client(-1L));
    }

    public void deleteClientById(final Long id) {
        clientRepository.deleteById(id);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

}
