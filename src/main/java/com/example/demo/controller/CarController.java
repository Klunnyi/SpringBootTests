package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/add1")
    public Boolean addCar( @RequestParam(value = "name", defaultValue = "test1") String name)  {
        Car car = new Car(name);
        boolean isCarCreated = carService.addCar(car);
        return isCarCreated;
    }

    @GetMapping("/add2")
    public String addCar2( @RequestParam(value = "name", defaultValue = "test2") String name)  {
        Car car = new Car(name);
        boolean isCarCreated = carService.addCar(car);
        return String.valueOf(isCarCreated);
    }


}
