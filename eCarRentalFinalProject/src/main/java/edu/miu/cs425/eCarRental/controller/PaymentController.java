package edu.miu.cs425.eCarRental.controller;

import java.util.List;

import javax.validation.Valid;

import edu.miu.cs425.eCarRental.model.Payment;
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

    @GetMapping(value = "/ecarrental/user/payments")
    public ModelAndView managePayments() {
        ModelAndView modelAndView = new ModelAndView();
        List<Payment> payments = paymentService.findAll();
        modelAndView.addObject("payments", payments);
        modelAndView.setViewName("user/payments/payments");
        return modelAndView;
    }

    @GetMapping(value = "/ecarrental/user/payments/add")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String newPaymentForm(Model model) {
        Payment newPayment = new Payment();
        model.addAttribute("payment", newPayment);
        return "public/book/paymentform";
    }


    @PostMapping(value = "/ecarrental/user/payments/add/save")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String addNewPayment(@Valid @ModelAttribute("payment") Payment payment,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "public/book/paymentconfirmation";
        }
        payment = paymentService.save(payment);
        return "public/book/paymentconfirmation";
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


}