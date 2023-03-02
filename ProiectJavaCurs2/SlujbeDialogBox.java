import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SlujbeDialogBox extends MainFrame implements ActionListener {

    JDialog slujbaDialogBox = new JDialog();
    Console console;
    ArrayList<String[]> continut;
    JButton adaugaButton, stergeButton, editButton, actualizeazaButton, ajutorButton, inchideButton, salveazaButton, reseteazaButton;
    JList listaSlujbe;
    JTextArea titleTA, ziTA, lunaTA, anTA;

    SlujbeDialogBox(Console console) {
        continut = console.displaySlujbe();
        this.console = console;
        slujbeBox();
    }

    private void slujbeBox()
    {
        slujbaDialogBox.setSize(750,500);
        slujbaDialogBox.setLocationRelativeTo(null);
        slujbaDialogBox.setResizable(false);
        slujbaDialogBox.setTitle("Manager slujbe");
        slujbaDialogBox.setVisible(true);

        JPanel slujbaBoxPanel = new JPanel();
        JPanel listArea = new JPanel();
        JPanel list = new JPanel();
        JPanel infoButtonsArea = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel gridBPTitle = new JPanel();
        JPanel gridBPZi = new JPanel();
        JPanel gridBPLuna = new JPanel();
        JPanel gridBPAn = new JPanel();
        JPanel gridBPTitleLabel = new JPanel();
        JPanel gridBPZiLabel = new JPanel();
        JPanel gridBPLunaLabel = new JPanel();
        JPanel gridBPAnLabel = new JPanel();
        JPanel buttonsArea = new JPanel();
        JPanel buttonsPanel = new JPanel();

        slujbaBoxPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        slujbaBoxPanel.setLayout(new GridLayout(1,2,5,0));
        listArea.setLayout(new BorderLayout());
        list.setLayout(new BorderLayout());
        infoButtonsArea.setLayout(new GridLayout(2,1,0,5));
        infoPanel.setLayout(new GridLayout(4,2,0,5));
        buttonsArea.setLayout(new GridBagLayout());
        buttonsPanel.setLayout(new GridLayout(4,2,10,15));
        gridBPTitle.setLayout(new GridBagLayout());
        gridBPZi.setLayout(new GridBagLayout());
        gridBPLuna.setLayout(new GridBagLayout());
        gridBPAn.setLayout(new GridBagLayout());
        gridBPTitleLabel.setLayout(new GridBagLayout());
        gridBPZiLabel.setLayout(new GridBagLayout());
        gridBPLunaLabel.setLayout(new GridBagLayout());
        gridBPAnLabel.setLayout(new GridBagLayout());

        JLabel titluLabel = new JLabel("Titlu:");
        JLabel ziLabel = new JLabel("Zi:");
        JLabel lunaLabel = new JLabel("Lună:");
        JLabel anLabel = new JLabel("An:");

        titleTA = new JTextArea(1,15);
        ziTA = new JTextArea(1,15);
        lunaTA = new JTextArea(1,15);
        anTA = new JTextArea(1,15);
        titleTA.setBorder(BorderFactory.createLineBorder(Color.black));
        ziTA.setBorder(BorderFactory.createLineBorder(Color.black));
        lunaTA.setBorder(BorderFactory.createLineBorder(Color.black));
        anTA.setBorder(BorderFactory.createLineBorder(Color.black));
        titleTA.setEditable(false);
        ziTA.setEditable(false);
        lunaTA.setEditable(false);
        anTA.setEditable(false);

        ArrayList<String> nume = new ArrayList<String>();

        for (int i = 0; i < continut.size(); i++) {
            nume.add(continut.get(i)[1]);
        }

        listaSlujbe = new JList(nume.toArray());
        listaSlujbe.addListSelectionListener(listListener);

        JScrollPane listaScroll = new JScrollPane();
        listaScroll.setViewportView(listaSlujbe);

        adaugaButton = new JButton("Adaugă slujbă");
        stergeButton = new JButton("Șterge slujba selectată");
        editButton = new JButton("Activare câmpuri");
        actualizeazaButton = new JButton("Actualizează lista");
        ajutorButton = new JButton("Ajutor");
        inchideButton = new JButton("Închide fereastra");
        salveazaButton = new JButton("Salvează");
        reseteazaButton = new JButton("Șterge conținut formular");

        adaugaButton.addActionListener(this);
        stergeButton.addActionListener(this);
        editButton.addActionListener(this);
        actualizeazaButton.addActionListener(this);
        ajutorButton.addActionListener(this);
        inchideButton.addActionListener(this);
        salveazaButton.addActionListener(this);
        reseteazaButton.addActionListener(this);

        salveazaButton.setEnabled(false);
        reseteazaButton.setEnabled(false);

        list.add(listaScroll,BorderLayout.CENTER);
        listArea.add(list, BorderLayout.CENTER);
        gridBPTitleLabel.add(titluLabel);
        gridBPTitle.add(titleTA);
        gridBPZiLabel.add(ziLabel);
        gridBPZi.add(ziTA);
        gridBPLunaLabel.add(lunaLabel);
        gridBPLuna.add(lunaTA);
        gridBPAnLabel.add(anLabel);
        gridBPAn.add(anTA);

        infoPanel.add(gridBPTitleLabel);
        infoPanel.add(gridBPTitle);
        infoPanel.add(gridBPZiLabel);
        infoPanel.add(gridBPZi);
        infoPanel.add(gridBPLunaLabel);
        infoPanel.add(gridBPLuna);
        infoPanel.add(gridBPAnLabel);
        infoPanel.add(gridBPAn);
        buttonsPanel.add(adaugaButton);
        buttonsPanel.add(stergeButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(actualizeazaButton);
        buttonsPanel.add(ajutorButton);
        buttonsPanel.add(inchideButton);
        buttonsPanel.add(salveazaButton);
        buttonsPanel.add(reseteazaButton);
        buttonsArea.add(buttonsPanel);
        infoButtonsArea.add(infoPanel);
        infoButtonsArea.add(buttonsArea);
        slujbaBoxPanel.add(listArea);
        slujbaBoxPanel.add(infoButtonsArea);
        slujbaDialogBox.add(slujbaBoxPanel);
        slujbaDialogBox.pack();

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==adaugaButton)
        {

            String error;
            error = console.getDataNewSlujba(titleTA.getText(),anTA.getText(),lunaTA.getText(),ziTA.getText());
            if(error.equals("valid!"))
            {
                actualizeazaButton.doClick();
                errorBox(error,"Slujba a fost adăugată cu succes!");
            }
            else
            {
                errorBox(error,"Slujba a fost adăugată cu succes!");
            }

        } else if (e.getSource() == actualizeazaButton)
        {
            slujbaDialogBox.dispose();
            SlujbeDialogBox reset = new SlujbeDialogBox(this.console);

        }
        else if(e.getSource() == stergeButton)
        {   String error;
            if(!continut.isEmpty()) {
            error = console.removeSlujba(continut.get(listaSlujbe.getAnchorSelectionIndex())[0]);
            actualizeazaButton.doClick();
            }
            else{
             errorBoxGUI();
            }
        }
        else if(e.getSource() == editButton)
        {
            titleTA.setEditable(true);
            ziTA.setEditable(true);
            lunaTA.setEditable(true);
            anTA.setEditable(true);
            salveazaButton.setEnabled(true);
            reseteazaButton.setEnabled(true);
        } else if (e.getSource() == reseteazaButton )
        {
            titleTA.setText(null);
            ziTA.setText(null);
            lunaTA.setText(null);
            anTA.setText(null);
        }
        else if(e.getSource() == salveazaButton)
        {   if(continut.isEmpty())
        {
            errorBoxGUI();
        }
        else {
            String error;
            error = console.callUpdateSlujba(continut.get(listaSlujbe.getAnchorSelectionIndex())[0], titleTA.getText(), anTA.getText(), lunaTA.getText(), ziTA.getText());
            errorBox(error, "Modificările au fost salvate!");
            if(error.equals("valid!"))
            {
                titleTA.setEditable(false);
                ziTA.setEditable(false);
                lunaTA.setEditable(false);
                anTA.setEditable(false);
                actualizeazaButton.doClick();
            }

        }
        } else if (e.getSource()==inchideButton)
        {
           slujbaDialogBox.dispose();
        }
        else if(e.getSource()==ajutorButton)
        {
            String continutAjutor;

            continutAjutor = "În partea stângă a ferestrei „Manager Slujbe” se află lista de slujbe create și programate\n" +
                    "În partea dreaptă, în prima jumătate a secțiunii se află câmpurile „Titlu”, „Zi”, „Lună”, „An”.\n" +
                    "Aceste câmpuri se actualizează cu informațiile slujbei selectate și sunt folosite pentru operațiile de adăugare și editare.\n" +
                    "În a doua jumatate a secțiunii se află butoanele.\n" +
                    "Butonul „Adaugă slujbă” face exact ce spune numele.\n" +
                    "Pentru a folosi butonul „Adaugă slujbă” trebuie întâi apăsat butonul „Activare câmpuri”.\n" +
                    "Butonul „Activare câmpuri” setează câmpurile drept editabile și activează butoanele „Salvează” și „Șterge conținut formular”, care fac exact ce sugerează numele lor.\n" +
                    "Butonul „Ajutor” afișează acest mesaj.\n" +
                    "Butonul „Șterge slujba selectată” face exact ce sugerează numele.\n" +
                    "Butonul „Actualizează lista” face exact ce sugerează numele.\n" +
                    "Butonul „Închide fereastra” face exact ce sugerează numele.";

            infoBox("Ajutor!",continutAjutor,350,335);
        }
    }

    ListSelectionListener listListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
          if(!e.getValueIsAdjusting()){
              titleTA.setText(continut.get(listaSlujbe.getAnchorSelectionIndex())[1]);
              ziTA.setText(continut.get(listaSlujbe.getAnchorSelectionIndex())[4]);
              lunaTA.setText(continut.get(listaSlujbe.getAnchorSelectionIndex())[3]);
              anTA.setText(continut.get(listaSlujbe.getAnchorSelectionIndex())[2]);
          }
        }
    };
}
