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
import java.util.Collections;
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
        Paiement expected = paiement;
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
                paiements;
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
        List<Paiement> listPaiements = paiements;
        when(paiementRepository.findPaiementByClientCourriel(any(String.class)))
                .thenReturn(listPaiements);

        // Act
        List<Paiement> returned = paiementService.findPaiementByCourriel(client.getCourriel());

        // Assert
        assertThat(returned).isEqualTo(listPaiements);
    }

    @Test
    void testDeletePaiement() {
        // Arrange
        Paiement expected = paiement;
        expected.setId(1);
        doNothing().when(paiementRepository).deleteById(anyInt());

        // Act
        paiementService.deletePaiement(expected.getId());

        // Assert
        verify(paiementRepository).deleteById(anyInt());
    }

    private Panier panier = new Panier(0,0, Collections.emptyList());

    private Client client = new Client("Prenom","Nom","prenom@email.com","password","adresse","telephone","province","ville", panier);

    private Paiement paiement =  new Paiement("VISA",2320323232L,"02/25","Mathieu Felton",123,"J6J5S2",client);

    private List<Paiement> paiements =  List.of(paiement,paiement,paiement);

}
