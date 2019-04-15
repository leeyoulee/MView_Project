package com.mview.mview_one.main_fragment.review;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
 * {@link ReviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class  ReviewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ReviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewFragment newInstance(String param1, String param2) {
        ReviewFragment fragment = new ReviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    //bestReview
    private RecyclerView bestReview;
    private ArrayList bestReviewdatas;
    private static final String URL_BEST = "http://alsrud55399.cafe24.com/review_best.php";

    //latelyReview
    private RecyclerView latelyReview;
    private ArrayList latelyReviewdatas;
    private static final String URL_LATELY = "http://alsrud55399.cafe24.com/review_lately.php";

    Spinner categorySpinner;
    Spinner Loc_1Spinner;
    Spinner Loc_2Spinner;
    ImageView searchBtn;

    String Loc_1, Loc_2, category, pay;
    private ArrayList reviewData;

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

        categorySpinner = getView().findViewById(R.id.categorySpinner);
        Loc_1Spinner = getView().findViewById(R.id.Loc_1Spinner);
        Loc_2Spinner = getView().findViewById(R.id.Loc_2Spinner);
        searchBtn = getView().findViewById(R.id.searchBtn);
        final LinearLayout main = getView().findViewById(R.id.main);
        final LinearLayout ReviewFragment = getView().findViewById(R.id.ReviewFragment);

        reviewData = new ArrayList<>();

        bestReview = getView().findViewById(R.id.bestReview);
        bestReview.setHasFixedSize(true);
        LinearLayoutManager layoutManager_best = new LinearLayoutManager(getContext());
        layoutManager_best.setOrientation(LinearLayoutManager.VERTICAL);
        bestReview.setLayoutManager(layoutManager_best);
        bestReviewdatas = new ArrayList<>();
        bestloadData();
        DividerItemDecoration dividerItemDecoration_best = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration_best.setDrawable(getContext().getResources().getDrawable(R.drawable.divider_recyclerview));
        bestReview.addItemDecoration(dividerItemDecoration_best);

        latelyReview = getView().findViewById(R.id.latelyReview);
        latelyReview.setHasFixedSize(true);
        LinearLayoutManager layoutManager_lately = new LinearLayoutManager(getContext());
        layoutManager_lately.setOrientation(LinearLayoutManager.VERTICAL);
        latelyReview.setLayoutManager(layoutManager_lately);
        latelyReviewdatas = new ArrayList<>();
        latelyloadData();
        DividerItemDecoration dividerItemDecoration_lately = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration_lately.setDrawable(getContext().getResources().getDrawable(R.drawable.divider_recyclerview));
        latelyReview.addItemDecoration(dividerItemDecoration_lately);
        latelyReview.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() { // 세로스크롤 막기
                return false;
            }
        });

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String result = (String) adapterView.getItemAtPosition(i);
                if(result.equals("분야")){

                }else{
                    if(result.substring(1,3).toString().equals("무료")){
                        pay = "0";
                        category = result.substring(4).toString();
                    }else{
                        pay = "pay";
                        category = result.substring(4).toString();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        Loc_1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Loc_1 = (String) adapterView.getItemAtPosition(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        Loc_2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Loc_2 = (String) adapterView.getItemAtPosition(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                review_data review_Data = new review_data (category,Loc_1,Loc_2,pay);
                reviewData.add(review_Data);

                main.setVisibility(View.GONE);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.ReviewFragment, new ReviewSearchFragment());
                fragmentTransaction.commit();
            }
        });
    }

    private void bestloadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_BEST, new Response.Listener<String>() {
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
                        bestReviewdatas.add(best_Data);
                    }
                    best_adapter adapter = new best_adapter(getContext(), bestReviewdatas);
                    bestReview.setAdapter(adapter);
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

    private void latelyloadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_LATELY, new Response.Listener<String>() {
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

                            lately_data lately_Data = new lately_data(reviewNo, rsvDate, reviewGrade, reviewContents, category, pay_result, modelImage, shopName, reviewViews);
                        latelyReviewdatas.add(lately_Data);
                    }
                    lately_adapter adapter = new lately_adapter(getContext(), latelyReviewdatas);
                    latelyReview.setAdapter(adapter);
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
        return inflater.inflate(R.layout.fragment_review, container, false);
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
