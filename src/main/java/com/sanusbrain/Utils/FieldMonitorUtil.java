package com.sanusbrain.Utils;

import com.sanusbrain.Controllers.Primary.Patient.Base.MenuBarController;
import com.sanusbrain.Models.Model;
import com.sanusbrain.Views.Enums.Database.BaseDataKeys;
import com.sanusbrain.Views.Enums.FXML.BaseDataViewOptions;
import io.github.palexdev.materialfx.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

import java.lang.reflect.Field;
import java.util.*;
import java.util.Map.Entry;

/**
 * Utility Class to monitor changes in JavaFX fields and handle initial field values.
 */
public class FieldMonitorUtil {

    private final Map<BaseDataKeys, Object> allInitialFieldValues = new HashMap<>();
    private static final Map<BaseDataKeys, Object> personalFieldsMap = new HashMap<>();
    private static final Map<BaseDataKeys, Object> contactFieldsMap = new HashMap<>();
    private static final Map<BaseDataKeys, Object> anamneseFieldsMap = new HashMap<>();
    private static final Map<BaseDataKeys, Object> insuranceFieldsMap = new HashMap<>();

    /**
     * Constructor for FieldMonitorUtil.
     */
    public FieldMonitorUtil() {
    }

    public void initializeFieldMap(Class T, Map<BaseDataKeys, Object> fieldMap, List<Object> fxmlElementList) {
        Field[] fields = T.getDeclaredFields();
        int counter = 0;

        for(Field field:fields){
            String key = field.getName();
            if(key.contains("mfx") && BaseDataKeys.contains(key)){
                fieldMap.put(BaseDataKeys.valueOf(key.toUpperCase()),fxmlElementList.get(counter));
                counter++;
            }
        }
    }

    /**
     * Initializes the initial field values, by storing initial values into a list and
     * adding Listeners to fields, to observe changes. Adding fields to specific section-
     * field-map for comparing during saving-process.
     *
     * @param fieldMap             The list of FXML-Nodes with fx:id as key value to monitor
     * @param initialFieldValues    The map to store the initial field values
     */
    public void initializeInitialFieldValues(Map<BaseDataKeys,Object> fieldMap, Map<BaseDataKeys, Object> initialFieldValues, BaseDataViewOptions section){
        //Adding fields to specific section field map for comparing during saving-process
        switch(section){
            case PERSONAL_DATA -> personalFieldsMap.putAll(fieldMap);
            case CONTACT_DATA -> contactFieldsMap.putAll(fieldMap);
            case ANAMNESE_DATA -> anamneseFieldsMap.putAll(fieldMap);
            case INSURANCE_DATA -> insuranceFieldsMap.putAll(fieldMap);
            default -> throw new IllegalArgumentException();
        }

        // Initializes the initial field values
        for(Entry<BaseDataKeys, Object> entry: fieldMap.entrySet()){
            BaseDataKeys key = entry.getKey();
            Object field = entry.getValue();
            String type = field.toString();

            if(type.contains("MFXTextField")){
                MFXTextField textField = (MFXTextField) field;

                // Save initial FXML-Field value into initialFieldValues List
                initialFieldValues.put(key,textField.getText() != null ? textField.getText() : "");

                // Adds a Listener to FXML-Field to observe changes
                textField.textProperty().addListener(createChangeListener(fieldMap, initialFieldValues));

            } else if (type.contains("MFXComboBox")) {
                MFXComboBox<?> comboBox = (MFXComboBox<?>) field;
                initialFieldValues.put(key,comboBox.getText() != null ? comboBox.getText() : "");
                comboBox.textProperty().addListener(createChangeListener(fieldMap, initialFieldValues));
            } else if (type.contains("MFXFilterComboBox")) {
                MFXFilterComboBox<?> filterComboBox = (MFXFilterComboBox<?>) field;
                initialFieldValues.put(key,filterComboBox.getText() != null ? filterComboBox.getText() : "");
                filterComboBox.textProperty().addListener(createChangeListener(fieldMap, initialFieldValues));
            } else if (type.contains("MFXDatePicker")) {
                MFXDatePicker datePicker = (MFXDatePicker) field;
                initialFieldValues.put(key, datePicker.getValue());
                datePicker.valueProperty().addListener(createChangeListener(fieldMap, initialFieldValues));
            }else if (type.contains("MFXToggleButton")) {
                MFXToggleButton toggleButton = (MFXToggleButton) field;
                initialFieldValues.put(key, toggleButton.isSelected());
                toggleButton.selectedProperty().addListener(createChangeListener(fieldMap, initialFieldValues));
            }else if (type.contains("ToggleGroup")){
                ToggleGroup toggleGroup = (ToggleGroup) field;
                initialFieldValues.put(key, Optional.ofNullable((MFXRadioButton) toggleGroup.getSelectedToggle())
                        .map(Labeled::getText)
                        .orElse(""));
                toggleGroup.selectedToggleProperty().addListener(createChangeListener(fieldMap, initialFieldValues));
            }else if (type.contains("TextArea")) {
                TextArea textArea = (TextArea) field;
                initialFieldValues.put(key, textArea.getText() != null ? textArea.getText() : "");
                textArea.textProperty().addListener(createChangeListener(fieldMap,initialFieldValues));
            }else if(type.contains("MFXSlider")) {
                MFXSlider slider = (MFXSlider) field;
                initialFieldValues.put(key, slider.getValue());
                slider.valueProperty().addListener(createChangeListener(fieldMap,initialFieldValues));
            }
        }
    }

