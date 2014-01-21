/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracleproject;

import java.awt.CardLayout;
import java.awt.Color;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Vincenzo Prignano <vincenzo.prignano@gmail.com>
 * @author Valerio Russo <russovalerio.92@gmail.com>
 * 
 */
public class MainFrame extends javax.swing.JFrame {
    
    private Connection connection;
    private int dialogSet;

    void showErr(String error) {
        errorDialog.setVisible(true);
        errText.setText(error);
    }
    
    // Costruttore 
    public MainFrame() {
        try {
            initComponents();
            connection = SQL();
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // SQL Connection condivisa
    private static Connection SQL() throws SQLException{
        OracleDataSource oracleDataSource = new OracleDataSource();
        oracleDataSource.setDriverType("thin");
        oracleDataSource.setPortNumber(1521);
        oracleDataSource.setDatabaseName("xe");
        oracleDataSource.setServerName("143.225.117.240");
        oracleDataSource.setUser("grp2_02");
        oracleDataSource.setPassword("o8f0Fayd");
        return oracleDataSource.getConnection();
    }
    
    private static Connection customSQL(String host, String user, char[] password1, int port) throws SQLException{
        OracleDataSource oracleDataSource = new OracleDataSource();
        oracleDataSource.setDriverType("thin");
        oracleDataSource.setPortNumber(port);
        oracleDataSource.setDatabaseName("xe");
        oracleDataSource.setServerName(host);
        oracleDataSource.setUser(user);
        oracleDataSource.setPassword(new String(password1));
        return oracleDataSource.getConnection();
    }

    public DefaultTableModel mapResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(resultSet.getObject(columnIndex));
            }
            data.add(vector);
        }

        
        
