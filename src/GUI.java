import java.awt.*;
import java.awt.event.*;

public class GUI extends Frame implements ActionListener {
    TextField startField, endField;
    Button findButton;
    Choice choice;
    TextArea path;
    Label pathSize, nodeSize, time;

    public GUI() {
        // Set Layout
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints(), constraints2 = new GridBagConstraints();

        // Constraints agar flow kebawah dan center
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 0;  // 1 kolom
        constraints.gridy = GridBagConstraints.RELATIVE;  // flow kebawah
        constraints.weightx = 1.0;  // width : 100%
        constraints.insets = new Insets(5, 10, 5, 10);  // Margin

        // Constraints untuk output
        constraints2.fill = GridBagConstraints.HORIZONTAL;
        constraints2.gridx = 0;  // 1 kolom
        constraints2.gridy = GridBagConstraints.RELATIVE;  // flow kebawah
        constraints2.weightx = 1.0;  // width : 100%
        constraints2.insets = new Insets(5, 10, 5, 10);  // Margin

        // Input start
        Panel firstInputPanel = new Panel();
        firstInputPanel.add(new Label("Enter word 1:"));
        startField = new TextField(20);
        firstInputPanel.add(startField);
        add(firstInputPanel, constraints);

        // Input end
        Panel secondInputPanel = new Panel();
        secondInputPanel.add(new Label("Enter word 2:"));
        endField = new TextField(20);
        secondInputPanel.add(endField);
        add(secondInputPanel, constraints);

        // Pilihan algoritma
        choice = new Choice();
        choice.add("Uniform Cost Search (UCS)");
        choice.add("Greedy Best-First Search");
        choice.add("A* Search");
        choice.setPreferredSize(new Dimension(2000, 20));  // Set the preferred size
        add(choice, constraints);

        // Button cari
        findButton = new Button("Cari");
        findButton.addActionListener(this);
        add(findButton, constraints);

        // Label output
        pathSize = new Label("");
        add(pathSize, constraints2);
        nodeSize = new Label("");
        add(nodeSize, constraints2);
        time = new Label("");
        add(time, constraints2);

        // Label path
        path = new TextArea(5, 0);
        path.setPreferredSize(new Dimension(100,20));
        constraints.weighty = 1;  // Give extra vertical space to the text area
        constraints.fill = GridBagConstraints.BOTH;
        add(path, constraints);

        setTitle("World Ladder Solver");
        pack();
        setVisible(true);
        setExtendedState(Frame.MAXIMIZED_BOTH);

        // Handle tutup window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    // Fungsi jika button ditekan
    public void actionPerformed(ActionEvent e) {
        // Mereset label output
        pathSize.setText("");
        nodeSize.setText("");
        time.setText("");
        if(e.getSource() == findButton){
            Algo.start = startField.getText();
            Algo.end = endField.getText();
            String selectedChoice = choice.getSelectedItem();
            
            // validasi input
            if(Algo.start.length() != Algo.end.length()){
                nodeSize.setText("2 Kata panjangnya berbeda");
            } else if(!Algo.dictionary.contains(Algo.start)){
                nodeSize.setText(Algo.start +" bukan kata yang valid");
            } else if(!Algo.dictionary.contains(Algo.end)){
                nodeSize.setText(Algo.end +" bukan kata yang valid");
            } else {
                try {
                    // pemanggilan fungsi algoritma
                    if(selectedChoice.equals("Uniform Cost Search (UCS)")){
                        Algo.UCS();
                    } else if(selectedChoice.equals("Greedy Best-First Search")){
                        Algo.GBFS();
                    } else {
                        Algo.AS();
                    }

                    // Output ke layar
                    pathSize.setText("Panjang path : " + Algo.path.size() + " langkah");
                    nodeSize.setText("Node yang dikunjungi : " + Algo.dikunjungi);
                    time.setText("Waktu : " + (Algo.endTime - Algo.startTime) + " ms");

                    path.setText(Algo.start + "\n");  // Clear previous results
                    for (String item : Algo.path) {
                        path.append(item + "\n");
                    }
                } catch (IndexOutOfBoundsException err) {
                    // Jika path tidak ada
                    nodeSize.setText("Tidak ditemukan path dari " + Algo.start + " menuju " + Algo.end + ".");
                }
            }
        }
    }

    public static void main(String[] args) {
        // Deklarasi set untuk menyimpan kata inggris yang valid
        Algo.readDictionary("src/dict.txt");
        new GUI();
    }
}