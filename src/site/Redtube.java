/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package site;

import javax.script.ScriptException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ian
 */
public class Redtube extends Site
{

    @Override
    public String title(Document doc)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String url(Document doc) throws ScriptException
    {
        Elements scripts = doc.select("script");
        String player="";
        for(Element script:scripts)
            if(script.data().contains("rtInitPage"))
            {
                player=script.data();
                break;
            }
        for(String line:player.split("\n"))
            if(line.contains("bootstrap.push"))
            {
                player=line.trim();
                break;
            }
        for(String value:player.split("\""))
            if(value.startsWith("http"))
            {
                player=value;
                break;
            }
        return player;
    }

    @Override
    public void download(Document doc)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
