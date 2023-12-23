package br.com.edu.settingsapplication.ui.adapters.viewholders.enums;

public enum VisualisationMode {
    LIST_MODE, GRID_MODE, STAGGERED_GRID_MODE;

    public boolean isListMode() {
        return this == LIST_MODE;
    }

    public boolean isGridMode() {
        return this == GRID_MODE;
    }

    public boolean isStaggeredGridMode() {
        return this == STAGGERED_GRID_MODE;
    }
}
