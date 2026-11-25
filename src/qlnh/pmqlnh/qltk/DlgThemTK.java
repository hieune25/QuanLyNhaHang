package qlnh.pmqlnh.qltk;

import qlnh.pmqlnh.qlnv.*;
import java.awt.Frame;
import qlnh.DBConnect;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 *
 * @author ADMIN
 */
public class DlgThemTK extends javax.swing.JDialog {
    private boolean cheDoSua = false;
    DlgThemTK() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private void updateNV() {

    try {

        Connection con =
                DBConnect.getConnection();

        String sql =
        "UPDATE TAIKHOAN "
      + "SET TENDANGNHAP=?, "
      + "MATKHAU=?, "
      + "VAITRO=? "
      + "WHERE MANV=?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(1,
                txtTK.getText().trim());

        ps.setString(2,
                txtMK.getText().trim());

        ps.setString(
                3,
                rdoQL.isSelected()
                ? "QuanLy"
                : "NhanVien"
        );
        try {
    int maNV =
        Integer.parseInt(txtMaNV.getText().trim());

    ps.setInt(4, maNV);

} catch(NumberFormatException e) {

    JOptionPane.showMessageDialog(
        this,
        "Mã nhân viên phải là số!"
    );

    return;
}

        ps.executeUpdate();

        JOptionPane.showMessageDialog(
                this,
                "Cập nhật thành công!"
        );

        dispose();

    } catch(Exception e){

        JOptionPane.showMessageDialog(
                this,
                e.getMessage()
        );

    }
}
    private void insertNV() {

    try {

        Connection con =
                DBConnect.getConnection();
                        String sqlCheck =
            "SELECT * FROM NHANVIEN WHERE MANV=?";

        PreparedStatement psCheck =
            con.prepareStatement(sqlCheck);

        psCheck.setInt(
            1,
            Integer.parseInt(txtMaNV.getText().trim())
        );

        ResultSet rs =
            psCheck.executeQuery();

        if(!rs.next()) {
            JOptionPane.showMessageDialog(
                this,
                "Mã nhân viên không tồn tại!"
            );
            return;
        }
            String sql =
    "INSERT INTO TAIKHOAN "
  + "(TENDANGNHAP,MATKHAU,VAITRO,MANV) "
  + "VALUES(?,?,?,?)";

        PreparedStatement ps =
                con.prepareStatement(sql);

ps.setString(1, txtTK.getText().trim());

ps.setString(2, txtMK.getText().trim());

ps.setString(
    3,
    rdoQL.isSelected()
        ? "QuanLy"
        : "NhanVien"
);

ps.setInt(
    4,
    Integer.parseInt(
        txtMaNV.getText().trim()
    )
);
        ps.executeUpdate();

        JOptionPane.showMessageDialog(
                this,
                "Thêm nhân viên thành công!"
        );

        dispose();

    } catch(Exception e){

        JOptionPane.showMessageDialog(
                this,
                e.getMessage()
        );
    }
}
private void CheckData()
{
    boolean dayDu =
                !txtTK.getText()
                        .trim()
                        .isEmpty()

            && !txtMK.getText()
                       .trim()
                       .isEmpty()

            && (
            rdoQL
                    .isSelected()

                    ||

                    rdoNV
                    .isSelected()
               )
            && !txtMaNV.getText()
                       .trim()
                       .isEmpty();

    btnLuu.setEnabled(dayDu);
};





    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DlgThemTK.class.getName());

    /**
     * Creates new form DlgThemSV
     */
    public DlgThemTK(
        java.awt.Frame parent,
        boolean modal) {

    super(parent, modal);

    initComponents();

}
public DlgThemTK(
        Frame parent,
        boolean modal,
        String maNV,
        String taiKhoan,
        String matKhau) {

    super(parent, modal);
    initComponents();

    txtMaNV.setText(maNV);
    txtTK.setText(taiKhoan);
    txtMK.setText(matKhau);

    cheDoSua = true;
    lblTitle.setText("CẬP NHẬT NHÂN VIÊN");
    btnLuu.setText("Cập nhật");
    txtMaNV.setEditable(false);
}
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngrpGioitinh = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblTK = new javax.swing.JLabel();
        txtTK = new javax.swing.JTextField();
        lblMK = new javax.swing.JLabel();
        txtMK = new javax.swing.JTextField();
        lblChucVu = new javax.swing.JLabel();
        rdoQL = new javax.swing.JRadioButton();
        rdoNV = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblMaNV = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lblTitle.setText("THÊM NHÂN VIÊN");

        jSeparator2.setBackground(new java.awt.Color(153, 153, 0));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        lblTK.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTK.setText("Tài Khoản :");

        txtTK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTKKeyReleased(evt);
            }
        });

        lblMK.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblMK.setText("Mật Khẩu :");

        txtMK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMKKeyReleased(evt);
            }
        });

        lblChucVu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblChucVu.setText("Chức Vụ : ");

        btngrpGioitinh.add(rdoQL);
        rdoQL.setText("Quản Lý");
        rdoQL.addActionListener(this::rdoQLActionPerformed);

        btngrpGioitinh.add(rdoNV);
        rdoNV.setText("Nhân Viên");
        rdoNV.addActionListener(this::rdoNVActionPerformed);

        btnLuu.setText("LƯU");
        btnLuu.setEnabled(false);
        btnLuu.addActionListener(this::btnLuuActionPerformed);

        btnHuy.setText("HỦY");
        btnHuy.addActionListener(this::btnHuyActionPerformed);

        lblMaNV.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblMaNV.setText("Mã Nhân Viên :");

        txtMaNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaNVKeyReleased(evt);
            }
        });

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
                            .addComponent(lblTK, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(rdoQL, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoNV, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblMK, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(lblTK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMaNV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoQL)
                    .addComponent(rdoNV))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnHuy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTKKeyReleased
    CheckData();     
    }//GEN-LAST:event_txtTKKeyReleased

    private void txtMKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMKKeyReleased
    CheckData();  
    }//GEN-LAST:event_txtMKKeyReleased

    private void rdoQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoQLActionPerformed
    CheckData();
    }//GEN-LAST:event_rdoQLActionPerformed

    private void rdoNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNVActionPerformed
    CheckData();
    }//GEN-LAST:event_rdoNVActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
         if(cheDoSua){

        updateNV();

    }else{

        insertNV();
         }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
    this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtMaNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaNVKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVKeyReleased
    public static void main(String args[]) {

    java.awt.EventQueue.invokeLater(() -> {

        DlgThemTK dlg =
                new DlgThemTK(null, true);

        dlg.setVisible(true);

    });

}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.ButtonGroup btngrpGioitinh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblMK;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblTK;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JRadioButton rdoNV;
    private javax.swing.JRadioButton rdoQL;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtTK;
    // End of variables declaration//GEN-END:variables
}