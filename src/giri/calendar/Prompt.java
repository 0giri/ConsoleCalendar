package giri.calendar;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Prompt {

	public void printMenu(Calendar cal, SimpleDateFormat sdf) {
		System.out.println();
		System.out.println("────────────────────────");
		System.out.println("  오늘 날짜 : " + sdf.format(cal.getTime()));
		System.out.println("────────────────────────");
		System.out.println(" 1. 일정 등록 🗒");
		System.out.println(" 2. 일정 검색 🔍");
		System.out.println(" 3. 달력 보기 📅");
		System.out.println(" h. 도움말 📚 / q. 종료 🙋🏻‍♂️");
		System.out.println("────────────────────────");
		System.out.print("> ");
	}

	public void runPrompt() throws ParseException, IOException {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		CalendarEx calEx = new CalendarEx();
		Plan plan = new Plan();
		Calendar cal = new GregorianCalendar();
		boolean flag = true;

		while (flag) {
			printMenu(cal, sdf);
			String menu = sc.next().toUpperCase();
			switch (menu) {
			case "1":
				System.out.println("날짜> ");
				sc.nextLine();
				String strDate = sc.nextLine();
				System.out.println("작성자> ");
				String writer = sc.nextLine();
				System.out.println("일정> ");
				String detail = sc.nextLine();
				PlanItem pi1 = plan.registerPlan(strDate, writer, detail, sc);
				break;
			case "2":
				System.out.println();
				System.out.println("날짜> ");
				sc.nextLine();
				String searchDate = sc.nextLine();
				PlanItem pi = plan.searchPlan(searchDate, sc);
				break;
			case "3":
				calEx.showCalendar(sc, cal);
				break;
			case "H":
				helpUser();
				break;
			case "Q":
				System.out.println("Bye~");
				flag = false;
				break;
			default:
				System.out.println("잘못된 입력");
				break;
			}
		}
		sc.close();
	}

	private void helpUser() {
		System.out.println();
		System.out.println("도움말 따위는 없지!");
	}

	public static void main(String[] args) throws ParseException, IOException {
		Prompt p = new Prompt();
		p.runPrompt();
	}
}
