package giri.calendar;

import java.util.Calendar;
import java.util.Scanner;

public class CalendarEx {

	private static final int[] MAX_DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final int[] LEAP_MAX_DAYS = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public boolean isLeapYear(int year) {
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			return true;
		}
		return false;
	}

	public int getMaxDaysOfMonth(int year, int month) {
		if (isLeapYear(year))
			return LEAP_MAX_DAYS[month - 1];
		else
			return MAX_DAYS[month - 1];
	}

	public int getWeekDay(int year, int month) {
		final int SYEAR = 1970;
		final int SWEEK = 4;

		int countDay = 0;

		for (int i = SYEAR; i < year; i++) {
			countDay += isLeapYear(i) ? 366 : 365;
		}

		for (int i = 1; i < month; i++) {
			countDay += getMaxDaysOfMonth(year, i);
		}

		int nWeek = (SWEEK + countDay) % 7;

		return nWeek;
	}

	public void printCalendar(int year, int month) {
		System.out.println();
		System.out.printf("   << %d년%2d월 >>\n", year, month);
		System.out.println(" SU MO TU WE TH FR SA");
		System.out.println("---------------------");

		int weekDay = getWeekDay(year, month);

		int maxDay = getMaxDaysOfMonth(year, month);

		for (int i = 1 - weekDay; i <= maxDay; i++) {
			if (i < 1)
				System.out.print("   ");
			else
				System.out.printf("%3d", i);

			if (weekDay == 0) {
				if (i % 7 == weekDay)
					System.out.println();
			} else if (i % 7 == 7 - weekDay) {
				System.out.println();
			}
		}
		System.out.println("\n---------------------");
	}

	public void showCalendar(Scanner sc, Calendar cal) {
		System.out.println();
		System.out.println("────────────────────────");
		System.out.println(" 1. 현재 달력");
		System.out.println(" 2. 검색 달력");
		System.out.println("────────────────────────");
		System.out.print("> ");

		int year = 0, month = 0;
		int calMenu = sc.nextInt();
		if (calMenu == 1) {
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
		} else if (calMenu == 2) {
			System.out.println();
			System.out.print("YEAR> ");
			year = sc.nextInt();
			System.out.println("MONTH> ");
			month = sc.nextInt();
		}

		printCalendar(year, month);

		while (true) {
			System.out.println(" q. 메인 메뉴");
			System.out.print("> ");
			if ("Q".equals(sc.next().toUpperCase())) {
				return;
			}
		}
	}
}
