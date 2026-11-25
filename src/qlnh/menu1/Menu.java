package qlnh.menu1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import qlnh.DBConnect;
import qlnh.DBConnect;
import qlnh.menu1.HoaDon;
import qlnh.menu1.foodcard.FoodCard;
import qlnh.menu1.itemframe.FoodBar;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
public class Menu extends javax.swing.JFrame {
    private HoaDon hoaDon;
    public static Menu instance;
    private CardLayout card;
    private void loadMonTheoLoai(
        String loai)
{
    pnlMenu.removeAll();

    try
    {
        Connection con =
                DBConnect.getConnection();

        if(loai.equals("ALL"))
{
            String sql = "SELECT * FROM THUCDON";
}
else
{
            String sql = "SELECT * FROM THUCDON "
                    + "WHERE LOAIMON=?";
}
        String sql = null;

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(
                1,
                loai
        );

        ResultSet rs =
                ps.executeQuery();

        while(rs.next())
        {
            FoodCard foodCard =
                    new FoodCard(
                            rs.getInt("MAMON"),
                            rs.getString("TENMON"),
                            rs.getDouble("DONGIA"),
                            rs.getString("LOAIMON"),
                            rs.getBytes("HINHANH")
                    );

            pnlMenu.add(foodCard);
        }

        pnlMenu.revalidate();
        pnlMenu.repaint();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}
public void resetOrder()
{
    txtKhachHang.setText("");

    txtKhachHang.setEditable(true);

    hoaDon.resetHoaDon();
}

    public void resetKhachHang()
{
    txtKhachHang.setText("");

    txtKhachHang.setEditable(true);

    txtSDT.setText("");

    txtTen.setText("");

    txtDiaChi.setText("");
}
    public void setKhachHang(String sdt)
{
    txtKhachHang.setText(sdt);

    txtKhachHang.setEditable(false);
}
  public void timKhachHang(String sdt)
{
    try
    {
        Connection con =
                DBConnect.getConnection();

        String sql =
                "SELECT * "
              + "FROM KHACHHANG "
              + "WHERE SDT=?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(1,sdt);

        ResultSet rs =
                ps.executeQuery();

        if(rs.next())
        {
            txtKhachHang.setText(
                    rs.getString("SDT")
            );

            txtKhachHang.setEditable(false);

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Không tìm thấy khách hàng\nTạo mới?",
                        "Thông báo",
                        JOptionPane.YES_NO_OPTION
                );

        if(result ==
                JOptionPane.YES_OPTION)
        {
            txtSDT.setText(sdt);

            card.show(
                    pnlShow,
                    "TAOKH"
            );
        }
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}
public void loadMonAn(String loai)
{
    pnlMenu.removeAll();

    try
    {
        Connection con =
                DBConnect.getConnection();

        String sql;

        if(loai.equals("ALL"))
        {
            sql =
                "SELECT * FROM THUCDON";
        }
        else
        {
            sql =
                "SELECT * FROM THUCDON "
              + "WHERE LoaiMon=?";
        }

        PreparedStatement ps =
                con.prepareStatement(sql);

        if(!loai.equals("ALL"))
        {
            ps.setString(
                    1,
                    loai
            );
        }

        ResultSet rs =
                ps.executeQuery();

        while(rs.next())
{
    FoodCard card =
            new FoodCard(
                    rs.getInt("MAMON"),
                    rs.getString("TENMON"),
                    rs.getDouble("DONGIA"),
                    rs.getString("LOAIMON"),
                    rs.getBytes("HINHANH")
            );

    pnlMenu.add(card);
}

        pnlMenu.revalidate();
        pnlMenu.repaint();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}
    
    public void showMenu()
{
    CardLayout card =
            (CardLayout) pnlShow.getLayout();

    card.show(
            pnlShow,
            "MENU"
    );
}

public void addFoodBar(
        String tenMon,
        double gia,
        int soLuong)
{
    HoaDon.instance.addFoodBar(
            tenMon,
            gia,
            soLuong
    );
}
    public class MonAn {

    private String tenMon;
    private double gia;
    private String loai;

    public MonAn(String tenMon, double gia, String loai) {
        this.tenMon = tenMon;
        this.gia = gia;
        this.loai = loai;
    }

    public String getTenMon() {
        return tenMon;
    }

    public double getGia() {
        return gia;
    }

    public String getLoai() {
        return loai;
    }

}
    

    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Menu.class.getName());

    /**
     * Creates new form Menu
     */
    public  Menu() {
        initComponents();
        instance = this;
        card = (CardLayout) pnlShow.getLayout();
        hoaDon = new HoaDon();

    pnlHoaDon.setLayout(new BorderLayout());

    pnlHoaDon.add(
            hoaDon,
            BorderLayout.CENTER
    );

    card =
        (CardLayout)
        pnlShow.getLayout();
        pnlShow.add(scrlpnlMenu, "MENU");
        pnlShow.add(pnlHoaDon, "HOADON");
        pnlShow.add(pnlTaoKH, "TaoKH");
pnlMenu.setLayout(
    new FlowLayout(
        FlowLayout.LEFT,
        10,
        10
    )
);
pnlMenu.setPreferredSize(
    new Dimension(
        scrlpnlMenu.getWidth(),
        1000
    )
);

    card.show(
            pnlShow,
            "MENU"
    );
    
    scrlpnlMenu.setHorizontalScrollBarPolicy(
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        loadMonAn("ALL");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlTaskbar = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblTatCa = new javax.swing.JLabel();
        lblDoUong = new javax.swing.JLabel();
        lblDoAn = new javax.swing.JLabel();
        lblHoaDon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtKhachHang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pnlShow = new javax.swing.JPanel();
        pnlHoaDon = new javax.swing.JPanel();
        scrlpnlMenu = new javax.swing.JScrollPane();
        pnlMenu = new javax.swing.JPanel();
        pnlTaoKH = new javax.swing.JPanel();
        lblTen = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        lblSDT = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        lblDiaChi = new javax.swing.JLabel();
        btnHuy = new javax.swing.JLabel();
        btnLuu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(51, 255, 204));

        lblTatCa.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lblTatCa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTatCa.setText("TẤT CẢ");
        lblTatCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTatCaMouseClicked(evt);
            }
        });

