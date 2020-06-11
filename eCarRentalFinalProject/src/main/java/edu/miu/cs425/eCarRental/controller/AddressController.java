package edu.miu.cs425.eCarRental.controller;

import java.util.List;
import javax.validation.Valid;
import edu.miu.cs425.eCarRental.model.Address;
import edu.miu.cs425.eCarRental.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping(value = "/ecarrental/user/addresses")
    public ModelAndView manageAddresses() {
        ModelAndView modelAndView = new ModelAndView();
        List<Address> addresses = addressService.findAll();
        modelAndView.addObject("addresses", addresses);
        modelAndView.setViewName("user/addresses/addresses");
        return modelAndView;
    }

    @GetMapping(value = "/ecarrental/user/addresses/add")
    public String newAddressForm(Model model) {
        Address newAddress = new Address();
        model.addAttribute("address", newAddress);
        return "user/addresses/addressform";
    }

    @PostMapping(value = "/ecarrental/user/addresses/add/save")
    public String addNewAddress(@Valid @ModelAttribute("address") Address address,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "user/addresses/addressform";
        }
        address = addressService.save(address);
        return "redirect:/user/addresses/addresslist";
    }

    @GetMapping(value = "/ecarrental/user/addresses/edit/{addressId}")
    public String editAddressForm(@PathVariable("addressId") Long addressId, Model model) {
        Address address = addressService.findById(addressId);
        if (address != null) {
            model.addAttribute("address", address);
            return "user/addresses/addressform";
        }
        return "redirect:/user/addresses/addresslist";
    }

    @PostMapping(value = "/ecarrental/admin/addresses/edit")
    public String updateAddress(@Valid @ModelAttribute("address") Address address,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "user/addresses/addresseditform";
        }
        address = addressService.save(address);
        return "redirect:/ecarrental/user/addresses";
    }

    @GetMapping(value="/ecarrental/user/addresses/delete/{addressId}")
    public String deleteAddress(@PathVariable("addressId") Long id, Model model){
        addressService.delete(id);
        return "redirect:/ecarrental/user/addresses";
    }

}
