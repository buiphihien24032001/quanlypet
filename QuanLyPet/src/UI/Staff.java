/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import entity.account;
import entity.addBill;
import entity.bill;
import entity.food;
import entity.item;
import entity.payBill;
import entity.pet;
import entity.staff;
import entity.supp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.security.interfaces.DSAKey;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Admin
 */
public class Staff extends javax.swing.JFrame {

    /**
     * Creates new form MainQL
     */
    //create var
    int total = 0;
    String user = "sa";
    String pass = "hiendaica123";
    String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyPet";
    int index_table_remove_pet = -1;
    int index_table_remove_item = -1;
    int index_table_remove_food = -1;
    int index_table_remove_staff = -1;
    int index_table_remove_supp = -1;
    int index_table_remove_bill = -1;
    int index_table_edit_pet = -1;
    int index_table_edit_item = -1;
    int index_table_edit_food = -1;
    int index_table_edit_staff = -1;
    int index_table_edit_supp = -1;
    int index_table_add_add = -1;
    int index_table_pay_add = -1;

    // table model
    ArrayList<pet> listpet = new ArrayList<pet>();
    ArrayList<food> listfood = new ArrayList<food>();
    ArrayList<item> listitem = new ArrayList<item>();
    ArrayList<staff> liststaff = new ArrayList<staff>();
    ArrayList<supp> listsupp = new ArrayList<supp>();
    ArrayList<bill> listbill = new ArrayList<bill>();
    ArrayList<bill> listbill_import = new ArrayList<bill>();
    ArrayList<account> listacc = new ArrayList<account>();
    ArrayList<account> listacc_admin = new ArrayList<account>();
    ArrayList<addBill> listaddbill = new ArrayList<addBill>();
    ArrayList<payBill> listpaybill = new ArrayList<payBill>();
    ArrayList<addBill> list_pay_add = new ArrayList<addBill>();
    ArrayList<payBill> list_pay_bill = new ArrayList<payBill>();
    String header_pet[] = {"ID", "Age", "Species", "Breed", "Price", "Amount"};
    String header_food[] = {"Code", "Name", "For", "Price", "Amount"};
    String header_item[] = {"Code", "Name", "Price", "Amount"};
    String header_staff[] = {"Code", "Name", "Phone", "Email", "ID", "Pass", "Role"};
    String header_supp[] = {"Code", "Name", "Item", "Phone", "Address", "Note"};
    String header_bill[] = {"Code", "Date", "Staff", "Customer", "Pet", "Amount", "Item", "Amount", "Food", "Amount", "Total"};
    String header_bill_import[] = {"Code", "Date", "Supplier", "Pet", "Amount", "Item", "Amount", "Food", "Amount", "Total"};
    String header_acc[] = {"ID", "Password", "Role"};
    String header_add_add[] = {"Name"};
    String header_add_pay[] = {"Name", "Amount", "Price", "Total"};
    String header_pay_add[] = {"Name", "Price", "Amount"};
    String header_pay_bill[] = {"Name", "Amount", "Price", "Total"};
    DefaultTableModel model_pet = new DefaultTableModel(header_pet, 0);
    DefaultTableModel model_food = new DefaultTableModel(header_food, 0);
    DefaultTableModel model_item = new DefaultTableModel(header_item, 0);
    DefaultTableModel model_staff = new DefaultTableModel(header_staff, 0);
    DefaultTableModel model_supp = new DefaultTableModel(header_supp, 0);
    DefaultTableModel model_bill = new DefaultTableModel(header_bill, 0);
    DefaultTableModel model_bill_import = new DefaultTableModel(header_bill_import, 0);
    DefaultTableModel model_acc = new DefaultTableModel(header_acc, 0);
    DefaultTableModel model_acc_admin = new DefaultTableModel(header_acc, 0);
    DefaultTableModel model_add_add = new DefaultTableModel(header_add_add, 0);
    DefaultTableModel model_add_pay = new DefaultTableModel(header_add_pay, 0);
    DefaultTableModel model_pay_add = new DefaultTableModel(header_pay_add, 0);
    DefaultTableModel model_pay_bill = new DefaultTableModel(header_pay_bill, 0);

