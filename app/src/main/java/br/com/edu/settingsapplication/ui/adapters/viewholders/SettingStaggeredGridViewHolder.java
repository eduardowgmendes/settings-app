package br.com.edu.settingsapplication.ui.adapters.viewholders;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import br.com.edu.settingsapplication.R;
import br.com.edu.settingsapplication.domain.Setting;
import br.com.edu.settingsapplication.ui.adapters.OnItemClickListener;

public class SettingStaggeredGridViewHolder extends RecyclerView.ViewHolder {
    private final ImageView icon;
    private final TextView title, complementaryInfo;

    private MaterialCardView container, iconContainer;

    public SettingStaggeredGridViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
        super(itemView);

        container = itemView.findViewById(R.id.setting_item_container);
        iconContainer = itemView.findViewById(R.id.setting_icon_container);
        icon = itemView.findViewById(R.id.setting_item_icon);
        title = itemView.findViewById(R.id.setting_item_title);
        complementaryInfo = itemView.findViewById(R.id.setting_item_action_complementary_info);

        if (onItemClickListener != null)
            container.setOnClickListener(v -> onItemClickListener.onItemClick(getAdapterPosition()));
    }

    public void bind(Setting setting) {

        iconContainer.setCardBackgroundColor(setting.getColorRes());
        icon.setImageResource(setting.getIconRes());
        title.setText(setting.getTitle());

        if (setting.hasExtraInfo()) {
            complementaryInfo.setText(setting.getAdditionalInfo());
            complementaryInfo.setVisibility(View.VISIBLE);
        } else {
            complementaryInfo.setVisibility(View.GONE);
        }

        container.setRippleColor(ColorStateList.valueOf(setting.getBackdropColor()).withAlpha(25));
    }
}
