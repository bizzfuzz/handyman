/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package site;

import javax.script.ScriptException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author ian
 */
public class Youvid extends Site
{

    @Override
    public String title(Document doc)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String url(Document doc) throws ScriptException
    {
        Element container = doc.select("#videoContainer noscript video").first();
        return container.attr("src");
    }

    @Override
    public void download(Document doc)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
