package params;

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

public class StringConvertableParameterView extends ParameterView  //JPanel

{
  StringConvertableParameter scParameter = null;

  JLabel nameLabel = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JTextField valueTextField = new JTextField();
  BorderLayout borderLayout2 = new BorderLayout();

  private StringConvertableParameterView() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public StringConvertableParameterView(  StringConvertableParameter scParameter  )
  {
    super( scParameter );
    this.scParameter = scParameter;

    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    nameLabel.setText( scParameter.getName() );

    load();
  }



  private void jbInit() throws Exception {
    nameLabel.setPreferredSize(new Dimension(100, 17));
    this.setLayout(borderLayout1);
    valueTextField.setText("123.456");
    valueTextField.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        valueTextField_actionPerformed(e);
      }
    });
    jPanel1.setLayout(borderLayout2);
    this.add(nameLabel, BorderLayout.WEST);
    this.add(jPanel1,  BorderLayout.CENTER);
    jPanel1.add(valueTextField,  BorderLayout.CENTER);
  }

  public void load()
  {
  valueTextField.setText( scParameter.toString() );
  }

  public void store()
  {
  try {
    scParameter.setString( valueTextField.getText() );
  }
  catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "" + ex );
  }

  load();
  }

  void valueTextField_actionPerformed(ActionEvent e) {
    store();
  }



}