package br.com.edu.settingsapplication.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.edu.settingsapplication.R;
import br.com.edu.settingsapplication.domain.Setting;
import br.com.edu.settingsapplication.ui.adapters.viewholders.SettingGridViewHolder;
import br.com.edu.settingsapplication.ui.adapters.viewholders.SettingListViewHolder;
import br.com.edu.settingsapplication.ui.adapters.viewholders.SettingRichGridViewHolder;
import br.com.edu.settingsapplication.ui.adapters.viewholders.SettingStaggeredGridViewHolder;
import br.com.edu.settingsapplication.ui.adapters.viewholders.enums.VisualisationMode;

public class SettingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Setting> settingsList;
    private OnItemClickListener onItemClickListener;
    private VisualisationMode visualisationMode;

    public SettingAdapter(Context context, List<Setting> settingsList, VisualisationMode visualisationMode) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.settingsList = settingsList;
        this.visualisationMode = visualisationMode;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (visualisationMode) {
            case LIST_MODE:
                return new SettingListViewHolder(inflater.inflate(R.layout.setting_list_item_layout, parent, false), onItemClickListener);
            case GRID_MODE:
                switch (viewType) {
                    case 0:
                        return new SettingGridViewHolder(inflater.inflate(R.layout.setting_grid_item_layout, parent, false), onItemClickListener, context);
                    case 1:
                        return new SettingRichGridViewHolder(inflater.inflate(R.layout.setting_rich_grid_item_layout, parent, false), onItemClickListener, context);
                    default:
                        throw new IllegalArgumentException("On GRID_MODE no viewType with given was found: " + viewType);
                }
            case STAGGERED_GRID_MODE:
                return new SettingStaggeredGridViewHolder(inflater.inflate(R.layout.setting_staggered_grid_item_layout, parent, false), onItemClickListener);
            default:
                throw new IllegalArgumentException("Invalid visualisation mode");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return settingsList.get(position).getTitle().equals("Battery") ? 1 : 0;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (visualisationMode) {
            case LIST_MODE:
                SettingListViewHolder settingListViewHolder = (SettingListViewHolder) viewHolder;
                settingListViewHolder.bind(settingsList.get(position), context);
                break;
            case GRID_MODE:
                switch (viewHolder.getItemViewType()) {
                    case 0:
                        SettingGridViewHolder settingGridViewHolder = (SettingGridViewHolder) viewHolder;
                        settingGridViewHolder.bind(settingsList.get(position), context);
                        break;
                    case 1:
                        SettingRichGridViewHolder settingRichGridViewHolder = (SettingRichGridViewHolder) viewHolder;
                        settingRichGridViewHolder.bind(settingsList.get(position), context);
                        break;
                }
                break;
            case STAGGERED_GRID_MODE:
                SettingStaggeredGridViewHolder settingStaggeredGridViewHolder = (SettingStaggeredGridViewHolder) viewHolder;
                settingStaggeredGridViewHolder.bind(settingsList.get(position));
                break;
            default:
                throw new IllegalArgumentException("Invalid visualisation mode");
        }
    }

    @Override
    public int getItemCount() {
        return settingsList != null ? settingsList.size() : 0;
    }

    public void setSettingsList(List<Setting> settingsList) {
        this.settingsList = settingsList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
