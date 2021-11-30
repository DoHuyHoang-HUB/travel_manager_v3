/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.huyhoang.swing.panel.PanelShadow;
import com.huyhoang.swing.slideshow.EventPagination;
import com.huyhoang.swing.textfield.MyTextField;

import dao.DiaDanh_Dao;
import dao.impl.DiaDanhImpl;
import model.DiaDanh;

/**
 *
 * @author NGUYE
 */
public class PanelDiaDanh extends javax.swing.JPanel {

    private DiaDanh_Dao diaDanhImpl;
    private List<DiaDanh> listDiaDanh;
    public PanelDiaDanh() throws RemoteException, MalformedURLException, NotBoundException {
        initComponents();
        setPropertiesForm();
        diaDanhImpl = (DiaDanh_Dao) Naming.lookup("rmi://localhost:1099/diaDanh_Dao");
        tblDiaDanhHandle();
        loadData(pnlPage.getCurrentIndex());
    }
    
    private void setPropertiesForm() {
        int txtRadius = 10;
        Color colorBtn = new Color(184, 238, 241);
        
        //set chiều cao  và font size của row header
        tblDiaDanh.getTableHeader().setPreferredSize(new Dimension(getWidth(), 40));
        tblDiaDanh.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        
        txtTimKiem.setBorderLine(true);
        txtTimKiem.setBorderRadius(txtRadius);

        cmbChonCot.addItem("Tên địa danh");
        cmbChonCot.addItem("Mã địa danh");
                
    }
    
    private void loadData(int numPage) {
        ((DefaultTableModel) tblDiaDanh.getModel()).setRowCount(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
					listDiaDanh = diaDanhImpl.getDiaDanh(numPage);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                if (listDiaDanh != null) {
                    listDiaDanh.forEach(i -> {
                        tblDiaDanh.addRow(new DiaDanh(i.getMaDiaDanh(), i.getTenDiaDanh(),
                                i.getTinh()).convertToRowTable());
                    });

                    tblDiaDanh.repaint();
                    tblDiaDanh.revalidate();
                }
            }
        }).start();
    }

    private void tblDiaDanhHandle() throws RemoteException {
        pnlPage.addEventPagination(new EventPagination() {
            @Override
            public void onClick(int pageClick) {
                loadData(pageClick);
            }
        });
        int soLuongPhong = diaDanhImpl.getSoLuongDiaDanh();
        pnlPage.init(soLuongPhong % 20 == 0 ? soLuongPhong / 20 : (soLuongPhong / 20) + 1);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new PanelShadow();
        txtTimKiem = new MyTextField();
        cmbChonCot = new javax.swing.JComboBox<>();
        cmbTinhThanhPho = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        pnlCenter = new PanelShadow();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDiaDanh = new gui.table2.MyTable();
        pnlPage = new gui.table2.PanelPage();

        setBackground(new java.awt.Color(255, 255, 255));

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));

        txtTimKiem.setText("Tìm kiếm");
        txtTimKiem.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        cmbChonCot.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmbChonCot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbChonCotActionPerformed(evt);
            }
        });

        cmbTinhThanhPho.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmbTinhThanhPho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn tỉnh" }));
        cmbTinhThanhPho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTinhThanhPhoActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton1.setText("Thêm");

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(cmbChonCot, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmbTinhThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(168, 168, 168))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbChonCot, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTinhThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));

        tblDiaDanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã địa danh", "Tên địa danh", "Tỉnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDiaDanh.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        tblDiaDanh.setRowHeight(40);
        jScrollPane1.setViewportView(tblDiaDanh);

        pnlPage.setOpaque(false);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pnlPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbChonCotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbChonCotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbChonCotActionPerformed

    private void cmbTinhThanhPhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTinhThanhPhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTinhThanhPhoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbChonCot;
    private javax.swing.JComboBox<String> cmbTinhThanhPho;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private PanelShadow pnlCenter;
    private gui.table2.PanelPage pnlPage;
    private PanelShadow pnlTop;
    private gui.table2.MyTable tblDiaDanh;
    private MyTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
