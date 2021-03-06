package my_manage.tool;

import android.app.DatePickerDialog;
import android.content.Context;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.function.Consumer;

import my_manage.iface.ISetValueByString;

public final class DateUtils {
    private static String                  JoinerString = " ～ ";
    public static  ThreadLocal<DateFormat> threadLocal  = new ThreadLocal<DateFormat>() {
        @Nullable
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-M-d");
        }
    };

    public static String date2String(Calendar date, int addMonth) {
        if (date == null) return "";
        String   first = threadLocal.get().format(date.getTime());
        Calendar d2    = Calendar.getInstance();
        d2.setTimeInMillis(date.getTimeInMillis());
        d2.add(Calendar.MONTH, addMonth);
        d2.add(Calendar.DATE, -1);
        return first + JoinerString + threadLocal.get().format(d2.getTime());
    }

    public static String string2DateString(int year, int month, int day, int months) {
        Calendar d1 = Calendar.getInstance();
        d1.set(year, month, day);
        if (months == 0)
            return date2String(d1);
        return date2String(d1, months);
    }

    public static String date2String(Calendar date) {
        if (date == null) return "";
        return threadLocal.get().format(date.getTime());
    }

    public static Calendar string2Date(String text) {
        if (StrUtils.isBlank(text)) return null;


        String[] sNull = text.split(JoinerString);
        String   ss;
        if (sNull.length < 2)
            ss = text;
        else
            ss = sNull[0];
        try {
            Calendar result = Calendar.getInstance();
            result.setTime(threadLocal.get().parse(ss));
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 点击弹出日期选择窗口,并将指定的View设置为字符串
     *
     * @param setText
     */
    public static void showDateDialog(Context context, ISetValueByString setText) {
        showDateDialog(context, setText, null, 0);
    }

    /**
     * 点击弹出日期选择窗口,并将指定的View设置为字符串
     *
     * @param setText
     */
    public static void showDateDialog(Context context, ISetValueByString setText, Consumer<String> consumer) {
        showDateDialog(context,setText,consumer,0);
    }


    /**
     * 点击弹出日期选择窗口,并将指定的View设置为字符串
     *
     * @param setText
     */
    public static void showDateDialog(Context context, ISetValueByString setText, Consumer<String> consumer, int months) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dpd = new DatePickerDialog(context, DatePickerDialog.THEME_HOLO_LIGHT, (view1, year, monthOfYear, dayOfMonth) -> {
            setText.setText(string2DateString(year, monthOfYear, dayOfMonth, months));
            if (consumer != null)
                consumer.accept(null);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dpd.show();
    }
}
