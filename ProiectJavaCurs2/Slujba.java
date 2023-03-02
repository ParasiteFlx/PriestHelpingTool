import java.time.LocalDate;

public class Slujba {
	private int id;
	private String nume;
	private  LocalDate data_ora;

	public Slujba(String nume, int an, int luna, int zi)
	{
		this.nume=nume;
		this.data_ora=LocalDate.of(an, luna, zi);
		this.id=1;
	}
	public void setId(int newId)
	{
		this.id= newId;
	}
	public void setNume(String newNume)
	{
		this.nume=newNume;
	}
	public void setData_Ora(int newAn,int newLuna,int newZi)
	{
		this.data_ora=LocalDate.of(newAn, newLuna, newZi);
	}
	public int getId()
	{
		return id;
	}
	public String getNume()
	{
		return nume;
	}
	public LocalDate getData_Ora()
	{
		return data_ora;
	}
}
