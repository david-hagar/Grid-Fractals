
package gridfractals;

import java.io.*;
import java.awt.image.*;

import javax.imageio.ImageIO;

//import com.sun.media.jai.codec.*;

/**
 * <p>Title: Grid Fractals</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author David Hagar
 * @version 1.0
 */

public class PNGSaver {

  public PNGSaver() {
  }


  static public void saveToPNG( Grid grid, String  fileName ) throws Exception
  {


BufferedImage image = grid.getBufferedImage( );


//create a file outputstream for saving the file
//FileOutputStream f = new FileOutputStream(fileName);

//ParameterBlock pb = new ParameterBlock();
//pb.addSource(image);
//pb.add(DataBuffer.TYPE_BYTE);


//PNGEncodeParam encodeParam = PNGEncodeParam.getDefaultEncodeParam(image);
//ImageEncoder encoder = ImageCodec.createImageEncoder("PNG", f, encodeParam);

ImageIO.write(image, "png", new File( fileName ));

//encoder.encode(image);
//f.close();


}





}