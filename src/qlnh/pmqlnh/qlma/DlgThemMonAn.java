package qlnh.pmqlnh.qlma;

import qlnh.pmqlnh.qlnv.*;
import java.awt.Frame;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import qlnh.DBConnect;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author ADMIN
 */
public class DlgThemMonAn extends javax.swing.JDialog {
    private boolean cheDoSua = false;
    private int maMon = -1;
    private File selectedFile;

    private void updateMonAn() {

   try
    {
        Connection con =
                DBConnect.getConnection();

        String loaiMon;

        if(cboLoaiMon.getSelectedItem()
                .toString()
                .equals("Đồ Ăn"))
        {
            loaiMon = "DOAN";
        }
        else
        {
            loaiMon = "DOUONG";
        }

        if(selectedFile != null)
        {
            String sql =
                    "UPDATE THUCDON "
                  + "SET TENMON=?, "
                  + "DONGIA=?, "
                  + "LOAIMON=?, "
                  + "HINHANH=? "
                  + "WHERE MAMON=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    txtTenMon.getText().trim()
            );

            ps.setDouble(
                    2,
                    Double.parseDouble(
                            txtDonGia.getText()
                                     .trim()
                    )
            );

            ps.setString(
                    3,
                    loaiMon
            );

            FileInputStream fis =
                    new FileInputStream(
                            selectedFile
                    );

            ps.setBinaryStream(
                    4,
                    fis,
                    (int) selectedFile.length()
            );

            ps.setInt(
                    5,
                    maMon
            );

            ps.executeUpdate();
        }
        else
        {
            String sql =
                    "UPDATE THUCDON "
                  + "SET TENMON=?, "
                  + "DONGIA=?, "
                  + "LOAIMON=? "
                  + "WHERE MAMON=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    txtTenMon.getText().trim()
            );

            ps.setDouble(
                    2,
                    Double.parseDouble(
                            txtDonGia.getText()
                                     .trim()
                    )
            );

            ps.setString(
                    3,
                    loaiMon
            );

            ps.setInt(
                    4,
                    maMon
            );

            ps.executeUpdate();
        }

        JOptionPane.showMessageDialog(
                this,
                "Cập nhật thành công!"
        );

