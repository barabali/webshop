package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DiscountTest {
	
	@Test
	public void testTimedDiscountAvailable(){
		Discount discount=createAvailableTimedDiscount();
		Assert.assertEquals(new BigDecimal("14.0"), discount.calculateDiscount(new BigDecimal(20)));
	}
	
	@Test
	public void testTimedDiscountNotAvailable(){
		Date now=new Date();
		
		Date before=(Date) now.clone();
		before.setYear(now.getYear()-1);
		
		TimedDiscount discount=new TimedDiscount("0.3",before,before);
		Assert.assertEquals(new BigDecimal("30"), discount.calculateDiscount(new BigDecimal(30)));
	}
	
	@Test
	public void testDailyDiscountOneDay(){
		
		//TODO: unused
		Discount timed=createAvailableTimedDiscount();
		Discount day=new DailyDiscount("0.3",Day.MONDAY);
		
		if(new Date().getDay()==Day.MONDAY.getValue())
			Assert.assertEquals(new BigDecimal("1400.0"),day.calculateDiscount(new BigDecimal("2000")));
		else
			Assert.assertEquals(new BigDecimal("2000"),day.calculateDiscount(new BigDecimal("2000")));
	}
	
	@Test
	public void testDailyDiscountAllDays(){
		Product product=new Product("test",new BigDecimal("2000"),new Category("testCategory"));
		List<Discount> discounts=new ArrayList<>();
		discounts.add(new DailyDiscount("0.3",Day.MONDAY));
		discounts.add(new DailyDiscount("0.3",Day.TUESDAY));
		discounts.add(new DailyDiscount("0.3",Day.WEDNESDAY));
		discounts.add(new DailyDiscount("0.3",Day.THURSDAY));
		discounts.add(new DailyDiscount("0.3",Day.FRIDAY));
		discounts.add(new DailyDiscount("0.3",Day.SATURDAY));
		discounts.add(new DailyDiscount("0.3",Day.SUNDAY));
		
		product.setDiscounts(discounts);
		Assert.assertEquals(new BigDecimal("1400"),product.getFinalPrice());
	}
	
	private Discount createAvailableTimedDiscount() {
		Date now=new Date();
		
		Date before=(Date) now.clone();
		before.setYear(now.getYear()-1);
		
		Date after=(Date) now.clone();
		after.setYear(now.getYear()+1);
		
		TimedDiscount discount=new TimedDiscount("0.3",before,after);
		return discount;
	}
	
}
