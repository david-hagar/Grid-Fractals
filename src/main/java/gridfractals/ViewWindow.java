package gridfractals;

import javax.swing.*;
import java.awt.*;


/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

public class ViewWindow extends JFrame {
  public GridView gridView = new GridView();

  public ViewWindow() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.setTitle("View Window");
    this.getContentPane().add(gridView, BorderLayout.CENTER);
  }
}