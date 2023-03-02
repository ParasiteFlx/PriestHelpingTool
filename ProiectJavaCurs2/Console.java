import java.time.LocalDate;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;


public class Console {
	private AnuntService anuntService;
	private SlujbaService slujbaService;
	private EnoriasService enoriasService;
	private ServiciuService serviciuService;
//
	// Programul este alcatuit din 4 layer uri distincte :
	// 1: GUI ul , primeste input de la utilizator , il transmite consolei , si primeste raspunsuri utile ( string uri) , si valori
	// printarii
	// 2: Console + TextPrintable , prima are ca functie procesarea raspunului de la GUI si apelarea functiilor necesare din service
	// precum si convertirea unor input uri si outpuri si formatarea lor, iar TextPrintable organizeaza listarea la imprimanta a input-ului
	// 3: Service , primeste input uri specifice de la consola si le verifica , in cazul in care sunt eronate returneaza string uri cu erori
	// utile la consola care sunt apoi returnate la GUI . Iar in cazul in care input ul este corect, apeleaza functiile clasei generice Repository
	// 4: Repository , formata din clasa generica Repository ce contine functiile pentru CRUD si clasele de instantiere


	public Console(AnuntService anuntService, SlujbaService slujbaService, EnoriasService enoriasService,ServiciuService serviciuService) {
		// Connstructor consola , intocmeste layer-ul 2 impreuna cu clasa de Print de la structura programului , primeste ca si parametrii toate elementele din layer 3 (servicii)
		this.anuntService = anuntService;
		this.slujbaService = slujbaService;
		this.enoriasService = enoriasService;
	    this.serviciuService = serviciuService;
	}

	public String printSlujbeServicii() {
		// Functie care este chemata de catre GUI (layer 1), primeste ca si string de la service urile celor doua obiecte
		// verifica daca sunt goale ("") si le adauga in rezultat care este pasat spre functia de printare a layer-ului 2
		// returneaza string uri utile pentru GUI
		LocalDate currentDate = LocalDate.now();
		ArrayList<String> result = new ArrayList<String>();
		if(slujbaService.getStringSlujbeByMonth(currentDate.getYear(),currentDate.getMonthValue(),currentDate.getDayOfMonth())!="invalid")
		{
			result.add(slujbaService.getStringSlujbeByMonth(currentDate.getYear(),currentDate.getMonthValue(),currentDate.getDayOfMonth()));
		}
		if(serviciuService.getServiciiByMonth(currentDate.getYear(),currentDate.getMonthValue(),currentDate.getDayOfMonth())!="invalid")
		{
			result.add(serviciuService.getServiciiByMonth(currentDate.getYear(),currentDate.getMonthValue(),currentDate.getDayOfMonth()));
		}

		if(result.size()==0)
		{
			return "no such serviciu/sluja exist";
		}
		else {
			printStrings(result);
			return "serviciu/slujba are being printed";
		}

	}
	public String getSfintiZiuaCurenta() {
		// Obtine datele zilei in care se afla utilizatorul
		// Si bazat pe valoare lunii decide care dintre file uri o sa fie folosite
		// Apeleaza functia de citire in file , getSfintiFromFile cu parametrii numele file ul si ziua in care se afla utilizatorul
		LocalDate currentDate = LocalDate.now();
		int month = currentDate.getMonthValue();
		switch (month) {
			case 1:
				return getSfintiFromFile("Ianuarie.txt", currentDate.getDayOfMonth());
			case 2:
				return getSfintiFromFile("Februarie.txt", currentDate.getDayOfMonth());

			case 3:
				return getSfintiFromFile("Martie.txt", currentDate.getDayOfMonth());
			case 4:
				return getSfintiFromFile("Aprilie.txt", currentDate.getDayOfMonth());
			case 5:
				return getSfintiFromFile("Mai.txt", currentDate.getDayOfMonth());
			case 6:
				return getSfintiFromFile("Iunie.txt", currentDate.getDayOfMonth());
			case 7:
				return getSfintiFromFile("Iulie.txt", currentDate.getDayOfMonth());
			default:
				return "Luna indisponibila momentan";
		}
	}




