/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javax.swing.JButton;

/**
 *
 * @author ian
 */
public class TagButton extends JButton
{
    private final Window window;
    
    public TagButton(String text, Window w)
    {
        window=w;
        setText(text);
        this.setToolTipText("click to remove");
    }
    
    public void unlist()
    {
        window.removeTag(this);
    }
}
