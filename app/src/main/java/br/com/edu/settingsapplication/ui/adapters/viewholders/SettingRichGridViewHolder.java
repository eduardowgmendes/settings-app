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

import java.util.Random;

import br.com.edu.settingsapplication.R;
import br.com.edu.settingsapplication.domain.Setting;
import br.com.edu.settingsapplication.ui.adapters.OnItemClickListener;
import br.com.edu.settingsapplication.ui.adapters.SimpleListAdapter;

public class SettingRichGridViewHolder extends RecyclerView.ViewHolder {
    private final ImageView icon;
    private final TextView title, description, featureDisplay;
    private final MaterialCardView container;
    private final MaterialCardView iconContainer;
    private final RecyclerView quickActions;
    private final Context context;

    public SettingRichGridViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener, Context context) {
        super(itemView);

        this.context = context;

        container = itemView.findViewById(R.id.setting_item_container);
        iconContainer = itemView.findViewById(R.id.setting_icon_container);
        icon = itemView.findViewById(R.id.setting_item_icon);
        title = itemView.findViewById(R.id.setting_item_title);
        description = itemView.findViewById(R.id.setting_item_description);
        quickActions = itemView.findViewById(R.id.setting_quick_actions);
        featureDisplay = itemView.findViewById(R.id.setting_feature_display);

        if (onItemClickListener != null)
            container.setOnClickListener(v -> onItemClickListener.onItemClick(getAdapterPosition()));
    }

    private static final int BATTERY_LEVEL_BOUND = 100;
    private static final Random random = new Random();

    public void bind(Setting setting, Context context) {

        int batteryLevel = random.nextInt(BATTERY_LEVEL_BOUND);

        title.setText(setting.getTitle());
        description.setText(setting.getDescription());

        setComponentsStatesAccordingToBatteryLevel(batteryLevel, false);

        featureDisplay.setText(String.format("%1$d%%", batteryLevel));

        if (setting.hasQuickActions()) {
            quickActions.setVisibility(View.VISIBLE);
            SimpleListAdapter simpleListAdapter = new SimpleListAdapter(context, setting);
            quickActions.setAdapter(simpleListAdapter);
        } else {
            quickActions.setVisibility(View.GONE);
        }

        container.setRippleColor(ColorStateList.valueOf(setting.getBackdropColor()).withAlpha(25));
    }

    private void setComponentsStatesAccordingToBatteryLevel(int batteryLevel, boolean isChargingRightNow) {

        if(isChargingRightNow){
            container.setStrokeColor(ContextCompat.getColor(context, R.color.green_fields));
            iconContainer.setCardBackgroundColor(ContextCompat.getColor(context, R.color.green_fields));
            featureDisplay.setTextColor(ContextCompat.getColor(context, R.color.green_fields));
            icon.setImageResource(R.drawable.twotone_battery_charging_full_24);
        }else{
            if (batteryLevel >= 0 && batteryLevel <= 10) {
                container.setStrokeColor(ContextCompat.getColor(context, R.color.black));
                iconContainer.setCardBackgroundColor(ContextCompat.getColor(context, R.color.black));
                featureDisplay.setTextColor(ContextCompat.getColor(context, R.color.black));
                icon.setImageResource(R.drawable.twotone_battery_alert_24);
                icon.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white)));
            } else if (batteryLevel >= 11 && batteryLevel <= 20) {
                container.setStrokeColor(ContextCompat.getColor(context, R.color.red_flag));
                iconContainer.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red_flag));
                featureDisplay.setTextColor(ContextCompat.getColor(context, R.color.red_flag));
                icon.setImageResource(R.drawable.twotone_battery_1_bar_24);
                icon.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.black)));
            } else if (batteryLevel >= 21 && batteryLevel <= 50) {
                container.setStrokeColor(ContextCompat.getColor(context, R.color.sunrise_sky));
                iconContainer.setCardBackgroundColor(ContextCompat.getColor(context, R.color.sunrise_sky));
                featureDisplay.setTextColor(ContextCompat.getColor(context, R.color.sunrise_sky));
                icon.setImageResource(R.drawable.twotone_battery_2_bar_24);
            } else if (batteryLevel >= 51 && batteryLevel <= 60) {
                container.setStrokeColor(ContextCompat.getColor(context, R.color.green_fields));
                iconContainer.setCardBackgroundColor(ContextCompat.getColor(context, R.color.green_fields));
                featureDisplay.setTextColor(ContextCompat.getColor(context, R.color.green_fields));
                icon.setImageResource(R.drawable.twotone_battery_3_bar_24);
            } else if (batteryLevel >= 61 && batteryLevel <= 70) {
                icon.setImageResource(R.drawable.twotone_battery_4_bar_24);
            } else if (batteryLevel >= 71 && batteryLevel <= 80) {
                icon.setImageResource(R.drawable.twotone_battery_5_bar_24);
            } else if (batteryLevel >= 81 && batteryLevel <= 90) {
                icon.setImageResource(R.drawable.twotone_battery_6_bar_24);
            } else if (batteryLevel >= 91 && batteryLevel <= 100) {
                icon.setImageResource(R.drawable.twotone_battery_full_24);
            }
        }

    }
}
