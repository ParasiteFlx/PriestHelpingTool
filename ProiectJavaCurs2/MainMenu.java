import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends MainFrame implements ActionListener {


    Console console;
    JPanel buttonPanel = new JPanel();
    JButton serviciiButton,prediciAnunturiButton,sfintiiButton,ajutorButton,slujbaButton,enoriasButton, imprimaButton;

    MainMenu(Console console){

        this.console = console;
        mainMenu();
    }

    public JPanel getButtonPanel(){
        return  buttonPanel;
    }
    private void mainMenu()
    {
        buttonPanel.setLayout(new GridLayout(8,1,10,30));
        buttonPanel.setSize(new Dimension(100,65));

        serviciiButton = new JButton("Servicii Religioase");
        prediciAnunturiButton = new JButton("Predici/Anunțuri");
        slujbaButton = new JButton("Slujbe");
        sfintiiButton = new JButton("Sfinții pomeniți azi și/sau sărbătorile!");
        ajutorButton = new JButton("Ajutor");
        enoriasButton = new JButton("Enoriasi");
        imprimaButton = new JButton("Imprimă servicii/slujbe din ziua curentă");
        serviciiButton.addActionListener(this);
        prediciAnunturiButton.addActionListener(this);
        slujbaButton.addActionListener(this);
        sfintiiButton.addActionListener(this);
        ajutorButton.addActionListener(this);
        enoriasButton.addActionListener(this);
        imprimaButton.addActionListener(this);

        buttonPanel.add(enoriasButton);
        buttonPanel.add(serviciiButton);
        buttonPanel.add(slujbaButton);
        buttonPanel.add(imprimaButton);
        buttonPanel.add(prediciAnunturiButton);
        buttonPanel.add(sfintiiButton);
        buttonPanel.add(ajutorButton);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == serviciiButton)
        {
            ServiciiDialogBox sdb = new ServiciiDialogBox(this.console);

        } else if (e.getSource() == prediciAnunturiButton)
        {
            PrediciAnunturiDBox padb = new PrediciAnunturiDBox(this.console);

        } else if (e.getSource() == slujbaButton)
        {
            SlujbeDialogBox sljdb = new SlujbeDialogBox(this.console);

        } else if (e.getSource() == enoriasButton)
        {
            EnoriasiDialogBox edb = new EnoriasiDialogBox(this.console);
        }
        else if(e.getSource() == imprimaButton)
        {
            infoBox("Status printare",console.printSlujbeServicii(),150,150);
        }
        else if(e.getSource() == sfintiiButton)
        {
            infoBox("Sfinții și/sau sărbătorile de azi!",console.getSfintiZiuaCurenta(),350,350);
        }
        else if(e.getSource() == ajutorButton)
        {
            String continutAjutor;

            continutAjutor = "În centrul ecranului se află meniul principal.\n" +
                    "Butonul „Enoriași” deschide „Manager enoriași”.\n" +
                    "Butonul „Servicii Religioase” deschide „Manager Servicii Religioase”.\n" +
                    "Butonul „Slujbe” deschide „Manager slujbe”.\n"+
                    "Butonul „Imprimă servicii/slujbe din ziua curentă” face ce sugerează numele.\n"+
                    "Butonul „Predici/Anunțuri” deschide „Manager predici/anunțuri”.\n" +
                    "Butonul „Sfinții pomeniți azi și/sau sărbătorile!” face ce sugerează numele.\n" +
                    "Butonul „Ajutor” afișează acest mesaj.\n" +
                    "Butonul „Înapoi” întoarce utilizatorul la pagina inițială.\n" +
                    "Butonul „Start” din pagina inițială face trecerea la acest meniu.";

            infoBox("Ajutor!",continutAjutor,350,255);
        }



    }
}
