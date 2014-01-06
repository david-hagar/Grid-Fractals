package params;

import javax.swing.JPanel;
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

public class ParameterListView extends JPanel {

  GridLayout gridLayout = new GridLayout();

  private ParameterListView() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public ParameterListView( ArrayList parameters ) {
    this();
    rebuild( parameters );
  }

  private void jbInit() throws Exception {
    gridLayout.setColumns(1);
    this.setLayout(gridLayout);
  }


  public void rebuild( ArrayList parameters )
  {
  this.removeAll();
  gridLayout.setRows( parameters.size() );

  Iterator iter = parameters.iterator();
  while (iter.hasNext())
  {
    Parameter parameter = (Parameter) iter.next();
    this.add( parameter.getView() );
  }

  this.validate();
  }




}