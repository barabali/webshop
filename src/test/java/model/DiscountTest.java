package model;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DiscountTest {
	
	@Test
	public void testTimedDiscountAvailable(){
		Date now=new Date();
		
		Date before=(Date) now.clone();
		before.setYear(now.getYear()-1);
		
		Date after=(Date) now.clone();
		after.setYear(now.getYear()+1);
		
		TimedDiscount discount=new TimedDiscount("0.3",before,after);
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
	
}
