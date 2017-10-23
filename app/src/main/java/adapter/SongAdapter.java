package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dabin.sanzhou.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import app.GlideImageLoader;
import bean.NewBase;

/**
 * Created by Dabin on 2017/10/21.
 */

public class SongAdapter extends RecyclerView.Adapter {
    private int TYPE_ONE = 0;
    private int TYPE_TWO = 1;
    MyViewHolder myViewHolder;
    private Context context;
    private List<NewBase.SongListBean> song_list;
    private List mylist = new ArrayList();
    public SongAdapter(Context context, List<NewBase.SongListBean> song_list) {
        this.context = context;
        this.song_list = song_list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(getItemViewType(viewType) == TYPE_ONE){
            myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.songa_item, parent, false));
            return myViewHolder;
        }else{
            MyViewHoldertwo myViewHoldertwo = new MyViewHoldertwo(LayoutInflater.from(context).inflate(R.layout.songb_item, parent, false));
            return  myViewHoldertwo;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ONE) {
            MyViewHolder holderone = (MyViewHolder) holder;
            for (int i = 0; i < song_list.size(); i++) {
                mylist.add(song_list.get(i).getPic_big());
            }
            holderone.mybanner.setImageLoader(new GlideImageLoader());
            holderone.mybanner.setImages(mylist);
            holderone.mybanner.start();
        }else{
            MyViewHoldertwo holdertwo = (MyViewHoldertwo) holder;
            ImageLoader.getInstance().displayImage(song_list.get(position).getPic_small(),((MyViewHoldertwo) holder).imageView);
            holdertwo.songname.setText(song_list.get(position).getTitle());
            holdertwo.songauthor.setText(song_list.get(position).getAuthor());
        }

    }


    @Override
    public int getItemCount() {
        return song_list.size();
    }

    // ViewHolder 1
    class MyViewHolder extends RecyclerView.ViewHolder {
        Banner mybanner;
        public MyViewHolder(View itemView) {
            super(itemView);
            mybanner = itemView.findViewById(R.id.mybanner);
        }
    }
    // ViewHolder 2
    class MyViewHoldertwo extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView songname;
        TextView songauthor;

        public MyViewHoldertwo(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.songb_item_image);
            songname = itemView.findViewById(R.id.song_name);
            songauthor = itemView.findViewById(R.id.song_author);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == TYPE_ONE) {

            return TYPE_ONE;
        } else {

            return TYPE_TWO;
        }


    }
}
