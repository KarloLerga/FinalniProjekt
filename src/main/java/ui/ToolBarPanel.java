package ui;

import event.ToolBarEvent;
import event.ToolBarListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa ToolBarPanel predstavlja panel alatne trake s gumbima za pregled narudžbi i odjavu.
 * Omogućuje korisnicima da pregledaju svoje narudžbe ili se odjave iz aplikacije.
 */
public class ToolBarPanel extends JPanel {
    private JButton viewOrdersButton, logoutButton; // Gumbi za pregled narudžbi i odjavu
    private ToolBarListener toolBarListener; // Listener za događaje alatne trake

    /**
     * Konstruktor ToolBarPanel inicijalizira gumbe i postavlja action listenere za događaje.
     */
    public ToolBarPanel() {
        viewOrdersButton = new JButton("View Orders");
        logoutButton = new JButton("Logout"); // Gumb za odjavu

        // Action listener za gumb "View Orders"
        viewOrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (toolBarListener != null) {
                    // Aktivira događaj pregleda narudžbi
                    toolBarListener.toolBarEventOccurred(new ToolBarEvent(false, true));
                }
            }
        });

        // Action listener za gumb "Logout"
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (toolBarListener != null) {
                    // Aktivira događaj odjave
                    toolBarListener.toolBarEventOccurred(new ToolBarEvent(true, false));
                }
            }
        });

        // Dodaje gumbe na alatnu traku
        add(viewOrdersButton);
        add(logoutButton);
    }

    /**
     * Postavlja listener za događaje alatne trake.
     *
     * @param listener Objekt koji implementira ToolBarListener sučelje.
     */
    public void setToolBarListener(ToolBarListener listener) {
        this.toolBarListener = listener;
    }
}
