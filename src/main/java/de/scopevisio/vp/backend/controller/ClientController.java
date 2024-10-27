package de.scopevisio.vp.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.scopevisio.vp.backend.data.model.Client;
import de.scopevisio.vp.backend.service.ClientService;
import de.scopevisio.vp.backend.service.VersicherungspraemieBerechnenService;
import lombok.RequiredArgsConstructor;

/**
 * This controller handles all operations related to managing Client.
 * It provides endpoints for creating, updating, and retrieving client data.
 */
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final VersicherungspraemieBerechnenService versicherungspraemieBerechnenService;

    /**
     * Create a new client.
     *
     * @param client with all information, firstname and last should not be null.
     * @return a ResponseEntity containing client object.
     */
    @PostMapping(value = "/create")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return (client.getFirstname() == null || client.getLastname() == null)
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : new ResponseEntity<>(clientService.createClient(client), HttpStatus.CREATED);
    }

    /**
     * Retrieves a client by id.
     *
     * @param clientId that needs to be retrieved
     * @return a ResponseEntity containing client object.
     */
    @GetMapping(value = "/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable final Long clientId) {
        return ResponseEntity.ok(clientService.getClient(clientId));
    }

    /**
     * Retrieves a list of all clients.
     *
     * @return a ResponseEntity containing a list of client objects
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    /**
     * Update a client.
     *
     * @param client with all new information.
     * @return a ResponseEntity containing client object.
     */
    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        client.getCars().forEach(versicherungspraemieBerechnenService::berechneVersicherungspraemie);
        return new ResponseEntity<>(clientService.updateClient(client), HttpStatus.OK);
    }

}
