package com.mfelton.Service;

import com.mfelton.Repository.PanierRepository;
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
public class PanierServiceTest {

    @Mock
    private PanierRepository panierRepository;

    @InjectMocks
    private PanierService panierService;

    @Test
    public void testAddPanier() {
        // Arrange
        Panier expected = getPanier();
        when(panierRepository.save(expected)).thenReturn(expected);

        // Act
        Optional<Panier> returned = panierService.addPanier(expected);

        // Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testGetAllPaniers() {
        // Arrange
        List<Panier> expected =
                getPaniers();
        when(panierRepository.findAll()).thenReturn(expected);

        // Act
        List<Panier> returned = panierService.getAllPaniers();

        // Assert
        assertThat(returned)
                .hasSize(3);
    }

    @Test
    public void testFindPanierByClientId() {
        // Arrange
        Panier expected = getPanier();
        when(panierRepository.findPanierByClientId(expected.getId()))
                .thenReturn(expected);

        // Act
        Optional<Panier> returned = panierService.findClienById(expected.getId());

        // Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    Client client = new Client("Mathieu","Felton","test@gmail.com","Test1234","123 rue test","51484593848","Quebec","Montreal");

    Fromage fromage = new Fromage("Chevre",12.95,"test",100, Base64.getDecoder().decode("test"));

    private List<Fromage> getFromages() {
        return List.of(fromage,fromage,fromage);
    }

    private Panier getPanier() {
        return new Panier(0,0,0,client,getFromages());
    }

    private List<Panier> getPaniers() {
        return List.of(getPanier(),getPanier(),getPanier());
    }

}