    /**
     * Creates a ChangeListener for a field.
     *
     * @param initialFieldValues    The map containing the field values.
     * @param <T>                   The type of the field's value.
     * @return                      Returns the ChangeListener.
     */
     private <T> ChangeListener<T> createChangeListener(Map<BaseDataKeys, Object> fieldMap, Map<BaseDataKeys,Object> initialFieldValues){
         //System.out.println("   -> init cl fieldM / initialFV:\n\t"+fieldMap+"\n\t"+initialFieldValues);
         return (observableValue, oldValue, newValue) -> {
            //boolean changeDetected = (newValue!=null && !Objects.equals(initialFieldValues.get(key), newValue));
            boolean changeDetected = initialFieldValues.entrySet().stream()
                    .anyMatch(entry -> {
                        BaseDataKeys key = entry.getKey();
                        Object initialValue = entry.getValue();
                        Object currentValue = getCurrentFieldValue(key, fieldMap);
                        System.out.println("entry:"+entry+" old:"+initialValue+"  new:"+currentValue);
                        return !Objects.equals(initialValue, currentValue);
                    });

             //System.out.println("change:"+changeDetected+" old:"+oldValue+"  new:"+newValue);
            Model.getInstance().getController(MenuBarController.class).enableSaveButton(changeDetected);
        };
    }

