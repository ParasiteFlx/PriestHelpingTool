import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.io.IOException;
import java.util.Objects;

public class MainFrame extends JFrame implements ActionListener {

    Console console;

    FileRepo fileRepo;

    private JFrame mainFrame;
    private Container container;
    private CardLayout cardLayout;
    private JButton startButton, backButton;

    MainFrame(Console console, FileRepo fileRepo) throws FileNotFoundException {

            this.console = console;
            this.fileRepo = fileRepo;
           // Path path = Paths.get("anuntRepo.txt");
            fileRepo.readAnunt("./anuntRepo.txt");
            fileRepo.readSlujba("./slujbaRepo.txt");
            fileRepo.readEnorias("./enoriasRepo.txt");
            fileRepo.readServiciu("./serviciuRepo.txt");
            cardLayout = new CardLayout();
            mainFrameInitialisation();
            content();

    }

    MainFrame(){

    }

    public void errorBox(String error, String succes)
    {
        JDialog eroare = new JDialog();
        JPanel eroareArea = new JPanel();

        eroareArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        eroare.setLayout(new GridBagLayout());
        eroareArea.setLayout(new BorderLayout());
        eroare.setSize(300,100);
        eroare.setResizable(false);
        if(error.equals("valid!"))
            eroare.setTitle(succes);
        else
            eroare.setTitle("Eroare!");
        eroare.setLocationRelativeTo(null);
        eroare.setVisible(true);

        JTextArea eroareText = new JTextArea();
        eroareText.setText(error);
        eroareText.setEditable(false);
        eroareText.setOpaque(false);
        //eroareText.setSize(300,100);
        eroareArea.add(eroareText,BorderLayout.CENTER);
        eroare.add(eroareArea);
        eroare.pack();

    }

    public void errorBoxGUI()
    {
        JDialog eroare = new JDialog();
        JPanel eroareArea = new JPanel();

        eroareArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        eroare.setLayout(new GridBagLayout());
        eroareArea.setLayout(new BorderLayout());
        eroare.setSize(300,100);
        eroare.setResizable(false);
        eroare.setTitle("Eroare!");
        eroare.setLocationRelativeTo(null);
        eroare.setVisible(true);

        JTextArea eroareText = new JTextArea();
        eroareText.setText("Lista este goală!");
        eroareText.setEditable(false);
        eroareText.setOpaque(false);
        //eroareText.setSize(300,100);
        eroareArea.add(eroareText,BorderLayout.CENTER);
        eroare.add(eroareArea);
        eroare.pack();


    }

    public void infoBox(String titlu, String continut, int latime, int inaltime)
    {
        JDialog ajutorText = new JDialog();
        ajutorText.setLayout(new BorderLayout());
        JTextArea text = new JTextArea(continut);
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setEditable(false);
        text.setBorder(BorderFactory.createLineBorder(Color.black));
        text.setSize(latime,inaltime);

        ajutorText.setTitle(titlu);
        ajutorText.setSize(latime,inaltime);

        ajutorText.setLocationRelativeTo(null);
        ajutorText.setResizable(false);
        ajutorText.setVisible(true);

        ajutorText.add(text, BorderLayout.CENTER);
        ajutorText.pack();

    }


    private void mainFrameInitialisation()
    {
        mainFrame = new JFrame("E-Church Agenda");
        mainFrame.setSize(800,600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    fileRepo.writeAnunt("./anuntRepo.txt");
                    fileRepo.writeSlujba("./slujbaRepo.txt");
                    fileRepo.writeEnorias("./enoriasRepo.txt");
                    fileRepo.writeServiciu("./serviciuRepo.txt");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    private void content()
    {
        container = mainFrame.getContentPane();
        container.setLayout(cardLayout);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(new Insets(25,200,200,200)));

        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("./testCross.png"))));

        startButton = new JButton("Start");
        startButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.add(startButton);
        //buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        mainPanel.add(image);
        mainPanel.add(Box.createRigidArea(new Dimension(0,50)));
        mainPanel.add(buttonPanel);
        //mainPanel.add(Box.createRigidArea(new Dimension(0,60)));

        MainMenu menu = new MainMenu(this.console);
        JPanel mainMenu = new JPanel();
        JPanel menuButtonPanel = menu.getButtonPanel();
        mainMenu.setLayout(new GridBagLayout());
        mainMenu.setBorder(BorderFactory.createEmptyBorder(150,150,150,150));

        backButton = new JButton("Înapoi");
        backButton.addActionListener(this);
        menuButtonPanel.add(backButton);
        mainMenu.add(menuButtonPanel);

        container.add(mainPanel);
        container.add(mainMenu);


    }

    @Override
    public void actionPerformed(ActionEvent e)
    {   if(e.getSource()==startButton)
        {
            cardLayout.next(container);
        } else if (e.getSource()==backButton)
        {
            cardLayout.previous(container);
        }
    }


}

