package wayne.springboot.task.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wayne.springboot.task.bean.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ForexService {

    @Autowired
    ForeignExchangeService foreignExchangeService;


    @PostMapping("/forex")
    public ForexRs getForex(@RequestBody ForexRq forexRq) {
        LocalDate startDate = forexRq.getStartDate();
        LocalDate endDate = forexRq.getEndDate();
        LocalDate current  = LocalDate.now();
        boolean flag = true;
        ForexRsError forexRsError = new ForexRsError();
        ForexRs forexRs = new ForexRs();
        forexRs.setError(forexRsError);

        if (!"usd".equalsIgnoreCase(forexRq.getCurrency())) {
            flag = false;
        } else if (endDate.compareTo(startDate) < 0) {
            flag = false;
        } else if (endDate.compareTo(current) >= 0) {
            flag = false;
        } else if (startDate.compareTo(current.minusYears(1)) < 0) {
            flag = false;
        }

        if (flag) {
            List<ForeignExchangeRate> foreignExchangeRates = foreignExchangeService.getForeignExchangeRateByDateRange(startDate, endDate);
            foreignExchangeRates.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
            forexRsError.setCode("0000");
            forexRsError.setMessage("成功");
            List<Currency> currencies = new ArrayList<>();
            for (ForeignExchangeRate foreignExchangeRate : foreignExchangeRates) {
                Currency currency = new Currency();
                currency.setDate(foreignExchangeRate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                currency.setUsd(foreignExchangeRate.getUsdToNtd());
                currencies.add(currency);
            }

            forexRs.setCurrencies(currencies);
            return forexRs;
        }

        forexRsError.setCode("E001");
        forexRsError.setMessage("日期區間不符");

        return forexRs;

    }
}
