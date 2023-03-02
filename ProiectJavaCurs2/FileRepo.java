
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class FileRepo {
    private AnuntService anuntService;
    private SlujbaService slujbaService;
    private EnoriasService enoriasService;
    private ServiciuService serviciuService;

    public FileRepo(AnuntService anuntService, SlujbaService slujbaService, EnoriasService enoriasService, ServiciuService serviciuService) {
        this.anuntService = anuntService;
        this.enoriasService = enoriasService;
        this.serviciuService = serviciuService;
        this.slujbaService = slujbaService;
    }

    public void readAnunt(String fileName) throws FileNotFoundException {

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {

                int id = Integer.parseInt(scanner.nextLine());
                String titlu = scanner.nextLine();
                String continut = scanner.nextLine();
                boolean isAnunt = Boolean.parseBoolean(scanner.nextLine());
                Anunt newAnunt = new Anunt(titlu, continut, isAnunt);
                newAnunt.setId(id);
                this.anuntService.add(newAnunt);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }


    }

    public void readSlujba(String fileName) throws FileNotFoundException {

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {

                int id = Integer.parseInt(scanner.nextLine());
                String nume = scanner.nextLine();
                int an = Integer.parseInt(scanner.nextLine());
                int luna = Integer.parseInt(scanner.nextLine());
                int zi = Integer.parseInt(scanner.nextLine());
                Slujba newSlujba = new Slujba(nume, an, luna, zi);
                newSlujba.setId(id);
                this.slujbaService.add(newSlujba);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }


    }

    public void readEnorias(String fileName) throws FileNotFoundException {

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {

                int id = Integer.parseInt(scanner.nextLine());
                String nume = scanner.nextLine();
                String telefon = scanner.nextLine();
                String adresa = scanner.nextLine();
                Enorias newEnorias = new Enorias(nume, telefon, adresa);
                newEnorias.setId(id);
                this.enoriasService.add(newEnorias);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }


    }

    public void readServiciu(String fileName) throws FileNotFoundException {

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {

                int id = Integer.parseInt(scanner.nextLine());
                String nume = scanner.nextLine();
                int idEnorias = Integer.parseInt(scanner.nextLine());
                int an = Integer.parseInt(scanner.nextLine());
                int luna = Integer.parseInt(scanner.nextLine());
                int zi = Integer.parseInt(scanner.nextLine());
                Enorias newEnorias = this.enoriasService.getEnoriasById(String.valueOf(idEnorias));
                Serviciu newServiciu = new Serviciu(nume, newEnorias, an, luna, zi);
                newServiciu.setId(id);
                this.serviciuService.add(newServiciu);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }


    }

    public void writeAnunt(String fileName) throws IOException {

        try {
            new PrintWriter(fileName).close();
            File file = new File(fileName);
            FileOutputStream stream = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
            ArrayList<Anunt> list = this.anuntService.getList();
            for (int i = 0; i < list.size(); i++) {
                Anunt newAnunt = list.get(i);
                writer.write(String.valueOf(newAnunt.getId()));
                writer.newLine();
                writer.write(newAnunt.getTitlu());
                writer.newLine();
                writer.write(newAnunt.getContinut());
                writer.newLine();
                writer.write(String.valueOf(newAnunt.getEsteAnunt()));
                writer.newLine();
            }
            writer.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }

    }

    public void writeSlujba(String fileName) throws IOException {

        try {
            new PrintWriter(fileName).close();
            File file = new File(fileName);
            FileOutputStream stream = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
            ArrayList<Slujba> list = this.slujbaService.getList();
            for (int i = 0; i < list.size(); i++) {
                Slujba newSlujba = list.get(i);
                writer.write(String.valueOf(newSlujba.getId()));
                writer.newLine();
                writer.write(newSlujba.getNume());
                writer.newLine();
                writer.write(String.valueOf(newSlujba.getData_Ora().getYear()));
                writer.newLine();
                writer.write(String.valueOf(newSlujba.getData_Ora().getMonthValue()));
                writer.newLine();
                writer.write(String.valueOf(newSlujba.getData_Ora().getDayOfMonth()));
                writer.newLine();
            }
            writer.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }

    }


    public void writeEnorias(String fileName) throws IOException {

        try {
            new PrintWriter(fileName).close();
            File file = new File(fileName);
            FileOutputStream stream = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
            ArrayList<Enorias> list = this.enoriasService.getList();
            for (int i = 0; i < list.size(); i++) {
                Enorias newEnorias = list.get(i);
                writer.write(String.valueOf(newEnorias.getId()));
                writer.newLine();
                writer.write(newEnorias.getNume());
                writer.newLine();
                writer.write(newEnorias.getTelefon());
                writer.newLine();
                writer.write(newEnorias.getAdresa());
                writer.newLine();
            }
            writer.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }

    }

    public void writeServiciu(String fileName) throws IOException {

        try {
            new PrintWriter(fileName).close();
            File file = new File(fileName);
            FileOutputStream stream = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
            ArrayList<Serviciu> list = this.serviciuService.getList();
            for (int i = 0; i < list.size(); i++) {
                Serviciu newServiciu = list.get(i);
                writer.write(String.valueOf(newServiciu.getId()));
                writer.newLine();
                writer.write(newServiciu.getNume());
                writer.newLine();
                writer.write(String.valueOf(newServiciu.getEnorias().getId()));
                writer.newLine();
                writer.write(String.valueOf(newServiciu.getDataOra().getYear()));
                writer.newLine();
                writer.write(String.valueOf(newServiciu.getDataOra().getMonthValue()));
                writer.newLine();
                writer.write(String.valueOf(newServiciu.getDataOra().getDayOfMonth()));
                writer.newLine();
            }
            writer.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }

    }
}

