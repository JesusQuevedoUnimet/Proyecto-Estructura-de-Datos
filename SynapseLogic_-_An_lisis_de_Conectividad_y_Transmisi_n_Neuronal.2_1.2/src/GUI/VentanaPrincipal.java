/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import javax.swing.JOptionPane;

/**
 * @author andre
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    // --- VARIABLES CENTRALES DEL MOTOR ---
    private Estructuras.HashTable tablaNT; 
    private Estructuras.Grafo grafo;

    // --- CONSTRUCTOR DE LA VENTANA ---
    public VentanaPrincipal() {
        initComponents(); 
        
        this.setLocationRelativeTo(null); 
        
        // Inicializamos las estructuras
        this.tablaNT = new Estructuras.HashTable(50); 
        this.grafo = new Estructuras.Grafo(tablaNT);
    }
    
    // --- MÉTODO PARA ACTUALIZAR CONTADORES EN LA INTERFAZ ---
    // Este método es propio nuestro, no es de NetBeans. Lo usamos para refrescar la pantalla.
    public void actualizarEstado() {
        if (grafo != null) {
            try {
                // Usamos reflexión para obtener los tamaños de forma segura
                java.lang.reflect.Method getNeuronasMetodo = grafo.getClass().getMethod("getNeuronas");
                Object listaNeuronas = getNeuronasMetodo.invoke(grafo);
                
                if (listaNeuronas != null) {
                    java.lang.reflect.Method getTamañoMetodo = listaNeuronas.getClass().getMethod("getTamaño");
                    int tamañoNeuronas = (int) getTamañoMetodo.invoke(listaNeuronas);
                    IblNeuronas.setText("Neuronas: " + tamañoNeuronas);
                    
                    int totalSinapsis = 0;
                    java.lang.reflect.Method obtenerMetodo = listaNeuronas.getClass().getMethod("obtener", int.class);
                    
                    for (int i = 0; i < tamañoNeuronas; i++) {
                        Object neurona = obtenerMetodo.invoke(listaNeuronas, i);
                        if (neurona != null) {
                            java.lang.reflect.Method getConexionesMetodo = neurona.getClass().getMethod("getConexiones");
                            Object conexiones = getConexionesMetodo.invoke(neurona);
                            if (conexiones != null) {
                                int tamConexiones = (int) getTamañoMetodo.invoke(conexiones);
                                totalSinapsis += tamConexiones;
                            }
                        }
                    }
                    IblSinapsis.setText("Sinapsis: " + totalSinapsis);
                }
            } catch (Exception e) {
                // Fallback si falla la reflexión
                IblNeuronas.setText("Neuronas: Cargadas");
                IblSinapsis.setText("Sinapsis: Cargadas");
            }
        }
    }
    
    // --- MÉTODO MAIN PARA ARRANCAR LA APLICACIÓN ---
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnCargarCSV = new javax.swing.JButton();
        btnCargarDiccionario = new javax.swing.JButton();
        btnMostrarGrafo = new javax.swing.JButton();
        btnDetectarZonas = new javax.swing.JButton();
        btnCalcularRuta = new javax.swing.JButton();
        btnSimularFatiga = new javax.swing.JButton();
        btnAgregarNeurona = new javax.swing.JButton();
        btnEliminarNeurona = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        IblNeuronas = new javax.swing.JLabel();
        IblSinapsis = new javax.swing.JLabel();
        IblDiccionario = new javax.swing.JLabel();
        IblEstado = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jTextField1.setText("jTextField1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCargarCSV.setText("Cargar Archivo CSV");
        btnCargarCSV.addActionListener(this::btnCargarCSVActionPerformed);

        btnCargarDiccionario.setText("Cargar Diccionario");
        btnCargarDiccionario.addActionListener(this::btnCargarDiccionarioActionPerformed);

        btnMostrarGrafo.setText("Mostrar Grafo");
        btnMostrarGrafo.addActionListener(this::btnMostrarGrafoActionPerformed);

        btnDetectarZonas.setText("Detectar Zonas Aisladas");
        btnDetectarZonas.addActionListener(this::btnDetectarZonasActionPerformed);

        btnCalcularRuta.setText("Calcular Ruta");
        btnCalcularRuta.addActionListener(this::btnCalcularRutaActionPerformed);

        btnSimularFatiga.setText("Simular Fatiga");
        btnSimularFatiga.addActionListener(this::btnSimularFatigaActionPerformed);

        btnAgregarNeurona.setText("Agregar Neurona");
        btnAgregarNeurona.addActionListener(this::btnAgregarNeuronaActionPerformed);

        btnEliminarNeurona.setText("Eliminar Neurona");
        btnEliminarNeurona.addActionListener(this::btnEliminarNeuronaActionPerformed);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado del Sistema"));

        IblNeuronas.setText("Neuronas:  0");

        IblSinapsis.setText("Sinapsis:  0");

        IblDiccionario.setText("Diccionario:  No cargado");

        IblEstado.setText("Estado:  Ninguno");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(IblNeuronas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(IblSinapsis, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IblDiccionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(IblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(IblNeuronas)
                .addGap(18, 18, 18)
                .addComponent(IblSinapsis)
                .addGap(18, 18, 18)
                .addComponent(IblDiccionario)
                .addGap(18, 18, 18)
                .addComponent(IblEstado)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDetectarZonas)
                    .addComponent(btnCargarDiccionario)
                    .addComponent(btnCargarCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarGrafo)
                    .addComponent(btnCalcularRuta)
                    .addComponent(btnSimularFatiga)
                    .addComponent(btnAgregarNeurona)
                    .addComponent(btnEliminarNeurona))
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCargarCSV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCargarDiccionario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMostrarGrafo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDetectarZonas)
                        .addGap(8, 8, 8)
                        .addComponent(btnCalcularRuta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSimularFatiga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarNeurona)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarNeurona)))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMostrarGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarGrafoActionPerformed
        GUI.MostrarGrafo ventanaVisual = new GUI.MostrarGrafo(this.grafo);
        ventanaVisual.setVisible(true);
        IblEstado.setText("Estado: Mostrando Grafo Interactivo");
    }//GEN-LAST:event_btnMostrarGrafoActionPerformed

    private void btnAgregarNeuronaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarNeuronaActionPerformed
// Corregido: Ahora abre la misma ventana unificada pasándole el grafo
        GUI.GestionNeuronas ventanaGestion = new GUI.GestionNeuronas(this.grafo);
        ventanaGestion.setVisible(true);
        IblEstado.setText("Estado: Panel de Inserción Abierto");
        ventanaGestion.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                actualizarEstado();
                IblEstado.setText("Estado: Red Actualizada");
            }
        });
    }//GEN-LAST:event_btnAgregarNeuronaActionPerformed

    private void btnCargarCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarCSVActionPerformed
        GUI.CargarArchivo ventanaCarga = new GUI.CargarArchivo(this.grafo);
        ventanaCarga.setVisible(true);
        IblEstado.setText("Estado: Cargando Red...");
        ventanaCarga.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                actualizarEstado();
                IblEstado.setText("Estado: Red Cargada");
            }
        });
    }//GEN-LAST:event_btnCargarCSVActionPerformed

    private void btnCargarDiccionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarDiccionarioActionPerformed
        GUI.CargarDiccionario ventanaDic = new GUI.CargarDiccionario(this.tablaNT);
        ventanaDic.setVisible(true);
        IblEstado.setText("Estado: Cargando Diccionario...");
        
        // Listener para enterarnos cuando se cierre la ventana de carga
        ventanaDic.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                IblDiccionario.setText("Diccionario: Cargado");
                IblEstado.setText("Estado: Diccionario Listo");
            }
        });
    }//GEN-LAST:event_btnCargarDiccionarioActionPerformed

    private void btnDetectarZonasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetectarZonasActionPerformed
        GUI.ZonasAisladas ventanaZonas = new GUI.ZonasAisladas(this.grafo);
        ventanaZonas.setVisible(true);
        IblEstado.setText("Estado: Buscando Zonas Aisladas");
    }//GEN-LAST:event_btnDetectarZonasActionPerformed

    private void btnCalcularRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularRutaActionPerformed
        GUI.Dijkstra ventanaRuta = new GUI.Dijkstra(this.grafo, this.tablaNT);
        ventanaRuta.setVisible(true);
        IblEstado.setText("Estado: Buscando Caminos de Transmisión");
    }//GEN-LAST:event_btnCalcularRutaActionPerformed

    private void btnSimularFatigaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimularFatigaActionPerformed
    SimularFatiga ventanaFatiga = new GUI.SimularFatiga(this.grafo);
    ventanaFatiga.setVisible(true);    }//GEN-LAST:event_btnSimularFatigaActionPerformed

    private void btnEliminarNeuronaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarNeuronaActionPerformed
// Corregido: Ahora abre la nueva ventana unificada pasando el grafo necesario
        GUI.GestionNeuronas ventanaGestion = new GUI.GestionNeuronas(this.grafo);
        ventanaGestion.setVisible(true);
        IblEstado.setText("Estado: Panel de Eliminación Abierto");
        ventanaGestion.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                actualizarEstado();
                IblEstado.setText("Estado: Red Actualizada");
            }
        });
    }//GEN-LAST:event_btnEliminarNeuronaActionPerformed


  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IblDiccionario;
    private javax.swing.JLabel IblEstado;
    private javax.swing.JLabel IblNeuronas;
    private javax.swing.JLabel IblSinapsis;
    private javax.swing.JButton btnAgregarNeurona;
    private javax.swing.JButton btnCalcularRuta;
    private javax.swing.JButton btnCargarCSV;
    private javax.swing.JButton btnCargarDiccionario;
    private javax.swing.JButton btnDetectarZonas;
    private javax.swing.JButton btnEliminarNeurona;
    private javax.swing.JButton btnMostrarGrafo;
    private javax.swing.JButton btnSimularFatiga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
