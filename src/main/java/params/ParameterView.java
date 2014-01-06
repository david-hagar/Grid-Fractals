package params;

import javax.swing.JPanel;


/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

public class ParameterView extends JPanel {

  Parameter parameter = null;

  public ParameterView( Parameter parameter )
  {
  this.parameter = parameter;
  }

  public ParameterView( )
  {
  }

  public void load() {}

  public void store() {}


}



