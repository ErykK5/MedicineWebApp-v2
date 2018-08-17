package com.company.medprojectapp.controller;

import com.company.medprojectapp.model.Medicine;
import com.company.medprojectapp.services.MedService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    @Mock
    MedService medService;

    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(medService);
    }

    @Test
    public void TestMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

        mockMvc.perform(get(""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

    }

    @Test
    public void getIndexPage() {
        //given
        Set<Medicine> meds = new HashSet<>();
        meds.add(new Medicine());
        Medicine med2 = new Medicine();
        Medicine med3 = new Medicine();
        med2.setId(1L);
        med3.setId(2L);
        meds.add(med2);
        meds.add(med3);

        when( medService.getMedicines() ).thenReturn( meds );

        ArgumentCaptor<Set<Medicine>> captor = ArgumentCaptor.forClass(Set.class);

        //when
        String properName = indexController.getIndexPage(model);

        //then
        assertEquals("index", properName );
        verify( medService, times(1) ).getMedicines();
        verify( model, times(1) ).addAttribute( eq("medicines" ), captor.capture() );
        Set<Medicine> setInController = captor.getValue();
        assertEquals(3, setInController.size());
    }
}