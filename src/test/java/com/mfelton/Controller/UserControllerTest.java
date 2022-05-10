package com.mfelton.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfelton.Service.UserService;
import com.mfelton.model.Administrateur;
import com.mfelton.model.Client;
import com.mfelton.model.Panier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ContextConfiguration(
        classes = UserController.class,
        initializers = ConfigFileApplicationContextInitializer.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private final ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;



    public UserControllerTest() {
        this.mapper = new ObjectMapper().findAndRegisterModules();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    void testLoginClient() throws Exception {
        // Arrange
        Client expected = client;
        when(userService.login(expected.getCourriel(), expected.getPassword()))
                .thenReturn(Optional.of(expected));
        String url = "/login/" + expected.getCourriel() + "/" + expected.getPassword();

        // Act
        MvcResult result =
                mockMvc
                        .perform(
                                get(url)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(mapper.writeValueAsString(expected)))
                        .andReturn();

        // Assert
        var actualClient =
                mapper.readValue(result.getResponse().getContentAsString(), Client.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualClient).isEqualTo(expected);
    }

    @Test
    void testFindClientByEmail() throws Exception {
        // Arrange
        Client expected = client;
        when(userService.findUserByCourriel(any(String.class))).thenReturn(Optional.of(expected));
        String url = "/" + expected.getCourriel();

        // Act
        MvcResult result =
                mockMvc
                        .perform(
                                get(url)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(mapper.writeValueAsString(expected)))
                        .andReturn();

        // Assert
        var actualClient =
                mapper.readValue(result.getResponse().getContentAsString(), Client.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualClient).isEqualTo(expected);
    }

    @Test
    void testLoginAdministrateur() throws Exception {
        // Arrange
        Administrateur expected = administrateur;
        when(userService.login(expected.getCourriel(), expected.getPassword()))
                .thenReturn(Optional.of(expected));
        String url = "/login/" + expected.getCourriel() + "/" + expected.getPassword();

        // Act
        MvcResult result =
                mockMvc
                        .perform(
                                get(url)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(mapper.writeValueAsString(expected)))
                        .andReturn();

        // Assert
        var actualAdministrateur =
                mapper.readValue(result.getResponse().getContentAsString(), Administrateur.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualAdministrateur).isEqualTo(expected);
    }

    @Test
    void testFindAdministrateurByEmail() throws Exception {
        // Arrange
        Administrateur expected = administrateur;
        when(userService.findUserByCourriel(any(String.class))).thenReturn(Optional.of(expected));
        String url = "/" + expected.getCourriel();

        // Act
        MvcResult result =
                mockMvc
                        .perform(
                                get(url)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(mapper.writeValueAsString(expected)))
                        .andReturn();

        // Assert
        var actualAdministrateur =
                mapper.readValue(result.getResponse().getContentAsString(), Administrateur.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualAdministrateur).isEqualTo(expected);
    }

    Administrateur administrateur = new Administrateur("Prenom","Nom","prenom@email.com","password","titre");

    Panier panier = new Panier(0,0,Collections.emptyList());

    Client client = new Client("Prenom","Nom","prenom@email.com","password","adresse","telephone","province","ville", panier);




}
