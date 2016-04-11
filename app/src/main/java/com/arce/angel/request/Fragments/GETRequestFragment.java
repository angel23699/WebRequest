package com.arce.angel.request.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.arce.angel.request.Adapters.SongAdapter;
import com.arce.angel.request.Listener.ResponseListener;
import com.arce.angel.request.Models.SongModel;
import com.arce.angel.request.Parsers.SongParser;
import com.arce.angel.request.R;
import com.arce.angel.request.Request.WebRequest;
import com.arce.angel.request.Util.ConstantsUtil;

import org.json.JSONException;

import java.util.ArrayList;

public class GETRequestFragment extends Fragment {

    View rootView;
    ListView listView;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;

    public GETRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GETRequestFragment.
     */
    public static GETRequestFragment newInstance() {
        GETRequestFragment fragment = new GETRequestFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_getrequest, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);
        progressBar.setVisibility(View.VISIBLE);

        listView = (ListView) rootView.findViewById(R.id.listView);

        makeListView();

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeListView();
            }
        });

        return rootView;
    }

    public void makeListView() {

        WebRequest.sendAsynchronousRequestGET(ConstantsUtil.URL_REQUEST_GET, getActivity().getApplicationContext(), this.rootView, new ResponseListener() {
            @Override
            public void onResponse(String response) {
                Log.d("Peticion", response);

                try {
                    ArrayList<SongModel> dataSet = SongParser.parseJSONFromString(response);
                    SongAdapter adapter = new SongAdapter(getActivity().getApplicationContext());
                    adapter.addAll(dataSet);
                    listView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(String message) {

                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
