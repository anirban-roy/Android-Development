package edu.uic.cs478.anirbanroy.project3;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WholeImageViewer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WholeImageViewer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WholeImageViewer extends Fragment {

    ImageView imgview;
    Bitmap bmp;

    public WholeImageViewer(Bitmap bitmap) {
        bmp = bitmap;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View v = inflater.inflate(R.layout.whole_image_fragment,container,false);
        imgview = (ImageView) v.findViewById(R.id.wholeImage);
        imgview.setImageBitmap(bmp);
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /*public void showBitmap(Bitmap bmp){
        //ImageView imgview = new ImageView(getActivity().getApplicationContext());
        imgview.setImageBitmap(bmp);
        imgview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
  //               (ImageView) getView().getRootView().findViewById(R.id.wholeImage);
       LinearLayout l1 = (LinearLayout)getActivity().findViewById(R.id.imgadd);
        l1.addView(imgview);

    }*/

}
