package giri.calendar;

import java.util.ArrayList;
import java.util.Scanner;

public class PlanItem {
	public String writer;
	public String detail;
	public ArrayList<String> attendees = new ArrayList<String>();

	public PlanItem(String writer, String detail) {
		this.writer = writer;
		this.detail = detail;
	}

	public void addAttendee(String attendee) {
		attendees.add(attendee);
	}

	public void removeAttendee(String attendee) {
		attendees.remove(attendee);
	}

	public void manageAttendees(Scanner sc) {
		while (true) {
			System.out.println();
			System.out.println("────────────────────────");
			System.out.println(" 1. 참석자 추가");
			System.out.println(" 2. 참석자 제외");
			System.out.println(" q. 메인 메뉴");
			System.out.println("────────────────────────");
			System.out.print("> ");
			String attendeesMenu = sc.nextLine();
			if (attendeesMenu.equals("1")) {
				System.out.println();
				System.out.println("추가할 참석자명");
				System.out.print("> ");
				String attendee = sc.nextLine();
				addAttendee(attendee);
			} else if (attendeesMenu.equals("2")) {
				System.out.println();
				System.out.println("제외할 참석자명");
				System.out.print("> ");
				String attendee = sc.nextLine();
				removeAttendee(attendee);
			} else if (attendeesMenu.toUpperCase().equals("Q")) {
				return;
			}
		}
	}

	public String saveString(String strDate) {
		String ret = "";
		ret += strDate + "," + writer + "," + detail + "," + attendees + "\n";
		return ret;
	}

	public void printPlanItem(String searchDate) {
		System.out.println();
		System.out.println("────────────────────────");
		System.out.println("       " + searchDate);
		System.out.println("────────────────────────");
		System.out.println(" 작성자: " + writer);
		System.out.println(" 일정: " + detail);
		System.out.println(" 참석자: " + attendees);
		System.out.println("────────────────────────");
	}
}