	public String getSfintiFromFile(String fileName, int day)
	{
		// Parcurge file ul linie cu linie de day-1 ori ( adica pana ajunge la linia necesara print ului)
		// la linia respectiva o returneaza ca si String GUI ului
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			for (int i = 1; i < day; i++) {
				reader.readLine();
			}
			return reader.readLine();
		} catch (IOException e) {
			return null;
		}
	}
	public static void printStrings(ArrayList<String> lines) {
		// Pregateste input ul pentru clasa TextPrintable in baza listei de String uri ( obiectele care au fost gasite pe data curenta)
		// In cazul neasteptat in care utilizatorul nu are un default printer , atunci se va afisa eroarea specifica in consola cu Error printing:
				PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(new TextPrintable(lines));
		if (printJob.printDialog()) {
			try {
				printJob.print();
			} catch (PrinterException e) {
				System.out.println("Error printing: " + e.getMessage());
			}
		}
	}
	public String getDataNewAnunt(String titlu, String continut, String esteAnunt) {
		//Apeleaza functia speficica din service si returneaza eroarea obtinuta GUI ului ( care poate fi doar "valid!")
		String error = new String();
		error = this.anuntService.addAnunt(titlu, continut, esteAnunt);
		return error;
	}

	public String getDataNewSlujba(String titlu, String an, String luna, String zi) {
		//Apeleaza functia speficica din service si returneaza eroarea obtinuta GUI ului ( care poate fi doar "valid!")
		String error = new String();
		error = this.slujbaService.addSlujba(titlu,an,luna,zi);
		return error;
	}

	public String getDataNewEnorias(String nume, String telefon, String adresa) {
		//Apeleaza functia speficica din service si returneaza eroarea obtinuta GUI ului ( care poate fi doar "valid!")
		String error = new String();
		error = this.enoriasService.addEnorias(nume,telefon,adresa);
		return error;
	}

	public String getDataNewServiciu(String idEnorias, String nume, String an, String luna, String zi) {
		//Apeleaza functia speficica din service si returneaza eroarea obtinuta GUI ului ( care poate fi doar "valid!")
		String error = new String();
		error = this.serviciuService.addServiciu(nume,this.enoriasService.getEnoriasById(idEnorias) , an, luna, zi);
		return error;
	}

	public ArrayList<String[]> displayServicii() {
		// Obtine de la service un ArrayList<String[]> cu toate propietatiile obiectelor din Repository la momentul chemarii
		ArrayList<String[]> displayResult = new ArrayList<String[]>();
		displayResult = this.serviciuService.getAllForPrint();
		return displayResult;
	}





	public ArrayList<String[]> displayAnunturi() {
		// Obtine de la service un ArrayList<String[]> cu toate propietatiile obiectelor din Repository la momentul chemarii
		ArrayList<String[]> displayResult = new ArrayList<String[]>();
		displayResult = anuntService.getAllForPrint();
		return displayResult;
	}

	public ArrayList<String[]> displaySlujbe() {
		// Obtine de la service un ArrayList<String[]> cu toate propietatiile obiectelor din Repository la momentul chemarii
		ArrayList<String[]> displayResult = new ArrayList<String[]>();
		displayResult = slujbaService.getAllForPrint();
		return displayResult;
	}

	public ArrayList<String[]> displayEnoriasi() {
		// Obtine de la service un ArrayList<String[]> cu toate propietatiile obiectelor din Repository la momentul chemarii
		ArrayList<String[]> displayResult = new ArrayList<String[]>();
		displayResult = enoriasService.getAllForPrint();
		return displayResult;
	}






	public String callUpdateAnunt(String id, String titlu, String continut, String isAnunt) {
		// Obtine de la GUI input ul necesar in string uri si apeleaza functia respectiva din Service
		String error = new String();
		error = this.anuntService.updateAnunt(id, titlu, continut, isAnunt);
		return error;
	}

	public String callUpdateSlujba(String id, String titlu, String an, String luna,String zi) {
		// Obtine de la GUI input ul necesar in string uri si apeleaza functia respectiva din Service
		String error = new String();
		error = this.slujbaService.updateSlujba(id, titlu, an, luna, zi);
		return error;
	}

	public String callUpdateEnorias(String id, String nume, String telefon, String adresa) {
		// Obtine de la GUI input ul necesar in string uri si apeleaza functia respectiva din Service
		String error = new String();
		error = this.enoriasService.updateEnorias(id,nume,telefon,adresa);
		return error;
	}

	public String callUpdateServiciu(String id, String idEnorias, String nume, String an,String luna,String zi) {
		// Obtine de la GUI input ul necesar in string uri si apeleaza functia respectiva din Service
		String error = new String();
		if(this.enoriasService.getEnoriasById(idEnorias)!=null)
		{
			error = this.serviciuService.updateServiciu(id,this.enoriasService.getEnoriasById(idEnorias),nume,an,luna,zi);
		}
		else
		{
			return "id of Enorias does not exist";
		}
		return error;
	}
	public String removeAnunt(String id) {
		// Obtine de la GUI input ul necesar in string si apeleaza functia respectiva din Service
		String error = new String();
		error = this.anuntService.removeAnunt(id);
		return error;
	}

	public String removeSlujba(String id) {
		// Obtine de la GUI input ul necesar in string uri si apeleaza functia respectiva din Service
		String error = new String();
		error = this.slujbaService.removeSlujba(id);
		return error;
	}

	public String removeEnorias(String id) {
		// Obtine de la GUI input ul necesar in string uri si apeleaza functia respectiva din Service
		String error = new String();
		error = this.enoriasService.removeEnorias(id);
		return error;
	}

	public String removeServiciu(String id) {
		// Obtine de la GUI input ul necesar in string uri si apeleaza functia respectiva din Service
		String error = new String();
		error = this.serviciuService.removeServiciu(id);
		return error;
	}


}