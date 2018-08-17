package com.company.medprojectapp.controller;

import com.company.medprojectapp.model.Medicine;
import com.company.medprojectapp.services.MedService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class MedicineControllerTest {

    @Mock
    MedService medService;

    @Mock
    Model model;

    MedicineController medicineController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        medicineController = new MedicineController(medService);
        mockMvc = MockMvcBuilders.standaloneSetup(medicineController).build();
    }

    @Test
    public void testShowById() throws Exception {
        Medicine m = new Medicine();
        m.setId(1L);

        when( medService.findById( anyLong() ) ).thenReturn( m );

        mockMvc.perform( get("/med/show/1") )
                .andExpect( status().isOk() )
                .andExpect( view().name("med/show") )
                .andExpect( model().attributeExists("med") );
    }

    @Test
    public void testCreateMed() throws Exception {
        Medicine m = new Medicine();

        mockMvc.perform( get("/med/new") )
                .andExpect( status().isOk() )
                .andExpect( view().name("med/medform") )
                .andExpect( model().attributeExists("med") );
    }

    @Test
    public void testSaveMed() throws Exception {
        Medicine m = new Medicine();
        m.setId(1L);

        when( medService.createMed( any() ) ).thenReturn( m );

        mockMvc.perform( post("/med")
            .contentType( MediaType.APPLICATION_FORM_URLENCODED )
            .param("id","")
            .param("description","some string")
        )
                .andExpect( status().is3xxRedirection() )
                .andExpect( view().name("redirect:/med/show/1") );
    }

    @Test
    public void testUpdateMed() throws Exception {
        Medicine m = new Medicine();
        m.setId( 1L );

        when( medService.findById( anyLong() ) ).thenReturn( m );

        mockMvc.perform(get("/med/update/1"))
                .andExpect( status().isOk() )
                .andExpect( view().name("med/medform") )
                .andExpect( model().attributeExists("med") );
    }

    @Test
    public void testDeleteMed() throws Exception {
        mockMvc.perform(get("/med/delete/1") )
                .andExpect( status().is3xxRedirection() )
                .andExpect( view().name("redirect:/") );
    }
}