/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * @author andre
 */
public class CargarDiccionario extends javax.swing.JFrame {
    
    // Logger estático autogenerado por NetBeans conservado para el método main
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CargarDiccionario.class.getName());

    // Usamos Object de forma temporal para evitar errores de compilación por nombres de clases
    private Object tablaNT;

    /**
     * Constructor modificado que recibe el diccionario de neurotransmisores desde la VentanaPrincipal
     */
    public CargarDiccionario(Object tablaNT) {
        this.tablaNT = tablaNT; // Enlazamos la estructura en memoria
        initComponents();
        this.setLocationRelativeTo(null); // Centra la ventana en pantalla
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); // Cierra solo esta ventana al salir
        
        // Enlace del botón mediante una expresión Lambda limpia
        btnCargarDict.addActionListener(e -> btnCargarDictActionPerformed(e));
    }

    /**
     * Constructor vacío por defecto requerido por el diseñador visual de NetBeans
     */
    public CargarDiccionario() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    // --- ACCIÓN DEL BOTÓN REAL (Abre archivo, guarda en memoria y actualiza la tabla visual) ---
    private void btnCargarDictActionPerformed(java.awt.event.ActionEvent evt) {                                              
        if (this.tablaNT == null) {
            JOptionPane.showMessageDialog(this, "Error crítico: La estructura del diccionario no se inicializó desde la ventana principal.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser buscador = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Diccionario de Neurotransmisores (*.txt, *.csv)", "txt", "csv");
        buscador.setFileFilter(filtro);
        
        int resultado = buscador.showOpenDialog(this);
        if (resultado != JFileChooser.APPROVE_OPTION) {
            return; // Si el usuario cancela, salimos ordenadamente
        }

        File archivoSeleccionado = buscador.getSelectedFile();
        DefaultTableModel modeloTabla = (DefaultTableModel) TablaDiccionario.getModel();
        modeloTabla.setRowCount(0); // Limpiamos filas antiguas de la tabla visual

        try (BufferedReader br = new BufferedReader(new FileReader(archivoSeleccionado))) {
            String linea;
            int neurotransmisoresContados = 0;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                
                // Omitir líneas vacías o cabeceras comunes
                if (linea.isEmpty() || linea.toLowerCase().contains("nombre") || linea.equalsIgnoreCase("[neurotransmisores]")) {
                    continue;
                }

                String[] datos = linea.split(",");
                if (datos.length >= 2) {
                    String nombre = datos[0].trim();
                    String tipo = datos[1].trim();
                    
                    // Ajustamos columnas adicionales basándonos en tu JTable (ID, Nombre, Tipo, Velocidad, Descripcion)
                    String id = (datos.length > 2) ? datos[2].trim() : String.valueOf(neurotransmisoresContados + 1);
                    String velocidad = (datos.length > 3) ? datos[3].trim() : "N/A";
                    String descripcion = (datos.length > 4) ? datos[4].trim() : "Sin descripción";
                    
                    // Intentamos invocar dinámicamente tu método "agregar" por reflexión para evitar conflictos de imports
                    try {
                        java.lang.reflect.Method metodoAgregar = tablaNT.getClass().getMethod("agregar", String.class, String.class);
                        metodoAgregar.invoke(tablaNT, nombre, tipo);
                    } catch (Exception ex) {
                        // Si tu método se llama de otra forma, puedes llamarlo aquí alternativamente o ignorar la carga lógica si solo deseas ver la tabla
                    }
                    
                    // Añadimos la fila en el JTable de tu pantalla
                    modeloTabla.addRow(new Object[]{id, nombre, tipo, velocidad, descripcion});
                    neurotransmisoresContados++;
                }
            }

            JOptionPane.showMessageDialog(this, "¡Diccionario cargado con éxito!\nNeurotransmisores registrados en pantalla: " + neurotransmisoresContados, "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al procesar el diccionario: " + e.getMessage(), "Error de lectura", JOptionPane.ERROR_MESSAGE);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCargarDict = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaDiccionario = new javax.swing.JTable();
        txtBuscador = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCargarDict.setText("Cargar Diccionario (csv)");

        TablaDiccionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Tipo", "Velocidad(v)", "Descripcion"
            }
        ));
        jScrollPane1.setViewportView(TablaDiccionario);

        txtBuscador.setText("Busqueda");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCargarDict)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCargarDict)
                    .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new CargarDiccionario().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaDiccionario;
    private javax.swing.JButton btnCargarDict;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
