package edu.uic.cs478.anirbanroy.project3;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */

// Fragment to display the list of topics to user.
public class SubjectList extends ListFragment {

    private OnFragmentInteractionListener mListener;
    private ArrayList<String> choiceList;

    // TODO: Rename and change types of parameters
    public static SubjectList newInstance(String param1, String param2) {
        SubjectList fragment = new SubjectList();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SubjectList() {
    }

    @Override
   public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Button bt = new Button(getActivity().getApplicationContext()); // Button to display the status Toast of the App
        bt.setText("Status");
        bt.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT,
                ListView.LayoutParams.WRAP_CONTENT));
        ListView lview = getListView();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "Status is: "+((MasterActivity)getActivity()).getStatus(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        lview.addFooterView(bt);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

       setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        choiceList = new ArrayList<String>();  // The topics to choose from for image download
        choiceList.add("People");
        choiceList.add("Cars"); choiceList.add("Landscapes");
        // TODO: Change Adapter to display your content
        ArrayAdapter<String> arr = new ArrayAdapter<String>(getActivity(),R.layout.sublist_layout, choiceList);
        setListAdapter(arr);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;  // check if activity implements the frag communication interface
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) the choice that has been selected.
            mListener.onFragmentInteraction(choiceList.get(position));
        }

    }

    /** AROY27
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }
}

