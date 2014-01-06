package params;

/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

public class FloatParameter extends StringConvertableParameter
{

  float value = 0;
  StringConvertableParameterView view = null;

  public FloatParameter( StringBuffer sb ) throws Exception
  {
  fromSaveString( sb.toString() );
  view = new StringConvertableParameterView( this );
  }

  public FloatParameter( String name )
  {
  super(name);

  view = new StringConvertableParameterView( this );
  }

  public FloatParameter( String name, float value )
  {
  this( name );

  setFloat( value );
  }


  public String toString()
  {
  return Float.toString( value );
  }

  public void setString( String stringValue ) throws NumberFormatException
  {
  value = Float.parseFloat( stringValue );
  fireChangedListeners();
  }

  public ParameterView getView() { return view; }

  public float getFloat() { return value; };
  public void setFloat( float value) { this.value = value; view.load(); };


}