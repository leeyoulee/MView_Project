package com.mview.mview_one.menu_fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import com.mview.mview_one.main_fragment.main.model_adapter;
import com.mview.mview_one.main_fragment.main.model_data;
import com.mview.mview_one.wanted_searchFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link model_wantedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link model_wantedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class model_wantedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //modelView
    private RecyclerView modelView;
    private ArrayList modelViewdatas;
    private RecyclerView.LayoutManager  layoutManager_model;
    String Loc_1, Loc_2, category, pay;
    private ArrayList wanted_data;

    public model_wantedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment model_wantedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static model_wantedFragment newInstance(String param1, String param2) {
        model_wantedFragment fragment = new model_wantedFragment();
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

        Spinner categorySpinner = getView().findViewById(R.id.categorySpinner);
        Spinner Loc_1Spinner = getView().findViewById(R.id.Loc_1Spinner);
        Spinner Loc_2Spinner = getView().findViewById(R.id.Loc_2Spinner);
        ImageView searchBtn = getView().findViewById(R.id.searchBtn);
        final LinearLayout main = getView().findViewById(R.id.main);
        final LinearLayout fragment = getView().findViewById(R.id.fragment);

        modelView = getView().findViewById(R.id.wantedView);
        layoutManager_model = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        modelView.setLayoutManager(layoutManager_model);
        modelViewdatas = new ArrayList<>();
        wanted_data = new ArrayList<>();
        modelloadData();

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
                com.mview.mview_one.menu_fragment.wanted_data wanted_Data = new com.mview.mview_one.menu_fragment.wanted_data (Loc_1,Loc_2,category,pay);
                wanted_data.add(wanted_Data);

                main.setVisibility(View.GONE);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new wanted_searchFragment());
                fragmentTransaction.commit();
            }
        });
    }
    private void modelloadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://alsrud55399.cafe24.com/model_wanted.php",  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.v("2222222",response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject obj = array.getJSONObject(i);
                        String img_result = "http://alsrud55399.cafe24.com/shop_image/" + obj.getString("SHOP_IMAGE.shopMainImg");
                        model_data model_Data = new model_data(img_result, obj.getString("SHOP_INFO.shopName"), obj.getString("SHOP_INFO.shopLoc_1"), obj.getString("SHOP_INFO.shopLoc_2"), obj.getString("MODEL_INFO.modelPrice"));
                        modelViewdatas.add(model_Data);
                    }
                    model_adapter adapter = new model_adapter(getContext(), modelViewdatas);
                    modelView.setAdapter(adapter);
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
        return inflater.inflate(R.layout.fragment_model_wanted, container, false);
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
