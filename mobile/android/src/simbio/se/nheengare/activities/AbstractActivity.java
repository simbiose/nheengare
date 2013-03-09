/**
 * Este arquivo é parte do programa Nheengaré



    Nheengaré é um software livre; você pode redistribui-lo e/ou 

    modifica-lo dentro dos termos da Licença Pública Geral GNU como 

    publicada pela Fundação do Software Livre (FSF); na versão 3 da 

    Licença.



    Este programa é distribuido na esperança que possa ser  util, 

    mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUAÇÂO a qualquer

    MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a

    Licença Pública Geral GNU para maiores detalhes.



    Você deve ter recebido uma cópia da Licença Pública Geral GNU

    junto com este programa, se não, escreva para a Fundação do Software

    Livre(FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package simbio.se.nheengare.activities;

import simbio.se.nheengare.R;
import simbio.se.nheengare.core.Analytics;
import simbio.se.nheengare.core.BlackBoard;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;

/**
 * @author Ademar Alves de Oliveira
 * @author ademar111190@gmail.com
 */
@SuppressLint("NewApi")
public class AbstractActivity extends Activity {

	private boolean dataHasLoaded = false;
	protected Analytics analytics;

	// customs
	protected BlackBoard getBlackBoard() {
		return BlackBoard.getBlackBoard(getApplicationContext());
	}

	protected void show(int[] viewsIds) {
		for (int i : viewsIds)
			findViewById(i).setVisibility(View.VISIBLE);
		findViewById(R.id.loadViews).setVisibility(View.INVISIBLE);
		clearTemp();
	}

	protected void loadOnUiThread() {
	}

	protected void loadOnThread() {
	}

	protected void trackerPage(Analytics analytics) {
	}

	protected void clearTemp() {
	}

	protected void share(String content) {
		Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
		intent.putExtra(Intent.EXTRA_TEXT, content);
		startActivity(Intent.createChooser(intent,
				getString(R.string.action_share_with)));
	}

	// find standart view
	public EditText findEditTextById(int id) {
		return (EditText) super.findViewById(id);
	}

	public TextView findTextViewById(int id) {
		return (TextView) super.findViewById(id);
	}

	public ListView findListViewById(int id) {
		return (ListView) super.findViewById(id);
	}

	public ImageView findImageViewById(int id) {
		return (ImageView) super.findViewById(id);
	}

	public LinearLayout findLinearLayoutById(int id) {
		return (LinearLayout) super.findViewById(id);
	}

	// Overrides

