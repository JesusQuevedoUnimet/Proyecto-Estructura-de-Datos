/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Quevedo
 */
package Main;

import javax.swing.JFileChooser;
import java.io.File;

public class SelectorArchivo {
    
    public static String seleccionarArchivo(){
        JFileChooser fc = new JFileChooser();
        
        int res = fc.showOpenDialog(null);
        
        if (res == JFileChooser.APPROVE_OPTION){
            File archivo = fc.getSelectedFile();
            return archivo.getAbsolutePath();
        }
        
        return null;
    } 
}
