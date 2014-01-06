package gridfractals;

import javax.swing.*;
import java.awt.*;
import params.*;
import java.util.*;

/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

public class CommandView extends JPanel {

  BorderLayout borderLayout1 = new BorderLayout();
  public CommandStatusView commandStatusView = null;
  public ParameterListView parameterListView = null;

  private CommandView() {
    //commandStatusView = new CommandStatusView("Test");
    parameterListView = new ParameterListView( new ArrayList() );

    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }


  }

  public CommandView( Command command )
  {
    commandStatusView = new CommandStatusView( command );
    parameterListView = new ParameterListView( command.getParameters() );

    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }

  }


  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    this.add(commandStatusView,  BorderLayout.NORTH);
    this.add(parameterListView, BorderLayout.CENTER);
    this.add( new JSeparator( JSeparator.HORIZONTAL ), BorderLayout.SOUTH );
    parameterListView.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
  }








}