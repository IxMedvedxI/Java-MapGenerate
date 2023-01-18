/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import mvc.controller.Controller;
import mvc.model.GenerateMap;


/**
 *
 * @author Netbeans
 */
public class MyFrame extends JFrame{
    MyPanel panel;
    Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public MyFrame(MyPanel panel) {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu action = new JMenu("Действие");
        JMenuItem generate = new JMenuItem("Generate");
        JMenu obr = new JMenu("Обработка");
        JMenuItem sgl = new JMenuItem("Сглаживание");
        action.add(generate);
        menuBar.add(action);
        obr.add(sgl);
        menuBar.add(obr);
        this.panel = panel;
        Random rand = new Random();
        
        generate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.getMap().setSeed(rand.nextInt());
                controller.generate( getWidth(), getHeight());
                panel.paintComponent(panel.getGraphics());
            }
        });
        sgl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.correct();
                controller.getMap().smoothing(getWidth(), getHeight());
                controller.clearList();
                panel.paintComponent(panel.getGraphics());
            }
        });
     
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setVisible(true);
    }
    
}
