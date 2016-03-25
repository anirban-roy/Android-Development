// *** Developed by Anirban Roy, Dept of Comp Sc., UIC, Feb-2015 ***

package edu.uic.cs478.anirbanroy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

// Adapter class to display images in the grid view
public class ImageAdapter extends BaseAdapter {
	Context mContext;
	private List<Integer> mThumbIds;
	
	public ImageAdapter(Context c, Set<Integer> list) {
		// TODO Auto-generated constructor stub
		mContext = c;
		mThumbIds = new ArrayList<Integer>();
		mThumbIds.addAll(list);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mThumbIds.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int pos) {
		// TODO Auto-generated method stub
		return mThumbIds.get(pos);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		// Create an imageview to display the thumbnail version of the resource in the gridview
		ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else { // when imageview is recycled
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds.get(position));
        return imageView;
	}

}
