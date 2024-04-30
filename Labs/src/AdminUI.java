
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author parham
 */
public class AdminUI extends javax.swing.JFrame {

    Login login;
    MobileCompany mobileCompany;

    /**
     * Creates new form AdminUI
     */
    public AdminUI(Login login, MobileCompany mobileCompany) {
        initComponents();
        this.login = login;
        this.mobileCompany = mobileCompany;
        reportCompanyLabel.setText(" Report Company for " + mobileCompany.getName());
        companyTextArea.setEditable(false);
        findUserTextArea.setEditable(false);

        for (User user : mobileCompany.getUser().values()) {
            userComboBox1.addItem(user.getUserID() + "");
        }
        for (User user : mobileCompany.getUser().values()) {
            usersComboBox2.addItem(user.getUserID() + "");
        }
        userFillTabel();
        fillTable();
        fillTabel2();
        removeTabWithTitle("Filter Plan");
    }

    private void removeTabWithTitle(String tabTitleToRemove) {
        for (int i = 0; i < UserTabbedPane.getTabCount(); i++) {
            String tabTitle = UserTabbedPane.getTitleAt(i);
            if (tabTitle.equals(tabTitleToRemove)) {
                UserTabbedPane.remove(i);
                break;
            }
        }
    }

    public void fillTable() {
        String[] header = {"Plan ID", "Username", "Model", "Type", "Memory Size", "handset Price", "Internet Quota", "Caplimit", "Expiry date", "City", "ABN", "NO Employee", "Monthly Payment"};
        DefaultTableModel model = new DefaultTableModel(header, 0);
        planInformationTable.setModel(model);
        String text = (String) usersComboBox2.getSelectedItem();
        int id = Integer.parseInt(text);
        User user = mobileCompany.findUser(id);
        for (MobilePlan plan : user.getPlans().values()) {
            String[] row = new String[13];
            row[0] = plan.getId() + "";
            row[1] = plan.userName;
            row[2] = plan.getMobileModel();
            row[3] = plan.handSet.getMobileType().toString();
            row[4] = plan.handSet.getMemorySize() + "";
            row[5] = plan.handSet.getPrice() + "";
            row[6] = plan.internetQuota + "";
            row[7] = plan.capLimit + "";
            row[8] = plan.getExpiryDate().toString();
            if (plan instanceof PersonalPlan) {
                row[9] = ((PersonalPlan) plan).city;
                row[10] = "";
                row[11] = "";
            } else {
                row[9] = "";
                row[10] = ((BusinessPlan) plan).ABN + "";
            }
            row[12] = String.format("$%.2f", plan.calcPay(10));
            model.addRow(row);

        }
    }

    public void userFillTabel() {
        String[] header = {"Name", "User ID", "City", "Suburb", "Street", "StreeNum"};
        DefaultTableModel model = new DefaultTableModel(header, 0);
        userReportTable.setModel(model);
        String text = (String) userComboBox1.getSelectedItem();
        int id = Integer.parseInt(text);
        User user = mobileCompany.findUser(id);
        String[] row = new String[6];
        row[0] = user.getName();
        row[1] = user.getUserID() + "";
        row[2] = user.getCity();
        row[3] = user.getSuburb();
        row[4] = user.getStreet();
        row[5] = user.getStreetNum() + "";
        model.addRow(row);
    }

