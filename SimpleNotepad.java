
// Notepad application

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleNotepad extends JFrame implements ActionListener,WindowListener {
    
    JTextArea jText = new JTextArea();
    File fname;

    SimpleNotepad(){

        Font font = new Font("Arial",Font.PLAIN,18);
        Container cont = getContentPane();

        JMenuBar jmb = new JMenuBar();
        JMenu jmFile = new JMenu("File");
        JMenu jmEdit = new JMenu("Edit");

        cont.setLayout(new BorderLayout());
        
        JScrollPane jsp = new JScrollPane(jText);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setVisible(true);

        jText.setFont(font);
        jText.setLineWrap(true);
        jText.setWrapStyleWord(true);

        cont.add(jsp);

        insertMenuItem(jmFile,"New");
        insertMenuItem(jmFile,"Open");
        insertMenuItem(jmFile,"Save");
        
        jmFile.addSeparator();
        insertMenuItem(jmFile,"Exit");
        
        insertMenuItem(jmEdit,"Cut");
        insertMenuItem(jmEdit,"Copy");
        insertMenuItem(jmEdit,"Paste");
        
        jmb.add(jmFile);
        jmb.add(jmEdit);

        setJMenuBar(jmb);
        setIconImage(Toolkit.getDefaultToolkit().getImage("notepad.gif"));
        addWindowListener(this);
        setSize(500,500);
        setTitle("Untitled.txt");

        setVisible(true);

    }

    private void insertMenuItem(JMenu jm,String option){
        JMenuItem jmi = new JMenuItem(option);
        jmi.addActionListener(this);
        jm.add(jmi);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        JFileChooser jf = new JFileChooser();

        if(ae.getActionCommand().equals("New")){
            this.setTitle("Untitled.txt");
            jText.setText("");
            fname=null;
        }
        else if(ae.getActionCommand().equals("Open")){
            int value = jf.showDialog(null,"Open");

            if(value == JFileChooser.APPROVE_OPTION){
                try{
                    File file = jf.getSelectedFile();
                    openFile(file.getAbsolutePath());
                    this.setTitle(file.getName());
                    fname = file;
                }catch(IOException ioe){ 
                    System.out.println(ioe.getMessage());
                }
            }
        }

        else if(ae.getActionCommand().equals("Save")){
            if(fname != null){
                jf.setCurrentDirectory(fname);
                jf.setSelectedFile(fname);
            }
            else{
                jf.setSelectedFile(new File("Untitled.txt"));
            }

            int val = jf.showSaveDialog(null);

            if(val == JFileChooser.APPROVE_OPTION){
                try{
                    File file = jf.getSelectedFile();
                    saveFile(file.getAbsolutePath());
                    this.setTitle(file.getName());
                    fname=file;
                }catch(Exception e){}
            }
        }

        else if(ae.getActionCommand().equals("Exit")){
            System.exit(1);
        }

        else if(ae.getActionCommand().equals("Copy")){
            jText.copy();
        }

        else if(ae.getActionCommand().equals("Paste")){
            jText.paste();
        }
        else if(ae.getActionCommand().equals("Cut")){
            jText.cut();
        }

    }

    private void openFile(String path) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String str;

        jText.setText("");

        setCursor(new Cursor(Cursor.WAIT_CURSOR));

        while((str = br.readLine()) != null){
            jText.setText(jText.getText()+str+"\r\n");
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        br.close();
    }

    private void saveFile(String path) throws IOException{
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(path));
        dos.writeBytes(jText.getText());
        dos.close();
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(1);
    }
    @Override
    public void windowClosed(WindowEvent e) {
    }
    @Override
    public void windowIconified(WindowEvent e) {
    }
    @Override
    public void windowDeiconified(WindowEvent e) {
    }
    @Override
    public void windowActivated(WindowEvent e) {
    }
    @Override
    public void windowDeactivated(WindowEvent e) {
    }    

    public static void main(String[] args) {
    
        new SimpleNotepad();
    }
}