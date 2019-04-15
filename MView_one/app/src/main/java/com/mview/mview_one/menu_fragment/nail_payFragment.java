package com.mview.mview_one.menu_fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
 * {@link nail_payFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link nail_payFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class nail_payFragment extends Fragment {
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
    private static final String URL_MODEL = "http://alsrud55399.cafe24.com/nail_pay.php";
    private RecyclerView.LayoutManager  layoutManager_model;
    String Loc_1, Loc_2;
    private ArrayList modelData;

    public nail_payFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment nail_payFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static nail_payFragment newInstance(String param1, String param2) {
        nail_payFragment fragment = new nail_payFragment();
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

        Spinner Loc_1Spinner = getView().findViewById(R.id.Loc_1Spinner);
        Spinner Loc_2Spinner = getView().findViewById(R.id.Loc_2Spinner);
        ImageView searchBtn = getView().findViewById(R.id.searchBtn);
        final LinearLayout main = getView().findViewById(R.id.main);
        final LinearLayout fragment = getView().findViewById(R.id.fragment);

        modelView = getView().findViewById(R.id.modelView);
        layoutManager_model = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        modelView.setLayoutManager(layoutManager_model);
        modelViewdatas = new ArrayList<>();
        modelData = new ArrayList<>();
        modelloadData();

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
                com.mview.mview_one.menu_fragment.model_data model_Data = new com.mview.mview_one.menu_fragment.model_data (Loc_1,Loc_2, "pay", "네일");
                modelData.add(model_Data);

                main.setVisibility(View.GONE);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new model_searchFragment());
                fragmentTransaction.commit();
            }
        });
    }

    private void modelloadData() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_MODEL,  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject obj = array.getJSONObject(i);
                        String img_result = "http://alsrud55399.cafe24.com/shop_image/" + obj.getString("SHOP_IMAGE.shopMainImg");
                        nail_pay_adapter.nail_pay_data nail_pay_Data = new nail_pay_adapter.nail_pay_data(obj.getString("MODEL_INFO.modelNo"),img_result, obj.getString("SHOP_INFO.shopName"), obj.getString("SHOP_INFO.shopLoc_1"), obj.getString("SHOP_INFO.shopLoc_2"), obj.getString("MODEL_INFO.modelPrice"));
                        modelViewdatas.add(nail_pay_Data);
                    }
                    nail_pay_adapter adapter = new nail_pay_adapter(getContext(), modelViewdatas);
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
        return inflater.inflate(R.layout.fragment_nail_pay, container, false);
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
