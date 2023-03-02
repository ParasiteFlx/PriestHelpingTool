import java.time.LocalDate;

public class Anunt {
	private int id;
	private String titlu;
	private String continut;

	private boolean esteAnunt;


	public Anunt(String titlu, String continut, boolean esteAnunt)
	{
		this.titlu = titlu;
		this.continut = continut;
		this.esteAnunt = esteAnunt;
		this.id=1;

	}
	public void setTitlu(String newTitlu)
	{
		this.titlu = newTitlu;
	}
	public void setContinut(String newContinut)
	{
		this.continut = newContinut;
	}
	public void setId(int newId)
	{
		this.id = newId;
	}

	public void setEsteAnunt(boolean newEsteAnunt)
	{
		this.esteAnunt = newEsteAnunt;
	}

	public int getId()
	{
		return id;
	}
	public String getTitlu()
	{
		return titlu;
	}
	public String getContinut()
	{
		return continut;
	}
	public boolean getEsteAnunt()
	{
		return esteAnunt;
	}
}