        return new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }
    
    public DefaultComboBoxModel mapBox(ResultSet resultSet) throws SQLException {
        
        ResultSetMetaData metaData = resultSet.getMetaData();
        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }
        
        return new DefaultComboBoxModel(columnNames);
    }
    
    public void searchBoxSet(String tableName, JComboBox box, JTextField inputBox, JTable table) throws SQLException {
        Statement statement = connection.createStatement();
        String query = new String();
        if (tableName.equals("State")) {
            query = "SELECT * FROM " + "StateVertex " + " NATURAL JOIN " + tableName + " WHERE " 
                    + box.getSelectedItem().toString() + " LIKE " +
                "'%" + inputBox.getText() + "%'";
        } else {
            query = "SELECT * FROM " + tableName + " WHERE " + box.getSelectedItem().toString() + " LIKE " +
                "'%" + inputBox.getText() + "%'";
        }
        try {
            ResultSet rs =  statement.executeQuery(query);
            table.setModel(mapResultSet(rs));
        } catch (SQLException e) {
            showErr(e.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, e);
        }        
    }
               
    
    void showDialog(int setDialog) {
        availDialog.setVisible(true);
        dialogSet = setDialog;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        availDialog = new javax.swing.JDialog();
        jScrollPane4 = new javax.swing.JScrollPane();
        availableTable = new javax.swing.JTable();
        chooseDialog = new javax.swing.JButton();
        errorDialog = new javax.swing.JDialog();
        jScrollPane7 = new javax.swing.JScrollPane();
        errText = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        defaultView = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        listSMView = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listSMTable = new javax.swing.JTable();
        deleteButtonListSM = new javax.swing.JButton();
        refreshListSM = new javax.swing.JButton();
        searchSMBox = new javax.swing.JComboBox();
        searchSMInput = new javax.swing.JTextField();
        searchButtonSM = new javax.swing.JButton();
        addSMView = new javax.swing.JPanel();
        authorSM = new javax.swing.JLabel();
        nameSM = new javax.swing.JLabel();
        descSM = new javax.swing.JLabel();
        pathSM = new javax.swing.JLabel();
        authorSMInput = new javax.swing.JTextField();
        nameSMInput = new javax.swing.JTextField();
        descSMInput = new javax.swing.JTextField();
        pathSMInput = new javax.swing.JTextField();
        addSMButton = new javax.swing.JButton();
        idSM = new javax.swing.JLabel();
        idSMInput = new javax.swing.JTextField();
        statusSM = new javax.swing.JLabel();
        statusSM.setVisible(false);
        modSMView = new javax.swing.JPanel();
        authorSM1 = new javax.swing.JLabel();
        nameSM1 = new javax.swing.JLabel();
        descSM1 = new javax.swing.JLabel();
        pathSM1 = new javax.swing.JLabel();
        authorSMInput1 = new javax.swing.JTextField();
        nameSMInput1 = new javax.swing.JTextField();
        descSMInput1 = new javax.swing.JTextField();
        pathSMInput1 = new javax.swing.JTextField();
        addSMButton1 = new javax.swing.JButton();
        idSM1 = new javax.swing.JLabel();
        idSMInput1 = new javax.swing.JTextField();
        statusSM1 = new javax.swing.JLabel();
        statusSM1.setVisible(false);
        chooseModSM = new javax.swing.JButton();
        listStatesView = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listStatesTable = new javax.swing.JTable();
        deleteButtonStates = new javax.swing.JButton();
        refreshListStates = new javax.swing.JButton();
        searchStatesBox = new javax.swing.JComboBox();
        searchStatesInput = new javax.swing.JTextField();
        searchButtonStates = new javax.swing.JButton();
        addStateView = new javax.swing.JPanel();
        idState = new javax.swing.JLabel();
        nameState = new javax.swing.JLabel();
        SMState = new javax.swing.JLabel();
        visibilityState = new javax.swing.JLabel();
        SMStateInput = new javax.swing.JComboBox();
        visibilityStateInput = new javax.swing.JComboBox();
        idStateInput = new javax.swing.JTextField();
        descStateInput = new javax.swing.JTextField();
        isTopInput = new javax.swing.JCheckBox();
        isFinalInput = new javax.swing.JCheckBox();
        addStateButton = new javax.swing.JButton();
        statusState = new javax.swing.JLabel();
        statusState.setVisible(false);
        descState = new javax.swing.JLabel();
        nameStateInput1 = new javax.swing.JTextField();
        modStateView = new javax.swing.JPanel();
        idState1 = new javax.swing.JLabel();
        nameState1 = new javax.swing.JLabel();
        SMState1 = new javax.swing.JLabel();
        visibilityState1 = new javax.swing.JLabel();
        SMStateInput1 = new javax.swing.JComboBox();
        visibilityStateInput1 = new javax.swing.JComboBox();
        idStateInput1 = new javax.swing.JTextField();
        descStateInput1 = new javax.swing.JTextField();
        isTopInput1 = new javax.swing.JCheckBox();
        isFinalInput1 = new javax.swing.JCheckBox();
        addStateButton1 = new javax.swing.JButton();
        statusState1 = new javax.swing.JLabel();
        statusState1.setVisible(false);
        descState1 = new javax.swing.JLabel();
        nameStateInput2 = new javax.swing.JTextField();
        chooseModState = new javax.swing.JButton();
        listTransitionsView = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listTransitionsTable = new javax.swing.JTable();
        refreshListTransitions = new javax.swing.JButton();
        deleteButtonTransition = new javax.swing.JButton();
        searchTransitionsBox = new javax.swing.JComboBox();
        searchTransitionsInput = new javax.swing.JTextField();
        searchButtonTransitions = new javax.swing.JButton();
        addTransitionView = new javax.swing.JPanel();
        idTransition = new javax.swing.JLabel();
        nameTransition = new javax.swing.JLabel();
        visibilityTransition = new javax.swing.JLabel();
        smTransition = new javax.swing.JLabel();
        sourceTransition = new javax.swing.JLabel();
        targetTransition = new javax.swing.JLabel();
        sourceTransitionInput = new javax.swing.JTextField();
        nameTransitionInput = new javax.swing.JTextField();
        visibilityTransitionInput = new javax.swing.JComboBox();
        SMTransitionInput = new javax.swing.JComboBox();
        addTransitionButton = new javax.swing.JButton();
        statusTransition = new javax.swing.JLabel();
        statusTransition.setVisible(false);
        chooseSourceTransition = new javax.swing.JButton();
        idTransitionInput = new javax.swing.JTextField();
        targetTransitionInput = new javax.swing.JTextField();
        chooseTargetTransition = new javax.swing.JButton();
        modTransitionView = new javax.swing.JPanel();
        idTransition1 = new javax.swing.JLabel();
        nameTransition1 = new javax.swing.JLabel();
        visibilityTransition1 = new javax.swing.JLabel();
        smTransition1 = new javax.swing.JLabel();
        sourceTransition1 = new javax.swing.JLabel();
        targetTransition1 = new javax.swing.JLabel();
        sourceTransitionInput1 = new javax.swing.JTextField();
        nameTransitionInput1 = new javax.swing.JTextField();
        visibilityTransitionInput1 = new javax.swing.JComboBox();
        SMTransitionInput1 = new javax.swing.JComboBox();
        addTransitionButton1 = new javax.swing.JButton();
        statusTransition1 = new javax.swing.JLabel();
        statusTransition1.setVisible(false);
        chooseSourceTransition1 = new javax.swing.JButton();
        idTransitionInput1 = new javax.swing.JTextField();
        targetTransitionInput1 = new javax.swing.JTextField();
        chooseTargetTransition1 = new javax.swing.JButton();
        chooseTransitionMod = new javax.swing.JButton();
        listActionsView = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listActionsTable = new javax.swing.JTable();
        deleteButtonActions = new javax.swing.JButton();
        refreshListActions = new javax.swing.JButton();
        searchButtonActions = new javax.swing.JButton();
        searchActionsInput = new javax.swing.JTextField();
        searchActionsBox = new javax.swing.JComboBox();
        addActionView = new javax.swing.JPanel();
        idActionInput = new javax.swing.JTextField();
        stateActionInput = new javax.swing.JTextField();
        stateAction = new javax.swing.JLabel();
        idAction = new javax.swing.JLabel();
        chooseStateAction = new javax.swing.JButton();
        transitionActionInput = new javax.swing.JTextField();
        chooseTransitionAction = new javax.swing.JButton();
        statusAction = new javax.swing.JLabel();
        statusAction.setVisible(false);
        addActionButton = new javax.swing.JButton();
        visibilityActionInput = new javax.swing.JComboBox();
        nameActionInput = new javax.swing.JTextField();
        transitionAction = new javax.swing.JLabel();
        visibilityAction = new javax.swing.JLabel();
        nameAction = new javax.swing.JLabel();
        smAction = new javax.swing.JLabel();
        SMActionInput = new javax.swing.JComboBox();
        modActionView = new javax.swing.JPanel();
        idActionInput1 = new javax.swing.JTextField();
        stateActionInput1 = new javax.swing.JTextField();
        stateAction1 = new javax.swing.JLabel();
        idAction1 = new javax.swing.JLabel();
        chooseStateAction1 = new javax.swing.JButton();
        transitionActionInput1 = new javax.swing.JTextField();
        chooseTransitionAction1 = new javax.swing.JButton();
        statusAction1 = new javax.swing.JLabel();
        statusAction1.setVisible(false);
        addActionButton1 = new javax.swing.JButton();
        visibilityActionInput1 = new javax.swing.JComboBox();
        nameActionInput1 = new javax.swing.JTextField();
        transitionAction1 = new javax.swing.JLabel();
        visibilityAction1 = new javax.swing.JLabel();
        nameAction1 = new javax.swing.JLabel();
        smAction1 = new javax.swing.JLabel();
        SMActionInput1 = new javax.swing.JComboBox();
        chooseModAction = new javax.swing.JButton();
        listEventsView = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        listEventsTable = new javax.swing.JTable();
        deleteButtonEvents = new javax.swing.JButton();
        refreshListEvents = new javax.swing.JButton();
        searchEventsInput = new javax.swing.JTextField();
        searchEventsBox = new javax.swing.JComboBox();
        searchButtonEvents = new javax.swing.JButton();
        addEventView = new javax.swing.JPanel();
        idEventInput = new javax.swing.JTextField();
        stateEventInput = new javax.swing.JTextField();
        stateEvent = new javax.swing.JLabel();
        idEvent = new javax.swing.JLabel();
        chooseStateEvent = new javax.swing.JButton();
        transitionEventInput = new javax.swing.JTextField();
        chooseTransitionEvent = new javax.swing.JButton();
        statusEvent = new javax.swing.JLabel();
        statusEvent.setVisible(false);
        addEventButton = new javax.swing.JButton();
        visibilityEventInput = new javax.swing.JComboBox();
        nameEventInput = new javax.swing.JTextField();
        transitionEvent = new javax.swing.JLabel();
        visibilityEvent = new javax.swing.JLabel();
        nameEvent = new javax.swing.JLabel();
        smEvent = new javax.swing.JLabel();
        SMEventInput = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        typeEventInput = new javax.swing.JComboBox();
        exprEv = new javax.swing.JLabel();
        exprEvent = new javax.swing.JTextField();
        modEventView = new javax.swing.JPanel();
        idEventInput1 = new javax.swing.JTextField();
        stateEventInput1 = new javax.swing.JTextField();
        stateEvent1 = new javax.swing.JLabel();
        idEvent1 = new javax.swing.JLabel();
        chooseStateEvent1 = new javax.swing.JButton();
        transitionEventInput1 = new javax.swing.JTextField();
        chooseTransitionEvent1 = new javax.swing.JButton();
        statusEvent1 = new javax.swing.JLabel();
        statusEvent1.setVisible(false);
        addEventButton1 = new javax.swing.JButton();
        visibilityEventInput1 = new javax.swing.JComboBox();
        nameEventInput1 = new javax.swing.JTextField();
        transitionEvent1 = new javax.swing.JLabel();
        visibilityEvent1 = new javax.swing.JLabel();
        nameEvent1 = new javax.swing.JLabel();
        smEvent1 = new javax.swing.JLabel();
        SMEventInput1 = new javax.swing.JComboBox();
        chooseModEvent = new javax.swing.JButton();
        defaultView1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        usernameInput = new javax.swing.JTextField();
        hostInput = new javax.swing.JTextField();
        passwordInput = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        portInput = new javax.swing.JTextField();
        statusEvent2 = new javax.swing.JLabel();
        statusEvent2.setVisible(false);
        jMenuBar1 = new javax.swing.JMenuBar();
        mainMenu = new javax.swing.JMenu();
        changeServer = new javax.swing.JMenuItem();
        MenuItemExit = new javax.swing.JMenuItem();
        stateMachinesMenu = new javax.swing.JMenu();
        listSM = new javax.swing.JMenuItem();
        addSM = new javax.swing.JMenuItem();
        modSM = new javax.swing.JMenuItem();
        statesMenu = new javax.swing.JMenu();
        listStates = new javax.swing.JMenuItem();
        addState = new javax.swing.JMenuItem();
        modState = new javax.swing.JMenuItem();
        transitionsMenu = new javax.swing.JMenu();
        listTransitions = new javax.swing.JMenuItem();
        addTransition = new javax.swing.JMenuItem();
        modTransition = new javax.swing.JMenuItem();
        modMenu = new javax.swing.JMenu();
        listActions = new javax.swing.JMenuItem();
        addAction = new javax.swing.JMenuItem();
        modAction = new javax.swing.JMenuItem();
        eventsMenu = new javax.swing.JMenu();
        listEvents = new javax.swing.JMenuItem();
        addEvent = new javax.swing.JMenuItem();
        modEvent = new javax.swing.JMenuItem();

        availDialog.setMinimumSize(new java.awt.Dimension(500, 520));
        availDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        availableTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        availableTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(availableTable);

        availDialog.getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 450, 390));

        chooseDialog.setText("Scegli");
        chooseDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseDialogActionPerformed(evt);
            }
        });
        availDialog.getContentPane().add(chooseDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 440, 92, -1));

        errorDialog.setLocation(new java.awt.Point(100, 150));
        errorDialog.setMinimumSize(new java.awt.Dimension(340, 220));

        errText.setColumns(20);
        errText.setRows(5);
        jScrollPane7.setViewportView(errText);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("Errore");

        org.jdesktop.layout.GroupLayout errorDialogLayout = new org.jdesktop.layout.GroupLayout(errorDialog.getContentPane());
        errorDialog.getContentPane().setLayout(errorDialogLayout);
        errorDialogLayout.setHorizontalGroup(
            errorDialogLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(errorDialogLayout.createSequentialGroup()
                .add(errorDialogLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(errorDialogLayout.createSequentialGroup()
                        .add(35, 35, 35)
                        .add(jScrollPane7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 267, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(errorDialogLayout.createSequentialGroup()
                        .add(127, 127, 127)
                        .add(jLabel2)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        errorDialogLayout.setVerticalGroup(
            errorDialogLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, errorDialogLayout.createSequentialGroup()
                .add(18, 18, 18)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Oracle Project - Group #2");
        setName("MainFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(500, 500));

        mainPanel.setLayout(new java.awt.CardLayout());

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Il programma in automatico si \nconnette al database dell'universita'.\n\nPer modificare la connessione\nFile -> Server");
        jTextArea1.setDragEnabled(false);
        jTextArea1.setFocusable(false);
        jScrollPane8.setViewportView(jTextArea1);

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        jLabel8.setText("State Machines");

        org.jdesktop.layout.GroupLayout defaultViewLayout = new org.jdesktop.layout.GroupLayout(defaultView);
        defaultView.setLayout(defaultViewLayout);
        defaultViewLayout.setHorizontalGroup(
            defaultViewLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(defaultViewLayout.createSequentialGroup()
                .add(defaultViewLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(defaultViewLayout.createSequentialGroup()
                        .add(109, 109, 109)
                        .add(jScrollPane8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(defaultViewLayout.createSequentialGroup()
                        .add(101, 101, 101)
                        .add(jLabel8)))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        defaultViewLayout.setVerticalGroup(
            defaultViewLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(defaultViewLayout.createSequentialGroup()
                .add(62, 62, 62)
                .add(jLabel8)
                .add(74, 74, 74)
                .add(jScrollPane8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(288, Short.MAX_VALUE))
        );

        mainPanel.add(defaultView, "defaultView");

        listSMView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listSMTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        listSMTable.getTableHeader().setReorderingAllowed(false);
        listSMTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                listSMTablePropertyChange(evt);
            }
        });
        listSMTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                listSMTableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(listSMTable);

        listSMView.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 68, -1, 330));

        deleteButtonListSM.setText("Cancella");
        deleteButtonListSM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonListSMActionPerformed(evt);
            }
        });
        listSMView.add(deleteButtonListSM, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 410, -1, -1));

        refreshListSM.setText("Aggiorna");
        refreshListSM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshListSMActionPerformed(evt);
            }
        });
        listSMView.add(refreshListSM, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        listSMView.add(searchSMBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, -1));
        listSMView.add(searchSMInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 220, -1));

        searchButtonSM.setText("Cerca");
        searchButtonSM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonSMActionPerformed(evt);
            }
        });
        listSMView.add(searchButtonSM, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        mainPanel.add(listSMView, "listSMView");

        addSMView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        authorSM.setText("Autore:");
        addSMView.add(authorSM, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        nameSM.setText("ID:");
        addSMView.add(nameSM, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        descSM.setText("Descrizione:");
        addSMView.add(descSM, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        pathSM.setText("Path:");
        addSMView.add(pathSM, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));
        addSMView.add(authorSMInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 240, -1));
        addSMView.add(nameSMInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 240, -1));
        addSMView.add(descSMInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 240, -1));
        addSMView.add(pathSMInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 240, -1));

        addSMButton.setText("Aggiungi");
        addSMButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSMButtonActionPerformed(evt);
            }
        });
        addSMView.add(addSMButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, -1, -1));

        idSM.setText("Nome:");
        addSMView.add(idSM, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        idSMInput.setEditable(false);
        idSMInput.setBackground(new java.awt.Color(204, 204, 204));
        addSMView.add(idSMInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 240, -1));

        statusSM.setText("Text");
        addSMView.add(statusSM, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, -1, -1));

        mainPanel.add(addSMView, "addSMView");

        modSMView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        authorSM1.setText("Autore:");
        modSMView.add(authorSM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        nameSM1.setText("ID:");
        modSMView.add(nameSM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        descSM1.setText("Descrizione:");
        modSMView.add(descSM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        pathSM1.setText("Path:");
        modSMView.add(pathSM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));
        modSMView.add(authorSMInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 240, -1));
        modSMView.add(nameSMInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 240, -1));
        modSMView.add(descSMInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 240, -1));
        modSMView.add(pathSMInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 240, -1));

        addSMButton1.setText("Modifica");
        addSMButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSMButton1ActionPerformed(evt);
            }
        });
        modSMView.add(addSMButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, -1, -1));

        idSM1.setText("Nome:");
        modSMView.add(idSM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        idSMInput1.setEditable(false);
        idSMInput1.setBackground(new java.awt.Color(204, 204, 204));
        modSMView.add(idSMInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 240, -1));

        statusSM1.setText("Text");
        modSMView.add(statusSM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, -1, -1));

        chooseModSM.setText("Scegli");
        chooseModSM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseModSMActionPerformed(evt);
            }
        });
        modSMView.add(chooseModSM, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        mainPanel.add(modSMView, "modSMView");

        listStatesView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listStatesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        listStatesTable.setShowVerticalLines(false);
        listStatesTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(listStatesTable);

        listStatesView.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 68, -1, 330));

        deleteButtonStates.setText("Cancella");
        deleteButtonStates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonStatesActionPerformed(evt);
            }
        });
        listStatesView.add(deleteButtonStates, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 410, -1, -1));

        refreshListStates.setText("Aggiorna");
        refreshListStates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshListStatesActionPerformed(evt);
            }
        });
        listStatesView.add(refreshListStates, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        listStatesView.add(searchStatesBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, -1));
        listStatesView.add(searchStatesInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 220, -1));

        searchButtonStates.setText("Cerca");
        searchButtonStates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonStatesActionPerformed(evt);
            }
        });
        listStatesView.add(searchButtonStates, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        mainPanel.add(listStatesView, "listStatesView");

        addStateView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idState.setText("ID:");
        addStateView.add(idState, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));

        nameState.setText("Nome:");
        addStateView.add(nameState, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        SMState.setText("State Machine:");
        addStateView.add(SMState, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        visibilityState.setText("Visibility:");
        addStateView.add(visibilityState, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, -1, -1));

        addStateView.add(SMStateInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 170, -1));

        visibilityStateInput.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Public", "Protected", "Private", "Package" }));
        addStateView.add(visibilityStateInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 170, -1));

        idStateInput.setEditable(false);
        idStateInput.setBackground(new java.awt.Color(204, 204, 204));
        addStateView.add(idStateInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 170, -1));
        addStateView.add(descStateInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 170, -1));

        isTopInput.setText("Top State");
        addStateView.add(isTopInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, -1, -1));

        isFinalInput.setText("Final State");
        addStateView.add(isFinalInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, -1, -1));

        addStateButton.setText("Aggiungi");
        addStateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStateButtonActionPerformed(evt);
            }
        });
        addStateView.add(addStateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

        statusState.setText("jLabel1");
        addStateView.add(statusState, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, -1, -1));

        descState.setText("Descrizione:");
        addStateView.add(descState, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));
        addStateView.add(nameStateInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 170, -1));

        mainPanel.add(addStateView, "addStateView");

        modStateView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idState1.setText("ID:");
        modStateView.add(idState1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));

        nameState1.setText("Nome:");
        modStateView.add(nameState1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        SMState1.setText("State Machine:");
        modStateView.add(SMState1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        visibilityState1.setText("Visibility:");
        modStateView.add(visibilityState1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, -1, -1));

        modStateView.add(SMStateInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 170, -1));

        visibilityStateInput1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Public", "Protected", "Private", "Package" }));
        modStateView.add(visibilityStateInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 170, -1));

        idStateInput1.setEditable(false);
        idStateInput1.setBackground(new java.awt.Color(204, 204, 204));
        modStateView.add(idStateInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 170, -1));
        modStateView.add(descStateInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 170, -1));

        isTopInput1.setText("Top State");
        modStateView.add(isTopInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, -1, -1));

        isFinalInput1.setText("Final State");
        modStateView.add(isFinalInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, -1, -1));

        addStateButton1.setText("Modifica");
        addStateButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStateButton1ActionPerformed(evt);
            }
        });
        modStateView.add(addStateButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

        statusState1.setText("jLabel1");
        modStateView.add(statusState1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, -1, -1));

        descState1.setText("Descrizione:");
        modStateView.add(descState1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));
        modStateView.add(nameStateInput2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 170, -1));

        chooseModState.setText("Scegli Stato");
        chooseModState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseModStateActionPerformed(evt);
            }
        });
        modStateView.add(chooseModState, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        mainPanel.add(modStateView, "modStateView");

        listTransitionsView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listTransitionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(listTransitionsTable);

        listTransitionsView.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 63, -1, 338));

        refreshListTransitions.setText("Aggiorna");
        refreshListTransitions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshListTransitionsActionPerformed(evt);
            }
        });
        listTransitionsView.add(refreshListTransitions, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 413, -1, -1));

        deleteButtonTransition.setText("Cancella");
        deleteButtonTransition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonTransitionActionPerformed(evt);
            }
        });
        listTransitionsView.add(deleteButtonTransition, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 413, -1, -1));

        listTransitionsView.add(searchTransitionsBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, -1));
        listTransitionsView.add(searchTransitionsInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 220, -1));

        searchButtonTransitions.setText("Cerca");
        searchButtonTransitions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonTransitionsActionPerformed(evt);
            }
        });
        listTransitionsView.add(searchButtonTransitions, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        mainPanel.add(listTransitionsView, "listTransitionsView");

        addTransitionView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idTransition.setText("ID:");
        addTransitionView.add(idTransition, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        nameTransition.setText("Nome:");
        addTransitionView.add(nameTransition, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        visibilityTransition.setText("Visibility:");
        addTransitionView.add(visibilityTransition, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        smTransition.setText("State Machine:");
        addTransitionView.add(smTransition, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        sourceTransition.setText("Source:");
        addTransitionView.add(sourceTransition, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        targetTransition.setText("Target:");
        addTransitionView.add(targetTransition, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        sourceTransitionInput.setEditable(false);
        addTransitionView.add(sourceTransitionInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 170, -1));
        addTransitionView.add(nameTransitionInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 170, -1));

        visibilityTransitionInput.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Public", "Protected", "Private", "Package" }));
        addTransitionView.add(visibilityTransitionInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 170, -1));

        SMTransitionInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SMTransitionInputActionPerformed(evt);
            }
        });
        addTransitionView.add(SMTransitionInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 170, -1));

        addTransitionButton.setText("Aggiungi");
        addTransitionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTransitionButtonActionPerformed(evt);
            }
        });
        addTransitionView.add(addTransitionButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

        statusTransition.setText("jLabel7");
        addTransitionView.add(statusTransition, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, -1, -1));

        chooseSourceTransition.setText("Scegli");
        chooseSourceTransition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseSourceTransitionActionPerformed(evt);
            }
        });
        addTransitionView.add(chooseSourceTransition, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, -1, -1));

        idTransitionInput.setEditable(false);
        idTransitionInput.setBackground(new java.awt.Color(204, 204, 204));
        addTransitionView.add(idTransitionInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 170, -1));

        targetTransitionInput.setEditable(false);
        addTransitionView.add(targetTransitionInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 170, -1));

        chooseTargetTransition.setText("Scegli");
        chooseTargetTransition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseTargetTransitionActionPerformed(evt);
            }
        });
        addTransitionView.add(chooseTargetTransition, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, -1, -1));

        mainPanel.add(addTransitionView, "addTransitionView");

        modTransitionView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idTransition1.setText("ID:");
        modTransitionView.add(idTransition1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        nameTransition1.setText("Nome:");
        modTransitionView.add(nameTransition1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        visibilityTransition1.setText("Visibility:");
        modTransitionView.add(visibilityTransition1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        smTransition1.setText("State Machine:");
        modTransitionView.add(smTransition1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, -1));

        sourceTransition1.setText("Source:");
        modTransitionView.add(sourceTransition1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        targetTransition1.setText("Target:");
        modTransitionView.add(targetTransition1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, -1));

        sourceTransitionInput1.setEditable(false);
        modTransitionView.add(sourceTransitionInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 170, -1));
        modTransitionView.add(nameTransitionInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 170, -1));

        visibilityTransitionInput1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Public", "Protected", "Private", "Package" }));
        modTransitionView.add(visibilityTransitionInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 170, -1));

        SMTransitionInput1.setEnabled(false);
        SMTransitionInput1.setFocusable(false);
        SMTransitionInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SMTransitionInput1ActionPerformed(evt);
            }
        });
        modTransitionView.add(SMTransitionInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 170, -1));

        addTransitionButton1.setText("Modifica");
        addTransitionButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTransitionButton1ActionPerformed(evt);
            }
        });
        modTransitionView.add(addTransitionButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

        statusTransition1.setText("jLabel7");
        modTransitionView.add(statusTransition1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, -1, -1));

        chooseSourceTransition1.setText("Scegli");
        chooseSourceTransition1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseSourceTransition1ActionPerformed(evt);
            }
        });
        modTransitionView.add(chooseSourceTransition1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, -1, -1));

        idTransitionInput1.setEditable(false);
        idTransitionInput1.setBackground(new java.awt.Color(204, 204, 204));
        modTransitionView.add(idTransitionInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 170, -1));

        targetTransitionInput1.setEditable(false);
        modTransitionView.add(targetTransitionInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 170, -1));

        chooseTargetTransition1.setText("Scegli");
        chooseTargetTransition1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseTargetTransition1ActionPerformed(evt);
            }
        });
        modTransitionView.add(chooseTargetTransition1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, -1, -1));

        chooseTransitionMod.setText("Scegli Transizione");
        chooseTransitionMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseTransitionModActionPerformed(evt);
            }
        });
        modTransitionView.add(chooseTransitionMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        mainPanel.add(modTransitionView, "modTransitionView");

        listActionsView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listActionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        listActionsTable.setShowVerticalLines(false);
        listActionsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(listActionsTable);

        listActionsView.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 340));

        deleteButtonActions.setText("Cancella");
        deleteButtonActions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionsActionPerformed(evt);
            }
        });
        listActionsView.add(deleteButtonActions, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, -1, -1));

        refreshListActions.setText("Aggiorna");
        refreshListActions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshListActionsActionPerformed(evt);
            }
        });
        listActionsView.add(refreshListActions, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        searchButtonActions.setText("Cerca");
        searchButtonActions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionsActionPerformed(evt);
            }
        });
        listActionsView.add(searchButtonActions, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));
        listActionsView.add(searchActionsInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 220, -1));

        listActionsView.add(searchActionsBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, -1));

        mainPanel.add(listActionsView, "listActionsView");

        addActionView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idActionInput.setEditable(false);
        idActionInput.setBackground(new java.awt.Color(204, 204, 204));
        addActionView.add(idActionInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 170, -1));

        stateActionInput.setEditable(false);
        addActionView.add(stateActionInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 170, -1));

        stateAction.setText("Stato:");
        addActionView.add(stateAction, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        idAction.setText("ID:");
        addActionView.add(idAction, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        chooseStateAction.setText("Scegli");
        chooseStateAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseStateActionActionPerformed(evt);
            }
        });
        addActionView.add(chooseStateAction, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, -1, -1));

        transitionActionInput.setEditable(false);
        addActionView.add(transitionActionInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 170, -1));

        chooseTransitionAction.setText("Scegli");
        chooseTransitionAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseTransitionActionActionPerformed(evt);
            }
        });
        addActionView.add(chooseTransitionAction, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, -1, -1));

        statusAction.setText("jLabel7");
        addActionView.add(statusAction, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, -1, -1));

        addActionButton.setText("Aggiungi");
        addActionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionButtonActionPerformed(evt);
            }
        });
        addActionView.add(addActionButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

        visibilityActionInput.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Public", "Protected", "Private", "Package" }));
        addActionView.add(visibilityActionInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 170, -1));
        addActionView.add(nameActionInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 170, -1));

        transitionAction.setText("Transizione:");
        addActionView.add(transitionAction, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        visibilityAction.setText("Visibility:");
        addActionView.add(visibilityAction, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        nameAction.setText("Nome:");
        addActionView.add(nameAction, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        smAction.setText("State Machine:");
        addActionView.add(smAction, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        SMActionInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SMActionInputActionPerformed(evt);
            }
        });
        addActionView.add(SMActionInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 170, -1));

        mainPanel.add(addActionView, "addActionView");

        modActionView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idActionInput1.setEditable(false);
        idActionInput1.setBackground(new java.awt.Color(204, 204, 204));
        modActionView.add(idActionInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 170, -1));

        stateActionInput1.setEditable(false);
        modActionView.add(stateActionInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 170, -1));

        stateAction1.setText("Stato:");
        modActionView.add(stateAction1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        idAction1.setText("ID:");
        modActionView.add(idAction1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        chooseStateAction1.setText("Scegli");
        chooseStateAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseStateAction1ActionPerformed(evt);
            }
        });
        modActionView.add(chooseStateAction1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, -1, -1));

        transitionActionInput1.setEditable(false);
        modActionView.add(transitionActionInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 170, -1));

        chooseTransitionAction1.setText("Scegli");
        chooseTransitionAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseTransitionAction1ActionPerformed(evt);
            }
        });
        modActionView.add(chooseTransitionAction1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, -1, -1));

        statusAction1.setText("jLabel7");
        modActionView.add(statusAction1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, -1, -1));

        addActionButton1.setText("Modifica");
        addActionButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionButton1ActionPerformed(evt);
            }
        });
        modActionView.add(addActionButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

        visibilityActionInput1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Public", "Protected", "Private", "Package" }));
        modActionView.add(visibilityActionInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 170, -1));
        modActionView.add(nameActionInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 170, -1));

        transitionAction1.setText("Transizione:");
        modActionView.add(transitionAction1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        visibilityAction1.setText("Visibility:");
        modActionView.add(visibilityAction1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        nameAction1.setText("Nome:");
        modActionView.add(nameAction1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        smAction1.setText("State Machine:");
        modActionView.add(smAction1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        SMActionInput1.setEnabled(false);
        SMActionInput1.setFocusable(false);
        SMActionInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SMActionInput1ActionPerformed(evt);
            }
        });
        modActionView.add(SMActionInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 170, -1));

        chooseModAction.setText("Scegli Azione");
        chooseModAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseModActionActionPerformed(evt);
            }
        });
        modActionView.add(chooseModAction, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        mainPanel.add(modActionView, "modActionView");

        listEventsView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listEventsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        listEventsTable.setShowVerticalLines(false);
        listEventsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(listEventsTable);

        listEventsView.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 330));

        deleteButtonEvents.setText("Cancella");
        deleteButtonEvents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonEventsActionPerformed(evt);
            }
        });
        listEventsView.add(deleteButtonEvents, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, -1, -1));

        refreshListEvents.setText("Aggiorna");
        refreshListEvents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshListEventsActionPerformed(evt);
            }
        });
        listEventsView.add(refreshListEvents, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));
        listEventsView.add(searchEventsInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 220, -1));

        listEventsView.add(searchEventsBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, -1));

        searchButtonEvents.setText("Cerca");
        searchButtonEvents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonEventsActionPerformed(evt);
            }
        });
        listEventsView.add(searchButtonEvents, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        mainPanel.add(listEventsView, "listEventsView");

        addEventView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idEventInput.setEditable(false);
        idEventInput.setBackground(new java.awt.Color(204, 204, 204));
        addEventView.add(idEventInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 170, -1));

        stateEventInput.setEditable(false);
        addEventView.add(stateEventInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 170, -1));

        stateEvent.setText("Stato:");
        addEventView.add(stateEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        idEvent.setText("ID:");
        addEventView.add(idEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        chooseStateEvent.setText("Scegli");
        chooseStateEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseStateEventActionPerformed(evt);
            }
        });
        addEventView.add(chooseStateEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, -1, -1));

        transitionEventInput.setEditable(false);
        addEventView.add(transitionEventInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 170, -1));

        chooseTransitionEvent.setText("Scegli");
        chooseTransitionEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseTransitionEventActionPerformed(evt);
            }
        });
        addEventView.add(chooseTransitionEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, -1, -1));

        statusEvent.setText("jLabel7");
        addEventView.add(statusEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, -1, -1));

        addEventButton.setText("Aggiungi");
        addEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventButtonActionPerformed(evt);
            }
        });
        addEventView.add(addEventButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, -1, -1));

        visibilityEventInput.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Public", "Protected", "Private", "Package" }));
        addEventView.add(visibilityEventInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 170, -1));
        addEventView.add(nameEventInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 170, -1));

        transitionEvent.setText("Transizione:");
        addEventView.add(transitionEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        visibilityEvent.setText("Visibility:");
        addEventView.add(visibilityEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        nameEvent.setText("Nome:");
        addEventView.add(nameEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        smEvent.setText("State Machine:");
        addEventView.add(smEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));

        SMEventInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SMEventInputActionPerformed(evt);
            }
        });
        addEventView.add(SMEventInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 170, -1));

        jLabel1.setText("Tipo:");
        addEventView.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, -1, -1));

        typeEventInput.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Call", "Time", "Signal", "Change" }));
        typeEventInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeEventInputActionPerformed(evt);
            }
        });
        addEventView.add(typeEventInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 170, -1));

        exprEv.setText("Return: ");
        addEventView.add(exprEv, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, -1, -1));
        addEventView.add(exprEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 170, -1));

        mainPanel.add(addEventView, "addEventView");

        modEventView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idEventInput1.setEditable(false);
        idEventInput1.setBackground(new java.awt.Color(204, 204, 204));
        modEventView.add(idEventInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 170, -1));

        stateEventInput1.setEditable(false);
        modEventView.add(stateEventInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 170, -1));

        stateEvent1.setText("Stato:");
        modEventView.add(stateEvent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        idEvent1.setText("ID:");
        modEventView.add(idEvent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        chooseStateEvent1.setText("Scegli");
        chooseStateEvent1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseStateEvent1ActionPerformed(evt);
            }
        });
        modEventView.add(chooseStateEvent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, -1, -1));

        transitionEventInput1.setEditable(false);
        modEventView.add(transitionEventInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 170, -1));

        chooseTransitionEvent1.setText("Scegli");
        chooseTransitionEvent1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseTransitionEvent1ActionPerformed(evt);
            }
        });
        modEventView.add(chooseTransitionEvent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, -1, -1));

        statusEvent1.setText("jLabel7");
        modEventView.add(statusEvent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, -1, -1));

        addEventButton1.setText("Modifica");
        addEventButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventButton1ActionPerformed(evt);
            }
        });
        modEventView.add(addEventButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, -1, -1));

        visibilityEventInput1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Public", "Protected", "Private", "Package" }));
        modEventView.add(visibilityEventInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 170, -1));
        modEventView.add(nameEventInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 170, -1));

        transitionEvent1.setText("Transizione:");
        modEventView.add(transitionEvent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        visibilityEvent1.setText("Visibility:");
        modEventView.add(visibilityEvent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        nameEvent1.setText("Nome:");
        modEventView.add(nameEvent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        smEvent1.setText("State Machine:");
        modEventView.add(smEvent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        SMEventInput1.setEnabled(false);
        SMEventInput1.setFocusable(false);
        SMEventInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SMEventInput1ActionPerformed(evt);
            }
        });
        modEventView.add(SMEventInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 170, -1));

        chooseModEvent.setText("Scegli Evento");
        chooseModEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseModEventActionPerformed(evt);
            }
        });
        modEventView.add(chooseModEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        mainPanel.add(modEventView, "modEventView");

        defaultView1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Password:");
        defaultView1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, 20));

        jLabel4.setText("Host:");
        defaultView1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jLabel5.setText("Username:");
        defaultView1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        usernameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameInputActionPerformed(evt);
            }
        });
        defaultView1.add(usernameInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 130, -1));
        defaultView1.add(hostInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 130, -1));

        passwordInput.setText("jPasswordField1");
        defaultView1.add(passwordInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, -1, -1));

        jButton1.setText("Connect");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        defaultView1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, -1, -1));

        jLabel6.setText("Port:");
        defaultView1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));
        defaultView1.add(portInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 130, -1));

        statusEvent2.setText("jLabel7");
        defaultView1.add(statusEvent2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, -1, -1));

        mainPanel.add(defaultView1, "server");

        mainMenu.setText("File");

        changeServer.setText("Server");
        changeServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeServerActionPerformed(evt);
            }
        });
        mainMenu.add(changeServer);

        MenuItemExit.setText("Exit");
        MenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemExitActionPerformed(evt);
            }
        });
        mainMenu.add(MenuItemExit);

        jMenuBar1.add(mainMenu);

        stateMachinesMenu.setText("State Machines");

        listSM.setText("Lista SM");
        listSM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listSMActionPerformed(evt);
            }
        });
        stateMachinesMenu.add(listSM);

        addSM.setText("Aggiungi SM");
        addSM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSMActionPerformed(evt);
            }
        });
        stateMachinesMenu.add(addSM);

        modSM.setText("Modifica SM");
        modSM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modSMActionPerformed(evt);
            }
        });
        stateMachinesMenu.add(modSM);

        jMenuBar1.add(stateMachinesMenu);

        statesMenu.setText("States");

        listStates.setText("Lista Stati");
        listStates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listStatesActionPerformed(evt);
            }
        });
        statesMenu.add(listStates);

        addState.setText("Aggiungi Stato");
        addState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStateActionPerformed(evt);
            }
        });
        statesMenu.add(addState);

        modState.setText("Modifica Stato");
        modState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modStateActionPerformed(evt);
            }
        });
        statesMenu.add(modState);

        jMenuBar1.add(statesMenu);

        transitionsMenu.setText("Transitions");

        listTransitions.setText("Lista Transizioni");
        listTransitions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listTransitionsActionPerformed(evt);
            }
        });
        transitionsMenu.add(listTransitions);

        addTransition.setText("Aggiungi Transizione");
        addTransition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTransitionActionPerformed(evt);
            }
        });
        transitionsMenu.add(addTransition);

        modTransition.setText("Modifica Transizione");
        modTransition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modTransitionActionPerformed(evt);
            }
        });
        transitionsMenu.add(modTransition);

        jMenuBar1.add(transitionsMenu);

        modMenu.setText("Actions");

        listActions.setText("Lista Azioni");
        listActions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listActionsActionPerformed(evt);
            }
        });
        modMenu.add(listActions);

        addAction.setText("Aggiungi Azione");
        addAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionActionPerformed(evt);
            }
        });
        modMenu.add(addAction);

        modAction.setText("Modifica Azione");
        modAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modActionActionPerformed(evt);
            }
        });
        modMenu.add(modAction);

        jMenuBar1.add(modMenu);

        eventsMenu.setText("Events");

        listEvents.setText("Lista Eventi");
        listEvents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listEventsActionPerformed(evt);
            }
        });
        eventsMenu.add(listEvents);

        addEvent.setText("Aggiungi Evento");
        addEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventActionPerformed(evt);
            }
        });
        eventsMenu.add(addEvent);

        modEvent.setText("Modifica Evento");
        modEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modEventActionPerformed(evt);
            }
        });
        eventsMenu.add(modEvent);

        jMenuBar1.add(eventsMenu);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_MenuItemExitActionPerformed

    private void listSMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listSMActionPerformed
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "listSMView");
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM StateMachine");
            System.out.println(resultSet.getFetchSize());
            listSMTable.setModel(mapResultSet(resultSet));
            searchSMBox.setModel(mapBox(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_listSMActionPerformed

    private void addSMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSMActionPerformed
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "addSMView");
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM StateMachine");
            resultSet.next();
            Integer count = resultSet.getInt(1) + 1;
            idSMInput.setText(count.toString());
            pathSMInput.setText("/Users/grp2/" + count + "/");
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addSMActionPerformed

    private void addSMButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSMButtonActionPerformed
        try {
            String addSMQuery = "INSERT INTO StateMachine VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(addSMQuery);
            preparedStatement.setInt(1, Integer.parseInt(idSMInput.getText()));
            preparedStatement.setString(2, nameSMInput.getText());
            preparedStatement.setString(3, descSMInput.getText());
            preparedStatement.setString(4, pathSMInput.getText());
            preparedStatement.setString(5, authorSMInput.getText());
            preparedStatement.executeUpdate();
            statusSM.setForeground(Color.green);
            statusSM.setText("AGGIUNTO");
            statusSM.setVisible(true);
        } catch (SQLException ex) {
            statusSM.setForeground(Color.red);
            statusSM.setText("ERRORE");
            statusSM.setVisible(true);
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addSMButtonActionPerformed

    private void listStatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listStatesActionPerformed
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "listStatesView");
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM StateVertex NATURAL JOIN State");
            ResultSet resultSet = stmt.executeQuery();
            searchStatesBox.setModel(mapBox(resultSet));
            listStatesTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_listStatesActionPerformed

    private void listTransitionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listTransitionsActionPerformed
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "listTransitionsView");
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Transition");
            searchTransitionsBox.setModel(mapBox(resultSet));
            listTransitionsTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_listTransitionsActionPerformed

    private void refreshListStatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshListStatesActionPerformed
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM StateVertex NATURAL JOIN State");
            listStatesTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_refreshListStatesActionPerformed

    private void addStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStateActionPerformed
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "addStateView");
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT Count(*) FROM State");
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            Integer count = resultSet.getInt(1) + 1;
            PreparedStatement stmt2 = connection.prepareStatement("SELECT SID FROM StateMachine");
            ResultSet resultSet2 = stmt2.executeQuery();
            idStateInput.setText(count.toString());
            SMStateInput.removeAllItems();
            while (resultSet2.next()) {
                SMStateInput.addItem(resultSet2.getString(1));
            }
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_addStateActionPerformed

    private void addStateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStateButtonActionPerformed
        try {
            String addVertexQuery = "INSERT INTO StateVertex VALUES (?, ?, ?)";
            String addStateQuery = "INSERT INTO State (STATEID, VID, VISIBILITY, DESCRIPTION, ISFINAL, ISTOP) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement vertexID = connection.prepareStatement("SELECT VID FROM StateVertex ORDER BY VID DESC");
            ResultSet vertexIDResult = vertexID.executeQuery();
            vertexIDResult.next();
            
            PreparedStatement preparedStatementVertex = connection.prepareStatement(addVertexQuery);
            preparedStatementVertex.setInt(1, vertexIDResult.getInt(1) + 1);
            preparedStatementVertex.setInt(2, Integer.parseInt(SMStateInput.getSelectedItem().toString()));
            preparedStatementVertex.setString(3, descStateInput.getText());
            preparedStatementVertex.executeUpdate();
            
            PreparedStatement preparedStatement = connection.prepareStatement(addStateQuery);
            preparedStatement.setInt(1, Integer.parseInt(idStateInput.getText()));
            preparedStatement.setInt(2, vertexIDResult.getInt(1) + 1);
            preparedStatement.setString(3, visibilityStateInput.getSelectedItem().toString());
            preparedStatement.setString(4, descStateInput.getText());
            if (isTopInput.isSelected()) {
                preparedStatement.setInt(5, 1);
            } else {
                preparedStatement.setInt(5, 0);
            }
            
            if (isFinalInput.isSelected()) {
                preparedStatement.setInt(6, 1);
            } else {
                preparedStatement.setInt(6, 0);
            }
            preparedStatement.executeUpdate();
            statusState.setForeground(Color.green);
            statusState.setText("AGGIUNTO");
            statusState.setVisible(true);
        } catch (SQLException ex) {
            statusState.setForeground(Color.red);
            statusState.setText("ERRORE");
            statusState.setVisible(true);
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addStateButtonActionPerformed

    private void deleteButtonListSMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonListSMActionPerformed
        TableModel tableModel = listSMTable.getModel();
        BigDecimal selected = (BigDecimal) tableModel.getValueAt(listSMTable.getSelectedRow(), 0);
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM StateMachine WHERE SID = (?)");
            stmt.setBigDecimal(1, selected);
            stmt.executeUpdate();
        } catch (SQLException ex) {
//            statusState.setForeground(Color.red);
//            statusState.setText("ERRORE");
//            statusState.setVisible(true);
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
//        tableModel.getV
    }//GEN-LAST:event_deleteButtonListSMActionPerformed

    private void refreshListSMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshListSMActionPerformed
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM StateMachine");
            ResultSet resultSet = stmt.executeQuery();
            listSMTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_refreshListSMActionPerformed

    private void refreshListTransitionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshListTransitionsActionPerformed
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Transition");
            ResultSet resultSet = stmt.executeQuery();
            listTransitionsTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_refreshListTransitionsActionPerformed

    private void deleteButtonTransitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonTransitionActionPerformed
        TableModel tableModel = listTransitionsTable.getModel();
        BigDecimal selected = (BigDecimal) tableModel.getValueAt(listTransitionsTable.getSelectedRow(), 0);
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Transition WHERE TransitionID = (?)");
            stmt.setBigDecimal(1, selected);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteButtonTransitionActionPerformed

    private void deleteButtonStatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonStatesActionPerformed
        TableModel tableModel = listStatesTable.getModel();
        BigDecimal selected = (BigDecimal) tableModel.getValueAt(listStatesTable.getSelectedRow(), 3);
        BigDecimal selectedVID = (BigDecimal) tableModel.getValueAt(listStatesTable.getSelectedRow(), 0);
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM State WHERE StateID = (?)");
            stmt.setBigDecimal(1, selected);
            stmt.executeUpdate();
            
            PreparedStatement stmt2 = connection.prepareStatement("DELETE FROM StateVertex WHERE VID = (?)");
            stmt2.setBigDecimal(1, selectedVID);
            stmt2.executeUpdate();
        } catch (SQLException ex) {
//            statusState.setForeground(Color.red);
//            statusState.setText("ERRORE");
//            statusState.setVisible(true);
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteButtonStatesActionPerformed

    private void addTransitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTransitionActionPerformed
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "addTransitionView");
        //SMTransitionInput.removeAllItems();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM Transition");
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            Integer count = resultSet.getInt(1) + 1;
            idTransitionInput.setText(count.toString());
            
            PreparedStatement stmt2 = connection.prepareStatement("SELECT SID FROM StateMachine");
            ResultSet resultSet2 = stmt2.executeQuery();
            while (resultSet2.next()) {
                SMTransitionInput.addItem(resultSet2.getString(1));
            }
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addTransitionActionPerformed

    private void SMTransitionInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SMTransitionInputActionPerformed
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT StateID FROM State NATURAL JOIN StateVertex"
                    + " WHERE SID = (?)");
            stmt.setInt(1, Integer.parseInt(SMTransitionInput.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SMTransitionInputActionPerformed

    private void chooseSourceTransitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseSourceTransitionActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM State NATURAL JOIN StateVertex"
                                                + " WHERE SID = (?)");
            stmt.setInt(1, Integer.parseInt(SMTransitionInput.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(1);
    }//GEN-LAST:event_chooseSourceTransitionActionPerformed

    private void chooseDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseDialogActionPerformed
        TableModel tableModel = availableTable.getModel();
        BigDecimal res = (BigDecimal) tableModel.getValueAt(availableTable.getSelectedRow(), 0);
        if (dialogSet == 1) {
            sourceTransitionInput.setText(res.toString());
        } else if (dialogSet == 2) {
            targetTransitionInput.setText(res.toString());
        } else if (dialogSet == 3) {
            res = (BigDecimal) tableModel.getValueAt(availableTable.getSelectedRow(), 1);
            stateActionInput.setText(res.toString());
        } else if (dialogSet == 4) {
            transitionActionInput.setText(res.toString());
        } else if (dialogSet == 5) {
            res = (BigDecimal) tableModel.getValueAt(availableTable.getSelectedRow(), 1);
            stateEventInput.setText(res.toString());
        } else if (dialogSet == 6) {
            transitionEventInput.setText(res.toString());
        } else if (dialogSet == 7) {
            idSMInput1.setText(res.toString());
            nameSMInput1.setText((String) tableModel.getValueAt(availableTable.getSelectedRow(), 1));
            descSMInput1.setText((String) tableModel.getValueAt(availableTable.getSelectedRow(), 2));
            pathSMInput1.setText((String) tableModel.getValueAt(availableTable.getSelectedRow(), 3));
            authorSMInput1.setText((String) tableModel.getValueAt(availableTable.getSelectedRow(), 4));
        } else if (dialogSet == 8) {
            idStateInput1.setText((String) tableModel.getValueAt(availableTable.getSelectedRow(), 3).toString());
            nameStateInput2.setText((String) tableModel.getValueAt(availableTable.getSelectedRow(), 2));
            descStateInput1.setText((String) tableModel.getValueAt(availableTable.getSelectedRow(), 5));
            for (int i = 0; i < visibilityStateInput1.getItemCount(); i++){
                if (visibilityStateInput1.getItemAt(i).toString().equals((String) tableModel.getValueAt(availableTable.getSelectedRow(), 4))) {
                    visibilityStateInput1.setSelectedIndex(i);
                }
            }
            if (((String) tableModel.getValueAt(availableTable.getSelectedRow(), 6).toString()).equals("1")) {
                isFinalInput1.setSelected(true);
            } else {
                isFinalInput1.setSelected(false);
            }
            if (((String) tableModel.getValueAt(availableTable.getSelectedRow(), 7).toString()).equals("1")) {
                isTopInput1.setSelected(true);
            } else {
                isTopInput1.setSelected(false);
            }
        } else if (dialogSet == 9) {
            try {
                idTransitionInput1.setText(res.toString());
                nameTransitionInput1.setText((String) tableModel.getValueAt(availableTable.getSelectedRow(), 1));
                for (int i = 0; i < visibilityTransitionInput1.getItemCount(); i++){
                    if (visibilityTransitionInput1.getItemAt(i).toString().equals((String) tableModel.getValueAt(availableTable.getSelectedRow(), 2))) {
                        visibilityTransitionInput1.setSelectedIndex(i);
                    }
                }
                sourceTransitionInput1.setText((String) tableModel.getValueAt(availableTable.getSelectedRow(), 3).toString());
                targetTransitionInput1.setText((String) tableModel.getValueAt(availableTable.getSelectedRow(), 4).toString());
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT SID FROM StateVertex WHERE VID = " + sourceTransitionInput1.getText());
                rs.next();
                Integer x = rs.getInt(1);
                for (int i = 0; i < SMTransitionInput1.getItemCount(); i++){
                    if (SMTransitionInput1.getItemAt(i).toString().equals(x.toString())) {
                        SMTransitionInput1.setSelectedIndex(i);
                    }
                }
            } catch (SQLException ex) {
                showErr(ex.toString());
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (dialogSet == 10) {
            sourceTransitionInput1.setText(res.toString());
        } else if (dialogSet == 11) {
            targetTransitionInput1.setText(res.toString());
        } else if (dialogSet == 12) {
            try {
                idActionInput1.setText(res.toString());
                nameActionInput1.setText((String) tableModel.getValueAt(availableTable.getSelectedRow(), 3));
                for (int i = 0; i < visibilityActionInput1.getItemCount(); i++){
                    if (visibilityActionInput1.getItemAt(i).toString().equals((String) tableModel.getValueAt(availableTable.getSelectedRow(), 4))) {
                        visibilityActionInput1.setSelectedIndex(i);
                    }
                }
                if (tableModel.getValueAt(availableTable.getSelectedRow(), 1) != null) {
                    stateActionInput1.setText((String) tableModel.getValueAt(availableTable.getSelectedRow(), 1).toString());
                } else {
                    stateActionInput1.setText("");
                }
                if (tableModel.getValueAt(availableTable.getSelectedRow(), 2) != null) {
                    transitionActionInput1.setText((String) tableModel.getValueAt(availableTable.getSelectedRow(), 2).toString());
                } else {
                    transitionActionInput1.setText("");
                }
                Statement stmt = connection.createStatement();
                if (!stateActionInput1.getText().equals("")) {
                    ResultSet rs = stmt.executeQuery("SELECT SID FROM StateVertex WHERE VID = " + stateActionInput1.getText());
                    rs.next();
                    Integer x = rs.getInt(1);
                    for (int i = 0; i < SMTransitionInput1.getItemCount(); i++){
                        if (SMActionInput1.getItemAt(i).toString().equals(x.toString())) {
                            SMActionInput1.setSelectedIndex(i);
                        }
                    }
                } else {
                    ResultSet rs = stmt.executeQuery("SELECT SID FROM StateVertex WHERE VID = " + transitionActionInput1.getText());
                    rs.next();
                    Integer x = rs.getInt(1);
                    for (int i = 0; i < SMTransitionInput1.getItemCount(); i++){
                        if (SMActionInput1.getItemAt(i).toString().equals(x.toString())) {
                            SMActionInput1.setSelectedIndex(i);
                        }
                    }
                }
                
                
            } catch (SQLException ex) {
                showErr(ex.toString());
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (dialogSet == 13) {
            res = (BigDecimal) tableModel.getValueAt(availableTable.getSelectedRow(), 1);
            stateActionInput1.setText(res.toString());
        } else if (dialogSet == 14) {
            transitionActionInput1.setText(res.toString());
        } else if (dialogSet == 15) {
            try {
                idEventInput1.setText(res.toString());
                nameEventInput1.setText((String) tableModel.getValueAt(availableTable.getSelectedRow(), 3));
                for (int i = 0; i < visibilityEventInput1.getItemCount(); i++){
                    if (visibilityEventInput1.getItemAt(i).toString().equals((String) tableModel.getValueAt(availableTable.getSelectedRow(), 4))) {
                        visibilityEventInput1.setSelectedIndex(i);
                    }
                }
                if (tableModel.getValueAt(availableTable.getSelectedRow(), 1) != null) {
                    stateEventInput1.setText((String) tableModel.getValueAt(availableTable.getSelectedRow(), 1).toString());
                } else {
                    stateEventInput1.setText("");
                }
                if (tableModel.getValueAt(availableTable.getSelectedRow(), 2) != null) {
                    transitionEventInput1.setText((String) tableModel.getValueAt(availableTable.getSelectedRow(), 2).toString());
                } else {
                    transitionEventInput1.setText("");
                }
                Statement stmt = connection.createStatement();
                if (!stateEventInput1.getText().equals("")) {
                    ResultSet rs = stmt.executeQuery("SELECT SID FROM StateVertex WHERE VID = " + stateEventInput1.getText());
                    rs.next();
                    Integer x = rs.getInt(1);
                    for (int i = 0; i < SMEventInput1.getItemCount(); i++){
                        if (SMEventInput1.getItemAt(i).toString().equals(x.toString())) {
                            SMEventInput1.setSelectedIndex(i);
                        }
                    }
                } else {
                    ResultSet rs = stmt.executeQuery("SELECT SID FROM StateVertex WHERE VID = " + transitionEventInput1.getText());
                    rs.next();
                    Integer x = rs.getInt(1);
                    for (int i = 0; i < SMEventInput1.getItemCount(); i++){
                        if (SMEventInput1.getItemAt(i).toString().equals(x.toString())) {
                            SMEventInput1.setSelectedIndex(i);
                        }
                    }
                }
                
                
            } catch (SQLException ex) {
                showErr(ex.toString());
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (dialogSet == 16) {
            transitionEventInput1.setText(res.toString());
        } else if (dialogSet == 18) {
            res = (BigDecimal) tableModel.getValueAt(availableTable.getSelectedRow(), 1);
            stateEventInput1.setText(res.toString());
        }
        availDialog.dispose();
    }//GEN-LAST:event_chooseDialogActionPerformed

    private void chooseTargetTransitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseTargetTransitionActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM State NATURAL JOIN StateVertex"
                                                + " WHERE SID = (?)");
            stmt.setInt(1, Integer.parseInt(SMTransitionInput.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(2);
    }//GEN-LAST:event_chooseTargetTransitionActionPerformed

    private void listActionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listActionsActionPerformed
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "listActionsView");
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Action");
            ResultSet resultSet = stmt.executeQuery();
            System.out.println(resultSet.getFetchSize());
            listActionsTable.setModel(mapResultSet(resultSet));
            searchActionsBox.setModel(mapBox(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_listActionsActionPerformed

    private void addEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventActionPerformed
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "addEventView");
        SMEventInput.removeAllItems();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM Event");
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            Integer count = resultSet.getInt(1) + 1;
             idEventInput.setText(count.toString());
            
            PreparedStatement stmt2 = connection.prepareStatement("SELECT SID FROM StateMachine");
            ResultSet resultSet2 = stmt2.executeQuery();
            while (resultSet2.next()) {
                SMEventInput.addItem(resultSet2.getString(1));
            }
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addEventActionPerformed

    private void deleteButtonActionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionsActionPerformed
        TableModel tableModel = listActionsTable.getModel();
        BigDecimal selected = (BigDecimal) tableModel.getValueAt(listActionsTable.getSelectedRow(), 0);
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Action WHERE ActionID = (?)");
            stmt.setBigDecimal(1, selected);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteButtonActionsActionPerformed

    private void refreshListActionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshListActionsActionPerformed
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Action");
            ResultSet resultSet = stmt.executeQuery();
            listActionsTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_refreshListActionsActionPerformed

    private void deleteButtonEventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonEventsActionPerformed
        TableModel tableModel = listEventsTable.getModel();
        BigDecimal selected = (BigDecimal) tableModel.getValueAt(listEventsTable.getSelectedRow(), 0);
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Event WHERE EventID = (?)");
            stmt.setBigDecimal(1, selected);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteButtonEventsActionPerformed

    private void refreshListEventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshListEventsActionPerformed
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Event");
            ResultSet resultSet = stmt.executeQuery();
            listEventsTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_refreshListEventsActionPerformed

    private void listEventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listEventsActionPerformed
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "listEventsView");
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Event");
            ResultSet resultSet = stmt.executeQuery();
            System.out.println(resultSet.getFetchSize());
            searchEventsBox.setModel(mapBox(resultSet));
            listEventsTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_listEventsActionPerformed

    private void addTransitionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTransitionButtonActionPerformed
        try {
            String addTransitionQuery = "INSERT INTO Transition VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatementTR = connection.prepareStatement(addTransitionQuery);
            preparedStatementTR.setInt(1, Integer.parseInt(idTransitionInput.getText()));
            preparedStatementTR.setString(2, nameTransitionInput.getText());
            preparedStatementTR.setString(3, visibilityTransitionInput.getSelectedItem().toString());
            preparedStatementTR.setInt(4, Integer.parseInt(sourceTransitionInput.getText()));
            preparedStatementTR.setInt(5, Integer.parseInt(targetTransitionInput.getText()));
            preparedStatementTR.executeUpdate();
            statusTransition.setForeground(Color.green);
            statusTransition.setText("AGGIUNTO");
            statusTransition.setVisible(true);
        } catch (SQLException ex) {
            statusTransition.setForeground(Color.red);
            statusTransition.setText("ERRORE");
            statusTransition.setVisible(true);
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addTransitionButtonActionPerformed

    private void chooseStateActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseStateActionActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM State NATURAL JOIN StateVertex"
                                                + " WHERE SID = (?)");
            stmt.setInt(1, Integer.parseInt(SMActionInput.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(3);
    }//GEN-LAST:event_chooseStateActionActionPerformed

    private void chooseTransitionActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseTransitionActionActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM Transition"
                                                + " WHERE Source IN (SELECT VID FROM StateVertex WHERE SID = (?))");
            stmt.setInt(1, Integer.parseInt(SMActionInput.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(4);
    }//GEN-LAST:event_chooseTransitionActionActionPerformed

    private void addActionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionButtonActionPerformed
        try {
            String addSMQuery = "INSERT INTO Action VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(addSMQuery);
            preparedStatement.setString(4, nameActionInput.getText());
            preparedStatement.setString(5, visibilityActionInput.getSelectedItem().toString());
            if (stateActionInput.getText().equals("")) {
                preparedStatement.setNull(2, java.sql.Types.INTEGER);
                preparedStatement.setInt(3, Integer.parseInt(transitionActionInput.getText()));
            } else if (transitionActionInput.getText().equals("")) {
                preparedStatement.setInt(2, Integer.parseInt(stateActionInput.getText()));
                preparedStatement.setNull(3, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setInt(2, Integer.parseInt(stateActionInput.getText()));
                preparedStatement.setInt(3, Integer.parseInt(transitionActionInput.getText()));
            }
            preparedStatement.setInt(1, Integer.parseInt(idActionInput.getText()));
            preparedStatement.executeQuery();
            statusAction.setForeground(Color.green);
            statusAction.setText("Aggiunto");
            statusAction.setVisible(true);
        } catch (SQLException ex) {
            statusAction.setForeground(Color.red);
            statusAction.setText("ERRORE");
            statusAction.setVisible(true);
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addActionButtonActionPerformed

    private void SMActionInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SMActionInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SMActionInputActionPerformed

    private void addActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionActionPerformed
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "addActionView");
        SMActionInput.removeAllItems();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM Action");
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            Integer count = resultSet.getInt(1) + 1;
            idActionInput.setText(count.toString());
            
            PreparedStatement stmt2 = connection.prepareStatement("SELECT SID FROM StateMachine");
            ResultSet resultSet2 = stmt2.executeQuery();
            while (resultSet2.next()) {
                SMActionInput.addItem(resultSet2.getString(1));
            }
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addActionActionPerformed

    private void chooseStateEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseStateEventActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM State NATURAL JOIN StateVertex"
                                                + " WHERE SID = (?)");
            stmt.setInt(1, Integer.parseInt(SMEventInput.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(5);
    }//GEN-LAST:event_chooseStateEventActionPerformed

    private void chooseTransitionEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseTransitionEventActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM Transition"
                                                + " WHERE Source IN (SELECT VID FROM StateVertex WHERE SID = (?))");
            stmt.setInt(1, Integer.parseInt(SMEventInput.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(6);
    }//GEN-LAST:event_chooseTransitionEventActionPerformed

    private void addEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventButtonActionPerformed
        try {
            String addTransitionQuery = "INSERT INTO Event VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatementTR = connection.prepareStatement(addTransitionQuery);
            preparedStatementTR.setInt(1, Integer.parseInt(idEventInput.getText()));
            if (stateEventInput.getText().equals("")) {
                preparedStatementTR.setNull(2, java.sql.Types.INTEGER);
                preparedStatementTR.setInt(3, Integer.parseInt(transitionEventInput.getText()));
            } else if (transitionEventInput.getText().equals("")) {
                preparedStatementTR.setInt(2, Integer.parseInt(stateEventInput.getText()));
                preparedStatementTR.setNull(3, java.sql.Types.INTEGER);
            } else {
                preparedStatementTR.setInt(2, Integer.parseInt(stateEventInput.getText()));
                preparedStatementTR.setInt(3, Integer.parseInt(transitionEventInput.getText()));
            }
            preparedStatementTR.setString(4, nameEventInput.getText());
            preparedStatementTR.setString(5, visibilityEventInput.getSelectedItem().toString());
            preparedStatementTR.executeUpdate();
            
            int x = typeEventInput.getSelectedIndex();
            if (x == 0) {
                String typeQuery = "INSERT INTO CallEvent VALUES (?, NULL, ?)";
                PreparedStatement typeQueryTR = connection.prepareStatement(typeQuery);
                typeQueryTR.setInt(1, Integer.parseInt(idEventInput.getText()));
                typeQueryTR.setString(2, exprEvent.getText());
                typeQueryTR.executeUpdate();
            } else if (x == 1) {
                String typeQuery = "INSERT INTO TimeEvent VALUES (?, ?)";
                PreparedStatement typeQueryTR = connection.prepareStatement(typeQuery);
                typeQueryTR.setInt(1, Integer.parseInt(idEventInput.getText()));
                typeQueryTR.setString(2, exprEvent.getText());
                typeQueryTR.executeUpdate();
            } else if (x == 2) {
                String typeQuery = "INSERT INTO SignalEvent VALUES (?, ?)";
                PreparedStatement typeQueryTR = connection.prepareStatement(typeQuery);
                typeQueryTR.setInt(1, Integer.parseInt(idEventInput.getText()));
                typeQueryTR.setString(2, exprEvent.getText());
                typeQueryTR.executeUpdate();
            } else {
                String typeQuery = "INSERT INTO ChangeEvent VALUES (?, ?)";
                PreparedStatement typeQueryTR = connection.prepareStatement(typeQuery);
                typeQueryTR.setInt(1, Integer.parseInt(idEventInput.getText()));
                typeQueryTR.setString(2, exprEvent.getText());
                typeQueryTR.executeUpdate();
            }
            
            statusEvent.setForeground(Color.green);
            statusEvent.setText("AGGIUNTO");
            statusEvent.setVisible(true);
        } catch (SQLException ex) {
            statusEvent.setForeground(Color.red);
            statusEvent.setText("ERRORE");
            statusEvent.setVisible(true);
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addEventButtonActionPerformed

    private void SMEventInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SMEventInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SMEventInputActionPerformed

    private void typeEventInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeEventInputActionPerformed
        int x = typeEventInput.getSelectedIndex();
        if (x == 0) {
            exprEv.setText("Return: ");
        } else if (x == 1) {
            exprEv.setText("When: ");
        } else if (x == 2) {
            exprEv.setText("Return: ");
        } else {
            exprEv.setText("Expression: ");
        }
    }//GEN-LAST:event_typeEventInputActionPerformed

    private void searchButtonActionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionsActionPerformed
        try {
            searchBoxSet("Action", searchActionsBox, searchActionsInput, listActionsTable);
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchButtonActionsActionPerformed

    private void searchButtonEventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonEventsActionPerformed
        try {
            searchBoxSet("Event", searchEventsBox, searchEventsInput, listEventsTable);
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchButtonEventsActionPerformed

    private void searchButtonTransitionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonTransitionsActionPerformed
        try {
            searchBoxSet("Transition", searchTransitionsBox, searchTransitionsInput, listTransitionsTable);
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchButtonTransitionsActionPerformed

    private void searchButtonStatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonStatesActionPerformed
        try {
            searchBoxSet("State", searchStatesBox, searchStatesInput, listStatesTable);
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchButtonStatesActionPerformed

    private void searchButtonSMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonSMActionPerformed
        try {
            searchBoxSet("StateMachine", searchSMBox, searchSMInput, listSMTable);
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchButtonSMActionPerformed

    private void listSMTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_listSMTablePropertyChange
        
    }//GEN-LAST:event_listSMTablePropertyChange

    private void listSMTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listSMTableKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_listSMTableKeyPressed

    private void modSMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modSMActionPerformed
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "modSMView");
    }//GEN-LAST:event_modSMActionPerformed

    private void addSMButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSMButton1ActionPerformed
        try {
            String addSMQuery = "UPDATE StateMachine SET NAME = ? , DESCRIPTION = ? , ABSPATH = ? , AUTHOR = ? "
                    + "WHERE SID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(addSMQuery);
            preparedStatement.setString(1, nameSMInput1.getText());
            preparedStatement.setString(2, descSMInput1.getText());
            preparedStatement.setString(3, pathSMInput1.getText());
            preparedStatement.setString(4, authorSMInput1.getText());
            preparedStatement.setInt(5, Integer.parseInt(idSMInput1.getText()));
            preparedStatement.executeQuery();
            statusSM1.setForeground(Color.green);
            statusSM1.setText("Modificato");
            statusSM1.setVisible(true);
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addSMButton1ActionPerformed

    private void addStateButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStateButton1ActionPerformed
        try {
            String addSMQuery = "UPDATE State SET DESCRIPTION = ? , VISIBILITY = ? , ISTOP = ? , ISFINAL = ? "
                    + "WHERE StateID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(addSMQuery);
            preparedStatement.setString(1, descStateInput1.getText());
            preparedStatement.setString(2, visibilityStateInput1.getSelectedItem().toString());
            if (isTopInput1.getModel().isSelected()) {
                preparedStatement.setInt(3, 1);
            } else {
                preparedStatement.setInt(3, 0);
            }
            if (isFinalInput1.getModel().isSelected()) {
                preparedStatement.setInt(4, 1);
            } else {
                preparedStatement.setInt(4, 0);
            }
            preparedStatement.setInt(5, Integer.parseInt(idStateInput1.getText()));
            preparedStatement.executeQuery();
            PreparedStatement stmt1 = connection.prepareStatement("SELECT VID FROM State WHERE StateID = " + Integer.parseInt(idStateInput1.getText()));
            ResultSet rs = stmt1.executeQuery();
            rs.next();
            PreparedStatement stmt = connection.prepareStatement("UPDATE StateVertex SET NAME = ? WHERE VID = ?");
            stmt.setString(1, nameStateInput2.getText());
            stmt.setInt(2, rs.getInt(1));
            stmt.executeUpdate();
            statusState1.setForeground(Color.green);
            statusState1.setText("Modificato");
            statusState1.setVisible(true);
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addStateButton1ActionPerformed

    private void addTransitionButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTransitionButton1ActionPerformed
        try {
            String addTransitionQuery = "UPDATE Transition SET NAME = ? , VISIBILITY = ? , SOURCE = ? , TARGET = ?  WHERE TransitionID = ?";
            // String addTransitionQuery = "INSERT INTO Transition VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatementTR = connection.prepareStatement(addTransitionQuery);
            preparedStatementTR.setInt(5, Integer.parseInt(idTransitionInput1.getText()));
            preparedStatementTR.setString(1, nameTransitionInput1.getText());
            preparedStatementTR.setString(2, visibilityTransitionInput1.getSelectedItem().toString());
            preparedStatementTR.setInt(3, Integer.parseInt(sourceTransitionInput1.getText()));
            preparedStatementTR.setInt(4, Integer.parseInt(targetTransitionInput1.getText()));
            preparedStatementTR.executeUpdate();
            statusTransition1.setForeground(Color.green);
            statusTransition1.setText("Modificato");
            statusTransition1.setVisible(true);
        } catch (SQLException ex) {
            statusTransition1.setForeground(Color.red);
            statusTransition1.setText("ERRORE");
            statusTransition1.setVisible(true);
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addTransitionButton1ActionPerformed

    private void chooseSourceTransition1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseSourceTransition1ActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM State NATURAL JOIN StateVertex"
                                                + " WHERE SID = (?)");
            stmt.setInt(1, Integer.parseInt(SMTransitionInput1.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(10);
    }//GEN-LAST:event_chooseSourceTransition1ActionPerformed

    private void chooseTargetTransition1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseTargetTransition1ActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM State NATURAL JOIN StateVertex"
                                                + " WHERE SID = (?)");
            stmt.setInt(1, Integer.parseInt(SMTransitionInput1.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(11);
    }//GEN-LAST:event_chooseTargetTransition1ActionPerformed

    private void chooseStateAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseStateAction1ActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM State NATURAL JOIN StateVertex"
                                                + " WHERE SID = (?)");
            stmt.setInt(1, Integer.parseInt(SMActionInput1.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(13);
    }//GEN-LAST:event_chooseStateAction1ActionPerformed

    private void chooseTransitionAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseTransitionAction1ActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM Transition"
                                                + " WHERE Source IN (SELECT VID FROM StateVertex WHERE SID = (?))");
            stmt.setInt(1, Integer.parseInt(SMActionInput1.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(14);
    }//GEN-LAST:event_chooseTransitionAction1ActionPerformed

    private void addActionButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionButton1ActionPerformed
        try {
            if (stateActionInput1.getText().equals("")) {
                String addSMQuery = "UPDATE Action SET NAME = ? , VISIBILITY = ? , TRANSITIONID = ? "
                        + "WHERE ActionID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(addSMQuery);
                preparedStatement.setString(1, nameActionInput1.getText());
                preparedStatement.setString(2, visibilityActionInput1.getSelectedItem().toString());
                preparedStatement.setInt(3, Integer.parseInt(transitionActionInput1.getText()));
                preparedStatement.setInt(4, Integer.parseInt(idActionInput1.getText()));
                preparedStatement.executeQuery();
                statusAction1.setForeground(Color.green);
                statusAction1.setText("Modificato");
                statusAction1.setVisible(true);
            } else if (transitionActionInput1.getText().equals("")) {
                String addSMQuery = "UPDATE Action SET NAME = ? , VISIBILITY = ? , STATEID = ? , TRANSITIONID = ? "
                        + "WHERE ActionID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(addSMQuery);
                preparedStatement.setString(1, nameActionInput1.getText());
                preparedStatement.setString(2, visibilityActionInput1.getSelectedItem().toString());
                preparedStatement.setInt(3, Integer.parseInt(stateActionInput1.getText()));
                preparedStatement.setInt(4, Integer.parseInt(idActionInput1.getText()));
                preparedStatement.executeQuery();
                statusAction1.setForeground(Color.green);
                statusAction1.setText("Modificato");
                statusAction1.setVisible(true);
            } else {
                String addSMQuery = "UPDATE Action SET NAME = ? , VISIBILITY = ? , STATEID = ? , TRANSITIONID = ? "
                        + "WHERE ActionID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(addSMQuery);
                preparedStatement.setString(1, nameActionInput1.getText());
                preparedStatement.setString(2, visibilityActionInput1.getSelectedItem().toString());
                preparedStatement.setInt(3, Integer.parseInt(stateActionInput1.getText()));
                preparedStatement.setInt(4, Integer.parseInt(transitionActionInput1.getText()));
                preparedStatement.setInt(5, Integer.parseInt(idActionInput1.getText()));
                preparedStatement.executeQuery();
                statusAction1.setForeground(Color.green);
                statusAction1.setText("Modificato");
                statusAction1.setVisible(true);
            }
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addActionButton1ActionPerformed

    private void SMActionInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SMActionInput1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SMActionInput1ActionPerformed

    private void chooseStateEvent1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseStateEvent1ActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM State NATURAL JOIN StateVertex"
                                                + " WHERE SID = (?)");
            stmt.setInt(1, Integer.parseInt(SMEventInput1.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(18);
    }//GEN-LAST:event_chooseStateEvent1ActionPerformed

    private void chooseTransitionEvent1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseTransitionEvent1ActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM Transition"
                                                + " WHERE Source IN (SELECT VID FROM StateVertex WHERE SID = (?))");
            stmt.setInt(1, Integer.parseInt(SMEventInput1.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(16);
    }//GEN-LAST:event_chooseTransitionEvent1ActionPerformed

    private void addEventButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventButton1ActionPerformed
        try {
            if (stateEventInput1.getText().equals("")) {
                String addSMQuery = "UPDATE Event SET NAME = ? , VISIBILITY = ? , TRANSITIONID = ? "
                    + "WHERE EventID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(addSMQuery);
                preparedStatement.setString(1, nameEventInput1.getText());
                preparedStatement.setString(2, visibilityEventInput1.getSelectedItem().toString());
                preparedStatement.setInt(3, Integer.parseInt(transitionEventInput1.getText()));
                preparedStatement.setInt(4, Integer.parseInt(idEventInput1.getText()));
                preparedStatement.executeQuery();
                statusEvent1.setForeground(Color.green);
                statusEvent1.setText("Modificato");
                statusEvent1.setVisible(true);
            } else if (transitionEventInput1.getText().equals("")) {
                String addSMQuery = "UPDATE Event SET NAME = ? , VISIBILITY = ? , STATEID = ? "
                    + "WHERE EventID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(addSMQuery);
                preparedStatement.setString(1, nameEventInput1.getText());
                preparedStatement.setString(2, visibilityEventInput1.getSelectedItem().toString());
                preparedStatement.setInt(3, Integer.parseInt(stateEventInput1.getText()));
                preparedStatement.setInt(4, Integer.parseInt(idEventInput1.getText()));
                preparedStatement.executeQuery();
                statusEvent1.setForeground(Color.green);
                statusEvent1.setText("Modificato");
                statusEvent1.setVisible(true);
            } else {
                String addSMQuery = "UPDATE Event SET NAME = ? , VISIBILITY = ? , STATEID = ? , TRANSITIONID = ? "
                    + "WHERE EventID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(addSMQuery);
                preparedStatement.setString(1, nameEventInput1.getText());
                preparedStatement.setString(2, visibilityEventInput1.getSelectedItem().toString());
                preparedStatement.setInt(3, Integer.parseInt(stateEventInput1.getText()));
                preparedStatement.setInt(4, Integer.parseInt(transitionEventInput1.getText()));
                preparedStatement.setInt(5, Integer.parseInt(idEventInput1.getText()));
                preparedStatement.executeQuery();
                statusEvent1.setForeground(Color.green);
                statusEvent1.setText("Modificato");
                statusEvent1.setVisible(true);
            }

        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addEventButton1ActionPerformed

    private void SMEventInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SMEventInput1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SMEventInput1ActionPerformed

    private void chooseModSMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseModSMActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM StateMachine");
            //stmt.setInt(1, Integer.parseInt(SMTransitionInput.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(7);
    }//GEN-LAST:event_chooseModSMActionPerformed

    private void chooseModStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseModStateActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM StateVertex NATURAL JOIN State WHERE SID = ?");
            stmt.setInt(1, Integer.parseInt(SMStateInput1.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(8);
    }//GEN-LAST:event_chooseModStateActionPerformed

    private void modStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modStateActionPerformed
        try {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "modStateView");
            PreparedStatement stmt2 = connection.prepareStatement("SELECT SID FROM StateMachine");
            ResultSet resultSet2 = stmt2.executeQuery();
            SMStateInput1.removeAllItems();
            while (resultSet2.next()) {
                SMStateInput1.addItem(resultSet2.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_modStateActionPerformed

    private void modTransitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modTransitionActionPerformed
        try {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "modTransitionView");
            PreparedStatement stmt2 = connection.prepareStatement("SELECT SID FROM StateMachine");
            ResultSet resultSet2 = stmt2.executeQuery();
            SMTransitionInput1.removeAllItems();
            while (resultSet2.next()) {
                SMTransitionInput1.addItem(resultSet2.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_modTransitionActionPerformed

    private void chooseTransitionModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseTransitionModActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM Transition");
            //stmt.setInt(1, Integer.parseInt(SMTransitionInput1.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(9);
    }//GEN-LAST:event_chooseTransitionModActionPerformed

    private void SMTransitionInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SMTransitionInput1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SMTransitionInput1ActionPerformed

    private void modActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modActionActionPerformed
        try {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "modActionView");
            PreparedStatement stmt2 = connection.prepareStatement("SELECT SID FROM StateMachine");
            ResultSet resultSet2 = stmt2.executeQuery();
            SMActionInput1.removeAllItems();
            while (resultSet2.next()) {
                SMActionInput1.addItem(resultSet2.getString(1));
            }
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_modActionActionPerformed

    private void chooseModActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseModActionActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM Action");
            //stmt.setInt(1, Integer.parseInt(SMTransitionInput1.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(12);
    }//GEN-LAST:event_chooseModActionActionPerformed

    private void modEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modEventActionPerformed
        try {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "modEventView");
            PreparedStatement stmt2 = connection.prepareStatement("SELECT SID FROM StateMachine");
            ResultSet resultSet2 = stmt2.executeQuery();
            SMEventInput1.removeAllItems();
            while (resultSet2.next()) {
                SMEventInput1.addItem(resultSet2.getString(1));
            }
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_modEventActionPerformed

    private void chooseModEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseModEventActionPerformed
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM Event");
            //stmt.setInt(1, Integer.parseInt(SMTransitionInput1.getSelectedItem().toString()));
            ResultSet resultSet = stmt.executeQuery();
            availableTable.setModel(mapResultSet(resultSet));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }      
        showDialog(15);
    }//GEN-LAST:event_chooseModEventActionPerformed

    private void changeServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeServerActionPerformed
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "server");
    }//GEN-LAST:event_changeServerActionPerformed

    private void usernameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameInputActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            connection = customSQL(hostInput.getText(), usernameInput.getText(), passwordInput.getPassword(), 
                    Integer.parseInt(portInput.getText()));
        } catch (SQLException ex) {
            showErr(ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        statusEvent2.setForeground(Color.green);
        statusEvent2.setText("Connesso");
        statusEvent2.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuItemExit;
    private javax.swing.JComboBox SMActionInput;
    private javax.swing.JComboBox SMActionInput1;
    private javax.swing.JComboBox SMEventInput;
    private javax.swing.JComboBox SMEventInput1;
    private javax.swing.JLabel SMState;
    private javax.swing.JLabel SMState1;
    private javax.swing.JComboBox SMStateInput;
    private javax.swing.JComboBox SMStateInput1;
    private javax.swing.JComboBox SMTransitionInput;
    private javax.swing.JComboBox SMTransitionInput1;
    private javax.swing.JMenuItem addAction;
    private javax.swing.JButton addActionButton;
    private javax.swing.JButton addActionButton1;
    private javax.swing.JPanel addActionView;
    private javax.swing.JMenuItem addEvent;
    private javax.swing.JButton addEventButton;
    private javax.swing.JButton addEventButton1;
    private javax.swing.JPanel addEventView;
    private javax.swing.JMenuItem addSM;
    private javax.swing.JButton addSMButton;
    private javax.swing.JButton addSMButton1;
    private javax.swing.JPanel addSMView;
    private javax.swing.JMenuItem addState;
    private javax.swing.JButton addStateButton;
    private javax.swing.JButton addStateButton1;
    private javax.swing.JPanel addStateView;
    private javax.swing.JMenuItem addTransition;
    private javax.swing.JButton addTransitionButton;
    private javax.swing.JButton addTransitionButton1;
    private javax.swing.JPanel addTransitionView;
    private javax.swing.JLabel authorSM;
    private javax.swing.JLabel authorSM1;
    private javax.swing.JTextField authorSMInput;
    private javax.swing.JTextField authorSMInput1;
    private javax.swing.JDialog availDialog;
    private javax.swing.JTable availableTable;
    private javax.swing.JMenuItem changeServer;
    private javax.swing.JButton chooseDialog;
    private javax.swing.JButton chooseModAction;
    private javax.swing.JButton chooseModEvent;
    private javax.swing.JButton chooseModSM;
    private javax.swing.JButton chooseModState;
    private javax.swing.JButton chooseSourceTransition;
    private javax.swing.JButton chooseSourceTransition1;
    private javax.swing.JButton chooseStateAction;
    private javax.swing.JButton chooseStateAction1;
    private javax.swing.JButton chooseStateEvent;
    private javax.swing.JButton chooseStateEvent1;
    private javax.swing.JButton chooseTargetTransition;
    private javax.swing.JButton chooseTargetTransition1;
    private javax.swing.JButton chooseTransitionAction;
    private javax.swing.JButton chooseTransitionAction1;
    private javax.swing.JButton chooseTransitionEvent;
    private javax.swing.JButton chooseTransitionEvent1;
    private javax.swing.JButton chooseTransitionMod;
    private javax.swing.JPanel defaultView;
    private javax.swing.JPanel defaultView1;
    private javax.swing.JButton deleteButtonActions;
    private javax.swing.JButton deleteButtonEvents;
    private javax.swing.JButton deleteButtonListSM;
    private javax.swing.JButton deleteButtonStates;
    private javax.swing.JButton deleteButtonTransition;
    private javax.swing.JLabel descSM;
    private javax.swing.JLabel descSM1;
    private javax.swing.JTextField descSMInput;
    private javax.swing.JTextField descSMInput1;
    private javax.swing.JLabel descState;
    private javax.swing.JLabel descState1;
    private javax.swing.JTextField descStateInput;
    private javax.swing.JTextField descStateInput1;
    private javax.swing.JTextArea errText;
    private javax.swing.JDialog errorDialog;
    private javax.swing.JMenu eventsMenu;
    private javax.swing.JLabel exprEv;
    private javax.swing.JTextField exprEvent;
    private javax.swing.JTextField hostInput;
    private javax.swing.JLabel idAction;
    private javax.swing.JLabel idAction1;
    private javax.swing.JTextField idActionInput;
    private javax.swing.JTextField idActionInput1;
    private javax.swing.JLabel idEvent;
    private javax.swing.JLabel idEvent1;
    private javax.swing.JTextField idEventInput;
    private javax.swing.JTextField idEventInput1;
    private javax.swing.JLabel idSM;
    private javax.swing.JLabel idSM1;
    private javax.swing.JTextField idSMInput;
    private javax.swing.JTextField idSMInput1;
    private javax.swing.JLabel idState;
    private javax.swing.JLabel idState1;
    private javax.swing.JTextField idStateInput;
    private javax.swing.JTextField idStateInput1;
    private javax.swing.JLabel idTransition;
    private javax.swing.JLabel idTransition1;
    private javax.swing.JTextField idTransitionInput;
    private javax.swing.JTextField idTransitionInput1;
    private javax.swing.JCheckBox isFinalInput;
    private javax.swing.JCheckBox isFinalInput1;
    private javax.swing.JCheckBox isTopInput;
    private javax.swing.JCheckBox isTopInput1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuItem listActions;
    private javax.swing.JTable listActionsTable;
    private javax.swing.JPanel listActionsView;
    private javax.swing.JMenuItem listEvents;
    private javax.swing.JTable listEventsTable;
    private javax.swing.JPanel listEventsView;
    private javax.swing.JMenuItem listSM;
    private javax.swing.JTable listSMTable;
    private javax.swing.JPanel listSMView;
    private javax.swing.JMenuItem listStates;
    private javax.swing.JTable listStatesTable;
    private javax.swing.JPanel listStatesView;
    private javax.swing.JMenuItem listTransitions;
    private javax.swing.JTable listTransitionsTable;
    private javax.swing.JPanel listTransitionsView;
    private javax.swing.JMenu mainMenu;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuItem modAction;
    private javax.swing.JPanel modActionView;
    private javax.swing.JMenuItem modEvent;
    private javax.swing.JPanel modEventView;
    private javax.swing.JMenu modMenu;
    private javax.swing.JMenuItem modSM;
    private javax.swing.JPanel modSMView;
    private javax.swing.JMenuItem modState;
    private javax.swing.JPanel modStateView;
    private javax.swing.JMenuItem modTransition;
    private javax.swing.JPanel modTransitionView;
    private javax.swing.JLabel nameAction;
    private javax.swing.JLabel nameAction1;
    private javax.swing.JTextField nameActionInput;
    private javax.swing.JTextField nameActionInput1;
    private javax.swing.JLabel nameEvent;
    private javax.swing.JLabel nameEvent1;
    private javax.swing.JTextField nameEventInput;
    private javax.swing.JTextField nameEventInput1;
    private javax.swing.JLabel nameSM;
    private javax.swing.JLabel nameSM1;
    private javax.swing.JTextField nameSMInput;
    private javax.swing.JTextField nameSMInput1;
    private javax.swing.JLabel nameState;
    private javax.swing.JLabel nameState1;
    private javax.swing.JTextField nameStateInput1;
    private javax.swing.JTextField nameStateInput2;
    private javax.swing.JLabel nameTransition;
    private javax.swing.JLabel nameTransition1;
    private javax.swing.JTextField nameTransitionInput;
    private javax.swing.JTextField nameTransitionInput1;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JLabel pathSM;
    private javax.swing.JLabel pathSM1;
    private javax.swing.JTextField pathSMInput;
    private javax.swing.JTextField pathSMInput1;
    private javax.swing.JTextField portInput;
    private javax.swing.JButton refreshListActions;
    private javax.swing.JButton refreshListEvents;
    private javax.swing.JButton refreshListSM;
    private javax.swing.JButton refreshListStates;
    private javax.swing.JButton refreshListTransitions;
    private javax.swing.JComboBox searchActionsBox;
    private javax.swing.JTextField searchActionsInput;
    private javax.swing.JButton searchButtonActions;
    private javax.swing.JButton searchButtonEvents;
    private javax.swing.JButton searchButtonSM;
    private javax.swing.JButton searchButtonStates;
    private javax.swing.JButton searchButtonTransitions;
    private javax.swing.JComboBox searchEventsBox;
    private javax.swing.JTextField searchEventsInput;
    private javax.swing.JComboBox searchSMBox;
    private javax.swing.JTextField searchSMInput;
    private javax.swing.JComboBox searchStatesBox;
    private javax.swing.JTextField searchStatesInput;
    private javax.swing.JComboBox searchTransitionsBox;
    private javax.swing.JTextField searchTransitionsInput;
    private javax.swing.JLabel smAction;
    private javax.swing.JLabel smAction1;
    private javax.swing.JLabel smEvent;
    private javax.swing.JLabel smEvent1;
    private javax.swing.JLabel smTransition;
    private javax.swing.JLabel smTransition1;
    private javax.swing.JLabel sourceTransition;
    private javax.swing.JLabel sourceTransition1;
    private javax.swing.JTextField sourceTransitionInput;
    private javax.swing.JTextField sourceTransitionInput1;
    private javax.swing.JLabel stateAction;
    private javax.swing.JLabel stateAction1;
    private javax.swing.JTextField stateActionInput;
    private javax.swing.JTextField stateActionInput1;
    private javax.swing.JLabel stateEvent;
    private javax.swing.JLabel stateEvent1;
    private javax.swing.JTextField stateEventInput;
    private javax.swing.JTextField stateEventInput1;
    private javax.swing.JMenu stateMachinesMenu;
    private javax.swing.JMenu statesMenu;
    private javax.swing.JLabel statusAction;
    private javax.swing.JLabel statusAction1;
    private javax.swing.JLabel statusEvent;
    private javax.swing.JLabel statusEvent1;
    private javax.swing.JLabel statusEvent2;
    private javax.swing.JLabel statusSM;
    private javax.swing.JLabel statusSM1;
    private javax.swing.JLabel statusState;
    private javax.swing.JLabel statusState1;
    private javax.swing.JLabel statusTransition;
    private javax.swing.JLabel statusTransition1;
    private javax.swing.JLabel targetTransition;
    private javax.swing.JLabel targetTransition1;
    private javax.swing.JTextField targetTransitionInput;
    private javax.swing.JTextField targetTransitionInput1;
    private javax.swing.JLabel transitionAction;
    private javax.swing.JLabel transitionAction1;
    private javax.swing.JTextField transitionActionInput;
    private javax.swing.JTextField transitionActionInput1;
    private javax.swing.JLabel transitionEvent;
    private javax.swing.JLabel transitionEvent1;
    private javax.swing.JTextField transitionEventInput;
    private javax.swing.JTextField transitionEventInput1;
    private javax.swing.JMenu transitionsMenu;
    private javax.swing.JComboBox typeEventInput;
    private javax.swing.JTextField usernameInput;
    private javax.swing.JLabel visibilityAction;
    private javax.swing.JLabel visibilityAction1;
    private javax.swing.JComboBox visibilityActionInput;
    private javax.swing.JComboBox visibilityActionInput1;
    private javax.swing.JLabel visibilityEvent;
    private javax.swing.JLabel visibilityEvent1;
    private javax.swing.JComboBox visibilityEventInput;
    private javax.swing.JComboBox visibilityEventInput1;
    private javax.swing.JLabel visibilityState;
    private javax.swing.JLabel visibilityState1;
    private javax.swing.JComboBox visibilityStateInput;
    private javax.swing.JComboBox visibilityStateInput1;
    private javax.swing.JLabel visibilityTransition;
    private javax.swing.JLabel visibilityTransition1;
    private javax.swing.JComboBox visibilityTransitionInput;
    private javax.swing.JComboBox visibilityTransitionInput1;
    // End of variables declaration//GEN-END:variables
}
