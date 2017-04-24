/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ian
 */
public class Listener implements ActionListener
{
    TagButton parent;
    public Listener(TagButton button)
    {
        parent=button;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //System.out.println(e);
        //System.out.println(parent.getText());
        parent.unlist();
    }
    
}