    /**
     * Updates the field's value within the map.
     *
     * @param fieldMap              Contains all FXML-Fields.
     * @param initialFieldValues    Contains the field's values.
     */
    public void updateInitialFieldValues(Map<BaseDataKeys,Object> fieldMap, Map<BaseDataKeys,Object> initialFieldValues){
        //System.out.println("all initial before:\n"+allInitialFieldValues+"\n----------------------------------");
        for(Entry<BaseDataKeys,Object> entry: fieldMap.entrySet()){
            BaseDataKeys key = entry.getKey();
            Object field = entry.getValue();
            String type = entry.getValue().toString();

            if(type.contains("MFXTextField")){
                MFXTextField textField =  (MFXTextField) field;
                initialFieldValues.put(key, textField.getText() != null ? textField.getText() : "");
            } else if (type.contains("MFXComboBox")) {
                MFXComboBox<?> comboBox = (MFXComboBox<?>) field;
                initialFieldValues.put(key,comboBox.getText() != null ? comboBox.getText() : "");
            } else if (type.contains("MFXFilterComboBox")) {
                MFXFilterComboBox<?> filterComboBox = (MFXFilterComboBox<?>) field;
                initialFieldValues.put(key,filterComboBox.getText() != null ? filterComboBox.getText() : "");
            } else if (type.contains("MFXDatePicker")) {
                MFXDatePicker datePicker = (MFXDatePicker) field;
                initialFieldValues.put(key, datePicker.getValue());
            }else if (type.contains("MFXToggleButton")) {
                MFXToggleButton toggleButton = (MFXToggleButton) field;
                initialFieldValues.put(key, toggleButton.isSelected());
            }else if (type.contains("ToggleGroup")) {
                ToggleGroup toggleGroup = (ToggleGroup) field;
                initialFieldValues.put(key, Optional.ofNullable(
                        (MFXRadioButton) toggleGroup.getSelectedToggle())
                        .map(Labeled::getText)
                        .orElse(""));
            }else if (type.contains("TextArea")) {
                TextArea textArea = (TextArea) field;
                initialFieldValues.put(key, textArea.getText() != null ? textArea.getText() : "");
            }else if(type.contains("MFXSlider")) {
                MFXSlider slider = (MFXSlider) field;
                initialFieldValues.put(key, slider.getValue());
            }
        }

        // Saves all initial values into a separate list, for checks during saving-process
        this.allInitialFieldValues.putAll(initialFieldValues);
        //System.out.println("all initial after:\n"+allInitialFieldValues+"\n----------------------------------");

        // Enables Save-Button within MenuBarController
        Model.getInstance().getController(MenuBarController.class).enableSaveButton(false);
    }

    /**
     * Gets the current value of a field.
     *
     * @param key The key representing the field.
     * @return The current value of the field.
     */
    private Object getCurrentFieldValue(BaseDataKeys key, Map<BaseDataKeys, Object> fieldMap) {
        //System.out.println("get-Check: "+fieldMap+"\nkey:"+key+"\n");
        Object field = fieldMap.get(key);
        String type = field.toString();

        if(type.contains("MFXTextField")){
            return ((MFXTextField) field).getText();

        } else if (type.contains("MFXComboBox")) {
            return ((MFXComboBox<?>) field).getText();

        } else if(type.contains("MFXFilterComboBox")) {
            return ((MFXFilterComboBox<?>) field).getText();

        }else if (type.contains("MFXDatePicker")) {
            return ((MFXDatePicker) field).getValue();

        }else if (type.contains("MFXToggleButton")) {
            return ((MFXToggleButton) field).isSelected();

        }else if (type.contains("ToggleGroup")) {
            return Optional.ofNullable(((MFXRadioButton) ((ToggleGroup) field).getSelectedToggle()))
                    .map(Labeled::getText)
                    .orElse("");

        }else if (type.contains("TextArea")){
            return ((TextArea) field).getText();

        }else if(type.contains("MFXSlider")) {
            return ((MFXSlider) field).getValue();
        }

        return null;
    }

    /**
     * Collects and returns the current values of the fields within a map.
     *
     * @return A map containing the current field values.
     */
    public Map<BaseDataKeys, Object> collectCurrentValues(BaseDataViewOptions section) {
        Map<BaseDataKeys,Object> currentSectionValues = new HashMap<>();
        Map<BaseDataKeys, Object> extractValues = new HashMap<>();

        switch(section){
            case PERSONAL_DATA -> extractValues.putAll(personalFieldsMap);
            case CONTACT_DATA -> extractValues.putAll(contactFieldsMap);
            case ANAMNESE_DATA -> extractValues.putAll(anamneseFieldsMap);
            case INSURANCE_DATA -> extractValues.putAll(insuranceFieldsMap);
            default -> throw new IllegalArgumentException();
        }

        for(Map.Entry<BaseDataKeys,Object> entry: extractValues.entrySet()){
            BaseDataKeys key = entry.getKey();
            currentSectionValues.put(key,getCurrentFieldValue(key,extractValues));
        }

        return currentSectionValues;
    }

    public Map<BaseDataKeys, Object> getInitialFieldValues() {
        return allInitialFieldValues;
    }
}
