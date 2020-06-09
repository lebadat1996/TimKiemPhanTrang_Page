package com.codegym.controller;


import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.ICustomerProvince.ICustomerService;
import com.codegym.service.ICustomerProvince.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> provinces() {
        return provinceService.findAll();
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/Customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

//    @GetMapping("/list")
//    public ModelAndView showList() {
//        ModelAndView modelAndView = new ModelAndView("Customer/list");
//        Iterable<Customer> customers = customerService.findAll();
//        modelAndView.addObject("customers", customers);
//        return modelAndView;
//    }

    @GetMapping("/list")
    public ModelAndView listCustomers(@RequestParam("s") Optional<String> s, @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = new PageRequest(page, size);
        Page<Customer> customers;
        if (s.isPresent()) {
            customers = customerService.findAllByFirstName(s.get(), pageable);
        } else {
            customers = customerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/Customer/list");
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveCustomer(@ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView("/Customer/list");
        customerService.save(customer);
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;
    }

    @GetMapping("{id}/edit")
    public ModelAndView showEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/Customer/edit");
        modelAndView.addObject("customer", customerService.getById(id));
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView edit(@ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView("/Customer/list");
        customerService.update(customer);
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;
    }

    @GetMapping("{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/Customer/list");
        customerService.remove(id);
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;
    }

}
