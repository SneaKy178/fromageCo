package com.mfelton.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfelton.Service.PaiementService;
import com.mfelton.model.Client;
import com.mfelton.model.Paiement;
import com.mfelton.model.Panier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ContextConfiguration(classes = PaiementController.class)
@WebMvcTest(PaiementController.class)
public class PaiementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaiementService paiementService;

    private final ObjectMapper mapper;

    public PaiementControllerTest() {
        this.mapper = new ObjectMapper().findAndRegisterModules();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    public void testAddPaiement() throws Exception {
        // Arrange
        Paiement expected = paiement;
        when(paiementService.addPaiement(expected)).thenReturn(Optional.of(expected));

        // Act
        MvcResult result =
                mockMvc
                        .perform(
                                post("/paiement")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(mapper.writeValueAsString(expected)))
                        .andReturn();

        // Assert
        var actualPaiement =
                mapper.readValue(result.getResponse().getContentAsString(), Paiement.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actualPaiement).isEqualTo(expected);
    }

    @Test
    void testGetAllPaiements() throws Exception {
        // Arrange
        List<Paiement> expected = paiements;
        when(paiementService.getAllPaiements()).thenReturn(expected);

        // Act
        MvcResult result =
                mockMvc
                        .perform(
                                get("/paiements")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(mapper.writeValueAsString(expected)))
                        .andReturn();

        // Assert
        var actualPaiements = mapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualPaiements.size()).isEqualTo(expected.size());
    }


    @Test
    void testFindPaiementByClientCourriel() throws Exception {
        // Arrange
        List<Paiement> expected = paiements;
        when(paiementService.findPaiementByCourriel(any(String.class))).thenReturn(expected);
        String url = "/paiement/" + client.getCourriel();

        // Act
        MvcResult result =
                mockMvc
                        .perform(
                                get(url)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(mapper.writeValueAsString(expected)))
                        .andReturn();

        // Assert
        var actualPaiements = mapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualPaiements).hasSize(expected.size());
    }

    @Test
    void testDeletePaiement() throws Exception {
        // Arrange
        Paiement expected = new Paiement();
        expected.setId(1);
        doNothing().when(paiementService).deletePaiement(anyInt());

        // Act
        MvcResult result = mockMvc.perform(delete("/paiement/delete/" + expected.getId())).andReturn();

        // Assert
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    private Panier panier = new Panier(0,0, Collections.emptyList());

    private Client client = new Client("Prenom","Nom","prenom@email.com","password","adresse","telephone","province","ville", panier);

    private Paiement paiement =  new Paiement("VISA",2320323232L,"02/25","Mathieu Felton",123,"J6J5S2",client);

    private List<Paiement> paiements =  List.of(paiement,paiement,paiement);
}
