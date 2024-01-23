import com.company.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
/*
 * 🔺 Unit Testing:
 * · Designed to test specific sections of code (Method, Class).
 * · Percentage of lines of code tested is code coverage. Ideal coverage is in the 70-80% range.
 * · Should be ‘unity’ and execute very fast.
 * · Should have no external dependencies.
 * 🔺 Integration Testing:
 * · Designed to test behaviors between objects and parts of the overall system.
 * · Much Larger Scope.
 * · Can include database, spring context, message brokers, etc.
 * · Will run much slower than unit tests.
 *
 * 🦋 TDD vs BDD
 * 🔺 TDD (Test Driven Development): Writes test first, which will fail, then code to fix test
 * · The process starts by writing a test case.
 * · TDD focuses on how the functionality is implemented.
 * · Test cases are written in a programming language.
 * · Changes in how the application functions impact a lot on the test cases in TDD.
 * · Collaboration is required only between the developers.
 * · Some of the tools which support TDD are: JUnit, TestNG, NUnit, etc.
 * · Tests in TDD can only be understood by people with programming knowledge.
 * · TDD reduces the likelihood of having bugs in your tests.
 * 🔺 BDD (Behavior Driven Development): Builds on TDD and specifies that tests of any unit software should be specified in terms of desired behavior of the unit
 * · The process starts by writing a scenario as per the expected behavior.
 * · BDD focuses on the behavior of an application for the end user.
 * · Scenarios are more readable when compared to TDD as they are written in simple English format.
 * · BDD scenarios are not much impacted by the functionality changes.
 * · Collaboration is required between all the stakeholders.
 * · Some of the tools which support BDD are SpecFlow, Cucumber, MSpec, etc.
 * · Tests in BDD can be understood by any person including the ones without any programming knowledge
 * · Bugs in tests are difficult to track when compared to TDD.
 *
 * 🦋 Annotations
 * · @Test: It is used to define a certain method as test method.
 * · @BeforeEach: It is used to signal that the annotated method should be executed before each @Test method in the current class.
 * · @AfterEach:  It is used to signal that the annotated method should be executed after each @Test method in the current class.
 * · @BeforeAll: It is used to signal that the annotated method should be executed before all tests in the current test class. The @BeforeAll method must be static.
 * · @AfterAll: It is used to signal that the annotated method should be executed after all tests in the current test class. The @AfterAll method must be static.
 * · Parameterized Tests(@ParameterizedTest): @ValueSource @MethodSource @CsvSource @CsvFileSource
 *   1. @ValueSource: It is used to provide a single parameter per test method. It lets you specify an array of literals or primitive types.
 *   2. @MethodSource: It is used to specify a factory method for test arguments.
 *      This method can be present in the same class or any other class too.
 *      The factory method should be static and return Stream, Iterable or array elements.
 *   3. @CsvSource: It is used to run tests that take a comma-separated values as arguments.
 *   4. @CsvFileSource: If we have to write a lot of test data in the test code it can make test less readable.
 *      One solution to this is to provide the data in an external CSV file. Each line from the file works as a list of parameters.
 *   5. @EmptySource: passes a single empty argument to the annotated method.
 *   6. @NullSource: passes a single null argument to the annotated method.
 *   7. @NullAndEmptySource: passes a single null argument to the annotated method and then passes a single empty argument to the annotated method.
 *
 * 🦋 Assertions
 * · assertTrue(); asserts that the supplied condition is true or boolean condition supplied by BooleanSupplier is true.
 * · assertArrayEquals(); It is used to verify if the two arrays are equals.
 * · assertNotNull(); method asserts that actual is NOT null.
 * · assertNull(); method asserts that actual is null.
 * · assertNotSame(); asserts that expected and actual DO NOT refer to the same object.
 * · assertSame(); method asserts that expected and actual refer to exactly same object.
 * · assertThrows(); It is used to test exception cases in JUnit5.
 *   It asserts that execution of the supplied executable throws an exception of the expectedType and returns the exception.
 *   If no exception is thrown, or if an exception of a different type is thrown, this method will fail.
 *
 */
public class CalculatorParameterizedTest {

    @ParameterizedTest // If we have the @ParameterizedTest annotation, we don't have to use the @Test annotation.
    @ValueSource(strings = {"Java", "JS", "TS"}) // This test will run three times as a parameter.
    void testCase1(String args) { // Array values will be assigned to the string args parameter one by one.
        Assertions.assertTrue(!args.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 6, 10})
    void testCase2(int number) {
        Assertions.assertEquals(0, number % 3);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Java", "JS", "TS"})
//    @EmptySource // AssertionFailedError
//    @NullSource //  NullPointerException
    @NullAndEmptySource
    void testCase3(String args) {
        Assertions.assertTrue(!args.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("stringProvider") // "stringProvider" is a method name from which we get the data.
    void testCase4(String arg) {
        Assertions.assertNotNull(arg);
    }
    static String[] stringProvider() {
        return new String[]{"Java", "JS", "TS"};
    }

    @ParameterizedTest
    @CsvSource({"10,20,30", "20,20,40", "30,20,100"}) // Each string will be assigned the num that we have on the method parameter.
    void testCase5(int num1, int num2, int result) {
        Assertions.assertEquals(result, Calculator.add(num1, num2)); // 10+20=30, 20+20=40,
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/sample-data.csv", numLinesToSkip = 1) // numLinesToSkip will skip the first row of the CSV file, which is the header.
    void testCase6(int num1, int num2, int result) {
        Assertions.assertEquals(result, Calculator.add(num1, num2));
    }



}