        lblDoUong.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lblDoUong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDoUong.setText("ĐỒ UỐNG");
        lblDoUong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDoUongMouseClicked(evt);
            }
        });

        lblDoAn.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lblDoAn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDoAn.setText("ĐỒ ĂN");
        lblDoAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDoAnMouseClicked(evt);
            }
        });

        lblHoaDon.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lblHoaDon.setText("HÓA ĐƠN");
        lblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHoaDonMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setText("MÃ KHÁCH HÀNG :");

        txtKhachHang.addActionListener(this::txtKhachHangActionPerformed);

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setText("ĐĂNG XUẤT");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTatCa, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(lblDoUong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtKhachHang, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblHoaDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDoAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 469, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlTaskbarLayout = new javax.swing.GroupLayout(pnlTaskbar);
        pnlTaskbar.setLayout(pnlTaskbarLayout);
        pnlTaskbarLayout.setHorizontalGroup(
            pnlTaskbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaskbarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTaskbarLayout.setVerticalGroup(
            pnlTaskbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaskbarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlShow.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout pnlHoaDonLayout = new javax.swing.GroupLayout(pnlHoaDon);
        pnlHoaDon.setLayout(pnlHoaDonLayout);
        pnlHoaDonLayout.setHorizontalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 758, Short.MAX_VALUE)
        );
        pnlHoaDonLayout.setVerticalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );

        pnlShow.add(pnlHoaDon, "card3");

        scrlpnlMenu.setBackground(new java.awt.Color(153, 255, 204));

        pnlMenu.setBackground(new java.awt.Color(153, 255, 204));
        pnlMenu.setMaximumSize(new java.awt.Dimension(751, 598));

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 756, Short.MAX_VALUE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 763, Short.MAX_VALUE)
        );

        scrlpnlMenu.setViewportView(pnlMenu);

        pnlShow.add(scrlpnlMenu, "card2");

        lblTen.setText("Họ Tên:");

        txtTen.addActionListener(this::txtTenActionPerformed);

        lblSDT.setText("Số điện thoại");

        lblDiaChi.setText("Địa chỉ :");

        btnHuy.setText("Hủy");
        btnHuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyMouseClicked(evt);
            }
        });

        btnLuu.setText("Lưu");
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlTaoKHLayout = new javax.swing.GroupLayout(pnlTaoKH);
        pnlTaoKH.setLayout(pnlTaoKHLayout);
        pnlTaoKHLayout.setHorizontalGroup(
            pnlTaoKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaoKHLayout.createSequentialGroup()
                .addGroup(pnlTaoKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTaoKHLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlTaoKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTen)
                            .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(lblDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)))
                    .addGroup(pnlTaoKHLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(547, Short.MAX_VALUE))
        );
        pnlTaoKHLayout.setVerticalGroup(
            pnlTaoKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaoKHLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlTaoKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pnlShow.add(pnlTaoKH, "card4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(772, Short.MAX_VALUE)
                .addComponent(pnlTaskbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(195, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlTaskbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblTatCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTatCaMouseClicked
    card.show(pnlShow, "MENU");
    loadMonAn("ALL");
    }//GEN-LAST:event_lblTatCaMouseClicked

    private void lblDoAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoAnMouseClicked
    card.show(pnlShow, "MENU");

    loadMonTheoLoai(
            "DOAN"
    );

    }//GEN-LAST:event_lblDoAnMouseClicked

    private void lblDoUongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoUongMouseClicked
    card.show(pnlShow, "MENU");
       loadMonTheoLoai(
            "DOUONG"
    );
    }//GEN-LAST:event_lblDoUongMouseClicked

    private void lblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoaDonMouseClicked
    card.show(pnlShow, "HOADON");
    }//GEN-LAST:event_lblHoaDonMouseClicked

    private void txtKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhachHangActionPerformed
     String sdt =
            txtKhachHang.getText().trim();
     timKhachHang(sdt);
    }//GEN-LAST:event_txtKhachHangActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void btnLuuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMouseClicked
    try
    {
        Connection con =
                DBConnect.getConnection();

        String sql =
                "INSERT INTO KHACHHANG "
              + "(HOTEN,SDT,DIACHI) "
              + "VALUES(?,?,?)";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(
                1,
                txtTen.getText()
                        .trim()
        );

        ps.setString(
                2,
                txtSDT.getText()
                      .trim()
        );

        ps.setString(
                3,
                txtDiaChi.getText()
                         .trim()
        );

        ps.executeUpdate();

        JOptionPane.showMessageDialog(
                this,
                "Thêm khách hàng thành công"
        );

        txtKhachHang.setText(
                txtSDT.getText()
        );

        txtKhachHang.setEditable(false);

        card.show(
                pnlShow,
                "MENU"
        );
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnLuuMouseClicked

    private void btnHuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyMouseClicked
    txtSDT.setText("");

    txtTen.setText("");

    txtDiaChi.setText("");

    card.show(
            pnlShow,
            "MENU"
    );
    }//GEN-LAST:event_btnHuyMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
    JOptionPane.showMessageDialog(rootPane, "Đã đăng xuất tài khoản!");
    new qlnh.Login().setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new Menu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnHuy;
    private javax.swing.JLabel btnLuu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblDoAn;
    private javax.swing.JLabel lblDoUong;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTatCa;
    private javax.swing.JLabel lblTen;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlShow;
    private javax.swing.JPanel pnlTaoKH;
    private javax.swing.JPanel pnlTaskbar;
    private javax.swing.JScrollPane scrlpnlMenu;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables

  }