package params;

/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

public class IntParameter extends StringConvertableParameter
{
  private int value = 0;
  StringConvertableParameterView view = null;

  public IntParameter( StringBuffer sb ) throws Exception
  {
  fromSaveString( sb.toString() );
  view = new StringConvertableParameterView( this );
  }

  public IntParameter( String name )
  {
  super(name);

  view = new StringConvertableParameterView( this );
  }

  public IntParameter( String name, int value )
  {
  this( name );

  setInt( value );
  }

  public String toString()
  {
  return Integer.toString( value );
  }

  public void setString( String stringValue ) throws NumberFormatException
  {
  value = Integer.parseInt( stringValue );
  fireChangedListeners();
  }

  public ParameterView getView() { return view; }


  public int getInt() { return value; };
  public void setInt( int value) { this.value = value; view.load(); };

}