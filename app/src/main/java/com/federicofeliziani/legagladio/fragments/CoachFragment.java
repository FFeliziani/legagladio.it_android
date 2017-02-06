package com.federicofeliziani.legagladio.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.federicofeliziani.legagladio.R;
import com.federicofeliziani.legagladio.adapters.CoachArrayAdapter;
import com.federicofeliziani.legagladio.entities.Coach;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnCoachFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CoachFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoachFragment extends ListFragment {
    private OnCoachFragmentInteractionListener mListener;

    private ArrayList<Coach> coachList;

    public CoachFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CoachFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoachFragment newInstance() {
        CoachFragment fragment = new CoachFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coach, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCoachFragmentInteractionListener) {
            mListener = (OnCoachFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMainFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void updateCoaches(ArrayList<Coach> coachList) {
        this.coachList = coachList;
        CoachArrayAdapter adapter = new CoachArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, coachList);
        setListAdapter(adapter);
    }

    public void onViewCreated(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onViewCreated(container, savedInstanceState);
        if (coachList != null && coachList.size() > 0) {
            CoachArrayAdapter adapter = new CoachArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, coachList);
            setListAdapter(adapter);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCoachFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
