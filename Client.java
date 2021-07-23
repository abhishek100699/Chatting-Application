/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
 * @author ABHISHEK SINGH
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
 // Package used : chatting.application;
 
 
 
 
package chatting.application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author ABHISHEK SINGH
 */
public class Client extends JFrame implements ActionListener {
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    Boolean typing ;
    Client(){
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,350,60);
        add(p1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3) ;
        l1.setBounds(5,17,30,30);
        p1.add(l1);
        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/2.png"));
        Image i5 = i4.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6) ;
        l2.setBounds(40,1,60,60);
        p1.add(l2);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l5 = new JLabel(i9) ;
        //15.setBounds(40,1,60,60);
        l5.setBounds(190,1,60,45);
        p1.add(l5);
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel l6 = new JLabel(i12) ;
        l6.setBounds(250,1,60,45);
        p1.add(l6);
        
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(11,22,Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel l7 = new JLabel(i15) ;
        l7.setBounds(310,1,25,40);
        p1.add(l7);
        
        JLabel l3 = new JLabel("Gulshan");
        l3.setFont(new Font("SANS_SERIF",Font.BOLD,16));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110,10,100,20);
        p1.add(l3);
        
        JLabel l4 = new JLabel("Active Now");
        l4.setFont(new Font("SANS_SERIF",Font.PLAIN,10));
        l4.setForeground(Color.WHITE);
        l4.setBounds(110,38,100,10);
        p1.add(l4);
        Timer t = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(!typing){
                    l4.setText("Active Now");
                   
                }
            }
        });
        t.setInitialDelay(2000);
        
        a1 = new JTextArea();
        a1.setBounds(5,65,339,442);
        a1.setFont(new Font("SANS_SERIF",Font.PLAIN,14));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        add(a1);
        
        t1 = new JTextField();
        t1.setBounds(10,510,260,30);
        t1.setFont(new Font("SANS_SERIF",Font.PLAIN,15));
        add(t1);
        t1.addKeyListener(new KeyAdapter() {
            public void KeyPressed(KeyEvent ke){
                l4.setText("typing...");
                
                t.stop();
                typing = true;
            }
            
            public void keyReleased(KeyEvent ke){
                typing = false;
                if(!t.isRunning()){
                    t.start();
                }
            }
        });
        
        b1 = new JButton("Send");
        b1.setBounds(275,510,65,28);
        b1.setBackground(new Color(7,94,84));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);
        
       // getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setSize(350,550); 
        setLocation(1100,200);
        setUndecorated(true);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        try{
        String out = t1.getText();
        a1.setText(a1.getText()+"\n\t\t\t"+out);
        dout.writeUTF(out);
        t1.setText("");
        }
        catch(Exception e){
            
        }
    }
    public static void main(String[] args){
        new Client().setVisible(true);
        
        try{
            s = new Socket("127.0.0.1",6001);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            String msginput = "" ;
            msginput= din.readUTF();
             a1.setText(a1.getText()+"\n"+msginput);
            
        }
        catch(Exception e){
            
        }
    }


}





