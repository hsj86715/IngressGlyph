package com.hsj86715.ingress.glyph.pages;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ScrollView;

import com.hsj86715.ingress.glyph.R;

import java.util.Arrays;

import cn.com.farmcode.utility.tools.Logger;

public class CheatFragment extends Fragment {
    private ScrollView mCheatScroll;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_cheat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCheatScroll = view.findViewById(R.id.cheat_scroll);
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.getSupportActionBar().setSubtitle(R.string.nav_menu_cheat);
        }
//        final ViewStub stub1 = getView().findViewById(R.id.cheat_2);
//        if (stub1 != null) {
//            getView().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    stub1.inflate();
//
//                }
//            }, 250);
//        }
//        final ViewStub stub2 = getView().findViewById(R.id.cheat_3);
//        if (stub2 != null) {
//            getView().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    stub2.inflate();
//                }
//            }, 500);
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (getView() == null) {
            return super.onOptionsItemSelected(item);
        }
        int id = item.getItemId();
        View targetView = null;
        switch (id) {
            case R.id.cheat_player:
                targetView = getView().findViewById(R.id.cheat_player_header);
                break;
            case R.id.cheat_access_points:
                targetView = getView().findViewById(R.id.cheat_access_points_header);
                break;
            case R.id.cheat_force_amp:
                targetView = getView().findViewById(R.id.cheat_force_amp_header);
                break;
            case R.id.cheat_heat_sink:
                targetView = getView().findViewById(R.id.cheat_heat_sink_header);
                break;
            case R.id.cheat_link_amp:
                targetView = getView().findViewById(R.id.cheat_link_amp_header);
                break;
            case R.id.cheat_link_mitigation:
                targetView = getView().findViewById(R.id.cheat_links_mitigation_header);
                break;
            case R.id.cheat_multi_hack:
                targetView = getView().findViewById(R.id.cheat_multi_hack_header);
                break;
            case R.id.cheat_portal:
                targetView = getView().findViewById(R.id.cheat_portal_header);
                break;
            case R.id.cheat_resonator:
                targetView = getView().findViewById(R.id.cheat_resonator_header);
                break;
            case R.id.cheat_shield:
                targetView = getView().findViewById(R.id.cheat_shield_header);
                break;
            case R.id.cheat_turret:
                targetView = getView().findViewById(R.id.cheat_turret_header);
                break;
            case R.id.cheat_ultra_strike:
                targetView = getView().findViewById(R.id.cheat_ultra_strike_header);
                break;
            case R.id.cheat_xmp_burster:
                targetView = getView().findViewById(R.id.cheat_xmp_burster_header);
                break;
            default:
                break;
        }
        if (targetView != null) {
//            int[] location = new int[2];
//            targetView.getLocationOnScreen(location);
//            Logger.i("onOptionsItemSelected " + targetView + ": location is " + Arrays.toString(location));
            mCheatScroll.smoothScrollTo(0, (int) targetView.getY());

        }
        return super.onOptionsItemSelected(item);
    }
}
