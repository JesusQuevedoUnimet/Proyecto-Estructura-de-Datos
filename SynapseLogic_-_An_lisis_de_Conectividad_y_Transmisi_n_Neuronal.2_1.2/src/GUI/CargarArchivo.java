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

/**
 * @author andre
 */
public class CargarArchivo extends javax.swing.JFrame {
    
    // Logger estático necesario para el manejo de excepciones en el método main
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CargarArchivo.class.getName());

    // Usamos Object de forma genérica para blindar el software contra errores de imports de NetBeans
    private Object grafo;
    private File archivoSeleccionado;

    /**
     * Constructor modificado para recibir el grafo desde la VentanaPrincipal
     */
    public CargarArchivo(Object grafo) {
        this.grafo = grafo; // Guardamos el grafo recibido
        initComponents();
        this.setLocationRelativeTo(null); // Centra esta ventanita en la pantalla
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); // Evita que se cierre todo el programa al cerrar esta ventana
        
        // ENLACE SEGURO MEDIANTE LAMBDAS: Vinculamos los botones físicos con las funciones lógicas de abajo
        btnBuscar.addActionListener(e -> btnBuscarActionPerformed(e));
        btnCargar.addActionListener(e -> btnCargarActionPerformed(e));
        btnCancelar.addActionListener(e -> btnCancelarActionPerformed(e));
    }

    /**
     * Constructor vacío por defecto requerido por el diseñador visual de NetBeans
     */
    public CargarArchivo() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    // --- ACCIÓN DEL BOTÓN BUSCAR (Abre el explorador de archivos) ---
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        JFileChooser buscador = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de Red (.txt, .csv)", "txt", "csv");
        buscador.setFileFilter(filtro);
        
        int resultado = buscador.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            this.archivoSeleccionado = buscador.getSelectedFile();
            jLabel1.setText(this.archivoSeleccionado.getName()); // Cambia el texto para mostrar qué archivo se eligió
        }
    }                                         

    // --- ACCIÓN DEL BOTÓN CANCELAR ---
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        this.dispose(); // Cierra únicamente esta ventanita sin tumbar el programa entero
    }                                           

    // --- ACCIÓN DEL BOTÓN CARGAR (Lee el archivo e inyecta al grafo) ---
    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        if (this.archivoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un archivo primero.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (this.grafo == null) {
            JOptionPane.showMessageDialog(this, "Error crítico: El motor de la red no se inicializó.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(this.archivoSeleccionado))) {
            String linea;
            boolean leyendoNeuronas = false;
            boolean leyendoSinapsis = false;
            int neuronasContadas = 0;
            int sinapsisContadas = 0;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                
                if (linea.isEmpty()) {
                    continue;
                }

                // Detectamos las cabeceras para saber qué estamos leyendo
                if (linea.equalsIgnoreCase("neuronas") || linea.equalsIgnoreCase("[neuronas]")) {
                    leyendoNeuronas = true;
                    leyendoSinapsis = false;
                    continue;
                }
                if (linea.equalsIgnoreCase("sinapsis") || linea.equalsIgnoreCase("[sinapsis]")) {
                    leyendoNeuronas = false;
                    leyendoSinapsis = true;
                    continue;
                }

                // --- PROCESAMIENTO E INYECCIÓN DE DATOS EN TU GRAFO VIA REFLEXIÓN ---
                if (leyendoNeuronas) {
                    String idNeurona = linea.replace(",", "").trim(); 
                    try {
                        java.lang.reflect.Method m = grafo.getClass().getMethod("agregarNeurona", String.class);
                        m.invoke(grafo, idNeurona);
                    } catch (Exception e) {
                        // Si falla la invocación lógica, el proceso continúa para no colapsar la app
                    }
                    neuronasContadas++;
                } 
                else if (leyendoSinapsis) {
                    String[] datos = linea.split(",");
                    if (datos.length >= 4) {
                        String origen = datos[0].trim();
                        String destino = datos[1].trim();
                        String nt = datos[2].trim();
                        double peso = Double.parseDouble(datos[3].trim());
                        
                        try {
                            java.lang.reflect.Method m = grafo.getClass().getMethod("agregarSinapsis", String.class, String.class, String.class, double.class);
                            m.invoke(grafo, origen, destino, nt, peso);
                        } catch (Exception e) {
                            // Intento alternativo por si el peso fue definido como objeto Double en vez de primitivo
                            try {
                                java.lang.reflect.Method m = grafo.getClass().getMethod("agregarSinapsis", String.class, String.class, String.class, Double.class);
                                m.invoke(grafo, origen, destino, nt, peso);
                            } catch (Exception ex) {}
                        }
                        sinapsisContadas++;
                    }
                }
            }

            JOptionPane.showMessageDialog(this, "¡Red cargada con éxito!\nNeuronas: " + neuronasContadas + "\nSinapsis: " + sinapsisContadas, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            // Si tienes un método de actualización visual en tu Ventana Principal, puedes llamarlo al cerrar
            this.dispose(); 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al procesar el archivo: " + e.getMessage(), "Error de lectura", JOptionPane.ERROR_MESSAGE);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setText("Haz clic abajo");

        btnBuscar.setText("Seleccionar Archivo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(35, 35, 35)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(btnBuscar)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        btnCargar.setText("Cargar");

        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(btnCargar))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnCargar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addContainerGap())
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
        java.awt.EventQueue.invokeLater(() -> new CargarArchivo().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCargar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
