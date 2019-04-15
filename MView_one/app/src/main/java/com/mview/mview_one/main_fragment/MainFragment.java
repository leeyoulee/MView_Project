package com.mview.mview_one.main_fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.mview.mview_one.main_fragment.main.model_adapter;
import com.mview.mview_one.main_fragment.main.model_data;
import com.mview.mview_one.main_fragment.main.main_notice_adapter;
import com.mview.mview_one.main_fragment.main.main_notice_data;
import com.mview.mview_one.main_fragment.main.shop_adapter;
import com.mview.mview_one.main_fragment.main.shop_data;
import com.mview.mview_one.notice_qna.noticeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.mview.mview_one.R.*;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    //noticeView
    private RecyclerView noticeView;
    private ArrayList noticeViewdatas;
    private static final String URL_NOTICE = "http://alsrud55399.cafe24.com/notice_main.php";

    //shopView
    private RecyclerView shopView;
    private ArrayList shopViewdatas;
    private static final String URL_SHOP = "http://alsrud55399.cafe24.com/shop_main.php";

    //modelView
    private RecyclerView modelView;
    private ArrayList modelViewdatas;
    private static final String URL_MODEL = "http://alsrud55399.cafe24.com/model_main.php";

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

        final TextView noticeBtn = (TextView) getView().findViewById(R.id.noticeBtn);

        noticeView = getView().findViewById(R.id.noticeView);
        noticeView.setHasFixedSize(true);
        LinearLayoutManager layoutManager_notice = new LinearLayoutManager(getContext());
        layoutManager_notice.setOrientation(LinearLayoutManager.VERTICAL);
        noticeView.setLayoutManager(layoutManager_notice);
        noticeViewdatas = new ArrayList<>();
        noticeloadData();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(getContext().getResources().getDrawable(drawable.divider_recyclerview));
        noticeView.addItemDecoration(dividerItemDecoration);

        shopView = getView().findViewById(R.id.shopView);
        shopView.setHasFixedSize(true);
        LinearLayoutManager layoutManager_shop = new LinearLayoutManager(getContext());
        layoutManager_shop.setOrientation(LinearLayoutManager.HORIZONTAL);
        shopView.setLayoutManager(layoutManager_shop);
        shopViewdatas = new ArrayList<>();
        shoploadData();

        modelView = getView().findViewById(R.id.modelView);
        modelView.setHasFixedSize(true);
        LinearLayoutManager layoutManager_model = new LinearLayoutManager(getContext());
        layoutManager_model.setOrientation(LinearLayoutManager.HORIZONTAL);
        modelView.setLayoutManager(layoutManager_model);
        modelViewdatas = new ArrayList<>();
        modelloadData();

        noticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new noticeFragment());
                fragmentTransaction.commit();
            }
        });
    }

    private void noticeloadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_NOTICE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject obj = array.getJSONObject(i);
                        main_notice_data notice_Data = new main_notice_data(obj.getString("noticeTitle"),obj.getString("noticeDate"),obj.getString("noticeNo"));
                        noticeViewdatas.add(notice_Data);
                    }
                    main_notice_adapter adapter = new main_notice_adapter(getContext(), noticeViewdatas);
                    noticeView.setAdapter(adapter);
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

    private void shoploadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SHOP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject obj = array.getJSONObject(i);
                        String img_result = "http://alsrud55399.cafe24.com/shop_image/" + obj.getString("SHOP_IMAGE.shopMainImg");
                        shop_data shop_Data = new shop_data(img_result,obj.getString("SHOP_INFO.shopName"),obj.getString("SHOP_INFO.shopLoc_1") ,obj.getString("SHOP_INFO.shopLoc_2"));
                        shopViewdatas.add(shop_Data);
                    }
                    shop_adapter adapter = new shop_adapter(getContext(), shopViewdatas);
                    shopView.setAdapter(adapter);
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
        return inflater.inflate(layout.fragment_main, container, false);
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
