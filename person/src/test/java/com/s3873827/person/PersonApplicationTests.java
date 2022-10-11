package com.s3873827.person;

import com.s3873827.person.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class PersonApplicationTests {

    @Test
    @DisplayName("Test 1: Should pass if person's age is in the a desired time period (Last 100 years and not in Future)")
    void hasValidAge() {
        Person person = new Person(0L, "Ashmit", "Address", "3052", "32", "Job", "example@gmail.com", "1234567890");

        Assertions.assertTrue(Integer.parseInt(person.getAge()) > 0 || Integer.parseInt(person.getAge()) < 101);
    }

    @Test
    @DisplayName("Test 2: Should pass if person's phone number has 10 digits")
    void hasComplete10DigitPhoneNumber() {
        Person person = new Person(0L, "Ashmit", "Address", "3052", "32", "Job", "example@gmail.com", "1234567890");

        Assertions.assertEquals(10, person.getPhoneno().length());
    }

    @Test
    @DisplayName("Test 3: Should pass if person's email has a valid syntax")
    void hasValidEmailAddress() {
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Person person = new Person(0L, "Ashmit", "Address", "3052", "32", "Job", "example#$csd{}gmail.com", "1234567890");
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(person.getEmail());

        Assertions.assertFalse(matcher.find());
    }

}
