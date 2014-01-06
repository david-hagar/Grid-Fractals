package gridfractals;

import javax.swing.*;
import java.util.*;
import params.*;
import java.lang.reflect.*;

/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

abstract public class Command implements ParameterListener  {

  protected boolean needUpdate = true;
  protected Command next = null;
  protected Command previous = null;
  protected Grid grid = null;
  protected CommandView commandView = null;
  protected ArrayList parameters = new ArrayList();
  protected String title;

  public Command( String title )
  {
  this.title = title;
  commandView = new CommandView(this);
  }


  public void setNeedUpdate()
  {
  needUpdate = true;
  grid = null;

  commandView.commandStatusView.setStatus( CommandStatusView.NEEDUPDATE_STATUS );

  if( next != null )
    next.setNeedUpdate();
  }

  protected void clearNeedUpdate()
  {
  needUpdate = false;
  commandView.commandStatusView.setStatus( CommandStatusView.UPDATED_STATUS );
  }

  public Grid getGrid()
  {
  if( needUpdate )
    run();
  return grid;
  }

  public CommandView getView() { return commandView; }
  public String getTitle() { return title; }
  public ArrayList getParameters() { return parameters; }

  abstract public void run();

  public void setNext(Command command) { next = command; }
  public void setPrevious(Command command) { previous = command; }

  protected void addParameter( Parameter p )
  {
  parameters.add( p );
  p.addListener( this );
  }

  public void valueChanged( Parameter parameter )
  {
  setNeedUpdate();

  Startup.toolsFrame.commandSet.updateGridsAndRepaint();
  }

  protected void rebuildParameterView()
  {
    commandView.parameterListView.rebuild( parameters );
  }


  public String toString()
  {
  StringBuffer sb = new StringBuffer();
  sb.append( this.getClass().getName() + "|" );

  Iterator iter = parameters.iterator();
  while (iter.hasNext())
  {
    Parameter item = (Parameter) iter.next();
    sb.append( item.toSaveString() );
    sb.append( "|" );
  }

  return sb.toString();
  }


  public void fromString( String string ) throws Exception
  {
    StringTokenizer st = new StringTokenizer( string, "|", false );

    int sizeBefore = parameters.size();
    parameters.clear();

    while( st.hasMoreTokens() )
    {
    String paramString = st.nextToken();
    int index = paramString.indexOf(",");
    String className = paramString.substring(0, index);

    Class c = Class.forName(className);
    Class [] params = { Class.forName( "java.lang.StringBuffer" ) };
    Constructor constructor = c.getConstructor( params );
    StringBuffer restOfString = new StringBuffer( paramString.substring(index+1) );
    Object paramValues[] = { restOfString };
    Parameter parameter = (Parameter) constructor.newInstance( paramValues );
    addParameter( parameter );
    }

    if( parameters.size() != sizeBefore )
      throw new Exception( "Number of parameters loaded does not match required number. req=" +
                             sizeBefore + "read=" + parameters.size() );

    rebuildParameterView();

  }

}




