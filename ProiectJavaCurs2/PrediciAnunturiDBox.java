import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PrediciAnunturiDBox extends MainFrame implements ActionListener {

    Console console;
    private JDialog prediciAnunturiDialogBox = new JDialog();
    private JComboBox chooseType;
    private JTextArea title,text;
    private JButton urmatorulButton, anteriorulButton, adaugaButton, editButton, stergeButton,ajutorButton,salveazaButton,resetButton,actualizareButton;
    private ArrayList<String[]> continut;
    private int position = 0, minPosition = 0, maxPosition;

    public PrediciAnunturiDBox(Console console)
    {
        this.console = console;
        continut = this.console.displayAnunturi();
        if(continut.isEmpty())
        {
            maxPosition = 0;
        }
        else
        {
            maxPosition = continut.size()-1;
        }
        prediciAnunturiBox();
    }

    private void setTextArea()
    {
        title.setText(continut.get(position)[1]);
        text.setText(continut.get(position)[2]);

        if(continut.get(position)[3].equals("false"))
        {
            chooseType.setSelectedIndex(0);
        }
        else
        {
            chooseType.setSelectedIndex(1);
        }
    }

    private void prediciAnunturiBox()
    {
        prediciAnunturiDialogBox.setSize(715,715);
        prediciAnunturiDialogBox.setLocationRelativeTo(null);
        prediciAnunturiDialogBox.setResizable(false);
        prediciAnunturiDialogBox.setTitle("Manager predici/anunțuri");
        prediciAnunturiDialogBox.setVisible(true);

        JPanel paDialogBox = new JPanel();
        JPanel textPanel = new JPanel();
        JPanel textArea = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel buttonsArea = new JPanel();
        JPanel buttonsPanel = new JPanel();

        paDialogBox.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel typeLabel = new JLabel("Tip Slide: ");
        JLabel titleLabel = new JLabel("Title:");

        chooseType = new JComboBox();
        chooseType.addItem("Predică");
        chooseType.addItem("Anunț");

        chooseType.setEnabled(false);

        title = new JTextArea(1,13);
        title.setBorder(BorderFactory.createLineBorder(Color.black));
        title.setLineWrap(true);
        title.setEditable(false);

        JScrollPane textScroll = new JScrollPane();
        text = new JTextArea();
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        textScroll.setViewportView(text);

        if(!continut.isEmpty())
        {
          setTextArea();
        }

        paDialogBox.setLayout(new GridLayout(2,1));
        textArea.setLayout(new BorderLayout());
        textPanel.setLayout(new BorderLayout());
        buttonsArea.setLayout(new GridBagLayout());
        buttonsPanel.setLayout(new GridLayout(3,3,5,30));

        urmatorulButton = new JButton("Slide-ul următor");
        anteriorulButton = new JButton("Slide-ul anterior");
        adaugaButton = new JButton("Adaugă un slide nou");
        editButton = new JButton("Editează slide-ul curent");
        stergeButton = new JButton("Șterge slide-ul curent");
        ajutorButton = new JButton("Ajutor");
        salveazaButton = new JButton("Salvează modificările efectuate");
        resetButton = new JButton("Șterge conținutul slide-ului curent");
        actualizareButton = new JButton("Actualizare listă");

        salveazaButton.setEnabled(false);
        resetButton.setEnabled(false);

        adaugaButton.addActionListener(this);
        ajutorButton.addActionListener(this);
        urmatorulButton.addActionListener(this);
        anteriorulButton.addActionListener(this);
        actualizareButton.addActionListener(this);
        stergeButton.addActionListener(this);
        editButton.addActionListener(this);
        salveazaButton.addActionListener(this);
        resetButton.addActionListener(this);

        titlePanel.add(typeLabel);
        titlePanel.add(chooseType);
        titlePanel.add(titleLabel);
        titlePanel.add(title);
        textPanel.add(textScroll,BorderLayout.CENTER);
        textArea.add(titlePanel,BorderLayout.PAGE_START);
        textArea.add(textPanel,BorderLayout.CENTER);
        buttonsPanel.add(urmatorulButton);
        buttonsPanel.add(anteriorulButton);
        buttonsPanel.add(adaugaButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(stergeButton);
        buttonsPanel.add(ajutorButton);
        buttonsPanel.add(salveazaButton);
        buttonsPanel.add(actualizareButton);
        buttonsPanel.add(resetButton);
        buttonsArea.add(buttonsPanel);
        paDialogBox.add(textArea);
        paDialogBox.add(buttonsArea);
        prediciAnunturiDialogBox.add(paDialogBox);
        prediciAnunturiDialogBox.pack();


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==adaugaButton)
        {
            AdaugarePredicaAnunt nou = new AdaugarePredicaAnunt(this.console);
            continut = console.displayAnunturi();
            maxPosition++;
        }
        else if (e.getSource()==urmatorulButton)
        {   if(continut.isEmpty())
        {
            errorBoxGUI();
        }
        else{
            if(position<maxPosition)
            {
                position++;
                setTextArea();

            } else if (position==maxPosition)
            {
                position = 0;
                setTextArea();

            }
        }
        }
        else if (e.getSource()==anteriorulButton)
        {   if(continut.isEmpty())
        {
            errorBoxGUI();
        }
        else{
            if(position>minPosition)
            {
                position--;
                setTextArea();

            } else if (position==minPosition)
            {
                position = maxPosition;
                setTextArea();

            }
        }
        }
        else if(e.getSource()==ajutorButton)
        {   String continutAjutor;
            int latime, inaltime;

            continutAjutor = "În partea de sus a ferestrei „Manager predici/anunțuri”, în stânga, este afișat tipul slide-ului curent " +
                    "În partea dreaptă se află câmpul în care se află titlu slide-ului curent.\n" +
                    "În partea de mijloc a ferestrei se află zona în care se află conținutul slide-ului curent.\n" +
                    "În partea de jos a ferestrei se află butoanele.\nButonul „Ajutor” afișează acest mesaj.\nButonul „Actualizare listă” " +
                    "repornește fereastra „Manager predici/anunțuri” pentru a actualiza lista.\nButonul „Slide-ul următor” trece la slide-ul următor.\n" +
                    "Butonul „Slide-ul anterior” trece la slide-ul anterior.\nButonul „Șterge slide-ul curent” șterge slide-ul curent." +
                    "\nButonul „Editează slide-ul curent” activează editarea câmpurilor din slide-ul afișat cât și butoanele „Salvează modificările efectuate” și „Șterge conținutul slide-ului curent” care fac ce sugerează numele.";

            infoBox("Ajutor!",continutAjutor,350,300);

        }else if(e.getSource()==editButton)
        {
            if(continut.isEmpty())
            {
                errorBoxGUI();
            }
            else {
                salveazaButton.setEnabled(true);
                resetButton.setEnabled(true);
                title.setEditable(true);
                text.setEditable(true);
                chooseType.setEnabled(true);
            }


        }
        else if(e.getSource()==actualizareButton)
        {
            prediciAnunturiDialogBox.dispose();
            PrediciAnunturiDBox restart = new PrediciAnunturiDBox(this.console);
        }
        else if(e.getSource()==stergeButton)
        {
            if(continut.isEmpty())
            {
                errorBoxGUI();
            }
            else {
                String error;
                error = console.removeAnunt(continut.get(position)[0]);
                //errorBox(error, "Slide-ul a fost șters cu succes!");
                actualizareButton.doClick();
            }
        }
        else if(e.getSource()==resetButton)
        {
             title.setText(null);
             text.setText(null);
             chooseType.setSelectedIndex(0);
        } else if (e.getSource()==salveazaButton)
        {
            String change;
            if(chooseType.getSelectedIndex() == 0)
            {
                change = "false";
            }
            else
            {
                change = "true";
            }

            String error;
            error =  console.callUpdateAnunt(continut.get(position)[0],title.getText(),text.getText(),change);
            errorBox(error,"Slide-ul a fost editat cu succes!");
            if(error.equals("valid!"))
            {salveazaButton.setEnabled(false);
            resetButton.setEnabled(false);
            title.setEditable(false);
            text.setEditable(false);
            chooseType.setEditable(false);}
            actualizareButton.doClick();
        }


    }
}
