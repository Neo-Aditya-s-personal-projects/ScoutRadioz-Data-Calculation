package utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;

public class ScrollPopup extends JPopupMenu {
    private int maxVisibleRows;
    private JScrollBar scroll;


    public ScrollPopup(int maxVisibleRows) {
        super();
        if (maxVisibleRows <= 0) maxVisibleRows = Integer.MAX_VALUE;
        this.maxVisibleRows = maxVisibleRows;

        super.add(getScrollBar());
        setLayout(new ScrollPopupLayout());
        addMouseWheelListener(new MouseWheelListener() {
            @Override public void mouseWheelMoved(MouseWheelEvent event) {
                JScrollBar scrollBar = getScrollBar();
                int amount = (event.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL)
                             ? event.getUnitsToScroll() * scrollBar.getUnitIncrement()
                             : (event.getWheelRotation() < 0 ? -1 : 1) * scrollBar.getBlockIncrement();

                scrollBar.setValue(scrollBar.getValue() + amount);
                event.consume();
            }
        });
    }

    public ScrollPopup() {
        this(Integer.MAX_VALUE);
    }

    public int getMaxVisibleRows() {
        return maxVisibleRows;
    }

    public void setMaxVisibleRows(int maxVisibleRows) {
        if (maxVisibleRows <= 0) return;
        this.maxVisibleRows = maxVisibleRows;
    }

    public JScrollBar getScrollBar() {
        if (scroll == null) {
            scroll = new JScrollBar(JScrollBar.VERTICAL);
            scroll.addAdjustmentListener(new AdjustmentListener() {
                @Override public void adjustmentValueChanged(AdjustmentEvent e) {
                    doLayout();
                    repaint();
                }
            });
        }

        return scroll;
    }

    public void paintChildren(Graphics g) {
        Insets insets = getInsets();
        g.clipRect(insets.left, insets.top, getWidth(), getHeight() - insets.top - insets.bottom);
        super.paintChildren(g);
    }

    protected void addImpl(Component comp, Object constraints, int index) {
        super.addImpl(comp, constraints, index);

        if(maxVisibleRows < getComponentCount()-1) {
            getScrollBar().setVisible(true);
        }
    }

    public void remove(int index) {
        ++index; // To make sure the scroll bar isn't removes

        super.remove(index);

        if (maxVisibleRows >= getComponentCount()-1) {
            getScrollBar().setVisible(false);
        }
    }

    public void show(Component invoker, int x, int y) {
        JScrollBar scrollBar = getScrollBar();
        if(scrollBar.isVisible()) {
            int extent = 0;
            int max = 0;
            int i = 0;
            int unit = -1;
            int width = 0;
            for (Component comp : getComponents()) {
                if (!(comp instanceof JScrollBar)) {
                    Dimension preferredSize = comp.getPreferredSize();
                    width = Math.max(width, preferredSize.width);
                    if (unit < 0) {
                        unit = preferredSize.height;
                    }
                    if (i++ < maxVisibleRows) {
                        extent += preferredSize.height;
                    }
                    max += preferredSize.height;
                }
            }

            Insets insets = getInsets();
            int widthMargin = insets.left + insets.right;
            int heightMargin = insets.top + insets.bottom;
            scrollBar.setUnitIncrement(unit);
            scrollBar.setBlockIncrement(extent);
            scrollBar.setValues(0, heightMargin + extent, 0, heightMargin + max);

            width += scrollBar.getPreferredSize().width + widthMargin;
            int height = heightMargin + extent;

            setPopupSize(new Dimension(width, height));
        }

        super.show(invoker, x, y);
    }
}
