package com.leleleu.arduinocontroller;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private BluetoothAdapter btAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



    }



    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements View.OnTouchListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";



        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            setListeners(rootView);
            return rootView;
        }

        private void setListeners(View rootView) {

            if(rootView instanceof ViewGroup) {
                ViewGroup vg = ((ViewGroup)rootView);
                for (int i = 0; i < vg.getChildCount(); i++) {
                    setListeners(vg.getChildAt(i));
                }
            }
            else
            {
                rootView.setOnTouchListener(this);
            }

        }




        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));

        }


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            char sendInfo = 0;
            boolean coisado = true;
            boolean bt=false;

            if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction()==MotionEvent.ACTION_UP) {
                switch (v.getId()) {
                    case R.id.btn_up:
                        sendInfo = getKey('U', event);

                        break;
                    case R.id.btn_down:
                        sendInfo = getKey('D', event);
                        break;

                    case R.id.btn_left:
                        sendInfo = getKey('L', event);

                        break;

                    case R.id.btn_right:
                        sendInfo = getKey('R', event);

                        break;

                    case R.id.btn_action1:
                        sendInfo = getKey('F', event);

                        break;
                    case R.id.btn_action2:
                        sendInfo = getKey('G', event);

                        break;
                    case R.id.btn_action3:
                        sendInfo = getKey('H', event);

                        break;
                    case R.id.btn_action4:
                        sendInfo = getKey('I', event);

                        break;
                    case R.id.btn_action5:
                        sendInfo = getKey('J', event);

                        break;
                    case R.id.btn_action6:
                        sendInfo = getKey('K', event);

                        break;
                    case R.id.toggleBluetooth:
                        if (event.getAction() == MotionEvent.ACTION_DOWN)
                            ((ToggleButton) v).setChecked(!((ToggleButton) v).isChecked());
                        break;

                    default:
                        coisado = false;

                }
            }else {
                coisado = false;
            }

            if(coisado)
            {
                Log.i("DEGUG","Touch");
                Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(50);
            }


            return coisado;
        }

        private char getKey(char c, MotionEvent event) {
            char ret = c;
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN: //return minuscula
                    ret+=32;
                    break;
                case MotionEvent.ACTION_UP: //return maiúscula
                    ret=c;
                    break;
                default:
                    ret=0;
            }
            return ret;
        }
    }

}
