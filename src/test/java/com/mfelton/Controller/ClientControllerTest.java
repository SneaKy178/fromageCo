package com.mfelton.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfelton.Service.ClientService;
import com.mfelton.model.Client;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ContextConfiguration(classes = ClientController.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    private final ObjectMapper mapper;

    public ClientControllerTest() {
        this.mapper = new ObjectMapper().findAndRegisterModules();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    public void testAddClient() throws Exception {
        // Arrange
        Client expected = getClient();
        when(clientService.addClient(expected)).thenReturn(Optional.of(expected));

        // Act
        MvcResult result =
                mockMvc
                        .perform(
                                post("/client")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(mapper.writeValueAsString(expected)))
                        .andReturn();

        // Assert
        var actualClient =
                mapper.readValue(result.getResponse().getContentAsString(), Client.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actualClient).isEqualTo(expected);
    }

    @Test
    void testGetAllClients() throws Exception {
        // Arrange
        List<Client> expected = getClients();
        when(clientService.getAllClients()).thenReturn(expected);

        // Act
        MvcResult result =
                mockMvc
                        .perform(
                                get("/clients")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(mapper.writeValueAsString(expected)))
                        .andReturn();

        // Assert
        var actualClients = mapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualClients.size()).isEqualTo(expected.size());
    }

    @Test
    void testLoginClient() throws Exception {
        // Arrange
        Client expected = getClient();
        when(clientService.login(expected.getCourriel(), expected.getPassword()))
                .thenReturn(Optional.of(expected));
        String url = "/client/" + expected.getCourriel() + "/" + expected.getPassword();

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
        Client expected = getClient();
        when(clientService.findClientByCourriel(any(String.class))).thenReturn(Optional.of(expected));
        String url = "/client/" + expected.getCourriel();

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

    private List<Client> getClients(){
        return List.of(getClient(),getClient(),getClient());
    }

}
