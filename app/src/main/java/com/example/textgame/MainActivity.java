package com.example.textgame;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRec, buttonPlay, buttonTrade, buttonExit, button7, button8;
    private ScrollView scrollview;
    boolean play, wwt = true, trade, rec = true, cube = false;
    Trader trader = new Trader();
    Events event = new Events();
    Unit unit = new Unit(0, 0, 0);
    int d6, choice, history = 0, serv = 0, cube1 = 0, cube2 = 0, gold, a;
    String files = "", dateText, timeText, race;
    Random random = new Random();

    final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);

        scrollview = findViewById(R.id.ScrollView);

        buttonRec = findViewById(R.id.buttonRec);
        buttonRec.setOnClickListener(this);

        buttonPlay = findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);

        buttonTrade = findViewById(R.id.buttonTrade);
        buttonTrade.setOnClickListener(this);

        buttonExit = findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(this);

        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(this);

        button8 = findViewById(R.id.button8);
        button8.setOnClickListener(this);

        textView.setText(event.begin);
        button7.setEnabled(false);
        button8.setEnabled(false);
        buttonTrade.setEnabled(false);
        play = false;
    }

    @Override
    public void onClick(View v) {
        TextView textView = findViewById(R.id.textView);
        if (choice>10) {
            choice = 0;
        }
        if (cube) {
            switch (v.getId()) {
                case (R.id.button7):
                    button8.setEnabled(false);
                    d6 = random.nextInt(6) + 1;
                    button7.setText(Integer.toString(d6));
                    break;
                case (R.id.button8):
                    button7.setEnabled(false);
                    d6 = random.nextInt(6) + 1;
                    button8.setText(Integer.toString(d6));
                    break;
            }
            gold = random.nextInt(40) + random.nextInt((unit.lvl * 5 + 1)%100 + 1);
            event.d6(d6, gold);
            if (d6 % 2 == 0) {
                unit.hp -= 1;
                if (unit.hp > 0) {
                    unit.gold += gold;
                    unit.lvl += 2;
                }
            } else {
                unit.hp -= 2;
                if (unit.hp > 0) {
                    unit.lvl += 3;
                }
            }
            textView.append(event.cube);
            buttonRec.setEnabled(true);
            buttonPlay.setEnabled(true);
            buttonExit.setEnabled(true);
            buttonTrade.setEnabled(true);
            button7.setEnabled(true);
            button8.setEnabled(false);
            cube = false;
        } else if (trade) {
            switch (v.getId()) {
                case (R.id.button7):
                    d6 = 1;
                    event.Trade(unit.gold, d6);
                    textView.append(event.trade);
                    if (unit.gold >= 50) {
                        unit.hp += 1;
                        unit.gold -= 50;
                    }
                    button7.setText("Кубик");
                    button8.setText("Кубик");
                    buttonTrade.setText("Торг");
                    buttonRec.setEnabled(true);
                    buttonPlay.setEnabled(true);
                    buttonExit.setEnabled(true);
                    buttonTrade.setEnabled(true);
                    button7.setEnabled(true);
                    button8.setEnabled(false);
                    break;
                case (R.id.button8):
                    d6 = 2;
                    event.Trade(unit.gold, d6);
                    textView.append(event.trade);
                    if (unit.gold >= 100) {
                        unit.lvl += 1;
                        unit.gold -= 100;
                    }
                    button7.setText("Кубик");
                    button8.setText("Кубик");
                    buttonTrade.setText("Торг");
                    buttonRec.setEnabled(true);
                    buttonPlay.setEnabled(true);
                    buttonExit.setEnabled(true);
                    buttonTrade.setEnabled(true);
                    button7.setEnabled(true);
                    button8.setEnabled(false);
                    break;
                case (R.id.buttonTrade):
                    button7.setText("Кубик");
                    button8.setText("Кубик");
                    buttonTrade.setText("Торг");
                    buttonRec.setEnabled(true);
                    buttonPlay.setEnabled(true);
                    buttonExit.setEnabled(true);
                    buttonTrade.setEnabled(true);
                    button7.setEnabled(true);
                    button8.setEnabled(false);
                    break;
            }
            trade = false;
        } else {
            if (play) {
                buttonRec.setEnabled(true);
                buttonPlay.setEnabled(true);
                buttonExit.setEnabled(true);
                buttonTrade.setEnabled(true);
                button7.setEnabled(true);
                button8.setEnabled(false);
                switch (v.getId()) {
                    case (R.id.button7):
                        cube1 = random.nextInt(6) + 1;
                        button7.setText(Integer.toString(cube1));
                        choice = cube1;
                        button7.setEnabled(false);
                        button8.setEnabled(true);
                        break;
                    case (R.id.button8):
                        cube2 = random.nextInt(6) + 1;
                        button8.setText(Integer.toString(cube2));
                        choice = choice * 10 + cube2;
                        button8.setEnabled(false);
                        history++;
                        break;
                    case (R.id.buttonRec):
                        if (wwt) {
                            unit = new Thief(7, 1, 100);
                            wwt = false;
                            buttonPlay.setText("Заново");
                            buttonTrade.setText("Торг");
                            buttonRec.setText("Герой");
                            textView.append(event.thief);
                            event.Unit(unit.hp, unit.lvl, unit.gold);
                            textView.append(event.ch);
                            race = "Вор";
                        } else {
                            event.Unit(unit.hp, unit.lvl, unit.gold);
                            textView.append(event.ch);
                        }
                        break;
                    case (R.id.buttonPlay):
                        if (!wwt) {
                            textView.setText(event.begin);
                            button7.setEnabled(false);
                            button8.setEnabled(false);
                            buttonTrade.setEnabled(false);
                            buttonPlay.setText("Играть");
                            buttonRec.setText("Рекорды");
                            button7.setText("Кубик");
                            button8.setText("Кубик");
                            unit.lvl = 0;
                            unit.hp = 0;
                            unit.gold = 0;
                            history = 0;
                            play = false;
                            wwt = true;
                        } else {
                            unit = new Wizard(5, 3, 50);
                            wwt = false;
                            buttonPlay.setText("Заново");
                            buttonTrade.setText("Торг");
                            buttonRec.setText("Герой");
                            textView.append(event.wizard);
                            event.Unit(unit.hp, unit.lvl, unit.gold);
                            textView.append(event.ch);
                            race = "Маг";
                        }
                        break;
                    case (R.id.buttonTrade):
                        if (wwt) {
                            unit = new Warrior(12, 1, 10);
                            wwt = false;
                            buttonPlay.setText("Заново");
                            buttonTrade.setText("Торг");
                            buttonRec.setText("Герой");
                            textView.append(event.warrior);
                            event.Unit(unit.hp, unit.lvl, unit.gold);
                            textView.append(event.ch);
                            race = "Воин";
                        } else {
                            trader.trade(unit.gold);
                            textView.append(trader.trader);
                            button7.setText("1");
                            button8.setText("2");
                            buttonTrade.setText("Назад");
                            buttonRec.setEnabled(false);
                            buttonPlay.setEnabled(false);
                            buttonExit.setEnabled(true);
                            buttonTrade.setEnabled(true);
                            button7.setEnabled(true);
                            button8.setEnabled(true);
                            trade = true;
                        }
                        break;
                }
                serv = choice;
            } else if (!play) {
                Date currentDate = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                dateText = dateFormat.format(currentDate);
                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                timeText = timeFormat.format(currentDate);
                switch (v.getId()) {
                    case (R.id.buttonPlay):
                        textView.setText(event.rules);
                        buttonTrade.setText("Воин");
                        buttonPlay.setText("Маг");
                        buttonRec.setText("Вор");
                        buttonRec.setEnabled(true);
                        buttonPlay.setEnabled(true);
                        buttonExit.setEnabled(true);
                        buttonTrade.setEnabled(true);
                        button8.setEnabled(false);
                        button7.setEnabled(false);
                        play = true;
                        wwt = true;
                        break;
                    case (R.id.buttonRec):
                        if (rec) {
                            textView.setText("\n| Дата | Время | Итог | Раса | Шаги | Жизни | Уровень | Золото |\n\n");
                            textView.append(files);
                            buttonRec.setText("Назад");
                            rec = false;
                        } else {
                            textView.setText(event.begin);
                            buttonRec.setText("Рекорды");
                            rec = true;
                        }
                        break;
                }
            } else {
                choice = 0;
            }

            if (serv == 11 || serv == 66) {
                unit.hp -= 1;
                event.kill(unit.hp);
                textView.append(event.kill);
            } else if (serv == 21 || serv == 41 || serv == 61) {
                event.theft(unit.gold, unit.hp);
                if (unit.gold >= 30) {
                    unit.gold -= 30;
                } else {
                    unit.hp -= 2;
                }
                textView.append(event.theft);
            } else if (serv == 14 || serv == 23 || serv == 34 || serv == 43 || serv == 54 || serv == 63) {
                textView.append(event.nothing);
            } else if (serv == 13 || serv == 15 || serv == 31 || serv == 33 || serv == 35 || serv == 51 || serv == 53 || serv == 55) {
                gold = random.nextInt((unit.lvl * 5 + 1)%100 + 1) + 10;
                unit.lvl += 1;
                unit.gold += gold;
                event.Easy(gold);
                textView.append(event.easy);
            } else if (serv == 22 || serv == 24 || serv == 26 || serv == 42 || serv == 44 || serv == 46 || serv == 62 || serv == 64) {
                gold = random.nextInt((unit.lvl * 5 + 1)%100 + 1) + 10;
                textView.append(event.hard);
                buttonRec.setEnabled(false);
                buttonPlay.setEnabled(false);
                buttonExit.setEnabled(true);
                buttonTrade.setEnabled(false);
                button7.setEnabled(true);
                button8.setEnabled(true);
                cube = true;
            } else if (serv > 10 && serv < 67) {
                gold = random.nextInt((unit.lvl * 5 + 1)%100 + 1) + 10;
                unit.gold += gold;
                event.gold(gold);
                textView.append(event.goldd);
            }

            if (v.getId() == R.id.buttonExit) {
                this.finish();
            }

            if (unit.lvl >= 30) {
                textView.append(event.good);
                buttonRec.setEnabled(false);
                buttonPlay.setEnabled(true);
                buttonExit.setEnabled(true);
                buttonTrade.setEnabled(false);
                button7.setEnabled(false);
                button8.setEnabled(false);
                files += "" + "| " + dateText + " | " + timeText + " | ПОБЕДА | " + race + " | " + (int) (history / 2) + " | " + unit.hp + " | " + unit.lvl + " | " + unit.gold + " |\n\n";
            }

            if (unit.hp < 1 && unit.lvl > 0) {
                textView.append(event.bad);
                buttonRec.setEnabled(false);
                buttonPlay.setEnabled(true);
                buttonExit.setEnabled(true);
                buttonTrade.setEnabled(false);
                button7.setEnabled(false);
                button8.setEnabled(false);
                unit.hp = 0;
                files += "" + "| " + dateText + " | " + timeText + " | ПОРАЖЕНИЕ | " + race + " | " + history + " | " + unit.hp + " | " + unit.lvl + " | " + unit.gold + " |\n\n";
            }
        }
        scrollview.post(new Runnable() {
            @Override
            public void run() {
                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Destroy", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("Restart", "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Pause", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(flags);
        final View decorView = getWindow().getDecorView();
        decorView
                .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                            decorView.setSystemUiVisibility(flags);
                        }
                    }
                });
        Log.e("Resume", "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Start", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Stop", "onStop");
    }
}