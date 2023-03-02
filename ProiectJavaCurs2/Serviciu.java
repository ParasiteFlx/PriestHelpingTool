import java.time.LocalDate;


public class Serviciu {

    private int id;
    private String nume;
    private LocalDate dataOra;
    private Enorias enorias;


    public Serviciu(String nume,Enorias enorias,int an, int luna, int zi)
    {
        id =1;
        this.nume = nume;
        this.dataOra=LocalDate.of(an, luna, zi);
        this.enorias = enorias;
    }

    public int getId()
    {
        return id;
    }
    public LocalDate getDataOra()
    {
        return dataOra;
    }

    public Enorias getEnorias()
    {
        return enorias;
    }
    public String getNume()
    {
        return nume;
    }
    public void setNume(String newNume)
    {
        this.nume=newNume;
    }
    public void setDataOra(LocalDate newDataOra)
    {
        this.dataOra = newDataOra;
    }
    public void setId(int newId)
    {
        this.id = newId;
    }

}