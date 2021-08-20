package giri.calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Plan {
	private HashMap<Date, PlanItem> planMap;
	private static final String SAVE_FILE = "calendar.txt";
	File f = new File(SAVE_FILE);

	public Plan() {
		planMap = new HashMap<Date, PlanItem>();
	}

	public PlanItem registerPlan(String strDate, String writer, String detail, Scanner sc) throws ParseException {
		Date planDate = getDatefromString(strDate);
		PlanItem pi = new PlanItem(writer, detail);
		pi.manageAttendees(sc);

		planMap.put(planDate, pi);

		String item = pi.saveString(strDate);

		try {
			FileWriter fw = new FileWriter(f, true);
			fw.write(item);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pi;
	}

	public PlanItem searchPlan(String strDate, Scanner sc) throws ParseException, IOException {
		FileReader fr = new FileReader(f);
		fr.read();
		
		PlanItem pi = planMap.get(getDatefromString(strDate));
		pi.printPlanItem(strDate);
		pi.manageAttendees(sc);

		String item = pi.saveString(strDate);

		try {
			FileWriter fw = new FileWriter(f, true);
			fw.write(item);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pi;
	}

	public Date getDatefromString(String strDate) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
	}
}
