/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import util.Dispatch;
import util.Filer;
import util.Prefs;
import util.Session;

/**
 *
 * @author ian
 */
public class Window extends javax.swing.JFrame
{
    Session session;
    
    public Window()
    {
        init();
    }
    
    public Window(Prefs set)
    {
        init();
        loadprefs(set);
    }
    public Window(Prefs set, List<String> taglist)
    {
        init();
        loadprefs(set);
        showtags(taglist);
    }
    public Window(Session sess)
    {
        init();
        loadprefs(sess.set);
        showtags(sess.tags);
        session=sess;
    }
    
    public final void showtags(List<String> taglist)
    {
        tags.removeAll();
        tags.setLayout(new BoxLayout(tags, BoxLayout.PAGE_AXIS));
        TagButton button;
        for(String tag:taglist)
        {
            button=new TagButton(tag, this);
            button.addActionListener(new Listener(button));
            tags.add(button);
        }
        refresh();
    }
    public void removeTag(TagButton button)
    {
        session.tags.remove(button.getText());
        refreshtags();
    }
    private void refresh()
    {
        revalidate();
        repaint();
    }
    private void refreshtags()
    {
        tags.removeAll();
        showtags(session.tags);
    }
    private void loadprefs(Prefs set)
    {
        lenfield.setText(String.valueOf(set.minLength));
        linbox.setSelected(set.linear);
        ratefield.setText(String.valueOf(set.minRating));
        videofield.setText(String.valueOf(set.nVids));
    }
    public void log(String text)
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat form=new SimpleDateFormat("HH:mm");
        String time=form.format(cal.getTime());
        logtext.append(time.concat(" |"+text+"\n"));
    }
    private void getprefs()
    {
        int len=Integer.valueOf(lenfield.getText());
        boolean linear=linbox.isSelected();
        int rate= Integer.valueOf(ratefield.getText());
        int nvid=Integer.valueOf(videofield.getText());
        session.set.linear=linear;
        session.set.minLength=len;
        session.set.minRating=rate;
        session.set.nVids=nvid;
    }
    public void setstatus(String text)
    {
        statustext.setText(text);
    }
    
    private void init()
    {
        initComponents();
        logtext.setText("");
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_VERT);
        this.setTitle("handyman");
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        menu = new javax.swing.JToolBar();
        start = new javax.swing.JButton();
        save = new javax.swing.JButton();
        load = new javax.swing.JButton();
        pause = new javax.swing.JButton();
        status = new javax.swing.JToolBar();
        statustext = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        home = new javax.swing.JPanel();
        tags = new javax.swing.JPanel();
        add = new javax.swing.JPanel();
        tagfield = new javax.swing.JTextField();
        prefs = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        videofield = new javax.swing.JTextField();
        ratefield = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lenfield = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        linbox = new javax.swing.JCheckBox();
        log = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logtext = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menu.setRollover(true);

        start.setText("start");
        start.setFocusable(false);
        start.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        start.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        start.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                startActionPerformed(evt);
            }
        });
        menu.add(start);

        save.setText("save");
        save.setFocusable(false);
        save.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        save.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        save.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveActionPerformed(evt);
            }
        });
        menu.add(save);

        load.setText("load");
        load.setFocusable(false);
        load.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        load.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        load.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                loadActionPerformed(evt);
            }
        });
        menu.add(load);

        pause.setText("pause");
        pause.setFocusable(false);
        pause.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pause.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menu.add(pause);

        status.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        status.setRollover(true);

        statustext.setText("-");
        status.add(statustext);

        tags.setBorder(javax.swing.BorderFactory.createTitledBorder("tags"));

        javax.swing.GroupLayout tagsLayout = new javax.swing.GroupLayout(tags);
        tags.setLayout(tagsLayout);
        tagsLayout.setHorizontalGroup(
            tagsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        tagsLayout.setVerticalGroup(
            tagsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        add.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "add"));

        tagfield.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                tagfieldKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout addLayout = new javax.swing.GroupLayout(add);
        add.setLayout(addLayout);
        addLayout.setHorizontalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tagfield, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        addLayout.setVerticalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tagfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        prefs.setBorder(javax.swing.BorderFactory.createTitledBorder("prefs"));

        jLabel1.setText("videos");

        videofield.setMinimumSize(new java.awt.Dimension(50, 27));

        ratefield.setMinimumSize(new java.awt.Dimension(50, 27));

        jLabel2.setText("min. rating");

        jLabel3.setText("min. length");

        lenfield.setMinimumSize(new java.awt.Dimension(50, 27));

        jLabel4.setText("linear dl");

        javax.swing.GroupLayout prefsLayout = new javax.swing.GroupLayout(prefs);
        prefs.setLayout(prefsLayout);
        prefsLayout.setHorizontalGroup(
            prefsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prefsLayout.createSequentialGroup()
                .addGroup(prefsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(prefsLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(videofield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(prefsLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ratefield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(prefsLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lenfield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(prefsLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linbox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        prefsLayout.setVerticalGroup(
            prefsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prefsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(prefsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(videofield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(prefsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ratefield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(prefsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lenfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(prefsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(linbox))
                .addContainerGap(298, Short.MAX_VALUE))
        );

        log.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "log"));

        logtext.setEditable(false);
        logtext.setColumns(20);
        logtext.setRows(5);
        jScrollPane1.setViewportView(logtext);

        javax.swing.GroupLayout logLayout = new javax.swing.GroupLayout(log);
        log.setLayout(logLayout);
        logLayout.setHorizontalGroup(
            logLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        logLayout.setVerticalGroup(
            logLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addComponent(tags, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prefs, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(log, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(prefs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(tags, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(log, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabs.addTab("home", home);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tagfieldKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_tagfieldKeyPressed
    {//GEN-HEADEREND:event_tagfieldKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            session.tags.add(tagfield.getText());
            refreshtags();
            tagfield.setText("");
        }
    }//GEN-LAST:event_tagfieldKeyPressed

    private void startActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_startActionPerformed
    {//GEN-HEADEREND:event_startActionPerformed
        getprefs();
        Thread thread = new Thread(new Dispatch(session));
        thread.start();
    }//GEN-LAST:event_startActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveActionPerformed
    {//GEN-HEADEREND:event_saveActionPerformed
        JFileChooser chooser=new JFileChooser(session.set.savedir);
        int chosen=chooser.showSaveDialog(this);
        if(chosen==JFileChooser.APPROVE_OPTION)
        {
            try
            {
                Filer file = new Filer(); 
                String path=chooser.getSelectedFile().getPath();
                getprefs();
                String content = session.tagstring()+"%\n"+session.prefstring();
                file.truncate(content, path);
                
                log("session saved to "+path);
            } 
            catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_saveActionPerformed

    private void loadActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_loadActionPerformed
    {//GEN-HEADEREND:event_loadActionPerformed
        try
        {
            JFileChooser chooser=new JFileChooser(session.set.savedir);
            int chosen=chooser.showOpenDialog(this);
            
            if(chosen==JFileChooser.APPROVE_OPTION)
            {
                Filer file = new Filer();
                String path=chooser.getSelectedFile().getPath();
                String text = file.content(path);
                String[] saved=text.split("%");//split tags and prefs
                if(saved.length<2)
                {
                    log("[LEN] Error opening "+chooser.getSelectedFile().getName());
                    return;
                }

                text = saved[0];//load tags
                String[] list = text.split("\n");
                session.tags.clear();
                session.tags.addAll(Arrays.asList(list));

                text = saved[1].trim();//load prefs
                list = text.split("\n");
                session.set=new Prefs();
                for(String pref:list)
                    if(!(pref.contains("/") || pref.contains("\\") || pref.contains("true") || pref.contains("false"))
                            && !isnumber(pref,10))//not a path or number(invalid)
                    {
                        log("[INV] Error opening "+chooser.getSelectedFile().getName());
                        return;
                    }
                session.set.nVids=Integer.parseInt(list[0]);
                session.set.linear=Boolean.valueOf(list[1]);
                session.set.minLength=Integer.parseInt(list[2]);
                session.set.minRating=Integer.parseInt(list[3]);
                session.set.savedir=list[4];

                loadprefs(session.set);
                showtags(session.tags);
                log(chooser.getSelectedFile().getName()+" opened");
            }
        } 
        catch (IOException ex)
        {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_loadActionPerformed
    private boolean isnumber(String s,int rad)
    {
        Scanner sc=new Scanner(s.trim());
        if(!sc.hasNextInt(rad))
            return false;
        sc.nextInt(rad);
        return !sc.hasNext();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() ->
        {
            new Window().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel add;
    private javax.swing.JPanel home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lenfield;
    private javax.swing.JCheckBox linbox;
    private javax.swing.JButton load;
    private javax.swing.JPanel log;
    private javax.swing.JTextArea logtext;
    private javax.swing.JToolBar menu;
    private javax.swing.JButton pause;
    private javax.swing.JPanel prefs;
    private javax.swing.JTextField ratefield;
    private javax.swing.JButton save;
    private javax.swing.JButton start;
    private javax.swing.JToolBar status;
    private javax.swing.JLabel statustext;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTextField tagfield;
    private javax.swing.JPanel tags;
    private javax.swing.JTextField videofield;
    // End of variables declaration//GEN-END:variables
}
