package com.example.myapplication.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.HomeHorAdapter;
import com.example.myapplication.Adapters.HomeVerAdapter;
import com.example.myapplication.Adapters.UpdateVertical;
import com.example.myapplication.R;
import com.example.myapplication.activities.ProfileActivity;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.models.HomeHorModel;
import com.example.myapplication.models.HomeVerModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements UpdateVertical {
    ImageView profile;
    RecyclerView homeHorizontalRec,homeVerticalRec;
    ArrayList<HomeHorModel> homeHorModellist;
    HomeHorAdapter homeHorAdapter;
    ArrayList<HomeVerModel> homeVerModellist;
    HomeVerAdapter homeVerAdapter;
    private FragmentHomeBinding binding;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
        View root  = inflater.inflate(R.layout.fragment_home,container,false);
        homeHorizontalRec= root.findViewById(R.id.horizontal_home1);
        homeVerticalRec= root.findViewById(R.id.vertical_home1);
        profile=root.findViewById(R.id.profileViewer);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProfileActivity.class));
            }
        });

        //horizontal Recycler view
        homeHorModellist = new ArrayList<>();
        homeHorModellist.add(new HomeHorModel(R.drawable.restaurant,"Canteen"));
        homeHorModellist.add(new HomeHorModel(R.drawable.sign,"BruBakes"));
        homeHorModellist.add(new HomeHorModel(R.drawable.dosa_icn,"Dosa"));


        homeHorAdapter = new HomeHorAdapter(this,getActivity(),homeHorModellist);
        homeHorizontalRec.setAdapter(homeHorAdapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        homeVerModellist = new ArrayList<>();
//        homeVerModellist.add(new HomeVerModel(R.drawable.paneer_sandwich,"Paneer Sandwich","Rs.60"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.aalo_sandwich,"Aalo sandwich","Rs.40"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.butter_maggi,"butter maggi","Rs.35"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.veg_maggi,"Veg maggi","Rs.35"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.egg_maggi,"egg maggi","Rs.40"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.plain_maggi,"Plain Maggi","Rs.25"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.mixed_paratha,"Mixed paratha","Rs.30"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.aalo_paratha,"Aalo paratha","Rs.30"));
//        homeVerModellist.add(new HomeVerModel(R.drawable.paneer_paratha,"paneer paratha","Rs.40"));

        homeVerAdapter = new HomeVerAdapter(getActivity(),homeVerModellist);
        homeVerticalRec.setAdapter(homeVerAdapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
//        homeVerticalRec.setHasFixedSize(true);
//        homeVerticalRec.setNestedScrollingEnabled(false);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void callback(int position, ArrayList<HomeVerModel> list) {

        homeVerAdapter = new HomeVerAdapter(getContext(),list);
        homeVerAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVerAdapter);
    }
}