package com.example.currencyconverter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CurrencyServiceTest {

    @Test
    void shouldConvertUsdToInr() {
        CurrencyService service = new CurrencyService();

        double converted = service.convert(100, "USD", "INR");

        assertEquals(8312.0, converted, 0.01);
    }

    @Test
    void shouldReturnSameValueForSameCurrency() {
        CurrencyService service = new CurrencyService();

        double converted = service.convert(250, "EUR", "EUR");

        assertEquals(250.0, converted, 0.01);
    }

    @Test
    void shouldRejectInvalidCurrency() {
        CurrencyService service = new CurrencyService();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.convert(50, "USD", "XYZ"));

        assertEquals("Please select valid currencies.", exception.getMessage());
    }
}
