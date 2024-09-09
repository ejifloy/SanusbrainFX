package com.sanusbrain.Utils;

import com.sanusbrain.Views.Enums.Database.BaseDataKeys;

import java.util.Map;

public interface FieldMapProvider {
    Map<BaseDataKeys, Object> getFieldMap();
    Map<BaseDataKeys, Object> getInitialFieldValues();
}
