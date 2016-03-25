// *** Developed by Anirban Roy, Dept of Comp Sc., UIC, Feb-2015 ***

package edu.uic.cs478.anirbanroy;


import java.util.HashMap;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.net.Uri;
import android.os.Bundle;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;

public class ImageInGridApp extends Activity
{
    /** Called when the activity is first created. */
	public static String EXTRA_RES_ID ="EXTRA_RES_ID";
	private int item;
	private String url;
	private HashMap<Integer,String> datamap;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Hashmap to store the models and the web address for the car
        datamap = new HashMap<Integer,String>();	
         datamap.put(R.drawable.bmw_vision, "http://www.bmw.com/com/en/insights/technology/efficient_dynamics/phase_2/bmwvision/introduction.html");
         datamap.put(R.drawable.camaro, "http://www.chevrolet.com/camaro-performance-cars.html");
         datamap.put(R.drawable.dodge_challenger, "http://www.dodge.com/en/challenger/");
         datamap.put(R.drawable.porche_spyder, "http://www.porsche.com/usa/models/918/918-spyder/");
         datamap.put(R.drawable.ferrari, "http://www.bornrich.com/ferrari-enzo1.html");
         datamap.put(R.drawable.jaguar, "http://www.caranddriver.com/jaguar/xf");
         datamap.put(R.drawable.knisegg_1,"http://www.koenigsegg.com/models/one1/");
         datamap.put(R.drawable.hummerh3,"http://www.hummer.com/vehicles/hummer_vehicles.html#/H3");
         datamap.put(R.drawable.mustang, "http://www.ford.com/cars/mustang/");
         datamap.put(R.drawable.veyron, "http://www.bugatti.com/en/veyron.html");
         datamap.put(R.drawable.civic, "http://automobiles.honda.com/civic-sedan/");
         datamap.put(R.drawable.prius, "http://www.toyota.com/prius/");
         datamap.put(R.drawable.audir8, "http://www.audiusa.com/models/audi-r8");
         datamap.put(R.drawable.mazdarx8, "http://www.edmunds.com/mazda/rx-8/");
         datamap.put(R.drawable.mclaren, "http://www.mbusa.com/mercedes/innovation/slr_mclaren");
         datamap.put(R.drawable.lamborghini_aventador, "http://www.lamborghini.com/en/models/aventador-lp-700-4/overview/#!slide/1");
         datamap.put(R.drawable.viperacrfd_12, "http://www.drivesrt.com/2015/viper/");
         datamap.put(R.drawable.cadillac,"http://www.cadillac.com/cts-sport-sedan/build-your-own.html");
        
        GridView gridview = (GridView) findViewById(R.id.gridview);
        registerForContextMenu(gridview);
        gridview.setAdapter(new ImageAdapter(this, datamap.keySet()));
        
        // set onclick listener for the girdview
        gridview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            // Create explicit intent to start the ViewImageActivity that displays the full screen image				
				Intent intent = new Intent(ImageInGridApp.this, ViewImageActivity.class);
				intent.putExtra(EXTRA_RES_ID, (int) id);
				startActivity(intent);
				}
	        });
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;

        // Retrieve the resource id for the grid view item longclicked upon. It will be used to start intent
        item = (int) info.id;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu, menu);
    }
    
    
    // Handle the menu onclick event
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Handle item selection
    	Intent intent;
    	
    	// Find the case for the option selected by user on the context menu
        switch (item.getItemId()) {
            case R.id.item1:   // case View full image
            	intent = new Intent(ImageInGridApp.this, ViewImageActivity.class);
				intent.putExtra(EXTRA_RES_ID, this.item);
				startActivity(intent);
                return true;
            case R.id.item2:	// case go to official website
            	intent = new Intent(Intent.ACTION_VIEW); // create browser intent
            	intent.setData(Uri.parse(datamap.get(new Integer(this.item)))); // set the url for the grid item selected from hashmap
            	intent.addCategory(Intent.CATEGORY_BROWSABLE);
            	startActivity(intent); // start implicit browser intent
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
