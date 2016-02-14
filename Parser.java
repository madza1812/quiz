import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * This class is thread safe.
 */
public class Parser {
  private File file;
  public Parser(File f){
	  file = f;
  }
  
  public synchronized void setFile(File f) {
    file = f;
  }
  public synchronized File getFile() {
    return file;
  }
  public String getContent() throws IOException {
	  FileInputStream i = null;
	  BufferedInputStream buffer = null;
	  StringBuilder output = new StringBuilder("");
	  try {
		  i = new FileInputStream(file);
		  buffer = new BufferedInputStream(i);
		  int data;
		  while ((data = buffer.read()) > 0) {
			  output.append((char) data);
		  }
	  } finally {
		  if (buffer!=null) {
			  buffer.close();
		  }
		  if (i != null)
			  i.close();
	  }
	  return output.toString();
  }
  public String getContentWithoutUnicode() throws IOException {
	  FileInputStream i = null;
	  BufferedInputStream buffer = null;
	  StringBuilder output = new StringBuilder("");
	  try {
	    i = new FileInputStream(file);
	    buffer = new BufferedInputStream(i);
	    int data;
	    while ((data = buffer.read()) > 0) {
	      if (data < 0x80) {
	        output.append((char) data);
	      }
	    }
	  }	finally {
		  if (buffer!=null) {
			  buffer.close();
		  }
		  if (i != null)
			  i.close();
	   }
	  
	  return output.toString();
  }
  public synchronized void saveContent(String content) throws IOException {
	  FileOutputStream o = null;
	  BufferedOutputStream buffer = null;
	  try{
	     o = new FileOutputStream(file);
	     buffer= new BufferedOutputStream(o);
	     for (int i = 0; i < content.length(); i += 1) {
	    	 buffer.write(content.charAt(i));
	     }
	     buffer.flush();
	  } finally {
		  if (buffer != null)
			  buffer.close();
		  if (o != null)
			  o.close();
	  }  
  }
}

