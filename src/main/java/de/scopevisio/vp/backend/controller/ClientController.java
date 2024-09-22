package de.scopevisio.vp.backend.controller;

import de.scopevisio.vp.backend.data.model.Client;
import de.scopevisio.vp.backend.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping(value = "/create")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        if (client.firstname() == null
                || client.lastname() == null
                || client.firstname().isEmpty()
                || client.lastname().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return new ResponseEntity<>(
                    clientService.createClient(client.firstname(), client.lastname()),
                    HttpStatus.CREATED
            );
        }
    }

    @GetMapping(value = "/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable final Long clientId) {
        if (clientId == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        else return ResponseEntity.ok(clientService.getClient(clientId));
    }

}
