import java.awt.*;
import java.awt.event.*;

public class GUI extends Frame implements ActionListener {
    TextField word1Field, word2Field;
    Button submitButton;
    Choice choice;
    TextArea resultList;
    Label result1, result2, result3;

    public GUI() {
        // Set up the frame using GridBagLayout for precise control over components
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints(), constraints2 = new GridBagConstraints();

        // Constraints common to all components
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 0;  // Single column
        constraints.gridy = GridBagConstraints.RELATIVE;  // Each component on a new row
        constraints.weightx = 1.0;  // Expand all components fully horizontally
        constraints.insets = new Insets(5, 10, 5, 10);  // Margin around components

        // Constraints common to all components
        constraints2.fill = GridBagConstraints.HORIZONTAL;
        constraints2.gridx = 0;  // Single column
        constraints2.gridy = GridBagConstraints.RELATIVE;  // Each component on a new row
        constraints2.weightx = 1.0;  // Expand all components fully horizontally
        constraints2.insets = new Insets(5, 10, 5, 10);  // Margin around components

        // First input with label
        Panel firstInputPanel = new Panel();
        firstInputPanel.add(new Label("Enter word 1:"));
        word1Field = new TextField(20);
        firstInputPanel.add(word1Field);
        add(firstInputPanel, constraints);

        // Second input with label
        Panel secondInputPanel = new Panel();
        secondInputPanel.add(new Label("Enter word 2:"));
        word2Field = new TextField(20);
        secondInputPanel.add(word2Field);
        add(secondInputPanel, constraints);

        // Choice dropdown
        choice = new Choice();
        choice.add("Uniform Cost Search (UCS)");
        choice.add("Greedy Best-First Search");
        choice.add("A* Search");
        choice.setPreferredSize(new Dimension(2000, 20));  // Set the preferred size
        add(choice, constraints);

        // Submit button
        submitButton = new Button("Cari");
        submitButton.addActionListener(this);
        add(submitButton, constraints);

        // Labels for the results
        result1 = new Label("");
        add(result1, constraints2);

        result2 = new Label("");
        add(result2, constraints2);

        result3 = new Label("");
        add(result3, constraints2);

        // Text area for multiple results
        resultList = new TextArea(5, 0);
        resultList.setPreferredSize(new Dimension(100,20));
        constraints.weighty = 1;  // Give extra vertical space to the text area
        constraints.fill = GridBagConstraints.BOTH;
        add(resultList, constraints);

        setTitle("Simple GUI");
        pack();
        setVisible(true);
        setExtendedState(Frame.MAXIMIZED_BOTH);

        // Handle window closing
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        result1.setText("");
        result2.setText("");
        result3.setText("");
        if(e.getSource() == submitButton){
            Main.start = word1Field.getText();
            Main.end = word2Field.getText();
            String selectedChoice = choice.getSelectedItem();
            
            if(Main.start.length() != Main.end.length()){
                result2.setText("2 Kata panjangnya berbeda");
            } else if(!Main.dictionary.contains(Main.start)){
                result2.setText(Main.start +" bukan kata yang valid");
            } else if(!Main.dictionary.contains(Main.end)){
                result2.setText(Main.end +" bukan kata yang valid");
            } else {
                try {
                    if(selectedChoice.equals("Uniform Cost Search (UCS)")){
                        Main.UCS();
                    } else if(selectedChoice.equals("Greedy Best-First Search")){
                        Main.GBFS();
                    } else {
                        Main.AS();
                    }
                    
                    result1.setPreferredSize(new Dimension(2000,20));
                    result2.setPreferredSize(new Dimension(2000,20));
                    result3.setPreferredSize(new Dimension(2000,20));

                    result1.setText("Panjang path : " + Main.path.size() + " langkah");
                    result2.setText("Node yang dikunjungi : " + Main.dikunjungi);
                    result3.setText("Waktu : " + (Main.endTime - Main.startTime) + " ms");

                    result1.setPreferredSize(new Dimension(2000,20));
                    result2.setPreferredSize(new Dimension(2000,20));
                    result3.setPreferredSize(new Dimension(2000,20));

                    resultList.setText(Main.start + "\n");  // Clear previous results
                    for (String item : Main.path) {
                        resultList.append(item + "\n");
                    }
                } catch (IndexOutOfBoundsException err) {
                    result2.setText("Tidak ditemukan path dari " + Main.start + " menuju " + Main.end + ".");
                }
            }
        }
    }

    public static void main(String[] args) {
        // Deklarasi set untuk menyimpan kata inggris yang valid
        Main.readDictionary("../src/dict.txt");
        new GUI();
    }
}
