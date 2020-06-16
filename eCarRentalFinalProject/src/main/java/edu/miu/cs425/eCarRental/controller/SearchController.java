package edu.miu.cs425.eCarRental.controller;

import edu.miu.cs425.eCarRental.model.Category;
import edu.miu.cs425.eCarRental.service.CategoryService;
import edu.miu.cs425.eCarRental.service.SearchService;
import edu.miu.cs425.eCarRental.utility.PeriodRequested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    private PeriodRequested periodRequested = new PeriodRequested();

    public PeriodRequested getTemp() {
        return periodRequested;
    }



    @GetMapping(value = "/ecarrental/search")
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    public ModelAndView searchVehicles(@RequestParam ("start") @DateTimeFormat (pattern = "yyyy-MM-dd") LocalDate start,
                                       @RequestParam("end")  @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate end) {
        ModelAndView modelAndView = new ModelAndView();
        List<Category> availableCategories = searchService.findAvailableCategories(start, end);
        modelAndView.addObject("availableCategories", availableCategories);
        modelAndView.setViewName("public/search/results");
        periodRequested.setStart(start);
        periodRequested.setEnd(end);
        return modelAndView;
    }

}