	@Override
	protected void onStart() {
		super.onStart();
		EasyTracker.getInstance().activityStart(this);
		analytics = Analytics.getAnalytics(getApplicationContext());
		if (!dataHasLoaded) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					loadOnThread();
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							loadOnUiThread();
							trackerPage(analytics);
						}
					});
				}
			}).start();
			dataHasLoaded = true;
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		EasyTracker.getInstance().activityStop(this);
	}

	// Others Overrides
	//
	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// }
	// @Override
	// public void onActionModeFinished(ActionMode mode) {
	// SimbiLog.log(this, mode);
	// if (android.os.Build.VERSION.SDK_INT >= 11)
	// super.onActionModeFinished(mode);
	// }
	//
	// @Override
	// public void onActionModeStarted(ActionMode mode) {
	// SimbiLog.log(this, mode);
	// if (android.os.Build.VERSION.SDK_INT >= 11)
	// super.onActionModeStarted(mode);
	// }
	//
	// @Override
	// public void onAttachedToWindow() {
	// SimbiLog.log(this);
	// if (android.os.Build.VERSION.SDK_INT >= 5)
	// super.onAttachedToWindow();
	// }
	//
	// @Override
	// public void onAttachFragment(Fragment fragment) {
	// SimbiLog.log(this, fragment);
	// if (android.os.Build.VERSION.SDK_INT >= 11)
	// super.onAttachFragment(fragment);
	// }
	//
	// @Override
	// public void onBackPressed() {
	// SimbiLog.log(this);
	// if (android.os.Build.VERSION.SDK_INT >= 5)
	// super.onBackPressed();
	// }
	//
	// @Override
	// public void onConfigurationChanged(Configuration newConfig) {
	// SimbiLog.log(this, newConfig);
	// super.onConfigurationChanged(newConfig);
	// }
	//
	// @Override
	// public void onContentChanged() {
	// SimbiLog.log(this);
	// super.onContentChanged();
	// }
	//
	// @Override
	// public boolean onContextItemSelected(MenuItem item) {
	// SimbiLog.log(this, item);
	// return super.onContextItemSelected(item);
	// }
	//
	// @Override
	// public void onContextMenuClosed(Menu menu) {
	// SimbiLog.log(this, menu);
	// super.onContextMenuClosed(menu);
	// }
	//
	// @Override
	// public void onCreateContextMenu(ContextMenu menu, View v,
	// ContextMenuInfo menuInfo) {
	// SimbiLog.log(this, menu, v, menuInfo);
	// super.onCreateContextMenu(menu, v, menuInfo);
	// }
	//
	// @Override
	// public CharSequence onCreateDescription() {
	// SimbiLog.log(this);
	// return super.onCreateDescription();
	// }
	//
	// @Override
	// public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
	// SimbiLog.log(this, builder);
	// if (android.os.Build.VERSION.SDK_INT >= 16)
	// super.onCreateNavigateUpTaskStack(builder);
	// }
	//
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// SimbiLog.log(this, menu);
	// return super.onCreateOptionsMenu(menu);
	// }
	//
	// @Override
	// public boolean onCreatePanelMenu(int featureId, Menu menu) {
	// SimbiLog.log(this, featureId, menu);
	// return super.onCreatePanelMenu(featureId, menu);
	// }
	//
	// @Override
	// public View onCreatePanelView(int featureId) {
	// SimbiLog.log(this, featureId);
	// return super.onCreatePanelView(featureId);
	// }
	//
	// @Override
	// public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
	// SimbiLog.log(this, outBitmap, canvas);
	// return super.onCreateThumbnail(outBitmap, canvas);
	// }
	//
	// @Override
	// public View onCreateView(String name, Context context, AttributeSet
	// attrs) {
	// SimbiLog.log(this, name, context, attrs);
	// return super.onCreateView(name, context, attrs);
	// }
	//
	// @Override
	// public View onCreateView(View parent, String name, Context context,
	// AttributeSet attrs) {
	// SimbiLog.log(this, parent, name, context, attrs);
	// if (android.os.Build.VERSION.SDK_INT >= 11)
	// return super.onCreateView(parent, name, context, attrs);
	// else
	// return null;
	// }
	//
	// @Override
	// public void onDetachedFromWindow() {
	// SimbiLog.log(this);
	// if (android.os.Build.VERSION.SDK_INT >= 5)
	// super.onDetachedFromWindow();
	// }
	//
	// @Override
	// public boolean onGenericMotionEvent(MotionEvent event) {
	// SimbiLog.log(this, event);
	// if (android.os.Build.VERSION.SDK_INT >= 12)
	// return super.onGenericMotionEvent(event);
	// else
	// return false;
	// }
	//
	// @Override
	// public boolean onKeyDown(int keyCode, KeyEvent event) {
	// SimbiLog.log(this, keyCode, event);
	// return super.onKeyDown(keyCode, event);
	// }
	//
	// @Override
	// public boolean onKeyLongPress(int keyCode, KeyEvent event) {
	// SimbiLog.log(this, keyCode, event);
	// if (android.os.Build.VERSION.SDK_INT >= 5)
	// return super.onKeyLongPress(keyCode, event);
	// else
	// return false;
	// }
	//
	// @Override
	// public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent
	// event) {
	// SimbiLog.log(this, keyCode, repeatCount, event);
	// return super.onKeyMultiple(keyCode, repeatCount, event);
	// }
	//
	// @Override
	// public boolean onKeyShortcut(int keyCode, KeyEvent event) {
	// SimbiLog.log(this, keyCode, event);
	// if (android.os.Build.VERSION.SDK_INT >= 11)
	// return super.onKeyShortcut(keyCode, event);
	// else
	// return false;
	// }
	//
	// @Override
	// public boolean onKeyUp(int keyCode, KeyEvent event) {
	// SimbiLog.log(this, keyCode, event);
	// return super.onKeyUp(keyCode, event);
	// }
	//
	// @Override
	// public void onLowMemory() {
	// SimbiLog.log(this);
	// super.onLowMemory();
	// }
	//
	// @Override
	// public boolean onMenuItemSelected(int featureId, MenuItem item) {
	// SimbiLog.log(this, featureId, item);
	// return super.onMenuItemSelected(featureId, item);
	// }
	//
	// @Override
	// public boolean onMenuOpened(int featureId, Menu menu) {
	// SimbiLog.log(this, featureId, menu);
	// return super.onMenuOpened(featureId, menu);
	// }
	//
	// @Override
	// public boolean onNavigateUp() {
	// SimbiLog.log(this);
	// if (android.os.Build.VERSION.SDK_INT >= 16)
	// return super.onNavigateUp();
	// else
	// return false;
	// }
	//
	// @Override
	// public boolean onNavigateUpFromChild(Activity child) {
	// SimbiLog.log(this, child);
	// if (android.os.Build.VERSION.SDK_INT >= 16)
	// return super.onNavigateUpFromChild(child);
	// else
	// return false;
	// }
	//
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// SimbiLog.log(this, item);
	// return super.onOptionsItemSelected(item);
	// }
	//
	// @Override
	// public void onOptionsMenuClosed(Menu menu) {
	// SimbiLog.log(this, menu);
	// super.onOptionsMenuClosed(menu);
	// }
	//
	// @Override
	// public void onPanelClosed(int featureId, Menu menu) {
	// SimbiLog.log(this, featureId, menu);
	// super.onPanelClosed(featureId, menu);
	// }
	//
	// @Override
	// public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder) {
	// SimbiLog.log(this, builder);
	// if (android.os.Build.VERSION.SDK_INT >= 16)
	// super.onPrepareNavigateUpTaskStack(builder);
	// }
	//
	// @Override
	// public boolean onPrepareOptionsMenu(Menu menu) {
	// SimbiLog.log(this, menu);
	// return super.onPrepareOptionsMenu(menu);
	// }
	//
	// @Override
	// public boolean onPreparePanel(int featureId, View view, Menu menu) {
	// SimbiLog.log(this, featureId, view, menu);
	// return super.onPreparePanel(featureId, view, menu);
	// }
	//
	// @Override
	// @Deprecated
	// public Object onRetainNonConfigurationInstance() {
	// SimbiLog.log(this);
	// return super.onRetainNonConfigurationInstance();
	// }
	//
	// @Override
	// public boolean onSearchRequested() {
	// SimbiLog.log(this);
	// return super.onSearchRequested();
	// }
	//
	// @Override
	// public boolean onTouchEvent(MotionEvent event) {
	// SimbiLog.log(this, event);
	// return super.onTouchEvent(event);
	// }
	//
	// @Override
	// public boolean onTrackballEvent(MotionEvent event) {
	// SimbiLog.log(this, event);
	// return super.onTrackballEvent(event);
	// }
	//
	// @Override
	// public void onTrimMemory(int level) {
	// SimbiLog.log(this, level);
	// if (android.os.Build.VERSION.SDK_INT >= 14)
	// super.onTrimMemory(level);
	// }
	//
	// @Override
	// public void onUserInteraction() {
	// SimbiLog.log(this);
	// super.onUserInteraction();
	// }
	//
	// @Override
	// public void onWindowAttributesChanged(LayoutParams params) {
	// SimbiLog.log(this, params);
	// super.onWindowAttributesChanged(params);
	// }
	//
	// @Override
	// public void onWindowFocusChanged(boolean hasFocus) {
	// SimbiLog.log(this, hasFocus);
	// super.onWindowFocusChanged(hasFocus);
	// }
	//
	// @Override
	// public ActionMode onWindowStartingActionMode(Callback callback) {
	// SimbiLog.log(this, callback);
	// if (android.os.Build.VERSION.SDK_INT >= 11)
	// return super.onWindowStartingActionMode(callback);
	// else
	// return null;
	// }
	//
	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// SimbiLog.log(this, requestCode, resultCode, data);
	// super.onActivityResult(requestCode, resultCode, data);
	// }
	//
	// @Override
	// protected void onApplyThemeResource(Theme theme, int resid, boolean
	// first) {
	// SimbiLog.log(this, theme, resid, first);
	// super.onApplyThemeResource(theme, resid, first);
	// }
	//
	// @Override
	// protected void onChildTitleChanged(Activity childActivity,
	// CharSequence title) {
	// SimbiLog.log(this, childActivity, title);
	// super.onChildTitleChanged(childActivity, title);
	// }
	//
	// @Override
	// @Deprecated
	// protected Dialog onCreateDialog(int id) {
	// SimbiLog.log(this, id);
	// return super.onCreateDialog(id);
	// }
	//
	// @Override
	// @Deprecated
	// protected Dialog onCreateDialog(int id, Bundle args) {
	// SimbiLog.log(this, id, args);
	// if (android.os.Build.VERSION.SDK_INT >= 8)
	// return super.onCreateDialog(id, args);
	// else
	// return null;
	// }
	//
	// @Override
	// protected void onDestroy() {
	// SimbiLog.log(this);
	// super.onDestroy();
	// }
	//
	// @Override
	// protected void onNewIntent(Intent intent) {
	// SimbiLog.log(this, intent);
	// super.onNewIntent(intent);
	// }
	//
	// @Override
	// protected void onPause() {
	// SimbiLog.log(this);
	// super.onPause();
	// }
	//
	// @Override
	// protected void onPostCreate(Bundle savedInstanceState) {
	// SimbiLog.log(this, savedInstanceState);
	// super.onPostCreate(savedInstanceState);
	// }
	//
	// @Override
	// protected void onPostResume() {
	// SimbiLog.log(this);
	// super.onPostResume();
	// }
	//
	// @Override
	// @Deprecated
	// protected void onPrepareDialog(int id, Dialog dialog) {
	// SimbiLog.log(this, id, dialog);
	// super.onPrepareDialog(id, dialog);
	// }
	//
	// @Override
	// @Deprecated
	// protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
	// SimbiLog.log(this, id, dialog, args);
	// if (android.os.Build.VERSION.SDK_INT >= 8)
	// super.onPrepareDialog(id, dialog, args);
	// }
	//
	// @Override
	// protected void onRestart() {
	// SimbiLog.log(this);
	// super.onRestart();
	// }
	//
	// @Override
	// protected void onRestoreInstanceState(Bundle savedInstanceState) {
	// SimbiLog.log(this, savedInstanceState);
	// super.onRestoreInstanceState(savedInstanceState);
	// }
	//
	// @Override
	// protected void onResume() {
	// SimbiLog.log(this);
	// super.onResume();
	// }
	//
	// @Override
	// protected void onSaveInstanceState(Bundle outState) {
	// SimbiLog.log(this, outState);
	// super.onSaveInstanceState(outState);
	// }
	//
	// @Override
	// protected void onTitleChanged(CharSequence title, int color) {
	// SimbiLog.log(this, title, color);
	// super.onTitleChanged(title, color);
	// }
	//
	// @Override
	// protected void onUserLeaveHint() {
	// SimbiLog.log(this);
	// super.onUserLeaveHint();
	// }
}
