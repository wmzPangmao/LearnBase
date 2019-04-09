package com.pangmao.learnbase.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pangmao.learnbase.R;
import com.pangmao.learnbase.util.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Content2Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Content2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Content2Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Content2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Content2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Content2Fragment newInstance(String param1, String param2) {
        Content2Fragment fragment = new Content2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.log("Content2Fragment onAttach");
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.log("Content2Fragment onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LogUtil.log("Content2Fragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_content2, container, false);
        TextView tv = view.findViewById(R.id.tv_fragment_content2);
        tv.setText(mParam1 + "," + mParam2);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.log("Content2Fragment onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.log("Content2Fragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.log("Content2Fragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.log("Content2Fragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.log("Content2Fragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.log("Content2Fragment onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.log("Content2Fragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        LogUtil.log("Content2Fragment onDetach");
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
