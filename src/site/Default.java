/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package site;

import javax.script.ScriptException;
import org.jsoup.nodes.Document;

/**
 *
 * @author ian
 */
public class Default extends Site
{

    @Override
    public String title(Document doc)
    {
        return "";
    }

    @Override
    public String url(Document doc) throws ScriptException
    {
        return "";
    }

    @Override
    public void download(Document doc)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
