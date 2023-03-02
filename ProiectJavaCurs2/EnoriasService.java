import java.time.LocalDate;
import java.util.ArrayList;

public class EnoriasService extends Service {



    public void setAvailableId(Enorias object) {
        ArrayList<Enorias> enoriasList = super.getList();
        int maxId = 0;
        for (int i=0;i<enoriasList.size();i++) {
            maxId = Math.max(maxId, enoriasList.get(i).getId());
        }
        int[] existingIds = new int[maxId + 1];
        for (int i=0;i<enoriasList.size();i++) {
            existingIds[enoriasList.get(i).getId() - 1] = 1;
        }
        for (int i = 0; i <= maxId; i++) {
            if (existingIds[i] == 0) {
                object.setId(i + 1);
                break;
            }
        }
    }

    public EnoriasService(Repository<Enorias> repo)
    {
        super(repo);
    }
    public void add(Enorias newEnorias) {
        super.repo.add(newEnorias);

    }
    public String addEnorias(String numeEnorias,String telefonEnorias ,  String adresaEnorias)
    {
        String checkError = super.checkNume(numeEnorias, 25);
        if (checkError!="valid!") return checkError;
        checkError = super.checkNume(adresaEnorias, 50);
        if (checkError!="valid!") return checkError;
        checkError = super.checkNumarTelefon(telefonEnorias);
        if (checkError!="valid!") return checkError;
        Enorias newEnorias = new Enorias(numeEnorias,telefonEnorias,adresaEnorias);
        this.add(newEnorias);
        this.setAvailableId(newEnorias);
        return "valid!";
    }
    public int existId(int id) {
        try {

            ArrayList<Enorias> lista = this.repo.getList();
            for (int i = 0; i < lista.size(); i++) {
                if (id == lista.get(i).getId()) {
                    return i;
                }
            }
            throw new IllegalArgumentException("Id does not exist");
        } catch (IllegalArgumentException ex) {
            return -1;
        }
    }
    public ArrayList<String[]> getAllForPrint() {
        ArrayList<Enorias> listaPrint = super.getList();
        ArrayList<String[]> result = new ArrayList<String[]>();
        for(int i=0;i<listaPrint.size();i++)
        {
            String[] printObject  = new String[5];
            printObject[0] =  String.valueOf(listaPrint.get(i).getId());
            printObject[1] = listaPrint.get(i).getNume();
            printObject[2] = listaPrint.get(i).getTelefon();
            printObject[3] = listaPrint.get(i).getAdresa();
            result.add(printObject);
        }
        return result;
    }

    public Enorias getEnoriasById(String id)
    {
        String checkError = super.checkNumar(id, 5000);
        if(checkError=="valid!")
        {
            int pos = this.existId(Integer.parseInt(id));
            if(pos!=-1)
            {
                ArrayList<Enorias> lista = super.getList();
                return lista.get(pos);
            }
        }
        return null;

    }
    public String removeEnorias(String id) {
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
    public String updateEnorias(String id, String newNume, String newTelefon, String newAdresa) {

        String checkError = super.checkNumar(id,5000);
        int pos = this.existId(Integer.parseInt(id));
        if(checkError=="valid!")
        {
            if(pos!=-1)
            {
                checkError = this.checkupdate(id,newNume,newTelefon,newAdresa,pos);
                return checkError;
            }
            return "id does not exist";
        }
        return checkError;
    }
    private String checkupdate(String id, String newNume, String newTelefon, String newAdresa, int pos) {
        String checkError = super.checkNume(newNume,25);
        if (checkError!="valid!") return checkError;
        checkError = super.checkNumarTelefon(newTelefon);
        if (checkError!="valid!") return checkError;
        checkError = super.checkNume(newAdresa, 50);
        return executeUpdate(id,newNume,newTelefon,newAdresa,pos);
    }
    private String executeUpdate(String id, String newNume, String newTelefon, String newAdresa, int pos) {
        ArrayList<Enorias> lista = super.getList();
        Enorias newEnorias = new Enorias(newNume,newTelefon,newAdresa);
        newEnorias.setId(Integer.parseInt(id));
        //carbonara(a);
        super.update(newEnorias, pos);
        return "valid!";
    }
}