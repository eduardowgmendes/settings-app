package br.com.edu.settingsapplication.ui.adapters.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import br.com.edu.settingsapplication.R;
import br.com.edu.settingsapplication.domain.Setting;
import br.com.edu.settingsapplication.ui.adapters.OnItemClickListener;

public class SettingListViewHolder extends RecyclerView.ViewHolder {
    private final MaterialCardView iconContainer;
    private final ImageView icon;
    private final TextView title, complementaryInfo;

    public SettingListViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
        super(itemView);

        iconContainer = itemView.findViewById(R.id.setting_icon_container);
        icon = itemView.findViewById(R.id.setting_item_icon);
        title = itemView.findViewById(R.id.setting_item_title);
        complementaryInfo = itemView.findViewById(R.id.setting_item_action_complementary_info);

        if (onItemClickListener != null)
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(getAdapterPosition()));

    }

    public void bind(Setting setting, Context context) {
        iconContainer.setCardBackgroundColor(setting.getColorRes());
        icon.setImageResource(setting.getIconRes());
        title.setText(setting.getTitle());

        if (setting.hasExtraInfo()) {
            title.setSelected(true);
            complementaryInfo.setText(setting.getAdditionalInfo());
            complementaryInfo.setVisibility(View.VISIBLE);
        } else {
            title.setMaxEms(16);
            complementaryInfo.setVisibility(View.GONE);
        }

    }

}
