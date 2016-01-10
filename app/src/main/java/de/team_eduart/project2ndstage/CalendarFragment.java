package de.team_eduart.project2ndstage;


import android.app.Fragment;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Locale;


public class CalendarFragment extends Fragment implements OnClickListener {
    private static final String tag = "MyCalendarActivity";

    Button prevWeek;
    Button nextWeek;
    Button AddEvent;
    Button SaveEvent;
    TextView calendarWeek;
    View change;
    String activeWeek, activeWeekDate;
    Calendar _Calendar;
    int tweek, week, monday, thuesday, wensday, thurseday, friday, year, day, month, weekCount;

    /** Called when the activity is first created. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View currentView = inflater.inflate(R.layout.fragment_calendar, container, false);

        _Calendar = Calendar.getInstance(Locale.getDefault());
        week      = _Calendar.get(Calendar.WEEK_OF_YEAR);
        day       = _Calendar.get(Calendar.DAY_OF_MONTH);
        month     = _Calendar.get(Calendar.MONTH);
        year      = _Calendar.get(Calendar.YEAR);

        tweek = week;

        nextWeek = (Button) currentView.findViewById(R.id.nextWeekFrag);

        nextWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                week++;
                if (week > 52){week = 1;}
                activeWeek     = Integer.toString(week);

                if (tweek == week){
                calendarWeek.setText(activeWeek + ".Kalenderwoche \n (aktuell)");
                }
                else {calendarWeek.setText(activeWeek + ".Kalenderwoche");}
            }
        });

        prevWeek = (Button) currentView.findViewById(R.id.prevWeekFrag);

        prevWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                week--;
                if (week < 1) {
                    week = 52;
                }
                activeWeek = Integer.toString(week);
                if (tweek == week) {
                    calendarWeek.setText(activeWeek + ".Kalenderwoche \n (aktuell)");
                }
                else {calendarWeek.setText(activeWeek + ".Kalenderwoche");}
            }
        });


        if (tweek == week) {
            activeWeek     = Integer.toString(week);
            calendarWeek = (TextView) currentView.findViewById(R.id.calendarWeekFrag);
            calendarWeek.setText(activeWeek + ". Kalernderwoche \n (aktuell)");
        }

        return currentView;
    }


    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public void onClick(View view) {

    }

    public CalendarFragment() {
        // Required empty public constructor
    }



}
