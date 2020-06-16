package edu.miu.cs425.eCarRental.controller;

import edu.miu.cs425.eCarRental.service.serviceimplementation.CarRentalUserDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HomePageController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CarRentalUserDetailsService carRentalUserDetailsService;

    @Test
    public void testDisplayHomePage() throws Exception {
        mockMvc.perform(get("/ecarrental"))
                .andExpect(status().isOk())
                .andExpect(view().name("public/home/index"))
                .andExpect(content().string(
                        containsString("<h1>Book Your Car Now</h1>")));
    }
}
