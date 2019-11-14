package com.example.shamim.weaponzeroing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class AddNewRecord extends AppCompatActivity {

    Button zero_wpn, b_image, b_incl_picture;
    TextView textView_v_error, textView_h_error, textView_firers_error, textView_comment, t_grouping;
    double dist_check = 9999;
    int short_i1, short_i2;
    int no_of_inmact = 5;
    double bull_x, bull_y;
    public double v_error, h_error, grouping;
    double x[] = {1, 1, 3, 5, 12};    // test case
    double y[] = {1, 4, 7, 2, 5};    // test case
    double f_errer_x[] = x;
    double f_errer_y[] = y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_record);

        zero_wpn = (Button) findViewById(R.id.zero_wpn);
        b_image = (Button) findViewById(R.id.b_image);      // loading image
        b_incl_picture = (Button) findViewById(R.id.b_incl_picture);
        textView_v_error = (TextView) findViewById(R.id.textView_v_error);
        textView_h_error = (TextView) findViewById(R.id.textView_h_error);
        zero_wpn = (Button) findViewById(R.id.zero_wpn);
        t_grouping = (TextView) findViewById(R.id.t_grouping);


        b_incl_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process_page();
            }
        });

        b_image.setOnClickListener(new View.OnClickListener() {    // loading image
            @Override
            public void onClick(View v) {

                calculate_error();
                firers_error();
            }
        });

        zero_wpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_for_zeroing();
            }
        });
    }

    public void process_page() {
        Intent intent = new Intent(this, NewImage.class);
        startActivity(intent);

    }

    public void go_for_zeroing() {
        String v_st = Double.toString(v_error);
        String h_st = Double.toString(h_error);
        Intent intent = new Intent(this,zeroingwpn.class);
        intent.putExtra("pass_v_error", v_st);
        intent.putExtra("pass_h_error", h_st);
        startActivity(intent);

    }

    public void calculate_error() {
        int temp = 0;
        double dst;
        for (int i = 0; i < no_of_inmact; i++)
            temp = temp + i;
        double[] dist = new double[temp];


        int count = 0;
        grouping = 0;
        for (int i = 0; i < no_of_inmact; i++) {
            for (int j = i + 1; j < no_of_inmact; j++) {
                dist[count] = Math.sqrt(Math.pow((x[i] - x[j]), 2) + Math.pow((y[i] - y[j]), 2));

                if (dist[count] <= dist_check) {
                    short_i1 = i;
                    short_i2 = j;
                    dist_check = dist[count];

                }
                if (grouping < dist[count]) {
                    grouping = dist[count];
                }
            }
        }

        swap(x[0], x[short_i1]);
        swap(x[1], x[short_i2]);
        swap(y[0], y[short_i1]);
        swap(y[1], y[short_i2]);
        dist_check = 9999;

        for (int i = 0; i < no_of_inmact - 2; i++) {
            x[i + 1] = ((x[i] * (i + 1)) + x[i + 1]) / i + 2;
            y[i + 1] = ((y[i] * (i + 1)) + y[i + 1]) / i + 2;


            for (int j = i + 2; j < no_of_inmact - 1; j++) {

                dst = Math.sqrt(Math.pow((x[i + 1] - x[j]), 2) + Math.pow((y[i + 1] - y[j]), 2));
                if (dst < dist_check) {
                    dist_check = dst;
                    short_i2 = j;
                }

            }

            swap(x[i + 1], x[short_i2]);
            swap(y[i + 1], y[short_i2]);

        }


        h_error = x[no_of_inmact - 1] - bull_x;
        v_error = y[no_of_inmact - 1] - bull_y;


        textView_h_error.setText("V Error: : " + v_error);

        textView_v_error.setText("H Error: " + h_error);
        t_grouping.setText("Grouping: " + Math.round(grouping));


    }


    public void firers_error() {
        for (int i = 0; i < no_of_inmact; i++) {
//            textView_v_error.setText("" + f_errer_x[i]);
//            System.out.println("" + f_errer_x[i]);

        }


    }

    public void swap(double a, double b) {
        double temp = 0;

        temp = a;
        a = b;
        b = temp;
    }


}
