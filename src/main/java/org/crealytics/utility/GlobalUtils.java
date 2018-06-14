package org.crealytics.utility;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

public class GlobalUtils {
    public static <T> T parseValue(Class<T> clazz, String value){
        PropertyEditor editor = PropertyEditorManager.findEditor(clazz);
        editor.setAsText(value);
        return (T)editor.getValue();
    }

   public enum site{
       desktop_web( "desktop web"),
       mobile_web( "mobile web"),
       android( "android"),
       iOS( "iOS");

       private String name;

       site(String name) {
           this.name = name;
       }

       public String getName() {
           return name;
       }
   }
}
