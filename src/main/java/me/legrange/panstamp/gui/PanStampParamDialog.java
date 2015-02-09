package me.legrange.panstamp.gui;

import java.awt.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.InputVerifier;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import me.legrange.panstamp.GatewayException;
import me.legrange.panstamp.PanStamp;
import me.legrange.panstamp.Parameter;
import me.legrange.panstamp.Register;
import me.legrange.panstamp.gui.mvc.Model;

/**
 *
 * @author gideon
 */
public class PanStampParamDialog extends javax.swing.JDialog {

    /**
     * Creates new form PanStampParamDialog
     */
    public PanStampParamDialog(java.awt.Frame parent, Model model, PanStamp ps) {
        super(parent, true);
        this.ps = ps;
        this.model = model;
        initComponents();
        try {
            addParamComponents();
        } catch (GatewayException ex) {
            Logger.getLogger(PanStampParamDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addParamComponents() throws GatewayException {
        for (Register reg : ps.getRegisters()) {
            List<Parameter> pars = reg.getParameters();
            if (!pars.isEmpty()) {
                paramTabbedPane.add(reg.getName(), registerPanel(reg));
            }
        }
    }

    private JPanel registerPanel(Register reg) throws GatewayException {
        JPanel panel = new JPanel();
        for (Parameter par : reg.getParameters()) {
            JLabel label = new JLabel(par.getName(), SwingConstants.LEFT);
            panel.add(label);
            Component com = null;
            switch (par.getType()) {
                case NUMBER:
                    com = makeNumberField(par);
                    break;
                case INTEGER:
                    com = makeIntegerField(par);
                    break;
                case BINARY: {
                    com = makeBinaryField(par);
                    break;
                }
                case STRING: {
                    com = makeStringField(par);
                    break;
                }
            }
            panel.add(com);
            paramMap.put(com, par);
        }
        return panel;
    }

    private Component makeTextField(final Parameter par) throws GatewayException {
        final JTextField field = new JTextField();
        if (par.getRegister().hasValue()) {
            field.setText(par.getValue().toString());
        } else {
            field.setText(par.getDefault().toString());
        }
        field.setInputVerifier(new InputVerifier() {
            
            private final Pattern pattern = Pattern.compile(par.getPattern());

            @Override
            public boolean verify(JComponent input) {
                return (pattern.matcher(field.getText()).matches());
            }
        });
        return field;
    }
    private Component makeStringField(Parameter<String> par) throws GatewayException {
        return makeTextField(par);
    }

    private Component makeBinaryField(Parameter<Boolean> par) throws GatewayException {
        JCheckBox field = new JCheckBox();
        if (par.getRegister().hasValue()) {
            field.setSelected(par.getValue());
        } else {
            field.setSelected(false);
        }
        return field;
    }

    private Component makeIntegerField(Parameter<Integer> par) throws GatewayException {
        return makeTextField(par);
    }

    private Component makeNumberField(Parameter<Double> par) throws GatewayException {
        return makeTextField(par);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        paramTabbedPane = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paramTabbedPane)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addGap(17, 17, 17))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paramTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
       for (Component com : paramMap.keySet()) {
           Parameter par = paramMap.get(com);
           updateFromComponent(par, com);
       }
    }//GEN-LAST:event_okButtonActionPerformed

    private void updateFromComponent(Parameter par, Component com) {
        try {
            switch (par.getType()) {
                case NUMBER:
                    JTextField text = (JTextField)com;
                    double dVal = Double.parseDouble(text.getText());
                    Parameter<Double> dPar = par;
                    if (dVal != dPar.getValue()) {
                        dPar.setValue(dVal);
                    }
                    break;
                case INTEGER:
                    com = makeIntegerField(par);
                    text = (JTextField)com;
                    int iVal = Integer.parseInt(text.getText());
                    Parameter<Integer> iPar = par;
                    if (iVal != iPar.getValue()) {
                        iPar.setValue(iVal);
                    }
                    break;
                case BINARY: {
                    com = makeBinaryField(par);
                    JCheckBox check = (JCheckBox)com;
                    boolean bVal = check.isSelected();
                    Parameter<Boolean> bPar = par;
                    if (bVal != bPar.getValue()) {
                        bPar.setValue(bVal);
                    }
                    break;
                }
                case STRING: {
                    com = makeStringField(par);
                    text = (JTextField)com;
                    String val = text.getText();
                    Parameter<String> sPar = par;
                    if (!val.equals(sPar.getValue())) {
                        sPar.setValue(val);
                    } 
                    break;
                }
            }
            
            // Variables declaration - do not modify
        } catch (GatewayException ex) {
            Logger.getLogger(PanStampParamDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton okButton;
    private javax.swing.JTabbedPane paramTabbedPane;
    // End of variables declaration//GEN-END:variables
    private final PanStamp ps;
    private final Model model;
    private final Map<Component, Parameter> paramMap = new HashMap<>();
}
