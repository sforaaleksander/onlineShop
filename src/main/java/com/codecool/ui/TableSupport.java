package com.codecool.ui;

import com.jakewharton.fliptables.FlipTable;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableSupport {
    private static final Pattern METHOD = Pattern.compile("^(?:get|is|has)([A-Z][a-zA-Z0-9]*)+$");
    private static final Comparator<Method> METHOD_COMPARATOR = new Comparator<Method>() {
        @Override
        public int compare(Method o1, Method o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public static <T> String fromIterable(Iterable<T> rows, Class<T> rowType) {
        if (rows == null) throw new NullPointerException("rows == null");
        if (rowType == null) throw new NullPointerException("rowType == null");

        Method[] declaredMethods = rowType.getMethods();
        Arrays.sort(declaredMethods, METHOD_COMPARATOR);

        List<Method> methods = new ArrayList<>();
        List<String> headers = new ArrayList<>();
        for (Method declaratedMethod : declaredMethods) {
            if (declaratedMethod.getName().equals("getClass")){
                continue;
            }
            if (declaratedMethod.getParameterTypes().length > 0) continue;
            if (declaratedMethod.getReturnType() == void.class) continue;
            Matcher matcher = METHOD.matcher(declaratedMethod.getName());
            if (!matcher.matches()) continue;

            declaratedMethod.setAccessible(true);
            methods.add(declaratedMethod);
            headers.add(matcher.group(1));
        }

        int columnCount = methods.size();
        List<String[]> data = new ArrayList<>();
        for (T row : rows) {
            String[] rowData = new String[columnCount];
            for (int column = 0; column < columnCount; column++) {
                try {
                    rowData[column] = String.valueOf(methods.get(column).invoke(row));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            data.add(rowData);
        }

        String[] headerArray = headers.toArray(new String[headers.size()]);
        String[][] dataArray = data.toArray(new String[data.size()][]);
        return FlipTable.of(headerArray, dataArray);
    }
}
