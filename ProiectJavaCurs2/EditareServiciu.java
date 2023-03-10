import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditareServiciu extends MainFrame implements ActionListener {

    Console console;

    String idSelectat;
    JDialog adaugareSBox = new JDialog();
    ArrayList<String[]> continut;
    JButton salveazaButton, ajutorButton, inchideButton, reseteazaButton;
    JList listaEnoriasi;
    JTextArea titluTA,enoriasTA, ziTA, lunaTA, anTA;

    EditareServiciu(Console console , String idSelectat)
    {
        this.console = console;
        this.idSelectat = idSelectat;
        continut = console.displayEnoriasi();
        setup();
    }

    private void setup()
    {
        adaugareSBox.setSize(750,500);
        adaugareSBox.setLocationRelativeTo(null);
        adaugareSBox.setResizable(false);
        adaugareSBox.setTitle("Editare serviciu religios");
        adaugareSBox.setVisible(true);

        JPanel addSRBoxPanel = new JPanel();
        JPanel listArea = new JPanel();
        JPanel list = new JPanel();
        JPanel infoButtonsArea = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel gridBPTitlu = new JPanel();
        JPanel gridBPEnorias = new JPanel();
        JPanel gridBPZi = new JPanel();
        JPanel gridBPLuna = new JPanel();
        JPanel gridBPAn = new JPanel();
        JPanel gridBPTitluLabel = new JPanel();
        JPanel gridBPEnoriasLabel = new JPanel();
        JPanel gridBPZiLabel = new JPanel();
        JPanel gridBPLunaLabel = new JPanel();
        JPanel gridBPAnLabel = new JPanel();
        JPanel buttonsArea = new JPanel();
        JPanel buttonsPanel = new JPanel();

        addSRBoxPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        addSRBoxPanel.setLayout(new GridLayout(1,2,5,0));
        listArea.setLayout(new BorderLayout());
        list.setLayout(new BorderLayout());
        infoButtonsArea.setLayout(new GridLayout(2,1,0,5));
        infoPanel.setLayout(new GridLayout(5,2,0,5));
        buttonsArea.setLayout(new GridBagLayout());
        buttonsPanel.setLayout(new GridLayout(2,2,10,15));

        gridBPTitlu.setLayout(new GridBagLayout());
        gridBPEnorias.setLayout(new GridBagLayout());
        gridBPZi.setLayout(new GridBagLayout());
        gridBPLuna.setLayout(new GridBagLayout());
        gridBPAn.setLayout(new GridBagLayout());
        gridBPTitluLabel.setLayout(new GridBagLayout());
        gridBPEnoriasLabel.setLayout(new GridBagLayout());
        gridBPZiLabel.setLayout(new GridBagLayout());
        gridBPLunaLabel.setLayout(new GridBagLayout());
        gridBPAnLabel.setLayout(new GridBagLayout());

        JLabel titluLabel = new JLabel("Titlu:");
        JLabel enoriasLabel = new JLabel("Enoria??:");
        JLabel ziLabel = new JLabel("Zi:");
        JLabel lunaLabel = new JLabel("Lun??:");
        JLabel anLabel = new JLabel("An:");

        titluTA = new JTextArea(1,15);
        enoriasTA = new JTextArea(1,15);
        ziTA = new JTextArea(1,15);
        lunaTA = new JTextArea(1,15);
        anTA = new JTextArea(1,15);
        titluTA.setBorder(BorderFactory.createLineBorder(Color.black));
        enoriasTA.setBorder(BorderFactory.createLineBorder(Color.black));
        ziTA.setBorder(BorderFactory.createLineBorder(Color.black));
        lunaTA.setBorder(BorderFactory.createLineBorder(Color.black));
        anTA.setBorder(BorderFactory.createLineBorder(Color.black));
        enoriasTA.setEditable(false);
        ArrayList<String> nume = new ArrayList<String>();

        for (int i = 0; i < continut.size(); i++) {
            nume.add(continut.get(i)[1]);
        }

        listaEnoriasi = new JList(nume.toArray());
        listaEnoriasi.addListSelectionListener(listListener);

        JScrollPane listaScroll = new JScrollPane();
        listaScroll.setViewportView(listaEnoriasi);

        salveazaButton = new JButton("Salveaz?? modfic??rile");
        ajutorButton = new JButton("Ajutor");
        inchideButton = new JButton("??nchide fereastra");
        reseteazaButton = new JButton("??terge con??inut formular");

        salveazaButton.addActionListener(this);
        ajutorButton.addActionListener(this);
        inchideButton.addActionListener(this);
        reseteazaButton.addActionListener(this);

        list.add(listaScroll,BorderLayout.CENTER);
        listArea.add(list, BorderLayout.CENTER);
        gridBPTitluLabel.add(titluLabel);
        gridBPTitlu.add(titluTA);
        gridBPEnoriasLabel.add(enoriasLabel);
        gridBPEnorias.add(enoriasTA);
        gridBPZiLabel.add(ziLabel);
        gridBPZi.add(ziTA);
        gridBPLunaLabel.add(lunaLabel);
        gridBPLuna.add(lunaTA);
        gridBPAnLabel.add(anLabel);
        gridBPAn.add(anTA);
        infoPanel.add(gridBPTitluLabel);
        infoPanel.add(gridBPTitlu);
        infoPanel.add(gridBPEnoriasLabel);
        infoPanel.add(gridBPEnorias);
        infoPanel.add(gridBPZiLabel);
        infoPanel.add(gridBPZi);
        infoPanel.add(gridBPLunaLabel);
        infoPanel.add(gridBPLuna);
        infoPanel.add(gridBPAnLabel);
        infoPanel.add(gridBPAn);
        buttonsPanel.add(salveazaButton);
        buttonsPanel.add(ajutorButton);
        buttonsPanel.add(inchideButton);
        buttonsPanel.add(reseteazaButton);
        buttonsArea.add(buttonsPanel);
        infoButtonsArea.add(infoPanel);
        infoButtonsArea.add(buttonsArea);
        addSRBoxPanel.add(listArea);
        addSRBoxPanel.add(infoButtonsArea);
        adaugareSBox.add(addSRBoxPanel);
        adaugareSBox.pack();
    }

    ListSelectionListener listListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(!e.getValueIsAdjusting()){

                enoriasTA.setText(continut.get(listaEnoriasi.getAnchorSelectionIndex())[1]);

            }
        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == inchideButton)
        {
            adaugareSBox.dispose();
        } else if (e.getSource() == reseteazaButton)
        {
            titluTA.setText(null);
            listaEnoriasi.clearSelection();
            enoriasTA.setText(null);
            ziTA.setText(null);
            lunaTA.setText(null);
            anTA.setText(null);
        } else if (e.getSource() == ajutorButton) {

            String continutAjutor = "??n partea st??ng?? a ferestrei ???Editare serviciu religios??? se afl?? lista cu enoria??ii parohiei din care se selecteaz?? enoria??ul pentru care se programeaz?? serviciul religios.\n" +
                    "??n partea dreapt??, ??n prima jum??tate a sec??iunii se afl?? c??mpurile ???Titlu???,???Enoria?????, ???Zi???, ???Lun?????, ???An???.\n" +
                    "??n aceste c??mpuri se modific?? datele dorite.\n" +
                    "??n a doua jumatate a sec??iunii se afl?? butoanele.\n" +
                    "Butonul ???Salveaz?? modific??rile??? face exact ce spune numele.\n" +
                    "Butonul ???Ajutor??? afi??eaz?? acest mesaj.\n" +
                    "Butonul ?????nchide fereastra??? face exact ce sugereaz?? numele." +
                    "Butonul ?????terge con??inut formular??? face ce sugereaz?? numele.";

            infoBox("Ajutor!",continutAjutor,300,270);
        }
        else if(e.getSource() == salveazaButton)
        {
            String error;
            error = console.callUpdateServiciu(idSelectat,continut.get(listaEnoriasi.getAnchorSelectionIndex())[0],titluTA.getText(),anTA.getText(),lunaTA.getText(),ziTA.getText());
            errorBox(error,"Serviciul a fost modificat cu succes!");

        }
    }
}


