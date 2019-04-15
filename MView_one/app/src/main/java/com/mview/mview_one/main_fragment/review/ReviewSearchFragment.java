package com.mview.mview_one.main_fragment.review;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mview.mview_one.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReviewSearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReviewSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewSearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    //searchReview
    private RecyclerView searchReview;
    private ArrayList searchReviewdatas;

    public ReviewSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReviewSearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewSearchFragment newInstance(String param1, String param2) {
        ReviewSearchFragment fragment = new ReviewSearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(Bundle b) {
        super.onActivityCreated(b);

        searchReview = getView().findViewById(R.id.searchReview);
        searchReview.setHasFixedSize(true);
        LinearLayoutManager layoutManager_best = new LinearLayoutManager(getContext());
        layoutManager_best.setOrientation(LinearLayoutManager.VERTICAL);
        searchReview.setLayoutManager(layoutManager_best);
        searchReviewdatas = new ArrayList<>();
        DividerItemDecoration dividerItemDecoration_best = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration_best.setDrawable(getContext().getResources().getDrawable(R.drawable.divider_recyclerview));
        searchReview.addItemDecoration(dividerItemDecoration_best);

        if(review_data.getPay().toString().equals("0")){
            search_freeData();
        }else{
            search_payData();
        }

    }

    private void search_freeData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://alsrud55399.cafe24.com/review_search_free.php?category="+review_data.getCategory().toString()+"&Loc_1="+review_data.getLoc_1().toString()+"&Loc_2="+review_data.getLoc_2().toString()
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        String reviewNo = obj.getString("REVIEW.reviewNo");
                        String reviewGrade = obj.getString("REVIEW.reviewGrade");
                        String reviewContents = obj.getString("REVIEW.reviewContents");
                        String category = obj.getString("MODEL_INFO.modelField");
                        String pay = obj.getString("MODEL_INFO.modelPrice");
                        String modelImage = "http://alsrud55399.cafe24.com/shop_image/" + obj.getString("SHOP_IMAGE.shopMainImg");
                        String shopName = obj.getString("SHOP_INFO.shopName");
                        String reviewViews = obj.getString("REVIEW.reviewViews");

                        String dateResult = obj.getString("REVIEW.modelDate");
                        String replace = dateResult.replace("-","/");
                        String rsvDate = replace;

                        String pay_result;
                        if (pay.equals("0")) {
                            pay_result = "무료";
                        } else {
                            pay_result = pay + "원";
                        }

                        best_data best_Data = new best_data(reviewNo, rsvDate, reviewGrade, reviewContents, category, pay_result, modelImage, shopName, reviewViews);
                        searchReviewdatas.add(best_Data);
                    }
                    best_adapter adapter = new best_adapter(getContext(), searchReviewdatas);
                    searchReview.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void search_payData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://alsrud55399.cafe24.com/review_search_pay.php?category="+review_data.getCategory().toString()+"&Loc_1="+review_data.getLoc_1().toString()+"&Loc_2="+review_data.getLoc_2().toString()
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        String reviewNo = obj.getString("REVIEW.reviewNo");
                        String reviewGrade = obj.getString("REVIEW.reviewGrade");
                        String reviewContents = obj.getString("REVIEW.reviewContents");
                        String category = obj.getString("MODEL_INFO.modelField");
                        String pay = obj.getString("MODEL_INFO.modelPrice");
                        String modelImage = "http://alsrud55399.cafe24.com/shop_image/" + obj.getString("SHOP_IMAGE.shopMainImg");
                        String shopName = obj.getString("SHOP_INFO.shopName");
                        String reviewViews = obj.getString("REVIEW.reviewViews");

                        String dateResult = obj.getString("REVIEW.modelDate");
                        String replace = dateResult.replace("-","/");
                        String rsvDate = replace;

                        String pay_result;
                        if (pay.equals("0")) {
                            pay_result = "무료";
                        } else {
                            pay_result = pay + "원";
                        }

                        best_data best_Data = new best_data(reviewNo, rsvDate, reviewGrade, reviewContents, category, pay_result, modelImage, shopName, reviewViews);
                        searchReviewdatas.add(best_Data);
                    }
                    best_adapter adapter = new best_adapter(getContext(), searchReviewdatas);
                    searchReview.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_search, container, false);
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
        }
    }

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
