import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class PersonaBook {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		ArrayList<ArrayList<String>> db_Entries = new ArrayList<ArrayList<String>>();
		String msg="";

		String horizontalSeparator = "+---------------------------------------------+";
		while(true) {
			System.out.println("\n\n"+horizontalSeparator);
			System.out.printf("|%-16s%-29s|%n", "","PersonaBook");
			System.out.println(horizontalSeparator);
			System.out.printf("|%-45s|%n", "1. Add Entry");
			System.out.println(horizontalSeparator);
			System.out.printf("|%-45s|%n", "2. Delete Entry");
			System.out.println(horizontalSeparator);
			System.out.printf("|%-45s|%n", "3. View All Entries");
			System.out.println(horizontalSeparator);
			System.out.printf("|%-45s|%n", "4. Update Entry");
			System.out.println(horizontalSeparator);
			System.out.printf("|%-45s|%n", "5. Delete All Entries");
			System.out.println(horizontalSeparator);
			System.out.printf("|%-45s|%n", "6. Search an Entry");
			System.out.println(horizontalSeparator);
			System.out.printf("|%-45s|%n", "0. Exit Program");
			System.out.println(horizontalSeparator);
			System.out.print(
				msg+"\n"+
				"\nEnter choice: "
			);

			switch(input.nextInt()) {
				case 1:
					db_Entries = addEntry(db_Entries);
					msg = "ENTRY ADDED!";
					break;
				case 2:
					viewEntries(db_Entries);
					db_Entries = removeEntry(db_Entries);
					msg = "ENTRY REMOVED!";
					break;
				case 3:
					viewEntries(db_Entries);
					System.out.print("Press Enter to continue...");
					try {System.in.read();} catch(Exception e){}
					msg = "ENTRY UPDATED!";
					break;
				case 4:
					viewEntries(db_Entries);
					db_Entries = updateEntry(db_Entries);
					msg = "ENTRY UPDATED!";
					break;
				case 5:
					db_Entries = removeAll(db_Entries);
					msg = "ALL ENTRIES REMOVED!";
					break;
				case 6:
					searchEntry(db_Entries);
					System.out.print("Press Enter to continue...");
					try {System.in.read();} catch(Exception e){}
					msg ="";
					break;
				case 0: System.exit(0);
				default: System.out.println("\n!!! Invalid choice !!!"); msg ="";
			}
		}
	}

	static ArrayList<ArrayList<String>> addEntry(ArrayList<ArrayList<String>> db_Temp) {
		ArrayList<String> db_Row = new ArrayList<String>();

		input.nextLine();
		System.out.printf("%n%-20s%n%n", "ADD ENTRY");
		System.out.print("Name: ");
		db_Row.add(input.nextLine());
		System.out.print("Age: ");
		db_Row.add(input.next());
		db_Temp.add(db_Row);
		return db_Temp;
	}

	static ArrayList<ArrayList<String>> removeEntry(ArrayList<ArrayList<String>> db_Temp) {
		int entryNum = 0;

		input.nextLine();
		while(true) {
			System.out.printf("%n%-20s%n%n", "REMOVE ENTRY");
			System.out.print("Entry No. (Negative numbers cancels selection): ");
			entryNum = input.nextInt();
			if(entryNum >= db_Temp.size()) continue;
			else if(entryNum < 0) break;
			System.out.println(
				"Are you sure you want to remove entry "+entryNum+
				" with details: "+db_Temp.get(entryNum)+"? Y | N"
			);
			if(input.next().toLowerCase().charAt(0) == 'y') db_Temp.remove(entryNum);
			break;
		}
		return db_Temp;
	}

	static void viewEntries(ArrayList<ArrayList<String>> db_Temp) {
		String horizontalSeparator = "+---------------------------------------------+";
		String sidedHSeparator = "+-----+---------------------------------+-----+";

		System.out.println("\n\n"+horizontalSeparator);
		System.out.printf("|%-16s%-29s|%n", "","All Entries");
		System.out.println(sidedHSeparator);
		System.out.printf("|%-1s|%-33s|%1s|%n", " No. "," Name"," Age ");
		System.out.println(sidedHSeparator);
		
		int i = 0;
		for(ArrayList<String> t : db_Temp) {
			System.out.printf("|%05d|%-33s|%-5s|%n",i,t.get(0),t.get(1));
			i++;
			System.out.println(sidedHSeparator);
		}
		
	}

	static ArrayList<ArrayList<String>> updateEntry(ArrayList<ArrayList<String>> db_Temp) {
		ArrayList<String> db_Row = new ArrayList<String>();

		int entryNum = 0;

		input.nextLine();
		breakOps:
		while(true) {
			System.out.printf("%n%-20s%n%n", "UPDATE ENTRY");
			System.out.print("Entry No. (Negative numbers cancels selection): ");
			entryNum = input.nextInt();
			if(entryNum >= db_Temp.size()) continue;
			else if(entryNum < 0) break;
			System.out.println(
				"Are you sure you want to update entry "+entryNum+
				" with details: "+db_Temp.get(entryNum)+"? Y | N"
			);
			if(input.next().toLowerCase().charAt(0) == 'y') {
				input.nextLine();
				System.out.print("Name: ");
				db_Row.add(input.nextLine());
				System.out.print("Age: ");
				db_Row.add(input.next());
				db_Temp.set(entryNum, db_Row);
			}
			break;
		}
		return db_Temp;
	}

	static ArrayList<ArrayList<String>> removeAll(ArrayList<ArrayList<String>> db_Temp) {
		input.nextLine();
		while(true) {
			System.out.printf("%n%-20s%n%n", "REMOVE ALL ENTRIES");
			System.out.println("Are you sure you want to remove all entries? Y | N");
			if(input.next().toLowerCase().charAt(0) == 'y') db_Temp.clear();
			break;
		}
		return db_Temp;
	}

	static void searchEntry(ArrayList<ArrayList<String>> db_Temp) {
		String tag = "";
		String horizontalSeparator = "+---------------------------------------------+";
		String sidedHSeparator = "+-----+---------------------------------+-----+";
		boolean isName = false;

		input.nextLine();
		System.out.printf("%n%-20s%n%n", "SEARCH ENTRIES");
		System.out.println("Search for name? Y | N If No, Age will be searched instead.");
		if(input.nextLine().toLowerCase().charAt(0) == 'y') isName = true;
		System.out.print("Search tag: ");
		tag = input.nextLine();

		System.out.println("\n\n"+horizontalSeparator);
		System.out.printf("|%-16s%-29s|%n", "","Found Entries");
		System.out.println(sidedHSeparator);
		System.out.printf("|%-1s|%-33s|%1s|%n", " No. "," Name"," Age ");
		System.out.println(sidedHSeparator);

		int i = 0, found = 0;
		for(ArrayList<String> t : db_Temp) {
			String toCompare = isName ? t.get(0).toLowerCase() : t.get(1);
			if(toCompare.equals(tag.toLowerCase())) {
				System.out.printf("|%05d|%-33s|%-5s|%n",i,t.get(0),t.get(1));
				System.out.println(sidedHSeparator);
			}
			i++;
		}
	}


}