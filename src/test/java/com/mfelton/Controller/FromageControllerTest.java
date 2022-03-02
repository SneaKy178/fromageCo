package com.mfelton.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfelton.Service.ClientService;
import com.mfelton.Service.FromageService;
import com.mfelton.model.Client;
import com.mfelton.model.Fromage;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ContextConfiguration(classes = FromageController.class)
@WebMvcTest(FromageController.class)
public class FromageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FromageService fromageService;

    private final ObjectMapper mapper;

    public FromageControllerTest() {
        this.mapper = new ObjectMapper().findAndRegisterModules();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    void testGetAllFromages() throws Exception {
        // Arrange
        List<Fromage> expected = getFromages();
        when(fromageService.getAllFromages()).thenReturn(expected);

        // Act
        MvcResult result =
                mockMvc
                        .perform(
                                get("/fromages")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(mapper.writeValueAsString(expected)))
                        .andReturn();

        // Assert
        var actualFromages = mapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualFromages.size()).isEqualTo(expected.size());
    }

    private Fromage getFromage() {
        return new Fromage("Chevre",12.95,"test",100, Base64.getDecoder().decode("test"));
    }

    private List<Fromage> getFromages() {
        return List.of(getFromage(),getFromage(),getFromage());
    }

}
