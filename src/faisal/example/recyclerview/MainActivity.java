package faisal.example.recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Toast;
import faisal.example.recyclerview.MovieAdapter.ClickListener;

public class MainActivity extends AppCompatActivity implements ClickListener, View.OnClickListener {

	RecyclerView mRecyclerView;
	MovieAdapter mMovieAdapter;
	FloatingActionButton floatingActionButton;
	Toolbar mToolbar;
	LinearLayout mToolbarContainer;
	List<MyMapper> moviesList = new ArrayList<MyMapper>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton_ID);
		floatingActionButton.setOnClickListener(this);
		floatingActionButton.setRippleColor(ContextCompat.getColor(getApplicationContext(), R.color.Red));
		// floatingActionButton.setBackgroundTintList(getResources().getColorStateList(R.color.Blue800));

		mToolbar = (Toolbar) findViewById(R.id.toolBar_ID);
		setSupportActionBar(mToolbar);
		setTitle(getString(R.string.app_name));
		mToolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));

		mToolbarContainer = (LinearLayout) findViewById(R.id.toolBarContainer_ID);

		mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_ID);
		mMovieAdapter = new MovieAdapter(getApplicationContext(), moviesList);

		mRecyclerView.setHasFixedSize(true);
		mMovieAdapter.setListener(this);
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setLayoutManager(new LinearLayoutManager(
				getApplicationContext()));
		mRecyclerView.setAdapter(mMovieAdapter);
		// RecyclerView.LayoutManager mLayoutManager = new
		// LinearLayoutManager(getApplicationContext());
		// mRecyclerView.setLayoutManager(mLayoutManager);
		mRecyclerView.addOnScrollListener(new HidingToolBar(this) {
			@Override
			public void onMoved(int distance) {
				mToolbarContainer.setTranslationY(-distance);
			}

			@Override
			public void onShow() {
				mToolbarContainer.animate().translationY(0)
						.setInterpolator(new DecelerateInterpolator(2)).start();
			}

			@Override
			public void onHide() {
				mToolbarContainer.animate().translationY(-mToolbarHeight).setInterpolator(new AccelerateInterpolator(2)).start();
			}
		});
		prepareMovieData();
	}

	@Override
	public void itemClicked(View view, int position) {
		MyMapper movieMapperObject = moviesList.get(position);
		Toast.makeText(getApplicationContext(),	movieMapperObject.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.floatingActionButton_ID:
			Intent i = new Intent(this,SecondActivity.class);
			startActivity(i);
			Toast.makeText(getApplicationContext(), ".... hurray ...",	Toast.LENGTH_SHORT).show();
		}
	}

	private void prepareMovieData() {
		MyMapper movie = new MyMapper("Mad Max: Fury Road",
				"Action & Adventure", "2015");
		moviesList.add(movie);

		movie = new MyMapper("Inside Out", "Animation, Kids & Family", "2015");
		moviesList.add(movie);

		movie = new MyMapper("Star Wars: Episode VII - The Force Awakens",
				"Action", "2015");
		moviesList.add(movie);

		movie = new MyMapper("Shaun the Sheep", "Animation", "2015");
		moviesList.add(movie);

		movie = new MyMapper("The Martian", "Science Fiction & Fantasy", "2015");
		moviesList.add(movie);

		movie = new MyMapper("Mission: Impossible Rogue Nation", "Action",
				"2015");
		moviesList.add(movie);

		movie = new MyMapper("Up", "Animation", "2009");
		moviesList.add(movie);

		movie = new MyMapper("Star Trek", "Science Fiction", "2009");
		moviesList.add(movie);

		movie = new MyMapper("The LEGO Movie", "Animation", "2014");
		moviesList.add(movie);

		movie = new MyMapper("Iron Man", "Action & Adventure", "2008");
		moviesList.add(movie);

		movie = new MyMapper("Aliens", "Science Fiction", "1986");
		moviesList.add(movie);

		movie = new MyMapper("Chicken Run", "Animation", "2000");
		moviesList.add(movie);

		movie = new MyMapper("Back to the Future", "Science Fiction", "1985");
		moviesList.add(movie);

		movie = new MyMapper("Raiders of the Lost Ark", "Action & Adventure",
				"1981");
		moviesList.add(movie);

		movie = new MyMapper("Goldfinger", "Action & Adventure", "1965");
		moviesList.add(movie);

		movie = new MyMapper("Guardians of the Galaxy",
				"Science Fiction & Fantasy", "2014");
		moviesList.add(movie);

		movie = new MyMapper("Batman", "Action & Adventure", "2008");
		moviesList.add(movie);

		movie = new MyMapper("Batman Returns", "Action & Adventure", "2010");
		moviesList.add(movie);

		movie = new MyMapper("The Purge", "Action & Adventure", "2013");
		moviesList.add(movie);

		movie = new MyMapper("Zombie Land", "Horror", "2009");
		moviesList.add(movie);

		mMovieAdapter.notifyDataSetChanged();
	}

	public abstract class HidingToolBar extends RecyclerView.OnScrollListener {

		private static final float HIDE_THRESHOLD = 10;
		private static final float SHOW_THRESHOLD = 70;

		private int mToolbarOffset = 0;
		private boolean mControlsVisible = true;
		protected int mToolbarHeight;
		private int mTotalScrolledDistance;

		public HidingToolBar(Context context) {
			mToolbarHeight = Utils.getToolbarHeight(context);
		}

		@Override
		public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
			super.onScrollStateChanged(recyclerView, newState);

			if (newState == RecyclerView.SCROLL_STATE_IDLE) {
				if (mTotalScrolledDistance < mToolbarHeight) {
					setVisible();
				} else {
					if (mControlsVisible) {
						if (mToolbarOffset > HIDE_THRESHOLD) {
							setInvisible();
						} else {
							setVisible();
						}
					} else {
						if ((mToolbarHeight - mToolbarOffset) > SHOW_THRESHOLD) {
							setVisible();
						} else {
							setInvisible();
						}
					}
				}
			}
		}

		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);

			clipToolbarOffset();
			onMoved(mToolbarOffset);

			if ((mToolbarOffset < mToolbarHeight && dy > 0)
					|| (mToolbarOffset > 0 && dy < 0)) {
				mToolbarOffset += dy;
			}

			mTotalScrolledDistance += dy;
		}

		private void clipToolbarOffset() {
			if (mToolbarOffset > mToolbarHeight) {
				mToolbarOffset = mToolbarHeight;
			} else if (mToolbarOffset < 0) {
				mToolbarOffset = 0;
			}
		}

		private void setVisible() {
			if (mToolbarOffset > 0) {
				onShow();
				mToolbarOffset = 0;
			}
			mControlsVisible = true;
		}

		private void setInvisible() {
			if (mToolbarOffset < mToolbarHeight) {
				onHide();
				mToolbarOffset = mToolbarHeight;
			}
			mControlsVisible = false;
		}

		public abstract void onMoved(int distance);

		public abstract void onShow();

		public abstract void onHide();
	}

}
