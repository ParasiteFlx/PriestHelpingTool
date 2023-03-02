import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EnoriasiDialogBox extends  MainFrame implements ActionListener {

    Console console;
    JDialog enoriasiDB = new JDialog();
    ArrayList<String[]> continut;
    JButton adaugaButton, stergeButton, editButton, actualizeazaButton, ajutorButton, inchideButton, salveazaButton, reseteazaButton;
    JTextArea numeTA, telefonTA, adresaTA;
    JList listaEnoriasi;

    EnoriasiDialogBox(Console console)
    {
        this.console = console;
        continut = console.displayEnoriasi();
        enoriasDBSetup();
    }

    private void enoriasDBSetup()
    {
        enoriasiDB.setSize(750,500);
        enoriasiDB.setLocationRelativeTo(null);
        enoriasiDB.setResizable(false);
        enoriasiDB.setTitle("Manager enoriași");
        enoriasiDB.setVisible(true);

        JPanel enoriasBoxPanel = new JPanel();
        JPanel listArea = new JPanel();
        JPanel list = new JPanel();
        JPanel infoButtonsArea = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel gridBPNume = new JPanel();
        JPanel gridBPTelefon = new JPanel();
        JPanel gridBPAdresa = new JPanel();
        JPanel gridBPNumeLabel = new JPanel();
        JPanel gridBPTelefonLabel = new JPanel();
        JPanel gridBPAdresaLabel = new JPanel();
        JPanel buttonsArea = new JPanel();
        JPanel buttonsPanel = new JPanel();

        enoriasBoxPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        enoriasBoxPanel.setLayout(new GridLayout(1,2,5,0));
        listArea.setLayout(new BorderLayout());
        list.setLayout(new BorderLayout());
        infoButtonsArea.setLayout(new GridLayout(2,1,0,5));
        infoPanel.setLayout(new GridLayout(3,2,0,5));
        buttonsArea.setLayout(new GridBagLayout());
        buttonsPanel.setLayout(new GridLayout(4,2,10,15));
        gridBPNume.setLayout(new GridBagLayout());
        gridBPTelefon.setLayout(new GridBagLayout());
        gridBPAdresa.setLayout(new GridBagLayout());
        gridBPNumeLabel.setLayout(new GridBagLayout());
        gridBPTelefonLabel.setLayout(new GridBagLayout());
        gridBPAdresaLabel.setLayout(new GridBagLayout());

        JLabel numeLabel = new JLabel("Nume:");
        JLabel telefonLabel = new JLabel("Telefon:");
        JLabel adresaLabel = new JLabel("Adresă:");

        numeTA = new JTextArea(1,15);
        telefonTA = new JTextArea(1,15);
        adresaTA = new JTextArea(1,15);

        numeTA.setBorder(BorderFactory.createLineBorder(Color.black));
        telefonTA.setBorder(BorderFactory.createLineBorder(Color.black));
        adresaTA.setBorder(BorderFactory.createLineBorder(Color.black));

        numeTA.setEditable(false);
        telefonTA.setEditable(false);
        adresaTA.setEditable(false);

        ArrayList<String> nume = new ArrayList<String>();

        for (int i = 0; i < continut.size(); i++) {
            nume.add(continut.get(i)[1]);
        }

        listaEnoriasi = new JList(nume.toArray());
        listaEnoriasi.addListSelectionListener(listListener);

        JScrollPane listaScroll = new JScrollPane();
        listaScroll.setViewportView(listaEnoriasi);

        adaugaButton = new JButton("Adaugă enoriaș");
        stergeButton = new JButton("Șterge enoriașul selectat");
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

        gridBPNumeLabel.add(numeLabel);
        gridBPNume.add(numeTA);
        gridBPTelefonLabel.add(telefonLabel);
        gridBPTelefon.add(telefonTA);
        gridBPAdresaLabel.add(adresaLabel);
        gridBPAdresa.add(adresaTA);
        infoPanel.add(gridBPNumeLabel);
        infoPanel.add(gridBPNume);
        infoPanel.add(gridBPTelefonLabel);
        infoPanel.add(gridBPTelefon);
        infoPanel.add(gridBPAdresaLabel);
        infoPanel.add(gridBPAdresa);
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
        enoriasBoxPanel.add(listArea);
        enoriasBoxPanel.add(infoButtonsArea);
        enoriasiDB.add(enoriasBoxPanel);
        enoriasiDB.pack();


    }

    ListSelectionListener listListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {

            if(!e.getValueIsAdjusting()){
                numeTA.setText(continut.get(listaEnoriasi.getAnchorSelectionIndex())[1]);
                telefonTA.setText(continut.get(listaEnoriasi.getAnchorSelectionIndex())[2]);
                adresaTA.setText(continut.get(listaEnoriasi.getAnchorSelectionIndex())[3]);
            }

        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == adaugaButton) {
            String error;
            error = console.getDataNewEnorias(numeTA.getText(), telefonTA.getText(), adresaTA.getText());
            if (error.equals("valid!")) {
                actualizeazaButton.doClick();
                errorBox(error, "Enoriaș a fost adăugată cu succes!");
            } else {
                errorBox(error, "Enoriaș a fost adăugată cu succes!");
            }
        } else if (e.getSource() == editButton) {
            numeTA.setEditable(true);
            telefonTA.setEditable(true);
            adresaTA.setEditable(true);
            salveazaButton.setEnabled(true);
            reseteazaButton.setEnabled(true);
        } else if (e.getSource() == actualizeazaButton) {
            enoriasiDB.dispose();
            EnoriasiDialogBox reset = new EnoriasiDialogBox(this.console);
        } else if (e.getSource() == stergeButton) {
            String error;
            if (!continut.isEmpty()) {
                error = console.removeEnorias(continut.get(listaEnoriasi.getAnchorSelectionIndex())[0]);
                actualizeazaButton.doClick();
            } else {
                errorBoxGUI();
            }
        } else if (e.getSource() == inchideButton) {
            enoriasiDB.dispose();
        } else if (e.getSource() == reseteazaButton) {
            numeTA.setText(null);
            telefonTA.setText(null);
            adresaTA.setText(null);
        } else if (e.getSource() == salveazaButton) {
            if (continut.isEmpty()) {
                errorBoxGUI();
            } else {
                String error;
                error = console.callUpdateEnorias(continut.get(listaEnoriasi.getAnchorSelectionIndex())[0], numeTA.getText(), telefonTA.getText(), adresaTA.getText());
                errorBox(error, "Modificările au fost salvate!");
                if (error.equals("valid!")) {
                    numeTA.setEditable(false);
                    telefonTA.setEditable(false);
                    adresaTA.setEditable(false);
                    actualizeazaButton.doClick();
                }
            }

        } else if(e.getSource()== ajutorButton)
        {
            String continutAjutor;

            continutAjutor = "În partea stângă a ferestrei „Manager enoriași” se află lista cu enoriașii care aparțin de parohie.\n" +
                    "În partea dreaptă, în prima jumătate a secțiunii se află câmpurile „Nume”, „Telefon”, „Adresă”.\n" +
                    "Aceste câmpuri se actualizează cu informațiile enoriașului selectat și sunt folosite pentru operațiile de adăugare și editare.\n" +
                    "În a doua jumatate a secțiunii se află butoanele.\n" +
                    "Butonul „Adaugă enoriaș” face exact ce sugerează numele.\n" +
                    "Pentru a folosi butonul „Adaugă enoriaș” trebuie întâi apăsat butonul „Activare câmpuri”.\n" +
                    "Butonul „Activare câmpuri” setează câmpurile drept editabile și activează butoanele „Salvează” și „Șterge conținut formular”, care fac exact ce sugerează numele lor.\n" +
                    "Butonul „Ajutor” afișează acest mesaj.\n" +
                    "Butonul „Șterge enoriașul selectat” face exact ce sugerează numele.\n" +
                    "Butonul „Actualizează lista” face exact ce sugerează numele.\n" +
                    "Butonul „Închide fereastra” face exact ce sugerează numele.";

            infoBox("Ajutor!",continutAjutor,350,335);
        }
    }
}
