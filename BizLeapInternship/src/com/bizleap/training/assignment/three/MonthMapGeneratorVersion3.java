package com.bizleap.training.assignment.three;

	import java.util.List;
	import java.util.ArrayList;
	import java.util.Map;
	import java.util.Map.Entry;
	import java.util.HashMap;
	import java.util.Set;
	import java.util.Iterator;

	
	public class MonthMapGeneratorVersion3 {
	private Map<Integer,Object> monthMap=new HashMap<Integer,Object>();
		
		private class Month{
			private String name;
			private int numberOfDays,year;
			
			public Month(String name,int numberOfDays,int year){
				this.name=name;
				this.numberOfDays=numberOfDays;
				this.year=year;
			}
			
			public String toString() {
				return this.name+"/"+this.year;
			}
		}
		
		private boolean isValid(int year) {
			return year>=0;
		}
		
		private boolean isValid(int fromYear,int toYear) {
			return !(fromYear<=0 || toYear<=0 || fromYear>toYear);
		}
		
		public boolean isLeapYear(int year) {
			return year%4 == 0 && year%100 != 0 || year%400 == 0;
		}
		
		private List<Month> createMonthsForYear(int fromYear) {
			List<Month> monthList=new ArrayList<Month>();
			
			monthList.add(new Month("January", 31,fromYear));
			
			if(isLeapYear(fromYear))
				monthList.add(new Month("February", 29,fromYear));
		    else
		    	monthList.add(new Month("February", 28,fromYear));
			
			monthList.add(new Month("March", 31,fromYear));
			monthList.add(new Month("April",30,fromYear));
			monthList.add(new Month("May", 31,fromYear));
			monthList.add(new Month("June",30,fromYear));
			monthList.add(new Month("July", 31,fromYear));
			monthList.add(new Month("August", 31,fromYear));
			monthList.add(new Month("September", 30,fromYear));
			monthList.add(new Month("October", 31,fromYear));
			monthList.add(new Month("November",30,fromYear));
			monthList.add(new Month("December", 31,fromYear));
			
			return monthList;
		}
		
		public Map<Integer,Object> createMonthMap(int fromYear,int toYear) {
			if(isValid(fromYear,toYear)) {
				for(int year=fromYear;year<=toYear;year++) {
					createMonthMap(year);
				}	
			}
			else {
				monthMap.put(fromYear,"Range or years are invalid");
				monthMap.put(toYear,"Range or years are invalid");
			}
			return monthMap;
		}
		
		public Map<Integer,Object> createMonthMap(int year) {
			if(isValid(year)) {
				for(Month month:createMonthsForYear(year)) {
					addMonthToMap(month);
				}
			}
			else {
				monthMap.put(year, "Year is invalid");
			}
			return monthMap;
		}
		
		private List<Month> createMonthsForYearRange(int fromYear,int toYear) {
			List<Month> monthList=new ArrayList<Month>();
			
			for(int year=fromYear;year<=toYear; year++) {
				monthList.addAll(createMonthsForYear(year));
			}
			return monthList;
		}
		
		public Map<Integer,Object> createMonthMapRange(int fromYear,int toYear) {
			if(isValid(fromYear,toYear)) {
				for(Month month:createMonthsForYearRange(fromYear,toYear)) {
					addMonthToMap(month);
				}
			}
			else {
				monthMap.put(fromYear,"Range or years are invalid");
				monthMap.put(toYear,"Range or years are invalid");
			}
			return monthMap;
		}
		
		private void addMonthToMap(Month month) {
			List<Month> monthList=(List<Month>) monthMap.get(month.numberOfDays);
			
			if(monthList!=null)
				monthList.add(month);
			else {
				monthList=new ArrayList<Month>();
				monthList.add(month);
				monthMap.put(month.numberOfDays,monthList);
			}
		}
		
		
		public void prettyPrint(int toYear,int fromYear) {
			System.out.println("monthMap in the prettiest form");
			boolean newLineRequired=false;
			for(int key:monthMap.keySet()) {
				System.out.println("For Day "+key+":");
				for(int year=toYear; year<=fromYear; year++)
				{	
					newLineRequired=false;
					for(Month month : (List<Month>)monthMap.get(key)) {
						if(year==month.year) {
							System.out.print(month+"   ");
							newLineRequired=true;
						}
					}
					if(newLineRequired) {
					System.out.println();
					}
				}
				System.out.println();
			}
		}
		
		public static void main(String[] args) {
			int toYear=2000;
			int fromYear=2004;
			MonthMapGeneratorVersion3 monthMapGenerator=new MonthMapGeneratorVersion3();
			System.out.println("monthMap in raw type");
			System.out.println(monthMapGenerator.createMonthMap(toYear,fromYear));
			System.out.println();
			monthMapGenerator.prettyPrint(toYear,fromYear);
		}
	}

