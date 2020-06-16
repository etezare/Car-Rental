package edu.miu.cs425.eCarRental.controller;

import edu.miu.cs425.eCarRental.model.*;
import edu.miu.cs425.eCarRental.service.*;
import edu.miu.cs425.eCarRental.service.serviceimplementation.CarRentalUserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.iterableWithSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest({PaymentController.class, BookingController.class, AddressController.class,
        Vehicle.class, UserController.class, SearchController.class, CategoryController.class})
//@WebMvcTest(controllers = BookingController.class)
public class BookingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private BookingService bookService;

    @MockBean
    private VehicleService vehicleService;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private SearchService searchService;
    @MockBean
    CarRentalUserDetailsService carRentalUserDetailsService;

    @MockBean
    private PaymentService paymentService;

    @MockBean
    private AddressService addressService;

    @Before
    public void setUp() {
        List<Booking> bookings = Arrays.asList(
                new Booking((long) 333, "12345", LocalDate.now(), LocalDate.of(2020, 6,
                        16), LocalDate.of(2020, 6, 25), 123.00,
                        "dawit", "hailu", LocalDate.of(1992, 4, 12),
                        (long) 12312434, "j@gmail.com", new Vehicle((long) 33, "1234",
                        "vr234", "toyota", "corolla", 2018), new User((long) 23,
                        "essey", "tezare", LocalDate.of(1998, 8, 6),
                        "124343"), new Payment((long) 12, LocalDate.of(2020, 6, 15),
                        "debit", (long) 12345, 123,220.00, new Address((long) 23,
                        "8475 yellow", "fairfield", "IA", 52556, "USA"))
                ));
        Mockito.when(bookService.findAllOrderByDate()).thenReturn(bookings);
    }

    @Test
    public void testListBooking() throws Exception {
        mockMvc.perform(get("/ecarrental/staff/bookinglist"))
                .andExpect(status().isOk())
                .andExpect(view().name("secured/staff/bookinglist"))
                .andExpect(model().attributeExists("bookings"))
                .andExpect(model().attribute("bookings", iterableWithSize(1)));
    }
}
