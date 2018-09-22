package br.iesb.meuprograma.apresentacao.table;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.Icon;

import java.awt.Component;

public class TreeCellRenderer extends DefaultTreeCellRenderer {


    private Icon iconUsuarios = new ImageIcon(getClass().getResource("/br/iesb/meuprograma/imagens/exit16.png"));
    private Icon iconConsultar = new ImageIcon(getClass().getResource("/br/iesb/meuprograma/imagens/edit16.png"));
    private Icon iconPrincipal = new ImageIcon(getClass().getResource("/br/iesb/meuprograma/imagens/users16.png"));
    private Icon iconInclude = new ImageIcon(getClass().getResource("/br/iesb/meuprograma/imagens/run16.png"));
    private Icon iconGps = new ImageIcon(getClass().getResource("/br/iesb/meuprograma/imagens/gps16.png"));

    public TreeCellRenderer() {
        super();
    }

    @Override
    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {

        super.getTreeCellRendererComponent(
                tree, value, sel,
                expanded, leaf, row,
                hasFocus);

        String texto = value.toString();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("br/iesb/meuprograma/apresentacao/bundle/Bundle"); // NOI18N
        String menu1 = bundle.getString("PrincipalCad.jTree.raiz.text"); // String = SCO
        String menu2 = bundle.getString("PrincipalCad.jTree.registroObras.text"); // String = Registrar Obras
        String menu3 = bundle.getString("PrincipalCad.jTree.consultarObras.text"); // String = Consultar Obras
        String menu4 = bundle.getString("PrincipalCad.jTree.parametroProducao.text"); // String = Sincronizar
        String menu5 = bundle.getString("PrincipalCad.jTree.gps.text"); // String = Gps
        String menu6 = bundle.getString("PrincipalCad.jTree.logout.text"); // String = Logout


        if (texto.equals(menu1)) {
            setIcon(iconPrincipal);
            setToolTipText("Sistema de Controle de Obras.");
        }

        if (texto.equals(menu3)) {
            setIcon(iconConsultar);
            setToolTipText("Consultar as obras cadastradas no sistema.");
        }
        if (texto.equals(menu4)) {
            setIcon(iconInclude);
            setToolTipText("Efetua a sincronização com o servidor web.");
        }

        if (texto.equals(menu5)) {
            setIcon(iconGps);
            setToolTipText("Baixa as informações de pontos de domicílios do GPS.");
        }

        if (texto.equals(menu6)) {
            setIcon(iconUsuarios);
            setToolTipText("Encerra o sistema.");
        }

        return this;
    }
}
