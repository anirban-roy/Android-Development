// *** Developed by Anirban Roy, Dept of Comp Sc., UIC, Feb-2015 ***

package edu.uic.cs478.anirbanroy.project3;

import java.util.ArrayList;
import java.util.Set;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

// Adapter class to display images in the grid view
public class ImageAdapter extends BaseAdapter {
	Context mContext;
	private ArrayList<Bitmap> mThumbIds;
	
	public ImageAdapter(Context c, ArrayList<Bitmap> list) {
		// TODO Auto-generated constructor stub
		mContext = c;
		mThumbIds = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mThumbIds.size();
	}

	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return mThumbIds.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		// Create an imageview to display the thumbnail version of the resource in the gridview
		ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2,2,2,2);
        } else { // when imageview is recycled
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(mThumbIds.get(position));
        return imageView;
	}

}
