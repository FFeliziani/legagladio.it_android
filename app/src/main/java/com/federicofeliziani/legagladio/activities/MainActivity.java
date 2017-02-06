package com.federicofeliziani.legagladio.activities;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.federicofeliziani.legagladio.R;
import com.federicofeliziani.legagladio.entities.Coach;
import com.federicofeliziani.legagladio.entities.Team;
import com.federicofeliziani.legagladio.fragments.CoachFragment;
import com.federicofeliziani.legagladio.fragments.MainFragment;
import com.federicofeliziani.legagladio.fragments.TeamFragment;
import com.federicofeliziani.legagladio.services.DownloadIntentService;
import com.federicofeliziani.legagladio.utils.Constants;
import com.federicofeliziani.legagladio.utils.UrlConstants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainFragment.OnMainFragmentInteractionListener, CoachFragment.OnCoachFragmentInteractionListener, TeamFragment.OnTeamFragmentInteractionListener {

    TeamFragment tf;
    CoachFragment cf;
    static boolean alreadyListening = false;
    BroadcastReceiver myBroadcast;
    IntentFilter networkIntentFilter;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkIntentFilter = new IntentFilter();
        networkIntentFilter.addAction(Constants.BROADCAST_ACTION);
        myBroadcast = new NetworkReceiver();

        if (!alreadyListening) {
            registerReceiver(myBroadcast, networkIntentFilter);
            alreadyListening = true;
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        cf = CoachFragment.newInstance();
        tf = TeamFragment.newInstance();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fm = getSupportFragmentManager();

        //doNetworking();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer, new MainFragment(), "MainFragment").addToBackStack(null).commit();
    }

    private void doNetworking(String url) {
        PendingIntent pendingResult = createPendingResult(0, new Intent(), 0);
        Intent intent = new Intent(getApplicationContext(), DownloadIntentService.class);
        intent.putExtra(Constants.URL_EXTRA, url);
        intent.putExtra(Constants.PENDING_RESULT_EXTRA, pendingResult);
        startService(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        FragmentManager fm = getSupportFragmentManager();

        int id = item.getItemId();

        if (id == R.id.nav_coaches) {
            // Handle the camera action
            FragmentTransaction ft = fm.beginTransaction();

            ft.replace(R.id.fragmentContainer, cf, "CoachFragment");
            //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            //fm.popBackStack();
            doNetworking(UrlConstants.COACH_LIST);
        } else if (id == R.id.nav_teams) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer, tf, "TeamFragment");
            ft.commit();
            doNetworking(UrlConstants.TEAM_LIST);
        } /*else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class NetworkReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast t = Toast.makeText(getApplicationContext(), "Networking received", Toast.LENGTH_LONG);
            t.show();

            List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
            String downloadUrl = intent.getExtras().get(Constants.DOWNLOAD_URL).toString();

            if (fragmentList != null) {
                for (Fragment f : fragmentList) {
                    switch (downloadUrl) {
                        case UrlConstants.COACH_LIST:
                            if(f instanceof CoachFragment){
                                ArrayList<Coach> coachList = intent.getExtras().getParcelableArrayList(Constants.URL_EXTRA);
                                CoachFragment cf = (CoachFragment) f;
                                cf.updateCoaches(coachList);
                            }
                            break;
                        case UrlConstants.TEAM_LIST:
                            if (f instanceof TeamFragment) {
                                ArrayList<Team> teamList = intent.getExtras().getParcelableArrayList(Constants.URL_EXTRA);
                                TeamFragment tf = (TeamFragment) f;
                                tf.updateTeams(teamList);
                            }
                            break;
                    }
                }
            }


        }
    }
}
