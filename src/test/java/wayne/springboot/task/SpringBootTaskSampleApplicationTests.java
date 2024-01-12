package wayne.springboot.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wayne.springboot.task.Scheduler.DailyTaskScheduler;
import wayne.springboot.task.bean.ForexRq;
import wayne.springboot.task.bean.ForexRs;
import wayne.springboot.task.service.ForeignExchangeService;
import wayne.springboot.task.service.ForexService;

import java.time.LocalDate;

@SpringBootTest
class SpringBootTaskSampleApplicationTests {

	@Autowired
	DailyTaskScheduler dailyTaskScheduler;

	@Autowired
	ForexService forexService;


	@Test
	void contextLoads() {
	}



	@Test
	void testScheduled(){
		Boolean result = dailyTaskScheduler.executeTask();
		assert result;
	}


	@Test
	void testGetForexSuccess(){
		ForexRq forexRq = new ForexRq();
		LocalDate startDate = LocalDate.now().minusYears(1);
		LocalDate endDate = LocalDate.now().minusDays(1);
		forexRq.setStartDate(startDate);
		forexRq.setEndDate(endDate);
		forexRq.setCurrency("usd");

		ForexRs forexRs = forexService.getForex(forexRq);
		assert "0000".equals(forexRs.getError().getCode());
	}

	@Test
	void testGetForexWithStartDateEqEndDate(){
		ForexRq forexRq = new ForexRq();
		LocalDate startDate = LocalDate.now().minusDays(1);
		LocalDate endDate = LocalDate.now().minusDays(1);
		forexRq.setStartDate(startDate);
		forexRq.setEndDate(endDate);
		forexRq.setCurrency("usd");

		ForexRs forexRs = forexService.getForex(forexRq);
		assert "0000".equals(forexRs.getError().getCode());
	}

	@Test
	void testGetForexWithCurrencyNotUSD(){
		ForexRq forexRq = new ForexRq();
		LocalDate startDate = LocalDate.now().minusDays(1);
		LocalDate endDate = LocalDate.now().minusDays(1);
		forexRq.setStartDate(startDate);
		forexRq.setEndDate(endDate);
		forexRq.setCurrency("usd1");

		ForexRs forexRs = forexService.getForex(forexRq);
		assert "E001".equals(forexRs.getError().getCode());
	}


	@Test
	void testGetForexWithStartDateAfterEndDate(){
		ForexRq forexRq = new ForexRq();
		LocalDate startDate = LocalDate.now().minusDays(1);
		LocalDate endDate = LocalDate.now().minusDays(2);
		forexRq.setStartDate(startDate);
		forexRq.setEndDate(endDate);
		forexRq.setCurrency("usd");

		ForexRs forexRs = forexService.getForex(forexRq);
		assert "E001".equals(forexRs.getError().getCode());
	}

	@Test
	void testGetForexWithStartDateBeforeOneYear(){
		ForexRq forexRq = new ForexRq();
		LocalDate startDate = LocalDate.now().minusDays(1).minusYears(1);
		LocalDate endDate = LocalDate.now().minusDays(1);
		forexRq.setStartDate(startDate);
		forexRq.setEndDate(endDate);
		forexRq.setCurrency("usd");

		ForexRs forexRs = forexService.getForex(forexRq);
		assert "E001".equals(forexRs.getError().getCode());
	}

	@Test
	void testGetForexWithEndDateAfterCurrentDateMinusOneDay(){
		ForexRq forexRq = new ForexRq();
		LocalDate startDate = LocalDate.now().minusYears(1);
		LocalDate endDate = LocalDate.now();
		forexRq.setStartDate(startDate);
		forexRq.setEndDate(endDate);
		forexRq.setCurrency("usd");

		ForexRs forexRs = forexService.getForex(forexRq);
		assert "E001".equals(forexRs.getError().getCode());
	}





}
