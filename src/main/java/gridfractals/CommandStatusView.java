package gridfractals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

public class CommandStatusView extends JPanel
{
  final static int UPDATED_STATUS = 1;
  final static int NEEDUPDATE_STATUS = 2;
  final static int RUNNING_STATUS = 3;
  final static String statusNames[] = {"?", "Updated", "Need Update", "Running"};

  Command command = null;

  JLabel nameLabel = new JLabel();
  JLabel statusLabel = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();




  public CommandStatusView( Command command )
  {
  this();
  this.command = command;
  nameLabel.setText(command.getTitle());
  setStatus( NEEDUPDATE_STATUS );
  }


  private CommandStatusView() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    nameLabel.setFont(new java.awt.Font("Dialog", 3, 12));
    nameLabel.setText("Name");
    statusLabel.setText("Status");
    this.setLayout(borderLayout1);
    this.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        this_mousePressed(e);
      }
    });
    this.add(nameLabel,  BorderLayout.CENTER);
    this.add(statusLabel,  BorderLayout.EAST);
  }


  public void setStatus( int status )
  {
  statusLabel.setText( statusNames[status] );
  }


  public void setSelect()
  {
    nameLabel.setForeground(SystemColor.control);
    statusLabel.setForeground(SystemColor.control);
    this.setBackground(UIManager.getColor("Label.foreground"));
  }
  public void clearSelect()
  {
    nameLabel.setForeground(UIManager.getColor("Label.foreground"));
    statusLabel.setForeground(UIManager.getColor("Label.foreground"));
    this.setBackground(SystemColor.control);
  }

  void this_mousePressed(MouseEvent e) {
  Startup.toolsFrame.commandSet.setCurrentSelection( command );
System.out.println("here p");
  }



}