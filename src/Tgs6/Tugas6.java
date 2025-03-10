/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tgs6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PushbackReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Eka Fara
 */
public class Tugas6 {
    
   private final tgs6Form view;

   
   public Tugas6 (tgs6Form view) {
        this.view = view;

        this.view.getBtBaca().addActionListener((ActionEvent e) -> {
            try {
                proses();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Tugas6.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadLocationException | IOException ex) {
                Logger.getLogger(Tugas6.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.view.getBtSimpan().addActionListener((ActionEvent e) -> {
            save();
        });
    }
     
     private void proses()throws FileNotFoundException, BadLocationException, IOException{
    JFileChooser loadFile = view.getLoadFile();
             StyledDocument doc = view.getTxtPane().getStyledDocument();
             if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
                 
                 PushbackReader  reader = new PushbackReader(new FileReader(loadFile.getSelectedFile()));
                 LineNumberReader re = new LineNumberReader(new FileReader(loadFile.getSelectedFile()));
                 
                  char[] words = new char[(int) loadFile.getSelectedFile().length()];
                 
                 try {
                     reader.read(words);//pembacaan dilakukan per char
                    
                    String data = null;
                    String data1 = null;
                    doc.insertString(0, "", null);
                    
                    int hitungKarakter = 0;//variabel hitungKarakter
                    int hitungKata = 0;//variabel hitungKata
                    
                    if((data = new String(words)) != null) {
                        String[] wordlist = data.split("\\s+");
                      
                        hitungKarakter += words.length;
                        hitungKata += wordlist.length;
                       
                        doc.insertString(doc.getLength(), "" + data + "\n", null);
                     
                    }
                    while((data1 = re.readLine()) != null){
                       
                    }
                     int hitungBaris = re.getLineNumber();
                     
                     JOptionPane.showMessageDialog(null, "File Berhasil Dibaca" + "\n"
                        + "Jumlah Baris = " + hitungBaris + "\n"
                        + "Jumlah Kata = " + hitungKata + "\n"
                        + "Jumlah Karakter = " + hitungKarakter);
                     
                 } catch (IOException | BadLocationException ex) {
                     Logger.getLogger(Tugas6.class.getName()).log(Level.SEVERE, null, ex);
                 } 
         }
    }

     
     
    private void save() {
         JFileChooser loadFile = view.getLoadFile();
         if (JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)) {
             BufferedWriter writer = null;
             try {
                 String contents = view.getTxtPane().getText();
                 if(contents !=null && !contents.isEmpty()){
                     writer = new BufferedWriter(new FileWriter(loadFile.getSelectedFile()));
                     writer.write(contents);
                 }
             } catch (FileNotFoundException ex) {
                Logger.getLogger(Tugas6.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(Tugas6.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             } finally {
                 if (writer != null) {
                     try {
                         writer.flush();
                         writer.close();
                         view.getTxtPane().setText("");
                     } catch (IOException ex) {
                         java.util.logging.Logger.getLogger(Tugas6.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                     }
                 }
             }
         }
     }
    
}
