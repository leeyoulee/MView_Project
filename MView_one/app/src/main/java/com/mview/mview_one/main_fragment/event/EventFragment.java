package com.mview.mview_one.main_fragment.event;

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
import android.widget.TextView;
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
 * {@link EventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //bestReview
    private RecyclerView rcdReview;
    private ArrayList rcdReviewdatas;
    private static final String URL_RCD = "http://alsrud55399.cafe24.com/event_rcd.php";

    //latelyReview
    private RecyclerView proReview;
    private ArrayList proReviewdatas;
    private static final String URL_PRO = "http://alsrud55399.cafe24.com/event_pro.php";

    private OnFragmentInteractionListener mListener;

    public EventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventFragment newInstance(String param1, String param2) {
        EventFragment fragment = new EventFragment();
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

        rcdReview = getView().findViewById(R.id.rcdReview);
        rcdReview.setHasFixedSize(true);
        LinearLayoutManager layoutManager_best = new LinearLayoutManager(getContext());
        layoutManager_best.setOrientation(LinearLayoutManager.VERTICAL);
        rcdReview.setLayoutManager(layoutManager_best);
        rcdReviewdatas = new ArrayList<>();
        bestloadData();
        DividerItemDecoration dividerItemDecoration_best = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration_best.setDrawable(getContext().getResources().getDrawable(R.drawable.divider_recyclerview));
        rcdReview.addItemDecoration(dividerItemDecoration_best);

        proReview = getView().findViewById(R.id.proReview);
        proReview.setHasFixedSize(true);
        LinearLayoutManager layoutManager_lately = new LinearLayoutManager(getContext());
        layoutManager_lately.setOrientation(LinearLayoutManager.VERTICAL);
        proReview.setLayoutManager(layoutManager_lately);
        proReviewdatas = new ArrayList<>();
        latelyloadData();
        DividerItemDecoration dividerItemDecoration_lately = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration_lately.setDrawable(getContext().getResources().getDrawable(R.drawable.divider_recyclerview));
        proReview.addItemDecoration(dividerItemDecoration_lately);

        proReview.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() { // 세로스크롤 막기
                return false;
            }
        });
    }

    private void bestloadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_RCD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        String shopNo = obj.getString("EVENT.shopNo");
                        String shopLoc_1 = obj.getString("SHOP_INFO.shopLoc_1");
                        String shopLoc_2 = obj.getString("SHOP_INFO.shopLoc_2");
                        String shopName = obj.getString("SHOP_INFO.shopName");
                        String eventTitle = obj.getString("EVENT.eventTitle");
                        String shopImage = "http://alsrud55399.cafe24.com/shop_image/" + obj.getString("SHOP_IMAGE.shopMainImg");
                        String modelPrice = obj.getString("EVENT.modelPrice")+"원";
                        event_data event_Data = new event_data( shopNo, shopImage, shopName, shopLoc_1, shopLoc_2, eventTitle, modelPrice);
                        rcdReviewdatas.add(event_Data);
                    }
                    event_adapter adapter = new event_adapter(getContext(), rcdReviewdatas);
                    rcdReview.setAdapter(adapter);
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
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        String shopNo = obj.getString("EVENT.shopNo");
                        String shopLoc_1 = obj.getString("SHOP_INFO.shopLoc_1");
                        String shopLoc_2 = obj.getString("SHOP_INFO.shopLoc_2");
                        String shopName = obj.getString("SHOP_INFO.shopName");
                        String eventTitle = obj.getString("EVENT.eventTitle");
                        String shopImage = "http://alsrud55399.cafe24.com/shop_image/" + obj.getString("SHOP_IMAGE.shopMainImg");
                        String modelPrice = obj.getString("EVENT.modelPrice")+"원";
                        event_data event_Data = new event_data( shopNo, shopImage, shopName, shopLoc_1, shopLoc_2, eventTitle, modelPrice);
                        proReviewdatas.add(event_Data);
                    }
                    event_adapter adapter = new event_adapter(getContext(), proReviewdatas);
                    proReview.setAdapter(adapter);
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
        return inflater.inflate(R.layout.fragment_event, container, false);
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
