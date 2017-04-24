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
public class Spankwire extends Site
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
            if(script.data().contains("playerData.cdnPath"))
            {
                player=script.data();
                break;
            }
        
        List<String> urls = new ArrayList<>();
        List<String> ret = new ArrayList<>();
        for(String line:player.split("\n"))
            if(line.trim().startsWith("playerData.cdnPath"))
                urls.add(line);
        
        urls.forEach((url) ->
        {
            for(String value:url.split("'"))
                if(value.startsWith("http"))
                {
                    ret.add(value);
                    //System.out.println(value);
                }
        });
        
        //System.out.println(ret.size());
        return ret.get(ret.size()-1);
    }

    @Override
    public void download(Document doc)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
