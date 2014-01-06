package params;

import java.util.*;

/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

abstract public class Parameter {

  String name = "?";
  private ArrayList listeners = new ArrayList();

  public Parameter( String name )
  {
  this.name = name;
  }

  public Parameter( )
  {
  this.name = "?";
  }

  public String getName() { return name; }


  abstract public ParameterView getView();


  public int getInt() { throw new NullPointerException("cannot get int on " +
                            this.getClass().getName()); };
  public float getFloat() { throw new NullPointerException("cannot get float on " +
                            this.getClass().getName()); };

  public void setInt( int value ) { throw new NullPointerException("cannot set int on " +
                            this.getClass().getName()); };
  public void setFloat( float value ) { throw new NullPointerException("cannot set float on " +
                            this.getClass().getName()); };

  public void addListener( ParameterListener listener )
  {
  listeners.add( listener );
  }

  public void fireChangedListeners( )
  {
  Iterator iter = listeners.iterator();
  while (iter.hasNext())
  {
    ((ParameterListener)iter.next()).valueChanged( this );
  }

  }


  public String toSaveString()
  {
  if( ! (this instanceof StringConvertableParameter) )
    throw new NullPointerException( "Error parameter is not a StringConvertableParameter:" + name );

  String value = ( ( StringConvertableParameter )this ).toString();

  return this.getClass().getName() + "," + name + "," + value;
  }


  public void fromSaveString( String string ) throws Exception
  {
    if( ! (this instanceof StringConvertableParameter) )
      throw new Exception( "Error parameter is not a StringConvertableParameter:" + name );

    int index=string.indexOf(',');
    if( index == -1 )
    {
      throw new NullPointerException("Couldn't find comma in parameter string: " + string);
    }

    name = string.substring(0, index);
    String value = string.substring(index+1);

    ( ( StringConvertableParameter )this ).setString(value);


  }




}