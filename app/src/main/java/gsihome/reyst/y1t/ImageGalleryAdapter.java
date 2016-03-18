package gsihome.reyst.y1t;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Reyst on 12.03.2016.
 */
public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.ImageViewHolder> {

    private Context mContext;
    private ArrayList<String> model; //[Comment] Use abstraction instead of realization

    private OnItemClickListener clickListener; //[Comment] mOnclickListener

    private int mSideLength;

    public static class ImageViewHolder extends ViewHolder {
        public ImageView imageView; //[Comment] Wrong visibility modifier

        public ImageViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.ivImage); //[Comment] Please don't use upper case in id names
        }
    }

    interface OnItemClickListener {
        void onClick(View view);
    }

    public ImageGalleryAdapter(Context mContext, ArrayList<String> model, OnItemClickListener listener) {
        this.mContext = mContext;
        initModel(model);
        clickListener = listener;
    }

    public ImageGalleryAdapter(Context mContext, String[] model, OnItemClickListener listener) {
        this.mContext = mContext;
        initModel(Arrays.asList(model)); //[Comment] Why you convert array to array list? You can use array
        clickListener = listener;
    }

    private void initModel(Collection<String> data) {
        model = new ArrayList<>(data.size());
        model.addAll(data);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.image_card, parent, false);
        //[Comment] You can make space item decorator instead of layout params

        mSideLength = parent.getHeight();

        v.getLayoutParams().height = mSideLength;
        v.getLayoutParams().width = mSideLength;

        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, final int position) {
        Picasso.with(mContext)
                .load(model.get(position))
                .resize(mSideLength, mSideLength)
                .centerCrop()
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setTag(mContext.getResources().getString(R.string.str_image) + " #" + String.valueOf(position));
                clickListener.onClick(v);
            }
        });


    }

    @Override
    public int getItemCount() {
        return model.size();
    }
}
