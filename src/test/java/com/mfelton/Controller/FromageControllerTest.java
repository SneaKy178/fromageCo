package com.mfelton.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfelton.Repository.FromageRepository;
import com.mfelton.Service.FromageService;
import com.mfelton.model.Fromage;
import com.mfelton.model.Paiement;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ContextConfiguration(classes = FromageController.class)
@WebMvcTest(FromageController.class)
public class FromageControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FromageRepository fromageRepository;

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
        List<Fromage> expected = fromages;
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
        var actualFromage = mapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualFromage.size()).isEqualTo(expected.size());
    }

    @Test
    void testDeleteFromage() throws Exception {
        // Arrange
        Fromage expected = new Fromage();
        expected.setId(1);
        doNothing().when(fromageService).deleteFromage(anyInt());

        // Act
        MvcResult result = mockMvc.perform(delete("/fromage/delete/" + expected.getId())).andReturn();

        // Assert
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }


    private Fromage fromage =  new Fromage("Chevre",12.95,"test", Base64.getDecoder().decode("test"));

    private List<Fromage> fromages =  List.of(fromage,fromage,fromage);

}