    public void fillTabel2() {
        if (cityRadioButton.isSelected()) {
            String[] header = {"City Name", "Total Monthly Payment"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            reportTable.setModel(model);
            ArrayList<String> cities = mobileCompany.populateDistinctCityNames();
            String[] row = new String[2];
            for (String city : cities) {
                row[0] = city;
                row[1] = mobileCompany.getTotalPaymentForCity(city) + "";
                model.addRow(row);
            }
        }
        if (modelRadioButton.isSelected()) {
            String[] header = {"Mobile Model", "Total Payment", "Average"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            reportTable.setModel(model);
            ArrayList<String> models = mobileCompany.populateDistinctMobileModels();
            HashMap<String, Integer> values = mobileCompany.getTotalCountPerMobileModel();
            HashMap<String, Double> payments = mobileCompany.getTotalPaymentPerMobileModel();
            ArrayList<Integer> countPerModel = new ArrayList<>();
            ArrayList<Double> monthlyPayment = new ArrayList<>();
            for (Integer value : values.values()) {
                countPerModel.add(value);
            }
            for (Double payment : payments.values()) {
                monthlyPayment.add(payment);
            }
            String[] row = new String[3];
            for (int i = 0; i < models.size(); i++) {
                row[0] = models.get(i);
                row[1] = countPerModel.get(i) + "";
                row[2] = monthlyPayment.get(i) / countPerModel.get(i) + "";
                model.addRow(row);
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        UserTabbedPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        findUserTextField = new javax.swing.JTextField();
        findButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        findUserTextArea = new javax.swing.JTextArea();
        logoutButton1 = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        userComboBox1 = new javax.swing.JComboBox<>();
        userLabel1 = new javax.swing.JLabel();
        logoutButton2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        userReportTable = new javax.swing.JTable();
        editButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        userLabel2 = new javax.swing.JLabel();
        usersComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        planInformationTable = new javax.swing.JTable();
        logoutButton3 = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        editButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        reportButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        companyTextArea = new javax.swing.JTextArea();
        reportCompanyLabel = new javax.swing.JLabel();
        logoutButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        filterTable = new javax.swing.JTable();
        FilterButton = new javax.swing.JButton();
        LogoutButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        modelField = new javax.swing.JTextField();
        dateField = new javax.swing.JTextField();
        dateLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cityRadioButton = new javax.swing.JRadioButton();
        modelRadioButton = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        reportTable = new javax.swing.JTable();
        repButton = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        findUserTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findUserTextFieldActionPerformed(evt);
            }
        });

        findButton.setText("Find");
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });

        findUserTextArea.setColumns(20);
        findUserTextArea.setRows(5);
        jScrollPane3.setViewportView(findUserTextArea);

