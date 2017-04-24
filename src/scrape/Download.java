/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrape;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;
import org.jsoup.nodes.Document;
import site.Default;
import site.ExTube;
import site.Keez;
import site.Mofo;
import site.Redtube;
import site.Site;
import site.Spankwire;
import site.Tube8;
import site.Vidhub;
import site.Xtube;
import site.Youvid;
import util.Session;
import util.Video;

/**
 *
 * @author ian
 */
public class Download extends Crawler
{
    //public Job parent;
    public volatile boolean running;
    private final Session session;
    
    Download(Session sess)
    {
        session=sess;
    }
    
    public void start(Video vid)
    {
        try
        {
            running=true;
            save(vid);
        } 
        catch (IOException | ScriptException ex)
        {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void save(Video vid) throws IOException, ScriptException
    {
        String filename = session.set.savedir+vid.title+".mp4";
        File file = new File(filename);
        if(file.exists())
        {
            session.log(vid.title+" already exists");
            return;
        }
        String video = decode(videoUrl(vid)); //
        //System.out.println(">"+vid.title+" - "+video);
        if(video.isEmpty())
        {
            session.log(vid.title+" FAILED linking");
            return;
        }
        URL url = new URL(video);
        
        try (InputStream in = new BufferedInputStream(url.openStream());
            OutputStream out = new BufferedOutputStream(new FileOutputStream(filename)))
        {
            int len = 4*1024;
            byte[] data = new byte[len];
            int count;
            float total=0;
            int size = filesize(video);
            String dlTitle = "\r"+vid.title+" - ";
            while((count = in.read(data,0,len)) != -1)
            {
                while(!running)
                {
                    try
                    {
                        Thread.sleep(1);
                    } 
                    catch (InterruptedException ex)
                    {
                        Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                out.write(data, 0, count);
                total+=count;
                session.status(dlTitle+ formatSize(total)+"/"+formatSize(size)+" - "+ (int)(total/size*100)+"%");
                //parent.session.window.setStatus(
                //    dlTitle+" "+formatSize(total)+"/"+dlSize+" "+(int)(total/size*100)+"%");
            }
            System.out.println("dl stopped: "+count+" / "+size);
            out.flush();
        }
        session.log(filename+" saved");
    }
    //open link to vid, get url from page
    private String videoUrl(Video vid) throws ScriptException, IOException
    {
        Document doc= getPage(vid.url);
        Site site;
        System.out.println("source -- "+vid.source);
        switch(vid.source)
        {
            case "pornhub":
                site=new Vidhub();
                break;
            case "youporn":
               site=new Youvid() ;
               break;
            case "redtube":
                site=new Redtube();
                break;
            case "tube8":
                site=new Tube8();
                break;
            case "extremetube":
                site=new ExTube();
                break;
            case "spankwire":
                site=new Spankwire();
                break;
            case "keezmovies":
                site=new Keez();
                break;
            case "mofosex":
                site=new Mofo();
                break;
            case "xtube":
                site=new Xtube();
                break;
            default:
                site=new Default();
                break;
        }
        return site.url(doc);
    }
    
    private String vidsource(Document doc)
    {
        String url = doc.location().toLowerCase();
        for(String site:session.set.sources)
            if(url.contains(site))
                return site;
        return "";
    }
}
