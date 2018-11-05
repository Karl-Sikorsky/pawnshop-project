package com.abwebmobile.karl.zslombard.allFragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.abwebmobile.karl.zslombard.AdressItem;
import com.abwebmobile.karl.zslombard.GatewaysAdaptersRepositories.AdressesRecyclerAdapter;
import com.abwebmobile.karl.zslombard.HostView;
import com.abwebmobile.karl.zslombard.RoomEntities.City;
import com.abwebmobile.karl.zslombard.RoomEntities.Department;
import com.abwebmobile.karl.zslombard.useCasesAndIteractors.InfoViewModel;
import com.abwebmobile.karl.zslombard.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import static com.abwebmobile.karl.zslombard.Constants.ADRESSES_FRAGMENT;

/**
 * Created by Karl on 04.02.2018.
 * Фрагмент, отображающий вкладку меню "КОНТАКТЫ"
 */

public class AdressesFragment extends Fragment {

    GoogleMap mGoogleMap;
    MapView mMapView;
    RecyclerView mRecyclerView;
    InfoViewModel mViewModel;
    Spinner mSpinner;
    HostView mHostView;
    LiveData<List<City>> mCities;
    int mSavedSelection;

    public void setHostView(HostView hostView) {
        this.mHostView = hostView;
    }

    AdressesRecyclerAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
        setRetainInstance(true);
        super.onCreate(savedInstanceState);

        Log.d("lifecycleObserver","Fragment onCreate");
        Log.d("landscape", "selection is "+mSavedSelection);
        Log.d("landscape", "onCreate selection is "+mSavedSelection);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("lifecycleObserver","Fragment onActivityCreated");
        Log.d("landscape", "onActivityCreated before savedInstance selection is "+mSavedSelection);
       }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_adresses, container, false);
        mSpinner = (Spinner) v.findViewById(R.id.spinnerCities);
        Log.d("lifecycleObserver","Fragment onCreateView");
     // if we need to add default data into Database
    //  viewModel.addDefaultCities();
    //  viewModel.addDefaultDepartments();
        mCities = mViewModel.getAllCities();
        if (mCities.getValue() != null) {
            Log.d("cities", "after cities called size = " + mCities.getValue().size());
        }

        Log.d("landscape", "onCreateView savedInstance selection is "+mSavedSelection);

        if (savedInstanceState != null) {
            mSavedSelection = savedInstanceState.getInt("spinner", 0);
            Log.d("landscape", " IN SAVEDINSTANCE selecttion setted "+ savedInstanceState.getInt("spinner", 0));
        }

        //load cities and departments from database
        mCities.observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(@Nullable List<City> items) {
                if (items.size() == 0) {
                    mViewModel.addDefaultCities();
                    mViewModel.addDefaultDepartments();

                } else {
                    Log.d("refreshObserver", "AHA CATCH CITIES size = " + items.size());

                    ArrayAdapter<City> adapter =
                            new ArrayAdapter<City>(getActivity().getApplicationContext(), R.layout.spinner_item, items);
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    mSpinner.setAdapter(adapter);
                    mSpinner.setSelection(mSavedSelection);
                    Log.d("landscape", "in Observed spinner set selection is " + mSavedSelection);
                    mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            refreshMap(mCities.getValue().get(mSpinner.getSelectedItemPosition()));
                            refreshDepartments(mCities.getValue().get(mSpinner.getSelectedItemPosition()));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }
        });



        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately
        //логика отображения гугл карты, нанесение точек

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mGoogleMap = mMap;
                // Get back the mutable Polygon
                //поворот и перемещение камеры карты
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target( new LatLng(50, 30))
                        .zoom(12)
                        .build();
              /* */

                CameraUpdate camUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

                mGoogleMap.moveCamera(camUpdate);

               /* googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(50.451271, 30.458920))
                        .title("тут"));*/
            }
        });
        mRecyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(llm);




        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("lifecycleObserver","Fragment onSaveInstanceState");
        outState.putInt("spinner", mSpinner.getSelectedItemPosition());
        Log.d("landscape", "in onSavedInstanceState "+ mSpinner.getSelectedItemPosition());

        // do this for each or your Spinner
        // You might consider using Bundle.putStringArray() instead
    }
    // set actual departments from database
    private void refreshDepartments(City city) {
        Log.d("adapterAdresses", "Selected city = "+city.city_name);
        LiveData<List<Department>> adressItems = mViewModel.getAdressItemsByCity(city.city_name);
        if(adressItems.getValue()!=null){
            Log.d("adress","after getAdresstItems called size = "+adressItems.getValue().size());}

        adressItems.observe(this, new Observer<List<Department>>() {
            @Override
            public void onChanged(@Nullable List<Department> items) {
                Log.d("adress","adressItemsChanged size = "+items.size());
                ArrayList<AdressItem> adresses = new ArrayList<>();
                for (int i =0;i<items.size(); i++){
                    adresses.add(new AdressItem(items.get(i).department_id,items.get(i).department_name,items.get(i).department_description, items.get(i).department_adress, items.get(i).department_protocol, items.get(i).department_director, items.get(i).department_workhours ));
                }
                adapter  = new AdressesRecyclerAdapter(adresses);
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void refreshMap(City city) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target( new LatLng(city.lat, city.lng ))
                .zoom(12)
                .build();

        CameraUpdate camUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        mGoogleMap.moveCamera(camUpdate);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("lifecycleObserver","Fragment onAttach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("lifecycleObserver","Fragment onPause");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("lifecycleObserver","Fragment onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("lifecycleObserver","Fragment onDestroy");
    }
}
