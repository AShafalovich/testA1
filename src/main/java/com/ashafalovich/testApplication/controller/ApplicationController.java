package com.ashafalovich.testApplication.controller;

import com.ashafalovich.testApplication.model.Posting;
import com.ashafalovich.testApplication.service.ConversionService;
import com.ashafalovich.testApplication.service.DeliveryService;
import com.ashafalovich.testApplication.service.MathematicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/app")
public class ApplicationController {

    private final ConversionService conversionService;
    private final MathematicalService mathematicalService;
    private final DeliveryService deliveryService;

    @Autowired
    public ApplicationController(ConversionService conversionService,
                                 MathematicalService mathematicalService,
                                 DeliveryService deliveryService) {
        this.conversionService = conversionService;
        this.mathematicalService = mathematicalService;
        this.deliveryService = deliveryService;
    }

    @GetMapping("/convert/{param}")
    public String convert(@PathVariable String param) {
        return conversionService.convert(param);
    }

    @GetMapping("/func/{n}")
    public double calculateFactorialFunction(@PathVariable int n) {
        return mathematicalService.calculateFactorialFunction(n);
    }

    @GetMapping("/delivery")
    public List<Posting> getJSON(@RequestParam(defaultValue = "") String startDate,
                                 @RequestParam(defaultValue = "") String endDate,
                                 @RequestParam(defaultValue = "") String isActive) throws IOException {
        deliveryService.parseFile();
        return deliveryService.getPostingsByParam(startDate, endDate, isActive);
    }
}
