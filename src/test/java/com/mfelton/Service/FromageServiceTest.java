package com.mfelton.Service;

import com.mfelton.Repository.FromageRepository;
import com.mfelton.model.Client;
import com.mfelton.model.Fromage;
import com.mfelton.model.Paiement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Base64;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FromageServiceTest {

    @Mock
    private FromageRepository fromageRepository;

    @InjectMocks
    private FromageService fromageService;


    @Test
    public void testGetAllFromages() {
        // Arrange
        List<Fromage> expected =
                getFromages();
        when(fromageRepository.findAll()).thenReturn(expected);

        // Act
        List<Fromage> returned = fromageService.getAllFromages();

        // Assert
        assertThat(returned)
                .hasSize(3);
    }

    @Test
    void testDeleteFromage() {
        // Arrange
        Fromage expected = getFromage();
        expected.setId(1);
        doNothing().when(fromageRepository).deleteById(anyInt());

        // Act
        fromageService.deleteFromage(expected.getId());

        // Assert
        verify(fromageRepository).deleteById(anyInt());
    }

    private Fromage getFromage() {
        return new Fromage("Chevre",12.95,"test",100,Base64.getDecoder().decode("test"));
    }

    private List<Fromage> getFromages() {
        return List.of(getFromage(),getFromage(),getFromage());
    }
}
