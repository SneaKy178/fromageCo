package com.mfelton.Service;

import com.mfelton.Repository.ClientRepository;
import com.mfelton.model.Client;
import com.mfelton.model.Fromage;
import com.mfelton.model.Panier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void testAddClient() {
        // Arrange
        Client expected = client;
        when(clientRepository.save(expected)).thenReturn(expected);

        // Act
        Optional<Client> returned = clientService.addClient(expected);

        // Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testGetAllClients() {
        // Arrange
        List<Client> expected =
                clients;
        when(clientRepository.findAll()).thenReturn(expected);

        // Act
        List<Client> returned = clientService.getAllClients();

        // Assert
        assertThat(returned)
                .hasSize(3);
    }



    private Panier panier = new Panier(0,0, Collections.emptyList());

    private Client client = new Client("Prenom","Nom","prenom@email.com","password","adresse","telephone","province","ville", panier);

    private List<Client> clients = List.of(client,client,client);

}
