/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package site;

import javax.script.ScriptException;
import org.jsoup.nodes.Document;

/**
 *
 * @author ian
 */
public abstract class Site
{
    abstract public String title(Document doc);
    abstract public String url(Document doc) throws ScriptException;
    abstract public void download(Document doc);
}
