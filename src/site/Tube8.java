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
public class Tube8 extends Site
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
            if(script.data().contains("flashvars"))
            {
                player=script.data();
                break;
            }
        for(String line : player.split("\n"))
            if(line.trim().startsWith("flashvars"))
            {
                player=line.trim();
                break;
            }
        List<String> links = new ArrayList<>();
        for(String value : player.split("\""))
            if(value.startsWith("http") && value.contains(".mp4"))
                links.add(value);
        //for(String link:links)
        //    System.out.println(decode(link));
        String[] ret={};
        ret=links.toArray(ret);
        return ret[ret.length-1]; //beat quality listed last
    }

    @Override
    public void download(Document doc)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
