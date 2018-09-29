package com.mycompany.myapp;

import android.app.*;
import android.os.*;
import android.webkit.*;
import com.github.ybq.android.spinkit.*;
import com.github.ybq.android.spinkit.style.*;
import android.graphics.*;
import android.view.*;
import android.support.v4.widget.*;

public class MainActivity extends Activity 
{
	private WebView web;
	private SpinKitView progrrss;
	private SwipeRefreshLayout mswipe;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		web=(WebView)findViewById(R.id.mainWebView);
		progrrss=(SpinKitView)findViewById(R.id.spin_kit);
		mswipe=(SwipeRefreshLayout)findViewById(R.id.SwipeRefreshLayout);
		WanderingCubes animation=new WanderingCubes();
		web();
		progrrss.setIndeterminateDrawable(animation);

		mswipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

				@Override
				public void onRefresh()
				{

					web.reload();
					// TODO: Implement this method
				}


			});



    }
	private void web(){

		web.getSettings().setJavaScriptEnabled(true);
		web.getSettings().setSupportZoom(true);
		web.loadUrl("https://sanedroid.blogspot.com");
		web.setWebViewClient(new WebViewClient(){

				@Override
				public void onPageStarted(WebView view,String url,Bitmap favicon){

					web.setVisibility(View.INVISIBLE);
					progrrss.setVisibility(View.VISIBLE);

					super.onPageStarted(view,url,favicon);
				}
				@Override
				public void onPageFinished(WebView view,String url){
					mswipe.setRefreshing(false);
					web.setVisibility(View.VISIBLE);
					progrrss.setVisibility(View.GONE);

					super.onPageFinished(view,url);
				}




			});




	}



}
