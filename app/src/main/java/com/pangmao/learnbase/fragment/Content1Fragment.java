package com.pangmao.learnbase.fragment;

import android.annotation.SuppressLint;
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
import com.pangmao.learnbase.util.LoggUtil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Content1Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Content1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Content1Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Content1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Content1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Content1Fragment newInstance(String param1, String param2) {
        Content1Fragment fragment = new Content1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LoggUtil.log("Content1Fragment onAttach");
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoggUtil.log("Content1Fragment onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LoggUtil.log("Content1Fragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_content1, container, false);
        TextView tv = view.findViewById(R.id.tv_fragment_content1);
        tv.setText(mParam1 + "," + mParam2);
        Fragment2Activity activity = (Fragment2Activity) getActivity();
        if(activity != null) {
            activity.testFunc(tv.getText().toString());
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LoggUtil.log("Content1Fragment onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        LoggUtil.log("Content1Fragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LoggUtil.log("Content1Fragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LoggUtil.log("Content1Fragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LoggUtil.log("Content1Fragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LoggUtil.log("Content1Fragment onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LoggUtil.log("Content1Fragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        LoggUtil.log("Content1Fragment onDetach");
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
