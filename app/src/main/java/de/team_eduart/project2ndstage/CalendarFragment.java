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
    TextView calendarWeek,calenderMo,calenderThue,calenderWe,calenderTh,calenderFr;
    String activeWeek, activeWeekDay;
    Calendar _Calendar;
    int tweek, week, year, day, month, monday, thuesday, wensday, thursday, friday, monthThuesday, monthWensday, monthThursday, monthFriday;
    int yearThuesday, yearWensday, yearThursday, yearFriday,yearAct, minMonth, x;

    /** Called when the activity is first created. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View currentView = inflater.inflate(R.layout.fragment_calendar, container, false);

        _Calendar       = Calendar.getInstance(Locale.getDefault());
        week            = _Calendar.get(Calendar.WEEK_OF_YEAR);
        day             = _Calendar.get(Calendar.DAY_OF_MONTH);
        month           = _Calendar.get(Calendar.MONTH);
        year            = _Calendar.get(Calendar.YEAR);
        activeWeekDay   = _Calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        tweek = week;
        month++;
        yearThuesday  = year;
        yearWensday   = year;
        yearThursday  = year;
        yearFriday    = year;
        monthThuesday = month;
        monthWensday  = month;
        monthThursday = month;
        monthFriday   = month;
        yearAct = year;



        nextWeek     = (Button) currentView.findViewById(R.id.nextWeekFrag);
        prevWeek     = (Button) currentView.findViewById(R.id.prevWeekFrag);
        calendarWeek = (TextView) currentView.findViewById(R.id.calendarWeekFrag);
        calenderMo   = (TextView) currentView.findViewById(R.id.dateMoFrag);
        calenderThue = (TextView) currentView.findViewById(R.id.dateThueFrag);
        calenderWe   = (TextView) currentView.findViewById(R.id.dateWeFrag);
        calenderTh   = (TextView) currentView.findViewById(R.id.dateThFrag);
        calenderFr   = (TextView) currentView.findViewById(R.id.dateFrFrag);


        if(activeWeekDay.equals("Montag")){monday = day;
        }else if(activeWeekDay.equals("Dienstag")){monday = day - 1;
        }else if(activeWeekDay.equals("Mittwoch")){monday = day - 2;
        }else if(activeWeekDay.equals("Donnerstag")){monday = day - 3;
        }else if(activeWeekDay.equals("Freitag")){monday = day - 4;
        }else if(activeWeekDay.equals("Samstag")){monday = day - 5;
        }else if(activeWeekDay.equals("Sonntag")){monday = day - 6;}

        thuesday = monday + 1;
        wensday  = monday + 2;
        thursday = monday + 3;
        friday = monday + 4;

        if (tweek == week) {
            activeWeek     = Integer.toString(week);
            calendarWeek.setText(activeWeek + ". Kalernderwoche \n (aktuell)");
            calenderMo.setText(Integer.toString(monday) + "." + Integer.toString(month) + "." + Integer.toString(year));
            calenderThue.setText(Integer.toString(thuesday) + "." + Integer.toString(month) + "." + Integer.toString(year));
            calenderWe.setText(Integer.toString(wensday) + "." + Integer.toString(month) + "." + Integer.toString(year));
            calenderTh.setText(Integer.toString(thursday) + "." + Integer.toString(month) + "." + Integer.toString(year));
            calenderFr.setText(Integer.toString(friday) + "." + Integer.toString(month) + "." + Integer.toString(year));
        }



            nextWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                    week++;
                    if (week > 52){week = 1;}
                    activeWeek     = Integer.toString(week);

                     if (tweek == week) {
                        if (year == yearAct) {
                            calendarWeek.setText(activeWeek + ".Kalenderwoche \n (aktuell)");
                        }else{calendarWeek.setText(activeWeek + ".Kalenderwoche");}
                     } else {
                        calendarWeek.setText(activeWeek + ".Kalenderwoche");
                       }

                    monday      = monday   + 7;
                    thuesday    = thuesday + 7;
                    wensday     = wensday  + 7;
                    thursday    = thursday + 7;
                    friday      = friday   + 7;

                    if (monthThursday < monthFriday){
                        x = monthThuesday;
                        if(monthWensday < monthThursday){
                            x = monthWensday;
                            if(monthThuesday < monthWensday){
                                x = monthThuesday;
                                if(month < monthThuesday){
                                    x = month;
                                }
                            }
                        }
                    }else {x = monthFriday;}


                    if (x == 1){
                        if (monthThursday == 12) {x = 12;
                        }else if (monthWensday == 12){x = 12;
                        }else if (monthThuesday == 12){x = 12;
                        }else if (month == 12){x = 12;}
                    }

                    minMonth = x;

                    if (minMonth == 1) {
                        if (friday > 31){
                            monthFriday = month + 1;
                            friday = friday - 31;
                        }
                        if (thursday > 31){
                            monthThursday = month + 1;
                            thursday = thursday - 31;
                        }
                        if (wensday > 31){
                            monthWensday = month + 1;
                            wensday = wensday - 31;
                        }
                        if(thuesday > 31){
                            monthThuesday = month + 1;
                            thuesday = thuesday - 31;
                        }
                        if(monday > 31){
                            month++;
                            monday = monday - 31;
                        }

                    }else if (minMonth == 2){
                        if (year == 2012 || year == 2016 || year == 2020 || year == 2024 || year == 2028 || year == 2032 || year == 2036 || year == 2040){
                            if (friday > 29){
                                monthFriday = month + 1;
                                friday = friday - 29;
                            }
                            if (thursday > 29){
                                monthThursday = month + 1;
                                thursday = thursday - 29;
                            }
                            if (wensday > 29){
                                monthWensday = month + 1;
                                wensday = wensday - 29;
                            }
                            if(thuesday > 29){
                                monthThuesday = month + 1;
                                thuesday = thuesday - 29;
                            }
                            if(monday > 29){
                                month++;
                                monday = monday - 29;
                            }

                        }else{
                            if (friday > 28){
                                monthFriday = month + 1;
                                friday = friday - 28;
                            }
                            if (thursday > 28){
                                monthThursday = month + 1;
                                thursday = thursday - 28;
                            }
                            if (wensday > 28){
                                monthWensday = month + 1;
                                wensday = wensday - 28;
                            }
                            if(thuesday > 28){
                                monthThuesday = month + 1;
                                thuesday = thuesday - 28;
                            }
                            if(monday > 28){
                                month++;
                                monday = monday - 28;
                            }

                        }
                    }else if (minMonth == 3){
                        if (friday > 31){
                            monthFriday = month + 1;
                            friday = friday - 31;
                        }
                        if (thursday > 31){
                            monthThursday = month + 1;
                            thursday = thursday - 31;
                        }
                        if (wensday > 31){
                            monthWensday = month + 1;
                            wensday = wensday - 31;
                        }
                        if(thuesday > 31){
                            monthThuesday = month + 1;
                            thuesday = thuesday - 31;
                        }
                        if(monday > 31){
                            month++;
                            monday = monday - 31;
                        }
                    }else if (minMonth == 4){
                        if (friday > 30){
                            monthFriday = month + 1;
                            friday = friday - 30;
                        }
                        if (thursday > 30){
                            monthThursday = month + 1;
                            thursday = thursday - 30;
                        }
                        if (wensday > 30){
                            monthWensday = month + 1;
                            wensday = wensday - 30;
                        }
                        if(thuesday > 30){
                            monthThuesday = month + 1;
                            thuesday = thuesday - 30;
                        }
                        if(monday > 30){
                            month++;
                            monday = monday - 30;
                        }
                    }else if (minMonth == 5){
                        if (friday > 31){
                            monthFriday = month + 1;
                            friday = friday - 31;
                        }
                        if (thursday > 31){
                            monthThursday = month + 1;
                            thursday = thursday - 31;
                        }
                        if (wensday > 31){
                            monthWensday = month + 1;
                            wensday = wensday - 31;
                        }
                        if(thuesday > 31){
                            monthThuesday = month + 1;
                            thuesday = thuesday - 31;
                        }
                        if(monday > 31){
                            month++;
                            monday = monday - 31;
                        }
                    }else if (minMonth == 6){
                        if (friday > 30){
                            monthFriday = month + 1;
                            friday = friday - 30;
                        }
                        if (thursday > 30){
                            monthThursday = month + 1;
                            thursday = thursday - 30;
                        }
                        if (wensday > 30){
                            monthWensday = month + 1;
                            wensday = wensday - 30;
                        }
                        if(thuesday > 30){
                            monthThuesday = month + 1;
                            thuesday = thuesday - 30;
                        }
                        if(monday > 30){
                            month++;
                            monday = monday - 30;
                        }
                    }else if (minMonth == 7){
                        if (friday > 31){
                            monthFriday = month + 1;
                            friday = friday - 31;
                        }
                        if (thursday > 31){
                            monthThursday = month + 1;
                            thursday = thursday - 31;
                        }
                        if (wensday > 31){
                            monthWensday = month + 1;
                            wensday = wensday - 31;
                        }
                        if(thuesday > 31){
                            monthThuesday = month + 1;
                            thuesday = thuesday - 31;
                        }
                        if(monday > 31){
                            month++;
                            monday = monday - 31;
                        }
                    }else if (minMonth == 8){
                        if (friday > 31){
                            monthFriday = month + 1;
                            friday = friday - 31;
                        }
                        if (thursday > 31){
                            monthThursday = month + 1;
                            thursday = thursday - 31;
                        }
                        if (wensday > 31){
                            monthWensday = month + 1;
                            wensday = wensday - 31;
                        }
                        if(thuesday > 31){
                            monthThuesday = month + 1;
                            thuesday = thuesday - 31;
                        }
                        if(monday > 31){
                            month++;
                            monday = monday - 31;
                        }
                    }else if (minMonth == 9){
                        if (friday > 30){
                            monthFriday = month + 1;
                            friday = friday - 30;
                        }
                        if (thursday > 30){
                            monthThursday = month + 1;
                            thursday = thursday - 30;
                        }
                        if (wensday > 30){
                            monthWensday = month + 1;
                            wensday = wensday - 30;
                        }
                        if(thuesday > 30){
                            monthThuesday = month + 1;
                            thuesday = thuesday - 30;
                        }
                        if(monday > 30){
                            month++;
                            monday = monday - 30;
                        }
                    }else if (minMonth == 10){
                        if (friday > 31){
                            monthFriday = month + 1;
                            friday = friday - 31;
                        }
                        if (thursday > 31){
                            monthThursday = month + 1;
                            thursday = thursday - 31;
                        }
                        if (wensday > 31){
                            monthWensday = month + 1;
                            wensday = wensday - 31;
                        }
                        if(thuesday > 31){
                            monthThuesday = month + 1;
                            thuesday = thuesday - 31;
                        }
                        if(monday > 31){
                            month++;
                            monday = monday - 31;
                        }
                    }else if (minMonth == 11){
                        if (friday > 30){
                            monthFriday = month + 1;
                            friday = friday - 30;
                        }
                        if (thursday > 30){
                            monthThursday = month + 1;
                            thursday = thursday - 30;
                        }
                        if (wensday > 30){
                            monthWensday = month + 1;
                            wensday = wensday - 30;
                        }
                        if(thuesday > 30){
                            monthThuesday = month + 1;
                            thuesday = thuesday - 30;
                        }
                        if(monday > 30){
                            month++;
                            monday = monday - 30;
                        }
                    }else if (minMonth == 12){
                        if (friday > 31){
                            monthFriday = 1;
                            friday = friday - 31;
                            yearFriday++;
                        }
                        if (thursday > 31){
                            monthThursday = 1;
                            thursday = thursday - 31;
                            yearThursday++;
                        }
                        if (wensday > 31){
                            monthWensday = 1;
                            wensday = wensday - 31;
                            yearWensday++;
                        }
                        if(thuesday > 31){
                            monthThuesday = 1;
                            thuesday = thuesday - 31;
                            yearThuesday++;
                        }
                        if(monday > 31){
                            month = 1;
                            monday = monday - 31;
                            year++;
                        }
                    }


                    calenderMo.setText(Integer.toString(monday) + "." + Integer.toString(month) + "." + Integer.toString(year));
                    calenderThue.setText(Integer.toString(thuesday) + "." + Integer.toString(monthThuesday) + "." + Integer.toString(yearThuesday));
                    calenderWe.setText(Integer.toString(wensday) + "." + Integer.toString(monthWensday) + "." + Integer.toString(yearWensday));
                    calenderTh.setText(Integer.toString(thursday) + "." + Integer.toString(monthThursday) + "." + Integer.toString(yearThursday));
                    calenderFr.setText(Integer.toString(friday) + "." + Integer.toString(monthFriday) + "." + Integer.toString(yearFriday));


                }
            });

            prevWeek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    week--;
                    if (week < 1) {
                        week = 52;
                    }
                    activeWeek = Integer.toString(week);
                    if (tweek == week) {
                        if (year == yearAct) {
                            calendarWeek.setText(activeWeek + ".Kalenderwoche \n (aktuell)");
                        }else{calendarWeek.setText(activeWeek + ".Kalenderwoche");}
                    } else {
                        calendarWeek.setText(activeWeek + ".Kalenderwoche");
                    }

                    monday      = monday   - 7;
                    thuesday    = thuesday - 7;
                    wensday     = wensday  - 7;
                    thursday    = thursday - 7;
                    friday      = friday   - 7;

                    if (month < monthThuesday){
                        x = monthThuesday;
                        if(monthThuesday < monthWensday){
                            x = monthWensday;
                            if(monthWensday < monthThursday){
                                x = monthThursday;
                                if(monthThursday < monthFriday){
                                    x = monthFriday;
                                }
                            }
                        }
                    }else {x = month;}


                    if (x == 12){
                        if (monthThursday == 1){x = 1;}
                        if (monthWensday == 1){x = 1;}
                        if (monthThuesday == 1){x = 1;}
                        if (month == 1){x = 1;}
                    }

                    minMonth = x;

                    if (monday < 1 || friday < 1) {
                        if (minMonth == 1) {
                            monday = 31 + monday;
                            month = 12;
                            year--;
                            yearThuesday  = year + 1;
                            yearWensday   = year + 1;
                            yearThursday  = year + 1;
                            yearFriday    = year + 1;
                            monthThuesday = 1;
                            monthWensday  = 1;
                            monthThursday = 1;
                            monthFriday   = 1;
                            if(thuesday < 1){
                                thuesday = 31 + thuesday;
                                monthThuesday = month;
                                yearThuesday = year;
                            }
                            if (wensday < 1){
                                wensday = 31 + wensday;
                                monthWensday = month;
                                yearWensday = year;
                            }
                            if(thursday < 1){
                                thursday = 31 + thursday;
                                monthThursday = month;
                                yearThursday = year;
                            }
                            if(friday < 1){
                                friday = 31 + friday;
                                monthFriday = month;
                                yearFriday = year;
                            }
                        } else if (minMonth == 2) {
                                monday = 31 + monday;
                                month = 1;
                                monthThuesday = month + 1;
                                monthWensday  = month + 1;
                                monthThursday = month + 1;
                                monthFriday   = month + 1;
                                if(thuesday < 1){
                                    thuesday = 31 + thuesday;
                                    monthThuesday = month;
                                }
                                if (wensday < 1){
                                    wensday = 31 + wensday;
                                    monthWensday = month;
                                }
                                if(thursday < 1){
                                    thursday = 31 + thursday;
                                    monthThursday = month;
                                }
                                if(friday < 1){
                                friday = 31 + friday;
                                monthFriday = month;
                                yearFriday = year;
                                }
                        } else if (minMonth == 3) {
                            if (year == 2012 || year == 2016 || year == 2020 || year == 2024 || year == 2028 || year == 2032 || year == 2036 || year == 2040) {
                                monday = 29 + monday;
                                month = 2;
                                monthThuesday = month + 1;
                                monthWensday = month + 1;
                                monthThursday = month + 1;
                                monthFriday = month + 1;
                                if (thuesday < 1) {
                                    thuesday = 29 + thuesday;
                                    monthThuesday = month;
                                }
                                if (wensday < 1) {
                                    wensday = 29 + wensday;
                                    monthWensday = month;
                                }
                                if (thursday < 1) {
                                    thursday = 29 + thursday;
                                    monthThursday = month;
                                }
                                if(friday < 1){
                                    friday = 29 + friday;
                                    monthFriday = month;
                                    yearFriday = year;
                                }
                            } else {
                                monday = 28 + monday;
                                month = 2;
                                monthThuesday = month + 1;
                                monthWensday = month + 1;
                                monthThursday = month + 1;
                                monthFriday = month + 1;
                                if (thuesday < 1) {
                                    thuesday = 28 + thuesday;
                                    monthThuesday = month;
                                }
                                if (wensday < 1) {
                                    wensday = 28 + wensday;
                                    monthWensday = month;
                                }
                                if (thursday < 1) {
                                    thursday = 28 + thursday;
                                    monthThursday = month;
                                }
                                if(friday < 1){
                                    friday = 28 + friday;
                                    monthFriday = month;
                                    yearFriday = year;
                                }
                            }
                        }else if (minMonth == 4) {
                            monday = 31 + monday;
                            month = 3;
                            monthThuesday = month + 1;
                            monthWensday  = month + 1;
                            monthThursday = month + 1;
                            monthFriday   = month + 1;
                            if(thuesday < 1){
                                thuesday = 31 + thuesday;
                                monthThuesday = month;
                            }
                            if (wensday < 1){
                                wensday = 31 + wensday;
                                monthWensday = month;
                            }
                            if(thursday < 1){
                                thursday = 31 + thursday;
                                monthThursday = month;
                            }
                            if(friday < 1){
                                friday = 31 + friday;
                                monthFriday = month;
                                yearFriday = year;
                            }
                        } else if (minMonth == 5) {
                            monday = 30 + monday;
                            month = 4;
                            monthThuesday = month + 1;
                            monthWensday  = month + 1;
                            monthThursday = month + 1;
                            monthFriday   = month + 1;
                            if(thuesday < 1){
                                thuesday = 30 + thuesday;
                                monthThuesday = month;
                            }
                            if (wensday < 1){
                                wensday = 30 + wensday;
                                monthWensday = month;
                            }
                            if(thursday < 1){
                                thursday = 30 + thursday;
                                monthThursday = month;
                            }
                            if(friday < 1){
                                friday = 30 + friday;
                                monthFriday = month;
                                yearFriday = year;
                            }
                        } else if (minMonth == 6) {
                            monday = 31 + monday;
                            month = 5;
                            monthThuesday = month + 1;
                            monthWensday  = month + 1;
                            monthThursday = month + 1;
                            monthFriday   = month + 1;
                            if(thuesday < 1){
                                thuesday = 31 + thuesday;
                                monthThuesday = month;
                            }
                            if (wensday < 1){
                                wensday = 31 + wensday;
                                monthWensday = month;
                            }
                            if(thursday < 1){
                                thursday = 31 + thursday;
                                monthThursday = month;
                            }
                            if(friday < 1){
                                friday = 31 + friday;
                                monthFriday = month;
                                yearFriday = year;
                            }
                        } else if (minMonth == 7) {
                            monday = 30 + monday;
                            month = 6;
                            monthThuesday = month + 1;
                            monthWensday  = month + 1;
                            monthThursday = month + 1;
                            monthFriday   = month + 1;
                            if(thuesday < 1){
                                thuesday = 30 + thuesday;
                                monthThuesday = month;
                            }
                            if (wensday < 1){
                                wensday = 30 + wensday;
                                monthWensday = month;
                            }
                            if(thursday < 1){
                                thursday = 30 + thursday;
                                monthThursday = month;
                            }
                            if(friday < 1){
                                friday = 30 + friday;
                                monthFriday = month;
                                yearFriday = year;
                            }
                        } else if (minMonth == 8) {
                            monday = 31 + monday;
                            month = 7;
                            monthThuesday = month + 1;
                            monthWensday  = month + 1;
                            monthThursday = month + 1;
                            monthFriday   = month + 1;
                            if(thuesday < 1){
                                thuesday = 31 + thuesday;
                                monthThuesday = month;
                            }
                            if (wensday < 1){
                                wensday = 31 + wensday;
                                monthWensday = month;
                            }
                            if(thursday < 1){
                                thursday = 31 + thursday;
                                monthThursday = month;
                            }
                            if(friday < 1){
                                friday = 31 + friday;
                                monthFriday = month;
                                yearFriday = year;
                            }
                        } else if (minMonth == 9) {
                            monday = 31 + monday;
                            month = 8;
                            monthThuesday = month + 1;
                            monthWensday  = month + 1;
                            monthThursday = month + 1;
                            monthFriday   = month + 1;
                            if(thuesday < 1){
                                thuesday = 31 + thuesday;
                                monthThuesday = month;
                            }
                            if (wensday < 1){
                                wensday = 31 + wensday;
                                monthWensday = month;
                            }
                            if(thursday < 1){
                                thursday = 31 + thursday;
                                monthThursday = month;
                            }
                            if(friday < 1){
                                friday = 31 + friday;
                                monthFriday = month;
                                yearFriday = year;
                            }
                        } else if (minMonth == 10) {
                            monday = 30 + monday;
                            month = 9;
                            monthThuesday = month + 1;
                            monthWensday  = month + 1;
                            monthThursday = month + 1;
                            monthFriday   = month + 1;
                            if(thuesday < 1){
                                thuesday = 30 + thuesday;
                                monthThuesday = month;
                            }
                            if (wensday < 1){
                                wensday = 30 + wensday;
                                monthWensday = month;
                            }
                            if(thursday < 1){
                                thursday = 30 + thursday;
                                monthThursday = month;
                            }
                            if(friday < 1){
                                friday = 30 + friday;
                                monthFriday = month;
                                yearFriday = year;
                            }
                        } else if (minMonth == 11) {
                            monday = 31 + monday;
                            month = 10;
                            monthThuesday = month + 1;
                            monthWensday  = month + 1;
                            monthThursday = month + 1;
                            monthFriday   = month + 1;
                            if(thuesday < 1){
                                thuesday = 31 + thuesday;
                                monthThuesday = month;
                            }
                            if (wensday < 1){
                                wensday = 31 + wensday;
                                monthWensday = month;
                            }
                            if(thursday < 1){
                                thursday = 31 + thursday;
                                monthThursday = month;
                            }
                            if(friday < 1){
                                friday = 31 + friday;
                                monthFriday = month;
                                yearFriday = year;
                            }
                        } else if (minMonth == 12) {
                            monday = 30 + monday;
                            month = 11;
                            monthThuesday = month + 1;
                            monthWensday  = month + 1;
                            monthThursday = month + 1;
                            monthFriday   = month + 1;
                            if(thuesday < 1){
                                thuesday = 30 + thuesday;
                                monthThuesday = month;
                            }
                            if (wensday < 1){
                                wensday = 30 + wensday;
                                monthWensday = month;
                            }
                            if(thursday < 1){
                                thursday = 30 + thursday;
                                monthThursday = month;
                            }
                            if(friday < 1){
                                friday = 30 + friday;
                                monthFriday = month;
                                yearFriday = year;
                            }
                        }
                    }
                        calenderMo.setText(Integer.toString(monday) + "." + Integer.toString(month) + "." + Integer.toString(year));
                        calenderThue.setText(Integer.toString(thuesday) + "." + Integer.toString(monthThuesday) + "." + Integer.toString(yearThuesday));
                        calenderWe.setText(Integer.toString(wensday) + "." + Integer.toString(monthWensday) + "." + Integer.toString(yearWensday));
                        calenderTh.setText(Integer.toString(thursday) + "." + Integer.toString(monthThursday) + "." + Integer.toString(yearThursday));
                        calenderFr.setText(Integer.toString(friday) + "." + Integer.toString(monthFriday) + "." + Integer.toString(yearFriday));

                }
            });
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
