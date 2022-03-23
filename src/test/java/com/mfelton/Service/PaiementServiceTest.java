package com.mfelton.Service;

import com.mfelton.Repository.PaiementRepository;
import com.mfelton.model.Client;
import com.mfelton.model.Fromage;
import com.mfelton.model.Paiement;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaiementServiceTest {

    @Mock
    private PaiementRepository paiementRepository;

    @InjectMocks
    private PaiementService paiementService;

    @Test
    public void testAddPaiement() {
        // Arrange
        Paiement expected = getPaiement();
        when(paiementRepository.save(expected)).thenReturn(expected);

        // Act
        Optional<Paiement> returned = paiementService.addPaiement(expected);

        // Assert
        assertThat(returned).isEqualTo(Optional.of(expected));
    }

    @Test
    public void testGetAllPaiements() {
        // Arrange
        List<Paiement> expected =
                getPaiements();
        when(paiementRepository.findAll()).thenReturn(expected);

        // Act
        List<Paiement> returned = paiementService.getAllPaiements();

        // Assert
        assertThat(returned)
                .hasSize(3);
    }

    @Test
    public void testFindPaiementByClientEmail() {
        // Arrange
        List<Paiement> listPaiements = getPaiements();
        when(paiementRepository.findPaiementByClientCourriel(any(String.class)))
                .thenReturn(listPaiements);

        // Act
        List<Paiement> returned = paiementService.findPaiementByCourriel(getClient().getCourriel());

        // Assert
        assertThat(returned).isEqualTo(listPaiements);
    }

    @Test
    void testDeletePaiement() {
        // Arrange
        Paiement expected = getPaiement();
        expected.setId(1);
        doNothing().when(paiementRepository).deleteById(anyInt());

        // Act
        paiementService.deletePaiement(expected.getId());

        // Assert
        verify(paiementRepository).deleteById(anyInt());
    }

    Fromage fromage = new Fromage("Chevre",12.95,"test",100, Base64.getDecoder().decode("test"));

    private List<Fromage> getFromages() {
        return List.of(fromage,fromage,fromage);
    }

    private Panier getPanier() {
        return new Panier(0,0,getFromages());
    }

    private Client getClient() {
        return new Client("Mathieu","Felton","test@gmail.com","Test1234","123 rue test","51484593848","Quebec","Montreal",getPanier());
    }

    private Paiement getPaiement() {
        return new Paiement("VISA",2320323232L,"02/25","Mathieu Felton",123,"J6J5S2",getClient());
    }

    private List<Paiement> getPaiements() {
        return List.of(getPaiement(),getPaiement(),getPaiement());
    }



}
