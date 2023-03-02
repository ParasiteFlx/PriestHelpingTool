import java.util.ArrayList;
import java.time.LocalDate;
public abstract class Service<T> {
	// Elementul principal din layer 3 , primeste la fiecare intializare un Repository generic
	// cu obiectul necesar, si contine functii care se folosesc de un obiect generic precum si
	// functii de verificare al input ului care sunt folosite de toate Service urile specifice ce
	// extind aceasta clasa
		protected Repository<T> repo;
		protected String error;
		public Service(Repository<T> repo)
		{
			this.repo = repo;
		}
		public  void add(T objectName) {
			repo.add(objectName);
		}
		public ArrayList<T> getList()
		{
			return this.repo.getList();
		}
		public String checkNume(String titlu, int limit) {
			// Cheama toate functiile necesare verificarii unui String ce trebuie sa
			// inceapa cu litera mare , sa nu fie gol , iar primul caracter sa fie o litera
			// Returneaza un string util pentru GUI
		    try {
		        if (titlu.isEmpty()) {
		            throw new IllegalArgumentException("Input must not be empty");
		        }
		        if (Character.isDigit(titlu.charAt(0))) {
		            throw new IllegalArgumentException("First character must be a letter");
		        }
		        if (!Character.isUpperCase(titlu.charAt(0))) {
		            throw new IllegalArgumentException("First letter must be uppercase");
		        }
		        if (titlu.length() > limit) {
		            throw new IllegalArgumentException("Input must be less than " + (limit + 1) + " characters");
		        }
		        return "valid!";
		    } catch (IllegalArgumentException ex) {
		        return ex.getMessage();
		    }
		}

		private String isStringEmptyError(String titlu) {
			// Verifica daca input ul este un string gol ("")
		    try {
		        if (titlu.isEmpty()) {
		            throw new IllegalArgumentException("Input must not be empty");
		        }
		        return "valid!";
		    } catch (IllegalArgumentException ex) {
		        return ex.getMessage();
		    }
		}
		public String checkNumarBoolean(String isAnunt) {
			// Aceasta functie verifica daca input ul este 1 sau 2 , o conventie formata in consola in care 1 reprezinta false
			// iar 2 reprezinta true , functia a fost utila la implementarea aplicatiei fara GUI , in baza unei console
		    try {
		        if (!isAnunt.equals("1") && !isAnunt.equals("2")) {
		            throw new IllegalArgumentException("Value should be 1 or 2");
		        }
		        return "valid!";
		    } catch (IllegalArgumentException ex) {
		        return ex.getMessage();
		    }
		}

		public String checkNumar(String id,int limit) {
			// Functia primeste input ul specific de la consola ca si String si limita numarului (necesara pentru zii luna an)
			String error = new String();
			error= this.isStringEmptyError(id);
			if(error!="valid!") return error;
			error = this.isStringNumber(id);
			if(error!="valid!") return error;
			return error;
		}

		public  String isStringNumber(String id) {
			//Verifica daca input ul de la GUI este un numar si returneaza String uri utile
		    try {
		        Integer.parseInt(id);
		        return "valid!";
		    } catch (NumberFormatException e) {
		        return "Input must be integer";
		    }
		}

     	public  String checkNumarTelefon(String numarTelefon) {
			// Folosita doar in cazul enoriasului , dar este creata in Service ul generic pentru
			// a putea fii apelata de toate celelalte service uri
			// Un numar de telefon trebuie sa inceapa cu 07 si sa aiba 10 caractere
		try {
			if (numarTelefon.length() == 10 && numarTelefon.startsWith("07")) {
				for (int i = 0; i < 10; i++) {
					if (!Character.isDigit(numarTelefon.charAt(i))) {
						return "invalid";
					}
				}
				return "valid!";
			} else {
				return "invalid";
			}
		} catch (Exception e) {
			return "invalid";
		}
     	}

// Funtia de remove apeleaza repo ul generic si se foloseste de functiile integrate in ArrayList
	public void remove(int index)
		{
			repo.remove(index);
		}

	// Funtia de update apeleaza repo ul generic si se foloseste de functiile integrate in ArrayList
		public void update(T ojbectName , int index)
		{
			repo.update(ojbectName, index);
		}
		public String checkData(String anSlujba, String lunaSlujba, String ziSlujba) {
			//Verifica daca input ul de la GUI este o data valabila calendaristic
			//returneaza erori utile
			try
			{
				LocalDate data = LocalDate.of(Integer.parseInt(anSlujba),Integer.parseInt(lunaSlujba),Integer.parseInt(ziSlujba));
				return "valid!";
			}

			catch (Exception e) {
		        return "Date does not exist";
		    }
		}

}
