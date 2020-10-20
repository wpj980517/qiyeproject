package itcast.utils;

import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.util.Date;

/*
在此类中继承PropertiesEditor,然后重写里面的方法来
 */
public class DateStringEditor extends PropertiesEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        //在这里使用方法来转换
        Date date=DateUtils.String2Date(text, "yyyy-MM-dd HH:mm");
        super.setValue(date);
    }
}
