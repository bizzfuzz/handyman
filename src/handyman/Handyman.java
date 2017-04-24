/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handyman;

import java.util.Arrays;
import javax.swing.UIManager;
import ui.Window;
import util.Session;

/**
 *
 * @author ian
 */
public class Handyman
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        setUI();
        String[] tags={"skin diamond","dani daniels"};
        Session sess=new Session();//Arrays.asList(tags));
        sess.open();
    }
    
    static private void setUI()
    {
        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("GTK+".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    return;
                }
            }

            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Windows".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    return;
                }
            }
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
