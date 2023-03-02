import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class SlujbaService extends Service {
	public SlujbaService(Repository<Slujba> repo)
	{
		super(repo);

	}
	public void setAvailableId(Slujba object) {
		ArrayList<Slujba> slujbaList = super.getList();
		int maxId = 0;
		for (int i=0;i<slujbaList.size();i++) {
			maxId = Math.max(maxId, slujbaList.get(i).getId());
		}
		int[] existingIds = new int[maxId + 1];
		for (int i=0;i<slujbaList.size();i++) {
			existingIds[slujbaList.get(i).getId() - 1] = 1;
		}
		for (int i = 0; i <= maxId; i++) {
			if (existingIds[i] == 0) {
				object.setId(i + 1);
				break;
			}
		}
	}
	public void add(Slujba newSlujba) {
		super.repo.add(newSlujba);
	}
	public String addSlujba(String numeSlujba, String anSlujba, String lunaSlujba , String ziSlujba)
	{
		String checkError = super.checkNumar(anSlujba,4000);
		if (checkError!="valid!") return checkError;
		checkError = super.checkNume(numeSlujba,25);
		if (checkError!="valid!") return checkError;
		checkError = super.checkNumar(lunaSlujba,13 );
		if (checkError!="valid!") return checkError;
		checkError = super.checkNumar(ziSlujba,32 );
		if (checkError!="valid!") return checkError;
		checkError = super.checkData(anSlujba, lunaSlujba, ziSlujba);
		if (checkError!="valid!") return checkError;
		Slujba newSlujba = new Slujba(numeSlujba,Integer.parseInt(anSlujba),Integer.parseInt(lunaSlujba),Integer.parseInt(ziSlujba));
		this.add(newSlujba);
		setAvailableId(newSlujba);
		return "valid!";

	}
	public ArrayList<String[]> getAllForPrint() {
		ArrayList<Slujba> listaPrint = super.getList();
		ArrayList<String[]> result = new ArrayList<String[]>();
		for(int i=0;i<listaPrint.size();i++)
		{
			String[] printObject  = new String[5];
			printObject[0] =  String.valueOf(listaPrint.get(i).getId());
			printObject[1] = listaPrint.get(i).getNume();
			LocalDate printedDate = listaPrint.get(i).getData_Ora();
			printObject[2] = String.valueOf(printedDate.getYear());
			printObject[3] = String.valueOf(printedDate.getMonthValue());
			printObject[4] = String.valueOf(printedDate.getDayOfMonth());
			result.add(printObject);
		}
		return result;
	}
	public String removeSlujba(String id) {
		String checkError = super.checkNumar(id,5000);

		if(checkError=="valid!")
		{
			int pos = this.existId(Integer.parseInt(id));
			if(pos!=-1)
			{
				super.remove(pos);
				return "valid!";
			}
			return checkError;
		}
		return checkError;

	}

	public int existId(int id) {
		try {

			ArrayList<Slujba> lista = this.repo.getList();
			for (int i = 0; i < this.repo.size(); i++) {
				if (id == lista.get(i).getId()) {
					return i;
				}
			}
			throw new IllegalArgumentException("Id does not exist");
		} catch (IllegalArgumentException ex) {
			return -1;
		}
	}

	public String updateSlujba(String id, String newTitle, String newAn, String newLuna,String newZi){

		String checkError = super.checkNumar(id,5000);
		if(checkError!="valid!") return checkError;
		if(checkError=="valid!")
		{
			int pos = this.existId(Integer.parseInt(id));
			if(pos!=-1)
			{
				checkError = this.checkUpdate(id, newTitle, newAn, newLuna,newZi,pos);
				return checkError;
			}
			return checkError;
		}
		return checkError;
	}

	public String checkUpdate(String id, String newTitle, String newYear , String newMonth, String newDay, int pos)
	{
		String checkError = super.checkNumar(id,5000);
		if (checkError!="valid!") return checkError;
		checkError = super.checkNume(newTitle,25);
		if (checkError!="valid!") return checkError;
		checkError = super.checkNumar(newYear, 4000);
		if (checkError!="valid!") return checkError;
		checkError = super.checkNumar(newMonth, 13);
		if (checkError!="valid!") return checkError;
		checkError = super.checkNumar(newDay, 32);
		if (checkError!="valid!") return checkError;

		return executeUpdate(id, newTitle, newYear, newMonth, newDay, pos) ;
	}
	public String executeUpdate(String id , String newTitle, String newYear, String newMonth, String newDay, int pos)
	{
		Slujba newSlujba = new Slujba(newTitle,Integer.parseInt(newYear),Integer.parseInt(newMonth),Integer.parseInt(newDay));
		newSlujba.setId(Integer.parseInt(id));
		super.update(newSlujba, pos);
		return "valid!";
	}

	public String getStringSlujbeByMonth(int yearValue, int monthValue, int dayValue) {
		ArrayList<Slujba> lista = super.getList();
		String result= new String();
		boolean flag=false;
		for(int i=0;i<lista.size();i++)
		{
			if(lista.get(i).getData_Ora().getYear()==yearValue && lista.get(i).getData_Ora().getMonthValue()==monthValue && lista.get(i).getData_Ora().getDayOfMonth()==dayValue)
			{
				result= result+" "+ lista.get(i).getNume() + "|";
				flag = true;
			}
		}
		System.out.println(result);
		if (flag) return result;
		else return "invalid";
	}
}