package com.unitri.ppi2v1.service;

import com.unitri.ppi2v1.domain.Cliente;
import com.unitri.ppi2v1.repository.ClienteRepository;
import com.unitri.ppi2v1.service.exception.ClienteAlreadyExistsException;
import com.unitri.ppi2v1.service.exception.ClienteNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente createCliente(Cliente cliente) {
        verifyIfClienteExists(cliente);
        return this.clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        Optional<Cliente> clienteById = this.clienteRepository.findById(id);
        Cliente clienteToDelete = clienteById.orElseThrow(ClienteNotFoundException::new);
        this.clienteRepository.delete(clienteToDelete);
    }

    public Cliente findById(Long id) {
        return this.clienteRepository.findById(id).orElseThrow(ClienteNotFoundException::new);
    }

    public List<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }

    private void verifyIfClienteExists(Cliente cliente) {
        Optional<Cliente> byCpf = this.clienteRepository.findByCpf(cliente.getCpf());
        if (byCpf.isPresent() && (cliente.isNew() || isUpdatingToADifferentClient(cliente, byCpf.get()))) {
            throw new ClienteAlreadyExistsException();
        }
    }

    private boolean isUpdatingToADifferentClient(Cliente cliente, Cliente byCpf) {
        return cliente.isUpdate() && !cliente.getId().equals(byCpf.getId());
    }


}
