package com.codegym.controller;

import com.codegym.model.Province;
import com.codegym.service.ICustomerProvince.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    IProvinceService provinceService;

    @GetMapping("list")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("Province/list");
        modelAndView.addObject("province", provinceService.findAll());
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("Province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("create-province")
    public ModelAndView createProvince(@ModelAttribute Province province) {
        ModelAndView modelAndView = new ModelAndView("Province/list");
        provinceService.save(province);
        modelAndView.addObject("province", provinceService.findAll());
        return modelAndView;
    }

    @GetMapping("{id}/edit")
    public ModelAndView showEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("Province/edit");
        modelAndView.addObject("province", provinceService.getById(id));
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView edit(@ModelAttribute Province province) {
        ModelAndView modelAndView = new ModelAndView("Province/list");
        provinceService.update(province);
        modelAndView.addObject("province", provinceService.findAll());
        return modelAndView;
    }

    @GetMapping("{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("Province/list");
        provinceService.remove(id);
        modelAndView.addObject("province", provinceService.findAll());
        return modelAndView;
    }

}
