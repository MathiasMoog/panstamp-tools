package me.legrange.panstamp.gui.model.tree;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import me.legrange.panstamp.Endpoint;
import me.legrange.panstamp.GatewayException;
import me.legrange.panstamp.gui.PanStampSettingsDialog;
import me.legrange.panstamp.gui.chart.ChartFactory;
import me.legrange.panstamp.gui.model.DataModel;
import me.legrange.panstamp.gui.model.Format;

/**
 *
 * @author gideon
 */
public class NetworkTreeNodeRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        if (value instanceof NetworkTreeNode) {
            try {
                NetworkTreeNode node = (NetworkTreeNode) value;
                Component com;
                switch (node.getType()) {
                    case ENDPOINT:
                        com = renderEndpoint((EndpointNode) node);
                        break;
                    case REGISTER:
                        com = renderRegister((RegisterNode) node);
                        break;
                    case PANSTAMP:
                        com = renderPanStamp((PanStampNode) node);
                        break;
                    case GATEWAY:
                        com = renderGateway((GatewayNode) node);
                        break;
                    case WORLD:
                        com = renderWorld((WorldNode) node);
                        break;
                    default:
                        com = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                        break;
                }
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                panel.add(com);
                panel.setBackground(sel ? backgroundSelectionColor : tree.getBackground());
                com.setForeground(sel ? textSelectionColor : textNonSelectionColor);
                return panel;
            } catch (GatewayException ex) {
                Logger.getLogger(NetworkTreeNodeRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
    }

    public JPopupMenu getPopupMenu(TreePath path) {
        NetworkTreeNode node = (NetworkTreeNode) path.getLastPathComponent();
        switch (node.getType()) {
            case PANSTAMP:
                return getPanStampPopupMenu((PanStampNode) node);
            case ENDPOINT:
                return getEndpointPopupMenu((EndpointNode) node);
            default:
                return null;
        }

    }

    public NetworkTreeNodeRenderer(DataModel model) {
        this.model = model;
    }

    private JPopupMenu getPanStampPopupMenu(final PanStampNode psn) {
        JPopupMenu menu = new JPopupMenu(psn.toString());
        final JMenuItem settingsItem = new JMenuItem("Settings...");
        settingsItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {    
                new PanStampSettingsDialog(null, psn.getPanStamp()).setVisible(true);
            }
        });
        menu.add(settingsItem);
        final JMenuItem graphItem = new JMenuItem("RSSI/LQI Graph...");
        graphItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ChartFactory.getFactory(model).getSignalChart(psn.getPanStamp()).setVisible(true);
            }
        });
        menu.add(graphItem);
        // register selection
        menu.add(new JSeparator());
        final JMenu regsMenu = new JMenu("Show Standard Registers");
        final JRadioButtonMenuItem allItem = new JRadioButtonMenuItem("All");
        final JRadioButtonMenuItem intItem = new JRadioButtonMenuItem("Interesting");
        final JRadioButtonMenuItem noneItem = new JRadioButtonMenuItem("None", true);
        ActionListener regL = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                allItem.setSelected(false);
                noneItem.setSelected(false);
                intItem.setSelected(false);
                if (e.getSource().equals(noneItem)) {
                    noneItem.setSelected(true);
                }
                if (e.getSource().equals(allItem)) {
                    allItem.setSelected(true);
                }
                if (e.getSource().equals(intItem)) {
                    intItem.setSelected(true);
                }
            }

        };
        allItem.addActionListener(regL);
        noneItem.addActionListener(regL);
        intItem.addActionListener(regL);

        regsMenu.add(allItem);
        regsMenu.add(intItem);
        regsMenu.add(noneItem);
        menu.add(regsMenu);
        return menu;
    }

    private JPopupMenu getEndpointPopupMenu(final EndpointNode epn) {
        JPopupMenu menu = new JPopupMenu(epn.toString());
        final JMenuItem graphItem = new JMenuItem("Data graph...");
        graphItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ChartFactory.getFactory(model).getEndpointChart(epn.getEndpoint()).setVisible(true);
            }
        });
        menu.add(graphItem);
        return menu;
    }

    private Component renderEndpoint(EndpointNode epn) throws GatewayException {
        JLabel label = new JLabel(epn.toString(), getIcon(ICON_ENDPOINT), JLabel.LEADING);
        return label;
    }

    private Component renderRegister(RegisterNode rn) {
        return new JLabel(rn.toString(), getIcon(ICON_REGISTER), JLabel.LEADING);

    }

    private Component renderPanStamp(PanStampNode psn) {
        return new JLabel(psn.toString(), getIcon(ICON_DEVICE), JLabel.LEADING);
    }

    private Component renderGateway(GatewayNode gn) {
        return new JLabel(gn.toString(), getIcon(ICON_NETWORK), JLabel.LEADING);
    }

    private Component renderWorld(WorldNode wn) {
        return new JLabel("", getIcon(ICON_WORLD), JLabel.LEADING);
    }

    private String formatValue(Endpoint ep) throws GatewayException {
        return Format.formatValue(ep);
    }

    private Icon getIcon(String name) {
        ImageIcon ico = icons.get(name);
        if (ico == null) {
            try {
                ico = new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream("images/" + name)));
            } catch (IOException ex) {
                Logger.getLogger(NetworkTreeNodeRenderer.class.getName()).log(Level.SEVERE, null, ex);

            }
            icons.put(name, ico);
        }

        return ico;
    }

    private static final String ICON_WORLD = "world16x16.png";
    private static final String ICON_NETWORK = "network16x16.png";
    private static final String ICON_DEVICE = "device16x16.png";
    private static final String ICON_REGISTER = "register16x16.png";
    private static final String ICON_ENDPOINT = "endpoint16x16.png";
    private final Map<String, ImageIcon> icons = new HashMap<>();
    private final DataModel model;
}