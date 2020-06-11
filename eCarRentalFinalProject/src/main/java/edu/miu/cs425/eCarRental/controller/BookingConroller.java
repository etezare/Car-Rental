package edu.miu.cs425.eCarRental.controller;

import edu.miu.cs425.eCarRental.model.*;
import edu.miu.cs425.eCarRental.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BookingConroller {

    package mum.edu.cs.cs425.project.ecarrent.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mum.edu.cs.cs425.project.ecarrent.model.Booking;
import mum.edu.cs.cs425.project.ecarrent.model.Category;
import mum.edu.cs.cs425.project.ecarrent.model.Payment;
import mum.edu.cs.cs425.project.ecarrent.model.User;
import mum.edu.cs.cs425.project.ecarrent.model.Vehicle;
import mum.edu.cs.cs425.project.ecarrent.services.IBookingService;
import mum.edu.cs.cs425.project.ecarrent.services.IPaymentService;
import mum.edu.cs.cs425.project.ecarrent.services.IUserService;
import mum.edu.cs.cs425.project.ecarrent.services.IVehicleService;
import mum.edu.cs.cs425.project.ecarrent.services.implementations.SearchService;

    @Controller
    public class BookingController {

        @Autowired
        private BookingService bookingService;

        @Autowired
        private VehicleService vehicleService;

        @Autowired
        private UserService userService;

        @Autowired
        private PaymentService paymentService;

        @Autowired
        private SearchController searchController;

        @Autowired
        private SearchService searchService;

        @RequestMapping(value = "/company/admin/bookings", method = RequestMethod.GET)
        public ModelAndView bookingsList() {
            List<Booking> bookings = bookingService.findAll();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("bookings", bookings);
            modelAndView.setViewName("admin/bookings/bookings");
            return modelAndView;
        }

        @RequestMapping(value = "/company/admin/bookings/addnew", method = RequestMethod.GET)
        public String newBookingForm(Model model) {
            Booking newBooking = new Booking();
            newBooking.setReferenceNumber(bookingService.assignReferenceNumber());
            List<Vehicle> vehicles = vehicleService.findAll();
            List<User> users = userService.findAll();
            List<Payment> payments = paymentService.findAll();
            model.addAttribute("booking", newBooking);
            model.addAttribute("vehicles", vehicles);
            model.addAttribute("users", users);
            model.addAttribute("payments", payments);
            return "admin/bookings/bookingform";
        }

        @PostMapping(value = "/company/admin/bookings/addnew/save")
        public String addNewBooking(@Valid @ModelAttribute("booking") Booking booking,
                                    BindingResult bindingResult, Model model) {
            if(bindingResult.hasErrors()) {
                model.addAttribute("errors", bindingResult.getAllErrors());
                return "admin/bookings/bookingform";
            }
            booking = bookingService.save(booking);
            return "redirect:/company/admin/bookings/bookings";
        }

        @GetMapping(value = "/company/admin/bookings/edit/{bookingId}")
        public String editBookingForm(@PathVariable("bookingId") Long bookingId, Model model) {
            Booking booking = bookingService.findById(bookingId);
            if (booking != null) {
                model.addAttribute("booking", booking);
                return "admin/bookings/bookingeditform";
            }
            return "admin/bookings/bookings";
        }

        @PostMapping(value = "/company/admin/bookings/edit/save")
        public String updateBooking(@Valid @ModelAttribute("booking") Booking booking,
                                    BindingResult bindingResult, Model model) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("errors", bindingResult.getAllErrors());
                return "admin/bookings/bookingeditform";
            }
            booking = bookingService.save(booking);
            return "redirect:/company/admin/bookings";
        }

        @GetMapping(value="/company/admin/bookings/delete/{bookingId}")
        public String deleteBooking(@PathVariable("bookingId") Long id){
            bookingService.delete(id);
            return "redirect:/company/admin/bookings";
        }

        @RequestMapping(value = "/company/public/bookings/addnew/{category}", method = RequestMethod.GET)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        public String newPublicBookingForm(Model model, @PathVariable("category") Category category) {
            Booking newBooking = new Booking();
            Payment newPayment = new Payment();
            paymentService.save(newPayment);
            LocalDate start = searchController.getTemp().getStart();
            LocalDate end = searchController.getTemp().getEnd();
            Long dateDifference = (Long)(ChronoUnit.DAYS.between(start, end));
            Float unitPrice = category.getRatePerDay();
            Double totalPrice = (double) (dateDifference*unitPrice);
            newBooking.setTotalPrice(totalPrice);
            newBooking.setStartDate(start);
            newBooking.setEndDate(end);
            newBooking.setReferenceNumber(bookingService.assignReferenceNumber());
            newBooking.setBookingDate(LocalDate.now());
            newBooking.setPayment(newPayment);
            newBooking.setVehicle(searchService.getAvailableVehicles(start, end)
                    .stream()
                    .filter(v -> v.getCategory() == category)
                    .findFirst()
                    .orElse(null));
            model.addAttribute("booking", newBooking);
            return "public/book/bookingform";
        }

        @PostMapping(value = "/company/public/bookings/addnew/save")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        public String addNewBookingPublic(@Valid @ModelAttribute("booking") Booking booking,
                                          BindingResult bindingResult, Model model) {
            if(bindingResult.hasErrors()) {
                model.addAttribute("errors", bindingResult.getAllErrors());
                return "public/book/bookingform";
            }
            booking = bookingService.save(booking);
            return "redirect:/company/public/bookings/success";
        }

        @GetMapping(value = "/company/public/bookings/success")
        public String homePage() {
            return "public/book/confirmation";
        }

    }

}
