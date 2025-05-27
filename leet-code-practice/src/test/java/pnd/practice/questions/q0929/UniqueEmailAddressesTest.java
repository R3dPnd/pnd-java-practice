package pnd.practice.questions.q0929;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UniqueEmailAddressesTest {
    private UniqueEmailAddresses uniqueEmails;

    @BeforeEach
    void setUp() {
        uniqueEmails = new UniqueEmailAddresses();
    }

    @Test
    void testBasicEmailFormat() {
        String[] emails = {"test@example.com"};
        assertEquals(1, uniqueEmails.numUniqueEmails(emails));
    }

    @Test
    void testDotsInLocalName() {
        String[] emails = {
            "test.email@example.com",
            "testemail@example.com"
        };
        assertEquals(1, uniqueEmails.numUniqueEmails(emails));
    }

    @Test
    void testPlusInLocalName() {
        String[] emails = {
            "test+spam@example.com",
            "test@example.com"
        };
        assertEquals(1, uniqueEmails.numUniqueEmails(emails));
    }

    @Test
    void testCombinedDotsAndPlus() {
        String[] emails = {
            "test.email+spam@example.com",
            "testemail@example.com"
        };
        assertEquals(1, uniqueEmails.numUniqueEmails(emails));
    }

    @Test
    void testMultipleUniqueEmails() {
        String[] emails = {
            "test.email+spam@example.com",
            "test.email+spam@other.com",
            "different@example.com"
        };
        assertEquals(3, uniqueEmails.numUniqueEmails(emails));
    }

    @Test
    void testLeetCodeExample() {
        String[] emails = {
            "alice.z@leetcode.com",
            "alicez@leetcode.com",
            "m.y+name@email.com",
            "my@email.com"
        };
        assertEquals(2, uniqueEmails.numUniqueEmails(emails));
    }
} 