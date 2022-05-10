package com.mfelton.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfelton.Service.ClientService;
import com.mfelton.model.Client;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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
        Client expected = client;
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
        List<Client> expected = clients;
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
    void testDeleteClient() throws Exception {
        // Arrange
        Client expected = new Client();
        expected.setId(1);
        doNothing().when(clientService).deleteClient(anyInt());

        // Act
        MvcResult result = mockMvc.perform(delete("/client/delete/" + expected.getId())).andReturn();

        // Assert
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }


    private Panier panier = new Panier(0,0, Collections.emptyList());

    private Client client = new Client("Prenom","Nom","prenom@email.com","password","adresse","telephone","province","ville", panier);

    private List<Client> clients = List.of(client,client,client);

}
