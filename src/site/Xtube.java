/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package site;

import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        Elements scripts = doc.select("script");
        String player="";
        List<String> urls=new ArrayList();
        
        for(Element script:scripts)
            if(script.data().contains("playerConf"))
                player=script.data();
        for(String line:player.split("\n"))
            if(line.trim().startsWith("var playerConf"))
                player=line;
        for(String seg:player.split("\""))
            if(seg.trim().startsWith("http") && seg.contains(".mp4"))
                urls.add(seg);
        
        return urls.get(0);
        
    }

    @Override
    public void download(Document doc)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
