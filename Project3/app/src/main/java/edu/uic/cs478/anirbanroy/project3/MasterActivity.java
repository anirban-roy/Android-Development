package edu.uic.cs478.anirbanroy.project3;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.app.Fragment;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

// Main Activity for the App
public class MasterActivity extends Activity implements SubjectList.OnFragmentInteractionListener, ImageDisplay.ImageClickedListener {

    private String status;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ImageDisplay imgDispFrag;
    public ArrayList<Bitmap> mBitmaps;
    private LoadImageFromNetwork imageLoader;
    private ImageDisplay imgFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        status = "Idle";
        setContentView(R.layout.activity_master);
    }

    @Override  // Call back from Sublist fragment
    public void onFragmentInteraction(String choice){

        if(mBitmaps!=null)
            mBitmaps.clear();
        mBitmaps = new ArrayList<Bitmap>();   // Collection of downloaded Bitmaps from network

        imageLoader = new LoadImageFromNetwork();
        switch(choice) { // check what was selected by user,  accordingly download images from URL using Background thread
            case "People": {
                imageLoader.execute("http://healthyceleb.com/wp-content/uploads/2014/11/Hrithik-Roshan-shirtless-body.jpg", "http://www.pinkvilla.com/files/zxf_22.jpg",
                        "http://guardianlv.com/wp-content/uploads/2014/05/Katrina-Kaif-Is-Dating-Ranbir-Kapoor.jpg","http://images6.fanpop.com/image/photos/33600000/Emma-Icons-x-emma-watson-33646984-788-788.jpg",
                        "http://images.indiatvnews.com/entertainmentbollywood/Aishwarya-Rai-f11593.jpg", "http://upload.wikimedia.org/wikipedia/sq/thumb/e/ef/Marilynmonroe.jpg/300px-Marilynmonroe.jpg");
                break;
            }
            case "Cars": {
                imageLoader.execute("http://images2.fanpop.com/image/photos/13800000/farrari-sports-cars-13821367-1280-960.jpg", "http://images1.ecarlist.com/sites/cats_exotic/images/v2/car.png",
                        "http://cdn.wonderfulengineering.com/wp-content/uploads/2014/07/black-classic-car.jpg", "http://cdn.wonderfulengineering.com/wp-content/uploads/2014/07/BMW-M1-Homage-Concept-Red-bmw-wallpapers-car-wallpapers-1920x1080.jpg",
                        "http://cdn.wonderfulengineering.com/wp-content/uploads/2014/07/lamborghini_gallardo_lp560_4-wide.jpg", "http://cdn.wonderfulengineering.com/wp-content/uploads/2014/07/Bugatti-Veyron-2013-Sports-Cars-HD-Wallpaper-2.jpg");
                break;
            }
            case "Landscapes": {
                imageLoader.execute("http://snapzlife.com/wp-content/uploads/2013/12/photographing-landscapes-3.jpg","http://www.fubiz.net/wp-content/uploads/2014/02/Nordic-Landscapes35.jpg",
                                    "http://illusion.scene360.com/wp-content/themes/sahara-10/submissions/2013/04/reflection-landscapes-02.jpg","http://www.irishlandscapes.ie/images/slideshow/12.jpg",
                                    "http://learnmyshot.com/wp-content/uploads/living_landscapes_ebook-65.jpg","http://media.digitalcameraworld.com/wp-content/uploads/sites/123/2012/05/Landscape_photography_tips.telefoto_5.jpg");
                break;
            }
        }

    }

    // Call back from ImageDisplay Fragment to display the clicked Image in entire fragment
    @Override
    public void clickedImage(Bitmap imageBit){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        WholeImageViewer wholeImageViewer = new WholeImageViewer(imageBit);
        fragmentTransaction.replace(R.id.layoFrag, wholeImageViewer);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        //If there is nothing on backstack then reset status back to Idle
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(fragmentManager.getBackStackEntryCount()==0)
                    setStatus("Idle");
            }
        });
    }
    // Call back from Fragment AsyncTask to update the progess status
    public void setStatus(String stat){
        status = stat;
    }

    // fetch the download status for toast by Status button of fragment 1
    public String getStatus(){
        return status;
    }

    // Background AsyncTask thread to download images from the network when URLS are provided
    public class LoadImageFromNetwork extends AsyncTask<String, String, ArrayList<Bitmap> > {

        protected void onPreExecute() {
            super.onPreExecute();
        }
        protected ArrayList<Bitmap> doInBackground(String... args) {
            ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>(args.length);
            for(int i=0;i<args.length;i++)
            {
                publishProgress("Downloading pictures"); // Set the status to Activity,
                                                        // this status is displayed when Status button is clicked on activity
                try {
                      // download the image bitmap from a URL and add that to Java Collection
                      bitmaps.add(BitmapFactory.decodeStream((InputStream) new URL(args[i]).getContent()));
                    }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            publishProgress("Showing downloaded thumbnails");
            return bitmaps;
        }
        protected void onProgressUpdate(String... progress) {
            setStatus(progress[0]);
        }
        protected void onPostExecute(ArrayList<Bitmap> images) {

            if(images != null){
                mBitmaps.addAll(images);
                if(imgFrag==null)
                    imgFrag = new ImageDisplay(mBitmaps);
                else
                    imgFrag.setData(images);

                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoFrag, imgFrag);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }else{
                Toast.makeText(MasterActivity.this, "Image Does Not exist or probable Network Error", Toast.LENGTH_SHORT).show();
            }
            //publishProgress("Idle");
        }
    }


}

























































// AROY27