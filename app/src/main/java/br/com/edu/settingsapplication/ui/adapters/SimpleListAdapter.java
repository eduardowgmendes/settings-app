package br.com.edu.settingsapplication.ui.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.materialswitch.MaterialSwitch;

import java.util.List;

import br.com.edu.settingsapplication.R;
import br.com.edu.settingsapplication.domain.QuickAction;
import br.com.edu.settingsapplication.domain.Setting;

public class SimpleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private Setting setting;
    private final LayoutInflater inflater;

    private static final int INFORMATIONAL = 1;
    private static final int CHECKABLE = 2;
    private static final int CLICKABLE = 3;
    private static final int SWITCHABLE = 4;

    public SimpleListAdapter(Context context, Setting setting) {
        this.context = context;
        this.setting = setting;
        this.inflater = LayoutInflater.from(context);
    }

    public SimpleListAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case INFORMATIONAL:
                return new InformationalQuickActionViewHolder(inflater.inflate(R.layout.informational_item_layout, parent, false));
            case SWITCHABLE:
                return new SwitchableQuickActionViewHolder(inflater.inflate(R.layout.switchtable_item_layout, parent, false));
            case CHECKABLE:
                return new CheckableQuickActionViewHolder(inflater.inflate(R.layout.checkable_item_layout, parent, false));
            case CLICKABLE:
                return new ClickableQuickActionViewHolder(inflater.inflate(R.layout.clickable_item_layout, parent, false));
            default:
                throw new IllegalArgumentException("No viewType compatible with: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        List<QuickAction> quickActions = setting.getQuickActions();

        switch (quickActions.get(position).getType()) {
            case INFORMATIONAL:
                InformationalQuickActionViewHolder informationalQuickSetting = (InformationalQuickActionViewHolder) holder;
                informationalQuickSetting.bind(quickActions.get(position));
                break;
            case SWITCH:
                SwitchableQuickActionViewHolder switchableQuickSetting = (SwitchableQuickActionViewHolder) holder;
                switchableQuickSetting.bind(quickActions.get(position));
                break;
            case CHECKBOX:
                CheckableQuickActionViewHolder checkableQuickSetting = (CheckableQuickActionViewHolder) holder;
                checkableQuickSetting.bind(quickActions.get(position), setting);
                break;
            case BUTTON:
                ClickableQuickActionViewHolder clickableQuickSetting = (ClickableQuickActionViewHolder) holder;
                clickableQuickSetting.bind(quickActions.get(position), setting);
                break;
            default:
                throw new IllegalArgumentException("No holder compatible with: " + holder);
        }
    }

    @Override
    public int getItemCount() {
        List<QuickAction> quickActions = setting.getQuickActions();
        return quickActions == null ? 0 : quickActions.size();
    }

    @Override
    public int getItemViewType(int position) {
        QuickAction quickAction = setting.getQuickActions().get(position);
        if (quickAction.getType().isInformational()) return INFORMATIONAL;
        if (quickAction.getType().isSwitch()) return SWITCHABLE;
        if (quickAction.getType().isCheckbox()) return CHECKABLE;
        if (quickAction.getType().isButton()) return CLICKABLE;
        throw new IllegalArgumentException("No viewType compatible!");
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    class InformationalQuickActionViewHolder extends RecyclerView.ViewHolder {

        private TextView itemTitle, itemInformation;

        public InformationalQuickActionViewHolder(@NonNull View itemView) {
            super(itemView);

            itemTitle = itemView.findViewById(R.id.item_title);
            itemInformation = itemView.findViewById(R.id.item_information);
        }

        public TextView getItemTitle() {
            return itemTitle;
        }

        public void bind(QuickAction quickAction) {
            itemTitle.setText(quickAction.getTitle());
            itemInformation.setText(quickAction.getActionName());
        }

    }

    class SwitchableQuickActionViewHolder extends InformationalQuickActionViewHolder {

        private MaterialSwitch switchAction;

        public SwitchableQuickActionViewHolder(@NonNull View itemView) {
            super(itemView);

            switchAction = itemView.findViewById(R.id.item_switch_action);
        }

        @Override
        public void bind(QuickAction quickAction) {
            getItemTitle().setText(quickAction.getTitle());
        }
    }

    class CheckableQuickActionViewHolder extends InformationalQuickActionViewHolder {

        private MaterialCheckBox checkableAction;

        public CheckableQuickActionViewHolder(@NonNull View itemView) {
            super(itemView);

            checkableAction = itemView.findViewById(R.id.item_checkbox_action);
        }

        public void bind(QuickAction quickAction, Setting setting) {
            getItemTitle().setText(quickAction.getTitle());
            checkableAction.setHint(quickAction.getActionName());
            checkableAction.setButtonTintList(ColorStateList.valueOf(setting.getColorRes()));
        }
    }

    class ClickableQuickActionViewHolder extends InformationalQuickActionViewHolder {

        private MaterialButton clickableAction;

        public ClickableQuickActionViewHolder(@NonNull View itemView) {
            super(itemView);

            clickableAction = itemView.findViewById(R.id.item_clickable_action);
        }

        public void bind(QuickAction quickAction, Setting setting) {
            getItemTitle().setText(quickAction.getTitle());
            clickableAction.setText(quickAction.getActionName());
            clickableAction.setBackgroundTintList(ColorStateList.valueOf(setting.getColorRes()));
        }
    }

}
