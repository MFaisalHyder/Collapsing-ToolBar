package faisal.example.recyclerview;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import anim.AnimationUtils;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ItemsViewHolder>{
	
	private List<MyMapper> moviesList;
	int mPreviousPosition = -1;
	Context context;
	static ClickListener clickListener;
	//LayoutInflater layoutInflater;
	
	public MovieAdapter(Context con, List<MyMapper> moviesList){
		//this.layoutInflater = LayoutInflater.from(con);
		this.moviesList=moviesList;
		this.context=con;
	}
	
	public static class ItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
		
		TextView txtViewTitle,txtViewGenre,txtViewYear;		
		public ItemsViewHolder(View itemView){
			super(itemView);
			txtViewTitle = (TextView) itemView.findViewById(R.id.txtView_Title_ID);
			txtViewGenre = (TextView) itemView.findViewById(R.id.txtView_Genre_ID);
			txtViewYear = (TextView) itemView.findViewById(R.id.txtView_Year_ID);
			itemView.setOnClickListener(this);
		}
		
		@Override
		public void onClick(View view) {
			if(clickListener!=null){
				clickListener.itemClicked(view, getAdapterPosition());
			}			
		}
	}
	
	public void setListener(ClickListener clicked){
		MovieAdapter.clickListener = clicked;
	}
	
	@Override
	public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
		//View view = layoutInflater.inflate(R.layout.movie_list_row, parent, false);
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row, parent, false);
		return new ItemsViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(ItemsViewHolder holder, int position) {
		MyMapper movieMapper = moviesList.get(position);
		holder.txtViewTitle.setText(movieMapper.getTitle());
		holder.txtViewGenre.setText(movieMapper.getGenre());
		holder.txtViewYear.setText(movieMapper.getYear());
				
		//Animation animation = AnimationUtils.loadAnimation(context, (position > mPreviousPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
	    //holder.itemView.startAnimation(animation);
	   
		if(position>mPreviousPosition){
			AnimationUtils.myAnimation(holder, true);			
		}else{
			AnimationUtils.myAnimation(holder, false);
		}
		
	    mPreviousPosition = position;
	}
	
	@Override
	public int getItemCount() {
		return moviesList.size();
	}
			
	public interface ClickListener{
		public void itemClicked(View view, int position);
	}
}
