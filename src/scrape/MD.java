/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.Session;
import util.Video;

/**
 *
 * @author ian
 */
public class MD extends Crawler
{
    private final String domain = "http://www.pornmd.com";
    public List<Video> results;
    Document page;
    Session session;
    String tag;
    
    public void start(String term,Session sess)
    {
        try
        {
            sess.log("Searching: "+term);
            page=search(term);
            this.session=sess;
            tag=term;
            getresults(page);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(MD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //get page 1 of search
    public Document search(String search) throws IOException//implement trap+mix
    {
        String term = search.toLowerCase().trim();
        term=term.replace(" ", "+");
        return getPage(domain+"/straight/"+term+"?o=mr");
    }
    //get vids in page
    public void getresults(Document page)
    {
        Elements found = page.select("div.video-info-container");
        String source;
        results = new ArrayList();
        Element link;
        Video v;
        
        for(Element vid:found)
        {
            v = new Video();
            link=vid.select("a").first();
            v.url = link.attr("href").trim();
            v.title = link.attr("title").trim();
            v.rate = rating(vid);
            v.len = length(vid);
            
            if (v.rate>=session.set.minRating && v.len>=session.set.minLength &&
                v.url.startsWith("/viewvideo")) //valid result
            {
                v.url=domain+v.url;
                //get site vid is from
                source = vid.select("div.video-source").first().text();
                v.source=source.trim().toLowerCase();
                
                for(String site:session.set.sources)
                    if(site.equalsIgnoreCase(v.source))
                    {
                        v.source=site;
                        results.add(v);
                        break;
                    }
            }
        }
        session.log(tag+": "+results.size()+" videos found");
        //System.out.println("Found "+results.size());
    }
    //get video rating from conatiner div
    private int rating(Element video)
    {
        Element rate = video.select("div.rating-value").first();
        if(rate==null)
            return 0;
        else
        {
            String rat = rate.text();
            if(rat.contains("unrated"))
                return 0;
            rat = rat.substring(0, rat.indexOf("%"));
            return Integer.parseInt(rat);
        }
    }
    //get video length in mins
    private int length(Element video)
    {
        Element container = video.select("div.video-lenght").first();
        String len = container.text();
        if(len.contains("h"))
        {
            String hrs = len.substring(0, len.indexOf("h"));
            int hours = Integer.parseInt(hrs);
            String min = len.substring(len.indexOf("h")+1, len.indexOf("m"));
            int mins = Integer.parseInt(min.trim());
            return hours*60+mins;
        }
        len = len.substring(0, len.indexOf("m"));
        return Integer.parseInt(len);
    }
    //get next page in search
    public void nextPage() throws IOException
    {
        session.log(tag+" finding more videos...");
        Elements pages = page.select("#footerPagination li a");
        for(Element link:pages)
            if(link.text().equalsIgnoreCase("next"))
            {
                page=getPage(link.attr("href").trim());
                getresults(page);
                System.out.println("NEXT - "+page.location());
            }
        page=new Document("");
    }
}