        dispose();
    }
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(
                this,
                e.getMessage()
        );
    }
}
    private void insertMonAn() {

       try
    {
        Connection con =
                DBConnect.getConnection();

        String sql =
                "INSERT INTO THUCDON "
              + "(TENMON,DONGIA,LOAIMON,HINHANH) "
              + "VALUES(?,?,?,?)";

        PreparedStatement ps =
                con.prepareStatement(sql);

        String loaiMon;

        if(cboLoaiMon.getSelectedItem()
                .toString()
                .equals("Đồ Ăn"))
        {
            loaiMon = "DOAN";
        }
        else
        {
            loaiMon = "DOUONG";
        }

        ps.setString(
                1,
                txtTenMon.getText().trim()
        );

        ps.setDouble(
                2,
                Double.parseDouble(
                        txtDonGia.getText()
                                 .trim()
                )
        );

        ps.setString(
                3,
                loaiMon
        );

        FileInputStream fis =
                new FileInputStream(
                        selectedFile
                );

        ps.setBinaryStream(
                4,
                fis,
                (int) selectedFile.length()
        );

        ps.executeUpdate();

        JOptionPane.showMessageDialog(
                this,
                "Thêm món ăn thành công!"
        );

        dispose();
    }
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(
                this,
                e.getMessage()
        );
    }
}
private void CheckData()
{
    boolean dayDu =
            !txtTenMon.getText()
                      .trim()
                      .isEmpty()

            &&

            !txtDonGia.getText()
                      .trim()
                      .isEmpty()

            &&

            selectedFile != null;

    btnLuu.setEnabled(dayDu);
};





    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DlgThemMonAn.class.getName());

    /**
     * Creates new form DlgThemSV
     */
    public DlgThemMonAn(
        java.awt.Frame parent,
        boolean modal) {

    super(parent, modal);

    initComponents();

}
public DlgThemMonAn(
        Frame parent,
        boolean modal,
        int maMon,
        String tenMon,
        double donGia,
        String loaiMon)
{
    super(parent, modal);

    initComponents();

    this.maMon = maMon;

    this.cheDoSua = true;

    txtTenMon.setText(
            tenMon
    );

    txtDonGia.setText(
            String.valueOf(
                    donGia
            )
    );

    if(loaiMon.equals("DOAN"))
    {
        cboLoaiMon.setSelectedItem(
                "Đồ Ăn"
        );
    }
    else
    {
        cboLoaiMon.setSelectedItem(
                "Đồ Uống"
        );
    }

    btnLuu.setText(
            "CẬP NHẬT"
    );
}
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngrpGioitinh = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblTenMon = new javax.swing.JLabel();
        txtTenMon = new javax.swing.JTextField();
        lblDonGia = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        lblChucVu = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblChonFile = new javax.swing.JLabel();
        cboLoaiMon = new javax.swing.JComboBox<>();
        btnChonFile = new javax.swing.JButton();
        lblHinhAnh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lblTitle.setText("THÊM MÓN ĂN");

        jSeparator2.setBackground(new java.awt.Color(153, 153, 0));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        lblTenMon.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTenMon.setText("Tên Món Ăn:");

        txtTenMon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenMonKeyReleased(evt);
            }
        });

        lblDonGia.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblDonGia.setText("Đơn Giá :");

        txtDonGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDonGiaKeyReleased(evt);
            }
        });

        lblChucVu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblChucVu.setText("Thể Loại");

        btnLuu.setText("LƯU");
        btnLuu.setEnabled(false);
        btnLuu.addActionListener(this::btnLuuActionPerformed);

        btnHuy.setText("HỦY");
        btnHuy.addActionListener(this::btnHuyActionPerformed);

        lblChonFile.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblChonFile.setText("Chọn File :");

        cboLoaiMon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Chọn Thể Loại Món --", "Đồ Ăn", "Đồ Uống", "" }));
        cboLoaiMon.addActionListener(this::cboLoaiMonActionPerformed);

        btnChonFile.setText("File");
        btnChonFile.addActionListener(this::btnChonFileActionPerformed);

        lblHinhAnh.setText("Hình ảnh");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                    .addComponent(lblChonFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnChonFile, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(lblDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenMon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDonGia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 53, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnHuy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblChonFile)
                            .addComponent(btnChonFile)
                            .addComponent(lblHinhAnh))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenMonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenMonKeyReleased
    CheckData();     
    }//GEN-LAST:event_txtTenMonKeyReleased

    private void txtDonGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDonGiaKeyReleased
    CheckData();  
    }//GEN-LAST:event_txtDonGiaKeyReleased

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
         if(cheDoSua){

        updateMonAn();

    }else{

        insertMonAn();

    }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
    this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnChonFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonFileActionPerformed
    JFileChooser chooser =
            new JFileChooser();

    int result =
            chooser.showOpenDialog(this);

    if(result ==
            JFileChooser.APPROVE_OPTION)
    {
        selectedFile =
                chooser.getSelectedFile();

        ImageIcon icon =
                new ImageIcon(
                        selectedFile
                                .getAbsolutePath()
                );

        Image img =
                icon.getImage()
                    .getScaledInstance(
                            140,
                            80,
                            Image.SCALE_SMOOTH
                    );

        lblHinhAnh.setIcon(
                new ImageIcon(img)
        );

        CheckData();
    
    }
    }//GEN-LAST:event_btnChonFileActionPerformed

    private void cboLoaiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiMonActionPerformed
        System.out.println(
            cboLoaiMon.getSelectedItem()
    );

    CheckData();
    }//GEN-LAST:event_cboLoaiMonActionPerformed
    public static void main(String args[]) {

    java.awt.EventQueue.invokeLater(() -> {

        DlgThemMonAn dlg =
                new DlgThemMonAn(null, true);

        dlg.setVisible(true);

    });

}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonFile;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.ButtonGroup btngrpGioitinh;
    private javax.swing.JComboBox<String> cboLoaiMon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblChonFile;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblDonGia;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblTenMon;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtTenMon;
    // End of variables declaration//GEN-END:variables
}