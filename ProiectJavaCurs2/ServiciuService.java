import java.time.LocalDate;
import java.util.ArrayList;


public class ServiciuService extends Service {

    public ServiciuService(Repository repo) {
        super(repo);
    }

    public void setAvailableId(Serviciu object) {
        ArrayList<Serviciu> serviciuList = super.getList();
        int maxId = 0;
        for (int i=0;i<serviciuList.size();i++) {
            maxId = Math.max(maxId, serviciuList.get(i).getId());
        }
        int[] existingIds = new int[maxId + 1];
        for (int i=0;i<serviciuList.size();i++) {
            existingIds[serviciuList.get(i).getId() - 1] = 1;
        }
        for (int i = 0; i <= maxId; i++) {
            if (existingIds[i] == 0) {
                object.setId(i + 1);
                break;
            }
        }
    }
    public String addServiciu(String nume,Enorias enorias, String an, String luna, String zi) {

        if(enorias==null) return "id of enorias does not exist";
        else
        {
            String error = super.checkNumar(an, 5000);
            if(error!="valid!") return error;
            error = super.checkNumar(luna, 13);
            if(error!="valid!") return error;
            error = super.checkNumar(zi, 32);
            if(error!="valid!") return error;
            error = super.checkData(an, luna, zi);
            if(error!="valid!") return error;
            Serviciu newServiciu = new Serviciu(nume,enorias,Integer.parseInt(an), Integer.parseInt(luna), Integer.parseInt(zi));
            super.add(newServiciu);
            setAvailableId(newServiciu);
            return "valid!";
        }

    }

    public String removeServiciu(String id) {
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

    public String updateServiciu(String id, Enorias newEnorias , String newNume, String newAn,String newLuna,String newZi){
        String checkError = super.checkNumar(id,5000);
        if(checkError!="valid!") return checkError;
        if(checkError=="valid!")
        {
            int pos = this.existId(Integer.parseInt(id));
            if(pos!=-1)
            {
                checkError = this.checkUpdate(id, newEnorias, newNume, newAn,newLuna,newZi,pos);
                return checkError;
            }
            return "Id of serviciu does not exist";
        }
        return checkError;
    }

    public String checkUpdate(String id, Enorias newEnorias, String newNume, String newAn, String newLuna, String newZii , int pos)
    {
        String checkError = super.checkNumar(id,5000);
        if (checkError!="valid!") return checkError;
        checkError = super.checkNume(newNume,25);
        if (checkError!="valid!") return checkError;
        checkError = super.checkNumar(newAn, 5000);
        if (checkError!="valid!") return checkError;
        checkError = super.checkNumar(newLuna, 13);
        if (checkError!="valid!") return checkError;
        checkError = super.checkNumar(newZii, 32);
        if (checkError!="valid!") return checkError;

        return executeUpdate(id, newEnorias, newNume, newAn,newLuna,newZii,pos) ;
    }
    public String executeUpdate(String id, Enorias newEnorias, String newNume, String newAn, String newLuna, String newZii , int pos)
    {
        Serviciu newServiciu = new Serviciu(newNume,newEnorias,Integer.parseInt(newAn),Integer.parseInt(newLuna),Integer.parseInt(newZii));
        newServiciu.setId(Integer.parseInt(id));
        super.update(newServiciu, pos);
        return "valid!";
    }
    public int existId(int id) {
        try {

            ArrayList<Serviciu> lista = this.repo.getList();
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
        ArrayList<Serviciu> listaPrint = super.getList();
        ArrayList<String[]> result = new ArrayList<String[]>();
        for(int i=0;i<listaPrint.size();i++)
        {
            String[] printObject  = new String[6];
            printObject[0] =  String.valueOf(listaPrint.get(i).getId());
            printObject[1] = listaPrint.get(i).getNume();
            printObject[2] =String.valueOf(listaPrint.get(i).getDataOra().getYear()) ;
            printObject[3] =String.valueOf(listaPrint.get(i).getDataOra().getMonthValue()) ;
            printObject[4] =String.valueOf(listaPrint.get(i).getDataOra().getDayOfMonth()) ;
            printObject[5] =listaPrint.get(i).getEnorias().getNume();
            result.add(printObject);
        }
        return result;
    }

    public String getServiciiByMonth(int yearValue, int monthValue, int dayOfMonth) {
        String result = new String();
        ArrayList<Serviciu> lista = super.getList();
        boolean flag = false;
        for(int i=0;i<lista.size();i++)
        {
            if(lista.get(i).getDataOra().getYear()==yearValue && lista.get(i).getDataOra().getMonthValue()==monthValue && lista.get(i).getDataOra().getDayOfMonth()==dayOfMonth)
            {
                result= result+" "+ lista.get(i).getNume() + " - " + lista.get(i).getEnorias().getNume() + " | ";
                flag = true;
            }
        }
        if(flag) return result;
        else return "invalid";
    }
}