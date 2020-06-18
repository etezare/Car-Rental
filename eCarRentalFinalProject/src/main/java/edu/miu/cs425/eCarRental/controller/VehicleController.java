package edu.miu.cs425.eCarRental.controller;

import java.util.List;
import javax.validation.Valid;

import edu.miu.cs425.eCarRental.model.Category;
import edu.miu.cs425.eCarRental.model.Vehicle;
import edu.miu.cs425.eCarRental.service.CategoryService;
import edu.miu.cs425.eCarRental.service.VehicleService;
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
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/ecarrental/admin/vehicleslist")
    public ModelAndView manageVehicles() {
        ModelAndView modelAndView = new ModelAndView();
        List<Vehicle> vehicles = vehicleService.findAll();
        modelAndView.addObject("vehicles", vehicles);
        modelAndView.setViewName("secured/admin/vehicles/vehicleslist");
        return modelAndView;
    }

    @GetMapping(value = "/ecarrental/admin/vehicles/add")
    public String newVehicleForm(Model model) {
        Vehicle newVehicle = new Vehicle();
        newVehicle.setVehicleNumber(vehicleService.assignVehicleNumber());
        List<Category> categories = categoryService.findAll();
        model.addAttribute("vehicle", newVehicle);
        model.addAttribute("categories", categories);
        return "secured/admin/vehicles/newvehicle";
    }

    @PostMapping(value = "/ecarrental/admin/vehicles/add/save")
    public String addNewVehicle(@Valid @ModelAttribute("vehicle") Vehicle vehicle,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/admin/vehicles/newvehicle";
        }
        vehicle = vehicleService.save(vehicle);
        return "redirect:/ecarrental/admin/vehicleslist";
    }

    @GetMapping(value = "/ecarrental/admin/vehicles/edit/{vehicleId}")
    public String editVehicleForm(@PathVariable("vehicleId") Long vehicleId, Model model) {
        Vehicle vehicle = vehicleService.findById(vehicleId);
        List<Category> categories = categoryService.findAll();
        if (vehicle != null) {
            model.addAttribute("vehicle", vehicle);
            model.addAttribute("categories", categories);
            return "secured/admin/vehicles/editvehicle";
        }
        return "secured/admin/vehicles/vehicleslist";
    }

    @GetMapping(value={"/ecarrental/admin/vehicles/delete/{vehicleId}"})
    public String deleteVehicle(@PathVariable("vehicleId") Long vehicleId, Model model){
                vehicleService.delete(vehicleId);
        return "redirect:/ecarrental/admin/vehicleslist";
    }

}