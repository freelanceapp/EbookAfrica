package com.apporio.ebookafrica.epubsamir;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.apporio.ebookafrica.logger.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivityEPUBSamir extends Activity {
	RelativeLayout contentView;

	final private String TAG = "EPub";




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (!this.makeBooksDirectory()) {
        	debug("faild to make books directory");
        }

		DisplayMetrics metrics = getResources().getDisplayMetrics();
		float density = metrics.density;

		contentView = new RelativeLayout(this);
		RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.FILL_PARENT);
		contentView.setLayoutParams(rlp);
        setContentView(contentView);
		installSamples();


	}
	
	public void debug(String msg) {
		Log.d("EPub", msg);
	}
	
	public boolean makeBooksDirectory() {
		boolean res;		
		String filePath = new String(getFilesDir().getAbsolutePath() + "/books");
		File file = new File(filePath);
		if (!file.exists()) {
			res = file.mkdirs();
		}else {
			res = false;		
		}
		return res;	
	}
	
	public boolean fileExists(String fileName) {
		boolean res;
		
		String pureName = this.removeExtention(fileName);
		String targetDirectory = getFilesDir().getAbsolutePath() + "/books/"+pureName;	        	  
		String targetPath = targetDirectory+"/"+ fileName;
		
		File file = new File(targetPath);
		debug(file.getAbsolutePath());
		
		if (file.exists()) res = true;
		else  res = false;
		return res;		
	}
	
	public boolean  deleteFile(String fileName) {
		boolean res;

		String pureName = this.removeExtention(fileName);
		String targetDirectory = getFilesDir().getAbsolutePath() + "/books/"+pureName;	        	  
		String targetPath = targetDirectory+"/"+ fileName;
		
		File file = new File(targetPath);
		res = file.delete();
		return res;		
	}
	
	public String removeExtention(String filePath) {
	    // These first few lines the same as Justin's
	    File f = new File(filePath);

	    // if it's a directory, don't remove the extention
	    if (f.isDirectory()) return filePath;

	    String name = f.getName();

	    // Now we know it's a file - don't need to do any special hidden
	    // checking or contains() checking because of:
	    final int lastPeriodPos = name.lastIndexOf('.');
	    if (lastPeriodPos <= 0)
	    {
	        // No period after first character - return name as it was passed in
	        return filePath;
	    }
	    else
	    {
	        // Remove the last period and everything after it
	        File renamed = new File(f.getParent(), name.substring(0, lastPeriodPos));
	        return renamed.getPath();
	    }
	}

	
	public void copyToDevice(String fileName) {			      
		if (!this.fileExists(fileName)){
	          try
	          {
	        	  String pureName = this.removeExtention(fileName);
	        	  String targetDirectory = getFilesDir().getAbsolutePath() + "/books/"+pureName;	
	        	  File dir = new File(targetDirectory);
	        	  dir.mkdirs();
	        	  String targetPath = targetDirectory+"/"+ fileName;
				  Logger.d("File Name for opening file " + FileaName.FileNAME);
				  Logger.d("File path for opening file " + FileaName.FilePath);


				  InputStream localInputStream = new FileInputStream(FileaName.FilePath);
	        	  FileOutputStream localFileOutputStream = new FileOutputStream(targetPath);

	        	  byte[] arrayOfByte = new byte[1024];
	        	  int offset;
	        	  while ((offset = localInputStream.read(arrayOfByte))>0)
	        	  {
	        		  localFileOutputStream.write(arrayOfByte, 0, offset);	              
	        	  }
	        	  localFileOutputStream.close();
	        	  localInputStream.close();
				  Log.d(TAG, fileName + " copied to phone");


				  startBookViewActivity();


	          }
	          catch (IOException localIOException)
	          {
	              localIOException.printStackTrace();
	              Log.d(TAG, "failed to copy");
	              return;
	          }
	      }
	      else {
	          Log.d(TAG, fileName+" already exist");
	      }	         
	}
	
	private void installBook(String fileName) {
        if (this.fileExists(fileName)){
        	Log.d(TAG, fileName+ " already exist. try to delete old file.");
        	this.deleteFile(fileName);
        }
        this.copyToDevice(fileName);		
	}
	
	private void installSamples() {
		this.installBook(FileaName.FileNAME);
	}
	
	private void startBookViewActivity() {
		Intent intent = new Intent(MainActivityEPUBSamir.this,BookViewActivityEPUBSamir.class);
		startActivity(intent);
		finish();
	}

	
	private OnClickListener listener=new OnClickListener(){
		@Override
		public void onClick(View arg) {
	        if (arg.getId()==8080) {	        	
	        	startBookViewActivity();
	        }else if (arg.getId()==8082) {
	        	installSamples();	        	
	        }else if (arg.getId()==8083) {
	        	
	        }
			
		}
	};




	// If targetLocation does not exist, it will be created.
	public void copyDirectory(File sourceLocation , File targetLocation)
			throws IOException {

		if (sourceLocation.isDirectory()) {
			if (!targetLocation.exists() && !targetLocation.mkdirs()) {
				throw new IOException("Cannot create dir " + targetLocation.getAbsolutePath());
			}

			String[] children = sourceLocation.list();
			for (int i=0; i<children.length; i++) {
				copyDirectory(new File(sourceLocation, children[i]),
						new File(targetLocation, children[i]));
			}
		} else {

			// make sure the directory we plan to store the recording in exists
			File directory = targetLocation.getParentFile();
			if (directory != null && !directory.exists() && !directory.mkdirs()) {
				throw new IOException("Cannot create dir " + directory.getAbsolutePath());
			}

			InputStream in = new FileInputStream(sourceLocation);
			OutputStream out = new FileOutputStream(targetLocation);

			// Copy the bits from instream to outstream
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		}
	}


	
}

