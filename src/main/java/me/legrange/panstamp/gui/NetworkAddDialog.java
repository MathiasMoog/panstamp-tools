package me.legrange.panstamp.gui;

import gnu.io.CommPortIdentifier;
import java.awt.CardLayout;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import me.legrange.panstamp.Gateway;
import me.legrange.panstamp.GatewayException;
import me.legrange.panstamp.gui.model.HexDocument;
import me.legrange.panstamp.gui.model.IntegerDocument;
import me.legrange.panstamp.gui.model.Model;
import me.legrange.panstamp.ModemException;
import me.legrange.swap.ModemSetup;
import me.legrange.swap.SwapException;

/**
 *
 * @author gideon
 */
public class NetworkAddDialog extends javax.swing.JDialog {

    private enum State {

        SELECT_TYPE, SELECT_SERIAL, ENTER_TCP, ENTER_NETWORK, END;
    };

    /**
     * Creates new form PanStampSettingsDialog
     */
    public NetworkAddDialog(java.awt.Frame parent, Model model) {
        super(parent, true);
        this.model = model;
        initComponents();
        state.push(State.SELECT_TYPE);
        applyState();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        typeButtonGroup = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        outerSplitPane = new javax.swing.JSplitPane();
        headerPanel = new javax.swing.JPanel();
        innerSplitPane = new javax.swing.JSplitPane();
        contentPanel = new javax.swing.JPanel();
        createNetworkPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        configTcpPanel = new javax.swing.JPanel();
        hostLabel = new javax.swing.JLabel();
        tcpPortLabel = new javax.swing.JLabel();
        tcpPortTextField = new javax.swing.JTextField();
        hostTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        selectTypePanel = new javax.swing.JPanel();
        serialRadioButton = new javax.swing.JRadioButton();
        tcpRadioButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        configSerialPanel = new javax.swing.JPanel();
        portLabel = new javax.swing.JLabel();
        speedLabel = new javax.swing.JLabel();
        speedComboBox = new javax.swing.JComboBox();
        portComboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        configNetworkPanel = new javax.swing.JPanel();
        frequencyLabel = new javax.swing.JLabel();
        channelTextField = new javax.swing.JTextField();
        networkIDLabel = new javax.swing.JLabel();
        networkTextField = new javax.swing.JTextField();
        addressLabel = new javax.swing.JLabel();
        addressTextField = new javax.swing.JTextField();
        securityLabel = new javax.swing.JLabel();
        securityTextField = new javax.swing.JTextField();
        buttonPanel = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        finishButton = new javax.swing.JButton();

        jLabel2.setText("Add a PanStamp network:");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Network");
        setResizable(false);
        getContentPane().setLayout(new java.awt.FlowLayout());

        outerSplitPane.setBorder(null);
        outerSplitPane.setDividerSize(0);
        outerSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        outerSplitPane.setRightComponent(headerPanel);

        innerSplitPane.setBorder(null);
        innerSplitPane.setDividerSize(0);
        innerSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        contentPanel.setLayout(new java.awt.CardLayout());

        createNetworkPanel.setName("create-network-panel"); // NOI18N

        jLabel6.setText("Network Summary");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("To create your new network, click 'Finish'");

        javax.swing.GroupLayout createNetworkPanelLayout = new javax.swing.GroupLayout(createNetworkPanel);
        createNetworkPanel.setLayout(createNetworkPanelLayout);
        createNetworkPanelLayout.setHorizontalGroup(
            createNetworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createNetworkPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(createNetworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                .addContainerGap())
        );
        createNetworkPanelLayout.setVerticalGroup(
            createNetworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createNetworkPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        contentPanel.add(createNetworkPanel, "create-network-panel");

        configTcpPanel.setName("config-tcp-panel"); // NOI18N

        hostLabel.setText("Host name/address:");
        hostLabel.setToolTipText("");

        tcpPortLabel.setText("TCP Port:");
        tcpPortLabel.setToolTipText("");

        tcpPortTextField.setColumns(4);
        tcpPortTextField.setDocument(new IntegerDocument(1,65535));
        tcpPortTextField.setText("3333");
        tcpPortTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcpPortTextFieldActionPerformed(evt);
            }
        });
        tcpPortTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tcpPortTextFieldPropertyChange(evt);
            }
        });

        hostTextField.setColumns(127);
        hostTextField.setText("localhost");
        hostTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                hostTextFieldPropertyChange(evt);
            }
        });

        jLabel5.setText("Enter hostname and TCP port");

        javax.swing.GroupLayout configTcpPanelLayout = new javax.swing.GroupLayout(configTcpPanel);
        configTcpPanel.setLayout(configTcpPanelLayout);
        configTcpPanelLayout.setHorizontalGroup(
            configTcpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configTcpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configTcpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(configTcpPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(configTcpPanelLayout.createSequentialGroup()
                        .addGroup(configTcpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hostLabel)
                            .addComponent(tcpPortLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(configTcpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tcpPortTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        configTcpPanelLayout.setVerticalGroup(
            configTcpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configTcpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(16, 16, 16)
                .addGroup(configTcpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hostLabel)
                    .addComponent(hostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(configTcpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tcpPortLabel)
                    .addComponent(tcpPortTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contentPanel.add(configTcpPanel, "config-tcp-panel");

        selectTypePanel.setName("select-type-panel"); // NOI18N

        typeButtonGroup.add(serialRadioButton);
        serialRadioButton.setSelected(true);
        serialRadioButton.setText("Serial Network");
        serialRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serialRadioButtonActionPerformed(evt);
            }
        });

        typeButtonGroup.add(tcpRadioButton);
        tcpRadioButton.setText("TCP/IP Network");

        jLabel1.setText("Select the type of PanStamp network to create");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout selectTypePanelLayout = new javax.swing.GroupLayout(selectTypePanel);
        selectTypePanel.setLayout(selectTypePanelLayout);
        selectTypePanelLayout.setHorizontalGroup(
            selectTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectTypePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(selectTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(selectTypePanelLayout.createSequentialGroup()
                        .addGroup(selectTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tcpRadioButton)
                            .addComponent(serialRadioButton))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        selectTypePanelLayout.setVerticalGroup(
            selectTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectTypePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(serialRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tcpRadioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contentPanel.add(selectTypePanel, "select-type-panel");

        configSerialPanel.setName("config-serial-panel"); // NOI18N

        portLabel.setText("Port:");
        portLabel.setName("config-serial-panel"); // NOI18N

        speedLabel.setText("Speed (bps):");
        speedLabel.setName("config-serial-panel"); // NOI18N

        speedComboBox.setModel(speedListModel());
        speedComboBox.setName("config-serial-panel"); // NOI18N
        speedComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speedComboBoxActionPerformed(evt);
            }
        });

        portComboBox.setModel(portListModel());
        portComboBox.setName("config-serial-panel"); // NOI18N
        portComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portComboBoxActionPerformed(evt);
            }
        });

        jLabel4.setText("Select serial port and baud rate");

        javax.swing.GroupLayout configSerialPanelLayout = new javax.swing.GroupLayout(configSerialPanel);
        configSerialPanel.setLayout(configSerialPanelLayout);
        configSerialPanelLayout.setHorizontalGroup(
            configSerialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configSerialPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configSerialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(configSerialPanelLayout.createSequentialGroup()
                        .addGroup(configSerialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(speedLabel)
                            .addComponent(portLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(configSerialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(speedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(portComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        configSerialPanelLayout.setVerticalGroup(
            configSerialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configSerialPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(configSerialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portLabel))
                .addGap(16, 16, 16)
                .addGroup(configSerialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(speedLabel)
                    .addComponent(speedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contentPanel.add(configSerialPanel, "config-serial-panel");

        configNetworkPanel.setName("config-network-panel"); // NOI18N

        frequencyLabel.setText("Frequency channel:");
        frequencyLabel.setToolTipText("");

        channelTextField.setColumns(4);
        channelTextField.setDocument(new IntegerDocument(0,255));
        channelTextField.setText("0");
        channelTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                channelTextFieldPropertyChange(evt);
            }
        });

        networkIDLabel.setText("Network ID:");

        networkTextField.setDocument(new HexDocument(0,65535));
        networkTextField.setText("b547");
        networkTextField.setToolTipText("Network ID in hexadecimal");
        networkTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                networkTextFieldActionPerformed(evt);
            }
        });
        networkTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                networkTextFieldPropertyChange(evt);
            }
        });

        addressLabel.setText("Device address:");

        addressTextField.setDocument(new IntegerDocument(0, 255));
        addressTextField.setText("1");
        addressTextField.setToolTipText("Local device address in decimal");
        addressTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                addressTextFieldPropertyChange(evt);
            }
        });

        securityLabel.setText("Security option:");

        securityTextField.setDocument(new IntegerDocument(0,255));
        securityTextField.setText("0");
        securityTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                securityTextFieldPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout configNetworkPanelLayout = new javax.swing.GroupLayout(configNetworkPanel);
        configNetworkPanel.setLayout(configNetworkPanelLayout);
        configNetworkPanelLayout.setHorizontalGroup(
            configNetworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configNetworkPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configNetworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(frequencyLabel)
                    .addComponent(networkIDLabel)
                    .addComponent(addressLabel)
                    .addComponent(securityLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addGroup(configNetworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(networkTextField)
                    .addComponent(securityTextField)
                    .addComponent(addressTextField)
                    .addComponent(channelTextField))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        configNetworkPanelLayout.setVerticalGroup(
            configNetworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configNetworkPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configNetworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frequencyLabel)
                    .addComponent(channelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(configNetworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(networkIDLabel)
                    .addComponent(networkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(configNetworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLabel)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(configNetworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(securityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(securityLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contentPanel.add(configNetworkPanel, "config-network-panel");

        innerSplitPane.setLeftComponent(contentPanel);

        backButton.setText("< Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        nextButton.setText("Next >");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        finishButton.setText("Finish");
        finishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextButton)
                .addGap(124, 124, 124)
                .addComponent(cancelButton)
                .addGap(5, 5, 5)
                .addComponent(finishButton))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nextButton)
                        .addComponent(backButton))
                    .addComponent(cancelButton)
                    .addComponent(finishButton)))
        );

        innerSplitPane.setRightComponent(buttonPanel);

        outerSplitPane.setLeftComponent(innerSplitPane);

        getContentPane().add(outerSplitPane);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        backward();

    }//GEN-LAST:event_backButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        forward();
    }//GEN-LAST:event_nextButtonActionPerformed

    private void finishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishButtonActionPerformed
        try {
            createNetwork();
        } catch (ModemException | SwapException ex) {
            Logger.getLogger(NetworkAddDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GatewayException ex) {
            Logger.getLogger(NetworkAddDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_finishButtonActionPerformed

    private void serialRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serialRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serialRadioButtonActionPerformed

    private void tcpPortTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tcpPortTextFieldPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tcpPortTextFieldPropertyChange

    private void hostTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_hostTextFieldPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_hostTextFieldPropertyChange

    private void tcpPortTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcpPortTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tcpPortTextFieldActionPerformed

    private void portComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portComboBoxActionPerformed
        //config.setPortName((String) portComboBox.getModel().getSelectedItem());
    }//GEN-LAST:event_portComboBoxActionPerformed

    private void speedComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speedComboBoxActionPerformed
        //config.setPortSpeed((Integer) speedComboBox.getModel().getSelectedItem());
    }//GEN-LAST:event_speedComboBoxActionPerformed

    private void channelTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_channelTextFieldPropertyChange
        String text = channelTextField.getText().trim();
        if ((text != null) && !text.equals("")) {
            //     config.setChannel(Integer.parseInt(text));
        }
    }//GEN-LAST:event_channelTextFieldPropertyChange

    private void networkTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_networkTextFieldPropertyChange
        String text = networkTextField.getText().trim();
        if ((text != null) && !text.equals("")) {
            //     config.setNetworkID(Integer.parseInt(text, 16));
        }
    }//GEN-LAST:event_networkTextFieldPropertyChange

    private void addressTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_addressTextFieldPropertyChange
        String text = addressTextField.getText().trim();
        if ((text != null) && !text.equals("")) {
            //      config.setDeviceAddress(Integer.parseInt(text));
        }
    }//GEN-LAST:event_addressTextFieldPropertyChange

    private void securityTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_securityTextFieldPropertyChange
        String text = securityTextField.getText().trim();
        if ((text != null) && !text.equals("")) {
            //      config.setSecurityOption(Integer.parseInt(text));
        }
    }//GEN-LAST:event_securityTextFieldPropertyChange

    private void networkTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_networkTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_networkTextFieldActionPerformed

    private void forward() {
        switch (getState()) {
            case ENTER_TCP:
            case SELECT_SERIAL:
                state.push(State.ENTER_NETWORK);
                break;
            case SELECT_TYPE:
                if (typeIsSerial()) {
                    state.push(State.SELECT_SERIAL);
                } else {
                    state.push(State.ENTER_TCP);
                }
                break;
            case ENTER_NETWORK:
                state.push(State.END);
                break;
        }
        applyState();
    }

    private void backward() {
        state.pop();
        applyState();
    }

    private void applyState() {
        backButton.setEnabled(getState() != State.SELECT_TYPE);
        nextButton.setEnabled(getState() != State.END);
        finishButton.setEnabled(getState() == State.END);
        // select content for wizard based on state
        JPanel content;
        switch (getState()) {
            case SELECT_TYPE:
                content = selectTypePanel;
                break;
            case ENTER_TCP:
                content = configTcpPanel;
                break;
            case SELECT_SERIAL:
                content = configSerialPanel;
                break;
            case ENTER_NETWORK:
                content = configNetworkPanel;
                break;
            case END:
                content = createNetworkPanel;
                break;
            default:
                return;
        }
        CardLayout clo = (CardLayout) contentPanel.getLayout();
        clo.show(contentPanel, content.getName());
    }

    private State getState() {
        return state.peek();
    }

    private void createNetwork() throws ModemException, SwapException, GatewayException {
        Gateway gw;
        if (typeIsSerial()) {
            gw = Gateway.createSerial(getSerialPort(), getSerialSpeed());
        } else {
            gw = Gateway.createTcp(getTcpHost(), getTcpPort());
        }
        gw.open();
        ModemSetup setup = gw.getSWAPModem().getSetup();
        setup.setChannel(getChannel());
        setup.setDeviceAddress(getDeviceAddress());
        setup.setNetworkID(getNetworkID());
        gw.getSWAPModem().setSetup(setup);
        model.addGateway(gw);
    }

    private String getSerialPort() {
        return portComboBox.getSelectedItem().toString();
    }

    private int getSerialSpeed() {
        return Integer.parseInt(speedComboBox.getSelectedItem().toString());
    }

    private String getTcpHost() {
        return hostTextField.getText();
    }

    private int getTcpPort() {
        return Integer.parseInt(tcpPortTextField.getText());
    }

    private boolean typeIsSerial() {
        return serialRadioButton.isSelected();
    }

    private int getChannel() {
        return Integer.parseInt(channelTextField.getText());
    }

    private int getDeviceAddress() {
        return Integer.parseInt(addressTextField.getText());
    }

    private int getNetworkID() {
        return Integer.parseInt(networkTextField.getText(), 16);
    }

    private String[] getPorts() {
        List<String> serials = new LinkedList<>();
        Enumeration<CommPortIdentifier> ports = CommPortIdentifier.getPortIdentifiers();
        while (ports.hasMoreElements()) {
            CommPortIdentifier cpi = ports.nextElement();
            if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                serials.add(cpi.getName());
            }
        }
        return serials.toArray(new String[]{});
    }

    private ComboBoxModel<String> portListModel() {
        DefaultComboBoxModel<String> mod = new DefaultComboBoxModel<>();

        for (String port : getPorts()) {
            mod.addElement(port);
        }
        return mod;
    }

    private Integer[] getSpeeds() {
        return new Integer[]{38400, 19200, 9600};
    }

    private ComboBoxModel<Integer> speedListModel() {
        DefaultComboBoxModel<Integer> mod = new DefaultComboBoxModel<>();
        for (Integer speed : getSpeeds()) {
            mod.addElement(speed);
        }
        return mod;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JButton backButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField channelTextField;
    private javax.swing.JPanel configNetworkPanel;
    private javax.swing.JPanel configSerialPanel;
    private javax.swing.JPanel configTcpPanel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel createNetworkPanel;
    private javax.swing.JButton finishButton;
    private javax.swing.JLabel frequencyLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel hostLabel;
    private javax.swing.JTextField hostTextField;
    private javax.swing.JSplitPane innerSplitPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel networkIDLabel;
    private javax.swing.JTextField networkTextField;
    private javax.swing.JButton nextButton;
    private javax.swing.JSplitPane outerSplitPane;
    private javax.swing.JComboBox portComboBox;
    private javax.swing.JLabel portLabel;
    private javax.swing.JLabel securityLabel;
    private javax.swing.JTextField securityTextField;
    private javax.swing.JPanel selectTypePanel;
    private javax.swing.JRadioButton serialRadioButton;
    private javax.swing.JComboBox speedComboBox;
    private javax.swing.JLabel speedLabel;
    private javax.swing.JLabel tcpPortLabel;
    private javax.swing.JTextField tcpPortTextField;
    private javax.swing.JRadioButton tcpRadioButton;
    private javax.swing.ButtonGroup typeButtonGroup;
    // End of variables declaration//GEN-END:variables
    private final Model model;
    private final Stack<State> state = new Stack<>();

}
