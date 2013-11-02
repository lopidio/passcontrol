/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver;

import br.com.thecave.passcontrolserver.util.PassControlConfigurationSynchronizer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author lopidio
 */
public class PassControlSystemTray
{
    public void run() {
        //Schedule a job for the event-dispatching thread:
        //adding TrayIcon.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private void createAndShowGUI() {
        //Check the SystemTray support
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray não é suportado pela plataforma");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(createImage("/resources/bulb.gif"));
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Servidor do sistema gerenciador de filas está online");

        final SystemTray tray = SystemTray.getSystemTray();
        
        // Create a popup menu components
//        MenuItem aboutItem = new MenuItem("About");
//        CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
//        CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
        Menu displayMenu = new Menu("Exibir");
        MenuItem serverIPmenu = new MenuItem("IP do servidor");
        MenuItem numClientsMenu = new MenuItem("Número de clientes conectados");
        MenuItem sobreMenu = new MenuItem("Sobre");
//        MenuItem noneItem = new MenuItem("None");
        MenuItem exitItem = new MenuItem("Encerrar");
        
        //Add components to popup menu
//        popup.add(aboutItem);
//        popup.add(cb1);
//        popup.add(cb2);
        popup.add(displayMenu);
        popup.addSeparator();
        displayMenu.add(serverIPmenu);
        displayMenu.add(numClientsMenu);
        displayMenu.add(sobreMenu);
//        displayMenu.add(noneItem);
        popup.add(exitItem);
        
        trayIcon.setPopupMenu(popup);
        
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("Impossível adicionar ícone");
            return;
        }
        
        trayIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Servidor do sistema gerenciador de filas está online");
            }
        });
        
//        aboutItem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null,
//                        "This dialog box is run from the About menu item");
//            }
//        });
        
//        cb1.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                int cb1Id = e.getStateChange();
//                if (cb1Id == ItemEvent.SELECTED){
//                    trayIcon.setImageAutoSize(true);
//                } else {
//                    trayIcon.setImageAutoSize(false);
//                }
//            }
//        });
        
//        cb2.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                int cb2Id = e.getStateChange();
//                if (cb2Id == ItemEvent.SELECTED){
//                    trayIcon.setToolTip("Sun TrayIcon");
//                } else {
//                    trayIcon.setToolTip(null);
//                }
//            }
//        });
        
//        ("Exibir");
//        ("IP do servidor");
//        ("Número de clientes conectados");
//        ("Sobre");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuItem item = (MenuItem)e.getSource();
                switch (item.getLabel()) {
                    case "IP do servidor":
                        //type = TrayIcon.MessageType.ERROR;
                        String ip = PassControlConfigurationSynchronizer.getInstance().getConfigurationFile().getIpServer();
                        JOptionPane.showMessageDialog(null,
                                "IP: " + ip, "IP do servidor", JOptionPane.INFORMATION_MESSAGE);                        
//                        trayIcon.displayMessage("Sun TrayIcon Demo",
//                                "This is an error message", TrayIcon.MessageType.ERROR);
                        break;
                    case "Número de clientes conectados":
                        int numClientes = PassControlServer.getInstance().getServer().getNumClients();
                        JOptionPane.showMessageDialog(null,
                                "Número de clientes conectados: " + numClientes, "Clientes conectados", JOptionPane.INFORMATION_MESSAGE);                        
                        //type = TrayIcon.MessageType.WARNING;
//                        trayIcon.displayMessage("Sun TrayIcon Demo",
//                                "This is a warning message", TrayIcon.MessageType.WARNING);
                        break;
                    case "Sobre":
                        //type = TrayIcon.MessageType.INFO;
                        JOptionPane.showMessageDialog(null,
                                "The Cave softwares [thecavesoftwares@gmail.com]", "Contato", JOptionPane.INFORMATION_MESSAGE); 
//                        trayIcon.displayMessage("Sun TrayIcon Demo",
//                                "This is an info message", TrayIcon.MessageType.INFO);
                        break;
                }
            }
        };
        
        serverIPmenu.addActionListener(listener);
        numClientsMenu.addActionListener(listener);
        sobreMenu.addActionListener(listener);
//        noneItem.addActionListener(listener);
        
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
    }
    
    //Obtain the image URL
    protected Image createImage(String path) 
    {
        return new ImageIcon(getClass().getResource(path)).getImage();
    }
}