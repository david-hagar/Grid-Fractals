package gridfractals;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

public class CommandSetView extends JPanel
{
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel jLabel1 = new JLabel();

  public CommandSetView() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }


  private void jbInit() throws Exception {
    jLabel1.setText("jLabel1");
    this.setLayout(borderLayout1);
    this.add(jLabel1, BorderLayout.CENTER);
  }

  public void rebuild( CommandSet commandSet )
  {
  this.removeAll();

  JPanel parent = this;

  Iterator iter = commandSet.commands.iterator();
  while (iter.hasNext())
  {
    Command command = (Command) iter.next();
    JPanel panel = new JPanel();
    BorderLayout layout = new BorderLayout();
    panel.setLayout( layout );
    panel.add( command.getView(), BorderLayout.NORTH );
    parent.add( panel, BorderLayout.CENTER );
    parent = panel;
  }

  //Startup.toolsFrame.cspScrollPane.validate();
  //Startup.toolsFrame.cspScrollPane.repaint();
  this.validate();
  }




}