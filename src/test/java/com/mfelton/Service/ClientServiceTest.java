package com.mfelton.Service;

import com.mfelton.Repository.ClientRepository;
import com.mfelton.model.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void testGetAllClientsAllSession() {
        // Arrange
        List<Client> expected =
                getClients();
        when(clientRepository.findAll()).thenReturn(expected);

        // Act
        List<Client> returned = clientService.getAllClients();

        // Assert
        assertThat(returned)
                .hasSize(3);
    }

    private Client getClient() {
        return new Client("Mathieu","Felton","test@gmail.com","Test1234","123 rue test","51484593848","Quebec","Montreal");
    }

    private List<Client> getClients(){
        return List.of(getClient(),getClient(),getClient());
    }
}
