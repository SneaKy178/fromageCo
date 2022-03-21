package com.mfelton.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfelton.Service.ClientService;
import com.mfelton.Service.PaiementService;
import com.mfelton.model.Client;
import com.mfelton.model.Fromage;
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

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
        Paiement expected = getPaiement();
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
        List<Paiement> expected = getPaiements();
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
        List<Paiement> expected = getPaiements();
        when(paiementService.findPaiementByCourriel(any(String.class))).thenReturn(expected);
        String url = "/paiement/" + getClient().getCourriel();

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
