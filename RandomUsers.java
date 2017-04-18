package test;

import java.util.ArrayList;
import java.util.Scanner;

public class RandomUsers {
	public static String[] namesm = {"Marko","Andraž","Aleks","Mark","France","Jure","Esad","Niki","Janez","Lojze","Ivan","Beno","Radivoj","Miha","Oton","Rihard","Zdravko","Radovan","Lari","Luka","Jernej","Boris","Boštjan","Gregor","Rok","Anej","Aleksander","Klavdij","Uroš","Egon","Damjan","Bojan","David","Gorazd","Julijan","Aladin","Rajko","Simon","Rudolf","Andrej","Vinko","Janko","Mario","Slavko","Tine","Stanislav","Vladimir","Bogoljub","Ljubo","Slobodan","Erik","Filip"};
	public static String[] namesf = {"Vanja","Valentina","Valerija","Anka","Anika","Marija","Jožefa","Maša","Lara","Nina","Nuša","Mojca","Nika","Polona","Nataša","Beti","Veronika","Angelika","Patricija","Sara","Sofija","Alina","Manca","Ana","Brina","Miša","Aleksandra","Andreja","Martina","Gizela","Bojana","Janja","Tanja","Tjaša","Hedvika","Meta","Maja","Maruša","Manca","Bogomila","Anotinija","Cvetka","Dora","Alojzija","Lojzka","Jožica","Silvana","Silva","Tina","Loti","Hana","Neja"};
	public static String[] surnames = {"Novak","Kožuh","Valentinčič","Perinčič","Marinič","Ščurek","Belica","Demšar","Ambroželj","Filej","Plesničar","Žele","Ivančič","Bučar","Kovačič","Potočnik","Mlakar","Kos","Vidmar","Burgič","Sorčan","Arčon","Hozanović","Mugerli","Bizjak","Mislej","Kokošar","Boltar","Lazar","Laščak","Novšak","Velišček","Mohar","Rebula","Devetak","Korošec","Gabrijelčič","Makarovič","Mozetič","Komel","Škrlec","Munih","Šorli","Čuk","Čelik","Bratina","Modrijančič","Vouk","Kofol","Bremec"};
	public static String[] organizacije = {"ŠC Nova Gorica","HIT","Policija Tolmin","Rdeči križ Slovenije","Unicef","IGN","Unesco","who","Karitas","zzst","SŽ","Avrigo","Fia","ssz","zzzs","OŠ Podbrdo","Garfield","Dobro","Mercator","Spar","Lidl","Metalflex","Hidria","Prijon","Knjižnica Tolmin","Gostol","Avtobum","Tik Kobarid","Pekarna Hlebček","Tukaj","Salonit Anhovo","Rut d.o.o."};
	private static String datrojstva;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("please enter the ammount of users you want to generate: ");
		int ammount = sc.nextInt();
		sc.close();
		ArrayList<Oseba> people = new ArrayList<>();
		ArrayList<String> emsoti = new ArrayList<>();
		ArrayList<String> usernames = new ArrayList<>();
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
			String upime = (name.charAt(0)+surname).toLowerCase();
			int j = 1;
			while(usernames.contains(upime)){
				System.out.println(usernames.contains(upime));
				if(j > 1){
					upime = (name.charAt(0)+surname).toLowerCase()+j;
				}
				else
					upime += j;
				
			}
			String organizacija = organizacije[(int)(Math.random()*(organizacije.length))+0];
			String geslo = generatePassword();
			people.add(new Oseba(name, surname, emso, datrojstva, organizacija, upime, geslo));
		}
		System.out.printf("%12s", "name");
		System.out.printf("%13s", "surname");
		System.out.printf("%15s", "emso");
		System.out.printf("%12s", "DOB");
		System.out.printf("%22s", "organization");
		System.out.printf("%15s", "username");
		System.out.printf("%18s", "password");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		for(Oseba x : people){
			x.getOseba();
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------");
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
	
}