        logoutButton1.setText("Logout");
        logoutButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButton1ActionPerformed(evt);
            }
        });

        userLabel.setText("UserI D");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(107, Short.MAX_VALUE)
                        .addComponent(userLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(findUserTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(findButton)
                            .addComponent(logoutButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(findUserTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userLabel))
                .addGap(53, 53, 53)
                .addComponent(findButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton1)
                .addGap(127, 127, 127))
        );

        UserTabbedPane.addTab("Find User", jPanel2);

        userComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                userComboBox1ItemStateChanged(evt);
            }
        });
        userComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userComboBox1ActionPerformed(evt);
            }
        });

        userLabel1.setText("Users");

        logoutButton2.setText("Logout");
        logoutButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButton2ActionPerformed(evt);
            }
        });

        userReportTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(userReportTable);

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(userLabel1)
                        .addGap(37, 37, 37)
                        .addComponent(userComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editButton)
                            .addComponent(logoutButton2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userLabel1))
                        .addGap(150, 150, 150)
                        .addComponent(logoutButton2)
                        .addGap(117, 117, 117)
                        .addComponent(editButton))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(165, Short.MAX_VALUE))
        );

        UserTabbedPane.addTab("User Report", jPanel3);

        userLabel2.setText("Users");

        usersComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                usersComboBox2ItemStateChanged(evt);
            }
        });
        usersComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersComboBox2ActionPerformed(evt);
            }
        });

        planInformationTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(planInformationTable);

        logoutButton3.setText("Logout");
        logoutButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButton3ActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        editButton2.setText("Edit");
        editButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editButton2MouseClicked(evt);
            }
        });
        editButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(userLabel2)
                        .addGap(33, 33, 33)
                        .addComponent(usersComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(editButton2)
                        .addGap(76, 76, 76)
                        .addComponent(deleteButton)
                        .addGap(74, 74, 74)
                        .addComponent(logoutButton3)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userLabel2)
                    .addComponent(usersComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoutButton3)
                    .addComponent(deleteButton)
                    .addComponent(editButton2))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        UserTabbedPane.addTab("Plans Information", jPanel4);

        reportButton.setText("Report");
        reportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportButtonActionPerformed(evt);
            }
        });

        companyTextArea.setColumns(20);
        companyTextArea.setRows(5);
        jScrollPane4.setViewportView(companyTextArea);

        reportCompanyLabel.setText("Report Company");

        logoutButton4.setText("Logout");
        logoutButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(reportButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(logoutButton4)))
                        .addGap(76, 76, 76)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(reportCompanyLabel)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(reportButton)
                        .addGap(200, 200, 200)
                        .addComponent(logoutButton4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(reportCompanyLabel)
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(240, Short.MAX_VALUE))
        );

        UserTabbedPane.addTab("Report Company", jPanel1);

        filterTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(filterTable);

        FilterButton.setText("Filter");
        FilterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterButtonActionPerformed(evt);
            }
        });

        LogoutButton5.setText("Logout");

        jLabel1.setText("Mobile Model");

        modelField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modelFieldActionPerformed(evt);
            }
        });
        modelField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                modelFieldKeyReleased(evt);
            }
        });

        dateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateFieldActionPerformed(evt);
            }
        });

        dateLabel.setText("Expiry Date");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addGap(173, 173, 173)
                            .addComponent(FilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                            .addComponent(LogoutButton5))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addGap(137, 137, 137)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(dateLabel))
                            .addGap(56, 56, 56)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(modelField, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                .addComponent(dateField)))))
                .addContainerGap(181, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(modelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateLabel))
                .addGap(44, 44, 44)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FilterButton)
                    .addComponent(LogoutButton5))
                .addContainerGap(99, Short.MAX_VALUE))
        );

        UserTabbedPane.addTab("Filter Plan", jPanel5);

        jLabel3.setText("Report City");

        buttonGroup1.add(cityRadioButton);
        cityRadioButton.setText("City");

        buttonGroup1.add(modelRadioButton);
        modelRadioButton.setText("Model");
        modelRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modelRadioButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Report Model");

        reportTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(reportTable);

        repButton.setText("Report");
        repButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(modelRadioButton)
                                .addGap(111, 111, 111)
                                .addComponent(repButton))
                            .addComponent(cityRadioButton))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(cityRadioButton))
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(modelRadioButton)
                    .addComponent(repButton))
                .addContainerGap(143, Short.MAX_VALUE))
        );

        UserTabbedPane.addTab(" Report Data", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UserTabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UserTabbedPane))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modelRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modelRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modelRadioButtonActionPerformed

    private void dateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateFieldActionPerformed

    private void modelFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modelFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modelFieldActionPerformed

    private void logoutButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButton4ActionPerformed
        this.dispose();
        login.setVisible(true);
    }//GEN-LAST:event_logoutButton4ActionPerformed

    private void reportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportButtonActionPerformed
        companyTextArea.append(mobileCompany.toString());
    }//GEN-LAST:event_reportButtonActionPerformed

    private void editButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButton2ActionPerformed
        int selectedRow = planInformationTable.getSelectedRow();
        ArrayList<String> cities = mobileCompany.populateDistinctCityNames();
        String text = (String) usersComboBox2.getSelectedItem();
        int id = Integer.parseInt(text);
        User user = mobileCompany.findUser(id);
        UserUi userUI = new UserUi(login, user, cities);
        if (selectedRow >= 0) {
            UpdateUI upUI = new UpdateUI(user.getPlans().get(selectedRow), userUI);
            upUI.setVisible(true);
        }

    }//GEN-LAST:event_editButton2ActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed

        String text = (String) usersComboBox2.getSelectedItem();
        int id = Integer.parseInt(text);
        int line = planInformationTable.getSelectedRow();
        User user = mobileCompany.findUser(id);
        if (line >= 0) {
            user.getPlans().remove(Integer.parseInt(planInformationTable.getValueAt(line, 0).toString()));
            fillTable();
            JOptionPane.showMessageDialog(this, " The plan was deleted ");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void logoutButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButton3ActionPerformed
        this.dispose();
        login.setVisible(true);
    }//GEN-LAST:event_logoutButton3ActionPerformed

    private void usersComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_usersComboBox2ItemStateChanged
        fillTable();
    }//GEN-LAST:event_usersComboBox2ItemStateChanged

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        ArrayList<String> cities = mobileCompany.populateDistinctCityNames();
        String text = (String) userComboBox1.getSelectedItem();
        int id = Integer.parseInt(text);
        User user = mobileCompany.findUser(id);
        UserUi userUI = new UserUi(login, user, cities);
        userUI.setVisible(true);
    }//GEN-LAST:event_editButtonActionPerformed

    private void logoutButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButton2ActionPerformed
        this.dispose();
        login.setVisible(true);
    }//GEN-LAST:event_logoutButton2ActionPerformed

    private void userComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userComboBox1ActionPerformed

    }//GEN-LAST:event_userComboBox1ActionPerformed

    private void userComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_userComboBox1ItemStateChanged
        userFillTabel();
    }//GEN-LAST:event_userComboBox1ItemStateChanged

    private void logoutButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButton1ActionPerformed
        this.dispose();
        login.setVisible(true);
    }//GEN-LAST:event_logoutButton1ActionPerformed

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed

        try {
            User user = mobileCompany.findUser(Integer.parseInt(findUserTextField.getText()));
            if (user != null) {
                findUserTextArea.append(user.toString() + " \n ");
            } else {
                findUserTextArea.append(" User with this user ID " + findUserTextArea.getText() + " isn't find. \n ");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_findButtonActionPerformed

    private void findUserTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findUserTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_findUserTextFieldActionPerformed

    private void repButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repButtonActionPerformed
        fillTabel2();
    }//GEN-LAST:event_repButtonActionPerformed

    private void FilterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterButtonActionPerformed
        //   filterTable();
    }//GEN-LAST:event_FilterButtonActionPerformed

    private void modelFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modelFieldKeyReleased
        //  filterTable();
    }//GEN-LAST:event_modelFieldKeyReleased

    private void editButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButton2MouseClicked
          int selectedRow = planInformationTable.getSelectedRow();
        ArrayList<String> cities = mobileCompany.populateDistinctCityNames();
        String text = (String) usersComboBox2.getSelectedItem();
        int id = Integer.parseInt(text);
        User user = mobileCompany.findUser(id);
        UserUi userUI = new UserUi(login, user, cities);
        if (selectedRow >= 0) {
            UpdateUI upUI = new UpdateUI(user.getPlans().get(selectedRow), userUI);
            upUI.setVisible(true);
        }

    }//GEN-LAST:event_editButton2MouseClicked

    private void usersComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usersComboBox2ActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new AdminUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton FilterButton;
    private javax.swing.JButton LogoutButton5;
    private javax.swing.JTabbedPane UserTabbedPane;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton cityRadioButton;
    private javax.swing.JTextArea companyTextArea;
    private javax.swing.JTextField dateField;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton editButton2;
    private javax.swing.JTable filterTable;
    private javax.swing.JButton findButton;
    private javax.swing.JTextArea findUserTextArea;
    private javax.swing.JTextField findUserTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JButton logoutButton1;
    private javax.swing.JButton logoutButton2;
    private javax.swing.JButton logoutButton3;
    private javax.swing.JButton logoutButton4;
    private javax.swing.JTextField modelField;
    private javax.swing.JRadioButton modelRadioButton;
    private javax.swing.JTable planInformationTable;
    private javax.swing.JButton repButton;
    private javax.swing.JButton reportButton;
    private javax.swing.JLabel reportCompanyLabel;
    private javax.swing.JTable reportTable;
    private javax.swing.JComboBox<String> userComboBox1;
    private javax.swing.JLabel userLabel;
    private javax.swing.JLabel userLabel1;
    private javax.swing.JLabel userLabel2;
    private javax.swing.JTable userReportTable;
    private javax.swing.JComboBox<String> usersComboBox2;
    // End of variables declaration//GEN-END:variables
}
