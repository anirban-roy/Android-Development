package edu.uic.cs478.anirbanroy.project3;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TableLayout;
import java.util.ArrayList;

import java.io.InputStream;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ImageDisplay.ImageClickedListener} interface
 * to handle interaction events.
 */
public class ImageDisplay extends Fragment{

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public TableLayout tableLayout;
    public ImageView img1, img2, img3, img4, img5, img6;
    GridView gridview;
    private ArrayList<Bitmap> mBitmaps;

    private ImageClickedListener mListener;



    public ImageDisplay(ArrayList<Bitmap> bitmaps) {
        mBitmaps = bitmaps;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container, savedInstanceState);
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_image_display, container, false);
        gridview = (GridView) v.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(getActivity().getApplicationContext(), mBitmaps));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
               ((MasterActivity)getActivity()).clickedImage((Bitmap)adapterView.getItemAtPosition(pos));
            }
        });

        return v;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface ImageClickedListener {
        // TODO: Update argument type and name
        public void clickedImage(Bitmap image);
    }

    public void setData(ArrayList<Bitmap> data){
        mBitmaps.clear();
        mBitmaps.addAll(data);
        gridview.setAdapter(new ImageAdapter(getActivity().getApplicationContext(), mBitmaps));
    }

}