/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import scrape.Search;
import ui.Window;

/**
 *
 * @author ian
 */
public class Session
{
    private List<Search> searches;//perform search and download
    public List<String> tags;//list of tags/stars searched
    public Prefs set;
    private int saved,current;
    private Window win;
    
    public Session(List<String> terms)
    {
        init();
        tags=new LinkedList<>(terms);
    }
    public Session()
    {
        init();
        tags=new ArrayList();
    }
    private void init()
    {
        searches=new ArrayList();
        set=new Prefs();
        //set.nVids=3;
        saved=0;
        current=0;
    }
    public void open()
    {
        win=new Window(this);
    }
    public void start()//dl videos
    {
        if(tags.isEmpty())
            return;
        saved=0;
        tags.forEach((tag) ->
        {
            searches.add(new Search(tag,this));
        });
        log("downloading "+set.nVids+" video(s)");
        download();
    }
    public int pause()
    {
        if(searches.isEmpty())
            return 1;
        searches.get(current).dl.running=false;
        return 0;
    }
    public void resume()
    {
        searches.get(current).dl.running=true;
    }
    public String tagstring()
    {
        String ret="";
        for(String tag:tags)
            ret=ret.concat(tag+"\n");
        return ret;
    }
    public String prefstring()
    {
        String ret="";
        ret = ret.concat(String.valueOf(set.nVids)+"\n");
        ret = ret.concat(String.valueOf(set.linear)+"\n");
        ret = ret.concat(String.valueOf(set.minLength)+"\n");
        ret = ret.concat(String.valueOf(set.minRating)+"\n");
        ret = ret.concat(String.valueOf(set.savedir)+"\n");
        return ret;
    }
    public void log(String text)
    {
        win.log(text);
    }
    public void status(String text)
    {
        win.setstatus(text);
    }
    //get next video from active searches //add total to status?
    public int next()
    {
        if(saved>set.nVids) //already dl'd wanted n vids
            return 1;
        int ret=searches.get(current).next();//dl next vid
        if(set.linear)//set next search to dl from
        {
            current++;
            if(current>=searches.size())//reached end of tags, back to top
                current=0;
        }
        else// rand tag pick
        {
            Random rand=new Random();
            current=rand.nextInt(searches.size());
        }
        return ret;
    }
    public void download()
    {
        for(int i=0;i<set.nVids;i++)
        {
            int res=next();
            if(res==0)
            {
                saved++;
                log(saved+" video(s) saved");
                System.out.println(saved+" vids\n----------------------------------------");
            }
            else
                i-=1;
        }
        log("done");
    }
}
