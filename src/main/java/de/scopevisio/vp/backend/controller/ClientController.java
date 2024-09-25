package de.scopevisio.vp.backend.controller;

import de.scopevisio.vp.backend.data.model.Client;
import de.scopevisio.vp.backend.service.ClientService;
import de.scopevisio.vp.backend.service.VersicherungspraemieBerechnenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final VersicherungspraemieBerechnenService versicherungspraemieBerechnenService;

    @PostMapping(value = "/create")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        if (client.getFirstname() == null || client.getLastname() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            Client clientToBeSave = clientService.createClient(client);
            return new ResponseEntity<>(clientToBeSave, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable final Long clientId) {
        return ResponseEntity.ok(clientService.getClient(clientId));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {

        client.getCars().forEach(versicherungspraemieBerechnenService::berechneVersicherungspraemie);
        return new ResponseEntity<>(clientService.updateClient(client), HttpStatus.OK);
    }

}
