package com.company.medprojectapp.controller;

import com.company.medprojectapp.model.Component;
import com.company.medprojectapp.model.Medicine;
import com.company.medprojectapp.services.ComponentService;
import com.company.medprojectapp.services.MedService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ComponentControllerTest {

    @Mock
    MedService medService;

    @Mock
    ComponentService componentService;

    ComponentController componentController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this );
        componentController = new ComponentController(componentService, medService);
        mockMvc = MockMvcBuilders.standaloneSetup(componentController).build();
    }

    @Test
    public void testShowComponentList() throws Exception{
        //given
        Medicine m = new Medicine();
        m.setId(1L);
        when( medService.findById( anyLong() ) ).thenReturn( m );

        //when
        mockMvc.perform( get("/med/components/1") )
                .andExpect( status().isOk() )
                .andExpect( view().name("med/components/list") )
                .andExpect( model().attributeExists("med") );

        //then
        verify(medService, times(1) ).findById( anyLong() );
    }

    @Test
    public void testShowSpecificComponent() throws Exception{
        //given
        Medicine m = new Medicine();
        Component c = new Component();
        c.setMedicine( m );
        c.getMedicine().setId(1L);
        c.setId(1L);

        //when
        when( componentService.findByMedicineIdAndComponentId( anyLong(), anyLong() ) ).thenReturn( c );

        //then
        mockMvc.perform( get("/med/components/show/1/1") )
                .andExpect( status().isOk() )
                .andExpect( view().name("med/components/show") )
                .andExpect( model().attributeExists("comp") );
    }

    @Test
    public void testUpdateComponent() throws Exception{
        //given
        Medicine m = new Medicine();
        Component c = new Component();
        c.setMedicine( m );
        c.getMedicine().setId(1L);
        c.setId(1L);

        //when
        when( componentService.findByMedicineIdAndComponentId( anyLong(), anyLong() ) ).thenReturn( c );

        //then
        mockMvc.perform( get("/med/components/update/1/1") )
                .andExpect( status().isOk() )
                .andExpect( view().name("med/components/update") )
                .andExpect( model().attributeExists("comp") );
    }

    @Test
    public void testNewComponent() throws Exception{
        //given
        Component c = new Component();
        Medicine m = new Medicine();
        m.setId(1L);

        //when
        when( medService.findById( anyLong() ) ).thenReturn( m );

        //then
        mockMvc.perform( get("/med/components/new/1") )
                .andExpect( status().isOk() )
                .andExpect( view().name("med/components/new") )
                .andExpect( model().attributeExists("comp", "med") );
    }

    @Test
    public void testSaveOrUpdateComponent() throws Exception{
        //given
        Medicine m = new Medicine();
        Component c = new Component();
        c.setMedicine( m );
        m.setId( 1L );
        c.setId( 1L );

        //when
        when( medService.findById( anyLong() ) ).thenReturn( m );
        when( componentService.createComponent( any() ) ).thenReturn( c );

        //then
        mockMvc.perform( post("/med/components/1" )
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
        )
                .andExpect( status().is3xxRedirection() )
                .andExpect( view().name("redirect:/med/components/1") );
    }

    @Test
    public void testDeleteByIdComponent() throws Exception{
        //then
        mockMvc.perform( get("/med/components/delete/1/1") )
                .andExpect( status().is3xxRedirection() )
                .andExpect( view().name("redirect:/med/components/1"));

        verify(componentService, times(1 ) ).deleteById( anyLong(), anyLong() );
    }
}