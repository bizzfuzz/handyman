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
public class Xtube extends Site
{

    @Override
    public String title(Document doc)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String url(Document doc) throws ScriptException
    {
        Element player = doc.select("section.cntPanel script").first();
        String url="";
        
        for(String line:player.data().split(","))
            if(line.trim().startsWith("\"video_url\""))
            {
                url=line.trim();
                break;
            }
        for(String value:url.split("\""))
            if(value.startsWith("http"))
            {
                url=value;
                break;
            }
        return url;
    }

    @Override
    public void download(Document doc)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
