package com.example.annuaire.controller;

import static android.Manifest.permission.CALL_PHONE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.annuaire.R;
import com.example.annuaire.menu_app;
import com.example.annuaire.model.SpinnerItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public abstract class GeneralMethode<T> {

    static aucunResultatAdapter aucunResultat;
    public static void viderRecheche(EditText search){
        search.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if(event.getAction() == MotionEvent.ACTION_UP) {
                if(event.getRawX() >= (search.getRight() - search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    // your action here
                    search.setText("");
                    return true;
                }
            }
            return false;
        });
    }
    public static <T> ArrayList<T> selectFromTable(Class<T> type, String tableName, List<String> columnNames, String clauseWhere) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<T> result = new ArrayList<>();
        try {
            String columns = String.join(",", columnNames);
            String sql = "SELECT " + columns + " FROM " + tableName;
            if(!clauseWhere.equals("")){
                sql+=" where "+  clauseWhere;
            }
            System.out.println(sql);

            stmt = DataBaseConnection.getInstance().prepareStatement(sql);
            rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                T instance = type.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(columnName);
                    Field field = type.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(instance, value);
                }
                result.add(instance);
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static <T> ArrayList<T> filter(String text, ArrayList<T> originalList) {
        ArrayList<T> filteredList = new ArrayList<>();
        for (T item : originalList) {
            Class<?> clazz = item.getClass();
            Field[] fields = clazz.getDeclaredFields();
            boolean containsText = false;
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(item);
                    if (value != null && value.toString().toLowerCase().contains(text.toLowerCase())) {
                        containsText = true;
                        break;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (containsText) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }




    public static<T> void UpdateAdapterREcherche(EditText search, ArrayList<T> avantFiltre, RecyclerView recyclerView, Function<ArrayList<T>, RecyclerView.Adapter> adapterFunction) {
        ArrayList<T> filteredList =GeneralMethode.filter(search.getText().toString(),avantFiltre );
        if (filteredList == null || filteredList.isEmpty()) {
            aucunResultat = new aucunResultatAdapter();
            recyclerView.setAdapter(aucunResultat);

        } else {
            RecyclerView.Adapter adapter = adapterFunction.apply(filteredList);
            recyclerView.setAdapter(adapter);
        }
    }


    public static<T> void LoadAdapterContent(EditText search, ArrayList<T> avantFiltre, RecyclerView recyclerView, Function<ArrayList<T>, RecyclerView.Adapter> adapterFunction) {
        UpdateAdapterREcherche(search, avantFiltre, recyclerView, adapterFunction) ;

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                UpdateAdapterREcherche(search, avantFiltre, recyclerView, adapterFunction) ;
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }

        });



    }






    public static  void ConsulterSociete(ImageView more, Context context,   RecyclerView.ViewHolder holder,RecyclerView.Adapter adapter,
                                         Function<Integer, Void> EndrepriseID){
       // more.setPaintFlags(more.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        more.setOnClickListener(v -> {
            int p = holder.getAdapterPosition();
            if (p != RecyclerView.NO_POSITION) {
                adapter.notifyDataSetChanged();
                EndrepriseID.apply(p);
                Intent intent= new Intent(context, menu_app.class);
                context.startActivity(intent);
            }
        });
    }

    public static <T> void mailingAction(FloatingActionButton FABMail, View itemView, ArrayList<?> data, GeneralAdaptar<T>.ViewHolder holder, Function<T, String> getEmailFunction) {
        FABMail.setOnClickListener(view -> {
            int position=0;
            if(holder!=null) {
                position = holder.getAdapterPosition();
            }
            String email = getEmailFunction.apply((T) data.get(position));
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"+email));
            itemView.getContext().startActivity(intent);
        });
    }



    public static <T> void ActionCall(FloatingActionButton FABCall,
                                      View itemView, ArrayList<?> general,
                                      GeneralAdaptar<T>.ViewHolder holder, Function<T, String> getCallFunction){
        int PERMISSION_CODE = 100;
        FABCall.setOnClickListener(view -> {
            int position=0;


            if(holder!=null) {
                position = holder.getAdapterPosition();
            }
            String telephone = getCallFunction.apply((T) general.get(position));

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + telephone));

            if(ContextCompat.checkSelfPermission(itemView.getContext(), CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions((Activity) itemView.getContext(), new String[] {CALL_PHONE}, PERMISSION_CODE);
            }
            else {
                itemView.getContext().startActivity(intent);

            }




        });
    }



    public static <T> void ActionLinkTO(TextView Lien,
                                        RecyclerView.ViewHolder holder,RecyclerView.Adapter adapter,
                                        Function<Integer, Void> onClickTo) {
        Lien.setOnClickListener(view -> {
            int p = holder.getAdapterPosition();
            if (p != RecyclerView.NO_POSITION) {
                adapter.notifyDataSetChanged();
                onClickTo.apply(p);
            }
        });
    }


    @SuppressLint("SuspiciousIndentation")
    public static <T>  void ActionMoreDetailsAnnuaire(TextView more, ArrayList<?> general, GeneralAdaptar<T>.ViewHolder holder, Function<T, String> moreDetails ){
        more.setPaintFlags(more.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        more.setOnClickListener(v -> {
            String message ="Aucun résultat à afficher.";
            int position=0;
            View customLayout = LayoutInflater.from(v.getContext()).inflate(R.layout.custom_dialog, null);
            AlertDialog dialog = new AlertDialog.Builder(v.getContext(), R.style.CustomAlertDialog)
                    .setView(customLayout)
                    .create();
            dialog.show();
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = 600;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(lp);
            TextView messageView = customLayout.findViewById(R.id.dialog_message);

            if(holder!=null) {
                position = holder.getAdapterPosition();
            }

            if(general.size()!=0)
                message = moreDetails.apply((T) general.get(position));

            SpannableStringBuilder sb = new SpannableStringBuilder();
            String[] lines = message.split("\n");
            for (String line : lines) {
                int index = line.indexOf(":");
                if (index != -1) {
                    String label = line.substring(0, index);
                    String value = line.substring(index + 1).trim();
                    String labeledValue = label + ": " + value + "\n";
                    SpannableString spannableLabeledValue = new SpannableString(labeledValue);
                    spannableLabeledValue.setSpan(new StyleSpan(Typeface.BOLD), 0, label.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    sb.append(spannableLabeledValue);
                } else {
                    sb.append(line);
                }
            }
            messageView.setText(sb);
            ImageView close = customLayout.findViewById(R.id.close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        });


    }


    public static<T> SpannableStringBuilder  showTableDataInDialog(List<String> columnNames, T data, Class<T> type) {
        SpannableStringBuilder sb = new SpannableStringBuilder ();
        for (String columnName : columnNames) {
            try {
                Field field = type.getDeclaredField(columnName);
                field.setAccessible(true);
                String capitalizedColumnName = columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                String sourceString = "<bold>" + capitalizedColumnName + "</bold> ";
                sb.append("\n").append(HtmlCompat.fromHtml(sourceString,HtmlCompat.FROM_HTML_MODE_LEGACY)).append(": ").append(field.get(data).toString());
                sb.setSpan(new StyleSpan(Typeface.BOLD), sb.length() - capitalizedColumnName.length(), sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.append("\n");

        return  sb;

    }


    public static <T> void RemplirListAfterSelection(Spinner spinner, Spinner spinneraremplir, String premierElement, Function<Integer, ArrayList<T>> onClickTo) {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItem selectedItem = (SpinnerItem) parent.getSelectedItem();
                int selectedID = selectedItem.getID();
                ArrayList<T> liste= onClickTo.apply(selectedID);
                GeneralMethode.RemplirList(spinneraremplir, liste, premierElement); // Call the method with the spinner, direction list, and the first element string

                // Do something with the selected ID
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }


    public static <T> void RemplirList(Spinner spinner, List<T> dataList, String premierElement) {
        List<SpinnerItem> list = new ArrayList<>();
        list.add(new SpinnerItem(0, premierElement)); // add the first element
        for (T data : dataList) {
            try {
                Method method = data.getClass().getMethod("getIntitule");
                Method methodId = data.getClass().getMethod("getId");

                String displayName = (String) method.invoke(data);
                int displayID = (int) methodId.invoke(data);

                list.add(new SpinnerItem(displayID, displayName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<SpinnerItem> adapter = new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }


    public static <T> void actionSpinner(Spinner spinner, Function<Integer, Void> onClickTo) {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItem selectedItem = (SpinnerItem) parent.getSelectedItem();
                int selectedID = selectedItem.getID();
                onClickTo.apply(selectedID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }




    public static  void ActionMore(TextView more, TextView desc, TextView title){
        more.setPaintFlags(more.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        more.setOnClickListener(v -> {
            String text =  desc.getText().toString();
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setMessage(text)
                    .setTitle( title.getText())
                    .setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }









/*



    public static <T> void ActionLinkTO(TextView Lien,
                                        RecyclerView.ViewHolder holder,RecyclerView.Adapter adapter,
                                        Function<Integer, Void> onClickTo) {
        Lien.setOnClickListener(view -> {
                int p = holder.getAdapterPosition();
                if (p != RecyclerView.NO_POSITION) {
                    adapter.notifyDataSetChanged();
                    onClickTo.apply(p);
                }
        });
    }











    static aucunResultatAdapter aucunResultat;

    public static<T> void LoadAdapterContent(EditText search,ArrayList<T> listeResult, RecyclerView recyclerView, Function<ArrayList<T>, RecyclerView.Adapter> adapterFunction) {

        if (listeResult == null || listeResult.isEmpty()) {
            aucunResultat = new aucunResultatAdapter();
            recyclerView.setAdapter(aucunResultat);

        } else {
            RecyclerView.Adapter adapter = adapterFunction.apply(listeResult);
            recyclerView.setAdapter(adapter);
            GeneralMethode.recherche(search, listeResult, recyclerView, adapterFunction);
        }
    }














*/


}
