package com.example.currencyconverter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class CurrencyController {
    private final CurrencyService currencyService = new CurrencyService();

    private static final Map<String, String> CURRENCIES = new LinkedHashMap<>();

    static {
        CURRENCIES.put("USD", "US Dollar");
        CURRENCIES.put("INR", "Indian Rupee");
        CURRENCIES.put("EUR", "Euro");
        CURRENCIES.put("GBP", "British Pound");
        CURRENCIES.put("JPY", "Japanese Yen");
        CURRENCIES.put("AUD", "Australian Dollar");
        CURRENCIES.put("CAD", "Canadian Dollar");
        CURRENCIES.put("SGD", "Singapore Dollar");
        CURRENCIES.put("CHF", "Swiss Franc");
        CURRENCIES.put("CNY", "Chinese Yuan");
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("currencies", CURRENCIES);
        model.addAttribute("result", null);
        model.addAttribute("error", null);
        model.addAttribute("amount", "");
        model.addAttribute("fromCurrency", "USD");
        model.addAttribute("toCurrency", "INR");
        return "index";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam String amount,
                          @RequestParam String fromCurrency,
                          @RequestParam String toCurrency,
                          Model model) {
        try {
            double amountValue = Double.parseDouble(amount);
            double converted = currencyService.convert(amountValue, fromCurrency, toCurrency);
            String result = String.format("%.2f %s = %.2f %s",
                    amountValue, fromCurrency.toUpperCase(), converted, toCurrency.toUpperCase());

            model.addAttribute("currencies", CURRENCIES);
            model.addAttribute("result", result);
            model.addAttribute("error", null);
            model.addAttribute("amount", amount);
            model.addAttribute("fromCurrency", fromCurrency.toUpperCase());
            model.addAttribute("toCurrency", toCurrency.toUpperCase());
            return "index";
        } catch (NumberFormatException e) {
            model.addAttribute("currencies", CURRENCIES);
            model.addAttribute("result", null);
            model.addAttribute("error", "Please enter a valid numeric amount.");
            model.addAttribute("amount", amount);
            model.addAttribute("fromCurrency", fromCurrency.toUpperCase());
            model.addAttribute("toCurrency", toCurrency.toUpperCase());
            return "index";
        } catch (IllegalArgumentException e) {
            model.addAttribute("currencies", CURRENCIES);
            model.addAttribute("result", null);
            model.addAttribute("error", e.getMessage());
            model.addAttribute("amount", amount);
            model.addAttribute("fromCurrency", fromCurrency.toUpperCase());
            model.addAttribute("toCurrency", toCurrency.toUpperCase());
            return "index";
        }
    }
}
