package com.mfelton.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfelton.Service.ClientService;
import com.mfelton.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
    void testGetAllEtudiants() throws Exception {
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

    private Client getClient() {
        return new Client("Mathieu","Felton","test@gmail.com","Test1234","123 rue test","51484593848","Quebec","Montreal");
    }

    private List<Client> getClients(){
        return List.of(getClient(),getClient(),getClient());
    }
}
