/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrape;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Session;
import util.Video;

/**
 *
 * @author ian
 */
public class Search
{
    public boolean done=false;
    private int current;
    private final MD md;
    private final String tag;
    private final Session session;
    
    public Search(String search, Session sess)
    {
        current = 0;
        md=new MD();
        /*md.results.forEach((vid) ->
        {
            System.out.println(vid.source+" - "+vid.rate+"% - "+vid.len+"m > "+vid.title+"\n"+vid.url+"\n");
        });*/
        current=0;
        session=sess;
        tag=search;
        md.start(tag, session);
    }
    //get n videos from page & subsequent pages
    public void next()
    {
        try
        {
            Download dl=new Download(session);
            Video vid=md.results.get(current);
            System.out.println(" -Starting "+current+" from "+tag+"\n"+vid.title+" ("+vid.source+")\n");
            session.log("#"+(current+1)+" "+vid.title+" ("+tag+")");
            dl.start(md.results.get(current));
            current++;
            if(current>=md.results.size())//got all vids from current page
            {
                md.nextPage();
                current=0;
            }
        } 
        catch (IOException ex)
        {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
