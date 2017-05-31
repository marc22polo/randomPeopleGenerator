import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class RandomUsers {
	public static String[] namesm = {"Marko","Andraž","Aleks","Mark","France","Jure","Esad","Niki","Janez","Lojze","Ivan","Beno","Radivoj","Miha","Oton","Rihard","Zdravko","Radovan","Lari","Luka","Jernej","Boris","Boštjan","Gregor","Rok","Anej","Aleksander","Klavdij","Uroš","Egon","Damjan","Bojan","David","Gorazd","Julijan","Aladin","Rajko","Simon","Rudolf","Andrej","Vinko","Janko","Mario","Slavko","Tine","Stanislav","Vladimir","Bogoljub","Ljubo","Slobodan","Erik","Filip"};
	public static String[] namesf = {"Vanja","Valentina","Valerija","Anka","Anika","Marija","Jožefa","Maša","Lara","Nina","Nuša","Mojca","Nika","Polona","Nataša","Beti","Veronika","Angelika","Patricija","Sara","Sofija","Alina","Manca","Ana","Brina","Miša","Aleksandra","Andreja","Martina","Gizela","Bojana","Janja","Tanja","Tjaša","Hedvika","Meta","Maja","Maruša","Manca","Bogomila","Anotinija","Cvetka","Dora","Alojzija","Lojzka","Jožica","Silvana","Silva","Tina","Loti","Hana","Neja"};
	public static String[] surnames = {"Novak","Kožuh","Valentinčič","Perinčič","Marinič","Ščurek","Belica","Demšar","Ambroželj","Filej","Plesničar","Žele","Ivančič","Bučar","Kovačič","Potočnik","Mlakar","Kos","Vidmar","Burgič","Sorčan","Arčon","Hozanović","Mugerli","Bizjak","Mislej","Kokošar","Boltar","Lazar","Laščak","Novšak","Velišček","Mohar","Rebula","Devetak","Korošec","Gabrijelčič","Makarovič","Mozetič","Komel","Škrlec","Munih","Šorli","Čuk","Čelik","Bratina","Modrijančič","Vouk","Kofol","Bremec"};
	public static String[] organizacije = {"ŠC Nova Gorica","HIT","Policija Tolmin","Rdeči križ Slovenije","Unicef","IGN","Unesco","who","Karitas","zzst","SŽ","Avrigo","Fia","ssz","zzzs","OŠ Podbrdo","Garfield","Dobro","Mercator","Spar","Lidl","Metalflex","Hidria","Prijon","Knjižnica Tolmin","Gostol","Avtobum","Tik Kobarid","Pekarna Hlebček","Tukaj","Salonit Anhovo","Rut d.o.o."};
	private static String datrojstva;
	public static ArrayList<Oseba> people = new ArrayList<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("please enter the ammount of users you want to generate: ");
		int ammount = sc.nextInt();
		System.out.print("Do you want to create SQL data (y/n): ");
		boolean createSQL = sc.next().charAt(0) == 'y' ? true : false;
		String tableName = "";
		String[] param = {"emso","davcna" ,"name","surname","dob","organization","username","password"};
		String parameters = "";
		if(createSQL){
			System.out.print("Enter the table name: ");
			tableName = sc.next();
			System.out.print("Enter parameters (name, surname, emso, davcna, dob, organization, username, password) type \"all\" for everything (example: name,surname,emso): ");
			parameters = sc.next();
		}
		sc.close();
		ArrayList<String> emsoti = new ArrayList<>();
		ArrayList<String> usernames = new ArrayList<>();
		ArrayList<String> davcne = new ArrayList<>();
		for(int i = 0; i < ammount; i++){
			String emso = generateEmso();
			while(emso.length() > 13)
				emso = generateEmso();
			if(emsoti.contains(emso)){
				i--;
				continue;
			}
			emsoti.add(emso);
			String name = "";
			String surname = surnames[(int)(Math.random()*(surnames.length)+0)];
			if(emso.charAt(9) == '5')
				name = namesf[(int)(Math.random()*(namesf.length)+0)];
			else
				name = namesm[(int)(Math.random()*(namesm.length)+0)];
			String upime = (name+"."+surname).toLowerCase();
			int j = 1;
			while(usernames.contains(upime)){
				if(j == 1)
					upime += j;
				else if(j < 10)
					upime = upime.substring(0, upime.length()-1) + j;
				else if(j < 100)
					upime = upime.substring(0, upime.length()-2) + j;
				j++;
			}
			String organizacija = organizacije[(int)(Math.random()*(organizacije.length))+0];
			String geslo = generatePassword();
			String davcna = generateDavnca();
			while(davcne.contains(davcna))
				davcna = generateDavnca();
			people.add(new Oseba(name, surname, emso, davcna, datrojstva, organizacija, upime, geslo));
		}
		if(!createSQL){
			System.out.printf("%12s", "name");
			System.out.printf("%13s", "surname");
			System.out.printf("%15s", "emso");
			System.out.printf("%10s", "davcna");
			System.out.printf("%12s", "DOB");
			System.out.printf("%22s", "organization");
			System.out.printf("%23s", "username");
			System.out.printf("%18s", "password");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			for(Oseba x : people){
				x.getOseba();
			}
			System.out.println("-----------------------------------------------------------------------------------------------------------");
		}
		if(createSQL){
			ArrayList<String> parama = new ArrayList<>(Arrays.asList(param));
			if(parameters.equals("all"))
				printSQLUsers(parama, tableName);
			else{
				boolean removed = true;
				String[] tmp = parameters.split(",");
				for(int i = 0; i < tmp.length; i++){
					if(tmp[i].charAt(0) == '-')
						parama.remove(tmp[i].substring(1, tmp[i].length()));
					else if(tmp[i].charAt(0) != '-'){
						if(removed){
							removed = false;
							parama.clear();
						}
						parama.add(tmp[i]);
					}
				}
				printSQLUsers(parama,tableName);
			}
			System.out.println();
		}
//		printSortedPeople();
	}
	
	public static String generateEmso(){
		int[] emso = new int[13];
		int[] stx = {7,6,5,4,3,2,7,6,5,4,3,2};
		int vs = 0;
		emso[7] = 5;
		emso[8] = 0;
		int month = (int)(Math.random()*12+1);
		int day;
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
			day = (int)(Math.random()*31+1);
		else if(month == 2)
			day = (int)(Math.random()*28+1);
		else 
			day = (int)(Math.random()*30+1);
		int year = (int)(Math.random()*51+1950);
		int sex = (int)(Math.random()*2+1);
		setDateOfBirth(year, month, day);
		year = year % 1000;
		if(sex == 1)
			emso[9] = 0;
		else if(sex == 2)
			emso[9] = 5;
		int zaporednast = (int)(Math.random()*99+1);
		if(day < 10){
			emso[0] = 0;
			emso[1] = day;
		}
		else {
			emso[1] = day % 10;
			emso[0] = day / 10;
		}
		if(month < 10){
			emso[2] = 0;
			emso[3] = month;
		}
		else {
			emso[3] = month % 10;
			emso[2] = month / 10;
		}
		if(year < 100){
			emso[4] = 0;
			if(year < 10){
				emso[5] = 0;
				emso[6] = year;
			}
			else{
				emso[6] = year % 10;
				emso[5] = year / 10;
			}
		}
		else{
			emso[6] = year % 10;
			emso[5] = (year % 100) / 10;
			emso[4] = year / 100;
		}
		if(zaporednast < 10){
			emso[10] = 0;
			emso[11] = zaporednast;
		}
		else {
			emso[11] = zaporednast % 10;
			emso[10] = zaporednast / 10;
		}
		for(int i = 0; i < stx.length; i++){
			vs += stx[i] * emso[i];
		}
		int ostanek = vs % 11;
		emso[12] = 11 - ostanek;
		String emsos = "";
		for(int i = 0; i < emso.length; i++){
			emsos += emso[i];
		}
		return emsos;
	}
	
	public static void setDateOfBirth(int year, int month, int day){
		datrojstva = year+"-";
		if(month < 10)
			datrojstva += "0"+month+"-";
		else
			datrojstva += month+"-";
		if(day < 10)
			datrojstva += "0"+day;
		else 
			datrojstva += day+"";
	}

	public static String generatePassword(){
		int x = 0;
		String password = "";
		for(int i = 0; i < 16; i++){
			 x = (int)(Math.random()*3+1);
			 switch(x){
			 case 1: 
				 password += (int)(Math.random()*10+0);
				 break;
			 case 2: 
				 password += (char)(int)(Math.random()*26+97); 
				 break;
			 case 3:
				 password += (char)(int)(Math.random()*26+65);
				 break; 
			 }
		}
		return password;
	}
	
	public static String generateDavnca(){
		return (int)(Math.random()*90000000+10000000)+"";
	}
	
	public static void printSQLUsers(ArrayList<String> param, String tableName){
		System.out.println();
		for(Oseba x : people){
			System.out.print("INSERT INTO "+tableName+" VALUES (");
			for(int i = 0; i < param.size(); i++){
				switch(param.get(i)){
				case "emso":
					if(i == param.size()-1)
						System.out.print("\'"+x.getEmso()+"\'");
					else 
						System.out.print("\'"+x.getEmso()+"\', ");
					break;
				case "davcna":
					if(i == param.size()-1)
						System.out.print("\'"+x.getDavcna()+"\'");
					else 
						System.out.print("\'"+x.getDavcna()+"\', ");
					break;
				case "name":
					if(i == param.size()-1)
						System.out.print("\'"+x.getName()+"\'");
					else 
						System.out.print("\'"+x.getName()+"\', ");
					break;
				case "surname":
					if(i == param.size()-1)
						System.out.print("\'"+x.getSurname()+"\'");
					else 
						System.out.print("\'"+x.getSurname()+"\', ");
					break;
				case "dob":
					if(i == param.size()-1)
						System.out.print("\'"+x.getDOB()+"\'");
					else 
						System.out.print("\'"+x.getDOB()+"\', ");
					break;
				case "organization":
					if(i == param.size()-1)
						System.out.print("\'"+x.getOrganizacija()+"\'");
					else 
						System.out.print("\'"+x.getOrganizacija()+"\', ");
					break;
				case "username":
					if(i == param.size()-1)
						System.out.print("\'"+x.getUsername()+"\'");
					else 
						System.out.print("\'"+x.getUsername()+"\', ");
					break;
				case "password":
					if(i == param.size()-1)
						System.out.print("\'"+x.getPassword()+"\'");
					else 
						System.out.print("\'"+x.getPassword()+"\', ");
					break;
				}
			}
			System.out.println(");");
		}
	}
	
	public static void printSortedPeople(){
		Collections.sort(people, new OsebaComparator());
		for(Oseba x : people){
			x.getOseba();
		}
	}
}
