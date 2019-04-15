package com.mview.mview_one;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mview.mview_one.menu_fragment.wanted_data;
import com.mview.mview_one.menu_fragment.wanted_search_adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link wanted_searchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link wanted_searchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class wanted_searchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //wantedView
    private RecyclerView wantedView;
    private ArrayList wantedViewdatas;
    private RecyclerView.LayoutManager layoutManager_model;

    public wanted_searchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment wanted_searchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static wanted_searchFragment newInstance(String param1, String param2) {
        wanted_searchFragment fragment = new wanted_searchFragment();
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

        wantedView = getView().findViewById(R.id.wantedView);
        layoutManager_model = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        wantedView.setLayoutManager(layoutManager_model);
        wantedViewdatas = new ArrayList<>();
        if (wanted_data.pay.equals("0")) {
            try {
                modelFreeloadData();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            modelPayloadData();
        }
    }

    private void modelFreeloadData() throws UnsupportedEncodingException {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://alsrud55399.cafe24.com/wanted_search_free.php?shopLoc_1=" + wanted_data.Loc_1+ "&shopLoc_2=" +  wanted_data.Loc_2 + "&modelField=" + wanted_data.category, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        String img_result = "http://alsrud55399.cafe24.com/shop_image/" + obj.getString("SHOP_IMAGE.shopMainImg");
                        wanted_search_adapter.wanted_search_data wanted_Data = new wanted_search_adapter.wanted_search_data(obj.getString("MODEL_INFO.modelNo"), img_result, obj.getString("SHOP_INFO.shopName"), obj.getString("SHOP_INFO.shopLoc_1"), obj.getString("SHOP_INFO.shopLoc_2"), obj.getString("MODEL_INFO.modelPrice"));
                        wantedViewdatas.add(wanted_Data);
                    }
                    wanted_search_adapter adapter = new wanted_search_adapter(getContext(), wantedViewdatas);
                    wantedView.setAdapter(adapter);
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

    private void modelPayloadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://alsrud55399.cafe24.com/wanted_search_pay.php?shopLoc_1=" + wanted_data.Loc_1 + "&shopLoc_2=" + wanted_data.Loc_2 + "&modelField=" + wanted_data.category, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        String img_result = "http://alsrud55399.cafe24.com/shop_image/" + obj.getString("SHOP_IMAGE.shopMainImg");
                        wanted_search_adapter.wanted_search_data wanted_Data = new wanted_search_adapter.wanted_search_data(obj.getString("MODEL_INFO.modelNo"), img_result, obj.getString("SHOP_INFO.shopName"), obj.getString("SHOP_INFO.shopLoc_1"), obj.getString("SHOP_INFO.shopLoc_2"), obj.getString("MODEL_INFO.modelPrice"));
                        wantedViewdatas.add(wanted_Data);
                    }
                    wanted_search_adapter adapter = new wanted_search_adapter(getContext(), wantedViewdatas);
                    wantedView.setAdapter(adapter);
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
        return inflater.inflate(R.layout.fragment_wanted_search, container, false);
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
