package test;

public class Oseba {
	private String name;
	private String surname;
	private String emso;
	private String davcna;
	private String datrojstva;
	private String organization;
	private String username;
	private String password;
	
	public Oseba(String name, String surname, String emso, String davcna, String datrojstva, String organization, String username, String password){
		this.name = name;
		this.surname = surname;
		this.emso = emso;
		this.davcna = davcna;
		this.datrojstva = datrojstva;
		this.organization = organization;
		this.username = username;
		this.password = password;
	}
	
	public void getOseba(){
		System.out.printf("%12s", name);
		System.out.printf("%13s", surname);
		System.out.printf("%15s", emso);
		System.out.printf("%10s", davcna);
		System.out.printf("%12s", datrojstva);
		System.out.printf("%22s", organization);
		System.out.printf("%23s", username);
		System.out.printf("%18s", password);
		System.out.println();
	}
	
	public String getName(){
		return name;
	}
	public String getSurname(){
		return surname;
	}
	public String getEmso(){
		return emso;
	}
	public String getDavcna(){
		return davcna;
	}
	public String getDOB(){
		return datrojstva;
	}
	public String getOrganizacija(){
		return organization;
	}
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
}
