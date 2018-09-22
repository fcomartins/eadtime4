/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.iesb.meuprograma.apresentacao.bundle;

import java.util.ResourceBundle;

/**
 *
 * @author francisco
 */
public abstract class IesbBundle {
    
    private static ResourceBundle bundle = ResourceBundle.getBundle("br/iesb/meuprograma/apresentacao/bundle/Bundle");

    public static String getBundle(String key) {
        return bundle.getString(key);
    }
}
