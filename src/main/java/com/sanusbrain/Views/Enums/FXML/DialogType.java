package com.sanusbrain.Views.Enums.FXML;

public enum DialogType {
    INFO("fas-circle-info", "mfx-info-dialog"),
    SUCCESS("fas-circle-check", "mfx-success-dialog"),
    WARNING("fas-circle-exclamation", "mfx-warn-dialog"),
    ERROR("fas-circle-xmark", "mfx-error-dialog");

    private final String icon;
    private final String styleClass;

    /**
     * Constructor for DialogType.
     *
     * @param icon          Icon description.
     * @param styleClass    Style description.
     */
    DialogType(String icon, String styleClass) {
        this.icon = icon;
        this.styleClass = styleClass;
    }

    // Getter
    public String getIcon() {
        return icon;
    }

    public String getStyleClass() {
        return styleClass;
    }
}
