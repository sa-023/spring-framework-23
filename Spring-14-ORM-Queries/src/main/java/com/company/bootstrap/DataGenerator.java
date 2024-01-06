package com.company.bootstrap;
import com.company.repository.CourseRepository;
import com.company.repository.DepartmentRepository;
import com.company.repository.EmployeeRepository;
import com.company.repository.RegionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator implements CommandLineRunner {
    private final RegionRepository regionRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final CourseRepository courseRepository;
    public DataGenerator(RegionRepository regionRepository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, CourseRepository courseRepository) {
        this.regionRepository = regionRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(" ------------- Region Starts ------------- ");
        System.out.println("findByCountry "+ regionRepository.findByCountry("Canada"));
        System.out.println("findDistinctByCountry "+ regionRepository.findDistinctByCountry("Canada"));
        System.out.println("findByCountryContaining"+ regionRepository.findByCountryContaining("United"));
        System.out.println("findByCountryContainingOrderByCountry "+ regionRepository.findByCountryContainingOrderByCountry("Asia"));
        System.out.println("findTop2ByCountry "+ regionRepository.findTop2ByCountry("Canada"));
        System.out.println(" ------------- Region End ------------- ");


        System.out.println(" ------------- Department Starts ------------- ");
        System.out.println("findByDepartment "+ departmentRepository.findByDepartment("Toys"));
        System.out.println("findByDivision "+ departmentRepository.findByDivisionIs("Outdoors"));
        System.out.println("findDistinctTop3ByDivisionContaining "+ departmentRepository.findDistinctTop3ByDivisionContains("Hea"));
        System.out.println(" ------------- Department End ------------- ");


        System.out.println(" ------------- Employee Starts ------------- ");
        System.out.println("employeeRepository.getEmployeeDetail() = " + employeeRepository.getEmployeeDetail());
        System.out.println("employeeRepository.getEmployeeSalary() = " + employeeRepository.getEmployeeSalary());
        System.out.println("employeeRepository.findByEmail = " + employeeRepository.findByEmail("ssymonds2@hhs.gov"));
        System.out.println("employeeRepository.getEmployeeDetail = " + employeeRepository.getEmployeeDetail("fmorffew4@a8.net",55307));
        System.out.println(" ------------- Employee End ------------- ");


        System.out.println(" ------------- Course Starts ------------- ");
        courseRepository.findByCategory("Spring").forEach(System.out::println);
        System.out.println("********************");
        courseRepository.findByCategoryOrderByName("Spring").forEach(System.out::println);
        System.out.println("********************");
        System.out.println("existsByName = " +courseRepository.existsByName("Spring"));
        System.out.println("********************");
        System.out.println("countByCategory= " +courseRepository.countByCategory("Spring"));
        System.out.println("********************");
        System.out.println("findByNameStartsWith = " +courseRepository.findByNameStartsWith("Scalable"));
        System.out.println("********************");
//        courseRepository.streamByCategory("Spring").forEach(System.out::println);
        System.out.println(" ------------- Course End ------------- ");










    }
}
