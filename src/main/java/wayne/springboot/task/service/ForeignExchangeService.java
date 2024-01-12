package wayne.springboot.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wayne.springboot.task.bean.ForeignExchangeRate;
import wayne.springboot.task.repository.ForeignExchangeRateRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ForeignExchangeService {

    private final ForeignExchangeRateRepository foreignExchangeRateRepository;

    @Autowired
    public ForeignExchangeService(ForeignExchangeRateRepository foreignExchangeRateRepository) {
        this.foreignExchangeRateRepository = foreignExchangeRateRepository;
    }

    public void saveForeignExchangeRate(List<ForeignExchangeRate> data) {
        foreignExchangeRateRepository.saveAll(data);
    }

    public List<ForeignExchangeRate> getForeignExchangeRateByDateRange(LocalDate startDate, LocalDate endDate) {
        return foreignExchangeRateRepository.findForeignExchangeRateByDateRange(startDate, endDate);
    }

}
