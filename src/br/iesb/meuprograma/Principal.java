package br.iesb.meuprograma;

import br.iesb.meuprograma.apresentacao.JFramePrincipal;
import java.awt.EventQueue;
import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Principal {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                JFramePrincipal jFramePrincipal = new JFramePrincipal();
                jFramePrincipal.setVisible(true);
                jFramePrincipal.setExtendedState(Frame.MAXIMIZED_BOTH);
            }
        });
    }
}