    public Staff(String tennv) {
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
        loadtable();
        loadcbb();
        loadchart();
        txt_pay_staff.setText(tennv);
        //set day
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        txt_pay_date.setText("" + date);
        // set model 
        table_warehouse_pet.setModel(model_pet);
        table_warehouse_food.setModel(model_food);
        table_warehouse_item.setModel(model_item);
        table_remove_pet.setModel(model_pet);
        table_remove_food.setModel(model_food);
        table_remove_item.setModel(model_item);
        table_edit_pet.setModel(model_pet);
        table_edit_food.setModel(model_food);
        table_edit_item.setModel(model_item);
        table_edit_staff.setModel(model_staff);
        table_edit_supp.setModel(model_supp);
        table_remove_staff.setModel(model_staff);
        table_remove_supp.setModel(model_supp);
        table_remove_bill.setModel(model_bill);
        table_bill_sold.setModel(model_bill);
        table_bill_import.setModel(model_bill_import);
        table_acc_admin.setModel(model_acc_admin);
        table_acc_user.setModel(model_acc);
        table_pay_add.setModel(model_pay_add);
        table_pay_bill.setModel(model_pay_bill);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        app = new javax.swing.JPanel();
        main_bar = new javax.swing.JPanel();
        pb_statistical = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        pb_logout = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        pb_pay = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pb_warehouse = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pb_bill = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        main_title = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        mainshow = new javax.swing.JPanel();
        panel_warehouse = new javax.swing.JPanel();
        panel_header_warehouse = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        btn_item = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        btn_food = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        btn_pet = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        panel_body_warehouse = new javax.swing.JPanel();
        warehouse_pet = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_warehouse_pet = new javax.swing.JTable();
        warehouse_food = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_warehouse_food = new javax.swing.JTable();
        warehouse_item = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_warehouse_item = new javax.swing.JTable();
        panel_pay = new javax.swing.JPanel();
        pay_listadd = new javax.swing.JPanel();
        cbb_pay_add = new javax.swing.JComboBox<>();
        btn_add = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_pay_add = new javax.swing.JTable();
        pay_billadd = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txt_pay_date = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_pay_customer = new javax.swing.JTextField();
        txt_phone = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_pay_bill = new javax.swing.JTable();
        txt_pay_staff = new javax.swing.JLabel();
        pay_price = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_pay_total = new javax.swing.JTextField();
        txt_pay_recive = new javax.swing.JTextField();
        txt_pay_repay = new javax.swing.JTextField();
        btn_pay = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        panel_account = new javax.swing.JPanel();
        panel_header_account = new javax.swing.JPanel();
        btn_user = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        btn_admin = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        panel_body_account = new javax.swing.JPanel();
        account_admin = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        table_acc_admin = new javax.swing.JTable();
        account_user = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        table_acc_user = new javax.swing.JTable();
        panel_statistical = new javax.swing.JPanel();
        panel_header_statistical = new javax.swing.JPanel();
        btn_sold_statistical = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        btn_import_statistical = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel188 = new javax.swing.JLabel();
        jLabel189 = new javax.swing.JLabel();
        panel_body_statistical = new javax.swing.JPanel();
        statistical_chart_sold = new javax.swing.JPanel();
        panel_sta_sold_chart = new javax.swing.JPanel();
        statistical_chart_import = new javax.swing.JPanel();
        statistical_chart_revenue = new javax.swing.JPanel();
        panel_add = new javax.swing.JPanel();
        panel_header_add = new javax.swing.JPanel();
        btn_stafff_add = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        btn_pet_add = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        btn_item_add = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        btn_supp_add = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        panel_body_add = new javax.swing.JPanel();
        add_pet = new javax.swing.JPanel();
        add_pet_species = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        btn_create_species = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        txt_add_pet_species = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        add_pet_breed = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        btn_create_breed = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        cbb_add_pet_breed_species = new javax.swing.JComboBox<>();
        txt_add_pet_breed_breed = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        add_pet_pet = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        btn_create_pet = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        cbb_add_pet_pet_species = new javax.swing.JComboBox<>();
        cbb_add_pet_pet_breed = new javax.swing.JComboBox<>();
        txt_add_pet_pet_price = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        txt_add_pet_pet_age = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        txt_add_pet_pet_amount = new javax.swing.JTextField();
        add_item = new javax.swing.JPanel();
        add_if_item = new javax.swing.JPanel();
        btn_add_item_create = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        txt_add_item_item_name = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        txt_add_item_item_price = new javax.swing.JTextField();
        jLabel143 = new javax.swing.JLabel();
        txt_add_item_item_amount = new javax.swing.JTextField();
        add_if_food = new javax.swing.JPanel();
        btn_add_food_create = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txt_add_item_food_name = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        cbb_add_item_food_for = new javax.swing.JComboBox<>();
        jLabel72 = new javax.swing.JLabel();
        txt_add_item_food_price = new javax.swing.JTextField();
        jLabel149 = new javax.swing.JLabel();
        txt_add_item_food_amount = new javax.swing.JTextField();
        add_staff = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        txt_add_staff_name = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        txt_add_staff_phone = new javax.swing.JTextField();
        txt_add_staff_mail = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        txt_add_staff_id = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        txt_add_staff_pass = new javax.swing.JTextField();
        btn_add_staff_create = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        cbb_add_staff_role = new javax.swing.JComboBox<>();
        add_supp = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn_add_supp_create = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        txt_add_supp_name = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        txt_add_supp_phone = new javax.swing.JTextField();
        txt_add_supp_address = new javax.swing.JTextField();
        txt_add_supp_note = new javax.swing.JTextField();
        jLabel179 = new javax.swing.JLabel();
        cbb_add_supp_item = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        panel_bill = new javax.swing.JPanel();
        panel_header_bill = new javax.swing.JPanel();
        btn_sold = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        btn_import = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        panel_table_bill = new javax.swing.JPanel();
        bill_sold = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        table_bill_sold = new javax.swing.JTable();
        bill_import = new javax.swing.JPanel();
        ffff = new javax.swing.JScrollPane();
        table_bill_import = new javax.swing.JTable();
        panel_remove = new javax.swing.JPanel();
        panel_header_remove = new javax.swing.JPanel();
        btn_remove_bill = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        btn_remove_pet = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        btn_remove_item = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        btn_remove_food = new javax.swing.JPanel();
        jLabel105 = new javax.swing.JLabel();
        btn_remove_staff = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        btn_remove_sup = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        jLabel190 = new javax.swing.JLabel();
        jLabel191 = new javax.swing.JLabel();
        panel_body_remove = new javax.swing.JPanel();
        remove_pet = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        table_remove_pet = new javax.swing.JTable();
        jLabel94 = new javax.swing.JLabel();
        txt_remove_pet_search = new javax.swing.JTextField();
        btn_remove_pet_sreach = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        btn_remove_pet_remove = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        remove_item = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        table_remove_item = new javax.swing.JTable();
        jLabel101 = new javax.swing.JLabel();
        txt_remove_item_search = new javax.swing.JTextField();
        btn_remove_item_sreach = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        btn_remove_item_remove = new javax.swing.JPanel();
        jLabel103 = new javax.swing.JLabel();
        remove_food = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        table_remove_food = new javax.swing.JTable();
        jLabel99 = new javax.swing.JLabel();
        txt_remove_food_search = new javax.swing.JTextField();
        btn_remove_food_sreach = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        btn_remove_food_remove = new javax.swing.JPanel();
        jLabel104 = new javax.swing.JLabel();
        remove_staff = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        table_remove_staff = new javax.swing.JTable();
        jLabel106 = new javax.swing.JLabel();
        txt_remove_staff_search = new javax.swing.JTextField();
        btn_remove_staff_sreach = new javax.swing.JPanel();
        jLabel107 = new javax.swing.JLabel();
        btn_remove_staff_remove = new javax.swing.JPanel();
        jLabel108 = new javax.swing.JLabel();
        remove_supp = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        table_remove_supp = new javax.swing.JTable();
        jLabel110 = new javax.swing.JLabel();
        txt_remove_supp_search = new javax.swing.JTextField();
        btn_remove_supp_sreach = new javax.swing.JPanel();
        jLabel111 = new javax.swing.JLabel();
        btn_remove_supp_remove = new javax.swing.JPanel();
        jLabel112 = new javax.swing.JLabel();
        remove_bill = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        table_remove_bill = new javax.swing.JTable();
        jLabel114 = new javax.swing.JLabel();
        txt_remove_bill_search = new javax.swing.JTextField();
        btn_remove_bill_search = new javax.swing.JPanel();
        jLabel115 = new javax.swing.JLabel();
        btn_remove_bill_remove = new javax.swing.JPanel();
        jLabel116 = new javax.swing.JLabel();
        panel_edit = new javax.swing.JPanel();
        panel_header_edit = new javax.swing.JPanel();
        btn_edit_bill = new javax.swing.JPanel();
        jLabel119 = new javax.swing.JLabel();
        btn_edit_pet = new javax.swing.JPanel();
        jLabel120 = new javax.swing.JLabel();
        btn_edit_item = new javax.swing.JPanel();
        jLabel121 = new javax.swing.JLabel();
        btn_edit_food = new javax.swing.JPanel();
        jLabel122 = new javax.swing.JLabel();
        btn_edit_staff = new javax.swing.JPanel();
        jLabel123 = new javax.swing.JLabel();
        btn_edit_sup = new javax.swing.JPanel();
        jLabel124 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        panel_body_edit = new javax.swing.JPanel();
        edit_pet = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTable18 = new javax.swing.JTable();
        jLabel125 = new javax.swing.JLabel();
        jTextField39 = new javax.swing.JTextField();
        btn_remove_pet_sreach1 = new javax.swing.JPanel();
        jLabel126 = new javax.swing.JLabel();
        btn_remove_pet_remove1 = new javax.swing.JPanel();
        jLabel127 = new javax.swing.JLabel();
        edit_item = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTable19 = new javax.swing.JTable();
        jLabel128 = new javax.swing.JLabel();
        jTextField40 = new javax.swing.JTextField();
        btn_remove_item_sreach1 = new javax.swing.JPanel();
        jLabel129 = new javax.swing.JLabel();
        btn_remove_item_remove1 = new javax.swing.JPanel();
        jLabel130 = new javax.swing.JLabel();
        edit_food = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTable20 = new javax.swing.JTable();
        jLabel131 = new javax.swing.JLabel();
        jTextField41 = new javax.swing.JTextField();
        btn_remove_food_sreach1 = new javax.swing.JPanel();
        jLabel132 = new javax.swing.JLabel();
        btn_remove_food_remove1 = new javax.swing.JPanel();
        jLabel133 = new javax.swing.JLabel();
        edit_staff = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTable21 = new javax.swing.JTable();
        jLabel134 = new javax.swing.JLabel();
        jTextField42 = new javax.swing.JTextField();
        btn_remove_staff_sreach1 = new javax.swing.JPanel();
        jLabel135 = new javax.swing.JLabel();
        btn_remove_staff_remove1 = new javax.swing.JPanel();
        jLabel136 = new javax.swing.JLabel();
        edit_supp = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTable22 = new javax.swing.JTable();
        jLabel137 = new javax.swing.JLabel();
        jTextField43 = new javax.swing.JTextField();
        btn_remove_supp_sreach1 = new javax.swing.JPanel();
        jLabel138 = new javax.swing.JLabel();
        btn_remove_supp_remove1 = new javax.swing.JPanel();
        jLabel139 = new javax.swing.JLabel();
        edit_bill = new javax.swing.JPanel();
        jScrollPane24 = new javax.swing.JScrollPane();
        jTable23 = new javax.swing.JTable();
        jLabel140 = new javax.swing.JLabel();
        jTextField44 = new javax.swing.JTextField();
        btn_remove_bill_search1 = new javax.swing.JPanel();
        jLabel141 = new javax.swing.JLabel();
        btn_remove_bill_remove1 = new javax.swing.JPanel();
        jLabel142 = new javax.swing.JLabel();
        panel_edit1 = new javax.swing.JPanel();
        panel_header_edit1 = new javax.swing.JPanel();
        btn_edit1_pet = new javax.swing.JPanel();
        jLabel144 = new javax.swing.JLabel();
        btn_edit1_item = new javax.swing.JPanel();
        jLabel145 = new javax.swing.JLabel();
        btn_edit1_food = new javax.swing.JPanel();
        jLabel146 = new javax.swing.JLabel();
        btn_edit1_staff = new javax.swing.JPanel();
        jLabel147 = new javax.swing.JLabel();
        btn_edit1_supp = new javax.swing.JPanel();
        jLabel148 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        panel_body_edit1 = new javax.swing.JPanel();
        edit1_pet = new javax.swing.JPanel();
        jScrollPane25 = new javax.swing.JScrollPane();
        table_edit_pet = new javax.swing.JTable();
        jLabel150 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        txt_edit_pet_id = new javax.swing.JLabel();
        txt_edit_pet_age = new javax.swing.JTextField();
        cbb_edit_pet_species = new javax.swing.JComboBox<>();
        cbb_edit_pet_breed = new javax.swing.JComboBox<>();
        txt_edit_pet_price = new javax.swing.JTextField();
        btn_edit_pet_edit = new javax.swing.JPanel();
        jLabel156 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        txt_edit_pet_amount = new javax.swing.JTextField();
        edit1_food = new javax.swing.JPanel();
        jScrollPane27 = new javax.swing.JScrollPane();
        table_edit_food = new javax.swing.JTable();
        btn_edit_food_edit = new javax.swing.JPanel();
        jLabel163 = new javax.swing.JLabel();
        jLabel167 = new javax.swing.JLabel();
        jLabel168 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        txt_edit_food_code = new javax.swing.JLabel();
        txt_edit_food_name = new javax.swing.JTextField();
        txt_edit_food_price = new javax.swing.JTextField();
        cbb_edit_food_for = new javax.swing.JComboBox<>();
        jLabel171 = new javax.swing.JLabel();
        txt_edit_food_amount = new javax.swing.JTextField();
        edit1_staff = new javax.swing.JPanel();
        btn_edit_staff_edit = new javax.swing.JPanel();
        jLabel164 = new javax.swing.JLabel();
        jScrollPane28 = new javax.swing.JScrollPane();
        table_edit_staff = new javax.swing.JTable();
        jLabel172 = new javax.swing.JLabel();
        jLabel173 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        jLabel175 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        txt_edit_staff_code = new javax.swing.JLabel();
        txt_edit_staff_name = new javax.swing.JTextField();
        txt_edit_staff_phone = new javax.swing.JTextField();
        txt_edit_staff_email = new javax.swing.JTextField();
        txt_edit_staff_id = new javax.swing.JTextField();
        txt_edit_staff_pass = new javax.swing.JTextField();
        cbb_edit_staff_role = new javax.swing.JComboBox<>();
        edit1_supp = new javax.swing.JPanel();
        btn_edit_supp_edit = new javax.swing.JPanel();
        jLabel165 = new javax.swing.JLabel();
        jScrollPane30 = new javax.swing.JScrollPane();
        table_edit_supp = new javax.swing.JTable();
        jLabel181 = new javax.swing.JLabel();
        txt_edit_supp_code = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        jLabel184 = new javax.swing.JLabel();
        jLabel185 = new javax.swing.JLabel();
        jLabel186 = new javax.swing.JLabel();
        jLabel187 = new javax.swing.JLabel();
        txt_edit_supp_name = new javax.swing.JTextField();
        cbb_edit_supp_items = new javax.swing.JComboBox<>();
        txt_edit_supp_phone = new javax.swing.JTextField();
        txt_edit_supp_address = new javax.swing.JTextField();
        txt_edit_supp_note = new javax.swing.JTextField();
        edit1_item = new javax.swing.JPanel();
        jScrollPane26 = new javax.swing.JScrollPane();
        table_edit_item = new javax.swing.JTable();
        btn_edit_item_edit = new javax.swing.JPanel();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        txt_edit_item_code = new javax.swing.JLabel();
        txt_edit_item_name = new javax.swing.JTextField();
        txt_edit_item_price = new javax.swing.JTextField();
        jLabel162 = new javax.swing.JLabel();
        txt_edit_item_amount = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        app.setBackground(new java.awt.Color(255, 255, 255));
        app.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pb1MousePressed(evt);
            }
        });
        app.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        main_bar.setBackground(new java.awt.Color(54, 33, 89));
        main_bar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pb_statistical.setBackground(new java.awt.Color(64, 43, 100));
        pb_statistical.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pb_statisticalmouseclick(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pb_statisticalpb1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pb_statisticalpb1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pb_accountMousePressed(evt);
            }
        });
        pb_statistical.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(204, 204, 204));
        jLabel34.setText("Statistical");
        pb_statistical.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        main_bar.add(pb_statistical, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 220, 43));

        pb_logout.setBackground(new java.awt.Color(64, 43, 100));
        pb_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pb_logoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pb_logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pb_logoutMouseExited(evt);
            }
        });
        pb_logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Logout");
        pb_logout.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        main_bar.add(pb_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 660, 220, 43));

        pb_pay.setBackground(new java.awt.Color(64, 43, 100));
        pb_pay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseclick(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pb_homeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pb_homeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pb_accountMousePressed(evt);
            }
        });
        pb_pay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Receipt & Pay");
        pb_pay.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        main_bar.add(pb_pay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 220, 43));

        pb_warehouse.setBackground(new java.awt.Color(64, 43, 100));
        pb_warehouse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseclick(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pb_homeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pb_homeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pb_accountMousePressed(evt);
            }
        });
        pb_warehouse.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Warehouse");
        pb_warehouse.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        main_bar.add(pb_warehouse, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 220, 43));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Meow Store");
        main_bar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));
        main_bar.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 140, 10));

        pb_bill.setBackground(new java.awt.Color(64, 43, 100));
        pb_bill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pb_billmouseclick(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pb_billpb1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pb_billpb1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pb_accountMousePressed(evt);
            }
        });
        pb_bill.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("Bill");
        pb_bill.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        main_bar.add(pb_bill, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 220, 43));

        app.add(main_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 700));

        main_title.setBackground(new java.awt.Color(110, 89, 222));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Wellcome to paradise of pet");

        jLabel14.setBackground(new java.awt.Color(204, 204, 204));
        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("(=ↀωↀ=)");

        jLabel23.setBackground(new java.awt.Color(204, 204, 204));
        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 204, 204));
        jLabel23.setText("(=ↀωↀ=)");

        javax.swing.GroupLayout main_titleLayout = new javax.swing.GroupLayout(main_title);
        main_title.setLayout(main_titleLayout);
        main_titleLayout.setHorizontalGroup(
            main_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_titleLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(87, 87, 87))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_titleLayout.createSequentialGroup()
                .addContainerGap(153, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(151, 151, 151))
        );
        main_titleLayout.setVerticalGroup(
            main_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_titleLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(main_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel23))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        app.add(main_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 780, 110));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Meow ♥ Family");
        app.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText(" Prestige and quality");
        app.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, -1, 20));

        mainshow.setLayout(new java.awt.CardLayout());

        panel_warehouse.setBackground(new java.awt.Color(255, 255, 255));
        panel_warehouse.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_header_warehouse.setBackground(new java.awt.Color(255, 255, 255));
        panel_header_warehouse.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel29.setText("WAREHOUSE");
        panel_header_warehouse.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setText("Meow storage, place have everything");
        panel_header_warehouse.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 220, -1));

        btn_item.setBackground(new java.awt.Color(255, 255, 255));
        btn_item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_foodMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_foodMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_foodMouseExited(evt);
            }
        });
        btn_item.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setText("Items");
        btn_item.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 30));

        panel_header_warehouse.add(btn_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 90, 30));

        btn_food.setBackground(new java.awt.Color(255, 255, 255));
        btn_food.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_foodMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_foodMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_foodMouseExited(evt);
            }
        });
        btn_food.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setText("Food");
        btn_food.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 30));

        panel_header_warehouse.add(btn_food, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 90, 30));

        btn_pet.setBackground(new java.awt.Color(255, 255, 255));
        btn_pet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_foodMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_foodMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_foodMouseExited(evt);
            }
        });
        btn_pet.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("Pet");
        btn_pet.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 30));

        panel_header_warehouse.add(btn_pet, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 90, 30));

        panel_warehouse.add(panel_header_warehouse, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 90));

        panel_body_warehouse.setBackground(new java.awt.Color(255, 255, 255));
        panel_body_warehouse.setLayout(new java.awt.CardLayout());

        warehouse_pet.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_warehouse_pet.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_warehouse_pet.setModel(new javax.swing.table.DefaultTableModel(
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
        table_warehouse_pet.setGridColor(new java.awt.Color(255, 255, 255));
        table_warehouse_pet.setSelectionBackground(new java.awt.Color(110, 89, 222));
        jScrollPane3.setViewportView(table_warehouse_pet);

        warehouse_pet.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 430));

        panel_body_warehouse.add(warehouse_pet, "card2");

        warehouse_food.setBackground(new java.awt.Color(255, 255, 255));

        table_warehouse_food.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_warehouse_food.setModel(new javax.swing.table.DefaultTableModel(
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
        table_warehouse_food.setGridColor(new java.awt.Color(255, 255, 255));
        table_warehouse_food.setSelectionBackground(new java.awt.Color(110, 89, 222));
        jScrollPane4.setViewportView(table_warehouse_food);

        javax.swing.GroupLayout warehouse_foodLayout = new javax.swing.GroupLayout(warehouse_food);
        warehouse_food.setLayout(warehouse_foodLayout);
        warehouse_foodLayout.setHorizontalGroup(
            warehouse_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );
        warehouse_foodLayout.setVerticalGroup(
            warehouse_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );

        panel_body_warehouse.add(warehouse_food, "card3");

        warehouse_item.setBackground(new java.awt.Color(255, 255, 255));

        table_warehouse_item.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_warehouse_item.setModel(new javax.swing.table.DefaultTableModel(
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
        table_warehouse_item.setGridColor(new java.awt.Color(255, 255, 255));
        table_warehouse_item.setSelectionBackground(new java.awt.Color(110, 89, 222));
        jScrollPane5.setViewportView(table_warehouse_item);

        javax.swing.GroupLayout warehouse_itemLayout = new javax.swing.GroupLayout(warehouse_item);
        warehouse_item.setLayout(warehouse_itemLayout);
        warehouse_itemLayout.setHorizontalGroup(
            warehouse_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );
        warehouse_itemLayout.setVerticalGroup(
            warehouse_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(warehouse_itemLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        panel_body_warehouse.add(warehouse_item, "card4");

        panel_warehouse.add(panel_body_warehouse, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 780, 430));

        mainshow.add(panel_warehouse, "card3");

        panel_pay.setBackground(new java.awt.Color(255, 255, 255));
        panel_pay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pay_listadd.setBackground(new java.awt.Color(255, 255, 255));

        cbb_pay_add.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pet", "Item", "Food", " " }));
        cbb_pay_add.setBorder(null);
        cbb_pay_add.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_pay_addItemStateChanged(evt);
            }
        });

        btn_add.setBackground(new java.awt.Color(255, 255, 255));
        btn_add.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 33, 89)));
        btn_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_addMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_removeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_removeMouseExited(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel26.setText("Add");

        javax.swing.GroupLayout btn_addLayout = new javax.swing.GroupLayout(btn_add);
        btn_add.setLayout(btn_addLayout);
        btn_addLayout.setHorizontalGroup(
            btn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_addLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_addLayout.setVerticalGroup(
            btn_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_addLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        table_pay_add.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_pay_add.setModel(new javax.swing.table.DefaultTableModel(
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
        table_pay_add.setGridColor(new java.awt.Color(255, 255, 255));
        table_pay_add.setSelectionBackground(new java.awt.Color(110, 89, 222));
        table_pay_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_pay_addMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_pay_add);

        javax.swing.GroupLayout pay_listaddLayout = new javax.swing.GroupLayout(pay_listadd);
        pay_listadd.setLayout(pay_listaddLayout);
        pay_listaddLayout.setHorizontalGroup(
            pay_listaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pay_listaddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pay_listaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pay_listaddLayout.createSequentialGroup()
                        .addGroup(pay_listaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pay_listaddLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pay_listaddLayout.createSequentialGroup()
                                .addComponent(cbb_pay_add, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        pay_listaddLayout.setVerticalGroup(
            pay_listaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pay_listaddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbb_pay_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        panel_pay.add(pay_listadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 520));

        pay_billadd.setBackground(new java.awt.Color(255, 255, 255));
        pay_billadd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel13.setText("Date");
        pay_billadd.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 42, -1));

        txt_pay_date.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        txt_pay_date.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        pay_billadd.add(txt_pay_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 10, 98, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel15.setText("Staff");
        pay_billadd.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 52, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel16.setText("Customer");
        pay_billadd.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel17.setText("Phone");
        pay_billadd.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, -1));

        txt_pay_customer.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        txt_pay_customer.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        pay_billadd.add(txt_pay_customer, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 10, 125, -1));

        txt_phone.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        txt_phone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        pay_billadd.add(txt_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 125, -1));

        table_pay_bill.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_pay_bill.setModel(new javax.swing.table.DefaultTableModel(
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
        table_pay_bill.setGridColor(new java.awt.Color(255, 255, 255));
        table_pay_bill.setSelectionBackground(new java.awt.Color(110, 89, 222));
        jScrollPane2.setViewportView(table_pay_bill);

        pay_billadd.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 77, 520, 340));

        txt_pay_staff.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        pay_billadd.add(txt_pay_staff, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 110, 20));

        panel_pay.add(pay_billadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 520, 420));

        pay_price.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel18.setText("Pay");

        jLabel19.setText("__________________________________________________________________________________");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel21.setText("Total");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel20.setText("Recive");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel22.setText("Repay");

        txt_pay_total.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        txt_pay_total.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txt_pay_recive.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        txt_pay_recive.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txt_pay_recive.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_pay_reciveKeyReleased(evt);
            }
        });

        txt_pay_repay.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        txt_pay_repay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btn_pay.setBackground(new java.awt.Color(255, 255, 255));
        btn_pay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 33, 89)));
        btn_pay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_payMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_removeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_removeMouseExited(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel28.setText("Pay");

        javax.swing.GroupLayout btn_payLayout = new javax.swing.GroupLayout(btn_pay);
        btn_pay.setLayout(btn_payLayout);
        btn_payLayout.setHorizontalGroup(
            btn_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_payLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_payLayout.setVerticalGroup(
            btn_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_payLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel28)
                .addContainerGap())
        );

        javax.swing.GroupLayout pay_priceLayout = new javax.swing.GroupLayout(pay_price);
        pay_price.setLayout(pay_priceLayout);
        pay_priceLayout.setHorizontalGroup(
            pay_priceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pay_priceLayout.createSequentialGroup()
                .addGroup(pay_priceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pay_priceLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel18)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pay_priceLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel21)
                        .addGap(32, 32, 32)
                        .addComponent(txt_pay_total, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pay_priceLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pay_priceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(pay_priceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_pay_recive)
                            .addComponent(txt_pay_repay))
                        .addGap(192, 192, 192)
                        .addComponent(btn_pay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)))
                .addGap(11, 11, 11))
        );
        pay_priceLayout.setVerticalGroup(
            pay_priceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pay_priceLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pay_priceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addGroup(pay_priceLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel19)))
                .addGap(6, 6, 6)
                .addGroup(pay_priceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(txt_pay_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pay_priceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pay_priceLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel22))
                    .addGroup(pay_priceLayout.createSequentialGroup()
                        .addComponent(txt_pay_recive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txt_pay_repay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_pay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panel_pay.add(pay_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, 520, -1));

        mainshow.add(panel_pay, "card4");

        panel_account.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_header_account.setBackground(new java.awt.Color(255, 255, 255));
        panel_header_account.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_user.setBackground(new java.awt.Color(255, 255, 255));
        btn_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_userMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_adminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_adminMouseExited(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setText("User");

        javax.swing.GroupLayout btn_userLayout = new javax.swing.GroupLayout(btn_user);
        btn_user.setLayout(btn_userLayout);
        btn_userLayout.setHorizontalGroup(
            btn_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
            .addGroup(btn_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_userLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel40)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btn_userLayout.setVerticalGroup(
            btn_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(btn_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_userLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel_header_account.add(btn_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 90, 30));

        btn_admin.setBackground(new java.awt.Color(255, 255, 255));
        btn_admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_userMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_adminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_adminMouseExited(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setText("Admin");

        javax.swing.GroupLayout btn_adminLayout = new javax.swing.GroupLayout(btn_admin);
        btn_admin.setLayout(btn_adminLayout);
        btn_adminLayout.setHorizontalGroup(
            btn_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
            .addGroup(btn_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_adminLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel39)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btn_adminLayout.setVerticalGroup(
            btn_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(btn_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_adminLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel_header_account.add(btn_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 90, 30));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel24.setText("ACCOUNT\n");
        panel_header_account.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("indivIduals can access");
        panel_header_account.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 130, -1));

        panel_account.add(panel_header_account, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 90));

        panel_body_account.setBackground(new java.awt.Color(255, 255, 255));
        panel_body_account.setLayout(new java.awt.CardLayout());

        table_acc_admin.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_acc_admin.setModel(new javax.swing.table.DefaultTableModel(
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
        table_acc_admin.setGridColor(new java.awt.Color(255, 255, 255));
        table_acc_admin.setSelectionBackground(new java.awt.Color(110, 89, 222));
        jScrollPane8.setViewportView(table_acc_admin);

        javax.swing.GroupLayout account_adminLayout = new javax.swing.GroupLayout(account_admin);
        account_admin.setLayout(account_adminLayout);
        account_adminLayout.setHorizontalGroup(
            account_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );
        account_adminLayout.setVerticalGroup(
            account_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );

        panel_body_account.add(account_admin, "card2");

        table_acc_user.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_acc_user.setModel(new javax.swing.table.DefaultTableModel(
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
        table_acc_user.setGridColor(new java.awt.Color(255, 255, 255));
        table_acc_user.setSelectionBackground(new java.awt.Color(110, 89, 222));
        jScrollPane9.setViewportView(table_acc_user);

        javax.swing.GroupLayout account_userLayout = new javax.swing.GroupLayout(account_user);
        account_user.setLayout(account_userLayout);
        account_userLayout.setHorizontalGroup(
            account_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );
        account_userLayout.setVerticalGroup(
            account_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );

        panel_body_account.add(account_user, "card3");

        panel_account.add(panel_body_account, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 780, 430));

        mainshow.add(panel_account, "card5");

        panel_statistical.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_header_statistical.setBackground(new java.awt.Color(255, 255, 255));
        panel_header_statistical.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_sold_statistical.setBackground(new java.awt.Color(255, 255, 255));
        btn_sold_statistical.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_sold_statisticalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_sold_statisticalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_sold_statisticalMouseExited(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setText("Bill amount");

        javax.swing.GroupLayout btn_sold_statisticalLayout = new javax.swing.GroupLayout(btn_sold_statistical);
        btn_sold_statistical.setLayout(btn_sold_statisticalLayout);
        btn_sold_statisticalLayout.setHorizontalGroup(
            btn_sold_statisticalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
            .addGroup(btn_sold_statisticalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_sold_statisticalLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel41)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btn_sold_statisticalLayout.setVerticalGroup(
            btn_sold_statisticalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(btn_sold_statisticalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_sold_statisticalLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel_header_statistical.add(btn_sold_statistical, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 90, 30));

        btn_import_statistical.setBackground(new java.awt.Color(255, 255, 255));
        btn_import_statistical.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_sold_statisticalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_sold_statisticalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_sold_statisticalMouseExited(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setText("Proceeds");
        jLabel42.setToolTipText("");

        javax.swing.GroupLayout btn_import_statisticalLayout = new javax.swing.GroupLayout(btn_import_statistical);
        btn_import_statistical.setLayout(btn_import_statisticalLayout);
        btn_import_statisticalLayout.setHorizontalGroup(
            btn_import_statisticalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
            .addGroup(btn_import_statisticalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_import_statisticalLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel42)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btn_import_statisticalLayout.setVerticalGroup(
            btn_import_statisticalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(btn_import_statisticalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_import_statisticalLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel_header_statistical.add(btn_import_statistical, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 90, 30));

        jLabel188.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel188.setText("STATISTICAL");
        panel_header_statistical.add(jLabel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        jLabel189.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel189.setText("The statistics of the achievements that we have");
        panel_header_statistical.add(jLabel189, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 280, -1));

        panel_statistical.add(panel_header_statistical, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 90));

        panel_body_statistical.setBackground(new java.awt.Color(255, 255, 255));
        panel_body_statistical.setLayout(new java.awt.CardLayout());

        statistical_chart_sold.setBackground(new java.awt.Color(255, 255, 255));
        statistical_chart_sold.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_sta_sold_chart.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel_sta_sold_chartLayout = new javax.swing.GroupLayout(panel_sta_sold_chart);
        panel_sta_sold_chart.setLayout(panel_sta_sold_chartLayout);
        panel_sta_sold_chartLayout.setHorizontalGroup(
            panel_sta_sold_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        panel_sta_sold_chartLayout.setVerticalGroup(
            panel_sta_sold_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        statistical_chart_sold.add(panel_sta_sold_chart, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 430));

        panel_body_statistical.add(statistical_chart_sold, "card2");

        statistical_chart_import.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout statistical_chart_importLayout = new javax.swing.GroupLayout(statistical_chart_import);
        statistical_chart_import.setLayout(statistical_chart_importLayout);
        statistical_chart_importLayout.setHorizontalGroup(
            statistical_chart_importLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        statistical_chart_importLayout.setVerticalGroup(
            statistical_chart_importLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        panel_body_statistical.add(statistical_chart_import, "card3");

        javax.swing.GroupLayout statistical_chart_revenueLayout = new javax.swing.GroupLayout(statistical_chart_revenue);
        statistical_chart_revenue.setLayout(statistical_chart_revenueLayout);
        statistical_chart_revenueLayout.setHorizontalGroup(
            statistical_chart_revenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        statistical_chart_revenueLayout.setVerticalGroup(
            statistical_chart_revenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        panel_body_statistical.add(statistical_chart_revenue, "card4");

        panel_statistical.add(panel_body_statistical, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 780, 430));

        mainshow.add(panel_statistical, "card7");

        panel_add.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_header_add.setBackground(new java.awt.Color(255, 255, 255));
        panel_header_add.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_stafff_add.setBackground(new java.awt.Color(255, 255, 255));
        btn_stafff_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_stafff_addMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_stafff_addMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_stafff_addMouseExited(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel44.setText("Staff");

        javax.swing.GroupLayout btn_stafff_addLayout = new javax.swing.GroupLayout(btn_stafff_add);
        btn_stafff_add.setLayout(btn_stafff_addLayout);
        btn_stafff_addLayout.setHorizontalGroup(
            btn_stafff_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
            .addGroup(btn_stafff_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_stafff_addLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel44)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btn_stafff_addLayout.setVerticalGroup(
            btn_stafff_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(btn_stafff_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_stafff_addLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel_header_add.add(btn_stafff_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 90, 30));

        btn_pet_add.setBackground(new java.awt.Color(255, 255, 255));
        btn_pet_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_stafff_addMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_stafff_addMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_stafff_addMouseExited(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel46.setText("Pet");

        javax.swing.GroupLayout btn_pet_addLayout = new javax.swing.GroupLayout(btn_pet_add);
        btn_pet_add.setLayout(btn_pet_addLayout);
        btn_pet_addLayout.setHorizontalGroup(
            btn_pet_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
            .addGroup(btn_pet_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_pet_addLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel46)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btn_pet_addLayout.setVerticalGroup(
            btn_pet_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(btn_pet_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_pet_addLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel_header_add.add(btn_pet_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 90, 30));

        btn_item_add.setBackground(new java.awt.Color(255, 255, 255));
        btn_item_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_stafff_addMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_stafff_addMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_stafff_addMouseExited(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel48.setText("I&F");

        javax.swing.GroupLayout btn_item_addLayout = new javax.swing.GroupLayout(btn_item_add);
        btn_item_add.setLayout(btn_item_addLayout);
        btn_item_addLayout.setHorizontalGroup(
            btn_item_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
            .addGroup(btn_item_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_item_addLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel48)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btn_item_addLayout.setVerticalGroup(
            btn_item_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(btn_item_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_item_addLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel_header_add.add(btn_item_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 90, 30));

        btn_supp_add.setBackground(new java.awt.Color(255, 255, 255));
        btn_supp_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_supp_addMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_supp_addMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_supp_addMouseExited(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel57.setText("Supplier");

        javax.swing.GroupLayout btn_supp_addLayout = new javax.swing.GroupLayout(btn_supp_add);
        btn_supp_add.setLayout(btn_supp_addLayout);
        btn_supp_addLayout.setHorizontalGroup(
            btn_supp_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
            .addGroup(btn_supp_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_supp_addLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel57)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btn_supp_addLayout.setVerticalGroup(
            btn_supp_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(btn_supp_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_supp_addLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel_header_add.add(btn_supp_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 90, 30));

        jLabel180.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel180.setText("ADD");
        panel_header_add.add(jLabel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        jLabel182.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel182.setText(" Added quality products and angels");
        panel_header_add.add(jLabel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 220, -1));

        panel_add.add(panel_header_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 90));

        panel_body_add.setBackground(new java.awt.Color(255, 255, 255));
        panel_body_add.setLayout(new java.awt.CardLayout());

        add_pet.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add_pet_species.setBackground(new java.awt.Color(255, 255, 255));
        add_pet_species.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel54.setText("Species");
        add_pet_species.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 18, -1, -1));

        btn_create_species.setBackground(new java.awt.Color(255, 255, 255));
        btn_create_species.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_create_species.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_create_speciesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_create_petMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_create_petMouseExited(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel60.setText("Created");

        javax.swing.GroupLayout btn_create_speciesLayout = new javax.swing.GroupLayout(btn_create_species);
        btn_create_species.setLayout(btn_create_speciesLayout);
        btn_create_speciesLayout.setHorizontalGroup(
            btn_create_speciesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_create_speciesLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel60)
                .addGap(26, 26, 26))
        );
        btn_create_speciesLayout.setVerticalGroup(
            btn_create_speciesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        add_pet_species.add(btn_create_species, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 100, 30));

        txt_add_pet_species.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_pet_species.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        add_pet_species.add(txt_add_pet_species, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 170, 20));

        jLabel78.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel78.setText("Species");
        add_pet_species.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        add_pet.add(add_pet_species, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 120));

        add_pet_breed.setBackground(new java.awt.Color(255, 255, 255));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel55.setText("Breed");

        btn_create_breed.setBackground(new java.awt.Color(255, 255, 255));
        btn_create_breed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_create_breed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_create_breedMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_create_petMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_create_petMouseExited(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel61.setText("Created");

        javax.swing.GroupLayout btn_create_breedLayout = new javax.swing.GroupLayout(btn_create_breed);
        btn_create_breed.setLayout(btn_create_breedLayout);
        btn_create_breedLayout.setHorizontalGroup(
            btn_create_breedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_create_breedLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel61)
                .addGap(26, 26, 26))
        );
        btn_create_breedLayout.setVerticalGroup(
            btn_create_breedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        cbb_add_pet_breed_species.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txt_add_pet_breed_breed.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_pet_breed_breed.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel76.setText("Species");

        jLabel77.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel77.setText("Breed");

        javax.swing.GroupLayout add_pet_breedLayout = new javax.swing.GroupLayout(add_pet_breed);
        add_pet_breed.setLayout(add_pet_breedLayout);
        add_pet_breedLayout.setHorizontalGroup(
            add_pet_breedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_pet_breedLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel76)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbb_add_pet_breed_species, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_add_pet_breed_breed, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(btn_create_breed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(add_pet_breedLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel55)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        add_pet_breedLayout.setVerticalGroup(
            add_pet_breedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_pet_breedLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel55)
                .addGroup(add_pet_breedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(add_pet_breedLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btn_create_breed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(add_pet_breedLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(add_pet_breedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbb_add_pet_breed_species, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_add_pet_breed_breed, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel76)
                            .addComponent(jLabel77)))))
        );

        add_pet.add(add_pet_breed, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 780, 120));

        add_pet_pet.setBackground(new java.awt.Color(255, 255, 255));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel56.setText("Pet");

        btn_create_pet.setBackground(new java.awt.Color(255, 255, 255));
        btn_create_pet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_create_pet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_create_petMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_create_petMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_create_petMouseExited(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel62.setText("Created");

        javax.swing.GroupLayout btn_create_petLayout = new javax.swing.GroupLayout(btn_create_pet);
        btn_create_pet.setLayout(btn_create_petLayout);
        btn_create_petLayout.setHorizontalGroup(
            btn_create_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_create_petLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel62)
                .addGap(26, 26, 26))
        );
        btn_create_petLayout.setVerticalGroup(
            btn_create_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        cbb_add_pet_pet_species.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_add_pet_pet_species.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_add_pet_pet_speciesItemStateChanged(evt);
            }
        });

        cbb_add_pet_pet_breed.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txt_add_pet_pet_price.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_pet_pet_price.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel63.setText("Age");

        txt_add_pet_pet_age.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_pet_pet_age.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel65.setText("Price");

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel74.setText("Species");

        jLabel75.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel75.setText("Breed");

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel64.setText("Amount");

        txt_add_pet_pet_amount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_pet_pet_amount.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout add_pet_petLayout = new javax.swing.GroupLayout(add_pet_pet);
        add_pet_pet.setLayout(add_pet_petLayout);
        add_pet_petLayout.setHorizontalGroup(
            add_pet_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_pet_petLayout.createSequentialGroup()
                .addGroup(add_pet_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(add_pet_petLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel56))
                    .addGroup(add_pet_petLayout.createSequentialGroup()
                        .addGap(670, 670, 670)
                        .addComponent(btn_create_pet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(add_pet_petLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(add_pet_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(add_pet_petLayout.createSequentialGroup()
                                .addComponent(jLabel63)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_add_pet_pet_age, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(add_pet_petLayout.createSequentialGroup()
                                .addComponent(jLabel74)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbb_add_pet_pet_species, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(add_pet_petLayout.createSequentialGroup()
                                .addComponent(jLabel64)
                                .addGap(18, 18, 18)
                                .addComponent(txt_add_pet_pet_amount)))
                        .addGap(81, 81, 81)
                        .addGroup(add_pet_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(add_pet_petLayout.createSequentialGroup()
                                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_add_pet_pet_price, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(add_pet_petLayout.createSequentialGroup()
                                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbb_add_pet_pet_breed, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(10, 10, 10))
        );
        add_pet_petLayout.setVerticalGroup(
            add_pet_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_pet_petLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel56)
                .addGap(7, 7, 7)
                .addGroup(add_pet_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_add_pet_pet_species, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_add_pet_pet_breed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74)
                    .addComponent(jLabel75))
                .addGap(20, 20, 20)
                .addGroup(add_pet_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(add_pet_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel63)
                        .addComponent(txt_add_pet_pet_age, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel65)
                    .addComponent(txt_add_pet_pet_price, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(add_pet_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(txt_add_pet_pet_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(btn_create_pet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add_pet.add(add_pet_pet, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 780, 190));

        panel_body_add.add(add_pet, "card3");

        add_item.setBackground(new java.awt.Color(255, 255, 255));
        add_item.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add_if_item.setBackground(new java.awt.Color(255, 255, 255));
        add_if_item.setPreferredSize(new java.awt.Dimension(780, 215));

        btn_add_item_create.setBackground(new java.awt.Color(255, 255, 255));
        btn_add_item_create.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_add_item_create.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_add_item_createMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_add_item_createbtn_create_petMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_add_item_createbtn_create_petMouseExited(evt);
            }
        });

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel66.setText("Created");

        javax.swing.GroupLayout btn_add_item_createLayout = new javax.swing.GroupLayout(btn_add_item_create);
        btn_add_item_create.setLayout(btn_add_item_createLayout);
        btn_add_item_createLayout.setHorizontalGroup(
            btn_add_item_createLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_add_item_createLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel66)
                .addGap(26, 26, 26))
        );
        btn_add_item_createLayout.setVerticalGroup(
            btn_add_item_createLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel47.setText("Items");

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel70.setText("Name");

        txt_add_item_item_name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_item_item_name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel73.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel73.setText("Price");

        txt_add_item_item_price.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_item_item_price.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel143.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel143.setText("Amount");

        txt_add_item_item_amount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_item_item_amount.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout add_if_itemLayout = new javax.swing.GroupLayout(add_if_item);
        add_if_item.setLayout(add_if_itemLayout);
        add_if_itemLayout.setHorizontalGroup(
            add_if_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_if_itemLayout.createSequentialGroup()
                .addGroup(add_if_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_if_itemLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_add_item_create, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(add_if_itemLayout.createSequentialGroup()
                        .addGroup(add_if_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(add_if_itemLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel47))
                            .addGroup(add_if_itemLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(add_if_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(add_if_itemLayout.createSequentialGroup()
                                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_add_item_item_price, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(add_if_itemLayout.createSequentialGroup()
                                        .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_add_item_item_name, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(65, 65, 65)
                                        .addComponent(jLabel143)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_add_item_item_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 203, Short.MAX_VALUE)))
                .addContainerGap())
        );
        add_if_itemLayout.setVerticalGroup(
            add_if_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_if_itemLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel47)
                .addGap(18, 18, 18)
                .addGroup(add_if_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(txt_add_item_item_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel143)
                    .addComponent(txt_add_item_item_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(add_if_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(txt_add_item_item_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(btn_add_item_create, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add_item.add(add_if_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 210));

        add_if_food.setBackground(new java.awt.Color(255, 255, 255));
        add_if_food.setPreferredSize(new java.awt.Dimension(780, 215));

        btn_add_food_create.setBackground(new java.awt.Color(255, 255, 255));
        btn_add_food_create.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_add_food_create.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_add_food_createMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_add_food_createbtn_create_petMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_add_food_createbtn_create_petMouseExited(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel67.setText("Created");

        javax.swing.GroupLayout btn_add_food_createLayout = new javax.swing.GroupLayout(btn_add_food_create);
        btn_add_food_create.setLayout(btn_add_food_createLayout);
        btn_add_food_createLayout.setHorizontalGroup(
            btn_add_food_createLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_add_food_createLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel67)
                .addGap(26, 26, 26))
        );
        btn_add_food_createLayout.setVerticalGroup(
            btn_add_food_createLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel68.setText("Foods");

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel69.setText("Name");

        txt_add_item_food_name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_item_food_name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel71.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel71.setText("For");

        cbb_add_item_food_for.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel72.setText("Price");

        txt_add_item_food_price.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_item_food_price.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel149.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel149.setText("Amount");

        txt_add_item_food_amount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_item_food_amount.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout add_if_foodLayout = new javax.swing.GroupLayout(add_if_food);
        add_if_food.setLayout(add_if_foodLayout);
        add_if_foodLayout.setHorizontalGroup(
            add_if_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_if_foodLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(add_if_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_if_foodLayout.createSequentialGroup()
                        .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_add_item_food_price, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 393, Short.MAX_VALUE)
                        .addComponent(btn_add_food_create, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(add_if_foodLayout.createSequentialGroup()
                        .addGroup(add_if_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(add_if_foodLayout.createSequentialGroup()
                                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbb_add_item_food_for, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(add_if_foodLayout.createSequentialGroup()
                                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_add_item_food_name, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addComponent(jLabel149)
                        .addGap(16, 16, 16)
                        .addComponent(txt_add_item_food_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(add_if_foodLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel68)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        add_if_foodLayout.setVerticalGroup(
            add_if_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_if_foodLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel68)
                .addGap(18, 18, 18)
                .addGroup(add_if_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(txt_add_item_food_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel149)
                    .addComponent(txt_add_item_food_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(add_if_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(cbb_add_item_food_for, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(add_if_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_if_foodLayout.createSequentialGroup()
                        .addComponent(btn_add_food_create, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_if_foodLayout.createSequentialGroup()
                        .addGroup(add_if_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel72)
                            .addComponent(txt_add_item_food_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))))
        );

        add_item.add(add_if_food, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 780, 220));

        panel_body_add.add(add_item, "card4");

        add_staff.setBackground(new java.awt.Color(255, 255, 255));

        jLabel79.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel79.setText("Name");

        txt_add_staff_name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_staff_name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel80.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel80.setText("Phone");

        jLabel81.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel81.setText("Email");

        txt_add_staff_phone.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_staff_phone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txt_add_staff_mail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_staff_mail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel82.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel82.setText("ID");

        txt_add_staff_id.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_staff_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel83.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel83.setText("Pass");

        txt_add_staff_pass.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_staff_pass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btn_add_staff_create.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_add_staff_create.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_add_staff_createMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_add_staff_createMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_add_staff_createMouseExited(evt);
            }
        });

        jLabel85.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel85.setText("Created");

        javax.swing.GroupLayout btn_add_staff_createLayout = new javax.swing.GroupLayout(btn_add_staff_create);
        btn_add_staff_create.setLayout(btn_add_staff_createLayout);
        btn_add_staff_createLayout.setHorizontalGroup(
            btn_add_staff_createLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_add_staff_createLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel85)
                .addGap(22, 22, 22))
        );
        btn_add_staff_createLayout.setVerticalGroup(
            btn_add_staff_createLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel85, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jLabel166.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel166.setText("Role");
        jLabel166.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel166MouseClicked(evt);
            }
        });

        cbb_add_staff_role.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        cbb_add_staff_role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mananger", "Staff" }));

        javax.swing.GroupLayout add_staffLayout = new javax.swing.GroupLayout(add_staff);
        add_staff.setLayout(add_staffLayout);
        add_staffLayout.setHorizontalGroup(
            add_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_staffLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(add_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(add_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(add_staffLayout.createSequentialGroup()
                            .addGroup(add_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel79, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(28, 28, 28)
                            .addGroup(add_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_add_staff_name, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(txt_add_staff_phone)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, add_staffLayout.createSequentialGroup()
                            .addGroup(add_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel166))
                            .addGap(28, 28, 28)
                            .addGroup(add_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_add_staff_mail)
                                .addComponent(txt_add_staff_id)
                                .addComponent(txt_add_staff_pass)
                                .addComponent(cbb_add_staff_role, 0, 250, Short.MAX_VALUE))))
                    .addComponent(jLabel83))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 335, Short.MAX_VALUE)
                .addComponent(btn_add_staff_create, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        add_staffLayout.setVerticalGroup(
            add_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_staffLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(add_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(txt_add_staff_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(add_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(txt_add_staff_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(add_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(txt_add_staff_mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(add_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(txt_add_staff_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(add_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83)
                    .addComponent(txt_add_staff_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(add_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_add_staff_role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel166))
                .addContainerGap(124, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_staffLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_add_staff_create, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel_body_add.add(add_staff, "card6");

        add_supp.setBackground(new java.awt.Color(255, 255, 255));
        add_supp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btn_add_supp_create.setBackground(new java.awt.Color(255, 255, 255));
        btn_add_supp_create.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_add_supp_create.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_add_supp_createMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_add_supp_createMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_add_supp_createMouseExited(evt);
            }
        });

        jLabel92.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel92.setText("Created");

        javax.swing.GroupLayout btn_add_supp_createLayout = new javax.swing.GroupLayout(btn_add_supp_create);
        btn_add_supp_create.setLayout(btn_add_supp_createLayout);
        btn_add_supp_createLayout.setHorizontalGroup(
            btn_add_supp_createLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_add_supp_createLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel92)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        btn_add_supp_createLayout.setVerticalGroup(
            btn_add_supp_createLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jLabel86.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel86.setText("Supplier");

        jLabel88.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel88.setText("Name");

        txt_add_supp_name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_supp_name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel89.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel89.setText("Phone");

        jLabel90.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel90.setText("Note");

        jLabel91.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel91.setText("Address");

        txt_add_supp_phone.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_supp_phone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txt_add_supp_address.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_supp_address.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txt_add_supp_note.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_add_supp_note.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel179.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel179.setText("Item");

        cbb_add_supp_item.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        cbb_add_supp_item.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pet", "đồ dùng", "thức ăn" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel86)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel179, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_add_supp_name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(jLabel91))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbb_add_supp_item, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_add_supp_phone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGap(72, 72, 72)
                        .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_add_supp_address, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_add_supp_note, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                        .addComponent(btn_add_supp_create, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel86)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88)
                    .addComponent(txt_add_supp_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_add_supp_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel91))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(jLabel90)
                    .addComponent(txt_add_supp_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_add_supp_note, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel179)
                    .addComponent(cbb_add_supp_item, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_add_supp_create, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add_supp.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 170));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel87.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel87.setText("Coming soon...");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel87)
                .addContainerGap(642, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel87)
                .addContainerGap(224, Short.MAX_VALUE))
        );

        add_supp.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 780, 260));

        panel_body_add.add(add_supp, "card7");

        panel_add.add(panel_body_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 780, 430));

        mainshow.add(panel_add, "card8");

        panel_bill.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_header_bill.setBackground(new java.awt.Color(255, 255, 255));
        panel_header_bill.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_sold.setBackground(new java.awt.Color(255, 255, 255));
        btn_sold.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_soldMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_soldMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_soldMouseExited(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setText("Sold");

        javax.swing.GroupLayout btn_soldLayout = new javax.swing.GroupLayout(btn_sold);
        btn_sold.setLayout(btn_soldLayout);
        btn_soldLayout.setHorizontalGroup(
            btn_soldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
            .addGroup(btn_soldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_soldLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel36)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btn_soldLayout.setVerticalGroup(
            btn_soldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(btn_soldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_soldLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel_header_bill.add(btn_sold, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 90, 30));

        btn_import.setBackground(new java.awt.Color(255, 255, 255));
        btn_import.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_soldMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_soldMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_soldMouseExited(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setText("Import");

        javax.swing.GroupLayout btn_importLayout = new javax.swing.GroupLayout(btn_import);
        btn_import.setLayout(btn_importLayout);
        btn_importLayout.setHorizontalGroup(
            btn_importLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
            .addGroup(btn_importLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_importLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel37)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btn_importLayout.setVerticalGroup(
            btn_importLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(btn_importLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btn_importLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panel_header_bill.add(btn_import, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 90, 30));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel27.setText("BILL");
        panel_header_bill.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, -1));

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel51.setText("Place for our specified transactions");
        panel_header_bill.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, -1));

        panel_bill.add(panel_header_bill, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 90));

        panel_table_bill.setLayout(new java.awt.CardLayout());

        table_bill_sold.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_bill_sold.setModel(new javax.swing.table.DefaultTableModel(
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
        table_bill_sold.setGridColor(new java.awt.Color(255, 255, 255));
        table_bill_sold.setSelectionBackground(new java.awt.Color(110, 89, 222));
        jScrollPane7.setViewportView(table_bill_sold);

        javax.swing.GroupLayout bill_soldLayout = new javax.swing.GroupLayout(bill_sold);
        bill_sold.setLayout(bill_soldLayout);
        bill_soldLayout.setHorizontalGroup(
            bill_soldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );
        bill_soldLayout.setVerticalGroup(
            bill_soldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );

        panel_table_bill.add(bill_sold, "card3");

        table_bill_import.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_bill_import.setModel(new javax.swing.table.DefaultTableModel(
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
        table_bill_import.setGridColor(new java.awt.Color(255, 255, 255));
        table_bill_import.setSelectionBackground(new java.awt.Color(110, 89, 222));
        ffff.setViewportView(table_bill_import);

        javax.swing.GroupLayout bill_importLayout = new javax.swing.GroupLayout(bill_import);
        bill_import.setLayout(bill_importLayout);
        bill_importLayout.setHorizontalGroup(
            bill_importLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ffff, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );
        bill_importLayout.setVerticalGroup(
            bill_importLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ffff, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );

        panel_table_bill.add(bill_import, "card3");

        panel_bill.add(panel_table_bill, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 780, 430));

        mainshow.add(panel_bill, "card6");

        panel_remove.setBackground(new java.awt.Color(255, 255, 255));
        panel_remove.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_header_remove.setBackground(new java.awt.Color(255, 255, 255));
        panel_header_remove.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_remove_bill.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_bill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseExited(evt);
            }
        });

        jLabel93.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel93.setText("Bill");

        javax.swing.GroupLayout btn_remove_billLayout = new javax.swing.GroupLayout(btn_remove_bill);
        btn_remove_bill.setLayout(btn_remove_billLayout);
        btn_remove_billLayout.setHorizontalGroup(
            btn_remove_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_billLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel93)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        btn_remove_billLayout.setVerticalGroup(
            btn_remove_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel93, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panel_header_remove.add(btn_remove_bill, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 90, 30));

        btn_remove_pet.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_pet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseExited(evt);
            }
        });

        jLabel96.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel96.setText("Pet");

        javax.swing.GroupLayout btn_remove_petLayout = new javax.swing.GroupLayout(btn_remove_pet);
        btn_remove_pet.setLayout(btn_remove_petLayout);
        btn_remove_petLayout.setHorizontalGroup(
            btn_remove_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_petLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel96)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        btn_remove_petLayout.setVerticalGroup(
            btn_remove_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel96, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panel_header_remove.add(btn_remove_pet, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 90, 30));

        btn_remove_item.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseExited(evt);
            }
        });

        jLabel98.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel98.setText("Items");

        javax.swing.GroupLayout btn_remove_itemLayout = new javax.swing.GroupLayout(btn_remove_item);
        btn_remove_item.setLayout(btn_remove_itemLayout);
        btn_remove_itemLayout.setHorizontalGroup(
            btn_remove_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_itemLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel98)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        btn_remove_itemLayout.setVerticalGroup(
            btn_remove_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel98, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panel_header_remove.add(btn_remove_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 90, 30));

        btn_remove_food.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_food.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseExited(evt);
            }
        });

        jLabel105.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel105.setText("Food");

        javax.swing.GroupLayout btn_remove_foodLayout = new javax.swing.GroupLayout(btn_remove_food);
        btn_remove_food.setLayout(btn_remove_foodLayout);
        btn_remove_foodLayout.setHorizontalGroup(
            btn_remove_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_foodLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel105)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        btn_remove_foodLayout.setVerticalGroup(
            btn_remove_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel105, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panel_header_remove.add(btn_remove_food, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 90, 30));

        btn_remove_staff.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_staff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseExited(evt);
            }
        });

        jLabel109.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel109.setText("Staff");

        javax.swing.GroupLayout btn_remove_staffLayout = new javax.swing.GroupLayout(btn_remove_staff);
        btn_remove_staff.setLayout(btn_remove_staffLayout);
        btn_remove_staffLayout.setHorizontalGroup(
            btn_remove_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_staffLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel109)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        btn_remove_staffLayout.setVerticalGroup(
            btn_remove_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panel_header_remove.add(btn_remove_staff, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 90, 30));

        btn_remove_sup.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_sup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_billMouseExited(evt);
            }
        });

        jLabel113.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel113.setText("Supplier");

        javax.swing.GroupLayout btn_remove_supLayout = new javax.swing.GroupLayout(btn_remove_sup);
        btn_remove_sup.setLayout(btn_remove_supLayout);
        btn_remove_supLayout.setHorizontalGroup(
            btn_remove_supLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_supLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel113)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        btn_remove_supLayout.setVerticalGroup(
            btn_remove_supLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel113, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panel_header_remove.add(btn_remove_sup, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 90, 30));

        jLabel190.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel190.setText("REMOVE");
        panel_header_remove.add(jLabel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        jLabel191.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel191.setText("Where mistakes and sadness erase");
        panel_header_remove.add(jLabel191, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, -1));

        panel_remove.add(panel_header_remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 90));

        panel_body_remove.setBackground(new java.awt.Color(255, 255, 255));
        panel_body_remove.setLayout(new java.awt.CardLayout());

        remove_pet.setBackground(new java.awt.Color(255, 255, 255));

        table_remove_pet.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_remove_pet.setModel(new javax.swing.table.DefaultTableModel(
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
        table_remove_pet.setGridColor(new java.awt.Color(255, 255, 255));
        table_remove_pet.setSelectionBackground(new java.awt.Color(110, 89, 222));
        table_remove_pet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_remove_petMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(table_remove_pet);

        jLabel94.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel94.setText("Srearch");

        txt_remove_pet_search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_remove_pet_search.setToolTipText("");
        txt_remove_pet_search.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btn_remove_pet_sreach.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_pet_sreach.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_remove_pet_sreach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_pet_sreachMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_pet_sreachMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_pet_sreachMouseExited(evt);
            }
        });

        jLabel95.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel95.setText("Sreach");

        javax.swing.GroupLayout btn_remove_pet_sreachLayout = new javax.swing.GroupLayout(btn_remove_pet_sreach);
        btn_remove_pet_sreach.setLayout(btn_remove_pet_sreachLayout);
        btn_remove_pet_sreachLayout.setHorizontalGroup(
            btn_remove_pet_sreachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_pet_sreachLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel95)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_pet_sreachLayout.setVerticalGroup(
            btn_remove_pet_sreachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        btn_remove_pet_remove.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_pet_remove.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_remove_pet_remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_pet_removeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_pet_sreachMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_pet_sreachMouseExited(evt);
            }
        });

        jLabel97.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel97.setText("Remove");

        javax.swing.GroupLayout btn_remove_pet_removeLayout = new javax.swing.GroupLayout(btn_remove_pet_remove);
        btn_remove_pet_remove.setLayout(btn_remove_pet_removeLayout);
        btn_remove_pet_removeLayout.setHorizontalGroup(
            btn_remove_pet_removeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_pet_removeLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel97)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_pet_removeLayout.setVerticalGroup(
            btn_remove_pet_removeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel97, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout remove_petLayout = new javax.swing.GroupLayout(remove_pet);
        remove_pet.setLayout(remove_petLayout);
        remove_petLayout.setHorizontalGroup(
            remove_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remove_petLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel94)
                .addGap(10, 10, 10)
                .addComponent(txt_remove_pet_search, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_remove_pet_sreach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_remove_pet_remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(remove_petLayout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        remove_petLayout.setVerticalGroup(
            remove_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remove_petLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(remove_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(remove_petLayout.createSequentialGroup()
                        .addGroup(remove_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_remove_pet_sreach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_remove_pet_remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, remove_petLayout.createSequentialGroup()
                        .addGroup(remove_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel94)
                            .addComponent(txt_remove_pet_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
        );

        panel_body_remove.add(remove_pet, "card2");

        remove_item.setBackground(new java.awt.Color(255, 255, 255));

        table_remove_item.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_remove_item.setModel(new javax.swing.table.DefaultTableModel(
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
        table_remove_item.setGridColor(new java.awt.Color(255, 255, 255));
        table_remove_item.setSelectionBackground(new java.awt.Color(110, 89, 222));
        table_remove_item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_remove_itemMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(table_remove_item);

        jLabel101.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel101.setText("Srearch");

        txt_remove_item_search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_remove_item_search.setToolTipText("");
        txt_remove_item_search.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btn_remove_item_sreach.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_item_sreach.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_remove_item_sreach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_item_sreachMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_item_sreachMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_item_sreachMouseExited(evt);
            }
        });

        jLabel102.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel102.setText("Sreach");

        javax.swing.GroupLayout btn_remove_item_sreachLayout = new javax.swing.GroupLayout(btn_remove_item_sreach);
        btn_remove_item_sreach.setLayout(btn_remove_item_sreachLayout);
        btn_remove_item_sreachLayout.setHorizontalGroup(
            btn_remove_item_sreachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_item_sreachLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel102)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_item_sreachLayout.setVerticalGroup(
            btn_remove_item_sreachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel102, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        btn_remove_item_remove.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_item_remove.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_remove_item_remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_item_removeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_item_sreachMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_item_sreachMouseExited(evt);
            }
        });

        jLabel103.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel103.setText("Remove");

        javax.swing.GroupLayout btn_remove_item_removeLayout = new javax.swing.GroupLayout(btn_remove_item_remove);
        btn_remove_item_remove.setLayout(btn_remove_item_removeLayout);
        btn_remove_item_removeLayout.setHorizontalGroup(
            btn_remove_item_removeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_item_removeLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel103)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_item_removeLayout.setVerticalGroup(
            btn_remove_item_removeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel103, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout remove_itemLayout = new javax.swing.GroupLayout(remove_item);
        remove_item.setLayout(remove_itemLayout);
        remove_itemLayout.setHorizontalGroup(
            remove_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remove_itemLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel101)
                .addGap(10, 10, 10)
                .addComponent(txt_remove_item_search, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_remove_item_sreach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_remove_item_remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(remove_itemLayout.createSequentialGroup()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        remove_itemLayout.setVerticalGroup(
            remove_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remove_itemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(remove_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(remove_itemLayout.createSequentialGroup()
                        .addGroup(remove_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_remove_item_sreach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_remove_item_remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, remove_itemLayout.createSequentialGroup()
                        .addGroup(remove_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel101)
                            .addComponent(txt_remove_item_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
        );

        panel_body_remove.add(remove_item, "card2");

        remove_food.setBackground(new java.awt.Color(255, 255, 255));

        table_remove_food.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_remove_food.setModel(new javax.swing.table.DefaultTableModel(
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
        table_remove_food.setGridColor(new java.awt.Color(255, 255, 255));
        table_remove_food.setSelectionBackground(new java.awt.Color(110, 89, 222));
        table_remove_food.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_remove_foodMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(table_remove_food);

        jLabel99.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel99.setText("Srearch");

        txt_remove_food_search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_remove_food_search.setToolTipText("");
        txt_remove_food_search.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btn_remove_food_sreach.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_food_sreach.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_remove_food_sreach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_food_sreachMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_food_sreachMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_food_sreachMouseExited(evt);
            }
        });

        jLabel100.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel100.setText("Sreach");

        javax.swing.GroupLayout btn_remove_food_sreachLayout = new javax.swing.GroupLayout(btn_remove_food_sreach);
        btn_remove_food_sreach.setLayout(btn_remove_food_sreachLayout);
        btn_remove_food_sreachLayout.setHorizontalGroup(
            btn_remove_food_sreachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_food_sreachLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel100)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_food_sreachLayout.setVerticalGroup(
            btn_remove_food_sreachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel100, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        btn_remove_food_remove.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_food_remove.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_remove_food_remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_food_removeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_food_sreachMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_food_sreachMouseExited(evt);
            }
        });

        jLabel104.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel104.setText("Remove");

        javax.swing.GroupLayout btn_remove_food_removeLayout = new javax.swing.GroupLayout(btn_remove_food_remove);
        btn_remove_food_remove.setLayout(btn_remove_food_removeLayout);
        btn_remove_food_removeLayout.setHorizontalGroup(
            btn_remove_food_removeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_food_removeLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel104)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_food_removeLayout.setVerticalGroup(
            btn_remove_food_removeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel104, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout remove_foodLayout = new javax.swing.GroupLayout(remove_food);
        remove_food.setLayout(remove_foodLayout);
        remove_foodLayout.setHorizontalGroup(
            remove_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remove_foodLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel99)
                .addGap(10, 10, 10)
                .addComponent(txt_remove_food_search, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_remove_food_sreach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_remove_food_remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(remove_foodLayout.createSequentialGroup()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        remove_foodLayout.setVerticalGroup(
            remove_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remove_foodLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(remove_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(remove_foodLayout.createSequentialGroup()
                        .addGroup(remove_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_remove_food_sreach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_remove_food_remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, remove_foodLayout.createSequentialGroup()
                        .addGroup(remove_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel99)
                            .addComponent(txt_remove_food_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
        );

        panel_body_remove.add(remove_food, "card2");

        remove_staff.setBackground(new java.awt.Color(255, 255, 255));

        table_remove_staff.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_remove_staff.setModel(new javax.swing.table.DefaultTableModel(
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
        table_remove_staff.setGridColor(new java.awt.Color(255, 255, 255));
        table_remove_staff.setSelectionBackground(new java.awt.Color(110, 89, 222));
        table_remove_staff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_remove_staffMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(table_remove_staff);

        jLabel106.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel106.setText("Srearch");

        txt_remove_staff_search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_remove_staff_search.setToolTipText("");
        txt_remove_staff_search.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btn_remove_staff_sreach.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_staff_sreach.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_remove_staff_sreach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_staff_sreachMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_staff_sreachMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_staff_sreachMouseExited(evt);
            }
        });

        jLabel107.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel107.setText("Sreach");

        javax.swing.GroupLayout btn_remove_staff_sreachLayout = new javax.swing.GroupLayout(btn_remove_staff_sreach);
        btn_remove_staff_sreach.setLayout(btn_remove_staff_sreachLayout);
        btn_remove_staff_sreachLayout.setHorizontalGroup(
            btn_remove_staff_sreachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_staff_sreachLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel107)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_staff_sreachLayout.setVerticalGroup(
            btn_remove_staff_sreachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel107, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        btn_remove_staff_remove.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_staff_remove.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_remove_staff_remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_staff_removeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_staff_sreachMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_staff_sreachMouseExited(evt);
            }
        });

        jLabel108.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel108.setText("Remove");

        javax.swing.GroupLayout btn_remove_staff_removeLayout = new javax.swing.GroupLayout(btn_remove_staff_remove);
        btn_remove_staff_remove.setLayout(btn_remove_staff_removeLayout);
        btn_remove_staff_removeLayout.setHorizontalGroup(
            btn_remove_staff_removeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_staff_removeLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel108)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_staff_removeLayout.setVerticalGroup(
            btn_remove_staff_removeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel108, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout remove_staffLayout = new javax.swing.GroupLayout(remove_staff);
        remove_staff.setLayout(remove_staffLayout);
        remove_staffLayout.setHorizontalGroup(
            remove_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remove_staffLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel106)
                .addGap(10, 10, 10)
                .addComponent(txt_remove_staff_search, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_remove_staff_sreach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_remove_staff_remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(remove_staffLayout.createSequentialGroup()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        remove_staffLayout.setVerticalGroup(
            remove_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remove_staffLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(remove_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(remove_staffLayout.createSequentialGroup()
                        .addGroup(remove_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_remove_staff_sreach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_remove_staff_remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, remove_staffLayout.createSequentialGroup()
                        .addGroup(remove_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel106)
                            .addComponent(txt_remove_staff_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
        );

        panel_body_remove.add(remove_staff, "card2");

        remove_supp.setBackground(new java.awt.Color(255, 255, 255));

        table_remove_supp.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_remove_supp.setModel(new javax.swing.table.DefaultTableModel(
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
        table_remove_supp.setGridColor(new java.awt.Color(255, 255, 255));
        table_remove_supp.setSelectionBackground(new java.awt.Color(110, 89, 222));
        table_remove_supp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_remove_suppMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(table_remove_supp);

        jLabel110.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel110.setText("Srearch");

        txt_remove_supp_search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_remove_supp_search.setToolTipText("");
        txt_remove_supp_search.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btn_remove_supp_sreach.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_supp_sreach.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_remove_supp_sreach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_supp_sreachMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_supp_sreachMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_supp_sreachMouseExited(evt);
            }
        });

        jLabel111.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel111.setText("Sreach");

        javax.swing.GroupLayout btn_remove_supp_sreachLayout = new javax.swing.GroupLayout(btn_remove_supp_sreach);
        btn_remove_supp_sreach.setLayout(btn_remove_supp_sreachLayout);
        btn_remove_supp_sreachLayout.setHorizontalGroup(
            btn_remove_supp_sreachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_supp_sreachLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel111)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_supp_sreachLayout.setVerticalGroup(
            btn_remove_supp_sreachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        btn_remove_supp_remove.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_supp_remove.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_remove_supp_remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_supp_removeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_supp_sreachMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_supp_sreachMouseExited(evt);
            }
        });

        jLabel112.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel112.setText("Remove");

        javax.swing.GroupLayout btn_remove_supp_removeLayout = new javax.swing.GroupLayout(btn_remove_supp_remove);
        btn_remove_supp_remove.setLayout(btn_remove_supp_removeLayout);
        btn_remove_supp_removeLayout.setHorizontalGroup(
            btn_remove_supp_removeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_supp_removeLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel112)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_supp_removeLayout.setVerticalGroup(
            btn_remove_supp_removeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel112, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout remove_suppLayout = new javax.swing.GroupLayout(remove_supp);
        remove_supp.setLayout(remove_suppLayout);
        remove_suppLayout.setHorizontalGroup(
            remove_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remove_suppLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel110)
                .addGap(10, 10, 10)
                .addComponent(txt_remove_supp_search, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_remove_supp_sreach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_remove_supp_remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(remove_suppLayout.createSequentialGroup()
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        remove_suppLayout.setVerticalGroup(
            remove_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remove_suppLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(remove_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(remove_suppLayout.createSequentialGroup()
                        .addGroup(remove_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_remove_supp_sreach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_remove_supp_remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, remove_suppLayout.createSequentialGroup()
                        .addGroup(remove_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel110)
                            .addComponent(txt_remove_supp_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
        );

        panel_body_remove.add(remove_supp, "card2");

        remove_bill.setBackground(new java.awt.Color(255, 255, 255));

        table_remove_bill.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_remove_bill.setModel(new javax.swing.table.DefaultTableModel(
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
        table_remove_bill.setGridColor(new java.awt.Color(255, 255, 255));
        table_remove_bill.setSelectionBackground(new java.awt.Color(110, 89, 222));
        table_remove_bill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_remove_billMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(table_remove_bill);

        jLabel114.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel114.setText("Srearch");

        txt_remove_bill_search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_remove_bill_search.setToolTipText("");
        txt_remove_bill_search.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btn_remove_bill_search.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_bill_search.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_remove_bill_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_bill_searchMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_bill_searchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_bill_searchMouseExited(evt);
            }
        });

        jLabel115.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel115.setText("Sreach");

        javax.swing.GroupLayout btn_remove_bill_searchLayout = new javax.swing.GroupLayout(btn_remove_bill_search);
        btn_remove_bill_search.setLayout(btn_remove_bill_searchLayout);
        btn_remove_bill_searchLayout.setHorizontalGroup(
            btn_remove_bill_searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_bill_searchLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel115)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_bill_searchLayout.setVerticalGroup(
            btn_remove_bill_searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel115, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        btn_remove_bill_remove.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_bill_remove.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_remove_bill_remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_remove_bill_removeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_remove_bill_searchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_remove_bill_searchMouseExited(evt);
            }
        });

        jLabel116.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel116.setText("Remove");

        javax.swing.GroupLayout btn_remove_bill_removeLayout = new javax.swing.GroupLayout(btn_remove_bill_remove);
        btn_remove_bill_remove.setLayout(btn_remove_bill_removeLayout);
        btn_remove_bill_removeLayout.setHorizontalGroup(
            btn_remove_bill_removeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_bill_removeLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel116)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_bill_removeLayout.setVerticalGroup(
            btn_remove_bill_removeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel116, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout remove_billLayout = new javax.swing.GroupLayout(remove_bill);
        remove_bill.setLayout(remove_billLayout);
        remove_billLayout.setHorizontalGroup(
            remove_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remove_billLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel114)
                .addGap(10, 10, 10)
                .addComponent(txt_remove_bill_search, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_remove_bill_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_remove_bill_remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(remove_billLayout.createSequentialGroup()
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        remove_billLayout.setVerticalGroup(
            remove_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remove_billLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(remove_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(remove_billLayout.createSequentialGroup()
                        .addGroup(remove_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_remove_bill_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_remove_bill_remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, remove_billLayout.createSequentialGroup()
                        .addGroup(remove_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel114)
                            .addComponent(txt_remove_bill_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
        );

        panel_body_remove.add(remove_bill, "card2");

        panel_remove.add(panel_body_remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 780, 430));

        mainshow.add(panel_remove, "card9");

        panel_edit.setBackground(new java.awt.Color(255, 255, 255));
        panel_edit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_header_edit.setBackground(new java.awt.Color(255, 255, 255));
        panel_header_edit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_edit_bill.setBackground(new java.awt.Color(255, 255, 255));

        jLabel119.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel119.setText("Bill");

        javax.swing.GroupLayout btn_edit_billLayout = new javax.swing.GroupLayout(btn_edit_bill);
        btn_edit_bill.setLayout(btn_edit_billLayout);
        btn_edit_billLayout.setHorizontalGroup(
            btn_edit_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_edit_billLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel119)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        btn_edit_billLayout.setVerticalGroup(
            btn_edit_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel119, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panel_header_edit.add(btn_edit_bill, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 90, 30));

        btn_edit_pet.setBackground(new java.awt.Color(255, 255, 255));

        jLabel120.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel120.setText("Pet");

        javax.swing.GroupLayout btn_edit_petLayout = new javax.swing.GroupLayout(btn_edit_pet);
        btn_edit_pet.setLayout(btn_edit_petLayout);
        btn_edit_petLayout.setHorizontalGroup(
            btn_edit_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_edit_petLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel120)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        btn_edit_petLayout.setVerticalGroup(
            btn_edit_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel120, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panel_header_edit.add(btn_edit_pet, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 90, 30));

        btn_edit_item.setBackground(new java.awt.Color(255, 255, 255));

        jLabel121.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel121.setText("Items");

        javax.swing.GroupLayout btn_edit_itemLayout = new javax.swing.GroupLayout(btn_edit_item);
        btn_edit_item.setLayout(btn_edit_itemLayout);
        btn_edit_itemLayout.setHorizontalGroup(
            btn_edit_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_edit_itemLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel121)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        btn_edit_itemLayout.setVerticalGroup(
            btn_edit_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel121, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panel_header_edit.add(btn_edit_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 90, 30));

        btn_edit_food.setBackground(new java.awt.Color(255, 255, 255));

        jLabel122.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel122.setText("Food");

        javax.swing.GroupLayout btn_edit_foodLayout = new javax.swing.GroupLayout(btn_edit_food);
        btn_edit_food.setLayout(btn_edit_foodLayout);
        btn_edit_foodLayout.setHorizontalGroup(
            btn_edit_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_edit_foodLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel122)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        btn_edit_foodLayout.setVerticalGroup(
            btn_edit_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel122, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panel_header_edit.add(btn_edit_food, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 90, 30));

        btn_edit_staff.setBackground(new java.awt.Color(255, 255, 255));

        jLabel123.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel123.setText("Staff");

        javax.swing.GroupLayout btn_edit_staffLayout = new javax.swing.GroupLayout(btn_edit_staff);
        btn_edit_staff.setLayout(btn_edit_staffLayout);
        btn_edit_staffLayout.setHorizontalGroup(
            btn_edit_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_edit_staffLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel123)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        btn_edit_staffLayout.setVerticalGroup(
            btn_edit_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel123, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panel_header_edit.add(btn_edit_staff, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 90, 30));

        btn_edit_sup.setBackground(new java.awt.Color(255, 255, 255));

        jLabel124.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel124.setText("Supplier");

        javax.swing.GroupLayout btn_edit_supLayout = new javax.swing.GroupLayout(btn_edit_sup);
        btn_edit_sup.setLayout(btn_edit_supLayout);
        btn_edit_supLayout.setHorizontalGroup(
            btn_edit_supLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_edit_supLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel124)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        btn_edit_supLayout.setVerticalGroup(
            btn_edit_supLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel124, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panel_header_edit.add(btn_edit_sup, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 90, 30));

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel58.setText("REMOVE");
        panel_header_edit.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel59.setText("Where mistakes and sadness erase");
        panel_header_edit.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, -1));

        panel_edit.add(panel_header_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 90));

        panel_body_edit.setBackground(new java.awt.Color(255, 255, 255));
        panel_body_edit.setLayout(new java.awt.CardLayout());

        edit_pet.setBackground(new java.awt.Color(255, 255, 255));

        jTable18.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jTable18.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable18.setGridColor(new java.awt.Color(255, 255, 255));
        jTable18.setSelectionBackground(new java.awt.Color(110, 89, 222));
        jScrollPane19.setViewportView(jTable18);

        jLabel125.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel125.setText("Srearch");

        jTextField39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField39.setToolTipText("");
        jTextField39.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btn_remove_pet_sreach1.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_pet_sreach1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));

        jLabel126.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel126.setText("Sreach");

        javax.swing.GroupLayout btn_remove_pet_sreach1Layout = new javax.swing.GroupLayout(btn_remove_pet_sreach1);
        btn_remove_pet_sreach1.setLayout(btn_remove_pet_sreach1Layout);
        btn_remove_pet_sreach1Layout.setHorizontalGroup(
            btn_remove_pet_sreach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_pet_sreach1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel126)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_pet_sreach1Layout.setVerticalGroup(
            btn_remove_pet_sreach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel126, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        btn_remove_pet_remove1.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_pet_remove1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));

        jLabel127.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel127.setText("Remove");

        javax.swing.GroupLayout btn_remove_pet_remove1Layout = new javax.swing.GroupLayout(btn_remove_pet_remove1);
        btn_remove_pet_remove1.setLayout(btn_remove_pet_remove1Layout);
        btn_remove_pet_remove1Layout.setHorizontalGroup(
            btn_remove_pet_remove1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_pet_remove1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel127)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_pet_remove1Layout.setVerticalGroup(
            btn_remove_pet_remove1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel127, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout edit_petLayout = new javax.swing.GroupLayout(edit_pet);
        edit_pet.setLayout(edit_petLayout);
        edit_petLayout.setHorizontalGroup(
            edit_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit_petLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel125)
                .addGap(10, 10, 10)
                .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_remove_pet_sreach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_remove_pet_remove1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(edit_petLayout.createSequentialGroup()
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        edit_petLayout.setVerticalGroup(
            edit_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit_petLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(edit_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edit_petLayout.createSequentialGroup()
                        .addGroup(edit_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_remove_pet_sreach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_remove_pet_remove1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edit_petLayout.createSequentialGroup()
                        .addGroup(edit_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel125)
                            .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
        );

        panel_body_edit.add(edit_pet, "card2");

        edit_item.setBackground(new java.awt.Color(255, 255, 255));

        jTable19.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jTable19.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable19.setGridColor(new java.awt.Color(255, 255, 255));
        jTable19.setSelectionBackground(new java.awt.Color(110, 89, 222));
        jScrollPane20.setViewportView(jTable19);

        jLabel128.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel128.setText("Srearch");

        jTextField40.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField40.setToolTipText("");
        jTextField40.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btn_remove_item_sreach1.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_item_sreach1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));

        jLabel129.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel129.setText("Sreach");

        javax.swing.GroupLayout btn_remove_item_sreach1Layout = new javax.swing.GroupLayout(btn_remove_item_sreach1);
        btn_remove_item_sreach1.setLayout(btn_remove_item_sreach1Layout);
        btn_remove_item_sreach1Layout.setHorizontalGroup(
            btn_remove_item_sreach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_item_sreach1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel129)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_item_sreach1Layout.setVerticalGroup(
            btn_remove_item_sreach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel129, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        btn_remove_item_remove1.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_item_remove1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));

        jLabel130.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel130.setText("Remove");

        javax.swing.GroupLayout btn_remove_item_remove1Layout = new javax.swing.GroupLayout(btn_remove_item_remove1);
        btn_remove_item_remove1.setLayout(btn_remove_item_remove1Layout);
        btn_remove_item_remove1Layout.setHorizontalGroup(
            btn_remove_item_remove1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_item_remove1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel130)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_item_remove1Layout.setVerticalGroup(
            btn_remove_item_remove1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel130, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout edit_itemLayout = new javax.swing.GroupLayout(edit_item);
        edit_item.setLayout(edit_itemLayout);
        edit_itemLayout.setHorizontalGroup(
            edit_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit_itemLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel128)
                .addGap(10, 10, 10)
                .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_remove_item_sreach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_remove_item_remove1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(edit_itemLayout.createSequentialGroup()
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        edit_itemLayout.setVerticalGroup(
            edit_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit_itemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(edit_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edit_itemLayout.createSequentialGroup()
                        .addGroup(edit_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_remove_item_sreach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_remove_item_remove1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edit_itemLayout.createSequentialGroup()
                        .addGroup(edit_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel128)
                            .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
        );

        panel_body_edit.add(edit_item, "card2");

        edit_food.setBackground(new java.awt.Color(255, 255, 255));

        jTable20.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jTable20.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable20.setGridColor(new java.awt.Color(255, 255, 255));
        jTable20.setSelectionBackground(new java.awt.Color(110, 89, 222));
        jScrollPane21.setViewportView(jTable20);

        jLabel131.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel131.setText("Srearch");

        jTextField41.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField41.setToolTipText("");
        jTextField41.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btn_remove_food_sreach1.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_food_sreach1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));

        jLabel132.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel132.setText("Sreach");

        javax.swing.GroupLayout btn_remove_food_sreach1Layout = new javax.swing.GroupLayout(btn_remove_food_sreach1);
        btn_remove_food_sreach1.setLayout(btn_remove_food_sreach1Layout);
        btn_remove_food_sreach1Layout.setHorizontalGroup(
            btn_remove_food_sreach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_food_sreach1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel132)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_food_sreach1Layout.setVerticalGroup(
            btn_remove_food_sreach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel132, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        btn_remove_food_remove1.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_food_remove1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));

        jLabel133.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel133.setText("Remove");

        javax.swing.GroupLayout btn_remove_food_remove1Layout = new javax.swing.GroupLayout(btn_remove_food_remove1);
        btn_remove_food_remove1.setLayout(btn_remove_food_remove1Layout);
        btn_remove_food_remove1Layout.setHorizontalGroup(
            btn_remove_food_remove1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_food_remove1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel133)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_food_remove1Layout.setVerticalGroup(
            btn_remove_food_remove1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel133, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout edit_foodLayout = new javax.swing.GroupLayout(edit_food);
        edit_food.setLayout(edit_foodLayout);
        edit_foodLayout.setHorizontalGroup(
            edit_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit_foodLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel131)
                .addGap(10, 10, 10)
                .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_remove_food_sreach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_remove_food_remove1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(edit_foodLayout.createSequentialGroup()
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        edit_foodLayout.setVerticalGroup(
            edit_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit_foodLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(edit_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edit_foodLayout.createSequentialGroup()
                        .addGroup(edit_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_remove_food_sreach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_remove_food_remove1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edit_foodLayout.createSequentialGroup()
                        .addGroup(edit_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel131)
                            .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
        );

        panel_body_edit.add(edit_food, "card2");

        edit_staff.setBackground(new java.awt.Color(255, 255, 255));

        jTable21.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jTable21.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable21.setGridColor(new java.awt.Color(255, 255, 255));
        jTable21.setSelectionBackground(new java.awt.Color(110, 89, 222));
        jScrollPane22.setViewportView(jTable21);

        jLabel134.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel134.setText("Srearch");

        jTextField42.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField42.setToolTipText("");
        jTextField42.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btn_remove_staff_sreach1.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_staff_sreach1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));

        jLabel135.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel135.setText("Sreach");

        javax.swing.GroupLayout btn_remove_staff_sreach1Layout = new javax.swing.GroupLayout(btn_remove_staff_sreach1);
        btn_remove_staff_sreach1.setLayout(btn_remove_staff_sreach1Layout);
        btn_remove_staff_sreach1Layout.setHorizontalGroup(
            btn_remove_staff_sreach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_staff_sreach1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel135)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_staff_sreach1Layout.setVerticalGroup(
            btn_remove_staff_sreach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel135, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        btn_remove_staff_remove1.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_staff_remove1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));

        jLabel136.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel136.setText("Remove");

        javax.swing.GroupLayout btn_remove_staff_remove1Layout = new javax.swing.GroupLayout(btn_remove_staff_remove1);
        btn_remove_staff_remove1.setLayout(btn_remove_staff_remove1Layout);
        btn_remove_staff_remove1Layout.setHorizontalGroup(
            btn_remove_staff_remove1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_staff_remove1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel136)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_staff_remove1Layout.setVerticalGroup(
            btn_remove_staff_remove1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel136, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout edit_staffLayout = new javax.swing.GroupLayout(edit_staff);
        edit_staff.setLayout(edit_staffLayout);
        edit_staffLayout.setHorizontalGroup(
            edit_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit_staffLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel134)
                .addGap(10, 10, 10)
                .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_remove_staff_sreach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_remove_staff_remove1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(edit_staffLayout.createSequentialGroup()
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        edit_staffLayout.setVerticalGroup(
            edit_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit_staffLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(edit_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edit_staffLayout.createSequentialGroup()
                        .addGroup(edit_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_remove_staff_sreach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_remove_staff_remove1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edit_staffLayout.createSequentialGroup()
                        .addGroup(edit_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel134)
                            .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
        );

        panel_body_edit.add(edit_staff, "card2");

        edit_supp.setBackground(new java.awt.Color(255, 255, 255));

        jTable22.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jTable22.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable22.setGridColor(new java.awt.Color(255, 255, 255));
        jTable22.setSelectionBackground(new java.awt.Color(110, 89, 222));
        jScrollPane23.setViewportView(jTable22);

        jLabel137.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel137.setText("Srearch");

        jTextField43.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField43.setToolTipText("");
        jTextField43.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btn_remove_supp_sreach1.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_supp_sreach1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));

        jLabel138.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel138.setText("Sreach");

        javax.swing.GroupLayout btn_remove_supp_sreach1Layout = new javax.swing.GroupLayout(btn_remove_supp_sreach1);
        btn_remove_supp_sreach1.setLayout(btn_remove_supp_sreach1Layout);
        btn_remove_supp_sreach1Layout.setHorizontalGroup(
            btn_remove_supp_sreach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_supp_sreach1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel138)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_supp_sreach1Layout.setVerticalGroup(
            btn_remove_supp_sreach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel138, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        btn_remove_supp_remove1.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_supp_remove1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));

        jLabel139.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel139.setText("Remove");

        javax.swing.GroupLayout btn_remove_supp_remove1Layout = new javax.swing.GroupLayout(btn_remove_supp_remove1);
        btn_remove_supp_remove1.setLayout(btn_remove_supp_remove1Layout);
        btn_remove_supp_remove1Layout.setHorizontalGroup(
            btn_remove_supp_remove1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_supp_remove1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel139)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_supp_remove1Layout.setVerticalGroup(
            btn_remove_supp_remove1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel139, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout edit_suppLayout = new javax.swing.GroupLayout(edit_supp);
        edit_supp.setLayout(edit_suppLayout);
        edit_suppLayout.setHorizontalGroup(
            edit_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit_suppLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel137)
                .addGap(10, 10, 10)
                .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_remove_supp_sreach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_remove_supp_remove1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(edit_suppLayout.createSequentialGroup()
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        edit_suppLayout.setVerticalGroup(
            edit_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit_suppLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(edit_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edit_suppLayout.createSequentialGroup()
                        .addGroup(edit_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_remove_supp_sreach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_remove_supp_remove1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edit_suppLayout.createSequentialGroup()
                        .addGroup(edit_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel137)
                            .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
        );

        panel_body_edit.add(edit_supp, "card2");

        edit_bill.setBackground(new java.awt.Color(255, 255, 255));

        jTable23.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jTable23.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable23.setGridColor(new java.awt.Color(255, 255, 255));
        jTable23.setSelectionBackground(new java.awt.Color(110, 89, 222));
        jScrollPane24.setViewportView(jTable23);

        jLabel140.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel140.setText("Srearch");

        jTextField44.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField44.setToolTipText("");
        jTextField44.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btn_remove_bill_search1.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_bill_search1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));

        jLabel141.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel141.setText("Sreach");

        javax.swing.GroupLayout btn_remove_bill_search1Layout = new javax.swing.GroupLayout(btn_remove_bill_search1);
        btn_remove_bill_search1.setLayout(btn_remove_bill_search1Layout);
        btn_remove_bill_search1Layout.setHorizontalGroup(
            btn_remove_bill_search1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_bill_search1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel141)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_bill_search1Layout.setVerticalGroup(
            btn_remove_bill_search1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel141, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        btn_remove_bill_remove1.setBackground(new java.awt.Color(255, 255, 255));
        btn_remove_bill_remove1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));

        jLabel142.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel142.setText("Edit");

        javax.swing.GroupLayout btn_remove_bill_remove1Layout = new javax.swing.GroupLayout(btn_remove_bill_remove1);
        btn_remove_bill_remove1.setLayout(btn_remove_bill_remove1Layout);
        btn_remove_bill_remove1Layout.setHorizontalGroup(
            btn_remove_bill_remove1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_remove_bill_remove1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel142)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btn_remove_bill_remove1Layout.setVerticalGroup(
            btn_remove_bill_remove1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel142, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout edit_billLayout = new javax.swing.GroupLayout(edit_bill);
        edit_bill.setLayout(edit_billLayout);
        edit_billLayout.setHorizontalGroup(
            edit_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit_billLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel140)
                .addGap(10, 10, 10)
                .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_remove_bill_search1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_remove_bill_remove1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(edit_billLayout.createSequentialGroup()
                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        edit_billLayout.setVerticalGroup(
            edit_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit_billLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(edit_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edit_billLayout.createSequentialGroup()
                        .addGroup(edit_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_remove_bill_search1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_remove_bill_remove1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edit_billLayout.createSequentialGroup()
                        .addGroup(edit_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel140)
                            .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
        );

        panel_body_edit.add(edit_bill, "card2");

        panel_edit.add(panel_body_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 780, 430));

        mainshow.add(panel_edit, "card9");

        panel_edit1.setBackground(new java.awt.Color(255, 255, 255));
        panel_edit1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_header_edit1.setBackground(new java.awt.Color(255, 255, 255));
        panel_header_edit1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_edit1_pet.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit1_pet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_edit1_itemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_edit1_itemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_edit1_itemMouseExited(evt);
            }
        });

        jLabel144.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel144.setText("Pet");

        javax.swing.GroupLayout btn_edit1_petLayout = new javax.swing.GroupLayout(btn_edit1_pet);
        btn_edit1_pet.setLayout(btn_edit1_petLayout);
        btn_edit1_petLayout.setHorizontalGroup(
            btn_edit1_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_edit1_petLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel144)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        btn_edit1_petLayout.setVerticalGroup(
            btn_edit1_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_edit1_petLayout.createSequentialGroup()
                .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panel_header_edit1.add(btn_edit1_pet, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 90, 30));

        btn_edit1_item.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit1_item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_edit1_itemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_edit1_itemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_edit1_itemMouseExited(evt);
            }
        });

        jLabel145.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel145.setText("Items");

        javax.swing.GroupLayout btn_edit1_itemLayout = new javax.swing.GroupLayout(btn_edit1_item);
        btn_edit1_item.setLayout(btn_edit1_itemLayout);
        btn_edit1_itemLayout.setHorizontalGroup(
            btn_edit1_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_edit1_itemLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel145)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        btn_edit1_itemLayout.setVerticalGroup(
            btn_edit1_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_edit1_itemLayout.createSequentialGroup()
                .addComponent(jLabel145, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panel_header_edit1.add(btn_edit1_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 90, 30));

        btn_edit1_food.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit1_food.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_edit1_itemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_edit1_itemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_edit1_itemMouseExited(evt);
            }
        });

        jLabel146.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel146.setText("Food");

        javax.swing.GroupLayout btn_edit1_foodLayout = new javax.swing.GroupLayout(btn_edit1_food);
        btn_edit1_food.setLayout(btn_edit1_foodLayout);
        btn_edit1_foodLayout.setHorizontalGroup(
            btn_edit1_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_edit1_foodLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel146)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        btn_edit1_foodLayout.setVerticalGroup(
            btn_edit1_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_edit1_foodLayout.createSequentialGroup()
                .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panel_header_edit1.add(btn_edit1_food, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 90, 30));

        btn_edit1_staff.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit1_staff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_edit1_itemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_edit1_itemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_edit1_itemMouseExited(evt);
            }
        });

        jLabel147.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel147.setText("Staff");

        javax.swing.GroupLayout btn_edit1_staffLayout = new javax.swing.GroupLayout(btn_edit1_staff);
        btn_edit1_staff.setLayout(btn_edit1_staffLayout);
        btn_edit1_staffLayout.setHorizontalGroup(
            btn_edit1_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_edit1_staffLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel147)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        btn_edit1_staffLayout.setVerticalGroup(
            btn_edit1_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_edit1_staffLayout.createSequentialGroup()
                .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panel_header_edit1.add(btn_edit1_staff, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 90, 30));

        btn_edit1_supp.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit1_supp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_edit1_itemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_edit1_itemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_edit1_itemMouseExited(evt);
            }
        });

        jLabel148.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel148.setText("Supp");

        javax.swing.GroupLayout btn_edit1_suppLayout = new javax.swing.GroupLayout(btn_edit1_supp);
        btn_edit1_supp.setLayout(btn_edit1_suppLayout);
        btn_edit1_suppLayout.setHorizontalGroup(
            btn_edit1_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_edit1_suppLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel148)
                .addGap(27, 27, 27))
        );
        btn_edit1_suppLayout.setVerticalGroup(
            btn_edit1_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_edit1_suppLayout.createSequentialGroup()
                .addComponent(jLabel148, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panel_header_edit1.add(btn_edit1_supp, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 90, 30));

        jLabel84.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel84.setText("EDIT");
        panel_header_edit1.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        jLabel117.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel117.setText("Where shortcomings and improvements are corrected");
        panel_header_edit1.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, -1, -1));

        panel_edit1.add(panel_header_edit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 90));

        panel_body_edit1.setBackground(new java.awt.Color(255, 255, 255));
        panel_body_edit1.setLayout(new java.awt.CardLayout());

        edit1_pet.setBackground(new java.awt.Color(255, 255, 255));

        table_edit_pet.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_edit_pet.setModel(new javax.swing.table.DefaultTableModel(
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
        table_edit_pet.setGridColor(new java.awt.Color(255, 255, 255));
        table_edit_pet.setSelectionBackground(new java.awt.Color(110, 89, 222));
        table_edit_pet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_edit_petMouseClicked(evt);
            }
        });
        jScrollPane25.setViewportView(table_edit_pet);

        jLabel150.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel150.setText("ID");

        jLabel151.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel151.setText("Age");

        jLabel152.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel152.setText("Species");

        jLabel153.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel153.setText("Breed");

        jLabel154.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel154.setText("Price");

        txt_edit_pet_id.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_pet_id.setText("(ID)");

        txt_edit_pet_age.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_pet_age.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        cbb_edit_pet_species.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chó", "Mèo", "Hamster" }));
        cbb_edit_pet_species.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_edit_pet_speciesItemStateChanged(evt);
            }
        });

        txt_edit_pet_price.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_pet_price.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btn_edit_pet_edit.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit_pet_edit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_edit_pet_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_edit_pet_editMouseClicked(evt);
            }
        });

        jLabel156.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel156.setText("Edit");

        javax.swing.GroupLayout btn_edit_pet_editLayout = new javax.swing.GroupLayout(btn_edit_pet_edit);
        btn_edit_pet_edit.setLayout(btn_edit_pet_editLayout);
        btn_edit_pet_editLayout.setHorizontalGroup(
            btn_edit_pet_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_edit_pet_editLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel156)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        btn_edit_pet_editLayout.setVerticalGroup(
            btn_edit_pet_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel156, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jLabel155.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel155.setText("Amount");

        txt_edit_pet_amount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_pet_amount.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout edit1_petLayout = new javax.swing.GroupLayout(edit1_pet);
        edit1_pet.setLayout(edit1_petLayout);
        edit1_petLayout.setHorizontalGroup(
            edit1_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit1_petLayout.createSequentialGroup()
                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(edit1_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edit1_petLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btn_edit_pet_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(edit1_petLayout.createSequentialGroup()
                        .addGroup(edit1_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(edit1_petLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(edit1_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel151, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                                    .addComponent(jLabel150, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edit1_petLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(edit1_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel152)
                                    .addComponent(jLabel154, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel155))))
                        .addGap(18, 18, 18)
                        .addGroup(edit1_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_edit_pet_id, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_edit_pet_age)
                            .addComponent(cbb_edit_pet_species, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbb_edit_pet_breed, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_edit_pet_price)
                            .addComponent(txt_edit_pet_amount))))
                .addGap(43, 43, 43))
        );
        edit1_petLayout.setVerticalGroup(
            edit1_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit1_petLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(edit1_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel150)
                    .addComponent(txt_edit_pet_id))
                .addGap(18, 18, 18)
                .addGroup(edit1_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel151)
                    .addComponent(txt_edit_pet_age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(edit1_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel152)
                    .addComponent(cbb_edit_pet_species, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(edit1_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel153)
                    .addComponent(cbb_edit_pet_breed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(edit1_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel154)
                    .addComponent(txt_edit_pet_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(edit1_petLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel155)
                    .addComponent(txt_edit_pet_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(128, 128, 128)
                .addComponent(btn_edit_pet_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edit1_petLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        panel_body_edit1.add(edit1_pet, "card2");

        edit1_food.setBackground(new java.awt.Color(255, 255, 255));

        table_edit_food.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_edit_food.setModel(new javax.swing.table.DefaultTableModel(
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
        table_edit_food.setGridColor(new java.awt.Color(255, 255, 255));
        table_edit_food.setSelectionBackground(new java.awt.Color(110, 89, 222));
        table_edit_food.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_edit_foodMouseClicked(evt);
            }
        });
        jScrollPane27.setViewportView(table_edit_food);

        btn_edit_food_edit.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit_food_edit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_edit_food_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_edit_food_editMouseClicked(evt);
            }
        });

        jLabel163.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel163.setText("Edit");

        javax.swing.GroupLayout btn_edit_food_editLayout = new javax.swing.GroupLayout(btn_edit_food_edit);
        btn_edit_food_edit.setLayout(btn_edit_food_editLayout);
        btn_edit_food_editLayout.setHorizontalGroup(
            btn_edit_food_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_edit_food_editLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel163)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        btn_edit_food_editLayout.setVerticalGroup(
            btn_edit_food_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel163, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jLabel167.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel167.setText("Code");

        jLabel168.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel168.setText("Name");

        jLabel169.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel169.setText("For");

        jLabel170.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel170.setText("Price");

        txt_edit_food_code.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_food_code.setText("Code");

        txt_edit_food_name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_food_name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txt_edit_food_price.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_food_price.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        cbb_edit_food_for.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chó", "Mèo", "Hamster" }));

        jLabel171.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel171.setText("Amount");

        txt_edit_food_amount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_food_amount.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout edit1_foodLayout = new javax.swing.GroupLayout(edit1_food);
        edit1_food.setLayout(edit1_foodLayout);
        edit1_foodLayout.setHorizontalGroup(
            edit1_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit1_foodLayout.createSequentialGroup()
                .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(edit1_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edit1_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_edit_food_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(edit1_foodLayout.createSequentialGroup()
                            .addComponent(jLabel168, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txt_edit_food_name))
                        .addGroup(edit1_foodLayout.createSequentialGroup()
                            .addComponent(jLabel169, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(cbb_edit_food_for, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(edit1_foodLayout.createSequentialGroup()
                            .addComponent(jLabel170, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txt_edit_food_price))
                        .addGroup(edit1_foodLayout.createSequentialGroup()
                            .addComponent(jLabel167, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txt_edit_food_code, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(edit1_foodLayout.createSequentialGroup()
                        .addComponent(jLabel171)
                        .addGap(18, 18, 18)
                        .addComponent(txt_edit_food_amount)))
                .addGap(30, 30, 30))
        );
        edit1_foodLayout.setVerticalGroup(
            edit1_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit1_foodLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(edit1_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(edit1_foodLayout.createSequentialGroup()
                        .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(edit1_foodLayout.createSequentialGroup()
                        .addGroup(edit1_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel167)
                            .addComponent(txt_edit_food_code))
                        .addGap(18, 18, 18)
                        .addGroup(edit1_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel168)
                            .addComponent(txt_edit_food_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(edit1_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel169)
                            .addComponent(cbb_edit_food_for, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(edit1_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel170)
                            .addComponent(txt_edit_food_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(edit1_foodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel171)
                            .addComponent(txt_edit_food_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_edit_food_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))))
        );

        panel_body_edit1.add(edit1_food, "card4");

        edit1_staff.setBackground(new java.awt.Color(255, 255, 255));

        btn_edit_staff_edit.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit_staff_edit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_edit_staff_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_edit_staff_editMouseClicked(evt);
            }
        });

        jLabel164.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel164.setText("Edit");

        javax.swing.GroupLayout btn_edit_staff_editLayout = new javax.swing.GroupLayout(btn_edit_staff_edit);
        btn_edit_staff_edit.setLayout(btn_edit_staff_editLayout);
        btn_edit_staff_editLayout.setHorizontalGroup(
            btn_edit_staff_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_edit_staff_editLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel164)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        btn_edit_staff_editLayout.setVerticalGroup(
            btn_edit_staff_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel164, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        table_edit_staff.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_edit_staff.setModel(new javax.swing.table.DefaultTableModel(
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
        table_edit_staff.setGridColor(new java.awt.Color(255, 255, 255));
        table_edit_staff.setSelectionBackground(new java.awt.Color(110, 89, 222));
        table_edit_staff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_edit_staffMouseClicked(evt);
            }
        });
        jScrollPane28.setViewportView(table_edit_staff);

        jLabel172.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel172.setText("Code");

        jLabel173.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel173.setText("Name");

        jLabel174.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel174.setText("Phone");

        jLabel175.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel175.setText("Email");

        jLabel176.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel176.setText("ID");

        jLabel177.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel177.setText("Pass");

        jLabel178.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel178.setText("Role");

        txt_edit_staff_code.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_staff_code.setText("Code");

        txt_edit_staff_name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_staff_name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txt_edit_staff_phone.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_staff_phone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txt_edit_staff_email.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_staff_email.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txt_edit_staff_id.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_staff_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txt_edit_staff_pass.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_staff_pass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        cbb_edit_staff_role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mananger", "Staff" }));

        javax.swing.GroupLayout edit1_staffLayout = new javax.swing.GroupLayout(edit1_staff);
        edit1_staff.setLayout(edit1_staffLayout);
        edit1_staffLayout.setHorizontalGroup(
            edit1_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit1_staffLayout.createSequentialGroup()
                .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(edit1_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_edit_staff_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(edit1_staffLayout.createSequentialGroup()
                        .addComponent(jLabel173, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_edit_staff_name))
                    .addGroup(edit1_staffLayout.createSequentialGroup()
                        .addComponent(jLabel174, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_edit_staff_phone))
                    .addGroup(edit1_staffLayout.createSequentialGroup()
                        .addComponent(jLabel175, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_edit_staff_email))
                    .addGroup(edit1_staffLayout.createSequentialGroup()
                        .addComponent(jLabel176, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_edit_staff_id))
                    .addGroup(edit1_staffLayout.createSequentialGroup()
                        .addComponent(jLabel177, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_edit_staff_pass))
                    .addGroup(edit1_staffLayout.createSequentialGroup()
                        .addComponent(jLabel178, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbb_edit_staff_role, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(edit1_staffLayout.createSequentialGroup()
                        .addComponent(jLabel172, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_edit_staff_code, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        edit1_staffLayout.setVerticalGroup(
            edit1_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit1_staffLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(edit1_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(edit1_staffLayout.createSequentialGroup()
                        .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(edit1_staffLayout.createSequentialGroup()
                        .addGroup(edit1_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel172)
                            .addComponent(txt_edit_staff_code))
                        .addGap(18, 18, 18)
                        .addGroup(edit1_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel173)
                            .addComponent(txt_edit_staff_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(edit1_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel174)
                            .addComponent(txt_edit_staff_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(edit1_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel175)
                            .addComponent(txt_edit_staff_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(edit1_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel176)
                            .addComponent(txt_edit_staff_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(edit1_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel177)
                            .addComponent(txt_edit_staff_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(edit1_staffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel178)
                            .addComponent(cbb_edit_staff_role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_edit_staff_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))))
        );

        panel_body_edit1.add(edit1_staff, "card5");

        edit1_supp.setBackground(new java.awt.Color(255, 255, 255));

        btn_edit_supp_edit.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit_supp_edit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_edit_supp_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_edit_supp_editMouseClicked(evt);
            }
        });

        jLabel165.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel165.setText("Edit");

        javax.swing.GroupLayout btn_edit_supp_editLayout = new javax.swing.GroupLayout(btn_edit_supp_edit);
        btn_edit_supp_edit.setLayout(btn_edit_supp_editLayout);
        btn_edit_supp_editLayout.setHorizontalGroup(
            btn_edit_supp_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_edit_supp_editLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel165)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        btn_edit_supp_editLayout.setVerticalGroup(
            btn_edit_supp_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel165, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        table_edit_supp.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_edit_supp.setModel(new javax.swing.table.DefaultTableModel(
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
        table_edit_supp.setGridColor(new java.awt.Color(255, 255, 255));
        table_edit_supp.setSelectionBackground(new java.awt.Color(110, 89, 222));
        table_edit_supp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_edit_suppMouseClicked(evt);
            }
        });
        jScrollPane30.setViewportView(table_edit_supp);

        jLabel181.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel181.setText("Code");

        txt_edit_supp_code.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel183.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel183.setText("Name");

        jLabel184.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel184.setText("Items");

        jLabel185.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel185.setText("Phone");

        jLabel186.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel186.setText("Address");

        jLabel187.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel187.setText("Note");

        txt_edit_supp_name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_supp_name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        cbb_edit_supp_items.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pet", "Item", "Food" }));

        txt_edit_supp_phone.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_supp_phone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txt_edit_supp_address.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_supp_address.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txt_edit_supp_note.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_supp_note.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout edit1_suppLayout = new javax.swing.GroupLayout(edit1_supp);
        edit1_supp.setLayout(edit1_suppLayout);
        edit1_suppLayout.setHorizontalGroup(
            edit1_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit1_suppLayout.createSequentialGroup()
                .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(edit1_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_edit_supp_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(edit1_suppLayout.createSequentialGroup()
                        .addComponent(jLabel181, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_edit_supp_code, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(edit1_suppLayout.createSequentialGroup()
                        .addComponent(jLabel184, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbb_edit_supp_items, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(edit1_suppLayout.createSequentialGroup()
                        .addComponent(jLabel185, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_edit_supp_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(edit1_suppLayout.createSequentialGroup()
                        .addComponent(jLabel187, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_edit_supp_note))
                    .addGroup(edit1_suppLayout.createSequentialGroup()
                        .addComponent(jLabel186)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_edit_supp_address))
                    .addGroup(edit1_suppLayout.createSequentialGroup()
                        .addComponent(jLabel183, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_edit_supp_name, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        edit1_suppLayout.setVerticalGroup(
            edit1_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edit1_suppLayout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addGroup(edit1_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel181)
                    .addComponent(txt_edit_supp_code))
                .addGap(18, 18, 18)
                .addGroup(edit1_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel183)
                    .addComponent(txt_edit_supp_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(edit1_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel184)
                    .addComponent(cbb_edit_supp_items, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(edit1_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel185)
                    .addComponent(txt_edit_supp_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(edit1_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel186)
                    .addComponent(txt_edit_supp_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(edit1_suppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel187)
                    .addComponent(txt_edit_supp_note, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(136, 136, 136)
                .addComponent(btn_edit_supp_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edit1_suppLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_body_edit1.add(edit1_supp, "card6");

        edit1_item.setBackground(new java.awt.Color(255, 255, 255));

        table_edit_item.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        table_edit_item.setModel(new javax.swing.table.DefaultTableModel(
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
        table_edit_item.setGridColor(new java.awt.Color(255, 255, 255));
        table_edit_item.setSelectionBackground(new java.awt.Color(110, 89, 222));
        table_edit_item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_edit_itemMouseClicked(evt);
            }
        });
        jScrollPane26.setViewportView(table_edit_item);

        btn_edit_item_edit.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit_item_edit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(110, 89, 222)));
        btn_edit_item_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_edit_item_editMouseClicked(evt);
            }
        });

        jLabel157.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel157.setText("Edit");

        javax.swing.GroupLayout btn_edit_item_editLayout = new javax.swing.GroupLayout(btn_edit_item_edit);
        btn_edit_item_edit.setLayout(btn_edit_item_editLayout);
        btn_edit_item_editLayout.setHorizontalGroup(
            btn_edit_item_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_edit_item_editLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel157)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        btn_edit_item_editLayout.setVerticalGroup(
            btn_edit_item_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel157, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jLabel158.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel158.setText("Code ");

        jLabel159.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel159.setText("Name");

        jLabel160.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel161.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel161.setText("Price");

        txt_edit_item_code.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txt_edit_item_name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_item_name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txt_edit_item_price.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_item_price.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel162.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel162.setText("Amount");

        txt_edit_item_amount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_edit_item_amount.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout edit1_itemLayout = new javax.swing.GroupLayout(edit1_item);
        edit1_item.setLayout(edit1_itemLayout);
        edit1_itemLayout.setHorizontalGroup(
            edit1_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit1_itemLayout.createSequentialGroup()
                .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(edit1_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edit1_itemLayout.createSequentialGroup()
                        .addComponent(jLabel160)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(edit1_itemLayout.createSequentialGroup()
                        .addGroup(edit1_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_edit_item_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(edit1_itemLayout.createSequentialGroup()
                                .addGroup(edit1_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel159)
                                    .addComponent(jLabel158)
                                    .addComponent(jLabel161))
                                .addGap(26, 26, 26)
                                .addGroup(edit1_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_edit_item_name, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_edit_item_code, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_edit_item_price, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(edit1_itemLayout.createSequentialGroup()
                                .addComponent(jLabel162)
                                .addGap(18, 18, 18)
                                .addComponent(txt_edit_item_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 24, Short.MAX_VALUE))))
        );
        edit1_itemLayout.setVerticalGroup(
            edit1_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit1_itemLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(edit1_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(edit1_itemLayout.createSequentialGroup()
                        .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(edit1_itemLayout.createSequentialGroup()
                        .addGroup(edit1_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_edit_item_code, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel158))
                        .addGap(18, 18, 18)
                        .addGroup(edit1_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel159)
                            .addComponent(txt_edit_item_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel160)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(edit1_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel161)
                            .addComponent(txt_edit_item_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(edit1_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel162)
                            .addComponent(txt_edit_item_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_edit_item_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))))
        );

        panel_body_edit1.add(edit1_item, "card3");

        panel_edit1.add(panel_body_edit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 780, 430));

        mainshow.add(panel_edit1, "card11");

        app.add(mainshow, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 780, 520));

        getContentPane().add(app, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void loaddata_pet() {
        try {
            listpet.clear();
            model_pet.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql_select = "SELECT idpet,tuoi,tenloai,tengiong,gia,slpet FROM dbo.pet,dbo.loai,dbo.giong\n"
                    + "WHERE pet.maloai = loai.maloai AND pet.magiong = giong.magiong";
            ResultSet rs = st.executeQuery(sql_select);
            while (rs.next()) {
                String id = rs.getString(1);
                Float tuoi = rs.getFloat(2);
                String loai = rs.getString(3);
                String giong = rs.getString(4);
                int gia = rs.getInt(5);
                int soluong = rs.getInt(6);
                pet pet = new pet(id, tuoi, loai, giong, gia, soluong);
                listpet.add(pet);
            }
            for (int i = 0; i < listpet.size(); i++) {
                Vector v = new Vector();
                pet a = listpet.get(i);
                v.add(a.idpet);
                v.add(a.tuoi);
                v.add(a.maloai);
                v.add(a.magiong);
                v.add(a.gia);
                v.add(a.soluong);
                model_pet.addRow(v);
            }
            table_edit_pet.setModel(model_pet);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loaddata_food() {
        try {
            listfood.clear();
            model_food.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql_select = "SELECT mata,tenta,tenloai,gia,slta FROM dbo.thucan,dbo.loai\n"
                    + "WHERE maloai=petan";
            ResultSet rs = st.executeQuery(sql_select);
            while (rs.next()) {
                String mata = rs.getString(1);
                String tenta = rs.getString(2);
                String loai = rs.getString(3);
                int gia = rs.getInt(4);
                int soluong = rs.getInt(5);
                food food = new food(mata, tenta, loai, gia, soluong);
                listfood.add(food);
            }
            for (int i = 0; i < listfood.size(); i++) {
                Vector v = new Vector();
                food a = listfood.get(i);
                v.add(a.mata);
                v.add(a.tenta);
                v.add(a.petan);
                v.add(a.gia);
                v.add(a.soluong);
                model_food.addRow(v);
            }
        } catch (Exception e) {
        }
    }

    public void loaddata_item() {
        try {
            listitem.clear();
            model_item.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql_select = "SELECT * FROM dbo.dodung";
            ResultSet rs = st.executeQuery(sql_select);
            while (rs.next()) {
                String madd = rs.getString(1);
                String tendd = rs.getString(2);
                int gia = rs.getInt(3);
                int soluong = rs.getInt(4);
                item item = new item(madd, tendd, gia, soluong);
                listitem.add(item);
            }
            for (int i = 0; i < listitem.size(); i++) {
                Vector v = new Vector();
                item a = listitem.get(i);
                v.add(a.madd);
                v.add(a.tendd);
                v.add(a.gia);
                v.add(a.soluong);
                model_item.addRow(v);
            }
        } catch (Exception e) {
        }
    }

    public void loaddata_staff() {
        try {
            liststaff.clear();
            model_staff.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql = "select*from nhanvien";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String manv = rs.getString(1);
                String ten = rs.getString(2);
                String sdt = rs.getString(3);
                String email = rs.getString(4);
                String id = rs.getString(5);
                String pass = rs.getString(6);
                boolean role = rs.getBoolean(7);
                staff staff = new staff(manv, ten, sdt, email, id, pass, role);
                liststaff.add(staff);
            }
            for (int i = 0; i < liststaff.size(); i++) {
                Vector v = new Vector();
                staff a = liststaff.get(i);
                v.add(a.manv);
                v.add(a.ten);
                v.add(a.sdt);
                v.add(a.email);
                v.add(a.id);
                v.add(a.pass);
                v.add(a.role);
                model_staff.addRow(v);
            }
        } catch (Exception e) {
        }
    }

    public void loaddata_supp() {
        try {
            listsupp.clear();
            model_supp.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql = "select*from nhacungcap";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String mancc = rs.getString(1);
                String ten = rs.getString(2);
                String mathang = rs.getString(3);
                String sdt = rs.getString(4);
                String diachi = rs.getString(5);
                String ghichu = rs.getString(6);
                supp supp = new supp(mancc, ten, mathang, sdt, diachi, ghichu);
                listsupp.add(supp);
            }
            for (int i = 0; i < listsupp.size(); i++) {
                Vector v = new Vector();
                supp a = listsupp.get(i);
                v.add(a.mancc);
                v.add(a.ten);
                v.add(a.mathang);
                v.add(a.sdt);
                v.add(a.diachi);
                v.add(a.ghichu);
                model_supp.addRow(v);
            }
        } catch (Exception e) {
        }
    }

    public void loaddata_bill() {
        try {
            listbill.clear();
            model_bill.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql = "SELECT hdchitiet.mahd,ngaymua,manv,makh,mapet,hdchitiet.slpet,hdchitiet.mata,hdchitiet.slta,dodung.madd,hdchitiet.sldd,(pet.gia*hdchitiet.slpet+dodung.gia*hdchitiet.sldd+hdchitiet.slta*dodung.gia) AS total FROM dbo.hoadon,dbo.hdchitiet,dbo.dodung,dbo.thucan,dbo.pet\n"
                    + "WHERE hdchitiet.mahd=hoadon.mahd AND mapet=idpet AND hdchitiet.mata=thucan.mata AND dodung.madd=hdchitiet.madd\n"
                    + "GROUP BY hdchitiet.mahd,ngaymua,manv,makh,mapet,hdchitiet.slpet,hdchitiet.mata,hdchitiet.slta,dodung.madd,hdchitiet.sldd,(pet.gia*hdchitiet.slpet+dodung.gia*hdchitiet.sldd+hdchitiet.slta*dodung.gia)";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String mahd = rs.getString(1);
                Date ngaymua = rs.getDate(2);
                String tennv = rs.getString(3);
                String tenkh = rs.getString(4);
                String mapet = rs.getString(5);
                int slpet = rs.getInt(6);
                String tenta = rs.getString(7);
                int slta = rs.getInt(8);
                String tendd = rs.getString(9);
                int sldd = rs.getInt(10);
                int tongtien = rs.getInt(11);
                bill bill = new bill(mahd, ngaymua, tennv, tenkh, tendd, slpet, tenta, slta, tendd, sldd, tongtien);
                listbill.add(bill);
            }
            for (int i = 0; i < listbill.size(); i++) {
                Vector v = new Vector();
                bill a = listbill.get(i);
                v.add(a.mahd);
                v.add(a.ngaymua);
                v.add(a.tennv);
                v.add(a.tenkh);
                v.add(a.tenpet);
                v.add(a.slpet);
                v.add(a.tenta);
                v.add(a.slta);
                v.add(a.tendd);
                v.add(a.sldd);
                v.add(a.tongtien);
                model_bill.addRow(v);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loaddata_bill_import() {
        try {
            listbill_import.clear();
            model_bill_import.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql = " SELECT hdnhaphangchitiet.mahdnhap,ngaynhap,mancc,idpet,slpet,hdnhaphangchitiet.madd,sldd,hdnhaphangchitiet.mata,slta,SUM(petnhap.gia*slpet+dodungnhap.gia*sldd+thucannhap.gia*slta) FROM dbo.hoadonnhaphang,dbo.hdnhaphangchitiet,dbo.dodungnhap,dbo.thucannhap,dbo.petnhap\n"
                    + " WHERE hdnhaphangchitiet.mahdnhap=hoadonnhaphang.mahdnhap AND mapet=idpet AND dodungnhap.madd=hdnhaphangchitiet.madd AND hdnhaphangchitiet.mata=thucannhap.mata\n"
                    + " GROUP BY hdnhaphangchitiet.mahdnhap,ngaynhap,mancc,idpet,slpet,hdnhaphangchitiet.madd,sldd,hdnhaphangchitiet.mata,slta";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String mahd = rs.getString(1);
                Date ngaynhap = rs.getDate(2);
                String mancc = rs.getString(3);
                String mapet = rs.getString(4);
                int slpet = rs.getInt(5);
                String mata = rs.getString(6);
                int slta = rs.getInt(7);
                String madd = rs.getString(8);
                int sldd = rs.getInt(9);
                int tong = rs.getInt(10);
                bill bill = new bill(mahd, ngaynhap, mancc, mancc, mapet, slpet, mata, slta, madd, sldd, tong);
                listbill_import.add(bill);
            }
            for (int i = 0; i < listbill_import.size(); i++) {
                Vector v = new Vector();
                bill a = listbill_import.get(i);
                v.add(a.mahd);
                v.add(a.ngaymua);
                v.add(a.tennv);
                v.add(a.tenpet);
                v.add(a.slpet);
                v.add(a.tenta);
                v.add(a.slta);
                v.add(a.tendd);
                v.add(a.sldd);
                v.add(a.tongtien);
                model_bill_import.addRow(v);
            }
        } catch (Exception e) {
        }
    }

    public void loaddata_acc() {
        try {
            listacc.clear();
            model_acc.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql = "SELECT id,pass,vaitro FROM dbo.nhanvien WHERE vaitro = 1";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String pass = rs.getString(2);
                String role = "Staff";
                account acc = new account(id, pass, role);
                listacc.add(acc);
            }
            for (int i = 0; i < listacc.size(); i++) {
                Vector v = new Vector();
                account a = listacc.get(i);
                v.add(a.id);
                v.add(a.pass);
                v.add(a.role);
                model_acc.addRow(v);
            }

        } catch (Exception e) {
        }
    }

    public void loaddata_acc_admin() {
        try {
            listacc_admin.clear();
            model_acc_admin.setRowCount(0);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql = "SELECT id,pass,vaitro FROM dbo.nhanvien WHERE vaitro = 0";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String pass = rs.getString(2);
                String role = "Admin";
                account acc = new account(id, pass, role);
                listacc_admin.add(acc);
            }
            for (int i = 0; i < listacc_admin.size(); i++) {
                Vector v = new Vector();
                account a = listacc_admin.get(i);
                v.add(a.id);
                v.add(a.pass);
                v.add(a.role);
                model_acc_admin.addRow(v);
            }

        } catch (Exception e) {
        }
    }

    public void loaddata_pay_add() {
        list_pay_add.clear();
        model_pay_add.setRowCount(0);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql = "";
            if (cbb_pay_add.getSelectedIndex() == 0) {
                sql = "SELECT tengiong,gia,slpet FROM dbo.pet ,dbo.giong\n"
                        + "WHERE giong.magiong=pet.magiong";
            }
            if (cbb_pay_add.getSelectedIndex() == 1) {
                sql = "SELECT tendd,gia,sldd FROM dbo.dodung";
            }
            if (cbb_pay_add.getSelectedIndex() == 2) {
                sql = "SELECT tenta,gia,slta FROM dbo.thucan";
            }
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ten = rs.getString(1);
                int gia = rs.getInt(2);
                int slpet = rs.getInt(3);
                addBill a = new addBill(ten, gia, slpet);
                list_pay_add.add(a);
            }
            for (int i = 0; i < list_pay_add.size(); i++) {
                Vector v = new Vector();
                addBill a = list_pay_add.get(i);
                v.add(a.ten);
                v.add(a.gia);
                v.add(a.soluong);
                model_pay_add.addRow(v);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadtable() {
        loaddata_pet();
        loaddata_food();
        loaddata_item();
        loaddata_staff();
        loaddata_supp();
        loaddata_bill();
        loaddata_bill_import();
        loaddata_acc();
        loaddata_acc_admin();
        loaddata_pay_add();
    }

    // load cbb
    public void loadcbb_add_pet_breed_species() {
        try {
            cbb_add_pet_breed_species.removeAllItems();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "SELECT * FROM dbo.loai";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cbb_add_pet_breed_species.addItem(rs.getString(2));
                cbb_add_pet_pet_species.addItem(rs.getString(2));
            }
        } catch (Exception e) {
        }
    }

    public void loadcbb_edit_pet() {
        try {
            cbb_edit_pet_breed.removeAllItems();
            String loai = (String) cbb_edit_pet_species.getSelectedItem();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql = "SELECT tenloai,tengiong FROM dbo.loai,dbo.giong,dbo.pet\n"
                    + "WHERE giong.magiong=pet.magiong AND loai.maloai=pet.maloai AND tenloai=N'" + loai + "'\n"
                    + "GROUP BY tenloai,tengiong";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cbb_edit_pet_breed.addItem(rs.getString(2));
            }

        } catch (Exception e) {
        }
    }

    public void loadcbb_add_pet_species() {
        cbb_add_pet_pet_species.removeAllItems();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select*from loai";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cbb_add_pet_pet_species.addItem(rs.getString(2));
            }
        } catch (Exception e) {
        }

    }

    public void loadcbb_add_pet_breed() {
        cbb_add_pet_pet_breed.removeAllItems();
        try {
            String loai = (String) cbb_add_pet_pet_species.getSelectedItem();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "SELECT tenloai,tengiong FROM dbo.loai,dbo.giong,dbo.pet\n"
                    + " WHERE giong.magiong=pet.magiong AND loai.maloai=pet.maloai AND tenloai=N'" + loai + "'\n"
                    + " GROUP BY tenloai,tengiong";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cbb_add_pet_pet_breed.addItem(rs.getString(2));
            }
        } catch (Exception e) {
        }
    }

    public void loadcbb_add_item_food_for() {

        cbb_add_item_food_for.removeAllItems();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select*from loai";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cbb_add_item_food_for.addItem(rs.getString(2));
            }
        } catch (Exception e) {
        }

    }

    
    

    public void loadcbb() {
        loadcbb_add_pet_breed_species();
        loadcbb_edit_pet();
        loadcbb_add_pet_species();
        loadcbb_add_pet_breed();
        loadcbb_add_item_food_for();
        
    }
    
    
    // jfreechart
    
    public void loadchart1(){
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        ds.setValue(5, "Amount of bill", "Trương Đình Hoàng");
        ds.setValue(3, "Amount of bill", "Trần Quốc Nam");
        ds.setValue(3, "Amount of bill", "Vũ Trần Đại Dương");
        ds.setValue(3, "Amount of bill", "Nguyễn Trọng Đại");
        ds.setValue(4, "Amount of bill", "Vũ Trung Tín");
        ds.setValue(2, "Amount of bill", "Nguyễn Nhật Trung");
        ds.setValue(7, "Amount of bill", "Phạm Nhật Vượng");
        ds.setValue(0, "Amount of bill", "Âu Dương Phong");
        
        JFreeChart chart1 = ChartFactory.createBarChart("","Staff name", "Amount of bill",  ds,PlotOrientation.VERTICAL,true,true,false);
        CategoryPlot plot = chart1.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
        ChartPanel chartpanel = new ChartPanel(chart1);
        panel_sta_sold_chart.add(chartpanel,BorderLayout.CENTER);
        chartpanel.setSize(780,400);
        chartpanel.setVisible(true);
        panel_sta_sold_chart.validate();
    }
    
    public void loadchart2(){
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        ds.setValue(61744000, "Proceeds", "Trương Đình Hoàng");
        ds.setValue(39225000, "Proceeds", "Trần Quốc Nam");
        ds.setValue(53067000, "Proceeds", "Vũ Trần Đại Dương");
        ds.setValue(33637000, "Proceeds", "Nguyễn Trọng Đại");
        ds.setValue(31069000, "Proceeds", "Vũ Trung Tín");
        ds.setValue(88925000, "Proceeds", "Nguyễn Nhật Trung");
        ds.setValue(31487000, "Proceeds", "Phạm Nhật Vượng");
        ds.setValue(0, "Proceeds", "Âu Dương Phong");
        
        JFreeChart chart1 = ChartFactory.createBarChart("","Staff name", "Proceeds",  ds,PlotOrientation.VERTICAL,true,true,false);
        CategoryPlot plot = chart1.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
        ChartPanel chartpanel = new ChartPanel(chart1);
        statistical_chart_import.add(chartpanel,BorderLayout.CENTER);
        chartpanel.setSize(780,400);
        chartpanel.setVisible(true);
        statistical_chart_import.validate();
    }
    
    public void loadchart(){
        loadchart1();
        loadchart2();
    }
    private void pb_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pb_logoutMouseClicked
        // TODO add your handling code here:
        new Login().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_pb_logoutMouseClicked

    private void pb_logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pb_logoutMouseEntered
        // TODO add your handling code here:
        pb_logout.setBackground(new Color(85, 65, 118));
    }//GEN-LAST:event_pb_logoutMouseEntered

    private void pb_logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pb_logoutMouseExited
        // TODO add your handling code here:
        pb_logout.setBackground(new Color(64, 43, 100));
    }//GEN-LAST:event_pb_logoutMouseExited

    private void pb_homeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pb_homeMouseEntered
        // TODO add your handling code here:
        
        if (evt.getSource() == pb_warehouse) {
            pb_warehouse.setBackground(new Color(85, 65, 118));
        }
        if (evt.getSource() == pb_pay) {
            pb_pay.setBackground(new Color(85, 65, 118));
        }
        
    }//GEN-LAST:event_pb_homeMouseEntered

    private void pb_homeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pb_homeMouseExited
        // TODO add your handling code here:
        
        if (evt.getSource() == pb_warehouse) {
            pb_warehouse.setBackground(new Color(64, 43, 100));
        }
        if (evt.getSource() == pb_pay) {
            pb_pay.setBackground(new Color(64, 43, 100));
        }
        
    }//GEN-LAST:event_pb_homeMouseExited

    private void pb1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pb1MousePressed
        // TODO add your handling code here:
        
        pb_warehouse.setBackground(new Color(64, 43, 100));
        pb_pay.setBackground(new Color(64, 43, 100));
        
    }//GEN-LAST:event_pb1MousePressed

    private void mouseclick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseclick
        // TODO add your handling code here:
        
        if (evt.getSource() == pb_warehouse) {
           
            panel_warehouse.setVisible(true);
            panel_pay.setVisible(false);
            panel_account.setVisible(false);
            panel_bill.setVisible(false);
            panel_statistical.setVisible(false);
            panel_add.setVisible(false);
            panel_remove.setVisible(false);
            panel_edit1.setVisible(false);
        }
        if (evt.getSource() == pb_pay) {
            
            panel_warehouse.setVisible(false);
            panel_pay.setVisible(true);
            panel_account.setVisible(false);
            panel_bill.setVisible(false);
            panel_statistical.setVisible(false);
            panel_add.setVisible(false);
            panel_remove.setVisible(false);
            panel_edit1.setVisible(false);
        }
        
    }//GEN-LAST:event_mouseclick

    private void pb_billmouseclick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pb_billmouseclick
        // TODO add your handling code here:
        
        panel_warehouse.setVisible(false);
        panel_pay.setVisible(false);
        panel_account.setVisible(false);
        panel_bill.setVisible(true);
        panel_statistical.setVisible(false);
        panel_add.setVisible(false);
        panel_remove.setVisible(false);
        panel_edit1.setVisible(false);
    }//GEN-LAST:event_pb_billmouseclick

    private void pb_billpb1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pb_billpb1MouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == pb_bill) {
            pb_bill.setBackground(new Color(85, 65, 118));
        }
    }//GEN-LAST:event_pb_billpb1MouseEntered

    private void pb_billpb1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pb_billpb1MouseExited
        // TODO add your handling code here:
        if (evt.getSource() == pb_bill) {
            pb_bill.setBackground(new Color(64, 43, 100));
        }
    }//GEN-LAST:event_pb_billpb1MouseExited

    private void btn_removeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_removeMouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == btn_add) {
            btn_add.setBackground(new Color(110, 89, 222));
        }

        if (evt.getSource() == btn_pay) {
            btn_pay.setBackground(new Color(110, 89, 222));
        }
    }//GEN-LAST:event_btn_removeMouseEntered

    private void btn_removeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_removeMouseExited
        // TODO add your handling code here:
        if (evt.getSource() == btn_add) {
            btn_add.setBackground(new Color(255, 255, 255));
        }

        if (evt.getSource() == btn_pay) {
            btn_pay.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_btn_removeMouseExited

    private void btn_foodMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_foodMouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == btn_pet) {
            btn_pet.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_food) {
            btn_food.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_item) {
            btn_item.setBackground(new Color(110, 89, 222));
        }
    }//GEN-LAST:event_btn_foodMouseEntered

    private void btn_foodMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_foodMouseExited
        // TODO add your handling code here:
        if (evt.getSource() == btn_pet) {
            btn_pet.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_food) {
            btn_food.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_item) {
            btn_item.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_btn_foodMouseExited

    private void btn_foodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_foodMouseClicked
        // TODO add your handling code here:
        if (evt.getSource() == btn_pet) {
            warehouse_pet.setVisible(true);
            warehouse_food.setVisible(false);
            warehouse_item.setVisible(false);
        }
        if (evt.getSource() == btn_food) {
            warehouse_pet.setVisible(false);
            warehouse_food.setVisible(true);
            warehouse_item.setVisible(false);
        }
        if (evt.getSource() == btn_item) {
            warehouse_pet.setVisible(false);
            warehouse_food.setVisible(false);
            warehouse_item.setVisible(true);
        }
    }//GEN-LAST:event_btn_foodMouseClicked

    private void pb_statisticalmouseclick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pb_statisticalmouseclick
        // TODO add your handling code here:
     
        panel_warehouse.setVisible(false);
        panel_pay.setVisible(false);
        panel_account.setVisible(false);
        panel_bill.setVisible(false);
        panel_statistical.setVisible(true);
        panel_add.setVisible(false);
        panel_remove.setVisible(false);
        panel_edit1.setVisible(false);
    }//GEN-LAST:event_pb_statisticalmouseclick

    private void pb_statisticalpb1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pb_statisticalpb1MouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == pb_statistical) {
            pb_statistical.setBackground(new Color(85, 65, 118));
        }
    }//GEN-LAST:event_pb_statisticalpb1MouseEntered

    private void pb_statisticalpb1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pb_statisticalpb1MouseExited
        // TODO add your handling code here:
        if (evt.getSource() == pb_statistical) {
            pb_statistical.setBackground(new Color(64, 43, 100));
        }
    }//GEN-LAST:event_pb_statisticalpb1MouseExited

    private void btn_soldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_soldMouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == btn_sold) {
            btn_sold.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_import) {
            btn_import.setBackground(new Color(110, 89, 222));
        }
    }//GEN-LAST:event_btn_soldMouseEntered

    private void btn_soldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_soldMouseExited
        // TODO add your handling code here:
        if (evt.getSource() == btn_sold) {
            btn_sold.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_import) {
            btn_import.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_btn_soldMouseExited

    private void btn_soldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_soldMouseClicked
        // TODO add your handling code here:
        if (evt.getSource() == btn_sold) {
            bill_sold.setVisible(true);
            bill_import.setVisible(false);
        }
        if (evt.getSource() == btn_import) {
            bill_sold.setVisible(false);
            bill_import.setVisible(true);
        }
    }//GEN-LAST:event_btn_soldMouseClicked

    private void btn_adminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_adminMouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == btn_admin) {
            btn_admin.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_user) {
            btn_user.setBackground(new Color(110, 89, 222));
        }
    }//GEN-LAST:event_btn_adminMouseEntered

    private void btn_adminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_adminMouseExited
        // TODO add your handling code here:
        if (evt.getSource() == btn_admin) {
            btn_admin.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_user) {
            btn_user.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_btn_adminMouseExited

    private void btn_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_userMouseClicked
        // TODO add your handling code here:
        if (evt.getSource() == btn_admin) {
            account_admin.setVisible(true);
            account_user.setVisible(false);
        }
        if (evt.getSource() == btn_user) {
            account_admin.setVisible(false);
            account_user.setVisible(true);
        }
    }//GEN-LAST:event_btn_userMouseClicked

    private void btn_sold_statisticalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sold_statisticalMouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == btn_sold_statistical) {
            btn_sold_statistical.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_import_statistical) {
            btn_import_statistical.setBackground(new Color(110, 89, 222));
        }
        
    }//GEN-LAST:event_btn_sold_statisticalMouseEntered

    private void btn_sold_statisticalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sold_statisticalMouseExited
        // TODO add your handling code here:
        if (evt.getSource() == btn_sold_statistical) {
            btn_sold_statistical.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_import_statistical) {
            btn_import_statistical.setBackground(new Color(255, 255, 255));
        }
        
    }//GEN-LAST:event_btn_sold_statisticalMouseExited

    private void btn_sold_statisticalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sold_statisticalMouseClicked
        // TODO add your handling code here:
        if (evt.getSource() == btn_sold_statistical) {
            statistical_chart_sold.setVisible(true);
            statistical_chart_import.setVisible(false);
            statistical_chart_revenue.setVisible(false);
        }
        if (evt.getSource() == btn_import_statistical) {
            statistical_chart_sold.setVisible(false);
            statistical_chart_import.setVisible(true);
            statistical_chart_revenue.setVisible(false);
        }
        
    }//GEN-LAST:event_btn_sold_statisticalMouseClicked

    private void btn_stafff_addMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_stafff_addMouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == btn_pet_add) {
            btn_pet_add.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_item_add) {
            btn_item_add.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_stafff_add) {
            btn_stafff_add.setBackground(new Color(110, 89, 222));
        }
       
    }//GEN-LAST:event_btn_stafff_addMouseEntered

    private void btn_stafff_addMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_stafff_addMouseExited
        // TODO add your handling code here:
        if (evt.getSource() == btn_pet_add) {
            btn_pet_add.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_item_add) {
            btn_item_add.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_stafff_add) {
            btn_stafff_add.setBackground(new Color(255, 255, 255));
        }
        
    }//GEN-LAST:event_btn_stafff_addMouseExited

    private void btn_stafff_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_stafff_addMouseClicked
        // TODO add your handling code here:
        if (evt.getSource() == btn_pet_add) {
            add_pet.setVisible(true);
          
            add_item.setVisible(false);
            add_staff.setVisible(false);
            add_supp.setVisible(false);
        }
        
        if (evt.getSource() == btn_item_add) {
            add_pet.setVisible(false);
           
            add_item.setVisible(true);
            add_staff.setVisible(false);
            add_supp.setVisible(false);
        }
        if (evt.getSource() == btn_stafff_add) {
            add_pet.setVisible(false);
          
            add_item.setVisible(false);
            add_staff.setVisible(true);
            add_supp.setVisible(false);
        }
    }//GEN-LAST:event_btn_stafff_addMouseClicked

    private void btn_supp_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_supp_addMouseClicked
        // TODO add your handling code here:
        add_pet.setVisible(false);
        
        add_item.setVisible(false);
        add_staff.setVisible(false);
        add_supp.setVisible(true);
    }//GEN-LAST:event_btn_supp_addMouseClicked

    private void btn_supp_addMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_supp_addMouseEntered
        // TODO add your handling code here:
        btn_supp_add.setBackground(new Color(110, 89, 222));
    }//GEN-LAST:event_btn_supp_addMouseEntered

    private void btn_supp_addMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_supp_addMouseExited
        // TODO add your handling code here:
        btn_supp_add.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_supp_addMouseExited

    private void btn_create_petMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_create_petMouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == btn_create_species) {
            btn_create_species.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_create_breed) {
            btn_create_breed.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_create_pet) {
            btn_create_pet.setBackground(new Color(110, 89, 222));
        }
    }//GEN-LAST:event_btn_create_petMouseEntered

    private void btn_create_petMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_create_petMouseExited
        // TODO add your handling code here:
        if (evt.getSource() == btn_create_species) {
            btn_create_species.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_create_breed) {
            btn_create_breed.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_create_pet) {
            btn_create_pet.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_btn_create_petMouseExited

    private void btn_add_item_createbtn_create_petMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add_item_createbtn_create_petMouseEntered
        // TODO add your handling code here:
        btn_add_item_create.setBackground(new Color(110, 89, 222));
    }//GEN-LAST:event_btn_add_item_createbtn_create_petMouseEntered

    private void btn_add_item_createbtn_create_petMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add_item_createbtn_create_petMouseExited
        // TODO add your handling code here:
        btn_add_item_create.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_add_item_createbtn_create_petMouseExited

    private void btn_add_food_createbtn_create_petMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add_food_createbtn_create_petMouseEntered
        // TODO add your handling code here:
        btn_add_food_create.setBackground(new Color(110, 89, 222));
    }//GEN-LAST:event_btn_add_food_createbtn_create_petMouseEntered

    private void btn_add_food_createbtn_create_petMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add_food_createbtn_create_petMouseExited
        // TODO add your handling code here:
        btn_add_food_create.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_add_food_createbtn_create_petMouseExited

    private void btn_add_supp_createMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add_supp_createMouseEntered
        // TODO add your handling code here:
        btn_add_supp_create.setBackground(new Color(110, 89, 222));
    }//GEN-LAST:event_btn_add_supp_createMouseEntered

    private void btn_add_supp_createMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add_supp_createMouseExited
        // TODO add your handling code here:
        btn_add_supp_create.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_add_supp_createMouseExited

    private void btn_add_staff_createMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add_staff_createMouseEntered
        // TODO add your handling code here:
        btn_add_staff_create.setBackground(new Color(110, 89, 222));
    }//GEN-LAST:event_btn_add_staff_createMouseEntered

    private void btn_add_staff_createMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add_staff_createMouseExited
        // TODO add your handling code here:
        btn_add_staff_create.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_add_staff_createMouseExited

    private void btn_remove_billMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_billMouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == btn_remove_pet) {
            btn_remove_pet.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_remove_item) {
            btn_remove_item.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_remove_food) {
            btn_remove_food.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_remove_staff) {
            btn_remove_staff.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_remove_sup) {
            btn_remove_sup.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_remove_bill) {
            btn_remove_bill.setBackground(new Color(110, 89, 222));
        }
    }//GEN-LAST:event_btn_remove_billMouseEntered

    private void btn_remove_billMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_billMouseExited
        // TODO add your handling code here:
        if (evt.getSource() == btn_remove_pet) {
            btn_remove_pet.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_remove_item) {
            btn_remove_item.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_remove_food) {
            btn_remove_food.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_remove_staff) {
            btn_remove_staff.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_remove_sup) {
            btn_remove_sup.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_remove_bill) {
            btn_remove_bill.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_btn_remove_billMouseExited

    private void btn_remove_billMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_billMouseClicked
        // TODO add your handling code here:
        if (evt.getSource() == btn_remove_pet) {
            remove_pet.setVisible(true);
            remove_item.setVisible(false);
            remove_food.setVisible(false);
            remove_staff.setVisible(false);
            remove_supp.setVisible(false);
            remove_bill.setVisible(false);
        }
        if (evt.getSource() == btn_remove_item) {
            remove_pet.setVisible(false);
            remove_item.setVisible(true);
            remove_food.setVisible(false);
            remove_staff.setVisible(false);
            remove_supp.setVisible(false);
            remove_bill.setVisible(false);
        }
        if (evt.getSource() == btn_remove_food) {
            remove_pet.setVisible(false);
            remove_item.setVisible(false);
            remove_food.setVisible(true);
            remove_staff.setVisible(false);
            remove_supp.setVisible(false);
            remove_bill.setVisible(false);
        }
        if (evt.getSource() == btn_remove_staff) {
            remove_pet.setVisible(false);
            remove_item.setVisible(false);
            remove_food.setVisible(false);
            remove_staff.setVisible(true);
            remove_supp.setVisible(false);
            remove_bill.setVisible(false);
        }
        if (evt.getSource() == btn_remove_sup) {
            remove_pet.setVisible(false);
            remove_item.setVisible(false);
            remove_food.setVisible(false);
            remove_staff.setVisible(false);
            remove_supp.setVisible(true);
            remove_bill.setVisible(false);
        }
        if (evt.getSource() == btn_remove_bill) {
            remove_pet.setVisible(false);
            remove_item.setVisible(false);
            remove_food.setVisible(false);
            remove_staff.setVisible(false);
            remove_supp.setVisible(false);
            remove_bill.setVisible(true);
        }
    }//GEN-LAST:event_btn_remove_billMouseClicked

    private void btn_remove_pet_sreachMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_pet_sreachMouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == btn_remove_pet_sreach) {
            btn_remove_pet_sreach.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_remove_pet_remove) {
            btn_remove_pet_remove.setBackground(new Color(110, 89, 222));
        }
    }//GEN-LAST:event_btn_remove_pet_sreachMouseEntered

    private void btn_remove_pet_sreachMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_pet_sreachMouseExited
        // TODO add your handling code here:
        if (evt.getSource() == btn_remove_pet_sreach) {
            btn_remove_pet_sreach.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_remove_pet_remove) {
            btn_remove_pet_remove.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_btn_remove_pet_sreachMouseExited

    private void btn_remove_item_sreachMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_item_sreachMouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == btn_remove_item_sreach) {
            btn_remove_item_sreach.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_remove_item_remove) {
            btn_remove_item_remove.setBackground(new Color(110, 89, 222));
        }
    }//GEN-LAST:event_btn_remove_item_sreachMouseEntered

    private void btn_remove_item_sreachMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_item_sreachMouseExited
        // TODO add your handling code here:
        if (evt.getSource() == btn_remove_item_sreach) {
            btn_remove_item_sreach.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_remove_item_remove) {
            btn_remove_item_remove.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_btn_remove_item_sreachMouseExited

    private void btn_remove_food_sreachMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_food_sreachMouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == btn_remove_food_sreach) {
            btn_remove_food_sreach.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_remove_food_remove) {
            btn_remove_food_remove.setBackground(new Color(110, 89, 222));
        }
    }//GEN-LAST:event_btn_remove_food_sreachMouseEntered

    private void btn_remove_food_sreachMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_food_sreachMouseExited
        // TODO add your handling code here:
        if (evt.getSource() == btn_remove_food_sreach) {
            btn_remove_food_sreach.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_remove_food_remove) {
            btn_remove_food_remove.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_btn_remove_food_sreachMouseExited

    private void btn_remove_staff_sreachMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_staff_sreachMouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == btn_remove_staff_sreach) {
            btn_remove_staff_sreach.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_remove_staff_remove) {
            btn_remove_staff_remove.setBackground(new Color(110, 89, 222));
        }
    }//GEN-LAST:event_btn_remove_staff_sreachMouseEntered

    private void btn_remove_staff_sreachMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_staff_sreachMouseExited
        // TODO add your handling code here:
        if (evt.getSource() == btn_remove_staff_sreach) {
            btn_remove_staff_sreach.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_remove_staff_remove) {
            btn_remove_staff_remove.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_btn_remove_staff_sreachMouseExited

    private void btn_remove_supp_sreachMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_supp_sreachMouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == btn_remove_supp_sreach) {
            btn_remove_supp_sreach.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_remove_supp_remove) {
            btn_remove_supp_remove.setBackground(new Color(110, 89, 222));
        }
    }//GEN-LAST:event_btn_remove_supp_sreachMouseEntered

    private void btn_remove_supp_sreachMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_supp_sreachMouseExited
        // TODO add your handling code here:
        if (evt.getSource() == btn_remove_supp_sreach) {
            btn_remove_supp_sreach.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_remove_supp_remove) {
            btn_remove_supp_remove.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_btn_remove_supp_sreachMouseExited

    private void btn_remove_bill_searchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_bill_searchMouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == btn_remove_bill_search) {
            btn_remove_bill_search.setBackground(new Color(110, 89, 222));
        }
        if (evt.getSource() == btn_remove_bill_remove) {
            btn_remove_bill_remove.setBackground(new Color(110, 89, 222));
        }
    }//GEN-LAST:event_btn_remove_bill_searchMouseEntered

    private void btn_remove_bill_searchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_bill_searchMouseExited
        // TODO add your handling code here:
        if (evt.getSource() == btn_remove_bill_search) {
            btn_remove_bill_search.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_remove_bill_remove) {
            btn_remove_bill_remove.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_btn_remove_bill_searchMouseExited

    private void btn_edit1_itemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit1_itemMouseEntered
        // TODO add your handling code here:
        if (evt.getSource() == btn_edit1_pet) {
            btn_edit1_pet.setBackground(new Color(100, 89, 222));
        }
        if (evt.getSource() == btn_edit1_item) {
            btn_edit1_item.setBackground(new Color(100, 89, 222));
        }
        if (evt.getSource() == btn_edit1_food) {
            btn_edit1_food.setBackground(new Color(100, 89, 222));
        }
        if (evt.getSource() == btn_edit1_staff) {
            btn_edit1_staff.setBackground(new Color(100, 89, 222));
        }
        if (evt.getSource() == btn_edit1_supp) {
            btn_edit1_supp.setBackground(new Color(100, 89, 222));
        }
    }//GEN-LAST:event_btn_edit1_itemMouseEntered

    private void btn_edit1_itemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit1_itemMouseExited
        // TODO add your handling code here:
        if (evt.getSource() == btn_edit1_pet) {
            btn_edit1_pet.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_edit1_item) {
            btn_edit1_item.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_edit1_food) {
            btn_edit1_food.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_edit1_staff) {
            btn_edit1_staff.setBackground(new Color(255, 255, 255));
        }
        if (evt.getSource() == btn_edit1_supp) {
            btn_edit1_supp.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_btn_edit1_itemMouseExited

    private void btn_edit1_itemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit1_itemMouseClicked
        // TODO add your handling code here:
        if (evt.getSource() == btn_edit1_pet) {
            edit1_pet.setVisible(true);
            edit1_item.setVisible(false);
            edit1_food.setVisible(false);
            edit1_staff.setVisible(false);
            edit1_supp.setVisible(false);
        }
        if (evt.getSource() == btn_edit1_item) {
            edit1_pet.setVisible(false);
            edit1_item.setVisible(true);
            edit1_food.setVisible(false);
            edit1_staff.setVisible(false);
            edit1_supp.setVisible(false);
        }
        if (evt.getSource() == btn_edit1_food) {
            edit1_pet.setVisible(false);
            edit1_item.setVisible(false);
            edit1_food.setVisible(true);
            edit1_staff.setVisible(false);
            edit1_supp.setVisible(false);
        }
        if (evt.getSource() == btn_edit1_staff) {
            edit1_pet.setVisible(false);
            edit1_item.setVisible(false);
            edit1_food.setVisible(false);
            edit1_staff.setVisible(true);
            edit1_supp.setVisible(false);
        }
        if (evt.getSource() == btn_edit1_supp) {
            edit1_pet.setVisible(false);
            edit1_item.setVisible(false);
            edit1_food.setVisible(false);
            edit1_staff.setVisible(false);
            edit1_supp.setVisible(true);
        }
    }//GEN-LAST:event_btn_edit1_itemMouseClicked

    private void table_remove_petMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_remove_petMouseClicked
        // TODO add your handling code here:
        index_table_remove_pet = table_remove_pet.getSelectedRow();
    }//GEN-LAST:event_table_remove_petMouseClicked

    private void btn_remove_pet_removeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_pet_removeMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            pet remove = listpet.get(index_table_remove_pet);
            String id_remove = remove.idpet;
            int sl = remove.soluong;
            if (sl > 0) {
                String sql = "UPDATE dbo.pet SET slpet=slpet-1\n"
                        + "WHERE idpet=" + "'" + id_remove + "'";
                st.execute(sql);
            } else {
                JOptionPane.showMessageDialog(null, "Out of quantity");
            }
            loaddata_pet();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_remove_pet_removeMouseClicked

    private void btn_remove_pet_sreachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_pet_sreachMouseClicked
        // TODO add your handling code here:
        String find = txt_remove_pet_search.getText();
        for (int i = 0; i < listpet.size(); i++) {
            pet tim = listpet.get(i);
            if (find.equalsIgnoreCase(tim.idpet)) {
                table_remove_pet.setRowSelectionInterval(i, i);
                index_table_remove_pet = i;
            }
        }
    }//GEN-LAST:event_btn_remove_pet_sreachMouseClicked

    private void btn_remove_item_removeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_item_removeMouseClicked
        // TODO add your handling code here:
        String slxoa = JOptionPane.showInputDialog("Enter the amount to be deleted");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            item remove = listitem.get(index_table_remove_item);
            String id_remove = remove.madd;
            int sl = remove.soluong;
            int soxoa = Integer.parseInt(slxoa);
            if (sl > 0 && sl > soxoa) {
                String sql = "UPDATE dbo.dodung SET sldd=sldd-" + "'" + slxoa + "'" + "WHERE madd=" + "'" + id_remove + "'";
                st.execute(sql);
            } else {
                JOptionPane.showMessageDialog(null, "Out of quantity");
            }
            loaddata_item();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_remove_item_removeMouseClicked

    private void table_remove_itemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_remove_itemMouseClicked
        // TODO add your handling code here:
        index_table_remove_item = table_remove_item.getSelectedRow();
    }//GEN-LAST:event_table_remove_itemMouseClicked

    private void btn_remove_item_sreachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_item_sreachMouseClicked
        // TODO add your handling code here:
        String find = txt_remove_item_search.getText();
        for (int i = 0; i < listitem.size(); i++) {
            item tim = listitem.get(i);
            if (find.equalsIgnoreCase(tim.madd)) {
                table_remove_item.setRowSelectionInterval(i, i);
                index_table_remove_item = i;
            }
        }
    }//GEN-LAST:event_btn_remove_item_sreachMouseClicked

    private void table_remove_foodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_remove_foodMouseClicked
        // TODO add your handling code here:
        index_table_remove_food = table_remove_food.getSelectedRow();
    }//GEN-LAST:event_table_remove_foodMouseClicked

    private void btn_remove_food_removeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_food_removeMouseClicked
        // TODO add your handling code here:
        String slxoa = JOptionPane.showInputDialog("Enter the amount to be deleted");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            food remove = listfood.get(index_table_remove_food);
            String id_remove = remove.mata;
            int sl = remove.soluong;
            int soxoa = Integer.parseInt(slxoa);
            if (sl > 0 && sl > soxoa) {
                String sql = "UPDATE dbo.thucan SET slta=slta-" + "'" + slxoa + "'" + "WHERE mata=" + "'" + id_remove + "'";
                st.execute(sql);
            } else {
                JOptionPane.showMessageDialog(null, "Out of quantity");
            }
            loaddata_food();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_remove_food_removeMouseClicked

    private void btn_remove_food_sreachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_food_sreachMouseClicked
        // TODO add your handling code here:
        String find = txt_remove_food_search.getText();
        for (int i = 0; i < listitem.size(); i++) {
            food tim = listfood.get(i);
            if (find.equalsIgnoreCase(tim.mata)) {
                table_remove_food.setRowSelectionInterval(i, i);
                index_table_remove_food = i;
            }
        }
    }//GEN-LAST:event_btn_remove_food_sreachMouseClicked

    private void table_remove_staffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_remove_staffMouseClicked
        // TODO add your handling code here:
        index_table_remove_staff = table_remove_staff.getSelectedRow();
    }//GEN-LAST:event_table_remove_staffMouseClicked

    private void btn_remove_staff_removeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_staff_removeMouseClicked
        // TODO add your handling code here:
        staff staff = liststaff.get(index_table_remove_staff);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "DELETE dbo.nhanvien WHERE manv=? UPDATE dbo.hoadon SET manv='OUT' WHERE manv=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, staff.manv);
            st.setString(2, staff.manv);
            st.execute();
            loadtable();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_remove_staff_removeMouseClicked

    private void btn_remove_staff_sreachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_staff_sreachMouseClicked
        // TODO add your handling code here:
        String find = txt_remove_staff_search.getText();
        for (int i = 0; i < liststaff.size(); i++) {
            staff tim = liststaff.get(i);
            if (find.equalsIgnoreCase(tim.manv)) {
                table_remove_staff.setRowSelectionInterval(i, i);
                index_table_remove_staff = i;
            }
        }
    }//GEN-LAST:event_btn_remove_staff_sreachMouseClicked

    private void table_remove_suppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_remove_suppMouseClicked
        // TODO add your handling code here:
        index_table_remove_supp = table_remove_supp.getSelectedRow();
    }//GEN-LAST:event_table_remove_suppMouseClicked

    private void btn_remove_supp_removeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_supp_removeMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            supp remove = listsupp.get(index_table_remove_supp);
            String id_remove = remove.mancc;
            String sql = "DELETE dbo.nhacungcap WHERE mancc=" + "'" + id_remove + "'";
            st.execute(sql);
            loaddata_supp();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_remove_supp_removeMouseClicked

    private void btn_remove_supp_sreachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_supp_sreachMouseClicked
        // TODO add your handling code here:
        String find = txt_remove_supp_search.getText();
        for (int i = 0; i < listsupp.size(); i++) {
            supp tim = listsupp.get(i);
            if (find.equalsIgnoreCase(tim.mancc)) {
                table_remove_supp.setRowSelectionInterval(i, i);
                index_table_remove_supp = i;
            }
        }
    }//GEN-LAST:event_btn_remove_supp_sreachMouseClicked

    private void table_edit_petMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_edit_petMouseClicked
        // TODO add your handling code here:       
        index_table_edit_pet = table_edit_pet.getSelectedRow();
        loadcbb_edit_pet();
        pet pet = listpet.get(index_table_edit_pet);
        txt_edit_pet_id.setText(pet.idpet);
        txt_edit_pet_age.setText(String.valueOf(pet.tuoi));
        cbb_edit_pet_species.setSelectedItem(pet.maloai);
        cbb_edit_pet_breed.setSelectedItem(pet.magiong);
        txt_edit_pet_price.setText(String.valueOf(pet.gia));
        txt_edit_pet_amount.setText(String.valueOf(pet.soluong));


    }//GEN-LAST:event_table_edit_petMouseClicked

    private void cbb_edit_pet_speciesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_edit_pet_speciesItemStateChanged
        // TODO add your handling code here:
        int a = cbb_edit_pet_species.getSelectedIndex();
        cbb_edit_pet_breed.removeAllItems();
        if (a == 0) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection(url, user, pass);
                Statement st = con.createStatement();
                String sql = "SELECT tenloai,tengiong FROM dbo.loai,dbo.giong,dbo.pet\n"
                        + "WHERE pet.maloai=loai.maloai AND giong.magiong=pet.magiong AND tenloai=N'chó'\n"
                        + "GROUP BY tenloai,tengiong";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    cbb_edit_pet_breed.addItem(rs.getString(2));
                }
            } catch (Exception e) {
            }
        }
        if (a == 1) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection(url, user, pass);
                Statement st = con.createStatement();
                String sql = "SELECT tenloai,tengiong FROM dbo.loai,dbo.giong,dbo.pet\n"
                        + "WHERE pet.maloai=loai.maloai AND giong.magiong=pet.magiong AND tenloai=N'mèo'\n"
                        + "GROUP BY tenloai,tengiong";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    cbb_edit_pet_breed.addItem(rs.getString(2));
                }
            } catch (Exception e) {
            }
        }
        if (a == 2) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection(url, user, pass);
                Statement st = con.createStatement();
                String sql = "SELECT tenloai,tengiong FROM dbo.loai,dbo.giong,dbo.pet\n"
                        + "WHERE pet.maloai=loai.maloai AND giong.magiong=pet.magiong AND tenloai=N'hamster'\n"
                        + "GROUP BY tenloai,tengiong";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    cbb_edit_pet_breed.addItem(rs.getString(2));
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_cbb_edit_pet_speciesItemStateChanged

    private void table_edit_foodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_edit_foodMouseClicked
        // TODO add your handling code here:
        index_table_edit_food = table_edit_food.getSelectedRow();
        food food = listfood.get(index_table_edit_food);
        txt_edit_food_code.setText(food.mata);
        txt_edit_food_name.setText(food.tenta);
        cbb_edit_food_for.setSelectedItem(food.petan);
        txt_edit_food_price.setText(String.valueOf(food.gia));
        txt_edit_food_amount.setText(String.valueOf(food.soluong));
    }//GEN-LAST:event_table_edit_foodMouseClicked

    private void btn_edit_food_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_food_editMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "UPDATE dbo.thucan SET tenta=?,petan=?,gia=?,slta=?\n"
                    + "WHERE mata=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_edit_food_name.getText());
            String forpet = "";
            if (cbb_edit_food_for.getSelectedItem().equals("Chó")) {
                forpet = "L001";
            }
            if (cbb_edit_food_for.getSelectedItem().equals("Mèo")) {
                forpet = "L002";
            }
            if (cbb_edit_food_for.getSelectedItem().equals("Hamster")) {
                forpet = "L003";
            }
            st.setString(2, forpet);
            st.setInt(3, Integer.parseInt(txt_edit_food_price.getText()));
            st.setInt(4, Integer.parseInt(txt_edit_food_amount.getText()));
            st.setString(5, txt_edit_food_code.getText());
            st.execute();
            loaddata_food();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_edit_food_editMouseClicked

    private void table_edit_itemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_edit_itemMouseClicked
        // TODO add your handling code here:
        index_table_edit_item = table_edit_item.getSelectedRow();
        item item = listitem.get(index_table_edit_item);
        txt_edit_item_code.setText(item.madd);
        txt_edit_item_name.setText(item.tendd);
        txt_edit_item_price.setText(String.valueOf(item.gia));
        txt_edit_item_amount.setText(String.valueOf(item.soluong));
    }//GEN-LAST:event_table_edit_itemMouseClicked

    private void btn_edit_item_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_item_editMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "UPDATE dbo.dodung SET tendd=?,gia=?,sldd=? WHERE madd=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_edit_item_name.getText());
            st.setInt(2, Integer.parseInt(txt_edit_item_price.getText()));
            st.setInt(3, Integer.parseInt(txt_edit_item_amount.getText()));
            st.setString(4, txt_edit_item_code.getText());
            st.execute();
            loaddata_item();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_edit_item_editMouseClicked

    private void btn_edit_pet_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_pet_editMouseClicked
        // TODO add your handling code here:
        try {
            String loai = (String) cbb_edit_pet_species.getSelectedItem();
            String giong = (String) cbb_edit_pet_breed.getSelectedItem();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "DECLARE @magiong VARCHAR(10);\n"
                    + "DECLARE @maloai VARCHAR(10);\n"
                    + "SET @maloai = (SELECT dbo.timmaloai(N'" + loai + "'));\n"
                    + "SET @magiong = (SELECT dbo.timmagiong(N'" + giong + "'));\n"
                    + "UPDATE dbo.pet SET tuoi=?,maloai=@maloai,magiong=@magiong,gia=?,slpet=?\n"
                    + "WHERE idpet=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setFloat(1, Float.parseFloat(txt_edit_pet_age.getText()));
            st.setInt(2, Integer.parseInt(txt_edit_pet_price.getText()));
            st.setInt(3, Integer.parseInt(txt_edit_pet_amount.getText()));
            st.setString(4, txt_edit_pet_id.getText());
            st.execute();
            loaddata_pet();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_btn_edit_pet_editMouseClicked

    private void table_edit_staffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_edit_staffMouseClicked
        // TODO add your handling code here:
        index_table_edit_staff = table_edit_staff.getSelectedRow();
        staff staff = liststaff.get(index_table_edit_staff);
        txt_edit_staff_code.setText(staff.manv);
        txt_edit_staff_name.setText(staff.ten);
        txt_edit_staff_phone.setText(staff.sdt);
        txt_edit_staff_email.setText(staff.email);
        txt_edit_staff_id.setText(staff.id);
        txt_edit_staff_pass.setText(staff.pass);
        if (staff.role == true) {
            cbb_edit_staff_role.setSelectedIndex(1);
        } else
            cbb_edit_staff_role.setSelectedIndex(0);
    }//GEN-LAST:event_table_edit_staffMouseClicked

    private void btn_edit_staff_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_staff_editMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "UPDATE dbo.nhanvien SET tennv=?,sdt=?,email=?,id=?,pass=?,vaitro=? WHERE manv=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_edit_staff_name.getText());
            st.setString(2, txt_edit_staff_phone.getText());
            st.setString(3, txt_edit_staff_email.getText());
            st.setString(4, txt_edit_staff_id.getText());
            st.setString(5, txt_edit_staff_pass.getText());
            int a = cbb_edit_staff_role.getSelectedIndex();
            if (a == 0) {
                st.setBoolean(6, false);
            } else {
                st.setBoolean(6, true);
            }
            st.setString(7, txt_edit_staff_code.getText());
            st.execute();
            loaddata_staff();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_edit_staff_editMouseClicked

    private void table_edit_suppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_edit_suppMouseClicked
        // TODO add your handling code here:
        index_table_edit_supp = table_edit_supp.getSelectedRow();
        supp supp = listsupp.get(index_table_edit_supp);
        txt_edit_supp_code.setText(supp.mancc);
        txt_edit_supp_name.setText(supp.ten);
        txt_edit_supp_phone.setText(supp.sdt);
        txt_edit_supp_address.setText(supp.diachi);
        txt_edit_supp_note.setText(supp.ghichu);
        String a = supp.mathang;
        if (a.equals("thức ăn")) {
            cbb_edit_supp_items.setSelectedIndex(2);
        }
        if (a.equals("đồ dùng")) {
            cbb_edit_supp_items.setSelectedIndex(1);
        }
        if (a.equals("pet")) {
            cbb_edit_supp_items.setSelectedIndex(0);
        }
    }//GEN-LAST:event_table_edit_suppMouseClicked

    private void table_remove_billMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_remove_billMouseClicked
        // TODO add your handling code here:
        index_table_remove_bill = table_remove_bill.getSelectedRow();
    }//GEN-LAST:event_table_remove_billMouseClicked

    private void btn_remove_bill_removeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_bill_removeMouseClicked
        // TODO add your handling code here:
        bill bill = listbill.get(index_table_remove_bill);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "DELETE dbo.hdchitiet WHERE mahd=?\n"
                    + "DELETE dbo.hoadon WHERE mahd = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, bill.mahd);
            st.setString(2, bill.mahd);
            st.execute();
            loaddata_bill();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_remove_bill_removeMouseClicked

    private void btn_remove_bill_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remove_bill_searchMouseClicked
        // TODO add your handling code here:
        for (int i = 0; i < listbill.size(); i++) {
            if (listbill.get(i).mahd.equalsIgnoreCase(txt_remove_bill_search.getText()) == true) {
                table_remove_bill.setRowSelectionInterval(i, i);
                index_table_remove_bill = i;
            }
        }

    }//GEN-LAST:event_btn_remove_bill_searchMouseClicked

    private void btn_create_speciesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_create_speciesMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "INSERT INTO dbo.loai VALUES  ( '',  ? )";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_add_pet_species.getText());
            st.execute();
            loadtable();
            txt_add_pet_species.setText("");
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_create_speciesMouseClicked

    private void btn_create_breedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_create_breedMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = " INSERT INTO dbo.giong VALUES  ( '', ? )";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_add_pet_breed_breed.getText());
            st.execute();
            loadtable();
            txt_add_pet_breed_breed.setText("");
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_create_breedMouseClicked

    private void cbb_add_pet_pet_speciesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_add_pet_pet_speciesItemStateChanged
        // TODO add your handling code here:
        cbb_add_pet_pet_breed.removeAllItems();
        try {
            String loai = (String) cbb_add_pet_pet_species.getSelectedItem();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "SELECT tenloai,tengiong FROM dbo.loai,dbo.giong,dbo.pet\n"
                    + " WHERE giong.magiong=pet.magiong AND loai.maloai=pet.maloai AND tenloai=N'" + loai + "'\n"
                    + " GROUP BY tenloai,tengiong";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cbb_add_pet_pet_breed.addItem(rs.getString(2));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbb_add_pet_pet_speciesItemStateChanged

    private void btn_create_petMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_create_petMouseClicked
        // TODO add your handling code here:
        String loai = null;
        String giong = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql1 = "select*from loai";

            ResultSet rs1 = st.executeQuery(sql1);

            while (rs1.next()) {
                if (rs1.getString(2).equalsIgnoreCase((String) cbb_add_pet_pet_species.getSelectedItem()) == true) {
                    loai = rs1.getString(1);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql2 = "select*from giong";
            ResultSet rs2 = st.executeQuery(sql2);
            while (rs2.next()) {
                if (rs2.getString(2).equalsIgnoreCase((String) cbb_add_pet_pet_breed.getSelectedItem()) == true) {
                    giong = rs2.getString(1);
                }
            }
        } catch (Exception e) {
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql3 = " INSERT INTO dbo.pet VALUES  ( '' ,? , ? ,  ? , ? , ? )";
            PreparedStatement st2 = con.prepareStatement(sql3);
            st2.setFloat(1, Float.parseFloat(txt_add_pet_pet_age.getText()));
            st2.setString(2, loai);
            st2.setString(3, giong);
            st2.setInt(4, Integer.parseInt(txt_add_pet_pet_price.getText()));
            st2.setInt(5, Integer.parseInt(txt_add_pet_pet_amount.getText()));
            st2.execute();
            txt_add_pet_pet_age.setText("");
            txt_add_pet_pet_price.setText("");
            txt_add_pet_pet_amount.setText("");
            loadtable();
            loadcbb();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_create_petMouseClicked

    private void btn_add_item_createMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add_item_createMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = " INSERT INTO dbo.dodung VALUES  ( '',  ?, ?, ?  )";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_add_item_item_name.getText());
            st.setInt(2, Integer.parseInt(txt_add_item_item_price.getText()));
            st.setInt(3, Integer.parseInt(txt_add_item_item_amount.getText()));
            st.execute();
            loadtable();
            txt_add_item_item_name.setText("");
            txt_add_item_item_price.setText("");
            txt_add_item_item_amount.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_add_item_createMouseClicked

    private void btn_add_food_createMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add_food_createMouseClicked
        // TODO add your handling code here:
        String maloai = "";
        String loai = (String) cbb_add_item_food_for.getSelectedItem();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "SELECT dbo.timmaloai(N'" + loai + "')";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                loai = rs.getString(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "INSERT INTO dbo.thucan VALUES  ( '', ?,  ?,  ?,  ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_add_item_food_name.getText());
            st.setString(2, loai);
            st.setInt(3, Integer.parseInt(txt_add_item_food_price.getText()));
            st.setInt(4, Integer.parseInt(txt_add_item_food_amount.getText()));
            st.execute();
            loadcbb();
            loadtable();
            txt_add_item_food_name.setText("");
            txt_add_item_food_price.setText("");
            txt_add_item_food_amount.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_add_food_createMouseClicked

    private void jLabel166MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel166MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel166MouseClicked

    private void btn_add_staff_createMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add_staff_createMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = " INSERT INTO dbo.nhanvien VALUES  ( '',?, ?, ?,  ?,  ?, ?  )";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_add_staff_name.getText());
            st.setString(2, txt_add_staff_phone.getText());
            st.setString(3, txt_add_staff_mail.getText());
            st.setString(4, txt_add_staff_id.getText());
            st.setString(5, txt_add_staff_pass.getText());
            if (cbb_add_staff_role.getSelectedIndex() == 0) {
                st.setBoolean(6, false);
            }
            if (cbb_add_staff_role.getSelectedIndex() == 1) {
                st.setBoolean(6, true);
            }
            st.execute();
            loadcbb();
            loadtable();
            txt_add_staff_name.setText("");
            txt_add_staff_phone.setText("");
            txt_add_staff_mail.setText("");
            txt_add_staff_id.setText("");
            txt_add_staff_pass.setText("");
            cbb_add_staff_role.setSelectedIndex(0);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_add_staff_createMouseClicked

    private void btn_add_supp_createMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add_supp_createMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "INSERT INTO dbo.nhacungcap VALUES  ( '' , ? , ? ,  ? ,? , ?   )";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_add_supp_name.getText());
            st.setString(2, (String) cbb_add_supp_item.getSelectedItem());
            st.setString(3, txt_add_supp_phone.getText());
            st.setString(4, txt_add_supp_address.getText());
            st.setString(5, txt_add_supp_note.getText());
            st.execute();
            loadcbb();
            loadtable();
            txt_add_supp_name.setText("");
            cbb_add_supp_item.setSelectedIndex(0);
            txt_add_supp_phone.setText("");
            txt_add_supp_address.setText("");
            txt_add_supp_note.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_add_supp_createMouseClicked

    private void btn_edit_supp_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_supp_editMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "UPDATE dbo.nhacungcap SET tenncc=?,mathang=?,sdt=?,diachi=?,ghichu=? WHERE mancc =?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txt_edit_supp_name.getText());
            if (cbb_edit_supp_items.getSelectedIndex() == 0) {
                st.setString(2, "pet");
            }
            if (cbb_edit_supp_items.getSelectedIndex() == 1) {
                st.setString(2, "đồ dùng");
            }
            if (cbb_edit_supp_items.getSelectedIndex() == 2) {
                st.setString(2, "thức ăn");
            }
            st.setString(3, txt_edit_supp_phone.getText());
            st.setString(4, txt_edit_supp_address.getText());
            st.setString(5, txt_edit_supp_note.getText());
            st.setString(6, txt_edit_supp_code.getText());
            st.execute();
            loadcbb();
            loadtable();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_edit_supp_editMouseClicked

    private void pb_accountMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pb_accountMousePressed
        // TODO add your handling code here:
        
        if (evt.getSource() == pb_warehouse) {
            pb_warehouse.setBackground(new Color(85, 65, 118));
        }
        if (evt.getSource() == pb_pay) {
            pb_pay.setBackground(new Color(85, 65, 118));
        }
        
        if (evt.getSource() == pb_bill) {
            pb_bill.setBackground(new Color(85, 65, 118));
        }
        if (evt.getSource() == pb_statistical) {
            pb_statistical.setBackground(new Color(85, 65, 118));
        }
        
        
        

    }//GEN-LAST:event_pb_accountMousePressed

    private void cbb_pay_addItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_pay_addItemStateChanged
        // TODO add your handling code here:
        loaddata_pay_add();
    }//GEN-LAST:event_cbb_pay_addItemStateChanged

    private void table_pay_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_pay_addMouseClicked
        // TODO add your handling code here:
        index_table_pay_add = table_pay_add.getSelectedRow();
    }//GEN-LAST:event_table_pay_addMouseClicked

    private void btn_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_addMouseClicked
        // TODO add your handling code here:
        String ten = "";
        int soluong;
        int gia = 0;
        int tong;
        
        int soluongmua = Integer.parseInt(JOptionPane.showInputDialog("Enter the number you want to buy"));
        addBill a = list_pay_add.get(index_table_pay_add);
        if (soluongmua > a.soluong) {
            JOptionPane.showMessageDialog(null, "Out of quantity");
        } else {
            ten = a.ten;
            gia = a.gia;
            soluong = soluongmua;
            tong = gia * soluongmua;
            payBill b = new payBill(ten, soluong, gia, tong);
            list_pay_bill.add(b);
            Vector v = new Vector();
            v.add(b.ten);
            v.add(b.soluong);
            v.add(b.dongia);
            v.add(b.tongtien);
            model_pay_bill.addRow(v);
            table_pay_bill.setModel(model_pay_bill);
        }
        if (cbb_pay_add.getSelectedIndex() == 0) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection(url, user, pass);
                String sql = "UPDATE dbo.pet SET slpet=slpet-?\n"
                        + "WHERE (SELECT dbo.timmagiong(?)) = magiong AND gia=?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setInt(1, soluongmua);
                st.setString(2, ten);
                st.setInt(3, gia);
                st.execute();
                loadtable();
            } catch (Exception e) {
            }
        }
        if (cbb_pay_add.getSelectedIndex() == 1) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection(url, user, pass);
                String sql = "UPDATE dbo.dodung SET sldd=sldd-?\n"
                        + "WHERE (SELECT dbo.madd(?))=madd";
                PreparedStatement st = con.prepareStatement(sql);
                st.setInt(1, soluongmua);
                st.setString(2, ten);
                st.execute();
                loadtable();
            } catch (Exception e) {
            }
        }
        if (cbb_pay_add.getSelectedIndex() == 2) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection(url, user, pass);
                String sql = "UPDATE dbo.thucan SET slta=slta-?\n"
                        + "WHERE (SELECT dbo.mata(?))=mata";
                PreparedStatement st = con.prepareStatement(sql);
                st.setInt(1, soluongmua);
                st.setString(2, ten);
                st.execute();
                loadtable();
            } catch (Exception e) {
            }
        }
        for(int i=0;i<list_pay_bill.size();i++){
            payBill b = list_pay_bill.get(i);
            total = total + b.tongtien;
        }
        txt_pay_total.setText(String.valueOf(total));
    }//GEN-LAST:event_btn_addMouseClicked

    private void txt_pay_reciveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pay_reciveKeyReleased
        // TODO add your handling code here:
        int pay = Integer.parseInt(txt_pay_recive.getText());
        txt_pay_repay.setText(String.valueOf(pay-total));
    }//GEN-LAST:event_txt_pay_reciveKeyReleased

    private void btn_payMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_payMouseClicked
        // TODO add your handling code here:
        model_pay_bill.setRowCount(0);
        loadtable();
        txt_pay_total.setText("");
    }//GEN-LAST:event_btn_payMouseClicked

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
                if ("Window".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainQL.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainQL.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainQL.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainQL.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel account_admin;
    private javax.swing.JPanel account_user;
    private javax.swing.JPanel add_if_food;
    private javax.swing.JPanel add_if_item;
    private javax.swing.JPanel add_item;
    private javax.swing.JPanel add_pet;
    private javax.swing.JPanel add_pet_breed;
    private javax.swing.JPanel add_pet_pet;
    private javax.swing.JPanel add_pet_species;
    private javax.swing.JPanel add_staff;
    private javax.swing.JPanel add_supp;
    private javax.swing.JPanel app;
    private javax.swing.JPanel bill_import;
    private javax.swing.JPanel bill_sold;
    private javax.swing.JPanel btn_add;
    private javax.swing.JPanel btn_add_food_create;
    private javax.swing.JPanel btn_add_item_create;
    private javax.swing.JPanel btn_add_staff_create;
    private javax.swing.JPanel btn_add_supp_create;
    private javax.swing.JPanel btn_admin;
    private javax.swing.JPanel btn_create_breed;
    private javax.swing.JPanel btn_create_pet;
    private javax.swing.JPanel btn_create_species;
    private javax.swing.JPanel btn_edit1_food;
    private javax.swing.JPanel btn_edit1_item;
    private javax.swing.JPanel btn_edit1_pet;
    private javax.swing.JPanel btn_edit1_staff;
    private javax.swing.JPanel btn_edit1_supp;
    private javax.swing.JPanel btn_edit_bill;
    private javax.swing.JPanel btn_edit_food;
    private javax.swing.JPanel btn_edit_food_edit;
    private javax.swing.JPanel btn_edit_item;
    private javax.swing.JPanel btn_edit_item_edit;
    private javax.swing.JPanel btn_edit_pet;
    private javax.swing.JPanel btn_edit_pet_edit;
    private javax.swing.JPanel btn_edit_staff;
    private javax.swing.JPanel btn_edit_staff_edit;
    private javax.swing.JPanel btn_edit_sup;
    private javax.swing.JPanel btn_edit_supp_edit;
    private javax.swing.JPanel btn_food;
    private javax.swing.JPanel btn_import;
    private javax.swing.JPanel btn_import_statistical;
    private javax.swing.JPanel btn_item;
    private javax.swing.JPanel btn_item_add;
    private javax.swing.JPanel btn_pay;
    private javax.swing.JPanel btn_pet;
    private javax.swing.JPanel btn_pet_add;
    private javax.swing.JPanel btn_remove_bill;
    private javax.swing.JPanel btn_remove_bill_remove;
    private javax.swing.JPanel btn_remove_bill_remove1;
    private javax.swing.JPanel btn_remove_bill_search;
    private javax.swing.JPanel btn_remove_bill_search1;
    private javax.swing.JPanel btn_remove_food;
    private javax.swing.JPanel btn_remove_food_remove;
    private javax.swing.JPanel btn_remove_food_remove1;
    private javax.swing.JPanel btn_remove_food_sreach;
    private javax.swing.JPanel btn_remove_food_sreach1;
    private javax.swing.JPanel btn_remove_item;
    private javax.swing.JPanel btn_remove_item_remove;
    private javax.swing.JPanel btn_remove_item_remove1;
    private javax.swing.JPanel btn_remove_item_sreach;
    private javax.swing.JPanel btn_remove_item_sreach1;
    private javax.swing.JPanel btn_remove_pet;
    private javax.swing.JPanel btn_remove_pet_remove;
    private javax.swing.JPanel btn_remove_pet_remove1;
    private javax.swing.JPanel btn_remove_pet_sreach;
    private javax.swing.JPanel btn_remove_pet_sreach1;
    private javax.swing.JPanel btn_remove_staff;
    private javax.swing.JPanel btn_remove_staff_remove;
    private javax.swing.JPanel btn_remove_staff_remove1;
    private javax.swing.JPanel btn_remove_staff_sreach;
    private javax.swing.JPanel btn_remove_staff_sreach1;
    private javax.swing.JPanel btn_remove_sup;
    private javax.swing.JPanel btn_remove_supp_remove;
    private javax.swing.JPanel btn_remove_supp_remove1;
    private javax.swing.JPanel btn_remove_supp_sreach;
    private javax.swing.JPanel btn_remove_supp_sreach1;
    private javax.swing.JPanel btn_sold;
    private javax.swing.JPanel btn_sold_statistical;
    private javax.swing.JPanel btn_stafff_add;
    private javax.swing.JPanel btn_supp_add;
    private javax.swing.JPanel btn_user;
    private javax.swing.JComboBox<String> cbb_add_item_food_for;
    private javax.swing.JComboBox<String> cbb_add_pet_breed_species;
    private javax.swing.JComboBox<String> cbb_add_pet_pet_breed;
    private javax.swing.JComboBox<String> cbb_add_pet_pet_species;
    private javax.swing.JComboBox<String> cbb_add_staff_role;
    private javax.swing.JComboBox<String> cbb_add_supp_item;
    private javax.swing.JComboBox<String> cbb_edit_food_for;
    private javax.swing.JComboBox<String> cbb_edit_pet_breed;
    private javax.swing.JComboBox<String> cbb_edit_pet_species;
    private javax.swing.JComboBox<String> cbb_edit_staff_role;
    private javax.swing.JComboBox<String> cbb_edit_supp_items;
    private javax.swing.JComboBox<String> cbb_pay_add;
    private javax.swing.JPanel edit1_food;
    private javax.swing.JPanel edit1_item;
    private javax.swing.JPanel edit1_pet;
    private javax.swing.JPanel edit1_staff;
    private javax.swing.JPanel edit1_supp;
    private javax.swing.JPanel edit_bill;
    private javax.swing.JPanel edit_food;
    private javax.swing.JPanel edit_item;
    private javax.swing.JPanel edit_pet;
    private javax.swing.JPanel edit_staff;
    private javax.swing.JPanel edit_supp;
    private javax.swing.JScrollPane ffff;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable18;
    private javax.swing.JTable jTable19;
    private javax.swing.JTable jTable20;
    private javax.swing.JTable jTable21;
    private javax.swing.JTable jTable22;
    private javax.swing.JTable jTable23;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JPanel main_bar;
    private javax.swing.JPanel main_title;
    private javax.swing.JPanel mainshow;
    private javax.swing.JPanel panel_account;
    private javax.swing.JPanel panel_add;
    private javax.swing.JPanel panel_bill;
    private javax.swing.JPanel panel_body_account;
    private javax.swing.JPanel panel_body_add;
    private javax.swing.JPanel panel_body_edit;
    private javax.swing.JPanel panel_body_edit1;
    private javax.swing.JPanel panel_body_remove;
    private javax.swing.JPanel panel_body_statistical;
    private javax.swing.JPanel panel_body_warehouse;
    private javax.swing.JPanel panel_edit;
    private javax.swing.JPanel panel_edit1;
    private javax.swing.JPanel panel_header_account;
    private javax.swing.JPanel panel_header_add;
    private javax.swing.JPanel panel_header_bill;
    private javax.swing.JPanel panel_header_edit;
    private javax.swing.JPanel panel_header_edit1;
    private javax.swing.JPanel panel_header_remove;
    private javax.swing.JPanel panel_header_statistical;
    private javax.swing.JPanel panel_header_warehouse;
    private javax.swing.JPanel panel_pay;
    private javax.swing.JPanel panel_remove;
    private javax.swing.JPanel panel_sta_sold_chart;
    private javax.swing.JPanel panel_statistical;
    private javax.swing.JPanel panel_table_bill;
    private javax.swing.JPanel panel_warehouse;
    private javax.swing.JPanel pay_billadd;
    private javax.swing.JPanel pay_listadd;
    private javax.swing.JPanel pay_price;
    private javax.swing.JPanel pb_bill;
    private javax.swing.JPanel pb_logout;
    private javax.swing.JPanel pb_pay;
    private javax.swing.JPanel pb_statistical;
    private javax.swing.JPanel pb_warehouse;
    private javax.swing.JPanel remove_bill;
    private javax.swing.JPanel remove_food;
    private javax.swing.JPanel remove_item;
    private javax.swing.JPanel remove_pet;
    private javax.swing.JPanel remove_staff;
    private javax.swing.JPanel remove_supp;
    private javax.swing.JPanel statistical_chart_import;
    private javax.swing.JPanel statistical_chart_revenue;
    private javax.swing.JPanel statistical_chart_sold;
    private javax.swing.JTable table_acc_admin;
    private javax.swing.JTable table_acc_user;
    private javax.swing.JTable table_bill_import;
    private javax.swing.JTable table_bill_sold;
    private javax.swing.JTable table_edit_food;
    private javax.swing.JTable table_edit_item;
    private javax.swing.JTable table_edit_pet;
    private javax.swing.JTable table_edit_staff;
    private javax.swing.JTable table_edit_supp;
    private javax.swing.JTable table_pay_add;
    private javax.swing.JTable table_pay_bill;
    private javax.swing.JTable table_remove_bill;
    private javax.swing.JTable table_remove_food;
    private javax.swing.JTable table_remove_item;
    private javax.swing.JTable table_remove_pet;
    private javax.swing.JTable table_remove_staff;
    private javax.swing.JTable table_remove_supp;
    private javax.swing.JTable table_warehouse_food;
    private javax.swing.JTable table_warehouse_item;
    private javax.swing.JTable table_warehouse_pet;
    private javax.swing.JTextField txt_add_item_food_amount;
    private javax.swing.JTextField txt_add_item_food_name;
    private javax.swing.JTextField txt_add_item_food_price;
    private javax.swing.JTextField txt_add_item_item_amount;
    private javax.swing.JTextField txt_add_item_item_name;
    private javax.swing.JTextField txt_add_item_item_price;
    private javax.swing.JTextField txt_add_pet_breed_breed;
    private javax.swing.JTextField txt_add_pet_pet_age;
    private javax.swing.JTextField txt_add_pet_pet_amount;
    private javax.swing.JTextField txt_add_pet_pet_price;
    private javax.swing.JTextField txt_add_pet_species;
    private javax.swing.JTextField txt_add_staff_id;
    private javax.swing.JTextField txt_add_staff_mail;
    private javax.swing.JTextField txt_add_staff_name;
    private javax.swing.JTextField txt_add_staff_pass;
    private javax.swing.JTextField txt_add_staff_phone;
    private javax.swing.JTextField txt_add_supp_address;
    private javax.swing.JTextField txt_add_supp_name;
    private javax.swing.JTextField txt_add_supp_note;
    private javax.swing.JTextField txt_add_supp_phone;
    private javax.swing.JTextField txt_edit_food_amount;
    private javax.swing.JLabel txt_edit_food_code;
    private javax.swing.JTextField txt_edit_food_name;
    private javax.swing.JTextField txt_edit_food_price;
    private javax.swing.JTextField txt_edit_item_amount;
    private javax.swing.JLabel txt_edit_item_code;
    private javax.swing.JTextField txt_edit_item_name;
    private javax.swing.JTextField txt_edit_item_price;
    private javax.swing.JTextField txt_edit_pet_age;
    private javax.swing.JTextField txt_edit_pet_amount;
    private javax.swing.JLabel txt_edit_pet_id;
    private javax.swing.JTextField txt_edit_pet_price;
    private javax.swing.JLabel txt_edit_staff_code;
    private javax.swing.JTextField txt_edit_staff_email;
    private javax.swing.JTextField txt_edit_staff_id;
    private javax.swing.JTextField txt_edit_staff_name;
    private javax.swing.JTextField txt_edit_staff_pass;
    private javax.swing.JTextField txt_edit_staff_phone;
    private javax.swing.JTextField txt_edit_supp_address;
    private javax.swing.JLabel txt_edit_supp_code;
    private javax.swing.JTextField txt_edit_supp_name;
    private javax.swing.JTextField txt_edit_supp_note;
    private javax.swing.JTextField txt_edit_supp_phone;
    private javax.swing.JTextField txt_pay_customer;
    private javax.swing.JTextField txt_pay_date;
    private javax.swing.JTextField txt_pay_recive;
    private javax.swing.JTextField txt_pay_repay;
    private javax.swing.JLabel txt_pay_staff;
    private javax.swing.JTextField txt_pay_total;
    private javax.swing.JTextField txt_phone;
    private javax.swing.JTextField txt_remove_bill_search;
    private javax.swing.JTextField txt_remove_food_search;
    private javax.swing.JTextField txt_remove_item_search;
    private javax.swing.JTextField txt_remove_pet_search;
    private javax.swing.JTextField txt_remove_staff_search;
    private javax.swing.JTextField txt_remove_supp_search;
    private javax.swing.JPanel warehouse_food;
    private javax.swing.JPanel warehouse_item;
    private javax.swing.JPanel warehouse_pet;
    // End of variables declaration//GEN-END:variables

}
