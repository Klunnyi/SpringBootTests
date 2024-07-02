package com.example.demo.service;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import com.example.demo.utils.MailSender;
import com.example.demo.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class CarService {

    private final CarRepository carRepository;
    public final MailSender mailSender;

    @Autowired
    public CarService(CarRepository carRepository, MailSender mailSender) {
        this.carRepository = carRepository;
        this.mailSender = mailSender;
    }

    @Transactional
    public boolean addCar(Car car) {
        Car carFromDb = carRepository.findByName(car.getName());

        if (carFromDb != null) {
            return false;
        }

        car.setColor("red");
        car.setRole(Collections.singletonList(Role.TRACK));

        carRepository.save(car);
        mailSender.sendMail("car@gmail.com", car.getName(), "text");

        return true;
    }


    public boolean activateCar(String code) {
        Car car = carRepository.findByActivationCode(code);

        if (car == null) {
            return false;
        }

        car.setActivationCode(null);
        carRepository.save(car);
        return true;
    }
}
