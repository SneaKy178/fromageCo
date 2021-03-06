package com.mfelton.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfelton.Service.PanierService;
import com.mfelton.model.Fromage;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ContextConfiguration(classes = PanierController.class)
@WebMvcTest(PanierController.class)
public class PanierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PanierService panierService;

    private final ObjectMapper mapper;

    public PanierControllerTest() {
        this.mapper = new ObjectMapper().findAndRegisterModules();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    public void testAddPanier() throws Exception {
        // Arrange
        Panier expected = panier;
        when(panierService.addPanier(expected)).thenReturn(Optional.of(expected));

        // Act
        MvcResult result =
                mockMvc
                        .perform(
                                post("/panier")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(mapper.writeValueAsString(expected)))
                        .andReturn();

        // Assert
        var actualPanier =
                mapper.readValue(result.getResponse().getContentAsString(), Panier.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actualPanier).isEqualTo(expected);
    }

    @Test
    void testGetAllPaniers() throws Exception {
        // Arrange
        List<Panier> expected = paniers;
        when(panierService.getAllPaniers()).thenReturn(expected);

        // Act
        MvcResult result =
                mockMvc
                        .perform(
                                get("/paniers")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(mapper.writeValueAsString(expected)))
                        .andReturn();

        // Assert
        var actualPaniers = mapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualPaniers.size()).isEqualTo(expected.size());
    }


    Fromage fromage = new Fromage("Chevre",12.95,"test", Base64.getDecoder().decode("test"));

    private List<Fromage> fromages = List.of(fromage,fromage,fromage);

    private Panier panier = new Panier(0,0,fromages);

    private List<Panier> paniers = List.of(panier,panier,panier);

}
