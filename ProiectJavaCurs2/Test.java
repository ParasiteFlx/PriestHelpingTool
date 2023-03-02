import java.io.FileNotFoundException;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {

        Repository<Anunt> anuntRepository = new Repository<Anunt>();
        Repository<Slujba> slujbaRepository = new Repository<Slujba>();
        Repository<Enorias> enoriasRepository = new Repository<Enorias>();
        Repository<Serviciu> serviciuRepository = new Repository<Serviciu>();
        AnuntService anuntService = new AnuntService(anuntRepository);
        SlujbaService slujbaService = new SlujbaService(slujbaRepository);
        EnoriasService enoriasService = new EnoriasService(enoriasRepository);
        ServiciuService serviciuService = new ServiciuService(serviciuRepository);
        Console console = new Console(anuntService,slujbaService,enoriasService, serviciuService);
        FileRepo fileRepo = new FileRepo(anuntService,slujbaService,enoriasService,serviciuService);
        MainFrame app = new MainFrame(console,fileRepo);

    }

}