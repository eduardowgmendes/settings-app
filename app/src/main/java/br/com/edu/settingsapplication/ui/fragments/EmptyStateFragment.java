package br.com.edu.settingsapplication.ui.fragments;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.edu.settingsapplication.R;
import br.com.edu.settingsapplication.ui.placeholders.EmptyState;

public class EmptyStateFragment extends Fragment {

    private static final String TAG = "empty_state";
    private EmptyState emptyState;

    public EmptyStateFragment() {
        // Required empty public constructor
    }

    public static EmptyStateFragment newInstance(EmptyState emptyState) {
        EmptyStateFragment fragment = new EmptyStateFragment();
        Bundle args = new Bundle();
        args.putParcelable(TAG, emptyState);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            emptyState = getArguments().getParcelable(TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty_state, container, false);

        ImageView emptyStateBackdrop = view.findViewById(R.id.empty_state_backdrop);
        emptyStateBackdrop.setImageResource(emptyState.getBackdropRes());
        emptyStateBackdrop.setColorFilter(emptyState.getBackdropTintRes());
        TextView emptyStateTitle = view.findViewById(R.id.empty_state_title);
        emptyStateTitle.setText(emptyState.getTitle());

        TextView emptyStateDescription = view.findViewById(R.id.empty_state_description);
        emptyStateDescription.setText(emptyState.getDescription());

        return view;
    }

    public void setEmptyState(EmptyState emptyState) {
        this.emptyState = emptyState;
    }
}