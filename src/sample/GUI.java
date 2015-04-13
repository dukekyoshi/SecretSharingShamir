package sample;

import java.awt.*;
import javax.swing.*;
import ver1.QuestionsGenerator;

public class GUI {

    static JComponent[] components;
    static String[] labels;

    GUI() {
        QuestionsGenerator g = new QuestionsGenerator("questions.txt");
        g.read();
        labels = g.get();

        components = new JComponent[labels.length];
        for(int i = 0; i < components.length; i++) {
            components[i] = new JTextField(30);
        }
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {

            public void run() {
                GUI gui = new GUI();

                JComponent labelsAndFields = getComponentsAndLabels(labels, components);
                JComponent orderForm = new JPanel(new BorderLayout(5,5));
                orderForm.add(new JLabel("", SwingConstants.CENTER), BorderLayout.PAGE_START);
                orderForm.add(labelsAndFields, BorderLayout.CENTER);

                JOptionPane.showMessageDialog(null, orderForm);
            }
        };
        SwingUtilities.invokeLater(r);
    }

    public static JComponent getComponentsAndLabels(String[] labelStrings, JComponent[] fields) {
        JLabel[] l = new JLabel[labelStrings.length];
        for (int i = 0; i < labels.length; i++) {
            l[i] = new JLabel(labelStrings[i]);
        }
        return createComponent(l, fields);
    }

    public static JComponent createComponent(JLabel[] labels, JComponent[] fields) {
        if (labels.length != fields.length) {
            String s = labels.length + " labels supplied for "
                    + fields.length + " fields!";
            throw new IllegalArgumentException(s);
        }
        JComponent panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        //add gap between components
        layout.setAutoCreateGaps(true);
        
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        GroupLayout.Group yLabelGroup = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
        hGroup.addGroup(yLabelGroup);
        GroupLayout.Group yFieldGroup = layout.createParallelGroup();
        hGroup.addGroup(yFieldGroup);
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        layout.setVerticalGroup(vGroup);

        int p = GroupLayout.PREFERRED_SIZE;

        for(int i = 0; i < labels.length; i++) {
            yLabelGroup.addComponent(labels[i]);
        }

        for(int i = 0; i < fields.length; i++) {
            yFieldGroup.addComponent(fields[i], p, p, p);
        }

        for (int i = 0; i < labels.length; i++) {
            vGroup.addGroup(layout.createParallelGroup().
                   addComponent(labels[i]).
                   addComponent(fields[i], p, p, p));
        }

        return panel;
    }
}
