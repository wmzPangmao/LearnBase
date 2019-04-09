package com.pangmao.learnbase.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pangmao.learnbase.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TitleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TitleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TitleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TitleFragment() {
        // Required empty public constructor
    }

    /**
     * 建议使用setArguments来传递参数,不建议使用带参数的构造函数
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TitleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TitleFragment newInstance(String param1, String param2) {
        TitleFragment fragment = new TitleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * 当Fragment与Activity发生关联时调用。
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    /**
     * 在此获取外部传递的参数
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * 创建该Fragment的视图时调用
     * inflate()的第三个参数是false，因为在Fragment内部实现中，
     * 会把该布局添加到container中，如果设为true，那么就会重复做两次添加，则会抛如下异常
     * The specified child already has a parent.
     * You must call removeView() on the child's parent first.
     * @return      返回Fragment的UI布局
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_title, container, false);
    }

    public void clickBack(View view){
        onButtonPressed(null);
//        onDestroyView();
    }

    /**
     *与onAttach相对应，当Fragment与Activity关联被取消时调用
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
