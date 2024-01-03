package com.company.bootstrap;
import com.company.entity.Car;
import com.company.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator implements CommandLineRunner {
    CarRepository carRepository;
    public DataGenerator(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Car c1 = new Car("Toyota", "Rav4");
        Car c2 = new Car("Honda", "Civic");
        Car c3 = new Car("Tesla", "Model-S");
        carRepository.save(c1);
        carRepository.save(c2);
        carRepository.save(c3);
    }





}
