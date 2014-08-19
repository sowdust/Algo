package vinci.esercizio3;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.GraphicsEnvironment.*;

/**
 * La classe BinTree implementa "in stile libro di testo" la struttura ricorsiva
 * "Albero binario di interi". I metodi sono tutti statici.
 */
public class BinTree {

    public int element;
    BinTree left;
    BinTree right;

    public BinTree(int e, BinTree l, BinTree r) {
        element = e;
        left = l;
        right = r;
    }

    public BinTree(int e) {
        element = e;
        left = null;
        right = null;
    }

    public static BinTree binTree3(int e, int el, int er) {
        return new BinTree(e, new BinTree(el), new BinTree(er));
    }

    static int numFrame = 0;

    /**
     * disegna l'albero t in una finestra di titolo title
     */
    public static void draw(BinTree t, String title) {
        final JFrame frame = new JFrame(title);
        JPanel controlPanel = new JPanel();
        JButton refreshButton = new JButton("refresh");

        refreshButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.repaint();
                    }
                }
        );

        controlPanel.add(refreshButton);
        GraphicsEnvironment ge = getLocalGraphicsEnvironment();
        Rectangle bounds = ge.getMaximumWindowBounds();
        int x = bounds.x + bounds.width / 4 + numFrame * 30;
        int y = bounds.y + bounds.height / 4 + (numFrame++) * 30;
        frame.setBounds(x, y, 2 * bounds.width / 3, 2 * bounds.height / 3);
        frame.add(BorderLayout.SOUTH, controlPanel);
        frame.add(BorderLayout.CENTER, new DrawingPanel(t));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * costruisce e restituisce un albero binario descritto in un file di testo
     * di nome fileName
     */
    public static BinTree buildFromFile(String fileName) {
        try {
            BinTree t = null;
            Scanner input = new Scanner(new File(fileName));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                Scanner lineScan = new Scanner(line);
                int elem = lineScan.nextInt();
                String path = lineScan.hasNext() ? lineScan.next() : "";
                lineScan.close();
                t = BinTreeUtil.add(elem, t, path);
            }

            input.close();
            return t;
        } catch (IOException e) {
            return null;
        }
    }
}
