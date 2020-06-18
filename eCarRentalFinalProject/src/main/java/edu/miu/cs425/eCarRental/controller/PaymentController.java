package edu.miu.cs425.eCarRental.controller;

import java.util.List;

import javax.validation.Valid;

import edu.miu.cs425.eCarRental.model.Address;
import edu.miu.cs425.eCarRental.model.Booking;
import edu.miu.cs425.eCarRental.model.Payment;
import edu.miu.cs425.eCarRental.service.AddressService;
import edu.miu.cs425.eCarRental.service.BookingService;
import edu.miu.cs425.eCarRental.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AddressService addressService;

    private Booking booking;
    @Autowired
    private BookingService bookingService;

    @GetMapping(value = "/ecarrental/customer/payments")
    public ModelAndView managePayments() {
        ModelAndView modelAndView = new ModelAndView();
        List<Payment> payments = paymentService.findAll();
        modelAndView.addObject("payments", payments);
        modelAndView.setViewName("customer/payments/payments");
        return  modelAndView;
    }

    @GetMapping(value = "/ecarrental/customer/payments/add")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String newPaymentForm(Model model) {
        Payment newPayment = new Payment();
        Booking newBooking=new Booking();
        newBooking.setPayment(newPayment);
        Address address=new Address();
        newPayment.setBillingAddress(address);
        model.addAttribute("payment", newPayment);
        return "secured/customer/customers/paymentform";
    }

    @PostMapping(value = "/ecarrental/cutomer/payments/add/save")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public String addNewPayment(@Valid @ModelAttribute("payment") Payment payment, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("payment", payment);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/customer/customers/paymentform";
        }
        if(payment.getBillingAddress().getStreet().trim().isEmpty()
                && payment.getBillingAddress().getCity().trim().isEmpty()
                && payment.getBillingAddress().getState().trim().isEmpty()
                && payment.getBillingAddress().getCountry().trim().isEmpty()
                && payment.getBillingAddress().getZipCode() == null) {
            payment.setBillingAddress(null);
        }

        addressService.save(payment.getBillingAddress());

        payment.setPaymentDate(booking.getBookingDate());
        payment.setTotalPrice(booking.getTotalPrice());
//        if(booking!=null) {
//            Booking booking2 = bookingService.findById(booking.getBookingId());
//            payment.setBooking(booking);
//            bookingService.save(booking2);
//        }
//        booking.setPayment(payment);
        paymentService.save(payment);
//        payment.setBooking(bookingService.findById(booking.getBookingId()));
//        bookingService.save(payment.getBooking());



        return "secured/customer/customers/paymentconfirmation";
    }



    @GetMapping(value = "/ecarrental/user/payments/edit/{paymentId}")
    public String editPaymentForm(@PathVariable("paymentId") Long paymentId, Model model) {
        Payment payment = paymentService.findById(paymentId);
        if (payment != null) {
            model.addAttribute("payment", payment);
            return "user/payments/editpaymentform";
        }
        return "user/payments/payments";
    }
    @GetMapping(value="/ecarrental/user/payment/delete/{paymentId}")
    public String deletePayment(@PathVariable("paymentId") Long id, Model model){
        paymentService.delete(id);
        return "redirect:/ecarrental/user/payments";
    }

//    @GetMapping(value = "/ecarrental/customer/customers/paymentconfirmation")
//    public String homePage1() {
//        return "secured/customer/customers/bookingform";
//    }

    public void addNewPayment(Booking newBooking) {
       booking=newBooking;
    }
}