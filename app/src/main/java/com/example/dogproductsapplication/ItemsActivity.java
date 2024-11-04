package com.example.dogproductsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemsActivity extends AppCompatActivity {
    private String [][] items =
            {
                    {"Item 1","AAAAAAA","","","1500/="},
                    {"Item 2","AAAAA","","","1500/="},
                    {"Item 3","AAAAA","","","1500/="},
                    {"Item 4","AAAAA","","","1500/="},
                    {"Item 5","AAAAA","","","1500/="},

    };

    private String [] item_details ={
            "Protein\n"+
                    "Protein\n"+
                    "Protein\n"+
                    "Protein\n"+
                    "Protein\n"+
                    "Protein\n"+
                    "Protein\n"+
                    "Protein",
            "Suger Level",
            "Suger Level",
            "Suger Level",
            "All Nutrients\n"+
                    "Iron\n"+
                    "Iron\n"+
                    "Iron\n"+
                    "Iron\n"+
                    "Iron\n"+
                    "Iron"
    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoTocart,buttonBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_items);

        btnGoTocart = findViewById(R.id.btncart);
        buttonBack = findViewById(R.id.btnback);
        listView = findViewById(R.id.listViewlt);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ItemsActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();

        for (int i=0;i<items.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",items[i][0]);
            item.put("line2",items[i][1]);
            item.put("line3",items[i][2]);
            item.put("line4",items[i][3]);
            item.put("line5","Price : "+items[i][4]+" /=");
            list.add(item);
        }


        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(ItemsActivity.this,ItemDetailsActivity.class);
                it.putExtra("text1",items[i][0]);
                it.putExtra("text2",item_details[i]);
                it.putExtra("text3",items[i][4]);
                startActivity(it);

            }
        });

        btnGoTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ItemsActivity.this,CartItemActivity.class));
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}