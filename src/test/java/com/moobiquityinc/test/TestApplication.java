package com.moobiquityinc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.PackCreator;
import com.mobiquityinc.packer.PackPopulator;
import com.mobiquityinc.packer.Packer;

public class TestApplication {
	//Some tests were removed from here due change of access modifiers, like combination test.
	
	@Test
	public void shouldThrowApiExceptionDueItemProblem() {
		String line = "75 : (1,85.31,€102) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)";
		List<String> list = new ArrayList<>();
		list.add(line);
		PackPopulator populator = new PackPopulator();
		try {
			populator.create(list);
			fail();
		} catch (APIException e) {
			assertEquals("Error parsing due to following line \"75 : (1,85.31,€102) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)\"\n" + 
					"Item weight can't be bigger then 100\n" + 
					"Item cost can't be bigger then 100", e.getMessage());
		}
	}
	
	@Test
	public void shouldThrowApiExceptionDuePackProblem() {
		String line = "101 : (1,85.31,€10) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)";
		List<String> list = new ArrayList<>();
		list.add(line);
		PackPopulator populator = new PackPopulator();
		try {
			populator.create(list);
			fail();
		} catch (APIException e) {
			assertEquals("Error parsing due to following line \"101 : (1,85.31,€10) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)\"\n" + 
					"Total weight can't be bigger then 100\n" + 
					"Number of items can't be bigger then 15", e.getMessage());
		}
	}
	
	
	
	@Test
	public void shouldReturnNumberFour() {
		String line = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
		List<String> list = new ArrayList<>();
		list.add(line);
		PackPopulator populator = new PackPopulator();
		PackCreator create = new PackCreator();
		
		try {
			assertEquals(create.create(populator.create(list).get(0)),"4");
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void shouldReturnNumberMinusSignal() {
		String line = "8 : (1,15.3,€34)";
		List<String> list = new ArrayList<>();
		list.add(line);
		PackPopulator populator = new PackPopulator();
		PackCreator create = new PackCreator();
		
		try {
			assertEquals(create.create(populator.create(list).get(0)),"-");
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void shouldReturnFullSetViaFile() {
		try {
			assertEquals(Packer.pack("<Here it goes the path for the file>"), "4\n" + 
					"-\n" + 
					"2,7\n" + 
					"8,9\n");
			
		} catch (Exception e) {
			fail();
		}
	}
}
