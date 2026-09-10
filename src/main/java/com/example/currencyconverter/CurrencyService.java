package com.example.currencyconverter;

import java.util.Map;

public class CurrencyService {
    private static final Map<String, Double> RATES = Map.of(
            "USD", 1.0,
            "INR", 83.12,
            "EUR", 0.92,
            "GBP", 0.79,
            "JPY", 157.25,
            "AUD", 1.52,
            "CAD", 1.36,
            "SGD", 1.35,
            "CHF", 0.89,
            "CNY", 7.25
    );

    public double convert(double amount, String fromCurrency, String toCurrency) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        String from = fromCurrency.toUpperCase();
        String to = toCurrency.toUpperCase();

        if (!RATES.containsKey(from) || !RATES.containsKey(to)) {
            throw new IllegalArgumentException("Please select valid currencies.");
        }

        if (from.equals(to)) {
            return amount;
        }

        double usdValue = amount / RATES.get(from);
        return usdValue * RATES.get(to);
    }
}
