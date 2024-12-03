package utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JScrollBar;

class ScrollPopupLayout implements LayoutManager{
    @Override public void addLayoutComponent(String name, Component comp) {}
    @Override public void removeLayoutComponent(Component comp) {}

    @Override public Dimension preferredLayoutSize(Container parent) {
        int visibleAmount = Integer.MAX_VALUE;
        Dimension dim = new Dimension();
        for (Component comp : parent.getComponents()){
            if (comp.isVisible()) {
                if (comp instanceof JScrollBar){
                    JScrollBar scrollBar = (JScrollBar) comp;
                    visibleAmount = scrollBar.getVisibleAmount();
                }
                else {
                    Dimension pref = comp.getPreferredSize();
                    dim.width = Math.max(dim.width, pref.width);
                    dim.height += pref.height;
                }
            }
        }

        Insets insets = parent.getInsets();
        dim.height = Math.min(dim.height + insets.top + insets.bottom, visibleAmount);

        return dim;
    }

    @Override public Dimension minimumLayoutSize(Container parent) {
        int visibleAmount = Integer.MAX_VALUE;
        Dimension dim = new Dimension();
        for (Component comp : parent.getComponents()) {
            if (comp.isVisible()){
                if (comp instanceof JScrollBar) {
                    JScrollBar scrollBar = (JScrollBar) comp;
                    visibleAmount = scrollBar.getVisibleAmount();
                }
                else {
                    Dimension min = comp.getMinimumSize();
                    dim.width = Math.max(dim.width, min.width);
                    dim.height += min.height;
                }
            }
        }

        Insets insets = parent.getInsets();
        dim.height = Math.min(dim.height + insets.top + insets.bottom, visibleAmount);

        return dim;
    }

    @Override public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();

        int width = parent.getWidth() - insets.left - insets.right;
        int height = parent.getHeight() - insets.top - insets.bottom;

        int x = insets.left;
        int y = insets.top;
        int position = 0;

        for (Component comp : parent.getComponents()) {
            if ((comp instanceof JScrollBar) && comp.isVisible()) {
                JScrollBar scrollBar = (JScrollBar) comp;
                Dimension dim = scrollBar.getPreferredSize();
                scrollBar.setBounds(x + width-dim.width, y, dim.width, height);
                width -= dim.width;
                position = scrollBar.getValue();
            }
        }

        y -= position;
        for (Component comp : parent.getComponents()) {
            if (!(comp instanceof JScrollBar) && comp.isVisible()) {
                Dimension pref = comp.getPreferredSize();
                comp.setBounds(x, y, width, pref.height);
                y += pref.height;
            }
        }
    }
}