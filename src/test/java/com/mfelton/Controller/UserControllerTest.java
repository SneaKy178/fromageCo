package com.mfelton.Controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfelton.Service.ClientService;
import com.mfelton.Service.UserService;
import com.mfelton.model.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
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

@ContextConfiguration(classes = ClientController.class)
@WebMvcTest(ClientController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserService userService;

    @MockBean
    private ClientService clientService;

    @MockBean
    private ClientController clientController;




    private final ObjectMapper mapper;

    public UserControllerTest() {
        this.mapper = new ObjectMapper().findAndRegisterModules();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

//    @Test
//    void testLoginClient() throws Exception {
//        // Arrange
//        User expected = getClient();
//        when(userService.login(expected.getCourriel(), expected.getPassword()))
//                .thenReturn(Optional.of(expected));
//        String url = "/login/" + expected.getCourriel() + "/" + expected.getPassword();
//
//        // Act
//        MvcResult result =
//                mockMvc
//                        .perform(
//                                get(url)
//                                        .contentType(MediaType.APPLICATION_JSON)
//                                        .content(mapper.writeValueAsString(expected)))
//                        .andReturn();
//
//        // Assert
//        var actualClient =
//                mapper.readValue(result.getResponse().getContentAsString(), Client.class);
//        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(actualClient).isEqualTo(expected);
//    }

    @Test
    void testLoginAdministrateur() throws Exception {
        // Arrange
        Administrateur expected = getAdministrateur();
        when(userService.login(expected.getCourriel(), expected.getPassword()))
                .thenReturn(Optional.of(expected));
        String url = "/user/" + expected.getCourriel() + "/" + expected.getPassword();

        // Act
        MvcResult result =
                mockMvc
                        .perform(
                                get(url)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(mapper.writeValueAsString(expected)))
                        .andReturn();

        // Assert
        var actualEtudiant =
                mapper.readValue(result.getResponse().getContentAsString(), Administrateur.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualEtudiant).isEqualTo(expected);
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
