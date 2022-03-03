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
        Client expected = getClient();
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
                getClients();
        when(clientRepository.findAll()).thenReturn(expected);

        // Act
        List<Client> returned = clientService.getAllClients();

        // Assert
        assertThat(returned)
                .hasSize(3);
    }

    @Test
    public void testLoginClient() {
        // Arrange
        Client expected = getClient();
        when(clientRepository.findClientByCourrielIgnoreCaseAndPassword(
                expected.getCourriel(), expected.getPassword()))
                .thenReturn(expected);

        // Act
        Optional<Client> returned = clientService.login(expected.getCourriel(), expected.getPassword());

        // Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testFindClientByEmail() {
        // Arrange
        Client expected = getClient();
        when(clientRepository.findClientByCourrielIgnoreCase(expected.getCourriel()))
                .thenReturn(expected);

        // Act
        Optional<Client> returned = clientService.findClientByCourriel(expected.getCourriel());

        // Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }






    Fromage fromage = new Fromage("Chevre",12.95,"test",100, Base64.getDecoder().decode("test"));

    private List<Fromage> getFromages() {
        return List.of(fromage,fromage,fromage);
    }

    private Panier getPanier() {
        return new Panier(0,0,0,getFromages());
    }

//    private List<Panier> getPaniers() {
//        return List.of(getPanier(),getPanier(),getPanier());
//    }

    private Client getClient() {
        return new Client("Mathieu","Felton","test@gmail.com","Test1234","123 rue test","51484593848","Quebec","Montreal",getPanier());
    }

    private List<Client> getClients(){
        return List.of(getClient(),getClient(),getClient());
    }
}
