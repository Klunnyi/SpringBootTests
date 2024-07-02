package com.example.demo.service;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import com.example.demo.utils.MailSender;
import com.example.demo.utils.Role;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

//нам потребуется раннер что б спринг подготовил тестовое окружение
@SpringBootTest
@RunWith(SpringRunner.class) //будем использовать сервисный сло
class CarServiceTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    public MailSender mailSender;

    @Test
    void addUser() {
        Car car = new Car("test5");
        boolean isCarCreated = carService.addCar(car);

        //проверяет то что пользователь был успешно создан в БД
        Assert.assertTrue(isCarCreated);
        Assert.assertNotNull(car.getColor());
        Assert.assertTrue(CoreMatchers.is(car.getRole()).matches(Collections.singletonList(Role.TRACK)));
//
        Mockito.verify(carRepository, Mockito.times(1)).findByName(car.getName());
        Mockito.verify(carRepository, Mockito.times(1)).save(car);
        Mockito.verify(mailSender, Mockito.times(1)).sendMail(
                ArgumentMatchers.eq("car@gmail.com"),
                ArgumentMatchers.eq("test5"),
                ArgumentMatchers.anyString());
    }

    @Test
    void addUserFailTest() {
        Car car = new Car("test5");

//возвращаем новую машину когда на carRepository вызывается findByName с "test5"
        Mockito.doReturn(new Car())
                .when(carRepository)
                .findByName("test5");

        boolean isCarCreated = carService.addCar(car);

        Assert.assertFalse(isCarCreated);

//проверка  что у нас не сохраняется машина и что нет отправки на емейл
        Mockito.verify(carRepository, Mockito.times(0)).save(ArgumentMatchers.any(Car.class));
        Mockito.verify(mailSender, Mockito.times(0)).sendMail(
                ArgumentMatchers.eq("car@gmail.com"),
                ArgumentMatchers.eq("test5"),
                ArgumentMatchers.anyString());
    }

    @Test
    void activateCar() {
        Car car = new Car();
        car.setActivationCode("bingo");

        Mockito.doReturn(car)
                .when(carRepository)
                .findByActivationCode("activate");

        boolean isCarActivated = carService.activateCar("activate");

        Assert.assertNull(car.getActivationCode());
        Assert.assertTrue(isCarActivated);

        Mockito.verify(carRepository, Mockito.times(1)).save(ArgumentMatchers.any(Car.class));
    }

    @Test
    public void activateFaildTest() {
        Car car = new Car();
        car.setActivationCode("bingo");

        Mockito.doReturn(null)
                .when(carRepository)
                .findByActivationCode("activate");

        boolean isCarActivated = carService.activateCar("activate");

        Assert.assertNotNull(car.getActivationCode());
        Assert.assertFalse(isCarActivated);

        Mockito.verify(carRepository, Mockito.times(0)).save(ArgumentMatchers.any(Car.class));
    }
}