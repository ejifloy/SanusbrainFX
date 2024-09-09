package com.sanusbrain.Views.Enums.Database;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum HistoryKeys {
  // History Data Keys

  FXINITIALS("initials"),
  FXCREATIONDATE("creation_date"),
  FXAUTHOR("author"),
  FXCATEGORY("category"),
  FXTITLE("title"),
  FXDESCRIPTION("description"),
  FXPRIORITY("priority"),
  FXREMIND("remind"),
  FXREMIND_DATE("remind_date"),
  FXCOLOR("color"),
  FXSTATUS("status");

  public String label;
  // Set to hold all enum names for quick lookup
  private static final Set<String> KEYS = new HashSet<>();
  private static final Map<HistoryKeys,String> LABELS = new HashMap<>();

  // Static block to initialize the keys set
  static {
      for(HistoryKeys key: values()){
          KEYS.add(key.name().toLowerCase());
      }
  }

  HistoryKeys(String label){
      this.label = label;
  }

  /**
   * Checks if a given string exists in the enum.
   *
   * @param value String to be checked.
   * @return Returns true, if the String exists in the enum, otherwise false;
   */
  public static boolean contains(String value) {
    return KEYS.contains(value.toLowerCase());
  }


  /**
   * Returns the database-table label of the key.
   */
  public String getLabel() {
    return label;
  }
}
