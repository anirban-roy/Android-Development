// *** Developed by Anirban Roy, Dept of Comp Sc., UIC, Feb-2015 ***

package edu.uic.cs478.anirbanroy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

// Activity to display the full screen version of the image when clicked 
public class ViewImageActivity extends Activity {

	public void	onCreate(Bundle	savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		int res = intent.getIntExtra(ImageInGridApp.EXTRA_RES_ID, 0); // retrieve the resource id of the selected resource
		ImageView imageView = new ImageView(this);
		setContentView(imageView);
		imageView.setImageResource(res);	
	}
}
