package org.example.swingInterfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminPanel extends JFrame {
    private CDDAO cdDao;
    private JTextField titleField;
    private JTextField artistField;
    private JTextField idField;
    private JTextArea displayArea;

    public AdminPanel() {
        cdDao = new CDDAO();
        setTitle("Gestion des CDs");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Champs d'entrée
        JPanel inputPanel = new JPanel();
        titleField = new JTextField(10);
        artistField = new JTextField(10);
        idField = new JTextField(5);

        inputPanel.add(new JLabel("Titre:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Artiste:"));
        inputPanel.add(artistField);
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);

        add(inputPanel, BorderLayout.NORTH);

        // Zone d'affichage
        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Boutons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Ajouter CD");
        JButton removeButton = new JButton("Supprimer CD");
        JButton updateButton = new JButton("Modifier CD");
        JButton viewButton = new JButton("Voir CDs");

        addButton.addActionListener(new AddCDAction());
        removeButton.addActionListener(new RemoveCDAction());
        updateButton.addActionListener(new UpdateCDAction());
        viewButton.addActionListener(new ViewCDAction());

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(viewButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class AddCDAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String artist = artistField.getText();
            int id = Integer.parseInt(idField.getText());
            cdDao.addCD(new CD(id, title, artist));
            clearFields();
            JOptionPane.showMessageDialog(null, "CD ajouté !");
        }
    }

    private class RemoveCDAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(idField.getText());
            cdDao.removeCD(id);
            clearFields();
            JOptionPane.showMessageDialog(null, "CD supprimé !");
        }
    }

    private class UpdateCDAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(idField.getText());
            String title = titleField.getText();
            String artist = artistField.getText();
            cdDao.updateCD(id, new CD(id, title, artist));
            clearFields();
            JOptionPane.showMessageDialog(null, "CD modifié !");
        }
    }

    private class ViewCDAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List<CD> cds = cdDao.getAllCDs();
            displayArea.setText("");
            for (CD cd : cds) {
                displayArea.append(cd.toString() + "\n");
            }
        }
    }

    private void clearFields() {
        titleField.setText("");
        artistField.setText("");
        idField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminPanel adminPanel = new AdminPanel();
            adminPanel.setVisible(true);
        });
    }
}

