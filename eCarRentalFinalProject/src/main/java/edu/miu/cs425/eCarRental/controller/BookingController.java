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



    @Controller
    public class BookingController {
        @Autowired
        private PaymentController paymentController;

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

        @RequestMapping(value = "/ecarrental/staff/bookinglist", method = RequestMethod.GET)
        public ModelAndView bookingsList() {
            List<Booking> bookings = bookingService.findAllOrderByDate();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("bookings", bookings);
            modelAndView.setViewName("secured/staff/bookinglist");

            return modelAndView;
        }

        @RequestMapping(value = "/ecarrental/staff/booking/addnew", method = RequestMethod.GET)
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
            return "secured/staff/bookingform";
        }


        @PostMapping(value = "/ecarrental/staff/bookingform/save")
        public String addNewBooking(@Valid @ModelAttribute("booking") Booking booking,
                                    BindingResult bindingResult, Model model) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("errors", bindingResult.getAllErrors());
                return "secured/staff/bookingform";
            }

            booking = bookingService.save(booking);
            return "redirect:/ecarrental/staff/bookinglist";
        }

        @GetMapping(value = "/ecarrental/staff/booking/edit/{bookingId}")
        public String editBookingForm(@PathVariable("bookingId") Long bookingId, Model model) {
            Booking booking = bookingService.findById(bookingId);
                if (booking != null) {
                model.addAttribute("booking", booking);
                return "secured/staff/bookingeditform";
            }
            return "/ecarrental/staff/bookinglist";
        }

        @PostMapping(value = "/ecarrental/staff/bookings/update")
        public String updateBooking(@Valid @ModelAttribute("booking") Booking booking,
                                    BindingResult bindingResult, Model model) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("errors", bindingResult.getAllErrors());
                return "secured/staff/bookingeditform";
            }
            booking = bookingService.save(booking);
            return "redirect:/ecarrental/staff/bookinglist";
        }

        @GetMapping(value = "/ecarrental/staff/booking/delete/{bookingId}")
        public String deleteBooking(@PathVariable("bookingId") Long id) {
            bookingService.delete(id);
            return "redirect:/ecarrental/staff/bookinglist  ";
        }

        @RequestMapping(value = "/ecarrental/secured/customer/customers/newbookingform/{category}", method = RequestMethod.GET)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        public String newPublicBookingForm(Model model, @PathVariable("category") Category category) {
            Booking newBooking = new Booking();
            Payment newPayment = new Payment();
            newBooking.setPayment(newPayment);
            LocalDate start = searchController.getTemp().getStart();
            LocalDate end = searchController.getTemp().getEnd();
            Long dateDifference = (Long) (ChronoUnit.DAYS.between(start, end));
            Double unitPrice = category.getRatePerDay();
            Double totalPrice = (double) (dateDifference * unitPrice);
            newBooking.setTotalPrice(totalPrice);
            newBooking.setStartDate(start);
            newBooking.setEndDate(end);
            newBooking.setReferenceNumber(bookingService.assignReferenceNumber());
            newBooking.setBookingDate(LocalDate.now());
            paymentController.addNewPayment(newBooking);
//            newBooking.getPayment().setTotalPrice(newBooking.getTotalPrice());
            paymentService.save(newBooking.getPayment());
            newBooking.setVehicle(searchService.getAvailableVehicles(start, end)
                    .stream()
                    .filter(v -> v.getCategory() == category)
                    .findFirst()
                    .orElse(null));
            model.addAttribute("booking", newBooking);
            return "secured/customer/customers/bookingform";
        }

        @PostMapping(value = "/ecarrental/customer/bookings/addnewbooking/save")
        @DateTimeFormat(pattern = "yyyy-MM-dd")

        public String addNewBookingCustomer(@Valid @ModelAttribute("booking") Booking booking,
                                          BindingResult bindingResult, Model model) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("errors", bindingResult.getAllErrors());
                return "secured/customer/customers/bookingform";
            }
            booking = bookingService.save(booking);
            return "redirect:/ecarrental/customer/bookings/success";
        }

        @GetMapping(value = "/ecarrental/customer/bookings/success")
        public String homePage() {
            return "secured/customer/customers/confirmation";
        }

    }


