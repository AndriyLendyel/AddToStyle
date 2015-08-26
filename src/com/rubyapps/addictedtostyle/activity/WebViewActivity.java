package com.rubyapps.addictedtostyle.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.appodeal.ads.Appodeal;
import com.rubyapps.addictedtostyle.R;

public class WebViewActivity extends Activity {

	private WebView webView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view_activity);
		Appodeal.show(this, Appodeal.BANNER_BOTTOM);
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setDisplayUseLogoEnabled(false);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		webView = (WebView) findViewById(R.id.webView);
		webView.getSettings().setJavaScriptEnabled(true);
		// webView.getSettings().setLoadWithOverviewMode(true);
		// webView.getSettings().setUseWideViewPort(true);
		webView.setWebViewClient(new WebViewClient());
		webView.setWebChromeClient(new WebChromeClient());
		webView.getSettings()
				.setUserAgentString(
						"Mozilla/5.0 (Linux; Android 4.4; Nexus 4 Build/KRT16H) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36");
		webView.loadUrl(getIntent().getStringExtra("url"));

	}

	@Override
	public void onResume() {
		super.onResume();
		Appodeal.onResume(this, Appodeal.BANNER);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (webView != null) {
			webView.stopLoading();
			webView.onPause(); // pauses background threads, stops playing sound
			webView.pauseTimers(); // pauses the WebViewCore
		}
	}

	@Override
	public void onBackPressed() {
		if (webView.canGoBack()) {
			webView.goBack();
		} else {
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.toolbar_webview, menu);
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			// TODO back
			break;
		case R.id.menu_about:
			break;
		case R.id.menu_settings:
			break;
		case R.id.menu_load:
			webView.loadUrl(getIntent().getStringExtra("url"));
			break;
		default:
			break;
		}
		return true;
	}
}
