package br.com.edu.settingsapplication.ui.adapters.viewholders;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.Arrays;
import java.util.List;

import br.com.edu.settingsapplication.R;
import br.com.edu.settingsapplication.domain.QuickAction;
import br.com.edu.settingsapplication.domain.Setting;
import br.com.edu.settingsapplication.ui.adapters.OnItemClickListener;
import br.com.edu.settingsapplication.ui.adapters.SettingAdapter;
import br.com.edu.settingsapplication.ui.adapters.SimpleListAdapter;
import br.com.edu.settingsapplication.ui.adapters.viewholders.enums.VisualisationMode;

public class SettingGridViewHolder extends RecyclerView.ViewHolder {
    private final ImageView icon;
    private final TextView title, description, complementaryInfo;
    private MaterialCardView container, iconContainer;
    private ImageView backdrop;
    private RecyclerView quickActions;
    private SimpleListAdapter simpleListAdapter;

    public SettingGridViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener, Context context) {
        super(itemView);

        container = itemView.findViewById(R.id.setting_item_container);
        iconContainer = itemView.findViewById(R.id.setting_icon_container);
        icon = itemView.findViewById(R.id.setting_item_icon);
        title = itemView.findViewById(R.id.setting_item_title);
        description = itemView.findViewById(R.id.setting_item_description);
        complementaryInfo = itemView.findViewById(R.id.setting_item_action_complementary_info);
        quickActions = itemView.findViewById(R.id.setting_quick_actions);

        if (onItemClickListener != null)
            container.setOnClickListener(v -> onItemClickListener.onItemClick(getAdapterPosition()));
    }

    public void bind(Setting setting, Context context) {
        iconContainer.setCardBackgroundColor(setting.getColorRes());
        icon.setImageResource(setting.getIconRes());
        title.setText(setting.getTitle());
        description.setText(setting.getDescription());

        if (setting.hasQuickActions()) {
            quickActions.setVisibility(View.VISIBLE);
            simpleListAdapter = new SimpleListAdapter(context, setting);
            quickActions.setAdapter(simpleListAdapter);
        } else {
            quickActions.setVisibility(View.GONE);
        }

        container.setRippleColor(ColorStateList.valueOf(setting.getBackdropColor()).withAlpha(25));

        if (setting.hasExtraInfo()) {
            complementaryInfo.setText(setting.getAdditionalInfo());
            complementaryInfo.setVisibility(View.VISIBLE);
        } else {
            complementaryInfo.setVisibility(View.GONE);
        }
    }
}
