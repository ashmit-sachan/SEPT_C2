package com.s3873827.account;

import com.s3873827.account.enums.AccountType;
import com.s3873827.account.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class AccountApplicationTests {

    @Test
    @DisplayName("Test 1: Should pass if Account has sufficient balance, ie. more than 100$")
    void hasValidAge() {
        Account account = new Account(0L, AccountType.SAVING, "7896123786489681", "Max", "999", new Timestamp(System.currentTimeMillis()));

        Assertions.assertTrue(Integer.parseInt(account.getBalance()) > 99);
    }

    @Test
    @DisplayName("Test 2: Should pass if Account number has 16 digits")
    void hasComplete10DigitPhoneNumber() {
        Account account = new Account(0L, AccountType.SAVING, "1234567890112233", "Max", "999", new Timestamp(System.currentTimeMillis()));

        Assertions.assertEquals(16, account.getAccNumber().length());
    }

}
