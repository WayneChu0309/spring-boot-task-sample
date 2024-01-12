package wayne.springboot.task.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import wayne.springboot.task.bean.ForeignExchangeRate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface ForeignExchangeRateRepository extends MongoRepository<ForeignExchangeRate, String> {

    @Query("{ '_id' : { $gte: ?0, $lte: ?1 } }")
    List<ForeignExchangeRate> findForeignExchangeRateByDateRange(LocalDate startDate, LocalDate endDate);

}
