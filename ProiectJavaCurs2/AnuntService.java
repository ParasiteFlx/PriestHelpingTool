import java.util.ArrayList;

public class AnuntService extends Service<Anunt> {
	// Contine functiile specifice manipularii Anunturilor
	// Reprezinta legatura dintre consola si Service generic
	// Genereaza un id valabil pentru obiectul adaugat
		
		public AnuntService(Repository<Anunt> repo) 
		{
			super(repo);
		}
	public void setAvailableId(Anunt object) {
			// Se foloseste de un array de flag uri (0 , 1)
		ArrayList<Anunt> anuntList = super.getList();
		int maxId = 0;
		for (int i=0;i<anuntList.size();i++) {
			maxId = Math.max(maxId, anuntList.get(i).getId());
		}
		int[] existingIds = new int[maxId + 1];
		for (int i=0;i<anuntList.size();i++) {
			existingIds[anuntList.get(i).getId() - 1] = 1;
		}

		for (int i = 0; i <= maxId; i++) {
			if (existingIds[i] == 0) {
				object.setId(i + 1);
				break;
			}
		}
	}

		@Override
		public void add(Anunt newAnunt) {
			super.repo.add(newAnunt);
		}
		public String addAnunt(String titlu , String continut , String isAnunt)
		{
			String checkError = super.checkNumar(isAnunt,3);
			if (checkError!="valid!") return checkError;
			checkError = super.checkNumarBoolean(isAnunt);
			if (checkError!="valid!") return checkError;
			checkError = super.checkNume(titlu,25);
			if (checkError!="valid!") return checkError;
			checkError = super.checkNume(continut, 100);
			if (checkError!="valid!") return checkError;
			Anunt newAnunt = new Anunt(titlu,continut,isAnuntToBool(isAnunt));
			this.add(newAnunt);
			setAvailableId(newAnunt);
			return "valid!";
		}

		public boolean isAnuntToBool(String isAnunt)
		{
			if(isAnunt.equals("1"))
				return false;
			else
				return true;
		}
	public String updateAnunt(String id, String newTitle, String newContinut, String isAnunt){

		String checkError = super.checkNumar(id,5000);
		if(checkError!="valid!") return checkError;
		if(checkError=="valid!")
		{
			int pos = this.existId(Integer.parseInt(id));
			if(pos!=-1)
			{
				checkError = this.checkUpdate(id, newTitle, newContinut, isAnunt,pos);
				return checkError;
			}
			return checkError;
		}
		return checkError;
	}

	public String checkUpdate(String id, String newTitle, String newContinut, String isAnunt,int pos)
	{
		String checkError = super.checkNume(newTitle,25);
		if (checkError!="valid!") return checkError;
		checkError = super.checkNume(newContinut,50);
		if (checkError!="valid!") return checkError;
		return executeUpdate(Integer.parseInt(id), newTitle, newContinut,isAnunt,pos) ;
	}
	public String executeUpdate(int id , String newTitle, String newContinut, String isAnunt,int pos)
	{
		ArrayList<Anunt> listaPrint = super.getList();
		Anunt a = new Anunt(newTitle,newContinut,false);
		if(isAnunt.equals("true"))
		{
			a.setEsteAnunt(true);
		}
		a.setId(id);
		
		super.update(a, pos);
		return "valid!";
	}
		public ArrayList<String[]> getAllForPrint() {
		ArrayList<Anunt> listaPrint = super.getList();
		ArrayList<String[]> result = new ArrayList<String[]>();
		for(int i=0;i<listaPrint.size();i++)
		{
			String[] printObject  = new String[4];
			printObject[0] =  String.valueOf(listaPrint.get(i).getId());
			printObject[1] = listaPrint.get(i).getTitlu();
			printObject[2] = listaPrint.get(i).getContinut();
			printObject[3] = String.valueOf(listaPrint.get(i).getEsteAnunt());
			result.add(printObject);
		}
		return result;
	}

	public String removeAnunt(String id) {
		String checkError = super.checkNumar(id,5000);
		if(checkError=="valid!")
		{
			int pos = this.existId(Integer.parseInt(id));
			if(pos!=-1)
			{
				super.remove(pos);
				return "valid!";
			}
			return "id does not exist";
		}
		return checkError;

	}
	public int existId(int id) {

			ArrayList<Anunt> lista = this.repo.getList();
			for (int i = 0; i < this.repo.size(); i++) {
				if (id == repo.get(i).getId()) {
					return i;
				}
			}
			return -1;

	}
	}
