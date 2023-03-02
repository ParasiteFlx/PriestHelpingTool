import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdaugarePredicaAnunt extends MainFrame implements ActionListener {

    Console console;
    JDialog adaugaSlide = new JDialog();
    JButton adaugaButton, resetButton, exitButton, ajutorButton;
    JTextArea title,text;
    JComboBox chooseType;


    public AdaugarePredicaAnunt(Console console){
        this.console = console;
        formularAdaugare();

    }

    private void formularAdaugare()
    {
        adaugaSlide.setTitle("Adauga un slide nou!");
        adaugaSlide.setSize(605,605);
        adaugaSlide.setLocationRelativeTo(null);
        adaugaSlide.setResizable(false);
        adaugaSlide.setVisible(true);

        JPanel adaugaPanel = new JPanel();
        JPanel typeTextPanel = new JPanel();
        JPanel typeTextArea = new JPanel();
        JPanel buttonsArea = new JPanel();
        JPanel buttonsPanel = new JPanel();

        adaugaPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        text = new JTextArea();
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBorder(BorderFactory.createLineBorder(Color.black));
        title = new JTextArea(1,13);
        title.setWrapStyleWord(true);
        title.setLineWrap(true);
        title.setBorder(BorderFactory.createLineBorder(Color.black));

        JScrollPane textScroll = new JScrollPane();
        textScroll.setViewportView(text);

        adaugaPanel.setLayout(new GridLayout(2,1));
        typeTextArea.setLayout(new BorderLayout());
        buttonsArea.setLayout(new GridBagLayout());
        buttonsPanel.setLayout(new GridLayout(2,2,30,30));

        chooseType = new JComboBox();
        chooseType.addItem("Predică");
        chooseType.addItem("Anunț");

        JLabel typeLabel = new JLabel("Tip slide:");
        JLabel titleLabel = new JLabel("Titlu:");

        adaugaButton = new JButton("Adaugă slide-ul la listă");
        resetButton = new JButton("Restează câmpurile");
        exitButton = new JButton("Închide fereastra");
        ajutorButton = new JButton("Ajutor");
        adaugaButton.addActionListener(this);
        resetButton.addActionListener(this);
        exitButton.addActionListener(this);
        ajutorButton.addActionListener(this);

        typeTextPanel.add(typeLabel);
        typeTextPanel.add(chooseType);
        typeTextPanel.add(titleLabel);
        typeTextPanel.add(title);
        typeTextArea.add(typeTextPanel,BorderLayout.PAGE_START);
        typeTextArea.add(textScroll,BorderLayout.CENTER);
        buttonsPanel.add(adaugaButton);
        buttonsPanel.add(resetButton);
        buttonsPanel.add(ajutorButton);
        buttonsPanel.add(exitButton);
        buttonsArea.add(buttonsPanel);
        adaugaPanel.add(typeTextArea);
        adaugaPanel.add(buttonsArea);
        adaugaSlide.add(adaugaPanel);
        adaugaSlide.pack();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==adaugaButton)
        {   String error;
            error = console.getDataNewAnunt(title.getText(),text.getText(),String.valueOf(chooseType.getSelectedIndex()+1));
            errorBox(error,"Slide-ul a fost adăugat cu succes!");



        }
        else if (e.getSource()==resetButton) {
              chooseType.setSelectedIndex(0);
              title.setText(null);
              text.setText(null);
        }
        else if (e.getSource()==exitButton) {
            adaugaSlide.dispose();
        }
        else if (e.getSource()==ajutorButton) {

            String continutAjutor = "În partea de sus a ferestrei „Adaugă un slide nou!”, în stânga, puteți selecta tipul dorit " +
                    "al slide-ului dacă apăsați pe săgeată. În partea dreaptă se află câmpul în care puteți introduce titlu.\n" +
                    "În partea de mijloc a ferestrei se află zona în care puteți introduce conținutul slide-ului.\n" +
                    "În partea de jos a ferestrei se află butoanele.\nButonul „Ajutor” afișează acest mesaj.\nButonul „Închide fereastra” " +
                    "închide fereastra „Adaugă un slide nou!”.\nButonul „Resetează câmpurile” șterge datele introduse și setează tipul slide-ului drept „Predică”.\n" +
                    "Butonul „Adaugă slide-ul la listă” adaugă formularul completat în lista de anunțuri/predici.";

            infoBox("Ajutor!",continutAjutor,300,275);
        }


    }
}
