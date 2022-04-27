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
import java.util.Collections;
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
        Client expected = client;
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
        Administrateur expected = administrateur;
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
        Client expected = client;
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
        Administrateur expected = administrateur;
        when(administrateurRepository.findAministrateurByCourrielIgnoreCase(expected.getCourriel()))
                .thenReturn(expected);

        // Act
        Optional<User> returned = userService.findUserByCourriel(expected.getCourriel());

        // Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    Administrateur administrateur = new Administrateur("Prenom","Nom","prenom@email.com","password","titre");

    Panier panier = new Panier(0,0, Collections.emptyList());

    Client client = new Client("Prenom","Nom","prenom@email.com","password","adresse","telephone","province","ville", panier);

}
