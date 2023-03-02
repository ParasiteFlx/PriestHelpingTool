public class Enorias {

    private int id;
    private String nume,telefon,adresa;

    public Enorias(String nume, String telefon, String adresa)
    {
        id = 1;
        this.nume = nume;
        this.telefon = telefon;
        this.adresa = adresa;
    }

    public int getId()
    {
        return id;
    }

    public String getNume()
    {
        return nume;
    }

    public String getTelefon()
    {
        return telefon;
    }

    public String getAdresa()
    {
        return adresa;
    }

    public void setNume( String newNume)
    {
        this.nume = newNume;
    }

    public void setTelefon(String newTelefon)
    {
        this.telefon = newTelefon;
    }

    public void setAdresa(String newAdresa)
    {
        this.adresa = newAdresa;
    }

    public void setId(int newId) {
        this.id=newId;

    }
}