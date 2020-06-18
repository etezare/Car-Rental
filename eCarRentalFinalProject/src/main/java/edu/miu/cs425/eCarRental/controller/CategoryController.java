package edu.miu.cs425.eCarRental.controller;

import edu.miu.cs425.eCarRental.model.Category;
import edu.miu.cs425.eCarRental.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/ecarrental/admin/categories")
    public ModelAndView manageCategories() {
        ModelAndView modelAndView = new ModelAndView();
        List<Category> categories = categoryService.findAll();
        modelAndView.addObject("categories", categories);
        modelAndView.setViewName("secured/admin/categories/categorieslist");
        return modelAndView;
    }

    @GetMapping(value = "/ecarrental/admin/categories/add")
    public String newCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "secured/admin/categories/newcategoryform";
    }

    @PostMapping(value = "/ecarrental/admin/categories/add/save")
    public String addNewCategory(@Valid @ModelAttribute("category") Category category,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/admin/categories/newcategoryform";
        }
        category = categoryService.save(category);
        return "redirect:/ecarrental/admin/categories";
    }

    @GetMapping(value = "/ecarrental/admin/categories/edit/{categoryId}")
    public String editCategoryForm(@PathVariable("categoryId") Long categoryId, Model model) {
        Category category = categoryService.findById(categoryId);
        if (category != null) {
            model.addAttribute("category", category);
            return "secured/admin/categories/editcategoryform";
        }
        return "secured/admin/categories/categorieslist";
    }

    @GetMapping(value="/ecarrental/admin/categories/delete/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") Long id, Model model){
        categoryService.delete(id);
        return "redirect:/ecarrental/admin/categories";
    }

}

