package edu.miu.cs425.eCarRental.controller;

import edu.miu.cs425.eCarRental.model.Booking;
import edu.miu.cs425.eCarRental.model.Category;
import edu.miu.cs425.eCarRental.service.CategoryService;
import edu.miu.cs425.eCarRental.service.SearchService;
import edu.miu.cs425.eCarRental.utility.PeriodRequested;
import edu.miu.cs425.eCarRental.utility.validators.ConsistentDateParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Persistable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private CategoryService categoryService;
//    @Autowired
    private PeriodRequested periodRequested = new PeriodRequested();

    public PeriodRequested getTemp() {
        return periodRequested;
    }

    @PostMapping(value = "/ecarrental/search")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
        public String searchVehicles(@Valid @ModelAttribute("periodRequested") PeriodRequested periodRequested,
            BindingResult bindingResult, Model model
//            @RequestParam("start") @DateTimeFormat (pattern = "yyyy-MM-dd") @FutureOrPresent @Valid LocalDate start,
//                                       @RequestParam("end")  @DateTimeFormat(pattern = "yyyy-MM-dd") @FutureOrPresent @Valid LocalDate end
    ) {
//        ModelAndView modelAndView = new ModelAndView();
//        model.addAttribute("periodRequested", new PeriodRequested());
//        if (bindingResult.hasErrors()) {
        if(periodRequested.getStart().isBefore(LocalDate.now()) || periodRequested.getEnd().isBefore(LocalDate.now()) || bindingResult.hasErrors() ){
            model.addAttribute("errors", bindingResult.getAllErrors());
            // modelAndView.setViewName("/public/home/index");
            return "/public/home/index";
        }

//        if(start.isBefore(LocalDate.now()) || end.isBefore(LocalDate.now()) ){
//
//        }
        else {
            List<Category> availableCategories = searchService.findAvailableCategories(periodRequested.getStart(), periodRequested.getEnd());
            model.addAttribute("availableCategories", availableCategories);
            //modelAndView.setViewName("public/search/results");
            // periodRequested.setStart(start);
            //periodRequested.setEnd(end);
            return "public/search/results";
        }

        // return modelAndView;
    }

}