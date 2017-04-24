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
public class Vidhub extends Site
{

    @Override
    public String title(Document doc)
    {
        Element div = doc.select("#main-container").first();
        return div.attr("data-video-title");
    }

    @Override
    public String url(Document doc) throws ScriptException
    {
        Elements scripts = doc.select("script");
        String player="";
        
        for(Element script:scripts)
            if(script.data().contains("var player_quality"))
            {
                player = script.data();
                break;
            }
        List<String> links = new ArrayList<>();
        
        for(String line : player.split("\\n?\\r"))//break script into lines
            if(line.contains("player_quality_"))
            {
                player=line;
                break;
            }
        
        for(String value : player.split("'"))
            if(value.startsWith("http"))
                links.add(value);
        return links.get(0);
    }

    @Override
    public void download(Document doc)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
