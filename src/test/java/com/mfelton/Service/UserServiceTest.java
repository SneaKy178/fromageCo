package com.mfelton.Service;

import com.mfelton.Repository.AdministrateurRepository;
import com.mfelton.Repository.ClientRepository;
import com.mfelton.model.*;
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
public class UserServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private AdministrateurRepository administrateurRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testLoginClient() {
        // Arrange
        Client expected = getClient();
        when(clientRepository.findClientByCourrielIgnoreCase(expected.getCourriel()))
                .thenReturn(expected);
        when(clientRepository.findClientByCourrielIgnoreCaseAndPassword(
                expected.getCourriel(), expected.getPassword()))
                .thenReturn(expected);

        // Act
        Optional<User> returned = userService.login(expected.getCourriel(), expected.getPassword());

        // Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testLoginAdministrateur() {
        // Arrange
        Administrateur expected = getAdministrateur();
        when(administrateurRepository.findAministrateurByCourrielIgnoreCase(expected.getCourriel()))
                .thenReturn(expected);
        when(administrateurRepository.findAdministrateurByCourrielIgnoreCaseAndPassword(
                expected.getCourriel(), expected.getPassword()))
                .thenReturn(expected);

        // Act
        Optional<User> returned = userService.login(expected.getCourriel(), expected.getPassword());

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
        Optional<User> returned = userService.findUserByCourriel(expected.getCourriel());

        // Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testFindAdministrateurByEmail() {
        // Arrange
        Administrateur expected = getAdministrateur();
        when(administrateurRepository.findAministrateurByCourrielIgnoreCase(expected.getCourriel()))
                .thenReturn(expected);

        // Act
        Optional<User> returned = userService.findUserByCourriel(expected.getCourriel());

        // Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }



    Fromage fromage = new Fromage("Chevre",12.95,"test",100, Base64.getDecoder().decode("test"));

    private List<Fromage> getFromages() {
        return List.of(fromage,fromage,fromage);
    }

    private Panier getPanier() {
        return new Panier(0,0,getFromages());
    }

    private Client getClient() {
        return new Client("John","Doe","test@gmail.com","Test1234","123 rue test","51484593848","Quebec","Montreal",getPanier());
    }

    private Administrateur getAdministrateur() {
        return new Administrateur("John","Doe","test@gmail.com","Test1234","Test");
    }

}
