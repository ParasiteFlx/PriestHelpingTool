import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ServiciiDialogBox extends MainFrame implements ActionListener {

    Console console;
    ArrayList<String[]> continut;
    private JDialog serviciiDialogBox = new JDialog();
    private JButton adaugaButton, editButton, ajutorButton, detaliiButton, stergeButton, inchideButton, actualizareButton;
    JList listaServicii;

    public ServiciiDialogBox(Console console)
    {
        this.console = console;
        continut = console.displayServicii();
        serviciiBox();
    }



    private void serviciiBox()
    {
        serviciiDialogBox.setSize(550,550);
        serviciiDialogBox.setLocationRelativeTo(null);
        serviciiDialogBox.setResizable(false);
        serviciiDialogBox.setTitle("Manager Servicii Religioase");
        serviciiDialogBox.setVisible(true);

        JPanel srvDialogBox  = new JPanel();
        JPanel listArea = new JPanel();
        JPanel buttonsArea = new JPanel();
        JPanel buttonsPanel = new JPanel();
        JPanel listPanel = new JPanel();

        srvDialogBox.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        srvDialogBox.setLayout(new GridLayout(1,2));
        buttonsArea.setLayout(new GridBagLayout());
        listArea.setLayout(new BorderLayout());
        buttonsPanel.setLayout(new GridLayout(7,1,5,30));
        listPanel.setLayout(new BorderLayout());

        ArrayList<String> nume = new ArrayList<String>();

        for (int i = 0; i < continut.size(); i++) {
            nume.add(continut.get(i)[1] + "-" + continut.get(i)[5]);
        }

        listaServicii = new JList(nume.toArray());


        JScrollPane scrollPanelList = new JScrollPane();
        //scrollPanelList.setBorder(BorderFactory.createLineBorder(Color.red));
        scrollPanelList.setViewportView(listaServicii);

        listaServicii.setLayoutOrientation(JList.VERTICAL);
        listaServicii.setVisible(true);
        listaServicii.setBorder(BorderFactory.createLineBorder(Color.black));

        //listPanel.add(listaServicii);
        listPanel.add(scrollPanelList);
        listArea.add(listPanel,BorderLayout.CENTER);
        srvDialogBox.add(listArea);


        //buttonsArea.setBorder(BorderFactory.createLineBorder(Color.black));
        //buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        adaugaButton = new JButton("Adaug?? serviciu religios");
        detaliiButton = new JButton("Detalii despre serviciul religios selectat");
        editButton = new JButton("Modific?? serviciul religios selectat");
        stergeButton = new JButton("??terge serviciul religios selectat");
        ajutorButton = new JButton("Ajutor");
        inchideButton = new JButton("??nchide fereastra");
        actualizareButton = new JButton("Actualizeaz?? lista");

        buttonsPanel.add(adaugaButton);
        buttonsPanel.add(actualizareButton);
        buttonsPanel.add(detaliiButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(stergeButton);
        buttonsPanel.add(ajutorButton);
        buttonsPanel.add(inchideButton);

        adaugaButton.addActionListener(this);
        inchideButton.addActionListener(this);
        actualizareButton.addActionListener(this);
        stergeButton.addActionListener(this);
        editButton.addActionListener(this);
        detaliiButton.addActionListener(this);
        ajutorButton.addActionListener(this);

        buttonsArea.add(buttonsPanel);
        srvDialogBox.add(listArea);
        srvDialogBox.add(buttonsArea);

        serviciiDialogBox.add(srvDialogBox);
        serviciiDialogBox.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == adaugaButton)
        {
            AdaugareServiciuReligios nou = new AdaugareServiciuReligios(this.console);
        }
        else if(e.getSource() == inchideButton)
        {
            serviciiDialogBox.dispose();
        }
        else if (e.getSource() == actualizareButton)
        {
            serviciiDialogBox.dispose();
            ServiciiDialogBox reset = new ServiciiDialogBox(this.console);
        }
        else if(e.getSource() == stergeButton)
        {
            String error;
            if(!continut.isEmpty()) {
                error = console.removeServiciu(continut.get(listaServicii.getAnchorSelectionIndex())[0]);
                actualizareButton.doClick();
            }
            else{
                errorBoxGUI();
            }
        } else if (e.getSource() == editButton) {

           if(!continut.isEmpty())
           {
               EditareServiciu edit = new EditareServiciu(this.console,continut.get(listaServicii.getAnchorSelectionIndex())[0]);
           }
           else
           {
               errorBoxGUI();
           }

        }
        else if(e.getSource() == detaliiButton)
        {
            if(!continut.isEmpty())
            {
                String detalii  = "Titlu: " + continut.get(listaServicii.getAnchorSelectionIndex())[1] +
                                  "\nEnoria??:" + continut.get(listaServicii.getAnchorSelectionIndex())[5] +
                                  "\nData: " + continut.get(listaServicii.getAnchorSelectionIndex())[4] + "/" + continut.get(listaServicii.getAnchorSelectionIndex())[3] + "/" + continut.get(listaServicii.getAnchorSelectionIndex())[2];
                infoBox("Detalii serviciu selectat",detalii,125,125);
            }
            else
            {
                errorBoxGUI();
            }
        }
        else if(e.getSource() == ajutorButton)
        {
            String continutAjutor;

            continutAjutor = "??n partea st??ng?? a ferestrei ???Manager Servicii Religioase??? se afl?? lista de servicii religioase create ??i programate\n" +
                    "??n partea dreapt??, se afl?? butoanele.\n" +
                    "Butonul ???Adaug?? serviciu religios??? deschide o fereastr?? corespunz??toare opera??iei.\n" +
                    "Butonul ???Actualizeaz?? lista??? face ce sugereaz?? numele.\n" +
                    "Butonul ???Detalii despre serviciul religios selectat??? face ce sugereaz?? numele.\n"+
                    "Butonul ???Modific?? serviciul religios selectat??? deschide o fereastr?? corespunz??toare opera??iei.\n"+
                    "Butonul ?????terge serviciul religios selectat??? face ce sugereaz?? numele.\n" +
                    "Butonul ???Ajutor??? afi??eaz?? acest mesaj.\n" +
                    "Butonul ?????nchide fereastra??? face ce sugereaz?? numele.";

            infoBox("Ajutor!",continutAjutor,350,265);
        }


    }
}
