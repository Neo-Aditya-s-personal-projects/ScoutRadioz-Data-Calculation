package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class DismissablePopup extends ScrollPopup {

    private boolean popupShown;
    private JButton button;

    public DismissablePopup(JButton button) {
        super();
        this.button = button;

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                makeUI();
            }
        });
    }

    public DismissablePopup() {
      super();
      button = new JButton();
      SwingUtilities.invokeLater(new Runnable() {

          @Override
          public void run() {
              makeUI();
          }
      });
  }

    private void makeUI() {
      JPopupMenu menu = this;

        menu.addPopupMenuListener(new PopupMenuListener() {
 
      @Override
      public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        popupShown = true;
      }
 
      @Override
      public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
 
          @Override
          public void run() {
            popupShown = false;
          }
        });
      }
 
      @Override
      public void popupMenuCanceled(PopupMenuEvent e) {
      }
    });
 
    button.addMouseListener(new MouseAdapter() {
 
      @Override
      public void mousePressed(MouseEvent e) {
        final boolean shown = popupShown;
        SwingUtilities.invokeLater(new Runnable() {
 
          @Override
          public void run() {
            popupShown = shown;
          }
        });
      }
    });
    button.addActionListener(new ActionListener() {
 
      @Override
      public void actionPerformed(ActionEvent e) {
        if (popupShown) {
          setVisible(false);
          popupShown = false;
        } else {
          menu.show(button, (int) (button.getVisibleRect().getCenterX() - (double) button.getWidth() / 2.0) , (int) (button.getVisibleRect().getCenterY() + (double) button.getHeight() / 2.0));
        }
      }
    });
    }

    public void setButton(JButton button) {
      this.button = button;
      makeUI();
    }

    public JButton getButton() {
      return button;
    }

}
