package br.com.edu.settingsapplication.ui.fragments;

import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import br.com.edu.settingsapplication.R;
import br.com.edu.settingsapplication.ui.adapters.SettingAdapter;
import br.com.edu.settingsapplication.domain.Setting;
import br.com.edu.settingsapplication.ui.adapters.viewholders.enums.VisualisationMode;
import br.com.edu.settingsapplication.ui.placeholders.EmptyState;

public class ListFragment extends Fragment {

    private static final String TAG = "settings_list_fragment";
    public static final String VISUALISATION_MODE_TAG = "visualisation_mode";
    private List<Setting> settingList;
    private VisualisationMode visualisationMode;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(List<Setting> settingList, VisualisationMode visualisationMode) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(TAG, (ArrayList<? extends Parcelable>) settingList);
        args.putString(VISUALISATION_MODE_TAG, visualisationMode.name());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            settingList = getArguments().getParcelableArrayList(TAG);
            visualisationMode = VisualisationMode.valueOf(getArguments().getString(VISUALISATION_MODE_TAG));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView settingsList = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);

        setVisualisationMode(settingsList, visualisationMode);

        return settingsList;
    }

    private void setVisualisationMode(RecyclerView recyclerView, VisualisationMode visualisationMode) {

        switch (visualisationMode) {
            case LIST_MODE:
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                break;
            case GRID_MODE:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (position == 0 || position == 1) {
                            return 1;
                        } else if ((position - 2) % 3 == 0) {
                            return 2;
                        } else {
                            return 1;
                        }
                    }
                });

                recyclerView.setLayoutManager(gridLayoutManager);
                break;
            case STAGGERED_GRID_MODE:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                break;
            default:
                throw new IllegalArgumentException("Unknown visualisation mode!");
        }

        SettingAdapter settingAdapter = new SettingAdapter(getContext(), settingList, visualisationMode);
        recyclerView.setAdapter(settingAdapter);

        settingAdapter.setOnItemClickListener(position -> {
            applyContent(setUpContent(settingList.get(position)));
        });
    }

    private void applyContent(Fragment fragment) {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_content, fragment)
                .addToBackStack(TAG)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private Fragment setUpContent(Setting setting) {
        return EmptyStateFragment.newInstance(new EmptyState(setting.getIconRes(), setting.getBackdropColor(), setting.getTitle(), getContext().getResources().getString(R.string.empty_state_description_placeholder)));
    }

}