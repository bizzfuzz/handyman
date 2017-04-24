/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author ian
 */
public class Dispatch implements Runnable
{
    public final Session sess;
    public Dispatch(Session s)
    {
        sess=s;
    }
    
    @Override
    public void run()
    {
        sess.start();
    }
}
